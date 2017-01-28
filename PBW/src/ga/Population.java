package ga;

public class Population {

	public Route[] routes;

	public Population(int populationSize, boolean initialize) {
		routes = new Route[populationSize];

		if (initialize) {
			for (int i = 0; i < routes.length; i++) {
				Route route = new Route();
				route.generateIndividual();
				routes[i] = route;
			}
		}
	}

	public Route getBestRoute() {
		Route fittest = routes[0];

		for (int i = 1; i < routes.length; i++) {
			if (fittest.getFitness() <= routes[i].getFitness()) {
				fittest = routes[i];
			}
		}
		return fittest;
	}

}