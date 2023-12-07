import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import com.fazecast.jSerialComm.*;

public class DataScreen extends JPanel {

    private Interface hostFrame;
    private JPanel gpsPanel, gyroPanel, magPanel, accelPanel;
    private JPanel latPanel, longPanel, altPanel, satsPanel;
    private JPanel angVelXPanel, angVelYPanel, angVelZPanel;
    private JPanel accelXPanel, accelYPanel, accelZPanel;
    private JPanel magXPanel, magYPanel, magZPanel;
    private JTextField[] dataFields;
    private int hgap = 10;
    private int vgap = 10;
    private String portName;
    private SerialPort serialPort;

    // All sensor data in transmission order.
    // 'Lat', 'Long', 'Alt', 'NumSats', 'AngVelX', 'AngVelY', 'AngVelZ', 'AccelX',
    // 'AccelY', 'AccelZ', 'MagX', 'MagY', 'MagZ'

    public DataScreen(Interface hostFrame, String portName) {
        this.hostFrame = hostFrame;
        this.setPreferredSize(new Dimension(500, 600));
        this.setLayout(new GridLayout(2, 2));
        this.setBorder(new EmptyBorder(vgap, hgap, vgap, hgap));
        dataFields = new JTextField[13];
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenuItem item = new JMenuItem("Back");
        item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                hostFrame.screen = 0;
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                serialPort.closePort();
                hostFrame.setStartScreen();
            }
        });
        menu.add(item);
        hostFrame.setJMenuBar(menuBar);

        // ===============================================================================================================

        // GPS PANEL SETUP
        gpsPanel = new JPanel();
        gpsPanel.setBorder(new TitledBorder("GPS Data:"));
        ((javax.swing.border.TitledBorder) gpsPanel.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,15));
        gpsPanel.setLayout(new GridLayout(4, 1, hgap, vgap));

        // GPS SUBPANEL SETUP
        latPanel = new JPanel();
        latPanel.setBorder(new TitledBorder("Latitude:"));
        longPanel = new JPanel();
        longPanel.setBorder(new TitledBorder("Longitude:"));
        altPanel = new JPanel();
        altPanel.setBorder(new TitledBorder("Altitude:"));
        satsPanel = new JPanel();
        satsPanel.setBorder(new TitledBorder("Number of Satelites:"));

        // GPS SUBPANEL ADDITIONS
        for (int i = 0; i < 4; i++) {
            dataFields[i] = new JTextField("Placeholder");
            dataFields[i].setPreferredSize(new Dimension(220, 25));
            dataFields[i].setBorder(BorderFactory.createLoweredBevelBorder());
            dataFields[i].setEditable(false);
        }
        latPanel.add(dataFields[0]);
        longPanel.add(dataFields[1]);
        altPanel.add(dataFields[2]);
        satsPanel.add(dataFields[3]);

        // GPS PANEL ADDITIONS
        gpsPanel.add(latPanel);
        gpsPanel.add(longPanel);
        gpsPanel.add(altPanel);
        gpsPanel.add(satsPanel);
        this.add(gpsPanel);

        // ===============================================================================================================

        // GYRO PANEL SETUP
        gyroPanel = new JPanel();
        gyroPanel.setBorder(new TitledBorder("Gyroscope Data:"));
        ((javax.swing.border.TitledBorder) gyroPanel.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,15));
        gyroPanel.setLayout(new GridLayout(3, 1, hgap, vgap));
        this.add(gyroPanel);

        // GYRO SUBPANEL SETUP
        angVelXPanel = new JPanel();
        angVelXPanel.setBorder(new TitledBorder("Angular Velocity X:"));
        angVelYPanel = new JPanel();
        angVelYPanel.setBorder(new TitledBorder("Angular Velocity Y:"));
        angVelZPanel = new JPanel();
        angVelZPanel.setBorder(new TitledBorder("Angular Velocity Z:"));

        // GYRO SUBPANEL ADDITIONS
        for (int i = 4; i < 7; i++) {
            dataFields[i] = new JTextField("Placeholder");
            dataFields[i].setPreferredSize(new Dimension(220, 25));
            dataFields[i].setBorder(BorderFactory.createLoweredBevelBorder());
            dataFields[i].setEditable(false);
        }
        angVelXPanel.add(dataFields[4]);
        angVelYPanel.add(dataFields[5]);
        angVelZPanel.add(dataFields[6]);

        // GYRO PANEL ADDITIONS
        gyroPanel.add(angVelXPanel);
        gyroPanel.add(angVelYPanel);
        gyroPanel.add(angVelZPanel);
        this.add(gyroPanel);

        // ===============================================================================================================

        // ACCELEROMETER PANEL SETUP
        accelPanel = new JPanel();
        accelPanel.setBorder(new TitledBorder("Accelerometer Data"));
        ((javax.swing.border.TitledBorder) accelPanel.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,15));
        accelPanel.setLayout(new GridLayout(3, 1, hgap, vgap));
        this.add(accelPanel);

        // ACCELEROMETER SUBPANEL SETUP
        accelXPanel = new JPanel();
        accelXPanel.setBorder(new TitledBorder("Accelerometer X:"));
        accelYPanel = new JPanel();
        accelYPanel.setBorder(new TitledBorder("Accelerometer Y:"));
        accelZPanel = new JPanel();
        accelZPanel.setBorder(new TitledBorder("Accelerometer Z:"));

        // ACCELEROMETER SUBPANEL ADDITIONS
        for (int i = 7; i < 10; i++) {
            dataFields[i] = new JTextField("Placeholder");
            dataFields[i].setPreferredSize(new Dimension(220, 25));
            dataFields[i].setBorder(BorderFactory.createLoweredBevelBorder());
            dataFields[i].setEditable(false);
        }
        accelXPanel.add(dataFields[7]);
        accelYPanel.add(dataFields[8]);
        accelZPanel.add(dataFields[9]);

        // ACCELEROMETER PANEL ADDITIONS
        accelPanel.add(accelXPanel);
        accelPanel.add(accelYPanel);
        accelPanel.add(accelZPanel);
        this.add(accelPanel);

        // ===============================================================================================================

        // MAGNETOMETER PANEL SETUP
        magPanel = new JPanel();
        magPanel.setBorder(new TitledBorder("Magnetometer Data"));
        ((javax.swing.border.TitledBorder) magPanel.getBorder()).setTitleFont(new Font("Arial",Font.BOLD,15));
        magPanel.setLayout(new GridLayout(3, 1, hgap, vgap));
        this.add(magPanel);

        // MAGNETOMETER SUBPANEL SETUP
        magXPanel = new JPanel();
        magXPanel.setBorder(new TitledBorder("Mag X:"));
        magYPanel = new JPanel();
        magYPanel.setBorder(new TitledBorder("Mag Y:"));
        magZPanel = new JPanel();
        magZPanel.setBorder(new TitledBorder("Mag Z:"));

        // MAGNETOMETER SUBPANEL ADDITIONS
        for (int i = 10; i < 13; i++) {
            dataFields[i] = new JTextField("Placeholder");
            dataFields[i].setPreferredSize(new Dimension(220, 25));
            dataFields[i].setBorder(BorderFactory.createLoweredBevelBorder());
            dataFields[i].setEditable(false);
        }
        magXPanel.add(dataFields[10]);
        magYPanel.add(dataFields[11]);
        magZPanel.add(dataFields[12]);

        // MAGNETOMETER PANEL ADDITIONS
        magPanel.add(magXPanel);
        magPanel.add(magYPanel);
        magPanel.add(magZPanel);
        this.add(magPanel);

        // ===============================================================================================================
        this.portName = portName;
        this.serialPort = SerialPort.getCommPort(portName);
        serialPort.openPort();
    }

    public void readData() {
        byte[] readBuffer = new byte[serialPort.bytesAvailable()];
        String[] dataString = new String[13];
        if (serialPort.bytesAvailable() > 0) {
            int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
            updateDataFields(new String(readBuffer).split(","));
            // System.out.print(new String(readBuffer));
        }
        
    }

    public void updateDataFields(String[] data) {
        for (int i = 0; i < dataFields.length; i++) {
            try {
                dataFields[i].setText(data[i]);
            } catch (Exception e) {
                System.out.println("ERROR: unsupported data format over serial line...");
            }
        }
    }

    public SerialPort getSerialPort() {
        return this.serialPort;
    }
}
