package starter;

public class DatValiController {
    private DatValiService datValiService;
    private Publisher publ;

    public DatValiController(DatValiService datValiService,
            Publisher publ) {
        this.datValiService = datValiService;
        this.publ = publ;
    }

    public void control(String quantity, String unitPrice) {
        double realQuantity = Double.parseDouble(quantity);
        double realUnitPrice = Double.parseDouble(unitPrice);
        datValiService.execute(realQuantity, realUnitPrice);
        publ.notifySubscriber();
    }

}