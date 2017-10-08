package Tree;

import java.awt.Color;

public class TreeMain {

	public static void main(String[] args) {
		drawToDegree(30, 1000, 800, 100, null,10, true);
	}
	public static void drawToDegree(int degree, int x, int y, int size, Color color, int limit, boolean safety)
	{
		Tree tree = new Tree(limit, color);
		tree.setSize(size);
		tree.setTreeAngle(90);
		tree.setSeparationAngle(degree);
		tree.setPositionY(y);
		tree.setPositionX(x);
		tree.draw(safety);
	}
}
