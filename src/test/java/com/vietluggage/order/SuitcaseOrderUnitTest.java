package com.vietluggage.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SuitcaseOrderUnitTest {

    // 1. ĐẶC TẢ CÁC HÀM CỦA DOMAIN ENTITY (SuitcaseOrder)
    @Test
    @DisplayName("Unit Test 1: Đặc tả hàm calcTotal() và isBulkOrder() khi mua lẻ (< 3 vali, không giảm giá)")
    void shouldCalculateStandardTotalForRetailOrder() {
        SuitcaseOrder order = new SuitcaseOrder(2, 100.0);
        assertTrue(order.isWithinLimit());
        assertFalse(order.isBulkOrder(), "Mua 2 vali là đơn lẻ");
        assertEquals(200.0, order.calcTotal(), "2 * 100 phải bằng 200");
    }

    @Test
    @DisplayName("Unit Test 2: Đặc tả hàm calcTotal() và isBulkOrder() khi mua theo nhóm (>= 3 vali, giảm 10%)")
    void shouldApplyTenPercentDiscountForBulkOrder() {
        SuitcaseOrder order = new SuitcaseOrder(4, 100.0);
        assertTrue(order.isWithinLimit());
        assertTrue(order.isBulkOrder(), "Mua từ 3 vali trở lên là đơn nhóm");
        assertEquals(360.0, order.calcTotal(), "4 * 100 giảm 10% phải còn 360");
    }

    @Test
    @DisplayName("Unit Test 3: Đặc tả hàm isWithinLimit() khi số lượng vượt quá trần 20 chiếc")
    void shouldRejectOrderExceedingTwentySuitcases() {
        SuitcaseOrder order = new SuitcaseOrder(25, 100.0);
        assertFalse(order.isWithinLimit(), "25 chiếc vượt quá giới hạn 20 chiếc mỗi đơn");
    }

    // 2. ĐẶC TẢ PRESENTER (Mẫu Humble Object)
    @Test
    @DisplayName("Unit Test 4 [Humble Object]: OrderGUIPresenter định dạng đơn nhóm thành text màu RED")
    void presenterShouldFormatBulkOrderInRed() {
        OrderViewModel model = new OrderViewModel();
        OrderGUIPresenter presenter = new OrderGUIPresenter(model);
        OrderOutputDTO output = new OrderOutputDTO(360.0, true, true, "Hoàn thành tác vụ đặt mua vali");

        presenter.showResult(output);

        assertEquals("360", model.totalText);
        assertEquals("RED", model.color);
        assertEquals("Hoàn thành tác vụ đặt mua vali", model.statusMessage);
    }

    @Test
    @DisplayName("Unit Test 5 [Humble Object]: OrderGUIPresenter định dạng đơn lẻ thành text màu PINK")
    void presenterShouldFormatRetailOrderInPink() {
        OrderViewModel model = new OrderViewModel();
        OrderGUIPresenter presenter = new OrderGUIPresenter(model);
        OrderOutputDTO output = new OrderOutputDTO(200.0, false, true, "Hoàn thành tác vụ đặt mua vali");

        presenter.showResult(output);

        assertEquals("200", model.totalText);
        assertEquals("PINK", model.color);
        assertEquals("Hoàn thành tác vụ đặt mua vali", model.statusMessage);
    }

    // 3. ĐẶC TẢ LUỒNG ĐIỀU PHỐI CỦA SERVICE (OrderControl) & KẾT NỐI DBMOCK
    @Test
    @DisplayName("Unit Test 6: Đặc tả OrderControl khi input rỗng phải báo lỗi và không lưu vào DBMock")
    void controlShouldNotSaveWhenInputIsInvalid() {
        IOSaveToMemory memory = new IOSaveToMemory();
        OrderViewModel model = new OrderViewModel();
        OrderGUIPresenter presenter = new OrderGUIPresenter(model);
        OrderControl control = new OrderControl(memory, presenter);

        control.control(new OrderInputDTO("", "100"));

        assertTrue(memory.getDbMock().isConnected());
        assertTrue(memory.getSavedInvoices().isEmpty());
        assertEquals("N/A", model.totalText);
        assertEquals("GRAY", model.color);
        assertEquals("Không hoàn thành tác vụ đặt mua vali: Dữ liệu đầu vào không hợp lệ", model.statusMessage);
    }

    @Test
    @DisplayName("Unit Test 7 [DBMock]: Kiểm chứng OrderControl lưu bản ghi hóa đơn thành công vào DBMock")
    void controlShouldInsertInvoiceRecordIntoDBMock() {
        DBMock dbMock = new DBMock();
        IOSaveToMemory repository = new IOSaveToMemory(dbMock);
        OrderViewModel model = new OrderViewModel();
        OrderGUIPresenter presenter = new OrderGUIPresenter(model);
        OrderControl control = new OrderControl(repository, presenter);

        control.control(new OrderInputDTO("4", "100"));

        assertTrue(dbMock.isConnected(), "Phải kết nối thành công với DBMock");
        assertEquals(1, dbMock.selectAll().size(), "Bảng suitcase_invoices trong DBMock phải có 1 bản ghi");
        assertEquals("360", dbMock.selectAll().get(0).totalAmount);
        assertTrue(dbMock.selectAll().get(0).isBulkOrder);
    }
}