import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GuiOfSEP extends JFrame {
	
	JPanel panel;
	JPanel menuPanel;
	JPanel mapPanel;
	JPanel commandPanel;
	JPanel batteryPanel;
	JPanel connectionPanel;
	
	JMenuBar menuBar;
	JMenu file;
	JMenuItem save;
	JMenuItem load;
	
	private JButton auto;
	private JButton returnBase;
	
	private JButton forward;
	private JButton backward;
	private JButton left;
	private JButton right;
	private JButton stop;
	private JButton fakeButton1;
	private JButton fakeButton2;
	
	private JButton connect;
	private JButton mark;
	
	private JLabel batteryInfo;
	private JTextField batteryLevelDisplay;
	private JLabel connectionInfo;
	private JTextField connectionInfoDisplay;
	
//	private JTextArea mapArea;
	
	private Draw drawarea =null;
	
	public GuiOfSEP(){
		
		this.setTitle("Robot GUI");
		this.setSize(860,650);
		this.setLocation(200,80);
		drawarea=new Draw();
		this.add(drawarea);
		this.panel = new JPanel();
		this.menuPanel = new JPanel();
//		this.mapPanel = new JPanel();
		this.commandPanel = new JPanel();
		
		
		file = new JMenu("File");
		save = new JMenuItem("save");
		load = new JMenuItem("load");
		file.add(save);
		file.add(load);
		
		menuBar = new JMenuBar();
		menuBar.add(file);
		
		JPanel batteryPanel = new JPanel();
		JPanel connectionPanel = new JPanel();
		batteryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		batteryInfo = new JLabel("Battery Level");
		batteryLevelDisplay = new JTextField(6);
		batteryLevelDisplay.setEditable(false);
		batteryLevelDisplay.setText("60%");
		
		connectionInfo = new JLabel("Connection Status");
		connectionInfoDisplay = new JTextField(3);
		connectionInfoDisplay.setEditable(false);
		connectionInfoDisplay.setText("ON");
		
		batteryPanel.add(batteryInfo);
		batteryPanel.add(batteryLevelDisplay);
		batteryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		connectionPanel.add(connectionInfo);
		connectionPanel.add(connectionInfoDisplay);
		connectionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JPanel modePanel = new JPanel();
		JPanel northPanel = new JPanel();
		modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
		modePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 100, 0));
		modePanel.add(auto = new JButton(" Start Auto Mapping "));
		modePanel.add(returnBase = new JButton("    Return TO Base    "));
		northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
		northPanel.add(modePanel);
		northPanel.add(batteryPanel);
		northPanel.add(connectionPanel);
		
		JPanel taskPanel = new JPanel();

		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		taskPanel.add(connect = new JButton("Establish Connection"));
		taskPanel.add(mark = new JButton("  Mark Road Closure "));

		
		
		JPanel movePanel = new JPanel();
		JPanel movePanelStop = new JPanel();
		JPanel movePanelUP = new JPanel();
		JPanel movePanelDown = new JPanel();
		
		
		movePanelStop.add(stop = new JButton("Stop"));
		movePanelUP.add(fakeButton1 = new JButton(""));
		movePanelUP.add(forward = new JButton("Forward"));
		movePanelUP.add(fakeButton2 = new JButton(""));
		fakeButton1.setVisible(false);
		fakeButton2.setVisible(false);
		
		movePanelDown.add(left = new JButton("Left"));
		movePanelDown.add(backward = new JButton("Backward"));
		movePanelDown.add(right = new JButton("Right"));

		movePanel.setLayout(new BoxLayout(movePanel, BoxLayout.Y_AXIS));
		movePanel.add(movePanelStop);
		movePanel.add(movePanelUP);
		movePanel.add(movePanelDown);
		

		
	//	commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.Y_AXIS));
		commandPanel.setLayout(new BorderLayout());
		commandPanel.add(northPanel,BorderLayout.NORTH);
		commandPanel.add(taskPanel,BorderLayout.CENTER);
		commandPanel.add(movePanel,BorderLayout.SOUTH);
		
//		mapArea = new JTextArea(100,100);
//		mapPanel.add(mapArea);
		
		
		
		panel.setLayout(new BorderLayout());
		panel.add(menuBar, BorderLayout.NORTH);
		panel.add(commandPanel, BorderLayout.WEST);
//		panel.add(mapPanel, BorderLayout.CENTER);
		panel.add(drawarea, BorderLayout.CENTER);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(panel);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new GuiOfSEP();
	}

}
