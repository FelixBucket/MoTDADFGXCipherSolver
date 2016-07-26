package org.leahy.decoder;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {
	
	private static int currentSlide = 0;
	private static JFrame mainFrame = new JFrame("Mob of the Dead ADFGX Cipher Solver");
	private static String letterSquare = Brain.getLetterSquare(currentSlide);
	private static JLabel letterSquareLabel = new JLabel();
	private static String keyword = Brain.getKeyword(currentSlide);
	private static JLabel keywordLabel = new JLabel();
	private static String message = Brain.getMessage(currentSlide);
	private static JLabel messageLabel = new JLabel();
	private static JButton back = new JButton();
	private static JButton forward = new JButton();
	private static Font programFont = new Font("Comic Sans MS", Font.PLAIN, 24);
	private static Font messageFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	
	public static void loadMainDisplay() {
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setSize(600, 500);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		addLetterSquare();
		addKeyword();
		addMessage();
		addButtons();
		mainFrame.revalidate();
		System.out.println("REVALIDATED");
	}
	
	private static void addLetterSquare() {
		String temp = "<html>";
		for(int x = 0; x < letterSquare.length(); x++) {
			if(x == 4 || x == 9 || x == 14 || x == 19 || x == 24) {
				temp += letterSquare.substring(x, x+1) + "<br>";
			}else{
				temp += letterSquare.substring(x, x+1) + " ";
			}
		}
		temp += "</html>";
		
		System.out.println(temp);
		
		letterSquareLabel.setFont(programFont);
		letterSquareLabel.setText(temp);
		mainFrame.add(letterSquareLabel);
		letterSquareLabel.setBounds(new Rectangle(new Point(400, 50), letterSquareLabel.getPreferredSize()));
		letterSquareLabel.setVisible(true);
	}
	
	private static void addKeyword() {
		keywordLabel.setFont(programFont);
		keywordLabel.setText(keyword);
		mainFrame.add(keywordLabel);
		keywordLabel.setBounds(new Rectangle(new Point(100, 100), keywordLabel.getPreferredSize()));
		keywordLabel.setVisible(true);
	}
	
	private static void addMessage() {
		messageLabel.setFont(messageFont);
		messageLabel.setText(message);
		mainFrame.add(messageLabel);
		messageLabel.setBounds(new Rectangle(new Point(75, 300), messageLabel.getPreferredSize()));
		messageLabel.setVisible(true);
	}
	
	private static void addButtons() {
		back.setFont(messageFont);
		forward.setFont(messageFont);
		back.setText("Back");
		forward.setText("Next");
		mainFrame.add(back);
		mainFrame.add(forward);
		back.setBounds(new Rectangle(new Point(100, 400), back.getPreferredSize()));
		forward.setBounds(new Rectangle(new Point(400, 400), forward.getPreferredSize()));
		back.setVisible(true);
		forward.setVisible(true);
		back.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFrame(-1);
			}
		});
		forward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateFrame(1);
			}
		});
	}
	
	private static void updateFrame(int n) {
		if(!(currentSlide >= Main.number - 1)) {
			if(currentSlide > 0) {
				if(n > 0) {
					currentSlide++;
					//System.out.println("++");
				} else {
					currentSlide--;
					//System.out.println("--");
				}
			} else {
				if(n > 0) {
					currentSlide++;
					//System.out.println("+++");
				}
			}
		}else{
			if(n < 0) {
				currentSlide--;
			}
		}
		letterSquareLabel.setText(null);
		keywordLabel.setText(null);
		messageLabel.setText(null);
			
		updateText(Brain.getLetterSquare(currentSlide));
		keywordLabel.setText(Brain.getKeyword(currentSlide));
		messageLabel.setText(Brain.getMessage(currentSlide));
		letterSquareLabel.setBounds(new Rectangle(new Point(400, 50), letterSquareLabel.getPreferredSize()));
		messageLabel.setBounds(new Rectangle(new Point(75, 300), messageLabel.getPreferredSize()));
		
		mainFrame.revalidate();
	}
	
	private static void updateText(String str) {
		String temp = "<html>";
		for(int x = 0; x < str.length(); x++) {
			if(x == 4 || x == 9 || x == 14 || x == 19 || x == 24) {
				temp += str.substring(x, x+1) + "<br>";
			}else{
				temp += str.substring(x, x+1) + " ";
			}
		}
		temp += "</html>";
		

		System.out.println(temp);
		
		letterSquareLabel.setText(temp);
	}
}
