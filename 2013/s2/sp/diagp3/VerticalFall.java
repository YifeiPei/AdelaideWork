import java.util.ArrayList;
import java.util.Arrays;


public class VerticalFall {

	public static int dropTime(int x, int y, String[] obstacles) {
		
		if (obstacles.length == 0)
			return y;
		
		int time = y;
		Arrays.sort(obstacles);
		ArrayList<Integer> s = new ArrayList<Integer> ();
		for (int i=0;i<obstacles.length;i++) {
			String [] temp = obstacles[i].split(" ");
			for (int n=0;n<temp.length;n++) {
				s.add(Integer.parseInt(temp[n]));
			}
		}
		for (int i=s.size()-2;i>0;i-=3) {
			if (s.get(i-1) <= y) {
				if ((x >= s.get(i)) && (x <= s.get(i+1))) {
					x = s.get(i+1);
					y = s.get(i-1);
					time += 5;
				}
			}
		}
		return time;
	}

}
