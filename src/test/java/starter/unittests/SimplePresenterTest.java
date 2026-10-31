package starter.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import starter.SimplePresenter;
import starter.DatValiModelDTO;
import starter.DatValiOutputDTO;

public class SimplePresenterTest {

    @Test
    void testFormat_RetailOrderPink() {
        // Given: Đơn mua lẻ (< 3 chiếc)
        DatValiOutputDTO outputDTO = new DatValiOutputDTO();
        outputDTO.actualResult = 200;
        outputDTO.actualSaveStatus = true;
        outputDTO.bulkOrder = false;

        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        // When
        presenter.format(outputDTO);
        // Then
        assertEquals("31/10/2026", modelDTO.VNDate);
        assertEquals("pink", modelDTO.actualColor);
        assertEquals("200.0", modelDTO.presentResult);
    }

    @Test
    void testFormat_BulkOrderRed() {
        // Given: Đơn mua nhóm (>= 3 chiếc)
        DatValiOutputDTO outputDTO = new DatValiOutputDTO();
        outputDTO.actualResult = 360;
        outputDTO.actualSaveStatus = true;
        outputDTO.bulkOrder = true;

        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        // When
        presenter.format(outputDTO);
        // Then
        assertEquals("31/10/2026", modelDTO.VNDate);
        assertEquals("red", modelDTO.actualColor);
        assertEquals("360.0", modelDTO.presentResult);
    }

    @Test
    void testFormat_InvalidOrderGray() {
        // Given: Đơn không hợp lệ (actualResult = -1)
        DatValiOutputDTO outputDTO = new DatValiOutputDTO();
        outputDTO.actualResult = -1;
        outputDTO.actualSaveStatus = false;
        outputDTO.bulkOrder = false;

        DatValiModelDTO modelDTO = new DatValiModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        // When
        presenter.format(outputDTO);
        // Then
        assertEquals("31/10/2026", modelDTO.VNDate);
        assertEquals("gray", modelDTO.actualColor);
        assertEquals("-1.0", modelDTO.presentResult);
    }
}