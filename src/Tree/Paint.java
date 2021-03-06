package Tree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.JComponent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

@SuppressWarnings("serial")
public class Paint extends JComponent {

	private static ArrayList<Line2D> lines = new ArrayList<Line2D>();
	private static ArrayList<Ellipse2D.Double> circles = new ArrayList<Ellipse2D.Double>();
	private static ArrayList<Color> circleColors = new ArrayList<Color>();
	private static ArrayList<Color> lineColors = new ArrayList<Color>();
	private static Color defaultColor = new Color(165, 42, 42);
	private static BasicStroke defaulltStrokeWidth = new BasicStroke(3);

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (int line = 0; line < lines.size(); line++) {
			g2.setColor(defaultColor);
			if (lineColors.get(line) != null) {
				g2.setColor(lineColors.get(line));
			}
			g2.setStroke(defaulltStrokeWidth);
			g2.draw(lines.get(line));
		}

		for (int circle = 0; circle < circles.size(); circle++) {
			g2.setColor(circleColors.get(circle));
			g2.fill(circles.get(circle));
		}

	}

	protected void addBranches(ArrayList<Branch> branches) {
		for (int i = 0; i < branches.size(); i++) {
			lines.add(branches.get(i).trunk);
			lineColors.add(branches.get(i).color);
		}
	}

	protected void addLine(Line2D line, Color color) {
		lines.add(line);
		lineColors.add(color);
	}

	
	protected void addCircle(Point2D point2d, Color color) {
		circles.add(new Ellipse2D.Double(point2d.getX() - 5, point2d.getY() - 5, 10, 10));
		circleColors.add(color);
	}
}
