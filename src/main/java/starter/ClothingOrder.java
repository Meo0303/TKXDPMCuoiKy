package starter;

public class ClothingOrder {
    public double calculateTotal(double subTotal, double shippingFee) {
        if (subTotal < 50 || subTotal > 5000 || shippingFee <= 0 || shippingFee > 200) {
            return -1;
        }
        if (CheckPriorityOrderService.checkPriorityOrder(subTotal)) {
            return subTotal;
        }
        return subTotal + shippingFee;
    }
}