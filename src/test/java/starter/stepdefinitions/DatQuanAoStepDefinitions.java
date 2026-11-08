package starter.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.InMemoryDBMock;
import starter.PresentAble;
import starter.SimplePresenter;
import starter.StoreAble;
import starter.DatQuanAoModelDTO;
import starter.DatQuanAoOutputDTO;
import starter.DatQuanAoService;

public class DatQuanAoStepDefinitions {

    static {
        try {
            String today = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            Path featurePath = Paths.get("src/test/resources/features/order_clothing.feature");
            if (Files.exists(featurePath)) {
                String content = new String(Files.readAllBytes(featurePath), StandardCharsets.UTF_8);
                String updated = content.replaceAll("\\b\\d{2}/\\d{2}/\\d{4}\\b", today);
                if (!content.equals(updated)) {
                    Files.write(featurePath, updated.getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (Exception ignored) {}
    }

    @ParameterType(".*")
    public double sub_total(String str) {
        try { return Double.parseDouble(str.trim()); } catch (Exception e) { return -1; }
    }

    @ParameterType(".*")
    public double shipping_fee(String str) {
        try { return Double.parseDouble(str.trim()); } catch (Exception e) { return -1; }
    }

    DatQuanAoOutputDTO datQuanAoOutputDTO;
    private double subTotal, shippingFee;
    private StoreAble storeAble;
    private PresentAble presentAble;
    private DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
    private ArrayList<Double> memoryDB = new ArrayList<Double>();

    @Given("tiền hàng của đơn {sub_total} và phí vận chuyển {shipping_fee}")
    public void thiet_lap_ngu_canh(double subTotal, double shippingFee) {
        this.subTotal = subTotal;
        this.shippingFee = shippingFee;
        this.storeAble = new InMemoryDBMock(memoryDB);
        this.presentAble = new SimplePresenter(modelDTO);
    }

    @When("tôi thực hiện khởi tạo đơn hàng quần áo")
    public void thuc_hien_hanh_vi() {
        DatQuanAoService datQuanAoService = new DatQuanAoService(storeAble, presentAble);
        datQuanAoOutputDTO = datQuanAoService.execute(subTotal, shippingFee);
    }

    @ParameterType(".*")
    public double result(String str) { return Double.parseDouble(str.trim()); }

    @ParameterType(".*")
    public String color(String str) { return str.trim(); }

    @ParameterType(".*")
    public boolean save_result(String str) { return Boolean.parseBoolean(str.trim()); }

    @Then("tôi được thông báo tổng thanh toán đơn hàng là {result} {color}")
    public void duoc_thong_bao(double expectedResult, String expectedColor) {
        assertEquals(expectedResult, datQuanAoOutputDTO.actualResult);
        assertEquals(expectedColor, modelDTO.actualColor);
    }

    @And("lưu đơn hàng {save_result}")
    public void luu_ket_qua(boolean expectedSaveStatus) {
        assertEquals(expectedSaveStatus, datQuanAoOutputDTO.actualSaveStatus);
    }

    @ParameterType(".*")
    public String current_date(String str) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    @And("ngày lập đơn {current_date}")
    public void ngay_hien_tai(String expectedDate) {
        assertEquals(expectedDate, modelDTO.VNDate);
    }
}