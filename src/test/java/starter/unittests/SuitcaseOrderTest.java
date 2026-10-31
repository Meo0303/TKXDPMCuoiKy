package starter.unittests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import starter.SuitcaseOrder;

public class SuitcaseOrderTest {

    @Test
    void testCalculateTotal_RetailOrderUnder3() {
        // Given: Mua lẻ dưới 3 chiếc (không giảm giá)
        SuitcaseOrder orderAble = new SuitcaseOrder();
        // When
        double actualResult = orderAble.calculateTotal(2, 100);
        // Then
        assertEquals(200, actualResult);
    }

    @Test
    void testCalculateTotal_BulkOrderFrom3To20() {
        // Given: Mua theo nhóm từ 3 đến 20 chiếc (giảm 10%)
        SuitcaseOrder orderAble = new SuitcaseOrder();
        // When
        double actualResult = orderAble.calculateTotal(4, 100);
        // Then
        assertEquals(360, actualResult);
    }

    @Test
    void testCalculateTotal_ExceedLimitOver20() {
        // Given: Vượt quá giới hạn 20 chiếc mỗi đơn
        SuitcaseOrder orderAble = new SuitcaseOrder();
        // When
        double actualResult = orderAble.calculateTotal(25, 100);
        // Then
        assertEquals(-1, actualResult);
    }

    @Test
    void testCalculateTotal_InvalidInputZeroOrNegative() {
        // Given: Số lượng hoặc đơn giá <= 0
        SuitcaseOrder orderAble = new SuitcaseOrder();
        // When & Then
        assertEquals(-1, orderAble.calculateTotal(0, 100));
        assertEquals(-1, orderAble.calculateTotal(-2, 100));
        assertEquals(-1, orderAble.calculateTotal(2, -100));
    }
}