import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;


public class Interface extends JFrame{

    StartScreen startScreen;
    DataScreen dataScreen;
    
    Interface() {
        this.startScreen = new StartScreen(this);
        this.setTitle("ECEN 4013 - Data Monitor GUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(startScreen);
        this.pack();
        this.setVisible(true);
    }

    public StartScreen getStartScreen() {
        return this.startScreen;
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    public DataScreen getDataScreen() {
        return this.dataScreen;
    }

    public void setDataScreen(DataScreen dataScreen) {
        this.dataScreen = dataScreen;
    }

    public void setDataScreen(String portName) {
        this.dataScreen = new DataScreen(this, portName);
        this.getContentPane().removeAll();
        this.getContentPane().add(dataScreen);
        this.pack();
        this.setVisible(true);
        //this.dataScreen.readData();
    }
}
