package com.vietluggage.order;

public class OrderGUIPresenter implements Showing {
    private final OrderViewModel model;

    public OrderGUIPresenter(OrderViewModel model) {
        this.model = model;
    }

    @Override
    public void showResult(OrderOutputDTO outputData) {
        model.statusMessage = outputData.message;
        if (!outputData.isSuccess || outputData.totalAmount == null) {
            model.totalText = "N/A";
            model.color = "GRAY";
            return;
        }
        model.totalText = String.format("%.0f", outputData.totalAmount);
        model.color = outputData.isBulkOrder ? "RED" : "PINK";
    }
}
