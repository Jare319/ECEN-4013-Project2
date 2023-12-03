import java.awt.*;
import javax.swing.*;
import javax.swing.SwingConstants;


public class GUI {
    
    JFrame f = new JFrame("TempTitle");
    
    GUI() {
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        // f.setSize(new Dimension(600,800));

        JPanel mainPanel = new JPanel();
        // mainPanel.setPreferredSize(new Dimension(600,600));
        mainPanel.setLayout(new GridLayout(7, 4));
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != 3) {
                    if (j % 2 ==0) {
                    JLabel b = new JLabel("Source: ");
                    b.setHorizontalAlignment(SwingConstants.RIGHT);
                    b.setVerticalAlignment(SwingConstants.CENTER);
                    b.setPreferredSize(new Dimension(50,50));
                    mainPanel.add(b);
                    } else {
                        JLabel b = new JLabel("Data");
                        b.setHorizontalAlignment(SwingConstants.LEFT);
                        b.setVerticalAlignment(SwingConstants.CENTER);
                        b.setPreferredSize(new Dimension(200,50));
                        mainPanel.add(b);
                    }
                } else {
                    JPanel b = new JPanel();
                    b.setPreferredSize(new Dimension(50,10));
                    mainPanel.add(b);
                }
            }
        }
        f.add(mainPanel);


        f.pack();
        f.setVisible(true);
    }

}
