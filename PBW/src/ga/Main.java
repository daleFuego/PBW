package ga;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import graphics.PBWDrawer;
import graphics.PBWParser;

public class Main {

	private static PBWParser parser;
	private static ArrayList<String[]> pointCoordinates;

	private static String fileName = "p14-101.txt";
	private static int iterations = 200;
	private static int populationSize = 101;
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
				RouteManager.addDestination(new DestinationPoint(x, y));
			}

			Population population = new Population(populationSize, true);
			System.out.println("Initial distance: " + population.getBestRoute().getDistance());

			for (int x = 0; x < iterations; x++) {
				population = GeneticAlgorithm.evolvePopulation(population);
				
				for (int i = 0; i < 100; i++) {
					population = GeneticAlgorithm.evolvePopulation(population);
				}
				
				result = "\n\nFinal distance: " + population.getBestRoute().getDistance() + "\nIterations: " + iterations
						+ "\nSolution:\n" + fileName + "=" + population.getBestRoute();

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
