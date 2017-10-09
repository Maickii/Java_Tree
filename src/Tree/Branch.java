package Tree;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Branch {
	protected Branch leftChild = null;
	protected Branch rightChild = null;
	protected Line2D trunk = null;
	protected Color color = null;
	protected int generation = 0;
	
	/**
	 * Needs development: Why use this? It can be changed to going
	 * down the tree.
	 * This array is used because of a design constraint. The tree is
	 * drawn by ancestry generation, meaning that parents are drawn first,
	 * and then its children. BUT, not in a recursive manner. One branch
	 * splits into two branches, those two branches split into four branches.
	 * Those four into eight, and so on. The tree is drawn as was just
	 * described. 
	 * <br>Wait? if you set the array to hold 10, doesn't that set the
	 * total number of generation to 10? Something funny is happening here.
	 */
	private static ArrayList<Branch>[] lines = new ArrayList[10];
	
	/**
	 * This is the branch generator class. It is in charge of creating a branch.
	 * Because each branch has two children it creates a tree like behavior
	 * @param line A branch is simply just a line with color to it.
	 * @param color Sets the color of the branch
	 * @param generation Gives the ancestry generation to the branch
	 */
	Branch(Line2D line, Color color, int generation) {
		this.trunk = line;
		this.color = color;
		this.generation = generation;
		this.leftChild = null;
		this.rightChild = null;
		if (lines[generation-1] == null)
		{
			lines[generation-1] = new ArrayList<Branch>();
		}
		lines[generation-1].add(this);
	}

	/**
	 * @param childBranch1 adds the left child to the parent branch
	 * @param childBranch2 adds the right child to the parent branch
	 * @param defaultForNow sets the color of the branch
	 * @param generation sets the generation of the branch
	 */
	public void createChildren(Line2D childBranch1, Line2D childBranch2, Color defaultForNow, int generation) {
		leftChild = new Branch(childBranch1, defaultForNow, generation);
		rightChild = new Branch(childBranch2, defaultForNow, generation);
	}
	public static ArrayList<Branch> getListOfGenerationN(int N)
	{
		return lines[N-1];
	}
}
