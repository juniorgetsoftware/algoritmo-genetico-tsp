package tsp;

import java.util.ArrayList;
import java.util.List;

import abstrato.No;

public class GerenciarTurne {

	private static List<No> nos = new ArrayList<No>();

	public static void addNo(No city) {
		nos.add(city);
	}

	public static No getNo(int index) {
		return nos.get(index);
	}

	public static int quantidadeDeNos() {
		return nos.size();
	}

	public static List<No> getNos() {
		return nos;
	}
}