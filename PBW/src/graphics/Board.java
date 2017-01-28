package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel {

	private static final long serialVersionUID = 7212549884383800584L;
	private ArrayList<String[]> pointCoordinates;
	private int SCALE;
	private PBWParser parser;
	private String fileName;
	private int mode;

	public Board(int mode, String fileName, int scale) {
		setBackground(Color.WHITE);
		parser = new PBWParser();
		pointCoordinates = parser.fillPointsArray(fileName);
		this.fileName = fileName;
		this.SCALE = scale;
		this.mode = mode;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		int X0 = 0;
		int Y0 = this.getHeight();
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(X0, Y0, X0, Y0);
		g2d.drawString("[0, 0]", X0, Y0);

		for (int x = X0; x < this.getWidth(); x += this.getWidth() / 20) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(x, 0, x, getWidth());
		}
		for (int y = Y0; y > 0; y -= this.getWidth() / 20) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.setStroke(new BasicStroke(1));
			g2d.drawLine(0, y, getWidth(), y);
		}

		for (String[] coos : pointCoordinates) {
			int x = Integer.parseInt(coos[1]) * SCALE;
			int y = Integer.parseInt(coos[2]) * SCALE;
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke(3));
			g2d.drawLine(x, Y0 - y, x, Y0 - y);
			g2d.drawString(parser.readPointName(Integer.parseInt(coos[1]), Integer.parseInt(coos[2]), fileName), x,
					Y0 - y);
		}

		if (mode == 2) {
			ArrayList<String> paths = parser.parse(true);
			ArrayList<String[]> cs = new ArrayList<>();

			for (String path : paths) {
				if (path.contains(fileName)) {
					try {
						for (String p : path.split("=")[1].split(";")) {
							try {
								cs.add(p.split(", "));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						System.out.println("Path: ");
						for (int i = 0; i < cs.size(); i++) {
							try {
								int x1 = Integer.parseInt(cs.get(i)[0]);
								int y1 = Integer.parseInt(cs.get(i)[1]);
								int x2 = Integer.parseInt(cs.get(i + 1)[0]);
								int y2 = Integer.parseInt(cs.get(i + 1)[1]);

								System.out.print(parser.readPointName(x1, y1, fileName) + ", ");
								g2d.setColor(Color.BLACK);
								x1 *= SCALE;
								y1 *= SCALE;
								x2 *= SCALE;
								y2 *= SCALE;
								g2d.drawLine(x1, Y0 - y1, x2, Y0 - y2);
								g2d.drawString(parser.readPointName(x1, y1, fileName), x1, Y0 - y1);
							} catch (Exception e) {
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
