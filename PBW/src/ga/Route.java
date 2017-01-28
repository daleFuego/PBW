package ga;

import java.util.ArrayList;
import java.util.Collections;

public class Route {

	private ArrayList<DestinationPoint> route = new ArrayList<DestinationPoint>();
	private double fitness = 0;
	private int distance = 0;

	public Route() {
		for (int i = 0; i < RouteManager.destinationCount(); i++) {
			route.add(null);
		}
	}

	public void generateIndividual() {
		for (int i = 0; i < RouteManager.destinationCount(); i++) {
			setDestination(i, RouteManager.getDestination(i));
		}

		Collections.shuffle(route);
	}

	public DestinationPoint getDestination(int index) {
		return (DestinationPoint) route.get(index);
	}

	public void setDestination(int index, DestinationPoint destination) {
		route.set(index, destination);
		fitness = 0;
		distance = 0;
	}

	public double getFitness() {
		if (fitness == 0) {
			fitness = 1 / (double) getDistance();
		}
		return fitness;
	}

	public int getDistance() {
		if (distance == 0) {
			int routeDistance = 0;

			for (int i = 0; i < routeCount(); i++) {
				DestinationPoint from = getDestination(i);
				DestinationPoint destinationPoint;

				if (i + 1 < routeCount()) {
					destinationPoint = getDestination(i + 1);
				} else {
					destinationPoint = getDestination(0);
				}
				
				routeDistance += from.distanceTo(destinationPoint);
			}

			distance = routeDistance;
		}

		return distance;
	}

	public int routeCount() {
		return route.size();
	}

	public boolean containsDestination(DestinationPoint destination) {
		return route.contains(destination);
	}

	@Override
	public String toString() {
		String result = "";

		for (int i = 0; i < routeCount(); i++) {
			result += getDestination(i) + ";";
		}

		return result;
	}
}