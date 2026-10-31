package starter.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.InMemoryDBMock;
import starter.PresentAble;
import starter.SimplePresenter;
import starter.StoreAble;
import starter.DatValiModelDTO;
import starter.DatValiOutputDTO;
import starter.DatValiService;

public class DatValiStepDefinitions {

    @ParameterType(".*")
    public double quantity(String str) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    @ParameterType(".*")
    public double unit_price(String str) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    DatValiOutputDTO datValiOutputDTO;
    private double quantity, unitPrice;

    private StoreAble storeAble;
    private PresentAble presentAble;
    private DatValiModelDTO modelDTO = new DatValiModelDTO();
    private ArrayList<Double> memoryDB = new ArrayList<Double>();

    @Given("số lượng vali {quantity} và đơn giá {unit_price}")
    public void thiet_lap_ngu_canh(double quantity, double unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;

        this.storeAble = new InMemoryDBMock(memoryDB);
        this.presentAble = new SimplePresenter(modelDTO);
    }

    @When("tôi thực hiện đặt mua vali")
    public void thuc_hien_hanh_vi() {
        DatValiService datValiService = new DatValiService(storeAble, presentAble);
        datValiOutputDTO = datValiService.execute(quantity, unitPrice);
    }

    @ParameterType(".*")
    public double result(String str) {
        return Double.parseDouble(str.trim());
    }

    @ParameterType(".*")
    public String color(String str) {
        return str.trim();
    }

    @ParameterType(".*")
    public boolean save_result(String str) {
        return Boolean.parseBoolean(str.trim());
    }

    @Then("tôi được thông báo giá trị là {result} {color}")
    public void duoc_thong_bao(double expectedResult, String expectedColor) {
        assertEquals(expectedResult, datValiOutputDTO.actualResult);
        assertEquals(expectedColor, modelDTO.actualColor);

    }

    @And("lưu kết quả {save_result}")
    public void luu_ket_qua(boolean expectedSaveStatus) {
        assertEquals(expectedSaveStatus, datValiOutputDTO.actualSaveStatus);

    }

    @ParameterType(".*")
    public String current_date(String str) {
        return str.trim();
    }

    @And("ngày hiện tại {current_date}")
    public void ngay_hien_tai(String expectedDate) {
        assertEquals(expectedDate, modelDTO.VNDate);
    }

}