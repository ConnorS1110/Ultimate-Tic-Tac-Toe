import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Creates and implements Ultimate Tic-Tac-Toe game
 * 
 * @author Connor 11-6-2020
 */
public class UltimateTCT {
	private JFrame jfrm;
	private String playerOneName = "";
	private String playerTwoName = "";
	private boolean smallWin = false;
	private boolean bigWin = false;
	private boolean playerOneGo = true;
	private int[] boardWins = new int[9];
	private int emptyBoards = 0;
	private int playAgain = 2;
	private String[] data = new String[5];
	private String dataLocation = System.getProperty("user.dir") + "\\game_data\\data.txt";
	private File directory;
	private File dataFile;
	private int totalGames = 0;
	private int totalWins = 0;
	private int moveCount = 0;
	private double avgMoves = 0.0;
	private double winPercent = 0.0;
	private ArrayList<JButton> undoQueue = new ArrayList<JButton>();
	private ArrayList<Color> undoColors = new ArrayList<Color>();

	GridBagConstraints gbc = new GridBagConstraints();
	GridBagConstraints pc = new GridBagConstraints();
	GridBagConstraints sc = new GridBagConstraints();
	GridBagConstraints gc = new GridBagConstraints();
	GridBagConstraints sgc = new GridBagConstraints();
	GridBagConstraints tbc = new GridBagConstraints();
	GridBagConstraints bbc = new GridBagConstraints();
	Dimension d = new Dimension(60, 40);

	private JLayeredPane game1;
	private JLayeredPane game2;
	private JLayeredPane game3;
	private JLayeredPane game4;
	private JLayeredPane game5;
	private JLayeredPane game6;
	private JLayeredPane game7;
	private JLayeredPane game8;
	private JLayeredPane game9;
	JLayeredPane[] gamePanels = new JLayeredPane[9];

	private boolean panel1Found;
	private boolean panel2Found;
	private boolean panel3Found;
	private boolean panel4Found;
	private boolean panel5Found;
	private boolean panel6Found;
	private boolean panel7Found;
	private boolean panel8Found;
	private boolean panel9Found;
	boolean[] disablePanels = new boolean[9];

	private JButton button111;
	private JButton button112;
	private JButton button113;
	private JButton button121;
	private JButton button122;
	private JButton button123;
	private JButton button131;
	private JButton button132;
	private JButton button133;

	private JButton button211;
	private JButton button212;
	private JButton button213;
	private JButton button221;
	private JButton button222;
	private JButton button223;
	private JButton button231;
	private JButton button232;
	private JButton button233;

	private JButton button311;
	private JButton button312;
	private JButton button313;
	private JButton button321;
	private JButton button322;
	private JButton button323;
	private JButton button331;
	private JButton button332;
	private JButton button333;

	private JButton button411;
	private JButton button412;
	private JButton button413;
	private JButton button421;
	private JButton button422;
	private JButton button423;
	private JButton button431;
	private JButton button432;
	private JButton button433;

	private JButton button511;
	private JButton button512;
	private JButton button513;
	private JButton button521;
	private JButton button522;
	private JButton button523;
	private JButton button531;
	private JButton button532;
	private JButton button533;

	private JButton button611;
	private JButton button612;
	private JButton button613;
	private JButton button621;
	private JButton button622;
	private JButton button623;
	private JButton button631;
	private JButton button632;
	private JButton button633;

	private JButton button711;
	private JButton button712;
	private JButton button713;
	private JButton button721;
	private JButton button722;
	private JButton button723;
	private JButton button731;
	private JButton button732;
	private JButton button733;

	private JButton button811;
	private JButton button812;
	private JButton button813;
	private JButton button821;
	private JButton button822;
	private JButton button823;
	private JButton button831;
	private JButton button832;
	private JButton button833;

	private JButton button911;
	private JButton button912;
	private JButton button913;
	private JButton button921;
	private JButton button922;
	private JButton button923;
	private JButton button931;
	private JButton button932;
	private JButton button933;

	private JButton playerOne;
	private JButton playerTwo;
	private JButton restart;
	private JButton undo;
	private JButton stats;
	private JTextField winPercentField;
	private JTextField gameCountField;
	private JTextField avgMovesField;

	/**
	 * Constructor
	 */
	public UltimateTCT() {
		jfrm = new JFrame("Ultimate Tic-Tac-Toe Game");
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setSize(600, 500);
		jfrm.setLayout(new GridBagLayout());
		jfrm.setResizable(false);
		jfrm.setVisible(true);

		JPanel topBar = new JPanel();
		topBar.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(6, 10, 0, 0);
		jfrm.add(topBar, gbc);

		JPanel playing = new JPanel();
		playing.setLayout(new GridBagLayout());
		tbc.gridx = 1;
		tbc.gridy = 1;
		jfrm.add(playing, tbc);

		JLabel nowPlaying = new JLabel("Now Playing:");
		pc.gridx = 1;
		pc.insets = new Insets(0, 0, 0, 6);
		playing.add(nowPlaying, pc);

		playerOne = new JButton("Player O");
		pc.gridx = 2;
		playing.add(playerOne, pc);

		playerTwo = new JButton("Player X");
		pc.gridx = 3;
		playing.add(playerTwo, pc);

		JPanel settings = new JPanel();
		settings.setLayout(new GridBagLayout());
		tbc.gridx = 3;
		jfrm.add(settings, tbc);

		JLabel manage = new JLabel("Manage Game:");
		sc.gridx = 1;
		sc.insets = new Insets(0, 0, 0, 6);
		settings.add(manage, sc);

		restart = new JButton("Restart");
		sc.gridx = 2;
		settings.add(restart, sc);

		undo = new JButton("Undo");
		sc.gridx = 3;
		settings.add(undo, sc);

		JPanel games = new JPanel();
		games.setLayout(new GridBagLayout());
		games.setBorder(BorderFactory.createLineBorder(Color.red));
		gbc.insets = new Insets(6, 0, 0, 0);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.weightx = 0;
		gbc.weighty = 0;
		jfrm.add(games, gbc);

		game1 = new JLayeredPane();
		game1.setLayout(new GridLayout(3, 3, 4, 4));
		game1.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		games.add(game1, gc);
		gamePanels[0] = game1;

		button111 = new JButton("");
		button111.setPreferredSize(d);
		game1.add(button111);

		button112 = new JButton("");
		button112.setPreferredSize(d);
		game1.add(button112);

		button113 = new JButton("");
		button113.setPreferredSize(d);
		game1.add(button113);

		button121 = new JButton("");
		button121.setPreferredSize(d);
		game1.add(button121);

		button122 = new JButton("");
		button122.setPreferredSize(d);
		game1.add(button122);

		button123 = new JButton("");
		button123.setPreferredSize(d);
		game1.add(button123);

		button131 = new JButton("");
		button131.setPreferredSize(d);
		game1.add(button131);

		button132 = new JButton("");
		button132.setPreferredSize(d);
		game1.add(button132);

		button133 = new JButton("");
		button133.setPreferredSize(d);
		game1.add(button133);

		game2 = new JLayeredPane();
		game2.setLayout(new GridLayout(3, 3, 4, 4));
		game2.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 2;
		games.add(game2, gc);
		gamePanels[1] = game2;

		button211 = new JButton("");
		button211.setPreferredSize(d);
		game2.add(button211);

		button212 = new JButton("");
		button212.setPreferredSize(d);
		game2.add(button212);

		button213 = new JButton("");
		button213.setPreferredSize(d);
		game2.add(button213);

		button221 = new JButton("");
		button221.setPreferredSize(d);
		game2.add(button221);

		button222 = new JButton("");
		button222.setPreferredSize(d);
		game2.add(button222);

		button223 = new JButton("");
		button223.setPreferredSize(d);
		game2.add(button223);

		button231 = new JButton("");
		button231.setPreferredSize(d);
		game2.add(button231);

		button232 = new JButton("");
		button232.setPreferredSize(d);
		game2.add(button232);

		button233 = new JButton("");
		button233.setPreferredSize(d);
		game2.add(button233);

		game3 = new JLayeredPane();
		game3.setLayout(new GridLayout(3, 3, 4, 4));
		game3.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 3;
		games.add(game3, gc);
		gamePanels[2] = game3;

		button311 = new JButton("");
		button311.setPreferredSize(d);
		game3.add(button311);

		button312 = new JButton("");
		button312.setPreferredSize(d);
		game3.add(button312);

		button313 = new JButton("");
		button313.setPreferredSize(d);
		game3.add(button313);

		button321 = new JButton("");
		button321.setPreferredSize(d);
		game3.add(button321);

		button322 = new JButton("");
		button322.setPreferredSize(d);
		game3.add(button322);

		button323 = new JButton("");
		button323.setPreferredSize(d);
		game3.add(button323);

		button331 = new JButton("");
		button331.setPreferredSize(d);
		game3.add(button331);

		button332 = new JButton("");
		button332.setPreferredSize(d);
		game3.add(button332);

		button333 = new JButton("");
		button333.setPreferredSize(d);
		game3.add(button333);

		game4 = new JLayeredPane();
		game4.setLayout(new GridLayout(3, 3, 4, 4));
		game4.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		games.add(game4, gc);
		gamePanels[3] = game4;

		button411 = new JButton("");
		button411.setPreferredSize(d);
		game4.add(button411);

		button412 = new JButton("");
		button412.setPreferredSize(d);
		game4.add(button412);

		button413 = new JButton("");
		button413.setPreferredSize(d);
		game4.add(button413);

		button421 = new JButton("");
		button421.setPreferredSize(d);
		game4.add(button421);

		button422 = new JButton("");
		button422.setPreferredSize(d);
		game4.add(button422);

		button423 = new JButton("");
		button423.setPreferredSize(d);
		game4.add(button423);

		button431 = new JButton("");
		button431.setPreferredSize(d);
		game4.add(button431);

		button432 = new JButton("");
		button432.setPreferredSize(d);
		game4.add(button432);

		button433 = new JButton("");
		button433.setPreferredSize(d);
		game4.add(button433);

		game5 = new JLayeredPane();
		game5.setLayout(new GridLayout(3, 3, 4, 4));
		game5.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 2;
		games.add(game5, gc);
		gamePanels[4] = game5;

		button511 = new JButton("");
		button511.setPreferredSize(d);
		game5.add(button511);

		button512 = new JButton("");
		button512.setPreferredSize(d);
		game5.add(button512);

		button513 = new JButton("");
		button513.setPreferredSize(d);
		game5.add(button513);

		button521 = new JButton("");
		button521.setPreferredSize(d);
		game5.add(button521);

		button522 = new JButton("");
		button522.setPreferredSize(d);
		game5.add(button522);

		button523 = new JButton("");
		button523.setPreferredSize(d);
		game5.add(button523);

		button531 = new JButton("");
		button531.setPreferredSize(d);
		game5.add(button531);

		button532 = new JButton("");
		button532.setPreferredSize(d);
		game5.add(button532);

		button533 = new JButton("");
		button533.setPreferredSize(d);
		game5.add(button533);

		game6 = new JLayeredPane();
		game6.setLayout(new GridLayout(3, 3, 4, 4));
		game6.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 3;
		games.add(game6, gc);
		gamePanels[5] = game6;

		button611 = new JButton("");
		button611.setPreferredSize(d);
		game6.add(button611);

		button612 = new JButton("");
		button612.setPreferredSize(d);
		game6.add(button612);

		button613 = new JButton("");
		button613.setPreferredSize(d);
		game6.add(button613);

		button621 = new JButton("");
		button621.setPreferredSize(d);
		game6.add(button621);

		button622 = new JButton("");
		button622.setPreferredSize(d);
		game6.add(button622);

		button623 = new JButton("");
		button623.setPreferredSize(d);
		game6.add(button623);

		button631 = new JButton("");
		button631.setPreferredSize(d);
		game6.add(button631);

		button632 = new JButton("");
		button632.setPreferredSize(d);
		game6.add(button632);

		button633 = new JButton("");
		button633.setPreferredSize(d);
		game6.add(button633);

		game7 = new JLayeredPane();
		game7.setLayout(new GridLayout(3, 3, 4, 4));
		game7.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 1;
		gc.gridy = 3;
		gc.gridheight = 1;
		gc.gridwidth = 1;
		games.add(game7, gc);
		gamePanels[6] = game7;

		button711 = new JButton("");
		button711.setPreferredSize(d);
		game7.add(button711);

		button712 = new JButton("");
		button712.setPreferredSize(d);
		game7.add(button712);

		button713 = new JButton("");
		button713.setPreferredSize(d);
		game7.add(button713);

		button721 = new JButton("");
		button721.setPreferredSize(d);
		game7.add(button721);

		button722 = new JButton("");
		button722.setPreferredSize(d);
		game7.add(button722);

		button723 = new JButton("");
		button723.setPreferredSize(d);
		game7.add(button723);

		button731 = new JButton("");
		button731.setPreferredSize(d);
		game7.add(button731);

		button732 = new JButton("");
		button732.setPreferredSize(d);
		game7.add(button732);

		button733 = new JButton("");
		button733.setPreferredSize(d);
		game7.add(button733);

		game8 = new JLayeredPane();
		game8.setLayout(new GridLayout(3, 3, 4, 4));
		game8.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 2;
		games.add(game8, gc);
		gamePanels[7] = game8;

		button811 = new JButton("");
		button811.setPreferredSize(d);
		game8.add(button811);

		button812 = new JButton("");
		button812.setPreferredSize(d);
		game8.add(button812);

		button813 = new JButton("");
		button813.setPreferredSize(d);
		game8.add(button813);

		button821 = new JButton("");
		button821.setPreferredSize(d);
		game8.add(button821);

		button822 = new JButton("");
		button822.setPreferredSize(d);
		game8.add(button822);

		button823 = new JButton("");
		button823.setPreferredSize(d);
		game8.add(button823);

		button831 = new JButton("");
		button831.setPreferredSize(d);
		game8.add(button831);

		button832 = new JButton("");
		button832.setPreferredSize(d);
		game8.add(button832);

		button833 = new JButton("");
		button833.setPreferredSize(d);
		game8.add(button833);

		game9 = new JLayeredPane();
		game9.setLayout(new GridLayout(3, 3, 4, 4));
		game9.setBorder(BorderFactory.createLineBorder(Color.red));
		gc.gridx = 3;
		games.add(game9, gc);
		gamePanels[8] = game9;

		button911 = new JButton("");
		button911.setPreferredSize(d);
		game9.add(button911);

		button912 = new JButton("");
		button912.setPreferredSize(d);
		game9.add(button912);

		button913 = new JButton("");
		button913.setPreferredSize(d);
		game9.add(button913);

		button921 = new JButton("");
		button921.setPreferredSize(d);
		game9.add(button921);

		button922 = new JButton("");
		button922.setPreferredSize(d);
		game9.add(button922);

		button923 = new JButton("");
		button923.setPreferredSize(d);
		game9.add(button923);

		button931 = new JButton("");
		button931.setPreferredSize(d);
		game9.add(button931);

		button932 = new JButton("");
		button932.setPreferredSize(d);
		game9.add(button932);

		button933 = new JButton("");
		button933.setPreferredSize(d);
		game9.add(button933);

		JPanel bottomBar = new JPanel();
		bottomBar.setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		gbc.gridy = 5;
		jfrm.add(bottomBar, gbc);

		stats = new JButton("Playing Stats");
		bbc.insets = new Insets(0, 0, 0, 6);
		bbc.gridx = 1;
		bottomBar.add(stats, bbc);

		JLabel winPercent = new JLabel("Win %:");
		bbc.gridx = 2;
		bottomBar.add(winPercent, bbc);

		winPercentField = new JTextField();
		winPercentField.setEditable(false);
		bbc.fill = GridBagConstraints.HORIZONTAL;
		bbc.gridx = 3;
		bbc.weightx = 0.2;
		bottomBar.add(winPercentField, bbc);

		JLabel gameCount = new JLabel("Total # of Games:");
		bbc.gridx = 4;
		bbc.weightx = 0.0;
		bottomBar.add(gameCount, bbc);

		gameCountField = new JTextField();
		gameCountField.setEditable(false);
		bbc.gridx = 5;
		bbc.weightx = 0.2;
		bottomBar.add(gameCountField, bbc);

		JLabel avgMoves = new JLabel("Average # of Move Per Win:");
		bbc.gridx = 6;
		bbc.weightx = 0;
		bottomBar.add(avgMoves, bbc);

		avgMovesField = new JTextField();
		avgMovesField.setEditable(false);
		bbc.gridx = 7;
		bbc.weightx = 0.2;
		bottomBar.add(avgMovesField, bbc);

		addButtonListeners();

		try {
			directory = new File(System.getProperty("user.dir") + "\\game_data");
			dataFile = new File(dataLocation);
			if (!directory.exists()) {
				directory.mkdir();
			}
			dataFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Error creating data file.");
			e.printStackTrace();
		}

		startGame();
	}

	/**
	 * Adds actionListeners to all buttons
	 */
	public void addButtonListeners() {
		button111.addActionListener(new GameButton());
		button112.addActionListener(new GameButton());
		button113.addActionListener(new GameButton());
		button121.addActionListener(new GameButton());
		button122.addActionListener(new GameButton());
		button123.addActionListener(new GameButton());
		button131.addActionListener(new GameButton());
		button132.addActionListener(new GameButton());
		button133.addActionListener(new GameButton());

		button211.addActionListener(new GameButton());
		button212.addActionListener(new GameButton());
		button213.addActionListener(new GameButton());
		button221.addActionListener(new GameButton());
		button222.addActionListener(new GameButton());
		button223.addActionListener(new GameButton());
		button231.addActionListener(new GameButton());
		button232.addActionListener(new GameButton());
		button233.addActionListener(new GameButton());

		button311.addActionListener(new GameButton());
		button312.addActionListener(new GameButton());
		button313.addActionListener(new GameButton());
		button321.addActionListener(new GameButton());
		button322.addActionListener(new GameButton());
		button323.addActionListener(new GameButton());
		button331.addActionListener(new GameButton());
		button332.addActionListener(new GameButton());
		button333.addActionListener(new GameButton());

		button411.addActionListener(new GameButton());
		button412.addActionListener(new GameButton());
		button413.addActionListener(new GameButton());
		button421.addActionListener(new GameButton());
		button422.addActionListener(new GameButton());
		button423.addActionListener(new GameButton());
		button431.addActionListener(new GameButton());
		button432.addActionListener(new GameButton());
		button433.addActionListener(new GameButton());

		button511.addActionListener(new GameButton());
		button512.addActionListener(new GameButton());
		button513.addActionListener(new GameButton());
		button521.addActionListener(new GameButton());
		button522.addActionListener(new GameButton());
		button523.addActionListener(new GameButton());
		button531.addActionListener(new GameButton());
		button532.addActionListener(new GameButton());
		button533.addActionListener(new GameButton());

		button611.addActionListener(new GameButton());
		button612.addActionListener(new GameButton());
		button613.addActionListener(new GameButton());
		button621.addActionListener(new GameButton());
		button622.addActionListener(new GameButton());
		button623.addActionListener(new GameButton());
		button631.addActionListener(new GameButton());
		button632.addActionListener(new GameButton());
		button633.addActionListener(new GameButton());

		button711.addActionListener(new GameButton());
		button712.addActionListener(new GameButton());
		button713.addActionListener(new GameButton());
		button721.addActionListener(new GameButton());
		button722.addActionListener(new GameButton());
		button723.addActionListener(new GameButton());
		button731.addActionListener(new GameButton());
		button732.addActionListener(new GameButton());
		button733.addActionListener(new GameButton());

		button811.addActionListener(new GameButton());
		button812.addActionListener(new GameButton());
		button813.addActionListener(new GameButton());
		button821.addActionListener(new GameButton());
		button822.addActionListener(new GameButton());
		button823.addActionListener(new GameButton());
		button831.addActionListener(new GameButton());
		button832.addActionListener(new GameButton());
		button833.addActionListener(new GameButton());

		button911.addActionListener(new GameButton());
		button912.addActionListener(new GameButton());
		button913.addActionListener(new GameButton());
		button921.addActionListener(new GameButton());
		button922.addActionListener(new GameButton());
		button923.addActionListener(new GameButton());
		button931.addActionListener(new GameButton());
		button932.addActionListener(new GameButton());
		button933.addActionListener(new GameButton());

		playerOne.addActionListener(new SettingsListener());
		playerTwo.addActionListener(new SettingsListener());
		restart.addActionListener(new SettingsListener());
		undo.addActionListener(new SettingsListener());
		stats.addActionListener(new SettingsListener());
	}// End of actionListener method

	/**
	 * Logic for tic-tac-toe game buttons. Buttons for the current game are the only
	 * ones enabled. Panel of buttons is turned off when a game is completed.
	 * 
	 * @author Connor 11-16-2020
	 */
	class GameButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			panel1Found = game1.isAncestorOf((Component) e.getSource());
			panel2Found = game2.isAncestorOf((Component) e.getSource());
			panel3Found = game3.isAncestorOf((Component) e.getSource());
			panel4Found = game4.isAncestorOf((Component) e.getSource());
			panel5Found = game5.isAncestorOf((Component) e.getSource());
			panel6Found = game6.isAncestorOf((Component) e.getSource());
			panel7Found = game7.isAncestorOf((Component) e.getSource());
			panel8Found = game8.isAncestorOf((Component) e.getSource());
			panel9Found = game9.isAncestorOf((Component) e.getSource());

			boolean[] tempList = { panel1Found, panel2Found, panel3Found, panel4Found, panel5Found, panel6Found,
					panel7Found, panel8Found, panel9Found };
			for (int x = 0; x < disablePanels.length; x++) {
				disablePanels[x] = tempList[x];
			}

			// Disables all panels that aren't a part of the current small game board
			for (int i = 0; i < disablePanels.length; i++) {
				if (disablePanels[i] == false) {
					if (gamePanels[i] != null) {
						Component[] currentComponents = gamePanels[i].getComponents();
						for (int j = 0; j < currentComponents.length; j++) {
							currentComponents[j].setEnabled(false);
						}
					}
				}
			}

			// Changes color of button and stores record of button clicked and previous
			// button color
			if (playerOneGo == true) {
				playerOne.setEnabled(false);
				playerOne.setBackground(null);
				playerTwo.setEnabled(true);
				playerTwo.setBackground(Color.RED);
				JButton b = (JButton) e.getSource();
				b.setBackground(Color.BLUE);
				undoQueue.add(b);
				undoColors.add(b.getBackground());
			} else {
				playerOne.setEnabled(true);
				playerOne.setBackground(Color.BLUE);
				playerTwo.setEnabled(false);
				playerTwo.setBackground(null);
				JButton b = (JButton) e.getSource();
				b.setBackground(Color.RED);
				undoQueue.add(b);
				undoColors.add(b.getBackground());
			}
			checkSmallWin();
			playerOneGo = !playerOneGo;
			moveCount++;
		}// End of actionPerformed method
	}// End of GameButton class

	/**
	 * Logic for menu buttons
	 * 
	 * @author Connor 11-16-2020
	 */
	class SettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();

			// Clears the board and restarts the game
			if (b.getText().contentEquals("Restart")) {
				gamePanels[0] = game1;
				gamePanels[1] = game2;
				gamePanels[2] = game3;
				gamePanels[3] = game4;
				gamePanels[4] = game5;
				gamePanels[5] = game6;
				gamePanels[6] = game7;
				gamePanels[7] = game8;
				gamePanels[8] = game9;

				for (int i = 0; i < gamePanels.length; i++) {
					Component[] currentComponents = gamePanels[i].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						currentComponents[j].setBackground(null);
						currentComponents[j].setEnabled(true);
					}
				}
				startGame();
			} // End of Restart algorithm

			// Reads the data text file and displays the results in the proper text fields
			if (b.getText().contentEquals("Playing Stats")) {
				try {
					int lineCount = 0;
					Scanner scan = new Scanner(dataFile);
					Scanner scanSplit = new Scanner(dataFile);
					while (scan.hasNextLine()) {
						lineCount++;
						scan.nextLine();
					}
					scan.close();
					if (lineCount == 0) {
						JOptionPane.showMessageDialog(jfrm, "There is no data to show.");
					} else {
						data = scanSplit.nextLine().split(" ");
						double percent = Double.valueOf(data[4]) * 100;
						winPercentField.setText(String.format("%.2f", percent));
						gameCountField.setText(data[0]);
						avgMovesField.setText(String.format("%.2f", Double.valueOf(data[3])));
						scanSplit.close();
					}
				} catch (FileNotFoundException e1) {
					System.out.println("Error reading data file");
					e1.printStackTrace();
				}
			} // End of Playing Statistic algorithm

			// Undo logic to accommodate for a move that intersects with a completed game,
			// or is before the first move
			if (b.getText().contentEquals("Undo")) {
				try {
					JLayeredPane[] undoPanels = { game1, game2, game3, game4, game5, game6, game7, game8, game9 };
					if (undoQueue.size() > 0) {
						if ((undoQueue.get(undoQueue.size() - 1).getBackground() == Color.BLUE)
								|| (undoQueue.get(undoQueue.size() - 1).getBackground() == Color.RED)
								|| (undoQueue.get(undoQueue.size() - 1).getBackground() == Color.YELLOW)) {
							Container location = undoQueue.get(undoQueue.size() - 1).getParent();
							boolean valid = true;
							int blueCount = 0;
							int redCount = 0;
							int yellowCount = 0;
							for (int i = 0; i < undoPanels.length; i++) {
								if (undoPanels[i] == location) {
									Component[] currentComponents = undoPanels[i].getComponents();
									for (int k = 0; k < currentComponents.length; k++) {
										if (currentComponents[k].getBackground() == Color.BLUE) {
											blueCount++;
										}
										if (currentComponents[k].getBackground() == Color.RED) {
											redCount++;
										}
										if (currentComponents[k].getBackground() == Color.YELLOW) {
											yellowCount++;
										}
									}
								}
							}

							// If the board is completed, a move can't be undone
							if (blueCount == 9 || redCount == 9 || yellowCount == 9) {
								valid = false;
							}
							if (valid == true) {
								if (undoColors.size() > 1) {
									undoQueue.get(undoQueue.size() - 1)
											.setBackground(undoColors.get(undoColors.size() - 2));
									undoColors.remove(undoColors.get(undoColors.size() - 2));
								} else {
									undoQueue.get(undoQueue.size() - 1).setBackground(null);
									undoColors.clear();
								}
								moveCount--;
								if (playerOneGo == true) {
									playerOne.setEnabled(false);
									playerOne.setBackground(null);
									playerTwo.setEnabled(true);
									playerTwo.setBackground(Color.RED);
								} else {
									playerOne.setEnabled(true);
									playerOne.setBackground(Color.BLUE);
									playerTwo.setEnabled(false);
									playerTwo.setBackground(null);
								}
								playerOneGo = !playerOneGo;
								undoQueue.remove(undoQueue.get(undoQueue.size() - 1));
							} else {
								JOptionPane.showMessageDialog(jfrm, "You can't undo a completed game");
							}
						} else {
							JOptionPane.showMessageDialog(jfrm, "You can't undo");
						}
					} else {
						JOptionPane.showMessageDialog(jfrm, "You can't undo");
					}
				} catch (NullPointerException error) {
					JOptionPane.showMessageDialog(jfrm, "You can't undo");
					error.printStackTrace();
				}
			} // End of Undo algorithm
		}// End of actionPerformed method
	}// End of SettingListener class

	/**
	 * Checks if the whole game has been won
	 */
	private void checkBigWin() {
		bigWin = false;
		if (bigWin == false) {

			// Player One wins the whole game
			if ((boardWins[0] == 1 && boardWins[1] == 1 && boardWins[2] == 1)
					|| (boardWins[3] == 1 && boardWins[4] == 1 && boardWins[5] == 1)
					|| (boardWins[6] == 1 && boardWins[7] == 1 && boardWins[8] == 1)
					|| (boardWins[0] == 1 && boardWins[3] == 1 && boardWins[6] == 1)
					|| (boardWins[1] == 1 && boardWins[4] == 1 && boardWins[7] == 1)
					|| (boardWins[2] == 1 && boardWins[5] == 1 && boardWins[8] == 1)
					|| (boardWins[6] == 1 && boardWins[4] == 1 && boardWins[2] == 1)
					|| (boardWins[0] == 1 && boardWins[4] == 1 && boardWins[8] == 1)) {
				JOptionPane.showMessageDialog(jfrm, playerOneName + " won the game!");
				playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
						JOptionPane.YES_NO_OPTION);
				totalGames++;
				totalWins++;
				bigWin = true;

				// If the player doesn't click yes or no
				while (playAgain == -1) {
					playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
							JOptionPane.YES_NO_OPTION);
				}
			}

			// Player Two wins the whole game
			else if ((boardWins[0] == 2 && boardWins[1] == 2 && boardWins[2] == 2)
					|| (boardWins[3] == 2 && boardWins[4] == 2 && boardWins[5] == 2)
					|| (boardWins[6] == 2 && boardWins[7] == 2 && boardWins[8] == 2)
					|| (boardWins[0] == 2 && boardWins[3] == 2 && boardWins[6] == 2)
					|| (boardWins[1] == 2 && boardWins[4] == 2 && boardWins[7] == 2)
					|| (boardWins[2] == 2 && boardWins[5] == 2 && boardWins[8] == 2)
					|| (boardWins[6] == 2 && boardWins[4] == 2 && boardWins[2] == 2)
					|| (boardWins[0] == 2 && boardWins[4] == 2 && boardWins[8] == 2)) {
				JOptionPane.showMessageDialog(jfrm, playerTwoName + " won the game!");
				playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
						JOptionPane.YES_NO_OPTION);
				totalGames++;
				totalWins++;
				bigWin = true;

				// If the player doesn't click yes or no
				while (playAgain == -1) {
					playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
							JOptionPane.YES_NO_OPTION);
				}
			}

			// Checks if nobody won
			else {
				emptyBoards = 0;
				for (int i = 0; i < boardWins.length; i++) {
					if (boardWins[i] == 0) {
						emptyBoards++;
					}
				}
				if (emptyBoards == 0) {
					JOptionPane.showMessageDialog(jfrm, "It was a tie!");
					playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
							JOptionPane.YES_NO_OPTION);

					// If the player doesn't click yes or no
					while (playAgain == -1) {
						playAgain = JOptionPane.showConfirmDialog(jfrm, "Do you want to play again?", "Play Again",
								JOptionPane.YES_NO_OPTION);
					}
					totalGames++;
					bigWin = true;
				}
			}

			if (bigWin == true) {
				manageData();
			}

			// The players decide to play again
			if (playAgain == 0) {
				gamePanels[0] = game1;
				gamePanels[1] = game2;
				gamePanels[2] = game3;
				gamePanels[3] = game4;
				gamePanels[4] = game5;
				gamePanels[5] = game6;
				gamePanels[6] = game7;
				gamePanels[7] = game8;
				gamePanels[8] = game9;

				for (int i = 0; i < gamePanels.length; i++) {
					Component[] currentComponents = gamePanels[i].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						currentComponents[j].setBackground(null);
						currentComponents[j].setEnabled(true);
					}
				}
				startGame();
			}

			// The players don't play again
			else {
				if (bigWin == true) {
					System.exit(0);
				}
			}
		}
	}// end of checkBigWin method

	/**
	 * Updates game stats after a game has been completed
	 */
	private void manageData() {
		try {
			int lineCount = 0;
			Scanner scan = new Scanner(dataFile);
			Scanner scanSplit = new Scanner(dataFile);
			while (scan.hasNextLine()) {
				lineCount++;
				scan.nextLine();
			}
			scan.close();
			if (lineCount == 0) {
				avgMoves = moveCount;
				winPercent = totalWins;
				FileWriter dataWriter = new FileWriter(dataFile);
				dataWriter.write(totalGames + " " + totalWins + " " + moveCount + " " + avgMoves + " " + winPercent);
				dataWriter.close();
			} else {
				data = scanSplit.nextLine().split(" ");
				totalGames += Integer.valueOf(data[0]);
				totalWins += Integer.valueOf(data[1]);
				moveCount += Integer.valueOf(data[2]);
				avgMoves = moveCount / totalGames;
				winPercent = Double.valueOf(totalWins) / Double.valueOf(totalGames);
				scanSplit.close();
				FileWriter dataWriter = new FileWriter(dataFile);
				dataWriter.write(totalGames + " " + totalWins + " " + moveCount + " " + avgMoves + " " + winPercent);
				dataWriter.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error reading data file");
		} catch (IOException e) {
			System.out.println("Error updating data file");
		}
	}// End of manageData method

	/**
	 * Checks if a small gameboard has been completed
	 */
	private void checkSmallWin() {
		smallWin = false;
		if (smallWin == false) {

			// Win conditions for game1
			if (panel1Found == true) {

				// Checks if player 1 won the small game
				if ((button111.getBackground() == Color.BLUE && button112.getBackground() == Color.BLUE
						&& button113.getBackground() == Color.BLUE)
						|| (button121.getBackground() == Color.BLUE && button122.getBackground() == Color.BLUE
								&& button123.getBackground() == Color.BLUE)
						|| (button131.getBackground() == Color.BLUE && button132.getBackground() == Color.BLUE
								&& button133.getBackground() == Color.BLUE)
						|| (button111.getBackground() == Color.BLUE && button121.getBackground() == Color.BLUE
								&& button131.getBackground() == Color.BLUE)
						|| (button112.getBackground() == Color.BLUE && button122.getBackground() == Color.BLUE
								&& button132.getBackground() == Color.BLUE)
						|| (button113.getBackground() == Color.BLUE && button123.getBackground() == Color.BLUE
								&& button133.getBackground() == Color.BLUE)
						|| (button111.getBackground() == Color.BLUE && button122.getBackground() == Color.BLUE
								&& button133.getBackground() == Color.BLUE)
						|| (button131.getBackground() == Color.BLUE && button122.getBackground() == Color.BLUE
								&& button113.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[0] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button111.getBackground() == Color.RED && button112.getBackground() == Color.RED
						&& button113.getBackground() == Color.RED)
						|| (button121.getBackground() == Color.RED && button122.getBackground() == Color.RED
								&& button123.getBackground() == Color.RED)
						|| (button131.getBackground() == Color.RED && button132.getBackground() == Color.RED
								&& button133.getBackground() == Color.RED)
						|| (button111.getBackground() == Color.RED && button121.getBackground() == Color.RED
								&& button131.getBackground() == Color.RED)
						|| (button112.getBackground() == Color.RED && button122.getBackground() == Color.RED
								&& button132.getBackground() == Color.RED)
						|| (button113.getBackground() == Color.RED && button123.getBackground() == Color.RED
								&& button133.getBackground() == Color.RED)
						|| (button111.getBackground() == Color.RED && button122.getBackground() == Color.RED
								&& button133.getBackground() == Color.RED)
						|| (button131.getBackground() == Color.RED && button122.getBackground() == Color.RED
								&& button113.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[0] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[0].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[0] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game1 if the board is completed
				if (smallWin == true) {
					gamePanels[0] = null;
				}
			}

			// Win conditions for game2
			if (panel2Found == true) {

				// Checks if player 1 won the small game
				if ((button211.getBackground() == Color.BLUE && button212.getBackground() == Color.BLUE
						&& button213.getBackground() == Color.BLUE)
						|| (button221.getBackground() == Color.BLUE && button222.getBackground() == Color.BLUE
								&& button223.getBackground() == Color.BLUE)
						|| (button231.getBackground() == Color.BLUE && button232.getBackground() == Color.BLUE
								&& button233.getBackground() == Color.BLUE)
						|| (button211.getBackground() == Color.BLUE && button221.getBackground() == Color.BLUE
								&& button231.getBackground() == Color.BLUE)
						|| (button212.getBackground() == Color.BLUE && button222.getBackground() == Color.BLUE
								&& button232.getBackground() == Color.BLUE)
						|| (button213.getBackground() == Color.BLUE && button223.getBackground() == Color.BLUE
								&& button233.getBackground() == Color.BLUE)
						|| (button211.getBackground() == Color.BLUE && button222.getBackground() == Color.BLUE
								&& button233.getBackground() == Color.BLUE)
						|| (button231.getBackground() == Color.BLUE && button222.getBackground() == Color.BLUE
								&& button213.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[1] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button211.getBackground() == Color.RED && button212.getBackground() == Color.RED
						&& button213.getBackground() == Color.RED)
						|| (button221.getBackground() == Color.RED && button222.getBackground() == Color.RED
								&& button223.getBackground() == Color.RED)
						|| (button231.getBackground() == Color.RED && button232.getBackground() == Color.RED
								&& button233.getBackground() == Color.RED)
						|| (button211.getBackground() == Color.RED && button221.getBackground() == Color.RED
								&& button231.getBackground() == Color.RED)
						|| (button212.getBackground() == Color.RED && button222.getBackground() == Color.RED
								&& button232.getBackground() == Color.RED)
						|| (button213.getBackground() == Color.RED && button223.getBackground() == Color.RED
								&& button233.getBackground() == Color.RED)
						|| (button211.getBackground() == Color.RED && button222.getBackground() == Color.RED
								&& button233.getBackground() == Color.RED)
						|| (button231.getBackground() == Color.RED && button222.getBackground() == Color.RED
								&& button213.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[1] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[1].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[1] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game1 if the board is completed
				if (smallWin == true) {
					gamePanels[1] = null;
				}
			}

			// Win conditions for game3
			if (panel3Found == true) {

				// Checks if player 1 won the small game
				if ((button311.getBackground() == Color.BLUE && button312.getBackground() == Color.BLUE
						&& button313.getBackground() == Color.BLUE)
						|| (button321.getBackground() == Color.BLUE && button322.getBackground() == Color.BLUE
								&& button323.getBackground() == Color.BLUE)
						|| (button331.getBackground() == Color.BLUE && button332.getBackground() == Color.BLUE
								&& button333.getBackground() == Color.BLUE)
						|| (button311.getBackground() == Color.BLUE && button321.getBackground() == Color.BLUE
								&& button331.getBackground() == Color.BLUE)
						|| (button312.getBackground() == Color.BLUE && button322.getBackground() == Color.BLUE
								&& button332.getBackground() == Color.BLUE)
						|| (button313.getBackground() == Color.BLUE && button323.getBackground() == Color.BLUE
								&& button333.getBackground() == Color.BLUE)
						|| (button311.getBackground() == Color.BLUE && button322.getBackground() == Color.BLUE
								&& button333.getBackground() == Color.BLUE)
						|| (button331.getBackground() == Color.BLUE && button322.getBackground() == Color.BLUE
								&& button313.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[2] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button311.getBackground() == Color.RED && button312.getBackground() == Color.RED
						&& button313.getBackground() == Color.RED)
						|| (button321.getBackground() == Color.RED && button322.getBackground() == Color.RED
								&& button323.getBackground() == Color.RED)
						|| (button331.getBackground() == Color.RED && button332.getBackground() == Color.RED
								&& button333.getBackground() == Color.RED)
						|| (button311.getBackground() == Color.RED && button321.getBackground() == Color.RED
								&& button331.getBackground() == Color.RED)
						|| (button312.getBackground() == Color.RED && button322.getBackground() == Color.RED
								&& button332.getBackground() == Color.RED)
						|| (button313.getBackground() == Color.RED && button323.getBackground() == Color.RED
								&& button333.getBackground() == Color.RED)
						|| (button311.getBackground() == Color.RED && button322.getBackground() == Color.RED
								&& button333.getBackground() == Color.RED)
						|| (button331.getBackground() == Color.RED && button322.getBackground() == Color.RED
								&& button313.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[2] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[2].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[2] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game1 if the board is completed
				if (smallWin == true) {
					gamePanels[2] = null;
				}
			}

			// Win conditions for game4
			if (panel4Found == true) {

				// Checks if player 1 won the small game
				if ((button411.getBackground() == Color.BLUE && button412.getBackground() == Color.BLUE
						&& button413.getBackground() == Color.BLUE)
						|| (button421.getBackground() == Color.BLUE && button422.getBackground() == Color.BLUE
								&& button423.getBackground() == Color.BLUE)
						|| (button431.getBackground() == Color.BLUE && button432.getBackground() == Color.BLUE
								&& button433.getBackground() == Color.BLUE)
						|| (button411.getBackground() == Color.BLUE && button421.getBackground() == Color.BLUE
								&& button431.getBackground() == Color.BLUE)
						|| (button412.getBackground() == Color.BLUE && button422.getBackground() == Color.BLUE
								&& button432.getBackground() == Color.BLUE)
						|| (button413.getBackground() == Color.BLUE && button423.getBackground() == Color.BLUE
								&& button433.getBackground() == Color.BLUE)
						|| (button411.getBackground() == Color.BLUE && button422.getBackground() == Color.BLUE
								&& button433.getBackground() == Color.BLUE)
						|| (button431.getBackground() == Color.BLUE && button422.getBackground() == Color.BLUE
								&& button413.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[3] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button411.getBackground() == Color.RED && button412.getBackground() == Color.RED
						&& button413.getBackground() == Color.RED)
						|| (button421.getBackground() == Color.RED && button422.getBackground() == Color.RED
								&& button423.getBackground() == Color.RED)
						|| (button431.getBackground() == Color.RED && button432.getBackground() == Color.RED
								&& button433.getBackground() == Color.RED)
						|| (button411.getBackground() == Color.RED && button421.getBackground() == Color.RED
								&& button431.getBackground() == Color.RED)
						|| (button412.getBackground() == Color.RED && button422.getBackground() == Color.RED
								&& button432.getBackground() == Color.RED)
						|| (button413.getBackground() == Color.RED && button423.getBackground() == Color.RED
								&& button433.getBackground() == Color.RED)
						|| (button411.getBackground() == Color.RED && button422.getBackground() == Color.RED
								&& button433.getBackground() == Color.RED)
						|| (button431.getBackground() == Color.RED && button422.getBackground() == Color.RED
								&& button413.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[3] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[3].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[3] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game4 if the board is completed
				if (smallWin == true) {
					gamePanels[3] = null;
				}
			}

			// Win conditions for game5
			if (panel5Found == true) {

				// Checks if player 1 won the small game
				if ((button511.getBackground() == Color.BLUE && button512.getBackground() == Color.BLUE
						&& button513.getBackground() == Color.BLUE)
						|| (button521.getBackground() == Color.BLUE && button522.getBackground() == Color.BLUE
								&& button523.getBackground() == Color.BLUE)
						|| (button531.getBackground() == Color.BLUE && button532.getBackground() == Color.BLUE
								&& button533.getBackground() == Color.BLUE)
						|| (button511.getBackground() == Color.BLUE && button521.getBackground() == Color.BLUE
								&& button531.getBackground() == Color.BLUE)
						|| (button512.getBackground() == Color.BLUE && button522.getBackground() == Color.BLUE
								&& button532.getBackground() == Color.BLUE)
						|| (button513.getBackground() == Color.BLUE && button523.getBackground() == Color.BLUE
								&& button533.getBackground() == Color.BLUE)
						|| (button511.getBackground() == Color.BLUE && button522.getBackground() == Color.BLUE
								&& button533.getBackground() == Color.BLUE)
						|| (button531.getBackground() == Color.BLUE && button522.getBackground() == Color.BLUE
								&& button513.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[4] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button511.getBackground() == Color.RED && button512.getBackground() == Color.RED
						&& button513.getBackground() == Color.RED)
						|| (button521.getBackground() == Color.RED && button522.getBackground() == Color.RED
								&& button523.getBackground() == Color.RED)
						|| (button531.getBackground() == Color.RED && button532.getBackground() == Color.RED
								&& button533.getBackground() == Color.RED)
						|| (button511.getBackground() == Color.RED && button521.getBackground() == Color.RED
								&& button531.getBackground() == Color.RED)
						|| (button512.getBackground() == Color.RED && button522.getBackground() == Color.RED
								&& button532.getBackground() == Color.RED)
						|| (button513.getBackground() == Color.RED && button523.getBackground() == Color.RED
								&& button533.getBackground() == Color.RED)
						|| (button511.getBackground() == Color.RED && button522.getBackground() == Color.RED
								&& button533.getBackground() == Color.RED)
						|| (button531.getBackground() == Color.RED && button522.getBackground() == Color.RED
								&& button513.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[4] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[4].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[4] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game5 if the board is completed
				if (smallWin == true) {
					gamePanels[4] = null;
				}
			}

			// Win conditions for game6
			if (panel6Found == true) {

				// Checks if player 1 won the small game
				if ((button611.getBackground() == Color.BLUE && button612.getBackground() == Color.BLUE
						&& button613.getBackground() == Color.BLUE)
						|| (button621.getBackground() == Color.BLUE && button622.getBackground() == Color.BLUE
								&& button623.getBackground() == Color.BLUE)
						|| (button631.getBackground() == Color.BLUE && button632.getBackground() == Color.BLUE
								&& button633.getBackground() == Color.BLUE)
						|| (button611.getBackground() == Color.BLUE && button621.getBackground() == Color.BLUE
								&& button631.getBackground() == Color.BLUE)
						|| (button612.getBackground() == Color.BLUE && button622.getBackground() == Color.BLUE
								&& button632.getBackground() == Color.BLUE)
						|| (button613.getBackground() == Color.BLUE && button623.getBackground() == Color.BLUE
								&& button633.getBackground() == Color.BLUE)
						|| (button611.getBackground() == Color.BLUE && button622.getBackground() == Color.BLUE
								&& button633.getBackground() == Color.BLUE)
						|| (button631.getBackground() == Color.BLUE && button622.getBackground() == Color.BLUE
								&& button613.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[5] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button611.getBackground() == Color.RED && button612.getBackground() == Color.RED
						&& button613.getBackground() == Color.RED)
						|| (button621.getBackground() == Color.RED && button622.getBackground() == Color.RED
								&& button623.getBackground() == Color.RED)
						|| (button631.getBackground() == Color.RED && button632.getBackground() == Color.RED
								&& button633.getBackground() == Color.RED)
						|| (button611.getBackground() == Color.RED && button621.getBackground() == Color.RED
								&& button631.getBackground() == Color.RED)
						|| (button612.getBackground() == Color.RED && button622.getBackground() == Color.RED
								&& button632.getBackground() == Color.RED)
						|| (button613.getBackground() == Color.RED && button623.getBackground() == Color.RED
								&& button633.getBackground() == Color.RED)
						|| (button611.getBackground() == Color.RED && button622.getBackground() == Color.RED
								&& button633.getBackground() == Color.RED)
						|| (button631.getBackground() == Color.RED && button622.getBackground() == Color.RED
								&& button613.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[5] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[5].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[5] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game6 if the board is completed
				if (smallWin == true) {
					gamePanels[5] = null;
				}
			}

			// Win conditions for game7
			if (panel7Found == true) {

				// Checks if player 1 won the small game
				if ((button711.getBackground() == Color.BLUE && button712.getBackground() == Color.BLUE
						&& button713.getBackground() == Color.BLUE)
						|| (button721.getBackground() == Color.BLUE && button722.getBackground() == Color.BLUE
								&& button723.getBackground() == Color.BLUE)
						|| (button731.getBackground() == Color.BLUE && button732.getBackground() == Color.BLUE
								&& button733.getBackground() == Color.BLUE)
						|| (button711.getBackground() == Color.BLUE && button721.getBackground() == Color.BLUE
								&& button731.getBackground() == Color.BLUE)
						|| (button712.getBackground() == Color.BLUE && button722.getBackground() == Color.BLUE
								&& button732.getBackground() == Color.BLUE)
						|| (button713.getBackground() == Color.BLUE && button723.getBackground() == Color.BLUE
								&& button733.getBackground() == Color.BLUE)
						|| (button711.getBackground() == Color.BLUE && button722.getBackground() == Color.BLUE
								&& button733.getBackground() == Color.BLUE)
						|| (button731.getBackground() == Color.BLUE && button722.getBackground() == Color.BLUE
								&& button713.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[6] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button711.getBackground() == Color.RED && button712.getBackground() == Color.RED
						&& button713.getBackground() == Color.RED)
						|| (button721.getBackground() == Color.RED && button722.getBackground() == Color.RED
								&& button723.getBackground() == Color.RED)
						|| (button731.getBackground() == Color.RED && button732.getBackground() == Color.RED
								&& button733.getBackground() == Color.RED)
						|| (button711.getBackground() == Color.RED && button721.getBackground() == Color.RED
								&& button731.getBackground() == Color.RED)
						|| (button712.getBackground() == Color.RED && button722.getBackground() == Color.RED
								&& button732.getBackground() == Color.RED)
						|| (button713.getBackground() == Color.RED && button723.getBackground() == Color.RED
								&& button733.getBackground() == Color.RED)
						|| (button711.getBackground() == Color.RED && button722.getBackground() == Color.RED
								&& button733.getBackground() == Color.RED)
						|| (button731.getBackground() == Color.RED && button722.getBackground() == Color.RED
								&& button713.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[6] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[6].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[6] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game7 if the board is completed
				if (smallWin == true) {
					gamePanels[6] = null;
				}
			}

			// Win conditions for game8
			if (panel8Found == true) {

				// Checks if player 1 won the small game
				if ((button811.getBackground() == Color.BLUE && button812.getBackground() == Color.BLUE
						&& button813.getBackground() == Color.BLUE)
						|| (button821.getBackground() == Color.BLUE && button822.getBackground() == Color.BLUE
								&& button823.getBackground() == Color.BLUE)
						|| (button831.getBackground() == Color.BLUE && button832.getBackground() == Color.BLUE
								&& button833.getBackground() == Color.BLUE)
						|| (button811.getBackground() == Color.BLUE && button821.getBackground() == Color.BLUE
								&& button831.getBackground() == Color.BLUE)
						|| (button812.getBackground() == Color.BLUE && button822.getBackground() == Color.BLUE
								&& button832.getBackground() == Color.BLUE)
						|| (button813.getBackground() == Color.BLUE && button823.getBackground() == Color.BLUE
								&& button833.getBackground() == Color.BLUE)
						|| (button811.getBackground() == Color.BLUE && button822.getBackground() == Color.BLUE
								&& button833.getBackground() == Color.BLUE)
						|| (button831.getBackground() == Color.BLUE && button822.getBackground() == Color.BLUE
								&& button813.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[7] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button811.getBackground() == Color.RED && button812.getBackground() == Color.RED
						&& button813.getBackground() == Color.RED)
						|| (button821.getBackground() == Color.RED && button822.getBackground() == Color.RED
								&& button823.getBackground() == Color.RED)
						|| (button831.getBackground() == Color.RED && button832.getBackground() == Color.RED
								&& button833.getBackground() == Color.RED)
						|| (button811.getBackground() == Color.RED && button821.getBackground() == Color.RED
								&& button831.getBackground() == Color.RED)
						|| (button812.getBackground() == Color.RED && button822.getBackground() == Color.RED
								&& button832.getBackground() == Color.RED)
						|| (button813.getBackground() == Color.RED && button823.getBackground() == Color.RED
								&& button833.getBackground() == Color.RED)
						|| (button811.getBackground() == Color.RED && button822.getBackground() == Color.RED
								&& button833.getBackground() == Color.RED)
						|| (button831.getBackground() == Color.RED && button822.getBackground() == Color.RED
								&& button813.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[7] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[7].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[7] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game8 if the board is completed
				if (smallWin == true) {
					gamePanels[7] = null;
				}
			}

			// Win conditions for game9
			if (panel9Found == true) {

				// Checks if player 1 won the small game
				if ((button911.getBackground() == Color.BLUE && button912.getBackground() == Color.BLUE
						&& button913.getBackground() == Color.BLUE)
						|| (button921.getBackground() == Color.BLUE && button922.getBackground() == Color.BLUE
								&& button923.getBackground() == Color.BLUE)
						|| (button931.getBackground() == Color.BLUE && button932.getBackground() == Color.BLUE
								&& button933.getBackground() == Color.BLUE)
						|| (button911.getBackground() == Color.BLUE && button921.getBackground() == Color.BLUE
								&& button931.getBackground() == Color.BLUE)
						|| (button912.getBackground() == Color.BLUE && button922.getBackground() == Color.BLUE
								&& button932.getBackground() == Color.BLUE)
						|| (button913.getBackground() == Color.BLUE && button923.getBackground() == Color.BLUE
								&& button933.getBackground() == Color.BLUE)
						|| (button911.getBackground() == Color.BLUE && button922.getBackground() == Color.BLUE
								&& button933.getBackground() == Color.BLUE)
						|| (button931.getBackground() == Color.BLUE && button922.getBackground() == Color.BLUE
								&& button913.getBackground() == Color.BLUE)) {
					JOptionPane.showMessageDialog(jfrm, playerOneName + " won this game");
					smallWin = true;
					boardWins[8] = 1;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.BLUE);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if player 2 won the small game
				else if ((button911.getBackground() == Color.RED && button912.getBackground() == Color.RED
						&& button913.getBackground() == Color.RED)
						|| (button921.getBackground() == Color.RED && button922.getBackground() == Color.RED
								&& button923.getBackground() == Color.RED)
						|| (button931.getBackground() == Color.RED && button932.getBackground() == Color.RED
								&& button933.getBackground() == Color.RED)
						|| (button911.getBackground() == Color.RED && button921.getBackground() == Color.RED
								&& button931.getBackground() == Color.RED)
						|| (button912.getBackground() == Color.RED && button922.getBackground() == Color.RED
								&& button932.getBackground() == Color.RED)
						|| (button913.getBackground() == Color.RED && button923.getBackground() == Color.RED
								&& button933.getBackground() == Color.RED)
						|| (button911.getBackground() == Color.RED && button922.getBackground() == Color.RED
								&& button933.getBackground() == Color.RED)
						|| (button931.getBackground() == Color.RED && button922.getBackground() == Color.RED
								&& button913.getBackground() == Color.RED)) {
					JOptionPane.showMessageDialog(jfrm, playerTwoName + " won this game");
					smallWin = true;
					boardWins[8] = 2;
					for (int i = 0; i < disablePanels.length; i++) {
						if (disablePanels[i] == false) {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setEnabled(true);
								}
							}
						} else {
							if (gamePanels[i] != null) {
								Component[] currentComponents = gamePanels[i].getComponents();
								for (int j = 0; j < currentComponents.length; j++) {
									currentComponents[j].setBackground(Color.RED);
									currentComponents[j].setEnabled(false);
								}
							}
						}
					}
				}

				// Checks if there was a tie
				else {
					int nullCount = 0;
					Component[] currentComponents = gamePanels[8].getComponents();
					for (int j = 0; j < currentComponents.length; j++) {
						if (currentComponents[j].getBackground() == Color.BLUE
								|| currentComponents[j].getBackground() == Color.RED) {
							nullCount++;
						}
					}
					if (nullCount == 9) {
						JOptionPane.showMessageDialog(jfrm, "This game is a tie");
						smallWin = true;
						boardWins[8] = 3;
						for (int i = 0; i < disablePanels.length; i++) {
							if (disablePanels[i] == false) {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents.length; j++) {
										currentComponents2[j].setEnabled(true);
									}
								}
							} else {
								if (gamePanels[i] != null) {
									Component[] currentComponents2 = gamePanels[i].getComponents();
									for (int j = 0; j < currentComponents2.length; j++) {
										currentComponents2[j].setBackground(Color.YELLOW);
										currentComponents2[j].setEnabled(false);
									}
								}
							}
						}
					}
				}

				// Disables game9 if the board is completed
				if (smallWin == true) {
					gamePanels[8] = null;
				}
			}
		}
		checkBigWin();
	}// End of checkSmallWin method

	/**
	 * Starts the game
	 */
	public void startGame() {
		moveCount = 0;
		JOptionPane.showMessageDialog(jfrm,
				"Welcome to Ultimate Tic-Tac-Toe!\nPlayer One is 'O' and Player Two is 'X'\nEnter your names to continue");
		playerOneName = JOptionPane.showInputDialog(jfrm, "Player One, Enter Your Name:", null);
		playerTwoName = JOptionPane.showInputDialog(jfrm, "Player Two, Enter Your Name:", null);
		playerOne.setText(playerOneName);
		playerTwo.setText(playerTwoName);
		Random rand = new Random();
		int startCondition = rand.nextInt(2);

		// Alternates colors of 'Now Playing' buttons to intuitively show whose turn it
		// is.
		if (startCondition == 0) {
			playerOneGo = true;
			playerOne.setEnabled(true);
			playerOne.setBackground(Color.BLUE);
			playerTwo.setEnabled(false);
			playerTwo.setBackground(null);
			JOptionPane.showMessageDialog(jfrm, playerOneName + " goes first");
		} else {
			playerOneGo = false;
			playerOne.setEnabled(false);
			playerOne.setBackground(null);
			playerTwo.setEnabled(true);
			playerTwo.setBackground(Color.RED);
			JOptionPane.showMessageDialog(jfrm, playerTwoName + " goes first");
		}
		undoColors.add(null);
	}// End of startGame method
}// End of class