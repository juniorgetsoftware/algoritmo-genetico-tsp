package tsp;

import java.util.ArrayList;
import java.util.Collections;

import abstrato.No;

public class Rota {

	private ArrayList<No> rota = new ArrayList<No>();
	private double aptidao = 0;
	private int distancia = 0;

	public Rota() {
		for (int i = 0; i < GerenciarTurne.quantidadeDeNos(); i++) {
			rota.add(null);
		}
	}

	public Rota(ArrayList<No> tour) {
		this.rota = tour;
	}

	public void gerarIndividuo() {
		for (int posicao = 0; posicao < GerenciarTurne.quantidadeDeNos(); posicao++) {
			setNo(posicao, GerenciarTurne.getNo(posicao));
		}
		Collections.shuffle(rota);
	}

	public No getNo(int posicao) {
		return rota.get(posicao);
	}

	public void setNo(int posicao, No cidade) {
		rota.set(posicao, cidade);
		aptidao = 0;
		distancia = 0;
	}

	public double getAptidao() {
		if (aptidao == 0) {
			aptidao = 1 / (double) getDistancia();
		}
		return aptidao;
	}

	public int getDistancia() {
		if (distancia == 0) {
			int distanciaDaRota = 0;
			for (int indice = 0; indice < tamanhoRota(); indice++) {
				No noInicial = getNo(indice);
				No noDestino;
				if (indice + 1 < tamanhoRota()) {
					noDestino = getNo(indice + 1);
				} else {
					noDestino = getNo(0);
				}
				distanciaDaRota += noInicial.distanciaPara(noDestino);
			}
			distancia = distanciaDaRota;
		}
		return distancia;
	}

	public int tamanhoRota() {
		return rota.size();
	}

	public boolean temNo(No city) {
		return rota.contains(city);
	}

	public ArrayList<No> getRota() {
		return rota;
	}

	@Override
	public String toString() {
		String geneString = "|";
		for (int i = 0; i < tamanhoRota(); i++) {
			geneString += getNo(i) + (i == tamanhoRota() - 1 ? " | " : " > ");
		}
		return geneString;
	}
}