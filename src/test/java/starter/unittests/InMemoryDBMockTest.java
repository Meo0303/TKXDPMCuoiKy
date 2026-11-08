package starter.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import starter.InMemoryDBMock;

public class InMemoryDBMockTest {
    @Test
    void testSave() {
        ArrayList<Double> mockDB = new ArrayList<>();
        double actualResult = 500;
        InMemoryDBMock storeAble = new InMemoryDBMock(mockDB);
        boolean actualSaveStatus = storeAble.save(actualResult);
        int actualSize = storeAble.getMockDB().size();
        assertEquals(true, actualSaveStatus);
        assertEquals(1, actualSize);
    }
}