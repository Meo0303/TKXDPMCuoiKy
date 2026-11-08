package starter;

public class DatQuanAoController {
    private DatQuanAoService datQuanAoService;
    private Publisher publ;

    public DatQuanAoController(DatQuanAoService datQuanAoService, Publisher publ) {
        this.datQuanAoService = datQuanAoService;
        this.publ = publ;
    }

    public void control(String subTotal, String shippingFee) {
        double realSubTotal = -1;
        double realShippingFee = -1;
        try {
            realSubTotal = Double.parseDouble(subTotal.trim());
            realShippingFee = Double.parseDouble(shippingFee.trim());
        } catch (Exception e) {
            realSubTotal = -1;
            realShippingFee = -1;
        }
        datQuanAoService.execute(realSubTotal, realShippingFee);
        publ.notifySubscriber();
    }
}