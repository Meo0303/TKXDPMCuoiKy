package com.vietluggage.order;

public class SuitcaseOrder {
    public static final int MAX_QUANTITY_PER_ORDER = 20;
    public static final int BULK_DISCOUNT_THRESHOLD = 3;
    public static final double BULK_DISCOUNT_RATE = 0.90;

    private final int quantity;
    private final double unitPrice;

    public SuitcaseOrder(int quantity, double unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public boolean isValidPositiveInput() {
        return this.quantity > 0 && this.unitPrice > 0;
    }

    public boolean isWithinLimit() {
        return this.quantity <= MAX_QUANTITY_PER_ORDER;
    }

    public boolean isBulkOrder() {
        return this.quantity >= BULK_DISCOUNT_THRESHOLD;
    }

    public double calcTotal() {
        double baseTotal = this.quantity * this.unitPrice;
        return isBulkOrder() ? (baseTotal * BULK_DISCOUNT_RATE) : baseTotal;
    }
}
