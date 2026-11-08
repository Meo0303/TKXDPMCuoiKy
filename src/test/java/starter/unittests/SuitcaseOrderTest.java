package starter.unittests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import starter.SuitcaseOrder;

public class SuitcaseOrderTest {
    @Test
    void testCalculateTotal_RetailOrderUnder3() {
        SuitcaseOrder orderAble = new SuitcaseOrder();
        assertEquals(200, orderAble.calculateTotal(2, 100));
    }
    @Test
    void testCalculateTotal_BulkOrderFrom3To20() {
        SuitcaseOrder orderAble = new SuitcaseOrder();
        assertEquals(360, orderAble.calculateTotal(4, 100));
    }
    @Test
    void testCalculateTotal_ExceedLimitOver20() {
        SuitcaseOrder orderAble = new SuitcaseOrder();
        assertEquals(-1, orderAble.calculateTotal(25, 100));
    }
    @Test
    void testCalculateTotal_InvalidInput() {
        SuitcaseOrder orderAble = new SuitcaseOrder();
        assertEquals(-1, orderAble.calculateTotal(0, 100));
        assertEquals(-1, orderAble.calculateTotal(-2, 100));
    }
}
