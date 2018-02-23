package refactoring;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class BankApplication extends JFrame {

	ArrayList<BankAccount> accountList = new ArrayList<BankAccount>();
	static HashMap<Integer, BankAccount> table = new HashMap<Integer, BankAccount>();
	final static int TABLE_SIZE = 29;

	NavigateM navigate = new NavigateM();
	RecordM record = new RecordM();
	TransactionM transaction = new TransactionM();
	FileM file = new FileM();

	JMenuBar menuBar;
	JMenu exitMenu;
	JMenuItem closeApp;

	private JLabel accountIDLabel, accountNumberLabel, firstNameLabel, surnameLabel, accountTypeLabel, balanceLabel,
			overdraftLabel;
	static JTextField accountIDTextField;
	static JTextField accountNumberTextField;
	static JTextField firstNameTextField;
	static JTextField surnameTextField;
	static JTextField accountTypeTextField;
	static JTextField balanceTextField;
	static JTextField overdraftTextField;

	JFileChooser fc;
	JTable jTable;
	static double interestRate;

	static int currentItem = 0;

	static boolean openValues;

	public BankApplication() {

		super("Bank Application");

		initComponents();
	}

	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel displayPanel = new JPanel(new MigLayout());

		accountIDLabel = new JLabel("Account ID: ");
		accountIDTextField = new JTextField(15);
		accountIDTextField.setEditable(false);

		displayPanel.add(accountIDLabel, "growx, pushx");
		displayPanel.add(accountIDTextField, "growx, pushx, wrap");

		accountNumberLabel = new JLabel("Account Number: ");
		accountNumberTextField = new JTextField(15);
		accountNumberTextField.setEditable(false);

		displayPanel.add(accountNumberLabel, "growx, pushx");
		displayPanel.add(accountNumberTextField, "growx, pushx, wrap");

		surnameLabel = new JLabel("Last Name: ");
		surnameTextField = new JTextField(15);
		surnameTextField.setEditable(false);

		displayPanel.add(surnameLabel, "growx, pushx");
		displayPanel.add(surnameTextField, "growx, pushx, wrap");

		firstNameLabel = new JLabel("First Name: ");
		firstNameTextField = new JTextField(15);
		firstNameTextField.setEditable(false);

		displayPanel.add(firstNameLabel, "growx, pushx");
		displayPanel.add(firstNameTextField, "growx, pushx, wrap");

		accountTypeLabel = new JLabel("Account Type: ");
		accountTypeTextField = new JTextField(5);
		accountTypeTextField.setEditable(false);

		displayPanel.add(accountTypeLabel, "growx, pushx");
		displayPanel.add(accountTypeTextField, "growx, pushx, wrap");

		balanceLabel = new JLabel("Balance: ");
		balanceTextField = new JTextField(10);
		balanceTextField.setEditable(false);

		displayPanel.add(balanceLabel, "growx, pushx");
		displayPanel.add(balanceTextField, "growx, pushx, wrap");

		overdraftLabel = new JLabel("Overdraft: ");
		overdraftTextField = new JTextField(10);
		overdraftTextField.setEditable(false);

		displayPanel.add(overdraftLabel, "growx, pushx");
		displayPanel.add(overdraftTextField, "growx, pushx, wrap");

		add(displayPanel, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

		buttonPanel.add(navigate.getFirstItemButton());
		buttonPanel.add(navigate.getPrevItemButton());
		buttonPanel.add(navigate.getNextItemButton());
		buttonPanel.add(navigate.getLastItemButton());

		add(buttonPanel, BorderLayout.SOUTH);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuBar.add(navigate.getNavigateMenu());
		menuBar.add(record.getRecordsMenu());
		menuBar.add(file.getFileMenu());
		menuBar.add(transaction.getTransactionsMenu());

		exitMenu = new JMenu("Exit");

		closeApp = new JMenuItem("Close Application");

		exitMenu.add(closeApp);

		menuBar.add(exitMenu);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		closeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeApp();
			}
		});

	}

	private void closeApp() {
		int answer = JOptionPane.showConfirmDialog(BankApplication.this, "Do you want to save before quitting?");
		if (answer == JOptionPane.YES_OPTION) {
			file.saveFileAs();
			dispose();
		} else if (answer == JOptionPane.NO_OPTION) {
			dispose();
		}
	}

	public static void main(String[] args) {
		BankApplication ba = new BankApplication();
		ba.setSize(1200, 400);
		ba.pack();
		ba.setVisible(true);

	}
}