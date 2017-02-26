package nssQuiz;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.InputMethodListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;

public class AdminPanel extends JFrame implements  ActionListener{
	private JTextField maxTimeText;
	private JTextField noofsetText;
	private JTextField wrongtxt;
	private JTextField skiptxt;
	JComboBox <String>setcomboBox = new<String> JComboBox();
	
	/*	 static int MAX_TIME=1200;
	  static int WRONG_ANSWER_DEDUCTION=40;
	 static int  SKIP_DEDUCTION=20;
	 static int NO_OF_SET=3;
	 static int set; */
	
	AdminPanel ap=this;
	public AdminPanel() {
		
		this.setSize(500,500);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("MAX TIME");
		getContentPane().add(lblNewLabel);
		
		maxTimeText = new JTextField();
		getContentPane().add(maxTimeText);
		maxTimeText.setColumns(10);
		
		JLabel lblNoofset = new JLabel("NO_OF_SET");
		getContentPane().add(lblNoofset);
		
		noofsetText = new JTextField();
		
		noofsetText.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				int max;
				try{
					
					max=Integer.parseInt(noofsetText.getText());
					setcomboBox.removeAllItems();
					
					for(int i=1;i<=max;i++)
					{
						setcomboBox.addItem(i+"");
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(ap, "invalid input");
					return;
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		noofsetText.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent event) {
				
				JOptionPane.showMessageDialog(ap, "ramu");
			}
			public void inputMethodTextChanged(InputMethodEvent event) {			
			}
		});
		noofsetText.setColumns(10);
		getContentPane().add(noofsetText);
		
		JLabel lblSet = new JLabel("SET");
		getContentPane().add(lblSet);
		
		getContentPane().add(setcomboBox);
		
		JLabel lblWrongAnswerDeduction = new JLabel("WRONG ANSWER DEDUCTION (secs)");
		getContentPane().add(lblWrongAnswerDeduction);
		
		wrongtxt = new JTextField();
		wrongtxt.setColumns(10);
		getContentPane().add(wrongtxt);
		
		JLabel lblSkipdeduction = new JLabel("SKIP DEDUCTION(secs)");
		getContentPane().add(lblSkipdeduction);
		
		skiptxt = new JTextField();
		skiptxt.setColumns(10);
		getContentPane().add(skiptxt);
		
		JButton doneTxt = new JButton("submit");
		doneTxt.addActionListener(this);
		getContentPane().add(doneTxt);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AdminPanel().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try{
		RapidFire2.MAX_TIME=Integer.parseInt(maxTimeText.getText());

		RapidFire2.SKIP_DEDUCTION=Integer.parseInt(skiptxt.getText());

		RapidFire2.set=Integer.parseInt((String)setcomboBox.getSelectedItem());

		RapidFire2.NO_OF_SET=Integer.parseInt(noofsetText.getText());
		
		RapidFire2.WRONG_ANSWER_DEDUCTION=Integer.parseInt(wrongtxt.getText());
		}
		catch(NumberFormatException mfe)
		{
			JOptionPane.showInternalMessageDialog(ap, "enter numeric value only");
			return;
		}
		
		ap.setVisible(false);
		
		GetUserDetails gud=new GetUserDetails();
		
		gud.setAlwaysOnTop(true);
		
		gud.setVisible(true);
		
		ap.dispose();
		
		
	}


}
