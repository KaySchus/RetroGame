package com.kaleb;

import java.awt.BorderLayout;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		Game g = new Game();
		g.init(1920, 1080, 800, 600);
		
		JFrame frame = new JFrame("Retro Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(g.getCanvas(), BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(true);
		
		frame.setVisible(true);
		
		g.start();
	}
}
