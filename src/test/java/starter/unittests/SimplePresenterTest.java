package starter.unittests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import starter.SimplePresenter;
import starter.DatQuanAoModelDTO;
import starter.DatQuanAoOutputDTO;

public class SimplePresenterTest {
    private String getToday() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    @Test
    void testFormat_StandardOrderPink() {
        DatQuanAoOutputDTO outputDTO = new DatQuanAoOutputDTO();
        outputDTO.actualResult = 230;
        outputDTO.actualSaveStatus = true;
        outputDTO.priorityOrder = false;
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        presenter.format(outputDTO);
        assertEquals(getToday(), modelDTO.VNDate);
        assertEquals("pink", modelDTO.actualColor);
        assertEquals("230.0", modelDTO.presentResult);
    }

    @Test
    void testFormat_PriorityOrderRed() {
        DatQuanAoOutputDTO outputDTO = new DatQuanAoOutputDTO();
        outputDTO.actualResult = 600;
        outputDTO.actualSaveStatus = true;
        outputDTO.priorityOrder = true;
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        presenter.format(outputDTO);
        assertEquals(getToday(), modelDTO.VNDate);
        assertEquals("red", modelDTO.actualColor);
        assertEquals("600.0", modelDTO.presentResult);
    }

    @Test
    void testFormat_RejectedOrderGray() {
        DatQuanAoOutputDTO outputDTO = new DatQuanAoOutputDTO();
        outputDTO.actualResult = -1;
        outputDTO.actualSaveStatus = false;
        outputDTO.priorityOrder = false;
        DatQuanAoModelDTO modelDTO = new DatQuanAoModelDTO();
        SimplePresenter presenter = new SimplePresenter(modelDTO);
        presenter.format(outputDTO);
        assertEquals(getToday(), modelDTO.VNDate);
        assertEquals("gray", modelDTO.actualColor);
        assertEquals("-1.0", modelDTO.presentResult);
    }
}