package tsp;

public class Populacao {

	private Populacao() {
	}

	private Rota[] rotas;

	public Populacao(int tamanhoPopulacao, boolean inicializa) {
		this();
		rotas = new Rota[tamanhoPopulacao];
		if (inicializa) {
			for (int i = 0; i < tamanhoPopulacao(); i++) {
				Rota novaRota = new Rota();
				novaRota.gerarIndividuo();
				salvarRota(i, novaRota);
			}
		}
	}

	public void salvarRota(int indice, Rota rota) {
		rotas[indice] = rota;
	}

	public Rota getRota(int indice) {
		return rotas[indice];
	}

	public Rota getMaisApto() {
		Rota maisApto = rotas[0];
		for (int i = 1; i < tamanhoPopulacao(); i++) {
			if (maisApto.getAptidao() <= getRota(i).getAptidao()) {
				maisApto = getRota(i);
			}
		}
		return maisApto;
	}

	public int tamanhoPopulacao() {
		return rotas.length;
	}
}