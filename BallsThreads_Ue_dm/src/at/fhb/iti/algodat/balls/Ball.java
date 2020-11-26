package at.fhb.iti.algodat.balls;

import at.fhb.iti.algodat.balls.balls.BasicBall;
import at.fhb.iti.algodat.balls.defs.Definitions;
import at.fhb.iti.algodat.balls.grafics.BallsPanel;

public class Ball extends BasicBall implements Runnable {

	private BallRectangle theRectangle;
	private Thread theThread;
	private boolean shallRun;

	public Ball(BallsPanel ballsPanel, BallRectangle br) {
		super(ballsPanel);
		theRectangle = br;
		shallRun=true;

		theThread = new Thread(this);
		theThread.start();
	}

	public void run() {
		while ( shallRun ) {
			// fliege auserhalb vom Rechteck
			while(!this.isTouching(theRectangle) && shallRun) {
				delay(); move();
			}
			// Berechtigung feur Rechteck nehmen
			theRectangle.occupy();

			//fliege innerhalb vom Rechteck
			while(this.isTouching(theRectangle) && shallRun) {
				delay(); move();
			}
			// Berechtigung fuer Rechteck zurueckgeben
			theRectangle.free();

// 			delay(); move();
		}
	}

	public void stop() {
		shallRun = false;
	}

	private void delay() {
		try { Thread.sleep(Definitions.DELAY ); } catch (InterruptedException e) {}
	}

}
