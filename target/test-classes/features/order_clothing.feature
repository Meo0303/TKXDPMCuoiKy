Feature: khởi tạo và xử lý đơn hàng quần áo
Như là một khách hàng chốt đơn tại hệ thống VietFashion
Tôi muốn khởi tạo đơn hàng và biết tổng thanh toán cuối cùng kèm ưu đãi vận chuyển
Để đơn hàng hợp lệ được lưu trữ vào hệ thống và đơn hàng lớn được miễn phí giao hàng

Scenario Outline: khởi tạo đơn hàng tiêu chuẩn từ 50 đến dưới 500 nghìn hợp lệ có tính phí vận chuyển
Given tiền hàng của đơn <sub_total> và phí vận chuyển <shipping_fee>
When tôi thực hiện khởi tạo đơn hàng quần áo
Then tôi được thông báo tổng thanh toán đơn hàng là <result> <color>
And lưu đơn hàng <save_result>
And ngày lập đơn <current_date>

Examples:
| current_date | sub_total | shipping_fee | result | color | save_result | reason                              |
| 08/11/2026   | 50        | 25           | 75     | pink  | true        | don toi thieu 50k cong ship 25k     |
| 08/11/2026   | 200       | 30           | 230    | pink  | true        | don tieu chuan 200k cong ship 30k   |
| 08/11/2026   | 499       | 30           | 529    | pink  | true        | sat moc 500k van tinh ship          |

Scenario Outline: khởi tạo đơn hàng ưu tiên từ 500 đến 5000 nghìn hợp lệ được miễn phí vận chuyển
Given tiền hàng của đơn <sub_total> và phí vận chuyển <shipping_fee>
When tôi thực hiện khởi tạo đơn hàng quần áo
Then tôi được thông báo tổng thanh toán đơn hàng là <result> <color>
And lưu đơn hàng <save_result>
And ngày lập đơn <current_date>

Examples:
| current_date | sub_total | shipping_fee | result | color | save_result | reason                              |
| 08/11/2026   | 500       | 30           | 500    | red   | true        | cham moc 500k duoc freeship         |
| 08/11/2026   | 1200      | 40           | 1200   | red   | true        | don uu tien 1200k duoc freeship     |
| 08/11/2026   | 5000      | 50           | 5000   | red   | true        | don cham tran 5000k duoc freeship   |

Scenario Outline: từ chối khởi tạo đơn hàng khi tiền hàng ngoài hạn mức từ 50 đến 5000 nghìn
Given tiền hàng của đơn <sub_total> và phí vận chuyển <shipping_fee>
When tôi thực hiện khởi tạo đơn hàng quần áo
Then tôi được thông báo tổng thanh toán đơn hàng là <result> <color>
And lưu đơn hàng <save_result>
And ngày lập đơn <current_date>

Examples:
| current_date | sub_total | shipping_fee | result | color | save_result | reason                              |
| 08/11/2026   | 49        | 30           | -1     | gray  | false       | duoi muc don toi thieu 50k          |
| 08/11/2026   | 5001      | 30           | -1     | gray  | false       | vuot han muc an toan 5000k          |

Scenario Outline: từ chối khởi tạo đơn hàng khi phí vận chuyển hoặc dữ liệu đầu vào không hợp lệ
Given tiền hàng của đơn <sub_total> và phí vận chuyển <shipping_fee>
When tôi thực hiện khởi tạo đơn hàng quần áo
Then tôi được thông báo tổng thanh toán đơn hàng là <result> <color>
And lưu đơn hàng <save_result>
And ngày lập đơn <current_date>

Examples:
| current_date | sub_total | shipping_fee | result | color | save_result | reason                              |
| 08/11/2026   | 300       | 0            | -1     | gray  | false       | phi van chuyen bang 0               |
| 08/11/2026   | 300       | 250          | -1     | gray  | false       | phi van chuyen vuot tran 200k       |
| 08/11/2026   | abc       | 30           | -1     | gray  | false       | sai dinh dang so                    |