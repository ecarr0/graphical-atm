package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JTextField pinField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField dayField;
	private JTextField monthField;
	private JTextField yearField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField postalField;
	private JButton submitButton;
	private JButton returnButton;
	private BankAccount newAccount;
	private User newUser;
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the CreateView.
		
		//this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		initPinField();
		initFirstNameField();
		initLastNameField();
		initDayField();
		initMonthField();
		initYearField();
		initPhoneField();
		initAddressField();
		initCityField();
		initStateField();
		initPostalField();
		initSubmitButton();
		initReturnButton();
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	private void initPinField() {
		JLabel label = new JLabel("Pin Number: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JTextField(20);
		pinField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(pinField);	
	}

	private void initFirstNameField() {
		JLabel label = new JLabel("First Name: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(firstNameField);	
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(lastNameField);	
	}
	
	private void initDayField() {
		JLabel label = new JLabel("Day of Birth ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(dayField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		dayField = new JTextField(20);
		dayField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(dayField);	
	}
	
	private void initMonthField() {
		JLabel label = new JLabel("Birth Month: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(monthField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		monthField = new JTextField(20);
		monthField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(monthField);	
	}
	
	private void initYearField() {
		JLabel label = new JLabel("Birth Year: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(yearField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		yearField = new JTextField(20);
		yearField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(yearField);	
	}
	
	private void initPhoneField() {
		JLabel label = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(phoneField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneField = new JTextField(20);
		phoneField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(phoneField);	
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Street Address: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(addressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		addressField = new JTextField(20);
		addressField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(addressField);	
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(cityField);	
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(stateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		stateField = new JTextField(20);
		stateField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(stateField);	
	}
	
	private void initPostalField() {
		JLabel label = new JLabel("Postal Code: ", SwingConstants.RIGHT);
		label.setBounds(100,100,95,35);
		label.setLabelFor(postalField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postalField = new JTextField(20);
		postalField.setBounds(205, 100, 200, 35);
		
		this.add(label);
		this.add(postalField);	
	}
	
	private void initSubmitButton() {
		submitButton = new JButton("Create Account");
		submitButton.setBounds(126, 360, 248, 35);
		submitButton.addActionListener(this);

		this.add(submitButton);		
	}

	private void initReturnButton() {	
		returnButton = new JButton("Log Off");
		returnButton.setBounds(205, 300, 200, 35);
		returnButton.addActionListener(this);
		
		this.add(returnButton);
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(submitButton)) {
			//int dob = 
			System.out.println(Long.toString(manager.db.highestAcctNumber()));
			newUser = new User(Integer.valueOf(pinField.getText()), 010101, Long.valueOf(pinField.getText()), firstNameField.getText(), lastNameField.getText(), addressField.getText(), cityField.getText(), stateField.getText(), postalField.getText());
			newAccount = new BankAccount('Y', manager.db.highestAcctNumber() + 1, 0.00, newUser);
			manager.db.insertAccount(newAccount);
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if(source.equals(returnButton)) {
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else {
			System.out.println("Error");
		}
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}