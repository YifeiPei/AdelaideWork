package artificialIntelligenceUnit;

import java.awt.Point;

public class CollisionResult{
		private boolean collision;
		private boolean twoCollisions;
		private Point p1;
		private Point p2;

		public CollisionResult(boolean b){
			collision = b;
			twoCollisions = false;
		}

		public CollisionResult(boolean b, Point p){
			collision = b;
			twoCollisions = false;
			p1 = p;
		}

		public CollisionResult(boolean b, Point p1, Point p2){
			collision = b;
			twoCollisions = true;
			this.p1 = p1;
			this.p2 = p2;
		}

		public boolean collision(){
			return collision;
		}

		public boolean twoCollisions(){
			return twoCollisions;
		}

		public Point firstCollision(){
			return p1;
		}

		public Point secondCollision(){
			return p2;
		}
		
		public void print(){
			if(!collision()){
				 System.out.println("There are no collisions");
			 }
			 else{
				 if(twoCollisions()){
					 System.out.println("There are two collisions:");
					 System.out.println(firstCollision().toString());
					 System.out.println(secondCollision().toString());
				 }
				 else{
					 System.out.println("There is one collision:");
					 System.out.println(firstCollision().toString());
				 }
			 }
			System.out.println();
		}
}