import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class Interface extends JFrame{

    StartScreen startScreen = new StartScreen();
    DataScreen dataScreen = new DataScreen();
    
    Interface() {
        this.setTitle("Temp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        // f.setSize(new Dimension(600,800));
        this.add(startScreen);
        this.pack();
        this.setVisible(true);
    }

}
