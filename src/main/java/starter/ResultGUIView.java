package starter;

import javax.swing.JOptionPane;

public class ResultGUIView implements Subscriber {
    private DatValiModelDTO modelDTO;

    public ResultGUIView(DatValiModelDTO modelDTO) {
        this.modelDTO = modelDTO;
    }

    @Override
    public void update() {

        String content = "<html><body bgcolor='" + modelDTO.actualColor + "'>"
                + modelDTO.presentResult
                + "<br>" + modelDTO.VNDate
                + "</body></html>";
        JOptionPane.showMessageDialog(null, content);
    }

}