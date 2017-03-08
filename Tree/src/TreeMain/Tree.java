package TreeMain;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JFrame;

public class Tree {
	boolean holder = false;
	static Paint paint = new Paint();
	private Random random = new Random();
	private static JFrame frame;
	public static void main(String[] args) {
		
		new Tree(270, 960, 900, 120);
		//new Tree(240, 1095, 615, 50);
		//new Tree(300, 825, 615, 50);
	}

	public Tree() {
		initFrame();
		Line2D trunk = new Line2D.Double(960, 900, 960, 850);
		makeBranch(trunk, null);
		trunk = new Line2D.Double(1250, 700, 1270, 650);
		makeBranch(trunk, null);
	}

	// need to make a method that creates a tree only needing angle, length, and
	// coords as parameters
	public Tree(double angle, Point2D pointA, double length) {
		initFrame();
		Point2D pointB = getPointB(angle, pointA, length);
		Line2D trunk = new Line2D.Double(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
		makeBranch(trunk, null);
	}

	public Tree(double angle, int x_pos, int y_pos, double length) {
		initFrame();
		Point.Double pointA = new Point.Double(x_pos, y_pos);
		Point2D pointB = getPointB(angle, pointA, length);
		Line2D trunk = new Line2D.Double(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
		makeBranch(trunk, null);
	}

	public void initFrame() {
		if (frame != null)
		{
			return;
		}
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);
		frame.setResizable(false);
		frame.setTitle("Tree");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.add(paint);
		frame.setEnabled(true);
	}

	// private double generateRanges150To210()
	// {
	// holder = !holder;
	// if(holder)
	// {
	// return 150;
	// }
	// if(!holder)
	// {
	// return 210;
	// }
	//
	// return 0;
	//// return random.nextInt(61) +150;
	// }
	// steps:
	// find the length the original line
	// knowing and angle and the length of the original line, find the length of
	// the next line
	//
	private void makeBranch(Line2D line, Color color) {
		if (getDistanceOfLine(line) <= 4) {
			paint.addCircle(line.getP1(), color);
			return;
		}

		if (getDistanceOfLine(line) <= 120 || color == null) {
			color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		}

		paint.addLine(line, color);
		//slowMotion();
		Point.Double pointA = getPointB(toLineAngle(line, 150), line.getP2(), getDistanceOfLine(line) - 10);
		Line2D branchLine = new Line2D.Double(line.getX2(), line.getY2(), pointA.getX(), pointA.getY());

		Point.Double pointB = getPointB(toLineAngle(line, 210), line.getP2(), getDistanceOfLine(line) - 10);
		Line2D branchLine2 = new Line2D.Double(line.getX2(), line.getY2(), pointB.getX(), pointB.getY());

		frame.repaint();
		makeBranch(branchLine, color);
		makeBranch(branchLine2, color);

	}

	private double getDistanceOfLine(Line2D line) {
		Point2D pointA = line.getP1();
		Point2D pointB = line.getP2();

		return Math.abs(
				Math.sqrt(Math.pow(pointB.getX() - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2)));

	}

	private double toLineAngle(Line2D line, double angle) {
		return getAngle(line) + angle;
	}

	private double getAngle(Line2D line) {
		double height = line.getY1() - line.getY2();
		double width = line.getX2() - line.getX1();
		double angle = Math.toDegrees(Math.atan2(height, width));
		return angle;
	}

	/**
	 * getPointB() calculates a the second point "pointB" in relation to point
	 * "pointA" at the given angle and length
	 * 
	 * @param angle
	 * @param point2d
	 * @param length
	 * @return
	 */
	private Point.Double getPointB(double angle, Point2D pointA, double length) {
		// System.out.println(Math.cos(Math.toRadians(angle)) * length);
		double xLocation = (-Math.cos(Math.toRadians(angle)) * length) + pointA.getX();
		double yLocation = (Math.sin(Math.toRadians(angle)) * length) + pointA.getY();
		return new Point.Double(xLocation, yLocation);
	}

	boolean toggleDelay = true;

	private void slowMotion() {

		if (toggleDelay)
		{
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//toggleDelay = !toggleDelay;
	}
}
