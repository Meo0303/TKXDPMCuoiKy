package starter;

public class DatQuanAoService {
    private StoreAble storeAble;
    private PresentAble presentAble;

    public DatQuanAoService(StoreAble storeAble, PresentAble presentAble) {
        this.storeAble = storeAble;
        this.presentAble = presentAble;
    }

    public DatQuanAoOutputDTO execute(double subTotal, double shippingFee) {
        ClothingOrder orderAble = new ClothingOrder();
        double actualResult = orderAble.calculateTotal(subTotal, shippingFee);
        boolean actualSaveStatus = false;
        if (actualResult >= 0) {
            actualSaveStatus = storeAble.save(actualResult);
        }
        DatQuanAoOutputDTO outputDTO = new DatQuanAoOutputDTO();
        outputDTO.actualResult = actualResult;
        outputDTO.actualSaveStatus = actualSaveStatus;
        outputDTO.priorityOrder = CheckPriorityOrderService.checkPriorityOrder(subTotal);
        presentAble.format(outputDTO);
        return outputDTO;
    }
}