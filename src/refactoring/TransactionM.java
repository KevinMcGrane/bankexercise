package refactoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.*;

public class TransactionM {

	private JMenu transactionsMenu;
	private JMenuItem deposit, withdraw, calcInterest;

	public TransactionM() {
		initComponents();
	}

	public void initComponents() {
		transactionsMenu = new JMenu("Transactions");
		deposit = new JMenuItem("Deposit");
		withdraw = new JMenuItem("Withdraw");
		calcInterest = new JMenuItem("Calculate Interest");
		transactionsMenu.add(deposit);
		transactionsMenu.add(withdraw);
		transactionsMenu.add(calcInterest);

		deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountDeposit();
			}
		});

		withdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accountWithdraw();
			}
		});

		calcInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateInterest();
			}
		});

	}

	public JMenu getTransactionsMenu() {
		return transactionsMenu;
	}

	public void accountDeposit() {
		String accNum = JOptionPane.showInputDialog("Account number to deposit into: ");
		boolean found = false;

		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {
			if (accNum.equals(entry.getValue().getAccountNumber().trim())) {
				found = true;
				String toDeposit = JOptionPane.showInputDialog("Account found, Enter Amount to Deposit: ");
				entry.getValue().setBalance(entry.getValue().getBalance() + Double.parseDouble(toDeposit));
				Util.displayDetails(entry.getKey());

			}
		}
		if (!found)
			JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
	}

	public void accountWithdraw() {
		String accNum = JOptionPane.showInputDialog("Account number to withdraw from: ");
		String toWithdraw = JOptionPane.showInputDialog("Account found, Enter Amount to Withdraw: ");

		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {

			if (accNum.equals(entry.getValue().getAccountNumber().trim())) {

				if (entry.getValue().getAccountType().trim().equals("Current")) {
					if (Double.parseDouble(toWithdraw) > entry.getValue().getBalance()
							+ entry.getValue().getOverdraft())
						JOptionPane.showMessageDialog(null, "Transaction exceeds overdraft limit");
					else {
						entry.getValue().setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
						Util.displayDetails(entry.getKey());
					}
				} else if (entry.getValue().getAccountType().trim().equals("Deposit")) {
					if (Double.parseDouble(toWithdraw) <= entry.getValue().getBalance()) {
						entry.getValue().setBalance(entry.getValue().getBalance() - Double.parseDouble(toWithdraw));
						Util.displayDetails(entry.getKey());
					} else
						JOptionPane.showMessageDialog(null, "Insufficient funds.");
				}
			}
		}
	}

	public void calculateInterest() {
		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {
			if (entry.getValue().getAccountType().equals("Deposit")) {
				double equation = 1 + ((BankApplication.interestRate) / 100);
				entry.getValue().setBalance(entry.getValue().getBalance() * equation);
				JOptionPane.showMessageDialog(null, "Balances Updated");
				Util.displayDetails(entry.getKey());
			}
		}
	}
}