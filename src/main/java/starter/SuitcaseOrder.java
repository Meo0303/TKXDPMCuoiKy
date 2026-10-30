package starter;

public class SuitcaseOrder {

    public double calculateTotal(double quantity, double unitPrice) {
        if (quantity <= 0 || quantity > 20 || unitPrice <= 0) {
            return -1;
        }
        double actualResult = quantity * unitPrice;
        if (quantity >= 3) {
            actualResult = actualResult * 0.9;
        }
        return actualResult;
    }

}