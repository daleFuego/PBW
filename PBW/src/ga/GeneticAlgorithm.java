package ga;

public class GeneticAlgorithm {

	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;

	public static Population evolvePopulation(Population population) {
		Population newPopulation = new Population(population.routes.length, false);
		int elitismOffset = 0;

		if (elitism) {
			newPopulation.routes[0] = population.getBestRoute();
			elitismOffset = 1;
		}

		for (int i = elitismOffset; i < newPopulation.routes.length; i++) {
			newPopulation.routes[i] = crossover(tournamentSelection(population), tournamentSelection(population));
		}

		for (int i = elitismOffset; i < newPopulation.routes.length; i++) {
			mutate(newPopulation.routes[i]);
		}

		return newPopulation;
	}

	public static Route crossover(Route r1, Route r2) {
		Route route = new Route();

		int startPoint = (int) (Math.random() * r1.routeCount());
		int endPoint = (int) (Math.random() * r1.routeCount());

		for (int i = 0; i < route.routeCount(); i++) {
			if (startPoint < endPoint && i > startPoint && i < endPoint) {
				route.setDestination(i, r1.getDestination(i));
			} else if (startPoint > endPoint) {
				if (!(i < startPoint && i > endPoint)) {
					route.setDestination(i, r1.getDestination(i));
				}
			}
		}

		for (int i = 0; i < r2.routeCount(); i++) {
			if (!route.containsDestination(r2.getDestination(i))) {
				for (int j = 0; j < route.routeCount(); j++) {
					if (route.getDestination(j) == null) {
						route.setDestination(j, r2.getDestination(i));
						break;
					}
				}
			}
		}

		return route;
	}

	private static void mutate(Route route) {
		for (int i = 0; i < route.routeCount(); i++) {
			if (Math.random() < mutationRate) {
				int positionRoute2 = (int) (route.routeCount() * Math.random());

				DestinationPoint destination1 = route.getDestination(i);
				DestinationPoint destination2 = route.getDestination(positionRoute2);

				route.setDestination(positionRoute2, destination1);
				route.setDestination(i, destination2);
			}
		}
	}

	private static Route tournamentSelection(Population population) {
		Population tournament = new Population(tournamentSize, false);

		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * population.routes.length);
			tournament.routes[i] = population.routes[randomId];
		}

		return tournament.getBestRoute();
	}
}