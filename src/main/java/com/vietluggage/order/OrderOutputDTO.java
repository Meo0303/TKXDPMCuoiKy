package com.vietluggage.order;

public class OrderOutputDTO {
    public final Double totalAmount;
    public final boolean isBulkOrder;
    public final boolean isSuccess;
    public final String message;

    public OrderOutputDTO(Double totalAmount, boolean isBulkOrder, boolean isSuccess, String message) {
        this.totalAmount = totalAmount;
        this.isBulkOrder = isBulkOrder;
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
