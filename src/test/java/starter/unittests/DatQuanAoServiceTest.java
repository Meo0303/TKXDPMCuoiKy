package starter.unittests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import starter.InMemoryDBMock;
import starter.SimplePresenter;
import starter.DatQuanAoModelDTO;
import starter.DatQuanAoOutputDTO;
import starter.DatQuanAoService;

@DisplayName("Khi dịch vụ khởi tạo đơn hàng quần áo thực thi")
public class DatQuanAoServiceTest {
    @Test
    void testExecute_StandardOrder() {
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatQuanAoService service = new DatQuanAoService(mockDB, presenter);
        DatQuanAoOutputDTO out = service.execute(200, 30);
        assertEquals(230, out.actualResult);
        assertEquals("pink", modelDTO.actualColor);
        assertEquals(false, out.priorityOrder);
        assertEquals(true, out.actualSaveStatus);
    }

    @Test
    void testExecute_PriorityOrder() {
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatQuanAoService service = new DatQuanAoService(mockDB, presenter);
        DatQuanAoOutputDTO out = service.execute(600, 35);
        assertEquals(600, out.actualResult);
        assertEquals("red", modelDTO.actualColor);
        assertEquals(true, out.priorityOrder);
        assertEquals(true, out.actualSaveStatus);
    }

    @Test
    void testExecute_RejectedOrder() {
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        InMemoryDBMock mockDB = new InMemoryDBMock(new ArrayList<>());
        DatQuanAoService service = new DatQuanAoService(mockDB, presenter);
        DatQuanAoOutputDTO out = service.execute(5500, 30);
        assertEquals(-1, out.actualResult);
        assertEquals("gray", modelDTO.actualColor);
        assertEquals(false, out.actualSaveStatus);
    }
}