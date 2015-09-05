import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author Yifei Pei with student ID a1611648
 * The Gui for the safe lock
 *
 */

public class LockGui extends JFrame implements Gui, ActionListener {

	private Lock lock;

	// the keypad
	private JButton num0;
	private JButton num1;
	private JButton num2;
	private JButton num3;
	private JButton num4;
	private JButton num5;
	private JButton num6;
	private JButton num7;
	private JButton num8;
	private JButton num9;
	private JButton star;
	private JButton hatch;

	// the display screen
	private JTextField screen;

	// constructor for the Gui
	public LockGui (){

		// the title
		this.setTitle ("Safe");

		// the panel for the keyboard
		JPanel p1 = new JPanel ();
		p1.setLayout (new GridLayout (4, 3));
		p1.add(num1 = new JButton ("1"));
		p1.add(num2 = new JButton ("2"));
		p1.add(num3 = new JButton ("3"));
		p1.add(num4 = new JButton ("4"));
		p1.add(num5 = new JButton ("5"));
		p1.add(num6 = new JButton ("6"));
		p1.add(num7 = new JButton ("7"));
		p1.add(num8 = new JButton ("8"));
		p1.add(num9 = new JButton ("9"));
		p1.add(star = new JButton ("*"));
		p1.add(num0 = new JButton ("0"));
		p1.add(hatch = new JButton ("#"));

		// the panel for the screen
		JPanel p2 = new JPanel ();
		p2.setLayout(new FlowLayout());
		p2.add(screen = new JTextField(15));
		screen.setFont(new Font("" , Font.BOLD , 24));
		screen.setHorizontalAlignment(JTextField.RIGHT);
		screen.setEditable(false); 

		// the whole panel for the whole controller
		JPanel p = new JPanel ();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(p2, BorderLayout.NORTH);
		p.add(p1, BorderLayout.SOUTH);

		this.setContentPane(p);

		// set ActionListener for different buttons
		num1.addActionListener(this);
		num2.addActionListener(this);
		num3.addActionListener(this);
		num4.addActionListener(this);
		num5.addActionListener(this);
		num6.addActionListener(this);
		num7.addActionListener(this);
		num8.addActionListener(this);
		num9.addActionListener(this); 
		num0.addActionListener(this);
		star.addActionListener(this);
		hatch.addActionListener(this);

		// initialise the controller
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 450);

		this.setVisible(true);}

	public class ListenCloseWdw extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			System.exit(0);}}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == num1){
			lock.digitPressed(1);}
		if(source == num2){
			lock.digitPressed(2);}
		if(source == num3){	
			lock.digitPressed(3);}
		if(source == num4){
			lock.digitPressed(4);}
		if(source == num5){
			lock.digitPressed(5);}
		if(source == num6){
			lock.digitPressed(6);}
		if(source == num7){
			lock.digitPressed(7);}
		if(source == num8){
			lock.digitPressed(8);}
		if(source == num9){
			lock.digitPressed(9);}
		if(source == num0){
			lock.digitPressed(0);}
		if(source == star){
			lock.starPressed();}
		if(source == hatch){
			lock.hatchPressed();}}

	@Override
	// connect with the Lock class
	public void connect(Lock lock) {
		this.lock = lock;}

	@Override
	// initialise the Gui
	public void initialise() {
		setDisplay ("Open");
		setLocked (false);}

	@Override
	public void setDisplay(String s) {
		screen.setText(s);}

	@Override
	public void setLocked(boolean locked) {
		if (locked == false){
			screen.setText("Open");
		}else if (locked == true){
			screen.setText("Locked");}}

}
