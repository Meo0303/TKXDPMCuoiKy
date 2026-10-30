package starter;

public class DatValiService {// cấp cao nhì
    private StoreAble storeAble;
    private PresentAble presentAble;

    public DatValiService(StoreAble storeAble, PresentAble presentAble) {
        this.storeAble = storeAble;
        this.presentAble = presentAble;
    }

    public DatValiOutputDTO execute(double quantity, double unitPrice) {

        // tất cả api đã có rồi
        // api - nghiệp vụ cốt lõi - độc lập phần mềm: cao / thấp
        SuitcaseOrder orderAble = new SuitcaseOrder();
        double actualResult = orderAble.calculateTotal(quantity, unitPrice);
        // tính tổng tiền đặt mua vali
        // api lưu : cao / thấp
        boolean actualSaveStatus = false;
        if (actualResult >= 0) {
            actualSaveStatus = storeAble.save(actualResult);
        }
        DatValiOutputDTO outputDTO = new DatValiOutputDTO();
        outputDTO.actualResult = actualResult;
        outputDTO.actualSaveStatus = actualSaveStatus;
        // check bulk order (>= 3 chiếc)
        boolean bulkOrder = CheckBulkOrderService.checkBulkOrder(quantity);
        // api tạo màu đỏ / hồng : cao / thấp
        outputDTO.bulkOrder = bulkOrder;
        presentAble.format(outputDTO);

        return outputDTO;

    }

}