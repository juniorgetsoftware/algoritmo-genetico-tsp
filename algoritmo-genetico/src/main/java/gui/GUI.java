package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import abstrato.No;
import tsp.Populacao;

public class GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	public GUI(Populacao populacao) {
		this.nos = populacao.getMaisApto().getRota();
	}

	private List<No> nos;
	private final Font fonte = new Font("Arial", Font.CENTER_BASELINE, 10);
	private final int DIAMETRO = 15;
	private Map<No, Ellipse2D> elipses = new HashMap<No, Ellipse2D>();
	private Map<No, Line2D> linhas = new HashMap<No, Line2D>();

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (int i = 0; i < nos.size(); i++) {
			No no = nos.get(i);
			No no2 = nos.get(i + 1 == nos.size() ? 0 : i + 1);
			Line2D linha = desenharLinha(graphics, "" + no.getValor(), no.getX() + DIAMETRO / 2,
					no.getY() + DIAMETRO / 2, no2.getX() + DIAMETRO / 2, no2.getY() + DIAMETRO / 2, Color.LIGHT_GRAY);
			linhas.put(no, linha);
		}

		for (int i = 0; i < nos.size(); i++) {
			No no = nos.get(i);
			Ellipse2D elipse = desenharElipse(graphics, no.getNome(), no.getX(), no.getY(), Color.BLUE, DIAMETRO);
			elipses.put(no, elipse);
		}

		for (int i = 0; i < nos.size(); i++) {
			No no = nos.get(i);
			desenharRotulo(graphics, no.getNome(), elipses.get(no), Color.RED);
		}

		graphics.dispose();
	}

	private void desenhar(Graphics2D graphics, Shape s) {
		graphics.fill(s);
		graphics.draw(s);
	}

	private Ellipse2D desenharElipse(Graphics2D graphics, String rotulo, int x, int y, Color cor) {
		Double elipse = new Ellipse2D.Double(x, y, DIAMETRO, DIAMETRO);
		graphics.setColor(cor);
		desenhar(graphics, elipse);
		desenharRotulo(graphics, rotulo, elipse, Color.BLUE);
		return elipse;
	}

	private Ellipse2D desenharElipse(Graphics2D graphics, String rotulo, int x, int y, Color cor, int diametro) {
		Double elipse = new Ellipse2D.Double(x, y, diametro, diametro);
		graphics.setColor(cor);
		desenhar(graphics, elipse);
		desenharRotulo(graphics, rotulo, elipse, Color.WHITE);
		return elipse;
	}

	private Line2D desenharLinha(Graphics2D graphics, String valor, Ellipse2D de, Ellipse2D para, Color cor) {
		Line2D.Double linha = new Line2D.Double(de.getCenterX(), de.getCenterY(), para.getCenterX(), para.getCenterY());
		graphics.setColor(cor);
		desenhar(graphics, linha);
		desenharValor(graphics, valor, linha);
		return linha;
	}

	private Line2D desenharLinha(Graphics2D graphics, String valor, int x1, int y1, int x2, int y2, Color cor) {
		Line2D.Double linha = new Line2D.Double(x1, y1, x2, y2);
		graphics.setColor(cor);
		desenhar(graphics, linha);
		desenharValor(graphics, valor, linha);
		return linha;
	}

	private void desenharRotulo(Graphics2D graphics, String string, Ellipse2D e, Color cor) {
		FontRenderContext frc = graphics.getFontRenderContext();
		TextLayout t = new TextLayout(string, fonte, frc);
		graphics.setColor(cor);
		t.draw(graphics, (float) e.getCenterX() - (DIAMETRO / 2)/* - 7 */,
				(float) e.getCenterY() + (DIAMETRO)/* + 6 */);
	}

	private void desenharValor(Graphics2D graphics, String string, Line2D linha) {
		FontRenderContext frc = graphics.getFontRenderContext();
		TextLayout t = new TextLayout(string, fonte, frc);
		graphics.setColor(Color.BLACK);
		t.draw(graphics, (float) (linha.getX1() + linha.getX2()) / 2 + 5,
				(float) (linha.getY1() + linha.getY2()) / 2 + 5);
	}
}
