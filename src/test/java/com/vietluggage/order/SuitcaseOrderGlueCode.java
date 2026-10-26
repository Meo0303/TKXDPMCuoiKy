package com.vietluggage.order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;

public class SuitcaseOrderGlueCode {
    private OrderInputDTO inputDTO;
    private IOSaveToMemory memoryStorage;
    private OrderViewModel viewModel;
    private OrderGUIPresenter presenter;
    private OrderControl orderControl;

    private void initTracerBulletSystem() {
        memoryStorage = new IOSaveToMemory();
        viewModel = new OrderViewModel();
        presenter = new OrderGUIPresenter(viewModel);
        orderControl = new OrderControl(memoryStorage, presenter);
    }

    @Given("khách hàng chọn mua số lượng vali là {string} với đơn giá mỗi chiếc là {string}")
    public void khachHangChonMuaVali(String quantity, String unitPrice) {
        initTracerBulletSystem();
        inputDTO = new OrderInputDTO(quantity, unitPrice);
    }

    @When("khách hàng xác nhận đặt mua vali")
    @When("khách hàng cố gắng xác nhận đặt mua vali")
    public void khachHangXacNhanDatMuaVali() {
        orderControl.control(inputDTO);
    }

    @Then("hệ thống thông báo trạng thái {string}")
    public void heThongThongBaoTrangThai(String expectedStatus) {
        assertEquals(expectedStatus, viewModel.statusMessage);
    }

    @Then("hóa đơn với tổng tiền {string} được lưu trữ thành công")
    public void hoaDonDuocLuuTruThanhCong(String expectedTotal) {
        assertTrue(memoryStorage.getSavedInvoices().contains(expectedTotal));
    }

    @Then("không có hóa đơn nào được lưu trữ vào hệ thống")
    public void khongCoHoaDonNaoDuocLuuTru() {
        assertTrue(memoryStorage.getSavedInvoices().isEmpty());
    }

    @Then("kết quả tổng tiền hiển thị trên giao diện là {string} với màu {string}")
    public void ketQuaTongTienHienThiTrenGiaoDien(String expectedText, String expectedColor) {
        assertEquals(expectedText, viewModel.totalText);
        assertEquals(expectedColor, viewModel.color);
    }
}