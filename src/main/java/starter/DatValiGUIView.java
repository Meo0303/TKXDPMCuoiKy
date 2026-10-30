package starter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DatValiGUIView extends JFrame {
    private DatValiController datValiController;

    public DatValiGUIView() {
        GridLayout grid = new GridLayout(3, 2);
        setLayout(grid);

        JLabel lbl1 = new JLabel("Quantity:");
        add(lbl1);// dòng 1 cột 1
        JTextField tf1 = new JTextField(20);
        add(tf1);// dòng 1 cột 2

        JLabel lbl2 = new JLabel("Unit Price:");
        add(lbl2);// dòng 2 cột 1
        JTextField tf2 = new JTextField(20);
        add(tf2);// dòng 2 cột 2

        JLabel lbl3 = new JLabel("");
        add(lbl3);// dòng 3 cột 1
        JButton btX = new JButton("Order");
        btX.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        datValiController.control(tf1.getText(), tf2.getText());
                    }
                });
        add(btX);// dòng 3 cột 2

        pack();// gói gọn
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setController(DatValiController controller) {
        this.datValiController = controller;
    }
}