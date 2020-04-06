import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.FlowLayout;


public class Board extends JPanel {
	
	Timer timer;
	int delay = 33;
	BlockGrid grid;
	Piece piece;
	
	public Board() {
		timer = new Timer(delay, new BubbleListener() );

		setBackground(Color.BLACK);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnTShape = new JButton("T Shape");
		add(btnTShape);
		
		JButton btnLShape = new JButton("L Shape");
		add(btnLShape);
		
		JButton btnSquare = new JButton("Square");
		add(btnSquare);
		
		JButton btnLine = new JButton("Line");
		add(btnLine);
		
		JButton btnSShape = new JButton("S Shape");
		add(btnSShape);
		
		JButton btnZShape = new JButton("Z Shape");
		add(btnZShape);
		
		JButton btnBlShape = new JButton("BL Shape");
		add(btnBlShape);
		
		btnTShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.TSHAPE);
				}
				
			}
		);
		btnLShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.L_LSHAPE);
				}
				
			}
		);
		btnSquare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.SQUARE);
				}
				
			}
		);
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.LINE);
				}
				
			}
		);
		btnBlShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.R_LSHAPE);
				}
				
			}
		);
		btnZShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.ZSHAPE);
				}
				
			}
		);
		btnSShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				piece = new Piece(grid,TType.SSHAPE);
				}
				
			}
		);
		
		addMouseListener( new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener( new BubbleListener());
		timer.start();
		
		grid = new BlockGrid();
		piece = new Piece(grid,TType.LINE);
		
	}
	
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		grid.draw(canvas);
		if (piece.getType() != TType.NOSHAPE) {
			piece.draw(canvas);
		}
		
	}
	
	private class BubbleListener extends MouseAdapter implements ActionListener {
		public void mousePressed(MouseEvent e) {
			if (piece.getType() != TType.NOSHAPE) {
			
			if (e.getModifiers() == MouseEvent.BUTTON3_MASK && e.getClickCount() == 1) {
				piece.rotateLeft();
			}
			else if (piece.place()) {
				piece = new Piece(grid, TType.NOSHAPE);
				if (grid.isFull()) {
					grid.init();
				}
			}
			}
			repaint();
		}
		public void mouseDragged(MouseEvent e) {

			repaint();
		}
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (piece.getType() != TType.NOSHAPE) {
			piece.rotateLeft();
			}
		}
		public void actionPerformed(ActionEvent e) {
			//for (Bubble b : bubbleList)
			//	b.update();
			repaint();
		}
		public void mouseMoved(MouseEvent e) {
			if (piece.getType() != TType.NOSHAPE) {
			piece.setPos(e.getX(), e.getY());
			}
			repaint();
		}
	}
}

	

