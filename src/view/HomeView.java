package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ViewManager;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JButton logoffButton;
	private JButton depositButton;
	private JButton withdrawButton;
	private JButton transferButton;
//	private JButton viewBalanceButton;
//	private JButton viewAcctButton;
//	private JButton editAcctButton;
//	private JButton closeAcctButton;
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		
		//this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
		this.setLayout(null);
		
		initLogOffButton();
		initDepositButton();
		initWithdrawButton();
		initTransferButton();
//		initViewBalanceButton();
//		initViewAcctButton();
//		initEditAcctButton();
//		initCloseAcctButton();
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	private void initLogOffButton() {	
		logoffButton = new JButton("Log Off");
		logoffButton.setBounds(150, 100, 100, 35);
		logoffButton.addActionListener(this);
		
		this.add(logoffButton);
	}
	
	private void initDepositButton() {	
		depositButton = new JButton("Deposit");
		depositButton.setBounds(150, 140, 100, 35);
		depositButton.addActionListener(this);
		
		this.add(depositButton);
	}
	
	private void initWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(150, 180, 100, 35);
		withdrawButton.addActionListener(this);
		
		this.add(withdrawButton);
	}
	
	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(150, 220, 100, 35);
		transferButton.addActionListener(this);
		
		this.add(transferButton);
	}
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source.equals(logoffButton)) {
			//manager.login(null, null); //????
			manager.account.setUser(null);
			manager.setAccount(null);
			//System.out.println(manager.account.toString());
			//manager.account = new BankAccount('\0', 0, 0, manager.account.getUser());
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if(source.equals(depositButton)) {
			manager.switchTo(ATM.DEPOSIT_VIEW);
		}
		else if(source.equals(withdrawButton)) {
			manager.switchTo(ATM.WITHDRAW_VIEW);
		}
		else if(source.equals(transferButton)) {
			manager.switchTo(ATM.TRANSFER_VIEW);
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
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