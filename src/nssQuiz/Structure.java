package nssQuiz;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class Structure extends JFrame {

	
	static int pointsArray[]={0,0,0,0,0,0,0,0,0,0,0,0};
	int qcount=1;
	JTextPane textPane = new JTextPane();
	JLabel optA;
	JLabel optD;
	JLabel optB;
	JLabel optC;
	JButton btnSubmit = new JButton("Submit");
	static JLabel[] points=new JLabel[12];
	static JLabel []teams=new JLabel[12];
	private final ButtonGroup buttonGroup[] = new ButtonGroup[12];
	JRadioButton radiobuttons[][]=new JRadioButton[12][4];
	private Connection con;
	ResultSet rs;
	{
		for(int i=0;i<12;i++)
		{
				points[i]=new JLabel("");
				pointsArray[i]=0;
				points[i].setText("points:"+pointsArray[i]);
		}
	}
	void updatePoints()
	{
		for(int i=0;i<12;i++)
		{
			//pointsArray[i]=0;
			points[i].setText("points:"+pointsArray[i]);
			
		}
	}
	public static void main(String ...args)
	{
		new Structure().setVisible(true);
	}
	public Structure() {
	
		optC = new JLabel("C");
		optC.setFont(new Font("Dialog", Font.BOLD, 17));
		optB = new JLabel("B");
		optB.setFont(new Font("Dialog", Font.BOLD, 17));
		optA = new JLabel("A");
		optA.setFont(new Font("Dialog", Font.BOLD, 17));
		optD = new JLabel("D");
		optD.setFont(new Font("Dialog", Font.BOLD, 17));
		getDBConnection();
		getQuestion();
		setBackground(new Color(0, 128, 128));
		getContentPane().setLayout(new GridLayout(1, 2, 5, 5));
		this.setBounds(00,00,500,500);
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(2, 0, 5, 5));
		
		JPanel panel_25 = new JPanel();
		panel_25.setBackground(new Color(0, 0, 128));
		panel.add(panel_25);
		panel_25.setLayout(new GridLayout(0, 1, 0, 0));
		textPane.setFont(new Font("Dialog", Font.PLAIN, 33));
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.BLUE);
		//textPane.setText(rs.getString(2));
		panel_25.add(textPane);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 2, 5, 5));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(70, 130, 180));
		panel_2.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
	
		panel_5.add(optA);
		optA.setBackground(Color.GRAY);
		optA.setHorizontalAlignment(SwingConstants.CENTER);

		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(70, 130, 180));
		panel_2.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
	
		panel_8.add(optB);
		optB.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(70, 130, 180));
		panel_2.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		panel_9.add(optC);
		optC.setHorizontalAlignment(SwingConstants.CENTER);
	
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(70, 130, 180));
		panel_2.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		panel_10.add(optD);
		optD.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.menu);
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(15, 0, 0, 0));
		
		
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(SystemColor.textHighlight);
		panel_3.add(panel_23);
		
		JLabel lblpoints = new JLabel("teams");
		panel_23.add(lblpoints);
		lblpoints.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(15, 0, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.LIGHT_GRAY);
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 4, 0, 0));
		
		JLabel lblD = new JLabel("A");
		lblD.setFont(new Font("Dialog", Font.BOLD, 17));
		lblD.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblD);
		
		JLabel lblB = new JLabel("B");
		lblB.setFont(new Font("Dialog", Font.BOLD, 17));
		lblB.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblB);
		
		JLabel lblC = new JLabel("C");
		lblC.setFont(new Font("Dialog", Font.BOLD, 17));
		lblC.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblC);
		
		JLabel lblA = new JLabel("D");
		lblA.setFont(new Font("Dialog", Font.BOLD, 17));
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblA);
		
		
		
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(SystemColor.menu);
		panel_1.add(panel_11);
		panel_11.setLayout(new GridLayout(15, 0, 0, 0));
		
		JPanel panel_24 = new JPanel();
		panel_24.setBackground(SystemColor.inactiveCaption);
		panel_11.add(panel_24);
		
		JLabel lblPoints = new JLabel("POINTS");
		panel_24.add(lblPoints);
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		
		for(int i=0;i<12;i++)
					{
						JPanel panel_6 = new JPanel();
						panel_4.add(panel_6);
						panel_6.setLayout(new GridLayout(1, 4, 0, 0));
	
						buttonGroup[i]=new ButtonGroup();
						for(int j=0;j<4;j++)
						{
							radiobuttons[i][j]=new JRadioButton(i+" "+j+"");
							radiobuttons[i][j].setForeground(Color.magenta);
							radiobuttons[i][j].setBackground(Color.magenta);
							
							buttonGroup[i].add(radiobuttons[i][j]);
							
							panel_6.add(radiobuttons[i][j]);
			}
							panel_11.add(points[i]);
				teams[i]= new JLabel("team"+(i+1));
					teams[i].setHorizontalAlignment(SwingConstants.CENTER);
						panel_3.add(teams[i]);
		}
		JButton btnNewButton = new JButton("Next Question");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int c=5;
				for(int i=0;i<12;i++)
				{
					
					try {
						switch(rs.getString(7))
						{
						case "A":
							c=0;
							break;
						case "B":
							c=1;
							break;
						case "C":
							c=3;
						case "D":
							c=4;
						}
						//boolean result=false;
							if(c!=5)
							if(radiobuttons[i][c].isSelected())
							{
								pointsArray[i]+=10;
								updatePoints();
							}
						
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
			
				}
				clearAllRadio();
				btnSubmit.setEnabled(true);;
				qcount++;
				getQuestion();
			}
		});
		panel_11.add(btnNewButton);
		
		btnSubmit.addActionListener(submitLis);
		panel_4.add(btnSubmit);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnQuiz = new JMenu("Quiz");
		menuBar.add(mnQuiz);
		
		JMenuItem mntmSetTeamNames = new JMenuItem("Set Team Names");
		mntmSetTeamNames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new teamInfo().frame.setVisible(true);;
			}
		});
		mnQuiz.add(mntmSetTeamNames);
		/*for(int i=0;i<12;i++)
			for(int j=0;j<4;j++)
		radiobuttons[i][j].addActionListener(radioLis);*/
		
	
		

		}
	public void getQuestion()
	{
		getDBConnection();
		try
		{
			
			PreparedStatement ps=
					con.prepareStatement("select * from myprojectdb.QUIZ_QUE  where SNO=?");
			ps.setInt(1, qcount);				
			 rs=ps.executeQuery();
			if(rs.next())
			{
				System.out.println(rs.getString(2));
				optA.setText("A: "+rs.getString(3));
				optB.setText("B: "+rs.getString(4));
				optC.setText("C: "+rs.getString(5));
				optD.setText("D: "+rs.getString(6));
				textPane.setText(rs.getString(2));
				//getQuestion();
			}
			
			else
				JOptionPane.showMessageDialog(null, "Data Not Found");
				//qcount=1;
			
			
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
		}
	}

	public void getDBConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","ramu");
			//st=con.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean addPoints(int choice) throws SQLException
		{
		boolean result=false;
		
			String c;	
		
				switch(choice)
				{
				case 0:
					c="A";
					break;
				case 1:
					c="B";
					break;
				case 2:
					c="C";
				case 3:
					c="D";
				default:
					c="kuch ni";
				}
				System.out.println("c="+c+"   rs7=  "+rs.getString(7));
				
				if((""+rs.getString(7).charAt(0)).equals(c))
				{
					result=true;
				}
				else
				{
					System.out.println("ramu");
				}
				 updatePoints();
			return result;
		}
	
	ActionListener radioLis=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String str=e.getActionCommand();
			String str1[]=str.split(" ");
			for(String X:str1)
				System.out.print(X+"n");
			int j=Integer.parseInt(str1[1]);
		
			System.out.println(e.getActionCommand()+"j="+j);
			try {
				if(addPoints(j))
					{
						System.out.println("chikosolowakia ");
					};
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	};
	ActionListener submitLis=new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int c;
			for(int i=0;i<12;i++)
			{
				
				try {
					switch(rs.getString(7))
					{
					case "A":
						c=0;
						break;
					case "B":
						c=1;
						break;
					case "C":
						c=2;
					case "D":
						c=3;
						break;
					default: c=99;
					}
						
						if(radiobuttons[i][c].isSelected())
						{
							//pointsArray[i]+=10;
							points[i].setText("points:"+(pointsArray[i]+10));
						}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			btnSubmit.setEnabled(false);
		}
	};
void clearAllRadio()
{
	for(int i=0;i<12;i++)
			buttonGroup[i].setSelected(null, false);
	}
	
}
