package graphics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PBWParser {

	public static void main(String[] args) {
		PBWParser parser = new PBWParser();
		parser.parse(false);
	}

	public ArrayList<String> parse(boolean readPath) {
		ArrayList<String> coordinates = new ArrayList<>();

		try {
			Scanner input = new Scanner(
					new File(PBWParser.class.getClassLoader().getResource("testresultsdvrp.txt").getFile()));

			while (input.hasNext()) {
				try {
					coordinates.add(input.nextLine());
				} catch (Exception e) {
				}
			}

			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return coordinates;

	}

	public ArrayList<String[]> fillPointsArray(String fileName) {
		ArrayList<String[]> coordinates = new ArrayList<>();

		try {
			Scanner input = new Scanner(new File(PBWParser.class.getClassLoader().getResource(fileName).getFile()));

			while (input.hasNext()) {
				try {
					coordinates.add(input.nextLine().split(" "));
				} catch (Exception e) {
				}
			}

			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return coordinates;
	}

	public String readPointName(int x1, int y1, String filName) {
		ArrayList<String[]> coordinates = fillPointsArray(filName);

		for (String[] point : coordinates) {
			if (point[1].equals("" + x1) && point[2].equals("" + y1)) {
				return point[0];
			}
		}

		return null;
	}
}
