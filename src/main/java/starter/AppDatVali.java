package starter;

import java.util.ArrayList;

public class AppDatVali {
    public static void main(String[] args) {
        DatValiModelDTO model = new DatValiModelDTO();
        ArrayList<Double> arr = new ArrayList<>();
        InMemoryDBMock db = new InMemoryDBMock(arr);
        SimplePresenter presenter = new SimplePresenter(model);
        DatValiService datValiService = new DatValiService(db, presenter);
        DatValiController controller = new DatValiController(datValiService, model);
        DatValiGUIView gui = new DatValiGUIView();
        gui.setController(controller);

        ResultGUIView subscriber = new ResultGUIView(model);
        model.addSubscriber(subscriber);
    }

}