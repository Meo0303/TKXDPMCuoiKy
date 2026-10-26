package com.vietluggage.order;

public class OrderInputDTO {
    public final String rawQuantity;
    public final String rawUnitPrice;

    public OrderInputDTO(String rawQuantity, String rawUnitPrice) {
        this.rawQuantity = rawQuantity;
        this.rawUnitPrice = rawUnitPrice;
    }
}
