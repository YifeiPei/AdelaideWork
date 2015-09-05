import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Yifei Pei with the student ID a1611648
 *
 */

public class FsaEditor extends JFrame {

	JPanel panel;
	JPanel controlPanel;
	JToolBar controlBar;
	JButton reset;
	JButton step;
	JTextField event;
	JTextField isRecognised;
	JMenuBar menuBar;
	JMenu file;
	JMenu edit;
	JMenuItem open;
	JMenuItem saveAs;
	JMenuItem quit;
	JMenuItem newState;
	JMenuItem newTransition;
	JMenuItem setInitial;
	JMenuItem unsetInitial;
	JMenuItem setFinal;
	JMenuItem unsetFinal;
	JMenuItem delete;

	FileListener fl = new FileListener();
	EditListener el = new EditListener();
	ControlListener cl = new ControlListener();

	private FsaImpl fsa; //= new FsaImpl ();
	private FsaReaderWriter frw; //= new FsaReaderWriter ();

	FsaPanel fsaPanel;

	public FsaEditor() {
		this.setTitle("Fsa Editor");
		this.setSize(1280, 720);
		this.setLocation(200, 100);

		panel = new JPanel();
		controlPanel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

		fsaPanel = new FsaPanel();

		fsaPanel.setLayout(null);
		fsaPanel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()*9/10)); 
		fsaPanel.setBorder(BorderFactory.createLineBorder (Color.black, 1));

		file = new JMenu("File");
		file.add(open = new JMenuItem("Open ..."));
		file.add(saveAs = new JMenuItem("Save as ..."));
		file.add(quit = new JMenuItem("Quit"));
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		saveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		open.addActionListener(fl);
		open.setFont(new Font("" , Font.BOLD , 20));
		saveAs.addActionListener(fl);
		saveAs.setFont(new Font("" , Font.BOLD , 20));
		quit.addActionListener(fl);
		quit.setFont(new Font("" , Font.BOLD , 20));

		edit = new JMenu("Edit");
		edit.add(newState = new JMenuItem("New state"));
		edit.add(newTransition = new JMenuItem("New transition"));
		edit.add(setInitial = new JMenuItem("Set initial"));
		edit.add(unsetInitial = new JMenuItem("Unset initial"));
		edit.add(setFinal = new JMenuItem("Set final"));
		edit.add(unsetFinal = new JMenuItem("Unset final"));
		edit.add(delete = new JMenuItem("Delete"));
		newState.addActionListener(el);
		newState.setFont(new Font("" , Font.BOLD , 20));
		newTransition.addActionListener(el);
		newTransition.setFont(new Font("" , Font.BOLD , 20));
		setInitial.addActionListener(el);
		setInitial.setFont(new Font("" , Font.BOLD , 20));
		unsetInitial.addActionListener(el);
		unsetInitial.setFont(new Font("" , Font.BOLD , 20));
		setFinal.addActionListener(el);
		setFinal.setFont(new Font("" , Font.BOLD , 20));
		unsetFinal.addActionListener(el);
		unsetFinal.setFont(new Font("" , Font.BOLD , 20));
		delete.addActionListener(el);
		delete.setFont(new Font("" , Font.BOLD , 20));

		menuBar = new JMenuBar();
		menuBar.add(file);
		file.setPreferredSize(new Dimension(this.getWidth()*1/10,this.getHeight()*1/20));
		file.setFont(new Font("" , Font.BOLD , 24));
		file.setBorder(BorderFactory.createLineBorder (Color.black, 1));
		menuBar.add(edit);
		edit.setPreferredSize(new Dimension(this.getWidth()*1/10,this.getHeight()*1/20));
		edit.setFont(new Font("" , Font.BOLD , 24));
		edit.setBorder(BorderFactory.createLineBorder (Color.black, 1));

		controlBar = new JToolBar ();
		controlBar.add(reset = new JButton ("Reset"));
		controlBar.add(step = new JButton ("Step"));
		controlBar.add(event = new JTextField (40));
		controlBar.add(isRecognised = new JTextField (15));
		event.setFont(new Font("" , Font.BOLD , 24));
		event.setHorizontalAlignment(JTextField.LEFT);
		event.setEditable(true); 
		event.setText("Please enter the event name here");
		isRecognised.setFont(new Font("" , Font.BOLD , 24));
		isRecognised.setHorizontalAlignment(JTextField.LEFT);
		isRecognised.setEditable(false); 
		reset.addActionListener(cl);
		reset.setPreferredSize(new Dimension(this.getWidth()*1/10,this.getHeight()*1/10));
		reset.setFont(new Font("" , Font.BOLD , 24));
		step.addActionListener(cl);
		step.setPreferredSize(new Dimension(this.getWidth()*1/10,this.getHeight()*1/10));
		step.setFont(new Font("" , Font.BOLD , 24));

		this.setJMenuBar(menuBar);
		controlPanel.add(controlBar);
		controlPanel.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()*1/10));
		panel.add(fsaPanel);
		panel.add(controlPanel, BorderLayout.SOUTH);
		this.setContentPane(panel);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public class ListenCloseWdw extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

	public class FileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Object source = e.getSource();
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File("."));

			if (source == open){
				System.out.println("Open selected");
				int returnValue = fc.showOpenDialog(FsaEditor.this);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fc.getSelectedFile();
					System.out.println("Open " + selectedFile.getName());
					try {
						fsa = new FsaImpl ();
						frw = new FsaReaderWriter ();
						fsa.addListener(fsaPanel);
						fsaPanel.removeAll();
						frw.read(new FileReader(selectedFile), fsa);
					} catch(FileNotFoundException exception) {
						JOptionPane.showMessageDialog(fsaPanel , "Sorry, file cannot be found!", "File Not Found", JOptionPane.WARNING_MESSAGE);
					} catch (IOException exception) {
						JOptionPane.showMessageDialog(fsaPanel , "Sorry, error of opening file!", "Cannot Open File", JOptionPane.WARNING_MESSAGE);
					} catch (FsaFormatException exception) {
						JOptionPane.showMessageDialog(fsaPanel , "Sorry, the file is not a valid Fsa format!", "Fsa Format Exception", JOptionPane.WARNING_MESSAGE);
					}
				} else if(returnValue == JFileChooser.CANCEL_OPTION ) {
					return ;
				} else {
					System.err.println("Cannot open File");
				}
			}

			if (source == saveAs){
				System.out.println("Save as selected");
				int returnValue = fc.showSaveDialog(FsaEditor.this);
				if (returnValue == JFileChooser.APPROVE_OPTION){
					File selectedFile = fc.getSelectedFile();
					try {
						if(!selectedFile.getName().endsWith(".fsa")) {
							selectedFile = new File(selectedFile.getName()+".fsa");
						} else if (selectedFile.getName().endsWith(".fsa")) {
							selectedFile = new File(selectedFile.getName());
						}
						System.out.println("Save as " + selectedFile.getName());
						FileWriter w = new FileWriter(selectedFile);
						frw.write(w, fsa);
						w.close();
					} catch (IOException exception) {
						JOptionPane.showMessageDialog(fsaPanel , "Sorry, cannot save file!", "Cannot Save File", JOptionPane.WARNING_MESSAGE);
					}
				}else if(returnValue == JFileChooser.CANCEL_OPTION ){
					return ;
				}else{
					System.err.println("Cannot save File");
				}
			}
			if (source == quit) {
				System.out.println("Quit selected");
				System.exit(0);
			}
		}
	}

	public class EditListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == newState) {
				System.out.println("newState selected");
			}

			if (source == newTransition) {
				System.out.println("newTransition selected");
			}

			if (source == setInitial) {
				System.out.println("setInitial selected");
				for (StateIcon si : fsaPanel.getStateIcons()) {
					if (si.getSelected() == true)
						si.getState().setInitial(true);
				}
			}

			if (source == unsetInitial) {
				System.out.println("unsetInitial selected");
				for (StateIcon si : fsaPanel.getStateIcons()) {
					if (si.getSelected() == true)
						si.getState().setInitial(false);
					repaint ();
				}
			}

			if (source == setFinal) {
				System.out.println("setFinal selected");
				for (StateIcon si : fsaPanel.getStateIcons()) {
					if (si.getSelected() == true)
						si.getState().setFinal(true);
				}
			}

			if (source == unsetFinal) {
				System.out.println("unsetFinal selected");
				for (StateIcon si : fsaPanel.getStateIcons()) {
					if (si.getSelected() == true)
						si.getState().setFinal(false);
					repaint ();
				}
			}

			if (source == delete) {
				System.out.println("delete selected");
			}
		}		
	}

	public class ControlListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();

			if (source == reset) {
				System.out.println("reset selected");
			}

			if (source == step) {
				System.out.println("step selected");
			}
		}
	}

	public void isRecognisedDisplay (boolean b) {
		if (b == true) {
			isRecognised.setText("T");
		} else {
			isRecognised.setText("F");
		}
	}

	public static void main (String[] args) {
		FsaEditor editor = new FsaEditor ();
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	class FsaPanel extends JPanel implements FsaListener {

		Set <State> states;
		Set <StateIcon> stateIcons;
		Set <StateIcon> selectedStates;
		Set <StateIcon> selected;
		private int Type = 0;
		private final int IDLE = 0;
		private final int SELECTING = 1;
		private final int DRAGGING = 2;
		private int x0 = 0;
		private int y0 = 0;
		private SelectBox selectBox = new SelectBox ();

		public FsaPanel () {
			states = new HashSet<State> ();
			stateIcons = new HashSet<StateIcon> ();
			selectedStates = new HashSet<StateIcon> ();
			this.add(selectBox);
			selected = new HashSet<StateIcon> ();
			this.addMouseListener(new MouseAdapter () {

				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					System.out.println("Mouse Clicked in fsaPanel");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					super.mouseEntered(e);
					System.out.println("Mouse Entered in fsaPanel");
				}

				@Override
				public void mouseExited(MouseEvent e) {
					super.mouseExited(e);
					System.out.println("Mouse Exited in fsaPanel");
				}

				@Override
				public void mousePressed(MouseEvent e) {
					super.mousePressed(e);
					System.out.println("Mouse Pressed in fsaPanel");
					x0 = e.getX();
					y0 = e.getY();	
					switch (Type) {

					case IDLE:
						Component [] allComponent = getComponents();
						for (Component checkState : allComponent) {
							if (checkState instanceof StateIcon) {
								StateIcon tempState = (StateIcon)checkState;
								if (e.getX() < tempState.getLocation().x || e.getX() > tempState.getLocation().x + tempState.getWidth() || e.getY() < tempState.getLocation().y || e.getY() > tempState.getLocation().y + tempState.getHeight()) {
									if (tempState.getSelected() == true) {
										tempState.changeSelected();
										selected.remove(tempState);
										tempState.repaint();
									}
									Type = SELECTING;
								} else if (e.getX() > tempState.getLocation().x && e.getX() < tempState.getLocation().x + tempState.getWidth() && e.getY() > tempState.getLocation().y && e.getY() < tempState.getLocation().y + tempState.getHeight()) {
									Type = DRAGGING;
								}
							}
						}
						break;

					case SELECTING:

						break;

					case DRAGGING:

						break;
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					super.mouseReleased(e);
					System.out.println("Mouse Released in fsaPanel");

					switch (Type) {

					case IDLE:

						break;

					case SELECTING:
						selectBox.isPaint = false;
						selectBox.repaint();
						Type = IDLE;
						break;

					case DRAGGING:

						Type = IDLE;
						break;
					}
				}
			});
			
			this.addMouseMotionListener(new MouseAdapter () {

				@Override
				public void mouseDragged(MouseEvent e) {
					super.mouseDragged(e);
					System.out.println("Mouse Dragged in fsaPanel");
					
					switch (Type) {

					case IDLE:

						break;

					case SELECTING:
						selectBox.setLocation(x0, y0);
						selectBox.isPaint = true;
						selectBox.repaint();
						selectBox.lengthX = e.getX() - x0;
						selectBox.lengthY = e.getY() - y0;
						int X=0;
						int Y=0;
						if(selectBox.lengthX < 0) {
							selectBox.lengthX = -selectBox.lengthX;
							X = selectBox.lengthX;
						}
						if(selectBox.lengthY<0) {
							selectBox.lengthY = -selectBox.lengthY;
							Y = selectBox.lengthY;
						}
						selectBox.setSize(selectBox.lengthX+1, selectBox.lengthY+1);
						selectBox.repaint();
						selectBox.setBounds(x0-X, y0-Y, selectBox.lengthX+1, selectBox.lengthY+1);

						Rectangle mouse = new Rectangle(x0-X, y0-Y, selectBox.lengthX, selectBox.lengthY);

						for(StateIcon si : stateIcons) {
							if(mouse.intersects(si.getBounds()) ) {
								if (si.getSelected() == false) {
									si.changeSelected();
									si.repaint();
									selected.add(si);
								}
							}
						}
						break;

					case DRAGGING:

						break;
					}
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					super.mouseMoved(e);
					System.out.println("Mouse Moved in fsaPanel");
				}

			});
		}

		@Override
		public void statesChanged() {
			System.out.println("States Changed");
			this.add(selectBox);
			for (State s : fsa.getStates()) {
				if (!states.contains(s)) {
					System.out.println(s.getName());
					states.add(s);
					StateIcon si = new StateIcon (s);
					this.add(si);
					stateIcons.add(si);
					si.addMouseListener(new MouseAdapter () {
						@Override
						public void mouseClicked(MouseEvent e) {
							super.mouseClicked(e);
							System.out.println("Mouse Clicked");
							StateIcon sic = (StateIcon) e.getSource();
							if (sic.getSelected() == false) {
								sic.changeSelected();
								sic.repaint();
								selected.add(sic);
							}
							for (StateIcon sti : stateIcons) {
								if ((sti != sic) && (sti.getSelected() == true)) {
									sti.changeSelected();
									selected.remove(sti);
									sti.repaint();
								}
							}	
						}

						@Override
						public void mouseEntered(MouseEvent e) {
							super.mouseEntered(e);
							System.out.println("Mouse Entered");
						}

						@Override
						public void mouseExited(MouseEvent e) {
							super.mouseExited(e);
							System.out.println("Mouse Exited");
						}

						@Override
						public void mousePressed(MouseEvent e) {
							super.mousePressed(e);
							System.out.println("Mouse Pressed");
							StateIcon sic = (StateIcon) e.getSource();
							x0 = e.getX();
							y0 = e.getY();

							switch (Type) {

							case IDLE:								
								if (sic.getSelected() == false) {
									sic.changeSelected();
									selected.add(sic);
									sic.repaint();
									for (StateIcon sti : stateIcons) {
										if ((sti != sic) && (sti.getSelected() == true)) {
											sti.changeSelected();
											selected.remove(sti);
											sti.repaint();
										}
									}
								} 
								Type = DRAGGING;
								break;

							case SELECTING:

								break;

							case DRAGGING:

								break;
							}
						}

						@Override
						public void mouseReleased(MouseEvent e) {
							super.mouseReleased(e);
							System.out.println("Mouse Released");

							switch (Type) {

							case IDLE:

								break;

							case SELECTING:

								Type = IDLE;
								break;

							case DRAGGING:

								Type = IDLE;
								break;
							}
						}

					});
					si.addMouseMotionListener(new MouseMotionAdapter () {

						@Override
						public void mouseDragged(MouseEvent e) {
							super.mouseDragged(e);
							System.out.println("Mouse Dragged");

							switch (Type) {

							case IDLE:

								break;

							case SELECTING:

								break;

							case DRAGGING:
								//StateIcon sic = (StateIcon) e.getSource();
								int moveX = e.getX() - x0;
								int moveY = e.getY() - y0;
								//if (selected.size() == 1) {
								//	sic.state.moveBy(moveX, moveY);
								//} else {
									for(StateIcon si : selected) {
										if(si.getSelected()) {
											si.state.moveBy(moveX, moveY);
										}
									}
								//}
								break;
							}
						}

						@Override
						public void mouseMoved(MouseEvent e) {
							super.mouseMoved(e);
							System.out.println("Mouse Moved");
						}

					});
				}
			}
			revalidate();
		}

		@Override
		public void transitionsChanged() {
			System.out.println("Transitions Changed");
		}

		@Override
		public void otherChanged() {
			System.out.println("Others Changed");
		}

		public Set<StateIcon> getStateIcons () {
			Set<StateIcon> temp = new HashSet<StateIcon> ();
			temp.addAll(stateIcons);
			return temp;
		}

		private class SelectBox extends JComponent {
			int lengthX = 0;
			int lengthY = 0;
			boolean isPaint = false;

			public SelectBox() {
	
			}

			@Override
			public void paintComponent (Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.BLACK);
				Rectangle r = new Rectangle ( 0, 0, lengthX, lengthY);
				if(isPaint) {
					g2d.draw(r);
				}
			}
		}

	}

	//////////////////////////////////////////////////////////////////////////////////////////

	class StateIcon extends JComponent implements StateListener {

		State state;
		private boolean isSelected = false;

		public StateIcon () {
			this.setSize(60, 60);
			this.setVisible(true);
		}

		public StateIcon (State s) {
			state = s;
			this.setSize(60, 60);
			this.setVisible(true);
			state.addListener(this);
			this.setLocation(state.getXpos()-30,state.getYpos()-30);
		}

		@Override
		public void StateHasChanged() {
			System.out.println("StateHasChanged implies");
			this.setLocation(state.getXpos()-30,state.getYpos()-30);
			repaint ();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int w = getWidth ();
			int h = getHeight ();
			if ((state.isInitial() == true) && (state.isFinal() != true) ) {
				g.setColor(Color.ORANGE);
			} else if ((state.isFinal() == true) && (state.isInitial() != true)) {
				g.setColor(Color.MAGENTA);
				g.drawOval( (w-1)/6, (h-1)/6, (w-1)*2/3, (h-1)*2/3);
			}else if (state.isInitial() && state.isFinal()) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.GRAY);
			}
			if (isSelected) {
				g.fillOval(0, 0, w-1, h-1);
			}
			g.drawOval(0, 0, w-1, h-1);
			g.setColor(Color.BLACK);
			g.drawString(state.getName(), (w/3), (h*3/5));
		}

		public void changeSelected () {
			isSelected = !isSelected;
		}

		public boolean getSelected () {
			return isSelected;
		}

		public State getState () {
			return state;
		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////	

	class TransitionIcon extends JComponent implements TransitionListener {

		public TransitionIcon () {

		}

		@Override
		public void TransitionHasChanged() {
			System.out.println("TransitionHasChanged implies");
		}


		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////	

}
