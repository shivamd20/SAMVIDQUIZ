package nssQuiz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

class Person{
	String name="",branch="",college="",phNo="",sem="";
}
public class GetUserDetails extends JFrame implements ActionListener{
	
	Person person=new Person();
	
	public GetUserDetails() {
		
		this.setSize(565,445);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Fill Your Details");
		panel_1.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		lblName.setBackground(SystemColor.activeCaption);
		lblName.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblName);
		
		nameTxt = new JTextField();
		panel.add(nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblBranch = new JLabel("Branch");
		lblBranch.setBackground(SystemColor.activeCaption);
		lblBranch.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblBranch.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblBranch);
		
		panel.add(branchComboBox);
		
		JLabel lblSem = new JLabel("SEM");
		lblSem.setBackground(SystemColor.activeCaption);
		lblSem.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblSem.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSem);
		
		panel.add(semComboBox);
		
		JLabel lblCollege = new JLabel("College");
		lblCollege.setBackground(SystemColor.activeCaption);
		lblCollege.setFont(new Font("Dialog", Font.PLAIN, 19));
		lblCollege.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCollege);
		
		panel.add(clgComboBox);
		
		JLabel lblPhno = new JLabel("PhoneNo");
		lblPhno.setBackground(SystemColor.activeCaption);
		lblPhno.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhno.setFont(new Font("Dialog", Font.PLAIN, 19));
		panel.add(lblPhno);
		
		PhoneNo = new JTextField();
		panel.add(PhoneNo);
		PhoneNo.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton submitButton = new JButton("submit");
		panel_2.add(submitButton);
		
		submitButton.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private static final long serialVersionUID = 2404011542347307137L;
	private JTextField nameTxt;
	private JTextField PhoneNo;
	private JTextField branchComboBox = new JTextField();
	private JTextField clgComboBox =  new JTextField();
	private JTextField semComboBox =  new JTextField();
	
	public static void main(String[] args) {
		String set=JOptionPane.showInputDialog("enter set no");
		
		Person person=new Person();
		GetUserDetails gud= new GetUserDetails();
		gud.setVisible(true);
	}

	GetUserDetails gud=this;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		person.name=nameTxt.getText();
		person.branch=branchComboBox.getText();
		person.college=clgComboBox.getText();
		person.phNo=PhoneNo.getText();
		person.sem=semComboBox.getText();
		
		RapidFire2 rp2=new RapidFire2();
		
		gud.setVisible(false);
		
		rp2.setPersonDetails(person);
		

		rp2.setVisible(true);
		
		gud.dispose();
	}

}
