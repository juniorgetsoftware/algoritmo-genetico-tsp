package tsp;

import java.util.Random;

import abstrato.No;

public class Cidade implements No {
	private String nome;
	private Object valor;
	private Integer x;
	private Integer y;

	public Cidade() {
		this.x = new Random().nextInt(1160) + 100;
		this.y = new Random().nextInt(568) + 100;
		this.valor = (int) (Math.random() * 200);
	}

	public Cidade(String nome) {
		this();
		this.nome = nome;
	}

	public Cidade(String nome, Integer x, Integer y) {
		this.nome = nome;
		this.x = x;
		this.y = y;
		this.valor = (int) (Math.random() * 200);
	}

	public Cidade(String nome, Integer x, Integer y, Object valor) {
		this.nome = nome;
		this.x = x;
		this.y = y;
		this.valor = valor;
	}

	public Integer getX() {
		return this.x;
	}

	public Integer getY() {
		return this.y;
	}

	public String getNome() {
		return nome;
	}

	public double distanciaPara(No no) {
		int xDistancia = Math.abs(this.getX() - no.getX());
		int yDistancia = Math.abs(this.getY() - no.getY());
		double distancia = Math.sqrt((xDistancia * xDistancia) + (yDistancia * yDistancia));
		return distancia;
	}

	@Override
	public String toString() {
		return getNome() + "[" + getX() + ", " + getY() + "]";
	}

	public Object getValor() {
		return this.valor;
	}
}