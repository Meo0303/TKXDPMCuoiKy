package starter.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import starter.InMemoryDBMock;
import starter.SimplePresenter;
import starter.DatValiModelDTO;
import starter.DatValiOutputDTO;
import starter.DatValiService;

@DisplayName("Khi dịch vụ đặt mua vali thực thi")
public class DatValiServiceTest {
    @Test
    void testExecute() {
        // Given
        double quantity = 4, unitPrice = 100;
        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        ArrayList<Double> memoryDB = new ArrayList<>();
        InMemoryDBMock mockDB = new InMemoryDBMock(memoryDB);
        DatValiService datValiService = new DatValiService(mockDB, presenter);
        // When
        DatValiOutputDTO datValiOutputDTO = datValiService.execute(quantity, unitPrice);
        // Then
        assertEquals(360, datValiOutputDTO.actualResult);
        // true: red (>= 3 vali)
        // false: pink (< 3 vali)
        assertEquals("red", modelDTO.actualColor);
        assertEquals(true, datValiOutputDTO.bulkOrder);
        assertEquals(true, datValiOutputDTO.actualSaveStatus);

    }
}