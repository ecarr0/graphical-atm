package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class WithdrawView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JTextField amountField;		// textfield where the user enters his or her account number
	private JButton withdrawButton;
	private JButton returnButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages
	private BankAccount account;
	
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public WithdrawView(ViewManager manager) {
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
		initWithdrawButton();
		initReturnButton();
		initErrorMessageLabel();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAmountField() {
		JLabel label = new JLabel("Withdraw Amount: ", SwingConstants.RIGHT);
		label.setBounds(0, 100, 150, 35);
		label.setLabelFor(amountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amountField = new JTextField(20);
		amountField.setBounds(160, 100, 200, 35);
		
		this.add(label);
		this.add(amountField);
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initWithdrawButton() {	
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(126, 200, 248, 35);
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 150, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	/*
	 * Initializes the components needed for the create button.
	 */
	
	private void initReturnButton() {
		returnButton = new JButton("Return to Previous Menu");
		returnButton.setBounds(126, 250, 248, 35);
		returnButton.addActionListener(this);
		
		//this.add(label);
		this.add(returnButton);		
	}
	
	/*
	 * Initializes the components needed for the power button.
	 */

	public boolean checkUserInput(String input, int type) {
		// 1 = integer, 2 = double, 3 = long
		if(type == 1) {
			int integerInput;
			try{
				integerInput = Integer.parseInt(input);
		    }
		    catch(NumberFormatException e){
		    	System.out.println("Response must be numerical. Try again.\n");
		    	return false;
		    }
			return true;
		}
		
		else if(type == 2){
			double doubleInput;
			try {
				doubleInput = Double.parseDouble(input);
			}
			catch (NumberFormatException e){
				System.out.println("Response must be numerical. Try again.\n");
				return false;
			}
			return true;
		}
		else {
			Long longInput;
			try {
				longInput = Long.parseLong(input);
			}
			catch (NumberFormatException e) {
				System.out.println("Response must be numerical. Try again.\n");
				return false;
			}
			return true;
		}
	}
	
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
		
		if (source.equals(withdrawButton)) {
			int checkResult = 0;
			if(amountField.getText() == "" || !checkUserInput(amountField.getText(), 2)) {
				checkResult = 0;
			}
			else{
				checkResult = account.withdraw(Double.valueOf(amountField.getText()));
			}
			
			if(checkResult == 3) {
				manager.updateAccount(account);
				updateErrorMessage("Amount successfully withdrawn.");
				amountField.setText("");
				System.out.println("Success.");
			}
			else if(checkResult == 0) {
				updateErrorMessage("Invalid amount.");
				System.out.println("Failure.");
			}
			else if(checkResult == 1) {
				updateErrorMessage("Insufficient Funds.");
				System.out.println("Failure.");
			}
			else {
				System.out.println("Error");
			}
		}
		else if(source.equals(returnButton)) {
			updateErrorMessage("");
			amountField.setText("");
			manager.sendBankAccount(account, "Home");
			manager.switchTo(ATM.HOME_VIEW);
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
