import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Display extends JFrame implements ItemListener, ActionListener {
    int size;
    JButton b[][];
    JButton reset;
    boolean state, type, set;

    public Display(int size) {
        this.size = size;
        b = new JButton[size][size];
        state = true;
        type = true;
        set = true;
        setLayout(null);
        setSize(size * 30 + 30, 150 + 30 * size);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        showButton();
    }

    public void showButton() {
        int x = 10;
        int y = 10;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = new JButton();
                b[i][j].setBounds(x, y, 30, 30);
                add(b[i][j]);
                b[i][j].addActionListener(this);
                x+=30;
            }
            y += 30;
            x = 10;
        }
        reset = new JButton("RESET");
        reset.setBounds(100, 350, 100, 50);
        add(reset);
        reset.addActionListener(this);
    }

    public void itemStateChanged(ItemEvent e){

    }
    public void actionPerformed(ActionEvent e) {
        if (!e.getSource().equals(reset)){
            JButton button = (JButton) e.getSource();
            int row = (button.getBounds().y - 10) / 30;
            int col = (button.getBounds().x - 10) / 30;
        }
    }

}