package BuzzerRoundScoreManegement;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.awt.Rectangle;



public class MainScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainScreen frame=this;
	JPanel mainPanel = new JPanel();
	public static void main(String ...args)
	{
		new MainScreen().setVisible(true);
	}
	public MainScreen() {

		getContentPane().setBackground(Color.BLUE);
		mainPanel.setBackground(Color.BLUE);
		
	
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		btnHidePanel.setBounds(640, 13, 81, 23);
		mainPanel.add(btnHidePanel);
		btnHidePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideScorePane();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 0, 0);
		panel_1.setBackground(Color.GREEN);
		mainPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
	
	}
	JFrame hideScorePaneFrame=new JFrame();
	private final JButton btnHidePanel = new JButton("hide panel");
	void hideScorePane()
	{
		frame.setVisible(false);
		hideScorePaneFrame.setUndecorated(true);
		hideScorePaneFrame.setUndecorated(true);
		hideScorePaneFrame.setVisible(true);

		//hideScorePaneFrame.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
		hideScorePaneFrame.getContentPane().setLayout(new BorderLayout());
		JButton btn=new JButton("show Score Board");
		hideScorePaneFrame.getContentPane().add(btn);
		hideScorePaneFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		hideScorePaneFrame.setBounds(new Rectangle(10, 10));
		hideScorePaneFrame.setAlwaysOnTop(true);
		
		
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(true);
				hideScorePaneFrame.dispose();
				
			}
		});
	}

}
