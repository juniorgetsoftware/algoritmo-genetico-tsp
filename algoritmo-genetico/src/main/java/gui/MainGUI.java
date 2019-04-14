package gui;

import java.util.Comparator;

import javax.swing.JFrame;

import abstrato.No;
import tsp.Cidade;
import tsp.GA;
import tsp.GerenciarTurne;
import tsp.Populacao;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 50; i++) {
			Cidade cidade = new Cidade("C" + i);
			GerenciarTurne.addNo(cidade);
		}

		Populacao pop = new Populacao(100, true);
		
		pop.getFittest().getRota().sort(Comparator.comparing(No::getX).thenComparing(No::getY));

		GUI gui = new GUI(pop);
		MainGUI main = new MainGUI();
		main.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main.add(gui);
		main.setSize(400, 400);
		main.setExtendedState(MAXIMIZED_BOTH);
		main.setLocationRelativeTo(null);
		main.setVisible(true);

		pop = GA.evolvePopulation(pop);

		for (int i = 0; i < 1000; i++) {

			pop = GA.evolvePopulation(pop);
			gui = new GUI(pop);
			main.add(gui);
			gui.revalidate();
			main.revalidate();
			gui.repaint();
			Thread.sleep(100);
			main.remove(gui);

		}
	}
}
