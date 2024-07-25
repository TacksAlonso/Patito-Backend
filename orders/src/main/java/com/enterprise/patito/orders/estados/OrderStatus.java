package com.enterprise.patito.orders.estados;

public enum OrderStatus {

    PENDIENTE("E-P", "Pendiente"),
    CERRADO("E-C", "Cancelado"),
    ENTREGADO("E-E", "Entregado");

    private final String code;
    private final String description;

    OrderStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromCode(String code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado invalido: " + code);
    }
}
