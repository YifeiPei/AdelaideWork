
/**
 * 
 * @author Yifei Pei with student ID a1611648
 * The EnhancedLock
 * Everything based on the "Final decision on the safe lock" on the forum
 *
 */

public class EnhancedLock implements Lock 
{

	// the minimum size of the password is 4 while the maximum is 6
	private Gui g;
	private final static int MIN = 4;
	private final static int MAX = 6;
	private final static int OPEN = 0;
	private final static int LOCKED = 1;
	private String password;
	private String type;
	private int state;
	private int tick = 0;
	private int time = 0;
	private boolean wait = false;
	private boolean sleep = false;

	@Override
	// connect with the Gui
	public void connect(Gui g) {
		this.g = g;}

	@Override
	// initialise the EnhancedLock
	public void initialise() {
		password = "";
		type = "";
		state = OPEN;
		g.setLocked (false);
		g.setDisplay("Open");
		tick = 0;
		time = 0;
		wait = false;
		sleep = false;}

	@Override
	// press star to clear the screen
	// if the safe is in the sleep mode, press star to awake
	// if the safe is in the wait mode, press star then nothing will happen
	public void starPressed() {
		switch (state){

		case OPEN:		
			g.setLocked(false);
			g.setDisplay("Open");
			type = "";
			time = 0;
			sleep = false;
			break;

		case LOCKED:
			if (wait == false){
				g.setLocked(true);
				g.setDisplay("Locked");
				type = "";
				time = 0;
				sleep = false;}
			break;		}}

	@Override
	// press hatch as enter
	// if the password is less than 4, noting will happen
	// if the safe is in the sleep mode, press hatch then nothing will happen
	// when the safe is locked and the user pressed the wrong password
	// press hatch to enter wait mode, 40 seconds then remain locked
	public void hatchPressed() {
		switch (state){

		case OPEN:
			if (type.length() < MIN){
				if (sleep == false){
					time = 0;}
			}else if (type.length() >= MIN && type.length() <= MAX){
				if (sleep == false){
					password = type;
					g.setLocked(true);
					g.setDisplay("Locked");
					type = "";
					state = LOCKED;
					time = 0;}}
			break;

		case LOCKED:
			if (type.equals(password)){
				if (sleep == false){
					g.setLocked(false);
					g.setDisplay("Open");
					type = "";
					password = "";
					state = OPEN;
					time = 0;}
			}else{
				if (sleep == false){
                    if (wait == false){
   // The designer believe that even no digits entered, when the user press hatch key, the wait mode will also apear.
   // This can prevent people playing with the hatch button.
                    	// if (type.length() == 0){
                    	// time = 0;
                    	// }else{
					    wait = true;
					    g.setDisplay("Wait!");	
					    type = "";
                        time = 0;}}}
			break;}}

	@Override
	// press digit buttons to enter the password
	// only the first 6 digits entered will be remembered
	// if the safe is in sleep mode, press digit buttons then nothing will happen
	// if the safe is in wait mode, press digit buttons then nothing will happen
	public void digitPressed(int n) {
		if (type.length() < MAX){
			if (sleep == false){
				if (wait == false){
					type = type.concat(String.valueOf(n));
					String line = "------".substring(0,(MAX-(type.length())));
					g.setDisplay(line + type);
					time = 0;}}
		}else{
			if (sleep == false){
				if (wait == false){
                    // String temp = type.substring(1);
                    // type = temp + String.valueOf(n);
                    // g.setDisplay(type);
				    time = 0;}}}}

	@Override
	// 10 seconds after the last key pressed, the screen will be cleared
	// 15 seconds after the last key pressed, the safe will enter sleep mode
	// in sleep mode the screen is empty, only press star can awake the safe
	public void tick() {
		tick++;
		if(tick == 10){
			time++;	
			tick = 0;}
		switch(state){

		case OPEN:
			if(time >= 10 && time < 15){
				if (sleep == false){
					g.setLocked(false);
					g.setDisplay("Open");
					type = "";}
			}else if (time >= 15){
				g.setDisplay("");
				sleep = true;
				time = 0;}
			break;

		case LOCKED:
			if(time >= 10 && time < 15 && wait == false){
				if (sleep == false){
					type = "";
					g.setLocked(true);	
					g.setDisplay("Locked");}
			}else if (time >= 15 && wait == false){
				g.setDisplay("");
				time = 0;
				sleep = true;
			}else if (wait == true){
				if (time >= 40){
					type = "";
					g.setLocked(true);
					g.setDisplay("Locked");
					wait = false;
					time = 0;}}			
			break;}}

}
