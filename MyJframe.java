import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class MyJframe extends JFrame {

	
	JLabel fnLabel;
	JLabel lnLabel;
	JLabel ageLabel;
	JLabel posLabel;
	
	JTextField fnfield;
	JTextField lnfield;
	JTextField agefield;
	
	JComboBox posBox;
	String[] pos = {"PG","SG","SF","PF","C"};
	
	JButton submitButton;
	
	MyJframe() throws SecurityException, IOException{
		this.setLayout(new GridBagLayout());
		GridBagConstraints cons  = new GridBagConstraints();
		cons.gridx = 0;
		cons.gridy = 0;
		
		fnLabel = new JLabel("First Name: ");
		this.add(fnLabel,cons);
		
		fnfield = new JTextField(15);
		cons.gridx = 1;
		add(fnfield,cons);
		
		lnLabel = new JLabel("Last Name: ");
		cons.gridx = 0;
		cons.gridy = 1;
		this.add(lnLabel,cons);
		
		lnfield = new JTextField(15);
		cons.gridx = 1;
		add(lnfield,cons);
		
		ageLabel  = new JLabel("Age: ");
		cons.gridx = 0;
		cons.gridy = 2;
		this.add(ageLabel,cons);
		
		
		agefield = new JTextField(15);
		cons.gridx = 1;
		cons.anchor = GridBagConstraints.WEST;
		add(agefield,cons);
		
		posLabel = new  JLabel("pos: ");
		cons.gridx = 0;
		cons.gridy = 3;
		this.add(posLabel,cons);
		
		posBox = new JComboBox(pos);
		cons.gridx = 1;
		add(posBox, cons);
		
		submitButton = new JButton("Submit");
		cons.gridx = 0;
		cons.gridy = 4;
		add(submitButton, cons);
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("button clicked");
		}
	}
	
	
}
