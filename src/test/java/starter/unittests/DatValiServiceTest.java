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

@DisplayName("Khi dịch vụ đặt mua vali thực thi đầy đủ các trường hợp")
public class DatValiServiceTest {

    @Test
    void testExecute_RetailOrderSuccess() {
        // Given: Đặt mua lẻ 2 chiếc
        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatValiService datValiService = new DatValiService(mockDB, presenter);
        // When
        DatValiOutputDTO outputDTO = datValiService.execute(2, 100);
        // Then
        assertEquals(200, outputDTO.actualResult);
        assertEquals("pink", modelDTO.actualColor);
        assertEquals(false, outputDTO.bulkOrder);
        assertEquals(true, outputDTO.actualSaveStatus);
    }

    @Test
    void testExecute_BulkOrderSuccess() {
        // Given: Đặt mua nhóm 4 chiếc (giảm 10%)
        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatValiService datValiService = new DatValiService(mockDB, presenter);
        // When
        DatValiOutputDTO outputDTO = datValiService.execute(4, 100);
        // Then
        assertEquals(360, outputDTO.actualResult);
        assertEquals("red", modelDTO.actualColor);
        assertEquals(true, outputDTO.bulkOrder);
        assertEquals(true, outputDTO.actualSaveStatus);
    }

    @Test
    void testExecute_ExceedLimitFailed() {
        // Given: Đặt vượt quá 20 chiếc (25 chiếc)
        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatValiService datValiService = new DatValiService(mockDB, presenter);
        // When
        DatValiOutputDTO outputDTO = datValiService.execute(25, 100);
        // Then
        assertEquals(-1, outputDTO.actualResult);
        assertEquals("gray", modelDTO.actualColor);
        assertEquals(false, outputDTO.actualSaveStatus);
        assertEquals(0, mockDB.getMockDB().size());
    }

    @Test
    void testExecute_InvalidInputFailed() {
        // Given: Số lượng <= 0
        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatValiService datValiService = new DatValiService(mockDB, presenter);
        // When
        DatValiOutputDTO outputDTO = datValiService.execute(0, 100);
        // Then
        assertEquals(-1, outputDTO.actualResult);
        assertEquals("gray", modelDTO.actualColor);
        assertEquals(false, outputDTO.actualSaveStatus);
        assertEquals(0, mockDB.getMockDB().size());
    }
}