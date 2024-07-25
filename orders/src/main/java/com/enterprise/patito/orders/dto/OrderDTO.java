package com.enterprise.patito.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private StoreDTO store;
    private CustomerDTO customer;
    private String salesPersonName;
    private String status;
    private String statusDescription;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<OrderItemDTO> orderItems;
    private List<AuditDTO> audits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StoreDTO {
        private Long id;
        private String name;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerDTO {
        private Long id;
        private String name;
        private String lastname;
        private String phone;
        private String email;
        private String address;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemDTO {
        private Long id;
        private Integer orderItem;
        private String hawa;
        private String name;
        private Integer quantity;
        private Double price;
        private Double discount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AuditDTO {
        private Long id;
        private LocalDateTime date;
        private String userId;
        private String userIp;
    }
}
