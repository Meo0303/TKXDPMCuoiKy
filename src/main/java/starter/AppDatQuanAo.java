package starter;

import java.util.ArrayList;

public class AppDatQuanAo {
    public static void main(String[] args) {
        DatQuanAoModelDTO model = new DatQuanAoModelDTO();
        ArrayList<Double> arr = new ArrayList<>();
        model.savedOrders = arr;
        InMemoryDBMock dbMock = new InMemoryDBMock(arr);
        SimplePresenter presenter = new SimplePresenter(model);
        DatQuanAoService datQuanAoService = new DatQuanAoService(dbMock, presenter);
        DatQuanAoController controller = new DatQuanAoController(datQuanAoService, model);
        DatQuanAoGUIView gui = new DatQuanAoGUIView();
        gui.setController(controller);

        ResultGUIView resultView = new ResultGUIView(model);
        model.addSubscriber(resultView);
    }
}