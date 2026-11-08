package starter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DatQuanAoGUIView extends JFrame {
    private DatQuanAoController datQuanAoController;

    public DatQuanAoGUIView() {
        setTitle("VietFashion - Phan he Quan ly Don hang");
        GridLayout grid = new GridLayout(3, 2, 5, 5);
        setLayout(grid);

        JLabel lbl1 = new JLabel("  SubTotal (Tiền hàng kVNĐ):");
        add(lbl1);
        JTextField tf1 = new JTextField(15);
        add(tf1);

        JLabel lbl2 = new JLabel("  Shipping (Phí ship kVNĐ):");
        add(lbl2);
        JTextField tf2 = new JTextField(15);
        add(tf2);

        JLabel lbl3 = new JLabel("");
        add(lbl3);
        JButton btX = new JButton("Khởi tạo Đơn (X)");
        btX.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                datQuanAoController.control(tf1.getText(), tf2.getText());
            }
        });
        add(btX);

        pack();
        setSize(390, 145);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(220, 260);
        setAlwaysOnTop(true);
        setVisible(true);
        setAlwaysOnTop(false);
    }

    public void setController(DatQuanAoController controller) {
        this.datQuanAoController = controller;
    }
}