import java.util.ArrayList;;

public class StringSegment {

	public static double average(String s) {
		char [] c = s.toCharArray();
		char buffer;
		double temp = 1;
		ArrayList<Double> n = new ArrayList<Double> ();
		for (int i=0;i<c.length-1;i++) {
			buffer = c[i];
			if (buffer == c[i+1]) {
				temp++;
			} else {
				n.add(temp);
				temp = 1;
			}
		}
		n.add(temp);
		double sum = 0;
		double average;
		for (int i=0;i<n.size();i++) {
			sum += n.get(i);
		}
		average = sum/ (n.size());
		return average;
	}

}
