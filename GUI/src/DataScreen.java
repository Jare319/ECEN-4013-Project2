import javax.swing.JFrame;
import javax.swing.JPanel;

public class DataScreen extends JPanel{
    
    Interface hostFrame;
    JPanel gpsPanel, gyroPanel, magPanel, accelPanel;

    public DataScreen(Interface hostFrame) {
        this.hostFrame = hostFrame;
    }


}
