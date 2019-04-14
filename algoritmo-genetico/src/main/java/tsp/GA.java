package tsp;

import abstrato.No;

public class GA {

	private static final double taxaDeMutacao = 0.015;
	private static final int tamanhoTorneio = 5;
	private static final boolean elitismo = true;

	public static Populacao evolvePopulation(Populacao populacao) {
		Populacao novaPopulacao = new Populacao(populacao.tamanhoPopulacao(), false);

		int elitismoOffset = 0;
		if (elitismo) {
			novaPopulacao.salvarRota(0, populacao.getMaisApto());
			elitismoOffset = 1;
		}

		for (int i = elitismoOffset; i < novaPopulacao.tamanhoPopulacao(); i++) {
			Rota pai1 = selecaoPorTorneio(populacao);
			Rota pai2 = selecaoPorTorneio(populacao);
			Rota filho = cruzamento(pai1, pai2);
			novaPopulacao.salvarRota(i, filho);
		}

		for (int i = elitismoOffset; i < novaPopulacao.tamanhoPopulacao(); i++) {
			mutacao(novaPopulacao.getRota(i));
		}

		return novaPopulacao;
	}

	public static Rota cruzamento(Rota pai1, Rota pai2) {
		Rota filho = new Rota();
		int startPos = (int) (Math.random() * pai1.tamanhoRota());
		int endPos = (int) (Math.random() * pai1.tamanhoRota());

		for (int i = 0; i < filho.tamanhoRota(); i++) {
			if (startPos < endPos && i > startPos && i < endPos) {
				filho.setNo(i, pai1.getNo(i));
			} else if (startPos > endPos) {
				if (!(i < startPos && i > endPos)) {
					filho.setNo(i, pai1.getNo(i));
				}
			}
		}

		for (int i = 0; i < pai2.tamanhoRota(); i++) {
			if (!filho.temNo(pai2.getNo(i))) {
				for (int ii = 0; ii < filho.tamanhoRota(); ii++) {
					if (filho.getNo(ii) == null) {
						filho.setNo(ii, pai2.getNo(i));
						break;
					}
				}
			}
		}
		return filho;
	}

	private static void mutacao(Rota rota) {
		for (int indice1 = 0; indice1 < rota.tamanhoRota(); indice1++) {
			if (Math.random() < taxaDeMutacao) {
				int indice2 = (int) (rota.tamanhoRota() * Math.random());

				No no1 = rota.getNo(indice1);
				No no2 = rota.getNo(indice2);

				rota.setNo(indice2, no1);
				rota.setNo(indice1, no2);
			}
		}
	}

	private static Rota selecaoPorTorneio(Populacao pop) {
		Populacao torneio = new Populacao(tamanhoTorneio, false);
		for (int i = 0; i < tamanhoTorneio; i++) {
			int idRandomico = (int) (Math.random() * pop.tamanhoPopulacao());
			torneio.salvarRota(i, pop.getRota(idRandomico));
		}
		Rota maisApto = torneio.getMaisApto();
		return maisApto;
	}
}