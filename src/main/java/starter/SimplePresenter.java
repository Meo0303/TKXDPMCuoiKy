package starter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimplePresenter implements PresentAble {
    private DatQuanAoModelDTO modelDTO;

    public SimplePresenter(DatQuanAoModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    @Override
    public void format(DatQuanAoOutputDTO outputDTO) {
        if (outputDTO.actualResult < 0) {
            modelDTO.actualColor = "gray";
        } else if (outputDTO.priorityOrder) {
            modelDTO.actualColor = "red";
        } else {
            modelDTO.actualColor = "pink";
        }
        modelDTO.presentResult = String.valueOf(outputDTO.actualResult);
        modelDTO.actualSaveStatus = outputDTO.actualSaveStatus;
        Date currDate = new Date();
        modelDTO.VNDate = convertJavaDatetoVNDate(currDate);
    }

    private String convertJavaDatetoVNDate(Date currDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(currDate);
    }
}