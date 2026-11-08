package starter.unittests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import starter.ClothingOrder;

public class ClothingOrderTest {
    @Test
    void testCalculateTotal_StandardOrderWithShipping() {
        ClothingOrder orderAble = new ClothingOrder();
        assertEquals(230, orderAble.calculateTotal(200, 30));
    }

    @Test
    void testCalculateTotal_PriorityOrderFreeShip() {
        ClothingOrder orderAble = new ClothingOrder();
        assertEquals(600, orderAble.calculateTotal(600, 35));
    }

    @Test
    void testCalculateTotal_ExceedOrderLimitOver5000() {
        ClothingOrder orderAble = new ClothingOrder();
        assertEquals(-1, orderAble.calculateTotal(5500, 30));
    }

    @Test
    void testCalculateTotal_UnderMinimumOrInvalidInput() {
        ClothingOrder orderAble = new ClothingOrder();
        assertEquals(-1, orderAble.calculateTotal(30, 20));
        assertEquals(-1, orderAble.calculateTotal(-100, 30));
    }
}