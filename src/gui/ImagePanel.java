package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image backImage;
	private int width, height;
	public ImagePanel(Image bk, int w, int h) {
		backImage = bk;
		width = w;
		height = h;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backImage, 0,0,width, height, this);
	}
	public void setPicture(Image bk) {
		backImage = bk;
		repaint();
	}
}
