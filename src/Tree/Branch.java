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
	
	private static ArrayList<Branch>[] lines = new ArrayList[10];
	int array[] = new int[5];
	
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

	public void createChildren(Line2D branchLine, Line2D branchLine2, Color defaultForNow, int generation) {
		leftChild = new Branch(branchLine, defaultForNow, generation);
		rightChild = new Branch(branchLine2, defaultForNow, generation);
	}
	public static ArrayList<Branch> getListOfGenerationN(int N)
	{
		return lines[N-1];
	}
}
