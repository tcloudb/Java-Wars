import java.awt.Color;
import javax.swing.JFrame;

public class JavaWars {
	public static void main(String args[]){
		JFrame frame = new JFrame("This is a test");
		Grid game = new Grid();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(150,150,game.getMapWidth()*Sprite.GRIDWIDTH+6,game.getMapHeight()*Sprite.GRIDWIDTH+28);
		game.setBackground(Color.white);
		game.addMouseListener(game);
		frame.addKeyListener(game);
		frame.add(game);
		frame.setVisible(true);
	}
}
