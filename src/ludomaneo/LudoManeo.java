package ludomaneo;
import javax.swing.JFrame;
public class LudoManeo {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        jframe.setBounds(10, 10, 1000, 600);
        jframe.setTitle("LUDO MANIO");
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameMoves game = new GameMoves();
        game.setFocusable(true);
        game.addKeyListener(game);
        game.addMouseListener(game);
        jframe.add(game);
        jframe.setVisible(true);
}
}