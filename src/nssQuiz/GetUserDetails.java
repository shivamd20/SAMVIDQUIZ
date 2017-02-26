package nssQuiz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

class Person{
	String name,branch,college,set,
}
public class GetUserDetails extends JFrame{
	public GetUserDetails() {
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name");
		panel.add(lblName);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblBranch = new JLabel("Branch");
		panel.add(lblBranch);
		
		JComboBox comboBox = new JComboBox();
		panel.add(comboBox);
		
		JLabel lblSem = new JLabel("SEM");
		panel.add(lblSem);
		
		JComboBox comboBox_1 = new JComboBox();
		panel.add(comboBox_1);
		
		JLabel lblCollege = new JLabel("College");
		panel.add(lblCollege);
		
		JComboBox comboBox_2 = new JComboBox();
		panel.add(comboBox_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
	}

	private static final long serialVersionUID = 2404011542347307137L;
	private JTextField textField;

	public static void main(String[] args) {

	}

}
