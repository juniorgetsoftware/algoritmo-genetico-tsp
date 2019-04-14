package tsp;

import java.util.Random;

import abstrato.No;

public class Cidade implements No {
	private String name;
	private Object value;
	private Integer x;
	private Integer y;

	public Cidade() {
		this.x = new Random().nextInt(1160)+100;
		this.y = new Random().nextInt(568)+100;
		this.value = (int) (Math.random() * 200);
	}

	public Cidade(String name) {
		this();
		this.name = name;
	}

	public Cidade(String name, Integer x, Integer y) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.value = (int) (Math.random() * 200);
	}

	public Cidade(String name, Integer x, Integer y, Object value) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.value = value;
	}

	public Integer getX() {
		return this.x;
	}

	public Integer getY() {
		return this.y;
	}

	public String getName() {
		return name;
	}

	public double distanceTo(No city) {
		int xDistance = Math.abs(getX() - city.getX());
		int yDistance = Math.abs(getY() - city.getY());
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
		return distance;
	}

	@Override
	public String toString() {
		return getName() + "[" + getX() + ", " + getY() + "]";
	}

	public Object getValue() {
		return this.value;
	}
}