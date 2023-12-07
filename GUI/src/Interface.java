import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Interface extends JFrame {

    StartScreen startScreen;
    DataScreen dataScreen;
    int screen;
    final int STARTSCREEN = 2;
    final int DATASCREEN = 1;

    Interface() {
        this.startScreen = new StartScreen(this);
        this.setTitle("ECEN 4013 - Data Monitor GUI");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        });
        
        this.setResizable(false);
        this.add(startScreen);
        this.pack();
        this.setVisible(true);
        this.screen = STARTSCREEN;
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
        this.screen = DATASCREEN;
    }

    public void setStartScreen() {
        this.startScreen = new StartScreen(this);
        this.getContentPane().removeAll();
        this.getContentPane().add(startScreen);
        this.pack();
        this.setVisible(true);
        this.screen = STARTSCREEN;
    }

    public void exitProcedure() {
        this.screen = 0;
        this.dataScreen.getSerialPort().closePort();
        System.exit(0);
    }
}
