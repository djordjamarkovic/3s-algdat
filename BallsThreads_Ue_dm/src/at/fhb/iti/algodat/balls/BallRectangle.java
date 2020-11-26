package at.fhb.iti.algodat.balls;

import at.fhb.iti.algodat.balls.balls.BasicBallRectangle;

public class BallRectangle extends BasicBallRectangle {

	//private boolean isFree;
	private int cnt;

	public BallRectangle(int d, int e, int f, int g) {
		super(d,e,f,g);
		//isFree=true;
		cnt = 3;
	}

	public synchronized void occupy() {
		// solange das Rechteck besetzt ist: warten
		while (cnt <= 0 ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// danach:  das Rechteck besetzen
		//isFree = false;
		cnt--;
		// todo logging
	}

	public synchronized void free() {
		// das rechteck freigeben
		//isFree=true;
		cnt++;
		// todo logging
		// einen schlafenden Ball aufweccken (mind. einen).
		//while (true) {
			notify();
		//}
	}
}
