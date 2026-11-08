package starter;

public class CheckPriorityOrderService {
    public static boolean checkPriorityOrder(double subTotal) {
        return (subTotal >= 500 && subTotal <= 5000);
    }
}