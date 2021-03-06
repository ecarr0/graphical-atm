package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;

@SuppressWarnings("serial")
public class TransferView extends JPanel implements ActionListener {
		
	private ViewManager manager;
	private JTextField accountField;// manages interactions between the views, model, and database
	private JTextField amountField;		// textfield where the user enters his or her account number
	private JButton transferButton;
	private JButton returnButton;// label for potential error messages
	private JLabel errorMessageLabel;		// label for potential error messages
	private BankAccount account;
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public TransferView(ViewManager manager) {
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
		
		initAccountField();
		initAmountField();
		initTransferButton();
		initReturnButton();
		initErrorMessageLabel();
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	
	private void initAccountField() {
		JLabel label = new JLabel("Account Number: ", SwingConstants.RIGHT);
		label.setBounds(0, 100, 150, 35);
		label.setLabelFor(accountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		accountField = new JTextField(20);
		accountField.setBounds(160, 100, 200, 35);
		
		this.add(label);
		this.add(accountField);
	}
	
	private void initAmountField() {
		JLabel label = new JLabel("Transfer Amount: ", SwingConstants.RIGHT);
		label.setBounds(0, 200, 150, 35);
		label.setLabelFor(amountField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		amountField = new JTextField(20);
		amountField.setBounds(160, 200, 200, 35);
		
		this.add(label);
		this.add(amountField);
	}
	
	/*
	 * Initializes the components needed for the PIN textfield.
	 */
	
	
	/*
	 * Initializes the components needed for the login button.
	 */
	
	private void initTransferButton() {	
		transferButton = new JButton("Transfer");
		transferButton.setBounds(126, 300, 248, 35);
		transferButton.addActionListener(this);
		
		this.add(transferButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(0, 250, 500, 35);
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
		
		if (source.equals(transferButton)) {
			int test = -1;
			if(accountField.getText() == "" || !checkUserInput(accountField.getText(), 3) || Long.valueOf(accountField.getText()) == account.getAccountNumber()) {
				test = 2;
				updateErrorMessage("Invalid account number.");

			}
			else if(amountField.getText() == "" || !checkUserInput(amountField.getText(), 2)) {
				test = 0;
				updateErrorMessage("Invalid amount.");

			}
			else{
				BankAccount transferAccount = manager.getAccount(Long.valueOf(accountField.getText()));
				if(transferAccount != null && transferAccount.getStatus() != 'N') {
					test = account.transfer(transferAccount, Double.valueOf(amountField.getText()));
					if(test == 3) {
						manager.updateAccount(account);
//						System.out.println("Balance before updating account in transfer account: " + transferAccount.toString());
						manager.updateAccount(transferAccount);
//						System.out.println("Balance after updating account in transfer account: " + manager.db.getAccount(Long.valueOf(accountField.getText())).toString());
						amountField.setText("");
						accountField.setText("");
						updateErrorMessage("Amount successfully transferred.");
						
					}
					else if(test == 2) {
						updateErrorMessage("Invalid account number.");
					}
					else if(test == 1) {
						updateErrorMessage("Insufficient funds.");
					}
					else if(test == 0) {
						updateErrorMessage("Invalid amount.");
						System.out.println("Failure.");
					}
					else {
						System.out.println("Error");
					}
				}
				else {
					updateErrorMessage("Invalid account number.");
				}
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
