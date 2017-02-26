package nssQuiz;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
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
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.BoxLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class RapidFire2 extends JFrame {
	final static int MAX_TIME=1200;
	final int WRONG_ANSWER_DEDUCTION=40;
	final int SKIP_DEDUCTION=20;
	JPanel panel_10;
	int qcount = 1;
	int teamPoints=0;
	String ans = null;
	private Connection con;
	int min,sec;
	RapidFire2 frame;
	JLabel skpBtn;
	ResultSet rs;
	int correctAnswers=0,wrongAnswer=0,questionSkiped=0;
	static JLabel timer = new JLabel("TIMER");
	JSplitPane splitPaneMain = new JSplitPane();

	JTextPane textPane;
	JLabel points_label;
	JPanel optApanel,optBpanel,optCpanel,optDpanel;
	Clip clockClip;
	JLabel optALabel, optBLabel, optCLabel, optDLabel;
	JLabel logoLabel = new JLabel("");
	JLabel correct_Answer_label ;
	 JPanel previousQustionPanel;
	 JLabel questionStatus[]=new JLabel[100];
	 JLabel wrong_Answer_Label;
	   JLabel questionSkippedLabel ;
	public static void main(String... args) {
		String temp;
		System.out.println("rames");
		do
		{
		temp=JOptionPane.showInputDialog("Enter Your/Team Name");} while(temp==null||temp.compareTo("")==0);
		new RapidFire2(temp).setVisible(true);
	}
	
	public RapidFire2(String PlayerName) {
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		frame=this;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\NSSPICS\\NssLogo.jpg"));
		panel_10 = new JPanel();
		setTitle("RAPID FIRE_NSS QUIZ");
		setAlwaysOnTop(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setBounds(0,0,getToolkit().getScreenSize().width,getToolkit().getScreenSize().height);
		JPanel panel = new JPanel();
		
		splitPaneMain.setOneTouchExpandable(true);

		getContentPane().add(splitPaneMain, BorderLayout.CENTER);
		splitPaneMain.setRightComponent(panel);
		panel.setLayout(new GridLayout(1, 1, 1, 1));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(106, 90, 205));
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
		
		
		logoLabel.setIcon(new ImageIcon("C:\\NSSPIC\\hideScreen.jpg"));
		logoLabel.setVisible(true);
		

		splitPaneQA.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneQA.setDividerLocation(200);
		JPanel panel_5 = new JPanel();
		splitPaneQA.setRightComponent(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GRAY);
		panel_5.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 5, 5));

		 optApanel = new JPanel();
		optApanel.setBackground(Color.ORANGE);
		panel_3.add(optApanel);
		optApanel.setLayout(new GridLayout(0, 1, 0, 0));

		optALabel = new JLabel("A");
		optALabel.addMouseListener(optClick);
		optALabel.setForeground(Color.WHITE);
		optALabel.setHorizontalAlignment(SwingConstants.CENTER);
		optALabel.setFont(new Font("Dialog", Font.BOLD, 29));
		optALabel.setBackground(Color.GRAY);
		optApanel.add(optALabel);

		 optBpanel = new JPanel();
		optBpanel.setBackground(Color.ORANGE);
		panel_3.add(optBpanel);
		optBpanel.setLayout(new GridLayout(0, 1, 0, 0));

		optBLabel = new JLabel("B");
		optBLabel.addMouseListener(optClick);
		optBLabel.setForeground(Color.WHITE);
		optBLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optBLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		optBpanel.add(optBLabel);

		 optCpanel = new JPanel();
		optCpanel.setBackground(Color.ORANGE);
		panel_3.add(optCpanel);
		optCpanel.setLayout(new GridLayout(0, 1, 0, 0));

		optCLabel = new JLabel("C");
		optCLabel.addMouseListener(optClick);
		optCLabel.setForeground(Color.WHITE);
		optCLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optCLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		optCpanel.add(optCLabel);

		 optDpanel = new JPanel();
		optDpanel.setBackground(Color.ORANGE);
		panel_3.add(optDpanel);
		optDpanel.setLayout(new GridLayout(0, 1, 0, 0));

		optDLabel = new JLabel("D");
		optDLabel.addMouseListener(optClick);
		optDLabel.setForeground(Color.WHITE);
		optDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		optDLabel.setFont(new Font("Dialog", Font.BOLD, 29));
		optDpanel.add(optDLabel);

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
								startTimer();
								getQuestion();
								optApanel.setVisible(true);
								optBpanel.setVisible(true);
								optCpanel.setVisible(true);
								optDpanel.setVisible(true);
								skpBtn.setVisible(true);
								}
							}
						});
						mnNewMenu.add(mntmNewMenuItem);
						
						JMenuItem mntmGameInstruction = new JMenuItem("Game Instruction");
						mntmGameInstruction.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								new RapidFireInstructions(frame).setVisible(true);
							}
						});
						mnNewMenu.add(mntmGameInstruction);
						
						JMenuItem mntmQuit = new JMenuItem("Quit");
						mntmQuit.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							if(	JOptionPane.showConfirmDialog(frame, "If u quit from here All the progress made by you will be lost")==JOptionPane.OK_OPTION)
								System.exit(0);
							}
						});
						mnNewMenu.add(mntmQuit);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(139, 0, 0));
		menuBar.add(panel_11);
		
		JLabel lblNss = new JLabel("Tech Quiz (Apptitute Test)");
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
		splitPaneMain.setDividerLocation(1800);
		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{1, 22, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.YELLOW);
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.insets = new Insets(0, 0, 5, 0);
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panel_1.add(panel_8, gbc_panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("POINTS");
		lblNewLabel_1.setFont(new Font("Tekton Pro Cond", Font.BOLD, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel_1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.ORANGE);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		panel_1.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel playerNameLabel = new JLabel("Team:"+ PlayerName);
		playerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(playerNameLabel);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		panel_1.add(panel_6, gbc_panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.GREEN);
		panel_6.add(panel_12);
		   GridBagLayout gbl_panel_12 = new GridBagLayout();
		   gbl_panel_12.columnWidths = new int[]{365, 0};
		   gbl_panel_12.rowHeights = new int[]{187, 0, 0, 0, 262, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		   gbl_panel_12.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		   gbl_panel_12.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		   panel_12.setLayout(gbl_panel_12);
		   
		    points_label = new JLabel("0");
		   points_label.setHorizontalTextPosition(SwingConstants.CENTER);
		   points_label.setHorizontalAlignment(SwingConstants.CENTER);
		   points_label.setFont(new Font("Stencil Std", Font.BOLD, 67));
		   GridBagConstraints gbc_points_label = new GridBagConstraints();
		   gbc_points_label.insets = new Insets(0, 0, 5, 0);
		   gbc_points_label.fill = GridBagConstraints.VERTICAL;
		   gbc_points_label.gridx = 0;
		   gbc_points_label.gridy = 0;
		   panel_12.add(points_label, gbc_points_label);
		   
		   correct_Answer_label = new JLabel("Correct Answers 0");
		   correct_Answer_label.setForeground(Color.BLUE);
		   correct_Answer_label.setFont(new Font("Snap ITC", Font.PLAIN, 24));
		   GridBagConstraints gbc_correct_Answer_label = new GridBagConstraints();
		   gbc_correct_Answer_label.insets = new Insets(0, 0, 5, 0);
		   gbc_correct_Answer_label.gridx = 0;
		   gbc_correct_Answer_label.gridy = 1;
		   panel_12.add(correct_Answer_label, gbc_correct_Answer_label);
		   
		    wrong_Answer_Label = new JLabel("Wrong Answers 0");
		    wrong_Answer_Label.setForeground(Color.RED);
		   wrong_Answer_Label.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		   GridBagConstraints gbc_wrong_Answer_Label = new GridBagConstraints();
		   gbc_wrong_Answer_Label.insets = new Insets(0, 0, 5, 0);
		   gbc_wrong_Answer_Label.gridx = 0;
		   gbc_wrong_Answer_Label.gridy = 2;
		   panel_12.add(wrong_Answer_Label, gbc_wrong_Answer_Label);
		   
		    questionSkippedLabel = new JLabel("Question Skiped 0");
		   questionSkippedLabel.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		   GridBagConstraints gbc_questionSkippedLabel = new GridBagConstraints();
		   gbc_questionSkippedLabel.insets = new Insets(0, 0, 5, 0);
		   gbc_questionSkippedLabel.gridx = 0;
		   gbc_questionSkippedLabel.gridy = 3;
		   panel_12.add(questionSkippedLabel, gbc_questionSkippedLabel);
		   
		   JScrollPane scrollPane = new JScrollPane();
		   GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		   gbc_scrollPane.gridheight = 10;
		   gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		   gbc_scrollPane.fill = GridBagConstraints.BOTH;
		   gbc_scrollPane.gridx = 0;
		   gbc_scrollPane.gridy = 4;
		   panel_12.add(scrollPane, gbc_scrollPane);
		   
		   previousQustionPanel = new JPanel();
		   previousQustionPanel.setBackground(Color.GREEN);
		   scrollPane.setViewportView(previousQustionPanel);
		   previousQustionPanel.setLayout(new BoxLayout(previousQustionPanel, BoxLayout.Y_AXIS));
		
		textPane.setEditable(false);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.BLUE);
		panel_10.add(panel_9, BorderLayout.SOUTH);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		 skpBtn = new JLabel("Skip Question");
		 skpBtn.setAlignmentY(5.0f);
		 skpBtn.setForeground(Color.WHITE);
		 skpBtn.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		 skpBtn.setAlignmentX(5.0f);
		skpBtn.setBackground(Color.BLUE);
		skpBtn.setOpaque(true);
		skpBtn.addMouseListener(optClick);
		panel_9.add(skpBtn);

		splitPaneMain.setDividerLocation(getToolkit().getScreenSize().width/2+300);
		splitPaneQA.setDividerLocation(getToolkit().getScreenSize().height/2+100);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getContentPane(), splitPaneMain, panel, panel_1, panel_2, splitPaneQA, panel_4, timer, panel_10, textPane, panel_5, panel_3, optApanel, optALabel, optBpanel, optBLabel, optCpanel, optCLabel, optDpanel, optDLabel, menuBar, mnNewMenu, mntmNewMenuItem, panel_11, lblNss, lblNewLabel}));
		
		optApanel.setVisible(false);
		optBpanel.setVisible(false);
		optCpanel.setVisible(false);
		optDpanel.setVisible(false);
		skpBtn.setVisible(false);
		skpBtn.setToolTipText("It will cost "+SKIP_DEDUCTION+"secs");

		getDBConnection();
	}

	Timer timeReal;

	void startTimer() {
		timeReal = new Timer();
		timeReal.scheduleAtFixedRate(timerTask, 1000, 1000);
	}
	

	public void getQuestion() {
		//textPane.setBounds(panel_10.getBounds());
		try {

			PreparedStatement ps = con.prepareStatement("select * from QUIZ_QUE  where SNO=?");
			ps.setInt(1, qcount);
			rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString(2));
				optALabel.setText("A: " + rs.getString(3));
				optBLabel.setText("B: " + rs.getString(4));
				optCLabel.setText("C: " + rs.getString(5));
				optDLabel.setText("D: " + rs.getString(6));
				textPane.setText(qcount + ": " + rs.getString(2));
				ans = rs.getString(7);
				// getQuestion();
			}
			else
			{
				JOptionPane.showMessageDialog( this, "No More Questions Available");
				this.setTitle("No More Questions Available");
			count=0;
			}
			// qcount=1;

			//con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	TimerTask timerTask = new TimerTask() {
		public void run() {
			 if (count > -1) {
					min=count/60;
					sec=count%60;
					if(sec<10)
					{
						timer.setText("Time Left:" + min+":0"+sec);
					}
					else timer.setText("Time Left:" + min+":"+sec);
				count--;
				if(count==9)
				{

					playClockSound();
				}
			}
			 else
			 {
					timer.setText("Time UP");
					optApanel.setVisible(false);
					optBpanel.setVisible(false);
					optCpanel.setVisible(false);
					optDpanel.setVisible(false);
					skpBtn.setVisible(false);
			 }
		}
	};
	static int count = MAX_TIME;

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
	void getDBConnection()
	{
		 
		    try {
		      Class.forName("org.sqlite.JDBC");
		      con = DriverManager.getConnection("jdbc:sqlite:"+"quiz_que");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		     try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    }
		    System.out.println("Opened database successfully");
	}
	void nextQueston()
	{
		qcount++;
		getQuestion();
	}
	MouseListener optClick=new MouseListener() {
		
		public void mouseReleased(MouseEvent e) {
			JLabel temp=((JLabel)e.getSource());
			switch (temp.getText().charAt(0))
			{
				case 'A':
					optApanel.setBackground(Color.red);
					checkAnswer('A');
					break;
				case 'B':
					optBpanel.setBackground(Color.red);
					checkAnswer('B');
					break;
				case 'C':
					optCpanel.setBackground(Color.red);
					checkAnswer('C');
					break;
				case 'D':
					optDpanel.setBackground(Color.red);
					checkAnswer('D');
					break;
				case 'S':
					skpBtn.setBackground(Color.red);
					questionStatus[qcount]=new JLabel(qcount+": Question Skipped");
					previousQustionPanel.add(questionStatus[qcount]);
					questionSkiped++;
					questionSkippedLabel.setText("Question Skiped   "+questionSkiped);
					count =count-SKIP_DEDUCTION;
					nextQueston();
					break;
				default:
					JOptionPane.showMessageDialog(frame, "wrong cchoice");	
			}
		}

		public void mousePressed(MouseEvent e) {
			
			JLabel temp=((JLabel)e.getSource());
			switch (temp.getText().charAt(0))
			{
				case 'A':
					optApanel.setBackground(Color.CYAN);
					break;
				case 'B':
					optBpanel.setBackground(Color.CYAN);
					break;
				case 'C':
					optCpanel.setBackground(Color.CYAN);
					break;
				case 'D':
					optDpanel.setBackground(Color.CYAN);
					break;
				case 'S':
					skpBtn.setBackground(Color.CYAN);
					break;
				default:
					JOptionPane.showMessageDialog(frame, "wrong cchoice");	
			}
		}
		
		public void mouseExited(MouseEvent e) {
			JLabel temp=((JLabel)e.getSource());
		switch (temp.getText().charAt(0))
		{
			case 'A':
				optApanel.setBackground(Color.orange);
				break;
			case 'B':
				optBpanel.setBackground(Color.orange);
				break;
			case 'C':
				optCpanel.setBackground(Color.orange);
				break;
			case 'D':
				optDpanel.setBackground(Color.orange);
				break;
			case 'S':
				skpBtn.setBackground(Color.blue);
				break;
			default:
				JOptionPane.showMessageDialog(frame, "wrong cchoice");	
		}
	}
		
		public void mouseEntered(MouseEvent e) {
			JLabel temp=((JLabel)e.getSource());
			System.out.println(temp.getText());
			switch (temp.getText().charAt(0))
		{
			case 'A':
				optApanel.setBackground(Color.magenta);
				break;
			case 'B':
				optBpanel.setBackground(Color.magenta);
				break;
			case 'C':
				optCpanel.setBackground(Color.magenta);
				break;
			case 'D':
				optDpanel.setBackground(Color.magenta);
				break;
			case 'S':
				skpBtn.setBackground(Color.red);
				break;
			default:
				JOptionPane.showMessageDialog(frame, "wrong choice");	
		}}
		
		public void mouseClicked(MouseEvent e) {((JLabel)e.getSource()).setBackground(Color.lightGray);}
	};
	void checkAnswer(char choice) {
		try {
			if(choice==ans.charAt(0))
			{
				questionStatus[qcount]=new JLabel(qcount+"-"+choice+": Correct Answer");
				questionStatus[qcount].setFont(new Font("Tahoma", Font.PLAIN, 18));
				questionStatus[qcount].setForeground(Color.BLUE);
				previousQustionPanel.add(questionStatus[qcount]);
				teamPoints+=10;
				points_label.setText(teamPoints+"");
				nextQueston();
				correctAnswers++;
				correct_Answer_label.setText("Correct Answers:   "+correctAnswers);
				
			}
			else
			{
				questionStatus[qcount]=new JLabel(qcount+"-"+choice+": Wrong Answer");
				questionStatus[qcount].setForeground(Color.red);
				previousQustionPanel.add(questionStatus[qcount]);
				count=count-WRONG_ANSWER_DEDUCTION;
				nextQueston();
				wrongAnswer++;
				wrong_Answer_Label.setText("Wrong Answers:    "+wrongAnswer);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
