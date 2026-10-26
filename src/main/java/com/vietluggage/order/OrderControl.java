package com.vietluggage.order;

public class OrderControl {
    private final Saving saving;
    private final Showing showing;

    public OrderControl(Saving saving, Showing showing) {
        this.saving = saving;
        this.showing = showing;
    }

    public void control(OrderInputDTO input) {
        if (input == null || isBlank(input.rawQuantity) || isBlank(input.rawUnitPrice)) {
            publishError("Không hoàn thành tác vụ đặt mua vali: Dữ liệu đầu vào không hợp lệ");
            return;
        }

        try {
            int quantity = Integer.parseInt(input.rawQuantity.trim());
            double unitPrice = Double.parseDouble(input.rawUnitPrice.trim());

            SuitcaseOrder orderEntity = new SuitcaseOrder(quantity, unitPrice);

            if (!orderEntity.isValidPositiveInput()) {
                publishError("Không hoàn thành tác vụ đặt mua vali: Dữ liệu đầu vào không hợp lệ");
                return;
            }

            if (!orderEntity.isWithinLimit()) {
                publishError("Không hoàn thành tác vụ đặt mua vali: Vượt quá giới hạn 20 chiếc mỗi đơn");
                return;
            }

            double finalTotal = orderEntity.calcTotal();
            boolean bulk = orderEntity.isBulkOrder();

            OrderOutputDTO successOutput = new OrderOutputDTO(
                finalTotal, bulk, true, "Hoàn thành tác vụ đặt mua vali"
            );

            saving.save(successOutput);
            showing.showResult(successOutput);

        } catch (NumberFormatException e) {
            publishError("Không hoàn thành tác vụ đặt mua vali: Dữ liệu đầu vào không hợp lệ");
        }
    }

    private void publishError(String errorMsg) {
        OrderOutputDTO errorOutput = new OrderOutputDTO(null, false, false, errorMsg);
        showing.showResult(errorOutput);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
