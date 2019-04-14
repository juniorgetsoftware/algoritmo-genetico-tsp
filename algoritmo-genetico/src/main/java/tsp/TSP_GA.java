package tsp;

/**
 * TSP_GA.java Create a tour and evolve a solution
 */
public class TSP_GA {

	public static void main(String[] args) {

		// Create and add our cities
		Cidade city = new Cidade("C1",60, 200);
		GerenciarTurne.addNo(city);
		Cidade city2 = new Cidade("C2",180, 200);
		GerenciarTurne.addNo(city2);
		Cidade city3 = new Cidade("C3",80, 180);
		GerenciarTurne.addNo(city3);
		Cidade city4 = new Cidade("C4",140, 180);
		GerenciarTurne.addNo(city4);
		Cidade city5 = new Cidade("C5",20, 160);
		GerenciarTurne.addNo(city5);
		Cidade city6 = new Cidade("C6",100, 160);
		GerenciarTurne.addNo(city6);
		Cidade city7 = new Cidade("C7",200, 160);
		GerenciarTurne.addNo(city7);
		Cidade city8 = new Cidade("C8",140, 140);
		GerenciarTurne.addNo(city8);
		Cidade city9 = new Cidade("C9",40, 120);
		GerenciarTurne.addNo(city9);
		Cidade city10 = new Cidade("C10",100, 120);
		GerenciarTurne.addNo(city10);
		Cidade city11 = new Cidade("C11",180, 100);
		GerenciarTurne.addNo(city11);
		Cidade city12 = new Cidade("C12",60, 80);
		GerenciarTurne.addNo(city12);
		Cidade city13 = new Cidade("C13",120, 80);
		GerenciarTurne.addNo(city13);
		Cidade city14 = new Cidade("C14",180, 60);
		GerenciarTurne.addNo(city14);
		Cidade city15 = new Cidade("C15",20, 40);
		GerenciarTurne.addNo(city15);
		Cidade city16 = new Cidade("C16",100, 40);
		GerenciarTurne.addNo(city16);
		Cidade city17 = new Cidade("C17",200, 40);
		GerenciarTurne.addNo(city17);
		Cidade city18 = new Cidade("C18",20, 20);
		GerenciarTurne.addNo(city18);
		Cidade city19 = new Cidade("C19",60, 20);
		GerenciarTurne.addNo(city19);
		Cidade city20 = new Cidade("C20",160, 20);
		GerenciarTurne.addNo(city20);

		// Initialize population
		Populacao pop = new Populacao(100, true);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());

		// Evolve population for 100 generations
		pop = GA.evolvePopulation(pop);
		for (int i = 0; i < 100; i++) {
			pop = GA.evolvePopulation(pop);
			System.out.println("Fittest: " + pop.getFittest());
		}

		// Print final results
		System.out.println("Finished");
		System.out.println("Fittest: " + pop.getFittest());
		System.out.println("Final distance: " + pop.getFittest().getDistance());
		System.out.println("Solution:");
		System.out.println(pop.getFittest());

	}
}