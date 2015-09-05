import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import lejos.pc.comm.NXTCommException;

/**
 * the class is the implementation of GUI at the PC side.
 * @fileName GuiOfSEP
 * @author Yu Hong
 *
 */
public class GuiOfSEP extends JFrame implements ActionListener{

	JPanel panel;
	JPanel menuPanel;
	JPanel mapPanel;
	JPanel commandPanel;
	JPanel distancePanel;
	JPanel batteryPanel;
	JPanel connectionPanel;

	JMenuBar menuBar;
	JMenu file;
	JMenuItem save;
	JMenuItem load;
	JMenuItem quit;

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
	private JButton disconnect;
	private JButton mark;

	private JLabel distanceInfo;
	private JLabel batteryInfo;
	private JTextField distanceDisplay;
	private JTextField batteryLevelDisplay;
	private JLabel connectionInfo;
	private JTextField connectionInfoDisplay;

	private JFileChooser fc;
	private FileListener fl = new FileListener();
	
	private Draw drawMap =null;
	JFrame window;
	GUICommands gc;

	private String currentFile;
	ImageIcon stopImage;
	private int battery;
	private int distance;
	boolean connected;
	Timer b1;
	Timer d1;
	/**
	 * constructor of GUI
	 */
	public GuiOfSEP(){
		window = new JFrame();

		window.setTitle("Robot GUI");
		window.setSize(860,650);
		window.setLocation(200,80);
		
		this.panel = new JPanel();
		this.menuPanel = new JPanel();
//		this.mapPanel = new JPanel();
		this.commandPanel = new JPanel();
// 		drawMap = new Draw();
//		this.add(drawMap);
//		this.add(mapPanel);
//		
//		mapPanel.add(drawMap, BorderLayout.CENTER);
		file = new JMenu("File");
		save = new JMenuItem("save");
		load = new JMenuItem("load");
		quit = new JMenuItem("quit");
		file.add(save);
		file.add(load);
		file.add(quit);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		load.addActionListener(fl);
		save.addActionListener(fl);
		quit.addActionListener(fl);
		
		menuBar = new JMenuBar();
		menuBar.add(file);

	
		JPanel batteryPanel = new JPanel();
		JPanel distancePanel = new JPanel();
		JPanel connectionPanel = new JPanel();
		distancePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		batteryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		connectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		distanceInfo = new JLabel("Distance");
		distanceDisplay = new JTextField(8);
		distanceDisplay.setEditable(false);
		
		batteryInfo = new JLabel("Battery Level");
		batteryLevelDisplay = new JTextField(6);
		batteryLevelDisplay.setEditable(false);

		connectionInfo = new JLabel("Connection Status");
		connectionInfoDisplay = new JTextField(3);
		connectionInfoDisplay.setEditable(false);

		distancePanel.add(distanceInfo);
		distancePanel.add(distanceDisplay);
		distancePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
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
		northPanel.add(distancePanel);
		northPanel.add(batteryPanel);
		northPanel.add(connectionPanel);

		JPanel taskPanel = new JPanel();

		taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
		taskPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
		taskPanel.add(connect = new JButton("    Connect  "));
		taskPanel.add(disconnect = new JButton("Disconnect"));
		taskPanel.add(mark = new JButton("  Mark Road Closure "));


		JPanel movePanel = new JPanel();
		JPanel movePanelStop = new JPanel();
		JPanel movePanelUP = new JPanel();
		JPanel movePanelDown = new JPanel();

		stopImage = new ImageIcon("icon/stop.png");
		movePanelStop.add(stop = new JButton(stopImage));
		movePanelUP.add(fakeButton1 = new JButton(""));
		movePanelUP.add(forward = new JButton("Forward"));
		movePanelUP.add(fakeButton2 = new JButton(""));
		fakeButton1.setVisible(false);
		fakeButton2.setVisible(false);
		stop.setBackground(Color.white);
		stop.setBorderPainted(false);



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

//		mapPanel.setLayout(null);
//		mapPanel.setPreferredSize(new Dimension(this.getWidth()*2/3,this.getHeight()));    
//		mapPanel.setBorder(BorderFactory.createLineBorder (Color.black, 2));
//		mapPanel.setBackground(Color.white);
		
		
		
		panel.setLayout(new BorderLayout());
		panel.add(menuBar, BorderLayout.NORTH);
		panel.add(commandPanel, BorderLayout.WEST);
	//	panel.add(mapPanel, BorderLayout.CENTER);
				
		// add actionListener for all the buttons
		auto.addActionListener(this);
		returnBase.addActionListener(this);
		connect.addActionListener(this);
		disconnect.addActionListener(this);
		mark.addActionListener(this);
		forward.addActionListener(this);
		stop.addActionListener(this);
		left.addActionListener(this);
		backward.addActionListener(this);
		right.addActionListener(this);

		fc = new JFileChooser();
		fc.addChoosableFileFilter(new XMLFileFilter());
		fc.setAcceptAllFileFilterUsed(false);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);
		window.setVisible(true);
		
		gc = new GUICommands();
	}

	class DistanceDisplay extends TimerTask{

		public void run() {
			// TODO Auto-generated method stub
			distance = gc.getDistance();
			distanceDisplay.setText(distance+"");
		}
	}
	/**
	 * to get the battery data according to the timer
	 */
	class BatteryDisplay extends TimerTask{

		/**
		 * the override method to get the battery data
		 */
		public void run() {
			// TODO Auto-generated method stub
			battery = gc.getBatteryLevel();
			batteryLevelDisplay.setText(battery+"%");
//			System.out.println("battery: "+ battery);
			
			
			int light = gc.getLightValue();
			System.out.println("light: "+ light);
		}
	}
	
	/**
	 * the event handler for all the buttons
	 * @param e to get the source of the event.
	 */
	
	public void actionPerformed(ActionEvent e){
		if ((e.getSource() == connect)){
			try {
				connected = gc.connect();
				if (connected){
					connectionInfoDisplay.setText("ON");
					b1 = new Timer();
					b1.schedule(new BatteryDisplay(), 5*1000, 30*1000);
					d1 = new Timer();
					d1.schedule(new DistanceDisplay(), 5*1000, 1000);
				}else{
					connectionInfoDisplay.setText("OFF");
				}
				System.out.println("Connection Status: "+ connected);
				
			} catch (NXTCommException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if ((e.getSource()==disconnect)){
			try {
				b1.cancel();
				b1.purge();
				d1.cancel();
				d1.purge();
				gc.disconnect();
				batteryLevelDisplay.setText(" ");
				distanceDisplay.setText("");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			connectionInfoDisplay.setText("OFF");

		}
		if (e.getSource() == forward){
			try {
				gc.forward();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == backward){
			try {
				gc.backward();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == left){
			try {
				gc.turnLeft();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == right){
			try {
				gc.turnRight();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == stop){
			try {
				gc.emgerencyStop();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/**
	 * save the current opened file
	 * @param f the file name
	 */
	public void setCurrentFile(String f){
		currentFile = f;
	}
	
	/**
	 * return the current file name
	 * @return file name
	 */
	public String getCurrentFile(){
		return currentFile;
	}
	/**
	 * Inner class, which is used to deal with 
	 * XML file selecting.
	 */
	private class FileListener implements ActionListener{

		@Override
		/**
		 * the event handler for load and save menu.
		 */
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			fc.setCurrentDirectory(new File("."));
			if (e.getSource() == load){
				int returnVal = fc.showOpenDialog(GuiOfSEP.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File selectedFile = fc.getSelectedFile();
					drawMap = new Draw(selectedFile.getPath());
					setCurrentFile(selectedFile.getPath());
			//		mapPanel.add(drawMap);
					window.add(drawMap);
					window.setVisible(true);
					//					mapPanel = drawMap;
					
				}else if(returnVal == JFileChooser.CANCEL_OPTION ){
					// cancel
					return ;
				}else{
					System.err.println("Cannot open File");
				}
			}
			if (e.getSource() == save){
				 // save
				int returnVal = fc.showSaveDialog(GuiOfSEP.this);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					File newFile = fc.getSelectedFile();
					System.out.print("Save as selected, ");
					if(!newFile.getName().endsWith(".xml")){
						newFile = new File(newFile.getName()+".xml");
					}
					System.out.println("Save as " + newFile.getName());
					String file = getCurrentFile();
					Savemap saveObj = new Savemap(file, newFile.getPath());
					saveObj.Saving();
				}else if(returnVal == JFileChooser.CANCEL_OPTION ){
					//cancel
					return ;
				}else{
					System.err.println("Cannot save File");
				}
			}
			if (e.getSource() == quit){
				System.exit(0);
			}
		}
	}
	
	/**
	 * The get the extention of the file and
	 * check whether it is a valid XML file.
	 */
	private class XMLFileFilter extends FileFilter{

		/**
		 * to get the extention from a given file.
		 * @param f the file
		 * @return the extention of the file
		 */
		public String getExtention(File f){
			String ext = "";
			String fileName = f.getName();
			int index = fileName.lastIndexOf('.');
			if (index > 0 &&  index < fileName.length() - 1) {
				ext = fileName.substring(index+1, fileName.length()).toLowerCase();
			}
			return ext;
		}

		/**
		 * to check whether it is a valid file
		 * @param f the file to be checked
		 * @return the boolean whether it is
		 * valid or not.
		 */
		public boolean accept(File f) {
			// TODO Auto-generated method stub
			if(f.isDirectory()){
				return true;
			}
			String extension = this.getExtention(f);
			if(extension != null){
				if(extension.equals("xml")){
					return true;
				}else{
					return false;
				}
			}
			return false;
		}

		@Override
		/**
		 * return the file description
		 *  @return the file description
		 */
		public String getDescription() {
			// TODO Auto-generated method stub
			return "XML File";
		}

	}
}
