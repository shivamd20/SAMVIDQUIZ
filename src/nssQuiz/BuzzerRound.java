package nssQuiz;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.KeyEvent;

class Team {

	int teamPoint = 0;
	JLabel teamName = new JLabel();
	JLabel teamPointLabel = new JLabel(teamPoint + "Points");
	JPanel teamRadioPanel = new JPanel();
	JRadioButton teamRadioButton[] = new JRadioButton[4];
	JButton button;
	ButtonGroup btngrp = new ButtonGroup();
	
	Team() {
		teamPointLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		for (int i = 0; i < 4; i++) {
			teamRadioPanel.setLayout(new GridLayout(1, 4));
			teamRadioButton[i] = new JRadioButton("");
			teamRadioPanel.add(teamRadioButton[i]);
			btngrp.add(teamRadioButton[i]);
		}
	}
}
@SuppressWarnings("serial")
public class BuzzerRound extends JFrame {
	
	JPanel panel_10;
	int qcount = 1;
	String value = null;
	private Connection con;
	ResultSet rs;
	static JLabel timer = new JLabel("TIMER");
	JPanel teamsPanel, radioPanel, pointsPanel;
	JSplitPane splitPaneMain = new JSplitPane();
	
	JTextPane textPane;

	Clip clockClip;
	
	JLabel optALabel, optBLabel, optCLabel, optDLabel;
	
	JToggleButton hideToggleBtm, pauseTimer;
	JButton checkAnswerBtn;
	JLabel logoLabel = new JLabel("");
	static Team team[];
	
	
	
	
	void setTeamInfoInFrame() {
		for (int i = 0; i < team.length; i++) {
			team[i] = new Team();
			team[i].teamName.setText("Team" + (i + 1));
			team[i].teamName.setHorizontalAlignment(JLabel.CENTER);
			team[i].teamName.setFont(new Font(value, Font.BOLD,25));
			team[i].teamName.setForeground(new Color((10-i)*20,i*14,i*12));
			team[i].teamRadioPanel.setBackground(Color.CYAN);
			for(int j=0;j<4;j++)
			team[i].teamRadioButton[j].setBackground(new Color(1,20*i+10*j,0));
			//team[i].teamRadioPanel.setBackground(Color.RED);
			team[i].teamPointLabel.setHorizontalAlignment(JLabel.CENTER);
			team[i].teamPointLabel.setFont(new Font(value, Font.ITALIC,25));
			team[i].teamPointLabel.setForeground(new Color(10*i,7*(i+10),i+15));
			team[i].teamPointLabel.setBackground(Color.white);
			pointsPanel.setForeground(Color.CYAN);
			
			radioPanel.add(team[i].teamRadioPanel);
			//teamsPanel.setForeground(Color.black);
			teamsPanel.add(team[i].teamName);
			pointsPanel.add(team[i].teamPointLabel);
			// team[i].teamName.setBounds(0, 0,teamsPanel.getWidth(),
			// teamsPanel.getHeight());
			// team[i].teamPointLabel.setBounds(0, 0,teamsPanel.getWidth(),
			// teamsPanel.getHeight());

		}
	}

	public static void main(String... args) {
		new BuzzerRound(10).setVisible(true);
	}
	
	public BuzzerRound(int n) {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FILE_CHOOSER_DIALOG);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\NSSPICS\\NssLogo.jpg"));
		panel_10 = new JPanel();
		setTitle("NSS QUIZ");
		setAlwaysOnTop(true);
		team = new Team[n];
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(0,0,getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		JPanel panel = new JPanel();

		
		splitPaneMain.setOneTouchExpandable(true);

		getContentPane().add(splitPaneMain, BorderLayout.CENTER);
		splitPaneMain.setRightComponent(panel);
		panel.setLayout(new GridLayout(1, 1, 1, 1));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(106, 90, 205));
		//panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));

		teamsPanel = new JPanel();
		teamsPanel.setBackground(new Color(0,255,0));
		panel_1.add(teamsPanel);
		teamsPanel.setLayout(new GridLayout(13, 0, 0, 0));

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(SystemColor.textHighlight);
		teamsPanel.add(panel_12);

		JLabel lblTeams = new JLabel("TEAMS");
		lblTeams.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTeams.setHorizontalAlignment(SwingConstants.CENTER);
		panel_12.add(lblTeams);

		hideToggleBtm = new JToggleButton("hideQuestion");
		hideToggleBtm.setMnemonic('h');
		hideToggleBtm.setIcon(new ImageIcon(BuzzerRound.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-error.png")));
		hideToggleBtm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hideToggleBtm.isSelected()) {
					optALabel.setVisible(false);
					optBLabel.setVisible(false);
					optCLabel.setVisible(false);
					optDLabel.setVisible(false);
					textPane.setVisible(false);
					panel_10.removeAll();
					logoLabel.setVisible(true);
					logoLabel.setHorizontalAlignment(JLabel.CENTER);
					panel_10.setBackground(Color.BLACK);
					panel_10.add(logoLabel, BorderLayout.CENTER);
				} else {
					panel_10.add(textPane, null);
					optALabel.setVisible(true);
					optBLabel.setVisible(true);
					optCLabel.setVisible(true);
					optDLabel.setVisible(true);
					logoLabel.setVisible(false);
					textPane.setVisible(true);
				}

			}
		});

		radioPanel = new JPanel();
		radioPanel.setBackground(new Color(127, 255, 0));
		panel_1.add(radioPanel);
		radioPanel.setLayout(new GridLayout(13, 0, 0, 0));

		JPanel panel_14 = new JPanel();
		panel_14.setBackground(Color.LIGHT_GRAY);
		radioPanel.add(panel_14);
		panel_14.setLayout(new GridLayout(1, 4, 0, 0));

		JLabel label_5 = new JLabel("A");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_14.add(label_5);

		JLabel label_6 = new JLabel("B");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_14.add(label_6);

		JLabel label_7 = new JLabel("C");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_14.add(label_7);

		JLabel label_8 = new JLabel("D");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_14.add(label_8);

		checkAnswerBtn = new JButton("Check Answer");
		checkAnswerBtn.setEnabled(false);
		checkAnswerBtn.setMnemonic('c');
		checkAnswerBtn.setIcon(new ImageIcon(BuzzerRound.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		checkAnswerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAnswer();
				checkAnswerBtn.setEnabled(false);
				pauseTimer.doClick();
			}
		});

		pointsPanel = new JPanel();
		pointsPanel.setBackground(new Color(154, 205, 50));
		panel_1.add(pointsPanel);
		pointsPanel.setLayout(new GridLayout(13, 0, 0, 0));

		JPanel panel_16 = new JPanel();
		panel_16.setBackground(SystemColor.inactiveCaption);
		pointsPanel.add(panel_16);

		JLabel label_9 = new JLabel("POINTS");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_16.add(label_9);

		final JButton skipButton = new JButton("Next Question");
		skipButton.setEnabled(false);
		skipButton.setMnemonic('n');
		skipButton.setIcon(new ImageIcon(BuzzerRound.class.getResource("/com/sun/javafx/scene/web/skin/IncreaseIndent_16x16_JFX.png")));
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int n;
				if (!checkAnswerBtn.isEnabled()) {

					optALabel.setForeground(Color.BLACK);

					optBLabel.setForeground(Color.black);

					optCLabel.setForeground(Color.black);

					optDLabel.setForeground(Color.black);
					checkAnswerBtn.setEnabled(true);
					qcount++;
					getQuestion();
					clearAllSelection();
					count = 30;
					pauseTimer.doClick();
				} else {
					JOptionPane.showMessageDialog(textPane,
							"Please press the checkAnswer Button before going to next answer");

				}
			}
		});
		JPanel panel_2 = new JPanel();
		splitPaneMain.setLeftComponent(panel_2);
		splitPaneMain.setDividerLocation(300);
		panel_2.setLayout(new BorderLayout(0, 1));

		JSplitPane splitPaneQA = new JSplitPane();
		splitPaneQA.setBackground(Color.LIGHT_GRAY);
		panel_2.add(splitPaneQA);
		splitPaneQA.setDividerLocation(1800);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.RED);
		splitPaneQA.setLeftComponent(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 0, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		GridBagConstraints gbc_lblTimer = new GridBagConstraints();
		gbc_lblTimer.insets = new Insets(0, 0, 5, 0);
		gbc_lblTimer.gridx = 0;
		gbc_lblTimer.gridy = 0;
		timer.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_4.add(timer, gbc_lblTimer);

		
		panel_10.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 1;
		panel_4.add(panel_10, gbc_panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));

		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setFont(new Font("Dialog", Font.PLAIN, 33));
		textPane.setBackground(Color.BLUE);
		//textPane.setBounds(panel_10.getBounds());
		panel_10.add(textPane);
		
		
		logoLabel.setIcon(new ImageIcon("C:\\NSSPICS\\hideScreen.jpg"));
		

		splitPaneQA.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneQA.setDividerLocation(200);
		JPanel panel_5 = new JPanel();
		splitPaneQA.setRightComponent(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_5.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 5, 5));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.ORANGE);
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		optALabel = new JLabel("A");
		optALabel.setForeground(Color.WHITE);
		optALabel.setHorizontalAlignment(SwingConstants.CENTER);
		optALabel.setFont(new Font("Dialog", Font.BOLD, 29));
		optALabel.setBackground(Color.GRAY);
		panel_6.add(optALabel);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.ORANGE);
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		textPane.setEditable(false);

		optBLabel = new JLabel("B");
		optBLabel.setForeground(Color.WHITE);
		optBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optBLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		panel_7.add(optBLabel);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.ORANGE);
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));

		optCLabel = new JLabel("C");
		optCLabel.setForeground(Color.WHITE);
		optCLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optCLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		panel_8.add(optCLabel);

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.ORANGE);
		panel_3.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));

		optDLabel = new JLabel("D");
		optDLabel.setForeground(Color.WHITE);
		optDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optDLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		panel_9.add(optDLabel);
		setTeamInfoInFrame();

		teamsPanel.add(hideToggleBtm);

		pauseTimer = new JToggleButton("Pause Timer");
		pauseTimer.setMnemonic('p');

		pauseTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pauseTimer.setIcon(new ImageIcon(BuzzerRound.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		teamsPanel.add(pauseTimer);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.textHighlight);
		setJMenuBar(menuBar);
		
				JMenu mnNewMenu = new JMenu("Quiz");
				mnNewMenu.setForeground(SystemColor.text);
				mnNewMenu.setBackground(SystemColor.textHighlight);
				menuBar.add(mnNewMenu);
				
						JMenuItem mntmNewMenuItem = new JMenuItem("Start");
						mntmNewMenuItem.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(qcount==1)
								{
									checkAnswerBtn.setEnabled(true);
									skipButton.setEnabled(true);
								startTimer();
								getQuestion();
								hideToggleBtm.doClick();
								
								}
							}
						});
						mnNewMenu.add(mntmNewMenuItem);
						
								JMenuItem mntmSetTeamNames = new JMenuItem("Set Team Names");
								mntmSetTeamNames.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										new teamInfo().frame.setVisible(true);
									}
								});
								mnNewMenu.add(mntmSetTeamNames);
								
								JMenuItem mntmQuit = new JMenuItem("Quit");
								mntmQuit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										System.exit(0);
									}
								});
								mnNewMenu.add(mntmQuit);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(139, 0, 0));
		menuBar.add(panel_11);
		
		JLabel lblNss = new JLabel("NSS SSEC");
		lblNss.setIcon(null);
		lblNss.setFont(new Font("Tempus Sans ITC", Font.BOLD, 28));
		
		lblNss.setHorizontalAlignment(SwingConstants.CENTER);
		lblNss.setBackground(SystemColor.desktop);
		lblNss.setForeground(Color.WHITE);
		menuBar.setBounds(0, 0, this.getWidth(), 40);
		panel_11.setLayout(new BorderLayout(0, 0));
		panel_11.add(lblNss);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\NSSPICS\\NssLogo.jpg"));
		panel_11.add(lblNewLabel, BorderLayout.WEST);
		
		radioPanel.add(checkAnswerBtn);

		JButton btnClearSelection = new JButton("Clear Selection");
		btnClearSelection.setMnemonic('C');
		btnClearSelection.setMnemonic(KeyEvent.VK_BACK_SPACE);
		btnClearSelection.setIcon(new ImageIcon(BuzzerRound.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Outdent-Black@2x.png")));
		btnClearSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllSelection();
			}
		});
		radioPanel.add(btnClearSelection);

		pointsPanel.add(skipButton);

		JButton btnResettimer = new JButton("RESET TIMER");
		btnResettimer.setMnemonic('r');
		btnResettimer.setIcon(new ImageIcon(BuzzerRound.class.getResource("/com/sun/javafx/scene/web/skin/Redo_16x16_JFX.png")));
		btnResettimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				count = 30;
				clockClip.stop();
			}
		});
		pointsPanel.add(btnResettimer);
		splitPaneMain.setDividerLocation(1800);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), splitPaneMain, panel, panel_1, teamsPanel, panel_12, lblTeams, hideToggleBtm, pauseTimer, radioPanel, panel_14, label_5, label_6, label_7, label_8, checkAnswerBtn, btnClearSelection, pointsPanel, panel_16, label_9, skipButton, btnResettimer, panel_2, splitPaneQA, panel_4, timer, panel_10, textPane, panel_5, panel_3, panel_6, optALabel, panel_7, optBLabel, panel_8, optCLabel, panel_9, optDLabel, menuBar, mnNewMenu, mntmNewMenuItem, mntmSetTeamNames, panel_11, lblNss, lblNewLabel}));
		hideToggleBtm.doClick();
		panel.add(panel_1);

		splitPaneMain.setDividerLocation(getToolkit().getScreenSize().width/2+300);
		splitPaneQA.setDividerLocation(getToolkit().getScreenSize().height/2+100);

	}

	Timer timeReal;

	void startTimer() {
		timeReal = new Timer();
		timeReal.scheduleAtFixedRate(timerTask, 1000, 1000);
	}
	

	public void getQuestion() {
		//textPane.setBounds(panel_10.getBounds());
		getDBConnection();
		try {

			PreparedStatement ps = con.prepareStatement("select * from myprojectdb.QUIZ_QUE  where SNO=?");
			ps.setInt(1, qcount);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString(2));
				optALabel.setText("A: " + rs.getString(3));
				optBLabel.setText("B: " + rs.getString(4));
				optCLabel.setText("C: " + rs.getString(5));
				optDLabel.setText("D: " + rs.getString(6));
				textPane.setText(qcount + ": " + rs.getString(2));
				value = rs.getString(7);
				// getQuestion();
			}

			else
				JOptionPane.showMessageDialog( this, "Data Not Found");
			// qcount=1;

			con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	TimerTask timerTask = new TimerTask() {
		public void run() {
			if (pauseTimer.isSelected()) {

			} else if (count > -1) {
				timer.setText("Time Left:" + count);
				count--;
				if(count==9)
				{
					//TODO
					playClockSound();
				}
			} else {
				count = 30;
				qcount++;
				getQuestion();
				clearAllSelection();
				checkAnswerBtn.setEnabled(true);
				optALabel.setForeground(Color.BLACK);

				optBLabel.setForeground(Color.black);

				optCLabel.setForeground(Color.black);

				optDLabel.setForeground(Color.black);

			}
		}
	};
	static int count = 30;

	public void getDBConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "ramu");
			// st=con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void updateTeamPointsInFrame() {
		for (Team x : team) {
			x.teamPointLabel.setText(x.teamPoint + "Points");
		}
	}
	
	void checkAnswer() {
		int answer = 99;
		try {
			switch (value.charAt(0)) {
			case 'A':
				optALabel.setForeground(Color.GREEN);
				answer = 0;
				break;
			case 'B':
				optBLabel.setForeground(Color.GREEN);
				answer = 1;
				break;
			case 'C':
				optCLabel.setForeground(Color.GREEN);
				answer = 2;
				break;
			case 'D':
				optDLabel.setForeground(Color.GREEN);
				answer = 3;
				break;
			}
			
			System.out.println(value.charAt(0) + "" + answer);
			for (Team x : team) {
				if (x.teamRadioButton[answer].isSelected()) {

					System.out.println("inside the true");
					playClapSound();
					x.teamPoint += 10;
				} else {
					for (int i = 0; i < 4; i++) {
						if (i != answer) {
							if (x.teamRadioButton[i].isSelected()) {
								
								x.teamPoint -= 5;
								playWrongAnsSound();
							}
						}
					}
				}
			}
			updateTeamPointsInFrame();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playClockSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\shiva\\Documents\\EGDownloads\\countdown.wav").getAbsoluteFile());
	        clockClip = AudioSystem.getClip();
	        clockClip.open(audioInputStream);
	        clockClip.start();
	        
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }
	}
	public void playWrongAnsSound() {
	    try {
	    	Toolkit toolkit=getToolkit();
	    	toolkit.beep();
	    	wait();
	    	toolkit.beep();
	    	toolkit.beep();
	    	toolkit.beep();
	    } catch(Exception ex) {
	        System.out.println("Error with playing clap sound.");
	        ex.printStackTrace();
	    }
	}
	public void playClapSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\shiva\\Documents\\EGDownloads\\Audience_Applause-Matthiew11-1206899159.wav").getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	    } catch(Exception ex) {
	        System.out.println("Error with playing clap sound.");
	        ex.printStackTrace();
	    }
	}
	void clearAllSelection() {
		for (int i = 0; i < team.length; i++) {
			team[i].btngrp.clearSelection();

		}
	}
	
}
