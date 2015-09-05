package artificialIntelligenceUnit;

import java.awt.Point;
import java.awt.geom.Point2D;
import artificialIntelligenceUnit.CollisionResult;

public class TestOfCollisionTest {
	
	private static double euclideanDistance(Point p1, Point p2){
		return sqrt( sq(p2.getX() - p1.getX()) + sq(p2.getY() - p1.getY()));
	}

	private static double sq(double d){
		return d*d;
	}

	private static double sqrt(double d){
		return Math.sqrt(d);
	}
	
	private static Point getLinesIntersection(Point start1, Point end1, Point start2, Point end2){
		
		if((start2.getX() >= start1.getX()) && (start2.getX() <= end1.getX())){
			if((start1.getY() >= start2.getY()) && (start1.getY() <= end2.getY())){
				return new Point((int)start2.getX(), (int)start1.getY());
			}
			else if((start1.getY() <= start2.getY()) && (start1.getY() >= end2.getY())){
				return new Point((int)start2.getX(), (int)start1.getY());
			}
			else{
				System.out.println("line 31");
				return null;
			}
		}
		else if((start2.getX() <= start1.getX()) && (start2.getX() >= end1.getX())){
			if((start1.getY() >= start2.getY()) && (start1.getY() <= end2.getY())){
				return new Point((int)start2.getX(), (int)start1.getY());
			}
			else if((start1.getY() <= start2.getY()) && (start1.getY() >= end2.getY())){
				return new Point((int)start2.getX(), (int)start1.getY());
			}
			else{
				System.out.println("line 43");
				return null;
			}
		}
		else if((start1.getX() >= start2.getX()) && (start1.getX() <= end2.getX())){
			if((start2.getY() >= start1.getY()) && (start2.getY() <= end1.getY())){
				return new Point((int)start1.getX(), (int)start2.getY());
			}
			else if((start2.getY() <= start1.getY()) && (start2.getY() >= end1.getY())){
				return new Point((int)start1.getX(), (int)start2.getY());
			}
			else{
				System.out.println("line 55");
				return null;
			}
		}
		else if((start1.getX() <= start2.getX()) && (start1.getX() >= end2.getX())){
			if((start2.getY() >= start1.getY()) && (start2.getY() <= end1.getY())){
				return new Point((int)start1.getX(), (int)start2.getY());
			}
			else if((start2.getY() <= start1.getY()) && (start2.getY() >= end1.getY())){
				return new Point((int)start1.getX(), (int)start2.getY());
			}
			else{
				System.out.println("line 67");
				return null;
			}
		}
		else{
			System.out.println("line 72");
			return null;
		}
		
		
		/*
		double A1 = pe1.getY() - ps1.getY();
		double B1 = ps1.getX() - pe1.getX();
		double C1 = A1*ps1.getX() + B1*ps1.getY();

		double A2 = pe2.getY() - ps2.getY();
		double B2 = ps2.getX() - pe2.getX();
		double C2 = A2*ps2.getX() + B2*ps2.getY();

		double d = A1*B2 - A2*B1;
		
		System.out.println("A1 = "+A1);
		System.out.println("B1 = "+B1);
		System.out.println("C1 = "+C1);
		System.out.println("A2 = "+A2);
		System.out.println("B2 = "+B2);
		System.out.println("C2 = "+C2);
		System.out.println("d = "+d);

		if(d == 0.0){
			return null;
		}
		else{
			double ix = (B2*C1 - B1*C2)/d;
			double iy = (A1*C2 - A2*C1)/d;
			Point p = new Point((int)ix, (int)iy);

			if(isInBetween(ps1, pe2, p) && (isInBetween(ps2, pe2, p))){
				return p;
			}
			else{
				return null;
			}
		}
		*/
	}
	
	private static boolean isInBetween(Point a, Point b, Point c){
		int crossProduct = (int)(((c.getY() - a.getY()) * (b.getX() - a.getX()))
				- ((c.getX() - a.getX()) * (b.getY() - a.getY())));
		if(crossProduct != 0)
			return false;

		int dotProduct = (int)((c.getX() - a.getX()) * (b.getX() - a.getX())
				+ (c.getY() - a.getY())*(b.getY() - a.getY()));
		if(dotProduct < 0)
			return false;

		int squaredDistanceAB = (int)((b.getX() - a.getX())*(b.getX() - a.getX())
				+ (b.getY() - a.getY())*(b.getY() - a.getY()));
		if(dotProduct > squaredDistanceAB)
			return false;

		return true;
	}

	private static CollisionResult testCollision(Point A, Point B, Point C, double r){
		boolean isVertical;
		double Ax = A.getX();
		double Ay = A.getY();
		double Bx = B.getX();
		double By = B.getY();
		double Cx = C.getX();
		double Cy = C.getY();

		double ABlength = euclideanDistance(A, B);

		double Dx = (Bx-Ax)/ABlength;
		double Dy = (By-Ay)/ABlength;
		//System.out.println("Dx ="+Dx);
		//System.out.println("Dy ="+Dy);
		isVertical = (Dx == 0.0);
		double t = Dx*(Cx-Ax) + Dy*(Cy-Ay);

		double Ex = t*Dx+Ax;
		double Ey = t*Dy+Ay;

		double EClength = sqrt(sq(Ex-Cx) + sq(Ey-Cy));

		if((EClength < r) && !isVertical)
		{
			//System.out.println("EClength = "+EClength);
			double dt = sqrt(sq(r) - sq(EClength));
			double Fx = (t-dt)*Dx + Ax;
			double Fy = (t-dt)*Dy + Ay;
			double Gx = (t+dt)*Dx + Ax;
			double Gy = (t+dt)*Dy + Ay;
			if(euclideanDistance(B, C) <= r){
				System.out.println("line 165");
				return new CollisionResult(true, new Point((int)Fx, (int)Fy));
			}
			else if(euclideanDistance(A, C) <= r){
				System.out.println("line 169");
				return new CollisionResult(true, new Point((int)Gx, (int)Gy));
			}
			else{
				//System.out.println("line 104");
				return new CollisionResult(true, new Point((int)Fx, (int)Fy), new Point((int)Gx, (int)Gy));
			}
		}
		else if(isVertical){
			if(Math.abs(Bx - Cx) > r){
				return new CollisionResult(false);
			}
			else if(Math.abs(Bx - Cx) == r){
				System.out.println("line 182");
				return new CollisionResult(true, new Point((int)Bx, (int)Cy));
			}
			else if((euclideanDistance(B, C) <= r) && (euclideanDistance(A, C) <= r)){
				return new CollisionResult(false);
				}
			else{
				if(euclideanDistance(B, C) <= r){
					//c^2 = a^2 + b^2
					//c^2 - b^2 = a^2
					//radius - euclideanDistance(B, C) = a^2
					//y = Cy - sqrt(a^2)
					int b = (int)Math.abs(Bx - Cx);
					int a = (int)sqrt(sq(r) - sq(b));
					if(Ay >= Cy){
						System.out.println("line 197");
						return new CollisionResult(true, new Point((int)Bx, (int)(Cy + a)));
					}
					else{
						System.out.println("line 201");
						return new CollisionResult(true, new Point((int)Bx, (int)(Cy - a)));
					}
				}
				else if(euclideanDistance(A, C) <= r){
					//c^2 = a^2 + b^2
					//c^2 - b^2 = a^2
					//radius - euclideanDistance(B, C) = a^2
					//y = Cy - sqrt(a^2)
					int b = (int)Math.abs(Bx - Cx);
					int a = (int)sqrt(sq(r) - sq(b));
					if(By >= Cy){
						System.out.println("line 213");
						return new CollisionResult(true, new Point((int)Bx, (int)(Cy + a)));
					}
					else{
						System.out.println("line 217");
						return new CollisionResult(true, new Point((int)Bx, (int)(Cy - a)));
					}
				}
				else{
					if(((Ay > Cy) && (By > Cy)) || ((Ay < Cy) && (By < Cy))){
						return new CollisionResult(false);
					}
					else{
						int b = (int)Math.abs(Bx - Cx);
						int a = (int)sqrt(r - b);
						//System.out.println("line 154");
						return new CollisionResult(true, new Point((int)Bx, (int)(Cy + a)), new Point((int)Bx, (int)(Cy - a)));
					}

				}
			}
		}
		else if(EClength == r){
			return new CollisionResult(true, new Point((int)Ex, (int)Ey));
		}
		else{
			return new CollisionResult(false);
		}
	}
	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		 // TODO Auto-generated method stub
		 CollisionResult result = TestOfCollisionTest.testCollision(new Point(0, 0),
				 								new Point(2, 0),
				 								new Point(-1, 0),
				 								2.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(0, 0),
					new Point(2, 0),
					new Point(3, 0),
					2.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(0, 0),
					new Point(10, 0),
					new Point(5, 1),
					2.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(0, 0),
					new Point(10, 0),
					new Point(5, 1),
					1.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(10, 0),
					new Point(10, 10),
					new Point(10, 20),
					5.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(120, 20),
					new Point(120, 40),
					new Point(120, 80),
					27.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(120, 20),
					new Point(120, 0),
					new Point(120, 80),
					27.0);
		 result.print();
		 
		 result = TestOfCollisionTest.testCollision(new Point(120, 40),
					new Point(120, 80),
					new Point(120, 80),
					27.0);
		 result.print();
		 
		 Point p = TestOfCollisionTest.getLinesIntersection(new Point(120, 0),
				 new Point(120, 40), new Point(80, 40), new Point(160, 40));
		 if(p != null){
			 System.out.println(p.toString());
		 }
		 else{
			 System.out.println("null");
		 }
		 
		 System.out.println("\n"+isInBetween(new Point(120, 20), new Point(120, 40), new Point(120, 40)));
	 }

}
