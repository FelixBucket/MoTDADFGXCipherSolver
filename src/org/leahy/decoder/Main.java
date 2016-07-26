/**
 * Created by Justin Leahy
 * Created on Monday, July 18, 2016
 */
package org.leahy.decoder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main {
	
	private static JFrame startUp = new JFrame("Mob of the Dead ADFGX Cipher Solver Startup");
	private static JLabel numLabel = new JLabel();
	private static JButton decrypt = new JButton("");
	private static JTextField num = new JTextField(1);
	private static Font messageFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	public static int number;
	
	public static void main(String[] args) {
		initJFrame();
		initTextField();
		initButton();
		startUp.revalidate();
		/*
		main.decrypt();
		System.out.println(Brain.getKeyword());
		Debug.printMatrix(Brain.getLetterSquare());
		Display.loadMainDisplay();
		*/
	}
	
	private static void initJFrame() {
		startUp.setLayout(null);
		startUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startUp.pack();
		startUp.setSize(500, 500);
		startUp.setLocationRelativeTo(null);
		startUp.setResizable(false);
		startUp.setVisible(true);
	}
	
	private static void initButton() {
		decrypt.setFont(messageFont);
		decrypt.setText("Decrypt");
		decrypt.setSize(150, 25);
		startUp.add(decrypt);
		decrypt.setLocation(150, 300);
		decrypt.setVisible(true);
		decrypt.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decryption();
			}
		});
	}
	
	private static void initTextField() {
		numLabel.setFont(messageFont);
		numLabel.setText("Number of Decryptions");
		numLabel.setSize(400, 25);
		num.setSize(100, 25);
		num.setFont(messageFont);
		startUp.add(num);
		startUp.add(numLabel);
		numLabel.setLocation(125, 75);
		num.setLocation(185, 100);
		num.setVisible(true);
		numLabel.setVisible(true);
	}
	
	private static void decryption() {
		number = Integer.parseInt(num.getText());
		Brain.decrypt();
		startUp.setVisible(false);
		Display.loadMainDisplay();
	}
}
