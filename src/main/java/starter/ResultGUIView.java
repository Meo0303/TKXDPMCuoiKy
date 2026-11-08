package starter;

import javax.swing.JOptionPane;

public class ResultGUIView implements Subscriber {
    private DatQuanAoModelDTO modelDTO;

    public ResultGUIView(DatQuanAoModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    @Override
    public void update() {
        System.out.println("[KHOI TAO DON HANG] Tong thanh toan: " + modelDTO.presentResult
                + " | Mau: " + modelDTO.actualColor
                + " | Luu DB: " + modelDTO.actualSaveStatus
                + " | Ngay: " + modelDTO.VNDate
                + " | Kho InMemoryDBMock: " + modelDTO.savedOrders);

        String bgHex = "#d9d9d9";
        if ("pink".equals(modelDTO.actualColor)) {
            bgHex = "#ffd6eb";
        } else if ("red".equals(modelDTO.actualColor)) {
            bgHex = "#ffcccc";
        }

        String html = "<html><div style='background-color:" + bgHex + ";padding:12px;width:270px;font-family:sans-serif;'>"
                + "<h3 style='text-align:center;margin-top:0;'>KẾT QUẢ KHỞI TẠO ĐƠN HÀNG</h3>"
                + "<b>Tổng thanh toán (result):</b> " + modelDTO.presentResult + "<br/>"
                + "<b>Màu thông báo (color):</b> " + modelDTO.actualColor + "<br/>"
                + "<b>Lưu vào DB (save_result):</b> " + modelDTO.actualSaveStatus + "<br/>"
                + "<b>Ngày lập đơn (current_date):</b> " + modelDTO.VNDate + "<hr/>"
                + "<b>Kho InMemoryDBMock hiện có (" + modelDTO.savedOrders.size() + " đơn):</b><br/>"
                + modelDTO.savedOrders.toString()
                + "</div></html>";

        JOptionPane.showMessageDialog(null, html, "Thông báo đơn hàng - VietFashion", JOptionPane.INFORMATION_MESSAGE);
    }
}