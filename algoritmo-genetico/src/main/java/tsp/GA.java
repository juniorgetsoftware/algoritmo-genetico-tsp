package tsp;

import abstrato.No;

public class GA {

	private static final double mutationRate = 0.015;
	private static final int tournamentSize = 5;
	private static final boolean elitism = true;

	public static Populacao evolvePopulation(Populacao pop) {
		Populacao newPopulation = new Populacao(pop.populationSize(), false);

		int elitismOffset = 0;
		if (elitism) {
			newPopulation.saveTour(0, pop.getFittest());
			elitismOffset = 1;
		}

		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			Rota parent1 = tournamentSelection(pop);
			Rota parent2 = tournamentSelection(pop);
			Rota child = crossover(parent1, parent2);
			newPopulation.saveTour(i, child);
		}

		for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
			mutate(newPopulation.getTour(i));
		}

		return newPopulation;
	}

	public static Rota crossover(Rota parent1, Rota parent2) {
		Rota child = new Rota();

		int startPos = (int) (Math.random() * parent1.tamanhoRota());
		int endPos = (int) (Math.random() * parent1.tamanhoRota());

		for (int i = 0; i < child.tamanhoRota(); i++) {
			if (startPos < endPos && i > startPos && i < endPos) {
				child.setNo(i, parent1.getNo(i));
			} else if (startPos > endPos) {
				if (!(i < startPos && i > endPos)) {
					child.setNo(i, parent1.getNo(i));
				}
			}
		}

		for (int i = 0; i < parent2.tamanhoRota(); i++) {
			if (!child.temNo(parent2.getNo(i))) {
				for (int ii = 0; ii < child.tamanhoRota(); ii++) {
					if (child.getNo(ii) == null) {
						child.setNo(ii, parent2.getNo(i));
						break;
					}
				}
			}
		}
		return child;
	}

	private static void mutate(Rota tour) {
		for (int tourPos1 = 0; tourPos1 < tour.tamanhoRota(); tourPos1++) {
			if (Math.random() < mutationRate) {
				int tourPos2 = (int) (tour.tamanhoRota() * Math.random());

				No city1 = tour.getNo(tourPos1);
				No city2 = tour.getNo(tourPos2);

				tour.setNo(tourPos2, city1);
				tour.setNo(tourPos1, city2);
			}
		}
	}

	private static Rota tournamentSelection(Populacao pop) {
		Populacao tournament = new Populacao(tournamentSize, false);
		for (int i = 0; i < tournamentSize; i++) {
			int randomId = (int) (Math.random() * pop.populationSize());
			tournament.saveTour(i, pop.getTour(randomId));
		}
		Rota fittest = tournament.getFittest();
		return fittest;
	}
}