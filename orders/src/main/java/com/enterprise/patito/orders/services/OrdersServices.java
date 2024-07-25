package com.enterprise.patito.orders.services;

import com.enterprise.patito.orders.dto.OrderDTO;
import com.enterprise.patito.orders.dto.ProductDTO;
import com.enterprise.patito.orders.entity.Audits;
import com.enterprise.patito.orders.entity.OrderItems;
import com.enterprise.patito.orders.entity.Orders;
import com.enterprise.patito.orders.estados.OrderStatus;
import com.enterprise.patito.orders.repository.OrdersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrdersServices {

    private static final Logger logger = LoggerFactory.getLogger(OrdersServices.class);

    private static final String INVENTORY_SERVICES_URL = "http://inventory-services/v1/";
    private static final String COMPLEMENT_PRODUCT_URL = "products/{id}";
    private static final String COMPLEMENT_STOCK_FIND_URL = "stock?productId={productId}&quantity={quantity}";
    private static final String CUSTOMERS_SERVICE_FIND_ID_URL = "http://customers-services/v1/customers/{id}";


    @Autowired
    private OrdersRepository orderRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private RestTemplate restTemplate;

    public List<OrderDTO> getAllOrders() {
        List<Orders> allOrders = orderRepository.findAll();

        return allOrders.stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderDTOById(Long id) {
        Orders orders = orderRepository.findById(id).orElse(null);
        if (orders == null)
            return null;
        return convertToOrderDTO(orders);
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }


    public Orders createOrder(Orders order) {
        logger.info("Creating order: {}", order);

        order.setCreateDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());

        String userIp = request.getRemoteAddr();

        try {

            for (OrderItems item : order.getOrderItems()) {
                item.setOrder(order);
                restTemplate.exchange(INVENTORY_SERVICES_URL+COMPLEMENT_STOCK_FIND_URL, HttpMethod.PUT, getToken(), Void.class, item.getHawa(), -1 * item.getQuantity());
            }

        } catch (Exception e) {
            throw new RuntimeException("Hubo un error al asingar inventario se cancela el pedido", e);
        }


        for (Audits audit : order.getAudits()) {
            audit.setOrder(order);
            audit.setDate(LocalDateTime.now());
            audit.setUserIp(userIp);
        }

        logger.info("Creating order: {}", order);

        return orderRepository.save(order);
    }

    public Orders updateOrder(Long id, String state) {

        Orders order = orderRepository.findById(id).orElse(null);

        if (order == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado");

        if (Objects.equals(state, OrderStatus.CERRADO.getCode()) && order.getCreateDate().isBefore(LocalDateTime.now().minusMinutes(10)))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La orden no puede ser cancelada debido a que ya se supero el tiempo limite desde su creacion: "+ order.getCreateDate());

        if (Objects.equals(state, OrderStatus.CERRADO.getCode())){
            try {

                for (OrderItems item : order.getOrderItems()) {
                    item.setOrder(order);
                    restTemplate.exchange(INVENTORY_SERVICES_URL+COMPLEMENT_STOCK_FIND_URL, HttpMethod.PUT, getToken(), Void.class, item.getHawa(), item.getQuantity());
                }

            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La orden no puede ser cancelada debido a que ya se hubo un error al regresar el inventario: "+ order.getCreateDate());
            }

        }


        order.setUpdateDate(LocalDateTime.now());

        order.setStatus(state);

        return orderRepository.save(order);

    }

    private HttpEntity<String> getToken(){
        String token = request.getHeader("Authorization");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

    private String getStatusForCode(String code){
        OrderStatus orderStatus = OrderStatus.fromCode(code);
        return orderStatus.getDescription();
    }

    private OrderDTO convertToOrderDTO(Orders order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());

        OrderDTO.StoreDTO stores = new OrderDTO.StoreDTO(order.getStore().getId(), order.getStore().getName(), order.getStore().getDescription());

        orderDTO.setStore(stores);

        ResponseEntity<OrderDTO.CustomerDTO> customerResponse = restTemplate.exchange(
                CUSTOMERS_SERVICE_FIND_ID_URL,
                HttpMethod.GET,
                getToken(),
                OrderDTO.CustomerDTO.class,
                order.getCustomer()
        );

        orderDTO.setCustomer(customerResponse.getBody());

        orderDTO.setSalesPersonName(order.getSalesPersonName());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setStatusDescription(getStatusForCode(order.getStatus()));
        orderDTO.setCreateDate(order.getCreateDate());
        orderDTO.setUpdateDate(order.getUpdateDate());

        List<OrderDTO.OrderItemDTO> orderItemsDTO = order.getOrderItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList());
        orderDTO.setOrderItems(orderItemsDTO);

        List<OrderDTO.AuditDTO> auditsDTO = order.getAudits().stream()
                .map(this::convertToAuditDTO)
                .collect(Collectors.toList());
        orderDTO.setAudits(auditsDTO);

        return orderDTO;
    }

    private OrderDTO.OrderItemDTO convertToOrderItemDTO(OrderItems orderItem) {
        OrderDTO.OrderItemDTO dto = new OrderDTO.OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setOrderItem(orderItem.getOrderItem());
        dto.setHawa(orderItem.getHawa());

        ResponseEntity<ProductDTO> productResponse = restTemplate.exchange(
                INVENTORY_SERVICES_URL+COMPLEMENT_PRODUCT_URL,
                HttpMethod.GET,
                getToken(),
                ProductDTO.class,
                orderItem.getHawa()
        );

        ProductDTO productDTO = productResponse.getBody();
        if (!(productDTO ==null))
            dto.setName(productDTO.getName());

        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        dto.setDiscount(orderItem.getDiscount());
        return dto;
    }

    private OrderDTO.AuditDTO convertToAuditDTO(Audits audit) {
        OrderDTO.AuditDTO dto = new OrderDTO.AuditDTO();
        dto.setId(audit.getId());
        dto.setDate(audit.getDate());
        dto.setUserId(audit.getUserId());
        dto.setUserIp(audit.getUserIp());
        return dto;
    }
}
