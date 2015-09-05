
public class SnowyWinter {

	public static int snowyHighwayLength(int[] startPoints, int[] endPoints) {
		int start = startPoints[0];
		for (int i=0;i<startPoints.length;i++) {
			if (startPoints[i] < start)
				start = startPoints[i];
		}
		int end = endPoints[0];
		for (int i=0;i<endPoints.length;i++) {
			if (endPoints[i] > end)
				end = endPoints[i];
		}
		int [] seg = new int [end-start];
		for (int i=0;i<seg.length;i++)
			seg[i] = 0;
		for (int i=0;i<startPoints.length;i++) {
			for (int j=startPoints[i];j<endPoints[i];j++)
				seg[(j-start)] = 1;
		}
		int length = 0;
		for (int i=0;i<seg.length;i++)
			length += seg[i];
		return length;
	}

}
