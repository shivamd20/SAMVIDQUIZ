package nssQuiz;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class teamInfo extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel name[] =new JLabel[10];
	JTextField text[] =new JTextField[10];
	JButton submit=new JButton("Submit");
	public JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					teamInfo window = new teamInfo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public teamInfo() {
		initialize();
	}


	private void initialize() {
		frame=this;
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 617, 483);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel name_panel = new JPanel();
		frame.getContentPane().add(name_panel);
		name_panel.setLayout(new GridLayout(14, 0, 0, 0));
		
		JPanel textpanel_1 = new JPanel();
		frame.getContentPane().add(textpanel_1);
		textpanel_1.setLayout(new GridLayout(14, 0, 0, 0));
		for(int i=0;i<BuzzerRound.team.length;i++)
		{
			text[i]=new JTextField();
			name[i]=new JLabel("team"+(i+1)); 
			name[i].setHorizontalAlignment(JLabel.TRAILING);
			textpanel_1.add(text[i]);
			name_panel.add(name[i]);
			frame.setAlwaysOnTop(true);
		}
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<BuzzerRound.team.length;i++)
				{
					BuzzerRound.team[i].teamName.setText(text[i].getText());
				}
			}
		});
		textpanel_1.add(submit);
	}

}
