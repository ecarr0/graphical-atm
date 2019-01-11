package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.BankAccount;
import model.User;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JPasswordField pinField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JComboBox<String> dayPicker;
	private JComboBox<String> monthPicker;
	private JComboBox<String> yearPicker;
	//	private UtilDateModel model;
//	private Properties p;
//	private JDatePanelImpl datePanel;
//	private JDatePickerImpl datePicker;
//	private JTextField dayField;
//	private JTextField monthField;
//	private JTextField yearField;
	private JTextField phoneField1;
	private JTextField phoneField2;
	private JTextField phoneField3;
	private JTextField addressField;
	private JTextField cityField;
	private JComboBox<String> stateField;
	private JTextField postalField;
	private JButton submitButton;
	private JButton returnButton;
	private BankAccount newAccount;
	private User newUser;
	private long newAccountNumber;
	private JFrame frame;
	private JLabel errorMessageLabel = new JLabel("");;		// label for potential error messages

	
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
		this.setLayout(null);
		//this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		initPinField();
		initFirstNameField();
		initLastNameField();
		initDOBField();
//		initDayField();
//		initMonthField();
//		initYearField();
		initPhoneField();
		initAddressField();
		initCityField();
		initStateField();
		initPostalField();
		initSubmitButton();
		initReturnButton();
		initErrorMessageLabel();
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	private void initPinField() {
//		//JLabel intro = new JLabel("Your account number is: " + Long.toString(newAccountNumber), SwingConstants.CENTER);
//		intro.setBounds(50, 0, 500, 20);
//		intro.setFont(new Font("DialogInput", Font.BOLD, 14));
//		this.add(intro);
		JLabel label = new JLabel("Pin Number: ", SwingConstants.RIGHT);
		label.setBounds(50,20,130,25);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 20, 200, 25);
		
		this.add(label);
		this.add(pinField);	
	}

	private void initFirstNameField() {
		JLabel label = new JLabel("First Name: ", SwingConstants.RIGHT);
		label.setBounds(50,50,130,25);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setBounds(205, 50, 200, 25);
		
		this.add(label);
		this.add(firstNameField);	
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name: ", SwingConstants.RIGHT);
		label.setBounds(50,80,130,25);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setBounds(205, 80, 200, 25);
		
		this.add(label);
		this.add(lastNameField);	
	}
	
	private void initDOBField() {
		JLabel label = new JLabel("DOB(MMDDYYYY)", SwingConstants.RIGHT);
		label.setBounds(50, 110, 130, 25);
		//label.setLabelFor(dobField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] months = new String[12];
		String[] days = new String[31];
		String[] years = new String[119];
		
		for(int i = 0; i < months.length; i++) {
			months[i] = Integer.toString(i+1);
			if(months[i].length() != 2) {
				String original = months[i];
				months[i] = "0" + original;
			}
		}
		for(int i = 0; i < days.length; i++) {
			days[i] = Integer.toString(i + 1);
			if(days[i].length() != 2) {
				String original = days[i];
				days[i] = "0" + original;
			}
		}
		int initYear = 1900;
		for(int i = 0; i < years.length; i++) {
			years[i] = Integer.toString(initYear);
			initYear++;
		}
		
		monthPicker = new JComboBox<String>(months);
		monthPicker.setBounds(205, 110, 50, 25);
		
		dayPicker = new JComboBox<String>(days);
		dayPicker.setBounds(260, 110, 50, 25);
		
		yearPicker = new JComboBox<String>(years);
		yearPicker.setBounds(315, 110, 75, 25);
		
//		dobField = new JTextField(8);
//		dobField.setBounds(205, 110, 200, 25);
//		
		this.add(label);
		this.add(monthPicker);
		this.add(dayPicker);
		this.add(yearPicker);
		
	}

	private void initPhoneField() {
		JLabel label = new JLabel("Phone Number: ", SwingConstants.RIGHT);
		label.setBounds(50,140,130,25);
		label.setLabelFor(phoneField1);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneField1 = new JTextField(3);
		phoneField1.setBounds(205, 140, 35, 25);
		
		phoneField2 = new JTextField(3);
		phoneField2.setBounds(245, 140, 35, 25);
		
		phoneField3 = new JTextField(4);
		phoneField3.setBounds(285, 140, 50, 25);
		
		this.add(label);
		this.add(phoneField1);	
		this.add(phoneField2);
		this.add(phoneField3);
	}
	
	private void initAddressField() {
		JLabel label = new JLabel("Street Address: ", SwingConstants.RIGHT);
		label.setBounds(50,170,130,25);
		label.setLabelFor(addressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		addressField = new JTextField(20);
		addressField.setBounds(205, 170, 200, 25);
		
		this.add(label);
		this.add(addressField);	
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City: ", SwingConstants.RIGHT);
		label.setBounds(50,200,130,25);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setBounds(205, 200, 200, 25);
		
		this.add(label);
		this.add(cityField);	
	}
	
	private void initStateField() {
		JLabel label = new JLabel("State: ", SwingConstants.RIGHT);
		label.setBounds(50,230,130,25);
		label.setLabelFor(stateField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] state = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		stateField = new JComboBox<String>(state);
		stateField.setBounds(205, 230, 200, 25);
		
		this.add(label);
		this.add(stateField);	
	}
	
	private void initPostalField() {
		JLabel label = new JLabel("Postal Code: ", SwingConstants.RIGHT);
		label.setBounds(50,260,130,25);
		label.setLabelFor(postalField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		postalField = new JTextField(20);
		postalField.setBounds(205, 260, 200, 25);
		
		this.add(label);
		this.add(postalField);	
	}
	
	private void initSubmitButton() {
		submitButton = new JButton("Create Account");
		submitButton.setBounds(205, 300, 200, 35);
		submitButton.addActionListener(this);

		this.add(submitButton);		
	}

	private void initReturnButton() {	
		returnButton = new JButton("Cancel");
		returnButton.setBounds(255, 340, 100, 25);
		returnButton.addActionListener(this);
		
		this.add(returnButton);
	}
	
	private void initErrorMessageLabel() {
		errorMessageLabel.setBounds(10, 350, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 14));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
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
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(submitButton)) {
			//check inputs
			boolean ok = true;
			if(!checkUserInput(pinField.getText(), 1) || !checkUserInput(phoneField1.getText(), 1) || !checkUserInput(phoneField2.getText(), 1) || !checkUserInput(phoneField3.getText(), 1) || !checkUserInput(postalField.getText(), 1)) {
				ok = false;
				updateErrorMessage("Invalid entry.");
			}
			if(pinField.getText().length() != 4 || phoneField1.getText().length() != 3 || phoneField2.getText().length() != 3 || phoneField3.getText().length() != 3 || postalField.getText().length() != 5) {
				ok = false;
				updateErrorMessage("One or more entries are too long or too short.");
			}
			
			if(ok) {
				
				newAccountNumber = manager.db.highestAcctNumber() + 1;
				JOptionPane.showMessageDialog(frame,"Your account number is: " + Long.toString(newAccountNumber));
				newUser = new User(Integer.valueOf(pinField.getText()), Integer.valueOf((String)yearPicker.getSelectedItem() + (String)monthPicker.getSelectedItem() + (String)dayPicker.getSelectedItem()), Long.valueOf(phoneField1.getText() + phoneField2.getText() + phoneField3.getText()), firstNameField.getText(), lastNameField.getText(), addressField.getText(), cityField.getText(), (String)stateField.getSelectedItem(), postalField.getText());
				System.out.println(newUser.toString());
				newAccount = new BankAccount('Y', newAccountNumber, 0.00, newUser);
				manager.db.insertAccount(newAccount);
				pinField.setText("");
				firstNameField.setText("");
				lastNameField.setText("");
				dayPicker.setSelectedIndex(0);
				monthPicker.setSelectedIndex(0);
				yearPicker.setSelectedIndex(0);
				phoneField1.setText("");
				phoneField2.setText("");
				phoneField3.setText("");
				addressField.setText("");
				cityField.setText("");
				stateField.setSelectedItem("AL");
				postalField.setText("");
				updateErrorMessage("");
				manager.switchTo(ATM.LOGIN_VIEW);
			}
		}
		else if(source.equals(returnButton)) {
			pinField.setText("");
			firstNameField.setText("");
			lastNameField.setText("");
			dayPicker.setSelectedIndex(0);
			monthPicker.setSelectedIndex(0);
			yearPicker.setSelectedIndex(0);
			phoneField1.setText("");
			phoneField2.setText("");
			phoneField3.setText("");
			addressField.setText("");
			cityField.setText("");
			stateField.setSelectedItem("AL");
			postalField.setText("");
			updateErrorMessage("");
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