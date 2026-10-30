package starter.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import starter.SimplePresenter;
import starter.DatValiModelDTO;
import starter.DatValiOutputDTO;

public class SimplePresenterTest {
    @Test
    void testFormat() {
        // Given
        DatValiOutputDTO outputDTO = new DatValiOutputDTO();
        outputDTO.actualResult = 360;
        outputDTO.actualSaveStatus = true;
        outputDTO.bulkOrder = true;

        DatValiModelDTO modelDTO = new DatValiModelDTO();

        SimplePresenter presenter = new SimplePresenter(modelDTO);
        // When
        presenter.format(outputDTO);
        // Then
        assertEquals("30/10/2026", modelDTO.VNDate);
        assertEquals("red", modelDTO.actualColor);
        assertEquals("360.0", modelDTO.presentResult);

    }
}