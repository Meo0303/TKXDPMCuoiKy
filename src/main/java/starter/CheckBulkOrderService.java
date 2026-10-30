package starter;

public class CheckBulkOrderService {
    public static boolean checkBulkOrder(double quantity) {
        return (quantity >= 3 && quantity <= 20) ? true : false;
    }

}