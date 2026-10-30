package starter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimplePresenter implements PresentAble {
    private DatValiModelDTO modelDTO;

    public SimplePresenter(DatValiModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    @Override
    public void format(DatValiOutputDTO outputDTO) {
        // color
        if (outputDTO.actualResult < 0) {
            modelDTO.actualColor = "gray";
        } else if (outputDTO.bulkOrder) {
            modelDTO.actualColor = "red";
        } else {
            modelDTO.actualColor = "pink";
        }

        // result
        modelDTO.presentResult = String.valueOf(outputDTO.actualResult);

        // date
        Date currDate = new Date();
        // convert Java Date to Vietnam Day
        modelDTO.VNDate = convertJavaDatetoVNDate(currDate);

    }

    private String convertJavaDatetoVNDate(Date currDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(currDate);
    }

}