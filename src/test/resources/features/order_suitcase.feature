Feature: đặt mua vali
Như là một khách hàng mua sắm tại VietLuggage
Tôi muốn biết tổng tiền của đơn hàng đặt mua vali
Để được hưởng ưu đãi giảm giá nhóm và đỡ phải tốn thời gian tính bằng giấy

Scenario Outline: đặt mua lẻ dưới 3 chiếc vali hợp lệ với mức giá tiêu chuẩn
Given số lượng vali <quantity> và đơn giá <unit_price>
When tôi thực hiện đặt mua vali
Then tôi được thông báo giá trị là <result> <color>
And lưu kết quả <save_result>
And ngày hiện tại <current_date>

Examples:
| current_date | quantity | unit_price | result | color | save_result | reason                          |
| 31/10/2026   | 1        | 150        | 150    | pink  | true        | mua le 1 chiec nguyen gia       |
| 31/10/2026   | 2        | 100        | 200    | pink  | true        | mua le 2 chiec nguyen gia       |

Scenario Outline: đặt mua theo nhóm từ 3 đến 20 chiếc vali hợp lệ được giảm giá 10 phần trăm
Given số lượng vali <quantity> và đơn giá <unit_price>
When tôi thực hiện đặt mua vali
Then tôi được thông báo giá trị là <result> <color>
And lưu kết quả <save_result>
And ngày hiện tại <current_date>

Examples:
| current_date | quantity | unit_price | result | color | save_result | reason                          |
| 31/10/2026   | 3        | 100        | 270    | red   | true        | mua nhom 3 chiec giam 10%       |
| 31/10/2026   | 4        | 100        | 360    | red   | true        | mua nhom 4 chiec giam 10%       |
| 31/10/2026   | 10       | 200        | 1800   | red   | true        | mua nhom 10 chiec giam 10%      |
| 31/10/2026   | 20       | 100        | 1800   | red   | true        | mua nhom cham moc toi da 20     |

Scenario Outline: đặt mua vali thất bại khi số lượng vượt quá giới hạn 20 chiếc mỗi đơn
Given số lượng vali <quantity> và đơn giá <unit_price>
When tôi thực hiện đặt mua vali
Then tôi được thông báo giá trị là <result> <color>
And lưu kết quả <save_result>
And ngày hiện tại <current_date>

Examples:
| current_date | quantity | unit_price | result | color | save_result | reason                          |
| 31/10/2026   | 21       | 100        | -1     | gray  | false       | vuot qua gioi han 20 chiec      |
| 31/10/2026   | 25       | 100        | -1     | gray  | false       | vuot qua gioi han 20 chiec      |

Scenario Outline: đặt mua vali thất bại khi dữ liệu đầu vào không hợp lệ hoặc bằng không
Given số lượng vali <quantity> và đơn giá <unit_price>
When tôi thực hiện đặt mua vali
Then tôi được thông báo giá trị là <result> <color>
And lưu kết quả <save_result>
And ngày hiện tại <current_date>

Examples:
| current_date | quantity | unit_price | result | color | save_result | reason                          |
| 31/10/2026   | 0        | 100        | -1     | gray  | false       | so luong bang 0 khong hop le    |
| 31/10/2026   | -2       | 100        | -1     | gray  | false       | so luong am khong hop le        |
| 31/10/2026   | 2        | -100       | -1     | gray  | false       | don gia am khong hop le         |
| 31/10/2026   | abc      | 100        | -1     | gray  | false       | sai dinh dang so khong hop le   |