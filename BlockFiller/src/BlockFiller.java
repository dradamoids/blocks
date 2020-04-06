import javax.swing.JFrame;

public class BlockFiller extends JFrame {

	public static void main(String[] args) {
		BlockFiller game = new BlockFiller();
		game.setSize(Global.BOARDWIDTH,Global.BOARDHEIGHT);
		game.add(new Board());
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setVisible(true);
		

	}

}
