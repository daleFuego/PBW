package ga;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import graphics.PBWDrawer;
import graphics.PBWParser;

public class TSP_GA {

	private static PBWParser parser;
	private static ArrayList<String[]> pointCoordinates;

	private static String fileName = "p14-101.txt";
	private static int iterations = 200;
	private static int population = 101;
	private static int scale = 5;

	static int mode = 2;

	public static void main(String[] args) {

		if (mode == 1) {
			parser = new PBWParser();
			pointCoordinates = parser.fillPointsArray(fileName);
			String result = "";

			for (String[] coos : pointCoordinates) {
				int x = 0;
				int y = 0;

				try {
					x = Integer.parseInt(coos[1]);
				} catch (Exception e) {
					x = 0;
				}

				try {
					y = Integer.parseInt(coos[2]);
				} catch (Exception e) {
					y = 0;
				}
				TourManager.addCity(new City(x, y));
			}

			Population pop = new Population(population, true);
			System.out.println("Initial distance: " + pop.getFittest().getDistance());

			for (int x = 0; x < iterations; x++) {
				pop = GA.evolvePopulation(pop);
				for (int i = 0; i < 100; i++) {
					pop = GA.evolvePopulation(pop);
				}
				result = "\n\nFinal distance: " + pop.getFittest().getDistance() + "\nIterations: " + iterations
						+ "\nSolution:\n" + fileName + "=" + pop.getFittest();

				System.out.println(result);

			}

			try {
				FileWriter fw = new FileWriter(
						"C:\\Users\\Magdalena\\Dev\\EclipseWorkspace\\PBW\\resources\\testresultsdvrp.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(result);
				bw.close();
				fw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			PBWDrawer drawer1 = new PBWDrawer(1, fileName, scale);
			drawer1.setVisible(true);
			PBWDrawer drawer2 = new PBWDrawer(2, fileName, scale);
			drawer2.setVisible(true);
		}

	}
}
