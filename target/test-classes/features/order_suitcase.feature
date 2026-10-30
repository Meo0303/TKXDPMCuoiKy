Feature: đặt mua vali
Như là một khách hàng mua sắm tại VietLuggage
Tôi muốn biết tổng tiền của đơn hàng đặt mua vali
Để được hưởng ưu đãi giảm giá nhóm và đỡ phải tốn thời gian tính bằng giấy

Scenario Outline: đặt mua vali hợp lệ
Given số lượng vali <quantity> và đơn giá <unit_price>
When tôi thực hiện đặt mua vali
Then tôi được thông báo giá trị là <result> <color>
And lưu kết quả <save_result>
And ngày hiện tại <current_date>

Examples:

|current_date| quantity | unit_price | result | color | save_result | reason                     |
|30/10/2026  | 2        | 100        | 200    | pink  | true        | order is retail (< 3)      |
|30/10/2026  | 4        | 100        | 360    | red   | true        | order is bulk (>= 3)       |
|30/10/2026  | 10       | 200        | 1800   | red   | true        | order is bulk (>= 3)       |
|30/10/2026  | 25       | 100        | -1     | gray  | false       | exceeds limit 20 suitcases |