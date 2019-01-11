package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class DepositView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private BankAccount account;
	private JTextField amountField;		// textfield where the user enters his or her account number
	private JButton depositButton;
	private JButton returnButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages
	private JFrame frame;
	
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public DepositView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	public void setBankAccount(BankAccount setAccount) {
		this.account = setAccount;
	}
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Updates the error message label.
	 * 
	 * @param errorMessage
	 */
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the LoginView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
		initAmountField();
		initDepositButton();
		initReturnButton();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAmountField() {
		JLabel label = new JLabel("Deposit Amount: ", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(amountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amountField = new JTextField(20);
		amountField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(amountField);
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initDepositButton() {	
		depositButton = new JButton("Deposit");
		depositButton.setBounds(205, 180, 200, 35);
		depositButton.addActionListener(this);
		
		this.add(depositButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 240, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * Initializes the components needed for the create button.
	 */
	
	private void initReturnButton() {
		returnButton = new JButton("Return to Previous Menu");
		returnButton.setBounds(126, 360, 248, 35);
		returnButton.addActionListener(this);
		
		//this.add(label);
		this.add(returnButton);		
	}
	
	/*
	 * Initializes the components needed for the power button.
	 */

	
	/*
	 * LoginView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The LoginView class is not serializable.");
	}

	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks performed in the LoginView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(depositButton)) {
			int checkResult = account.deposit(Double.valueOf(amountField.getText()));
			if(checkResult == 0) {
				JOptionPane.showMessageDialog(frame, "Invalid amount.");
				amountField.setText("");
			}
			else if(checkResult == 3) {
				JOptionPane.showMessageDialog(frame, "Amount deposited");
				manager.db.updateAccount(account);
				manager.sendBankAccount(account, "Home");
				manager.switchTo(ATM.HOME_VIEW);
			}
			
//			if(manager.account.deposit(Double.valueOf(amountField.getText())) == 3) {
//				JOptionPane.showMessageDialog(null, "Amount successfully deposited.");
//				System.out.println("Success.");
//			}
//			else if(manager.account.deposit(Double.valueOf(amountField.getText())) == 0) {
//				JOptionPane.showMessageDialog(null, "Invalid amount.");
//				System.out.println("Failure.");
//			}
//			else {
//				System.out.println("Error");
//			}
		}
		else if(source.equals(returnButton)) {
			manager.switchTo(ATM.HOME_VIEW);
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
