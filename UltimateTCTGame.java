import javax.swing.SwingUtilities;

/**
 * Tests Ultimate Tic-Tac-Toe game
 * 
 * @author Connor 11-6-2020
 */
public class UltimateTCTGame {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new UltimateTCT();
			}
		});
	}
}
