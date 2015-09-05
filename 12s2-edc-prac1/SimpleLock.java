
/**
 * 
 * @author Yifei Pei with the student ID a1611648
 * The SimpleLock
 * Everything based on the "Final decision on the safe lock" on the forum
 *
 */

public class SimpleLock implements Lock {

	// the password length is only four integer numbers
	private Gui g;
	private final static int MIN = 4;
	private final static int OPEN = 0;
	private final static int LOCKED = 1;
	private String password;
	private String type;
	private int state;
	private int tick = 0;
	private int time = 0;


	@Override
	// connect with the Gui
	public void connect(Gui g) {
		this.g = g;}

	@Override
	// initialise the SimpleLock
	public void initialise() {
		password = "";
		type = "";
		state = OPEN;
		g.setLocked (false);
		g.setDisplay("Open");
		tick = 0;
		time = 0;}

	@Override
	// press star to clear the screen
	public void starPressed() {
		switch (state){

		case OPEN:		
			g.setLocked(false);
			g.setDisplay("Open");
			type = "";
			time = 0;
			break;

		case LOCKED:
			g.setLocked(true);
			g.setDisplay("Locked");
			type = "";
			time = 0;
			break;		}}

	@Override
	// press hatch as enter
	public void hatchPressed() {
		switch (state){

		case OPEN:
			if (type.length() < MIN){
				time = 0;
			}else if (type.length() == MIN){
				password = type;
				g.setLocked(true);
				g.setDisplay("Locked");
				type = "";
				state = LOCKED;
				time = 0;}
			break;

			// if the entered numbers equal to the password then open the safe
		case LOCKED:
			if (type.equals(password)){
				g.setLocked(false);
				g.setDisplay("Open");
				type = "";
				password = "";
				state = OPEN;
				time = 0;
			}else{
				if (type.length() == MIN){
				    g.setLocked(true);
				    g.setDisplay("Locked");
				    type ="";
				    time = 0;
				}else{
					time = 0;}}
			break;}}

	@Override
	// press digit buttons to input the password
	public void digitPressed(int n) {
		if (type.length() < MIN){
			type = type.concat(String.valueOf(n));
			g.setDisplay(type);
			time = 0;
		}else{
			time = 0;}}

	@Override
	// if in 10 seconds nothing happened, the screen will be cleared
	public void tick() {
		tick++;
		if(tick == 10){
			time++;	
			tick = 0;}
		switch(state){

		case OPEN:
			if(time >= 10){
				g.setLocked(false);
				g.setDisplay("Open");
				type = "";
                time = 0;}
			break;

		case LOCKED:
			if(time >= 10){
				type = "";
				g.setLocked(true);	
				g.setDisplay("Locked");
				time = 0;} 		
			break;}}

}
