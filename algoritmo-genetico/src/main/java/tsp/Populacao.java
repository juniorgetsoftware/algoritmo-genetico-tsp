package tsp;

/**
 * Population.java Manages a population of candidate tours
 */
public class Populacao {

	// Holds population of tours
	Rota[] tours;

	// Construct a population
	public Populacao(int populationSize, boolean initialise) {
		tours = new Rota[populationSize];
		// If we need to initialise a population of tours do so
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < populationSize(); i++) {
				Rota newTour = new Rota();
				newTour.gerarIndividuo();
				saveTour(i, newTour);
			}
		}
	}

	// Saves a tour
	public void saveTour(int index, Rota tour) {
		tours[index] = tour;
	}

	// Gets a tour from population
	public Rota getTour(int index) {
		return tours[index];
	}

	// Gets the best tour in the population
	public Rota getFittest() {
		Rota fittest = tours[0];
		// Loop through individuals to find fittest
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getTour(i).getFitness()) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}

	// Gets population size
	public int populationSize() {
		return tours.length;
	}
}