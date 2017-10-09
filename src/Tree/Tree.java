package Tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

/**
 * This class takes the usage of the JFrame, and the Graphics2D libraries.
 * This class will enable user to draw trees as they wish. The class takes
 * advantage of the fact that each branch can be split into two other child
 * branches and so on.
 * <br>How to use:
 * <br> You must always call one of the constructors {@link #Tree(int)}, {@link #Tree(int, Color)}
 * <br> Then you can custumize the tree as you like it using the setters methods:
 * <br>	{@link #setSize(int)}
 * <br>	{@link #setTreeAngle(int)}
 * <br>	{@link #setSeparationAngle(int)}
 * <br>	{@link #setPositionY(int)}
 * <br>	{@link #setPositionX(int)}
 * <br> After all is that set, call the {@link #draw(boolean)} method. 
 * @author Michael Santana
 * @see <a href="https://en.wikipedia.org/wiki/AVL_tree">AVL Trees</a>
 * @see JFrame
 */
public class Tree {
	private static Branch root = null;
	private static Color defaultColor = new Color(126, 56, 200);
	private Color desiredColor = null;
	private static Paint paint = new Paint(); //are multiple instances created? no
	private Random random = new Random();
	private static JFrame frame;
	private int limit;
	private boolean drawn = false;
	private int decreaseFactor = 10;
	private int separationAngle = 30; // measured in degrees
	private int treeAngle = 90;
	private int rootPositionX = 400;
	private int rootPositionY = 400;
	private int faster = 115;
	private static Dimension screenSize;

	/**
	 * @param limit This parameter determines the maximum possible ancestry length.
	 * <br>Each branch color will be selected at random
	 */
	Tree(int limit)
	{
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		rootPositionX = screenSize.width/2;
		rootPositionY = (5*screenSize.height)/6;
		initFrame();
		this.limit = limit;
//		delay(1000);
	}
	
	/**
	 * @param limit This parameter determines the maximum possible ancestry length
	 * @param color determines the color of the whole tree. If null, or otherwise
	 * not passed in, each branch color will be selected at random
	 */
	Tree(int limit, Color color)
	{
		this(limit);
		desiredColor = color;
	}
	
	/**
	 * @param safety when true, this will resize the tree to be proportional
	 * to your screen when the tree is going outside of your screen. Otherwise,
	 * the tree is drawn as is.
	 */
	public void draw(boolean safety)
	{
		if (!drawn)
		{
			drawn = true;
			if (safety && 
					(decreaseFactor * 52 >= screenSize.height || 
					rootPositionY  < (decreaseFactor * 52)))
			{
				setSize(screenSize.height/52);
				rootPositionY = screenSize.height;
				treeAngle = 90;
				rootPositionX = screenSize.width/2;
			}
			generateTree(null, 1, limit, true);
			drawTree();
		}
	}
	
	/**
	 * @param positionX sets the X position of the root branch. This is based on
	 * the pixels on your screen, 0 being the left side of your screen, and
	 * increasing in value as you move to the right of your screen. 
	 * <br>Note: this setting might be overwritten if the safety is on.
	 */
	public void setPositionX(int positionX)
	{
		this.rootPositionX = positionX;
	}
	
	/**
	 * @param positionY sets the Y position of the root branch. This is based on
	 * the pixels on your screen, 0 being the top side of your screen, and
	 * increasing in value as you move downward on your screen. 
	 * <br>Note: this setting might be overwritten if the safety is on.
	 */
	public void setPositionY(int positionY)
	{
		this.rootPositionY = positionY;
	}
	
	/**
	 * @param angle the angle the root tree is facing. Angle is in measures of degrees. 
	 * Degree 0 is to the right of the screen, 90 is vertical.
	 * Degree rotates in a counterclockwise motion.
	 */
	public void setTreeAngle(int angle)
	{
		this.treeAngle = angle;
	}
	
	/**
	 * @param angle sets the separation between the center of two child branches. 
	 * <br>Note: this setting might be overwritten if the safety is on.
	 */
	public void setSeparationAngle(int angle)
	{
		// further development: determine what is a good range to stop user from crashing code
		this.separationAngle = angle;
	}
	
	/**
	 * @param angle sets how big the tree should be displayed. 
	 * <br>Note: this setting might be overwritten if the safety is on.
	 */
	public void setSize(int size)
	{
		// further development: determine what is a good range to stop user from crashing code
		this.decreaseFactor = size;
	}
	
	/**
	 * This is a recursive method that takes information of a tree and generates it
	 * @param currentBranch Previous branch. Initially it must be NULL.
	 * @param currentGen The current generation of the branch
	 * @param limit The limit to the number of generations.
	 * @param color sets the color for the entire tree. if null, then it is randomly generated.
	 * @return Returns a generated tree in terms of the root branch
	 */
	private Branch generateTree(Branch currentBranch, int currentGen, int limit, boolean debugging) {
		if (desiredColor == null)
		{
			defaultColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		}
		else 
		{
			defaultColor = desiredColor;
		}
		if (currentBranch == null) {
			currentBranch = new Branch(makeRoot(treeAngle, rootPositionX, rootPositionY, decreaseFactor*10), defaultColor, currentGen);
			root = currentBranch;
		}
		if (getDistanceOfLine(currentBranch.trunk) <= decreaseFactor || currentGen == limit) {
			return null;
		}
		Point.Double endPoint = getPointB(toLineAngle(currentBranch.trunk, 180 - separationAngle), currentBranch.trunk.getP2(), getDistanceOfLine(currentBranch.trunk) - decreaseFactor);
		Line2D branchLine = new Line2D.Double(currentBranch.trunk.getX2(), currentBranch.trunk.getY2(), endPoint.getX(),endPoint.getY());
		endPoint = getPointB(toLineAngle(currentBranch.trunk, 180 + separationAngle ), currentBranch.trunk.getP2(), getDistanceOfLine(currentBranch.trunk) - decreaseFactor);
		Line2D branchLine2 = new Line2D.Double(currentBranch.trunk.getX2(), currentBranch.trunk.getY2(), endPoint.getX(), endPoint.getY());

		currentBranch.createChildren(branchLine, branchLine2, defaultColor, currentGen);
		currentGen++;
		generateTree(currentBranch.leftChild, currentGen, limit, debugging);
		generateTree(currentBranch.rightChild, currentGen, limit, false);

		return root;
	}
	
	/**
	 * initializes a frame. This frame is used to paint the tree.
	 * If this function prevents accidentally calling it twice
	 * by simply returning when that happens. This function
	 * has some hard coded parameters such as the size of the frame
	 * that can be reviewed later on.
	 */
	private void initFrame() {
		if (frame != null) {
			return; // Can't create more than one instance of frame
		}
		Color background = new Color(200, 216, 200);
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setMinimumSize(new Dimension(800, 600)); // default size
		frame.setTitle("Tree");
		frame.getContentPane().setBackground(background);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.add(paint);
		frame.setEnabled(true);
	}
	/**
	 * @param angle the angle the root tree is facing. Angle is in measures of degrees. 
	 *			Degree 0 is to the right of the screen and increases in a counterclockwise motion.
	 * @param x_pos The x position of the screen of where the root should be drawn
	 * @param y_pos The y position of the screen of where the root should be drawn
	 * @param length the length of the root. 
	 * 		*Warning* Each successive branch is shortened at a constant rate, so 
	 * the longer the length of the root the longer and wider the tree. To prevent creating excessively giant trees,
	 * either keep the length to a small size or set a reasonable limit in the constructor method
	 * @return Creates and returns the line of a root of a tree.
	 */
	private Line2D makeRoot(double angle, int x_pos, int y_pos, double length) {
		Point.Double pointA = new Point.Double(x_pos, y_pos);
		Point2D pointB = getPointB(angle - 180, pointA, length);
		return new Line2D.Double(pointA.getX(), pointA.getY(), pointB.getX(), pointB.getY());
	}
	
	/**
	 * This method calculates the end point of a line given the beginning point of the line,
	 * the angle of the line, and the length of the line.
	 * @param angle Angle of the line
	 * @param beginningPoint the beginning point of the line
	 * @param length The length of the line.
	 * @return end point of a line.
	 */
	private Point.Double getPointB(double angle, Point2D beginningPoint, double length) {
		double xLocation = (-Math.cos(Math.toRadians(angle)) * length) + beginningPoint.getX();
		double yLocation = (Math.sin(Math.toRadians(angle)) * length) + beginningPoint.getY();
		return new Point.Double(xLocation, yLocation);
	}
	
	/**
	 * @param line A valid line Line2D.
	 * @return Returns the length of the line using the distance formula.
	 */
	private double getDistanceOfLine(Line2D line) {
		Point2D pointA = line.getP1();
		Point2D pointB = line.getP2();

		return Math.abs(Math.sqrt(Math.pow(pointB.getX() - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2)));
	}
	
	/**
	 * This method calculates the new angle of a new line 
	 * by finding the angle of the given line in addition to
	 * the offset angle.
	 * @param line A valid Line2D line.
	 * @param offsetAngle
	 * @return Returns the new angle of a new line
	 */
	private double toLineAngle(Line2D line, double offsetAngle) {
		return getAngle(line) + offsetAngle;
	}
	
	/**
	 * This method calculates the angle of a given line.
	 * @param line A valid Line2D line.
	 * @return Returns the angle of the given line
	 */
	private double getAngle(Line2D line) {
		double height = line.getY1() - line.getY2();
		double width = line.getX2() - line.getX1();
		double angle = Math.toDegrees(Math.atan2(height, width));
		return angle;
	}

	/**
	 * @return Returns the number of generations of a tree.
	 */
	private int getNumberOfGeneration() {
		Branch temp = root;
		int i = 0;
		while (temp != null) {
			temp = temp.leftChild;
			i++;
		}
		return i;
	}

	/**
	 * This method creates a list of Branches of a given generation N
	 * @param N the generation which to find in the tree.
	 * @return
	 */
	private ArrayList<Branch> getListOfGenerationN(int N) {
		ArrayList<Branch> branches = new ArrayList<Branch>();
		getListOfGenerationRcursively(root, branches, N);
		return branches;
	}

	/**
	 * This method recursively adds branches of a given generation to a list of branches.
	 * @param currentBranch the recursive branch.
	 * @param branches a valid list of branches
	 * @param N the generation which to find in the tree.
	 * @return
	 */
	private ArrayList<Branch> getListOfGenerationRcursively(Branch currentBranch, ArrayList<Branch> branches, int N) {
		if (currentBranch == null) {
			return null;
		}
		if (currentBranch.generation == N) {
			branches.add(currentBranch);
		}
		getListOfGenerationRcursively(currentBranch.leftChild, branches, N);
		getListOfGenerationRcursively(currentBranch.rightChild, branches, N);
		return branches;
	}
	
	/**
	 * This draws the tree.
	 * <br>If the safety is on, the tree will be set to optimal settings.
	 * <br>This method draws the tree in slow motion.
	 */
	private void drawTree() {
		int i = 0;
		int genNum = getNumberOfGeneration();
		for (i = 1; i < genNum; i++) {
			paint.addBranches(Branch.getListOfGenerationN(i));
			paint.repaint();
//			delay(20);
			slowMotion();
		}
		if (desiredColor == null) {
			for (int n = 0; n < getListOfGenerationN(i-1).size(); n++) {
				paint.addCircle(getListOfGenerationN(i-1).get(n).trunk.getP2(),
						new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				paint.repaint();
			}
		}
		else {
			for (int n = 0; n < getListOfGenerationN(i-1).size(); n++) {
				paint.addCircle(getListOfGenerationN(i-1).get(n).trunk.getP2(),
						desiredColor);
				paint.repaint();
			}
		}
	}

	/**
	 * This method slows down the drawing process by putting the thread to sleep.
	 * This is used for aesthetics.  
	 */
	private void slowMotion() {
		try {
			Thread.sleep(faster);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		faster = faster - 10;
		if (faster < 0) {
			faster = 0;
		}
	}
	/**
	 * This method slows down the drawing process by putting the thread to sleep.
	 * This is used for aesthetics.  
	 */
	@SuppressWarnings("unused")
	private void delay(long delay)
	{
		try        
		{
			Thread.sleep(delay);
		} 
		catch(InterruptedException ex) 
		{
			Thread.currentThread().interrupt();
		}
	}
}