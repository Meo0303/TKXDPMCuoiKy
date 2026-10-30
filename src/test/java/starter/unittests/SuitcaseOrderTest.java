package starter.unittests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import starter.SuitcaseOrder;

public class SuitcaseOrderTest {
    @Test
    void testCalculateTotal() {
        // Given
        double quantity = 4;
        double unitPrice = 100;
        SuitcaseOrder orderAble = new SuitcaseOrder();
        // When
        double actualResult = orderAble.calculateTotal(quantity, unitPrice);

        // Then
        assertEquals(360, actualResult);

    }
}