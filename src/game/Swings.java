package game;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Swings extends JFrame  {
	JButton b1, b2;
	JLabel l1;
	public Swings() {
		b1 = new JButton(new ImageIcon(getClass().getResource("/image/bt_play2.png")));
		b2 = new JButton(new ImageIcon(getClass().getResource("/image/bt_huongdan2.png")));

		getContentPane().setLayout(null);
		add(b1);
		b1.setBounds(400 / 2 - 70, 200, 140, 35);
		add(b2);
		b2.setBounds(400 / 2 - 70, 250, 140, 35);

		l1 = new JLabel();
		l1.setIcon(new ImageIcon(getClass().getResource("/image/bg_map3.png")));
		getContentPane().add(l1);
		l1.setBounds(0, 0, 400, 530);

		setSize(400, 530);
		setLocationRelativeTo(null);
		setTitle("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				SwingPlaygame.main(new String[]{});
			}
		});
	}

//	public void thoat() {
//		return dispose();
//	}
	public static void main(String[] args) {
		EventQueue.invokeLater(Swings::new);
	}
}
