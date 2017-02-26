package nssQuiz;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.JButton;

 class RapidFireInstructions extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String ...args)
	{
		new RapidFireInstructions(new RapidFire2("")).setVisible(true);
	}

	public RapidFireInstructions(RapidFire2 RP2) {
		
		RP2.setEnabled(false);
		setTitle("RapidFire Instruction");
		setAlwaysOnTop(true);
		setBounds(getToolkit().getScreenSize().width/2-250,getToolkit().getScreenSize().height/2-250,500,500);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.QUESTION_DIALOG);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.BLUE);
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Instructions");
		lblNewLabel.setFont(new Font("Stencil Std", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea textPane = new JTextArea();
		textPane.setBackground(Color.YELLOW);
		textPane.setAlignmentY(CENTER_ALIGNMENT);
		textPane.setEditable(false);
		textPane.setWrapStyleWord(true);
		textPane.setLineWrap(true);
		textPane.setDropMode(DropMode.INSERT);
		textPane.setFont(new Font("Ravie", Font.PLAIN, 20));
		textPane.setText("\r\n1. You have 20 mins(1200 secs) to answer the questions\r\n\r\n\r\n2. each Wrong Answer will Cost you\r\n 40 secs\r\n\r\n\r\n3. each skip will cost you 20 secs");
		
		panel_1.add(textPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GREEN);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel("Created By:");
		lblNewLabel_5.setForeground(SystemColor.desktop);
		lblNewLabel_5.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Shivam Dwivedi");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(SystemColor.desktop);
		lblNewLabel_4.setFont(new Font("Snap ITC", Font.PLAIN, 18));
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CSE ");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_3);
		
		JButton lblNewLabel_1 = new JButton("DONE");
		lblNewLabel_1.setBackground(Color.GREEN);
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("Vth Sem");
		lblNewLabel_6.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setForeground(Color.BLUE);
		panel_2.add(lblNewLabel_6);
	}
	
}
