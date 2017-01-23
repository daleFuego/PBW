package graphics;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PBWDrawer extends JFrame {

	private static final long serialVersionUID = 1444772184723391391L;
	private JPanel contentPane;
	private Board board;

	public PBWDrawer(int mode, String fileName, int scale) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		board = new Board(mode, fileName, scale);
		contentPane.add(board);
	}

}
