package Takip;

import javax.swing.JFrame;

import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Info extends JFrame implements ActionListener {
	JButton createbuton,readbuton,updatebuton,deletebuton;
	Info() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		add(panel);
		JLabel welcomeText = new JLabel("Masraf Takibe Hoşgeldiniz");
		welcomeText.setBounds(125,0,400,40);
		panel.add(welcomeText);
		
		JLabel isim = new JLabel("Isim :");
		isim.setBounds(20,50,400,40);
		JTextField isimtext = new JTextField(15);
		isimtext.setBounds(100,62,150,20);
		JLabel masraf = new JLabel(" Aylık Masraf :");
		masraf.setBounds(15,100,400,40);
		JTextField masraftext = new JTextField(15);
		masraftext.setBounds(100,112,150,20);
		panel.add(isim);
		panel.add(isimtext);
		panel.add(masraf);
		panel.add(masraftext);
		
		createbuton = new JButton("Create");
		createbuton.setBounds(270,40,100,20);
		createbuton.addActionListener(this);
		panel.add(createbuton);
		
		readbuton = new JButton("Read");
		readbuton.setBounds(270,70,100,20);
		readbuton.addActionListener(this);
		panel.add(readbuton);

		updatebuton = new JButton("Update");
		updatebuton.setBounds(270,100,100,20);
		updatebuton.addActionListener(this);
		panel.add(updatebuton);
		
		deletebuton = new JButton("Delete");
		deletebuton.setBounds(270,130,100,20);
		deletebuton.addActionListener(this);
		panel.add(deletebuton);
		
		
		setTitle("Masraf Takip");
		setSize(400,200);
		setVisible(true);
		setLocation(350, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void actionPerformed(ActionEvent ac) {
		if (ac.getSource() == createbuton) {
			
		}
	}
	
	public static void main(String[] args) {
		new Info();
	}
}
