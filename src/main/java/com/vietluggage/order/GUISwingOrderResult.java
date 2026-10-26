package com.vietluggage.order;

public class GUISwingOrderResult {
    private final OrderViewModel model;

    public GUISwingOrderResult(OrderViewModel model) {
        this.model = model;
    }

    public void showResult() {
        System.out.println("[GUI Swing Render] " + model.statusMessage + " | Total: " + model.totalText + " | Color: " + model.color);
    }
}
