import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.fazecast.jSerialComm.*;

public class StartScreen extends JPanel{
    
    private SerialPort[] allPorts;
    private SerialPort[] activePorts;
    private int hgap ,vgap = 10;
    private JPanel comPanel, startPanel, comboBoxPanel, comButtonPanel, startButtonPanel, comButtonBottomPanel;
    private JComboBox<String> comSelector;
    private JButton startButton, detectPortsButton;
    private Interface hostFrame;

    StartScreen(Interface hostFrame) {
        this.hostFrame = hostFrame;
        this.setPreferredSize(new Dimension(300,200));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(vgap,hgap,vgap,hgap));

        comPanel = new JPanel();
        comPanel.setPreferredSize(new Dimension(300,100));
        comPanel.setLayout(new BoxLayout(comPanel, BoxLayout.Y_AXIS));
        comPanel.setBorder(new TitledBorder("Select COM Port To Inspect"));

        comboBoxPanel = new JPanel();
        //comboBoxPanel.setPreferredSize(new Dimension(300,50));
        comboBoxPanel.setSize(WIDTH, 75);
        comboBoxPanel.setBorder(new EmptyBorder(5,5,5,5));
        String[] testPorts = {"Test1", "Test2", "Test3"};
        comSelector = new JComboBox<String>(testPorts);
        comSelector.setPreferredSize(new Dimension(200,30));
        comboBoxPanel.add(comSelector);

        comButtonPanel = new JPanel();
        comButtonPanel.setLayout(new BorderLayout());
        comButtonPanel.setBorder(new EmptyBorder(5,5,5,5));
        comButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        comButtonBottomPanel = new JPanel();
        comButtonBottomPanel.setLayout(new BorderLayout());
        detectPortsButton = new JButton("Detect COM Ports");
        detectPortsButton.setBackground(Color.lightGray);
        detectPortsButton.setForeground(Color.black);
        detectPortsButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createEmptyBorder(2,5,2,5)));
        detectPortsButton.addActionListener(e -> detectPorts());
        comButtonBottomPanel.add(detectPortsButton, BorderLayout.LINE_END);
        comButtonPanel.add(comButtonBottomPanel, BorderLayout.PAGE_END);

        startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(300, 50));
        startPanel.setLayout(new BorderLayout());
        startButtonPanel = new JPanel();
        startButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        startButtonPanel.setLayout(new BorderLayout());
        startButton = new JButton("Start Data Collection");
        startButton.setBackground(Color.lightGray);
        startButton.setForeground(Color.black);
        startButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),BorderFactory.createEmptyBorder(2,5,2,5)));
        startButtonPanel.add(startButton, BorderLayout.LINE_END);
        startPanel.add(startButtonPanel, BorderLayout.PAGE_END);
        startButton.addActionListener(e -> this.hostFrame.setDataScreen());

        comPanel.add(comboBoxPanel);
        comPanel.add(comButtonPanel);
        
        this.add(comPanel);
        this.add(startPanel);
        detectPorts();
    }

    public void detectPorts() {
        allPorts = SerialPort.getCommPorts();
        comSelector.removeAllItems();
        for (int i = 0; i < allPorts.length; i++) {
            comSelector.addItem(allPorts[i].getSystemPortName());
        }
    }

    // public void detectPorts() {
    //     allPorts = SerialPort.getCommPorts();
    //     for (int i = 0; i < allPorts.length;i++) {
    //         if (handShake(allPorts[i])) {
    //             for (int j = 0; j < activePorts.length; j++) {
    //                 if (activePorts[j] != null) {
    //                     activePorts[j+1] = allPorts[i];
    //                     break;
    //                 }
    //             }
    //         }
    //     }
    // }

    private boolean handShake(SerialPort port) {
        String messageString = "I can only show you the door";
        byte[] message = messageString.getBytes();
        port.writeBytes(message, message.length);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String replyString = "you're the one that has to walk through it";
        byte[] reply = replyString.getBytes();
        port.readBytes(message, reply.length);
        if (Arrays.equals(message, reply)) {
            return true;
        }
        return false;
    }
}
