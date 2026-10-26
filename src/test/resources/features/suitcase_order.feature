Feature: Đặt mua vali du lịch và tính tổng tiền đơn hàng
  In order to mua được vali du lịch với mức giá chính xác, nhận ưu đãi khi mua theo nhóm và lưu trữ hóa đơn an toàn
  As a khách hàng mua sắm tại hệ thống VietLuggage
  I want to đặt mua một số lượng vali theo đơn giá niêm yết để biết tổng tiền cần thanh toán và trạng thái xử lý đơn hàng

  Rule: Đơn hàng hợp lệ tối đa 20 chiếc được giảm 10% nếu mua từ 3 chiếc trở lên, lưu hóa đơn và phân loại màu hiển thị

    Scenario: Đặt mua lẻ dưới 3 chiếc vali thành công với mức giá tiêu chuẩn
      Given khách hàng chọn mua số lượng vali là "2" với đơn giá mỗi chiếc là "100"
      When khách hàng xác nhận đặt mua vali
      Then hệ thống thông báo trạng thái "Hoàn thành tác vụ đặt mua vali"
      And hóa đơn với tổng tiền "200" được lưu trữ thành công
      And kết quả tổng tiền hiển thị trên giao diện là "200" với màu "PINK"

    Scenario: Đặt mua theo nhóm từ 3 chiếc vali trở lên được giảm giá 10 phần trăm và ưu tiên màu đỏ
      Given khách hàng chọn mua số lượng vali là "4" với đơn giá mỗi chiếc là "100"
      When khách hàng xác nhận đặt mua vali
      Then hệ thống thông báo trạng thái "Hoàn thành tác vụ đặt mua vali"
      And hóa đơn với tổng tiền "360" được lưu trữ thành công
      And kết quả tổng tiền hiển thị trên giao diện là "360" với màu "RED"

    Scenario: Đặt mua vali thất bại khi số lượng vượt quá giới hạn 20 chiếc mỗi đơn
      Given khách hàng chọn mua số lượng vali là "25" với đơn giá mỗi chiếc là "100"
      When khách hàng cố gắng xác nhận đặt mua vali
      Then hệ thống thông báo trạng thái "Không hoàn thành tác vụ đặt mua vali: Vượt quá giới hạn 20 chiếc mỗi đơn"
      And không có hóa đơn nào được lưu trữ vào hệ thống
      And kết quả tổng tiền hiển thị trên giao diện là "N/A" với màu "GRAY"

    Scenario: Đặt mua vali thất bại khi dữ liệu số lượng đầu vào bị bỏ trống hoặc không hợp lệ
      Given khách hàng chọn mua số lượng vali là "" với đơn giá mỗi chiếc là "100"
      When khách hàng cố gắng xác nhận đặt mua vali
      Then hệ thống thông báo trạng thái "Không hoàn thành tác vụ đặt mua vali: Dữ liệu đầu vào không hợp lệ"
      And không có hóa đơn nào được lưu trữ vào hệ thống
      And kết quả tổng tiền hiển thị trên giao diện là "N/A" với màu "GRAY"

    Scenario Outline: Kiểm chứng công thức tính tổng tiền vali và phân loại màu sắc theo số lượng đặt
      Given khách hàng chọn mua số lượng vali là "<quantity>" với đơn giá mỗi chiếc là "<unit_price>"
      When khách hàng xác nhận đặt mua vali
      Then hệ thống thông báo trạng thái "<status>"
      And kết quả tổng tiền hiển thị trên giao diện là "<total_display>" với màu "<color>"

      Examples:
        | quantity | unit_price | total_display | color | status                         |
        | 1        | 150        | 150           | PINK  | Hoàn thành tác vụ đặt mua vali |
        | 2        | 120        | 240           | PINK  | Hoàn thành tác vụ đặt mua vali |
        | 3        | 100        | 270           | RED   | Hoàn thành tác vụ đặt mua vali |
        | 10       | 200        | 1800          | RED   | Hoàn thành tác vụ đặt mua vali |
