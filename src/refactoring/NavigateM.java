package refactoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NavigateM {
	public JMenu navigateMenu;
	private JMenuItem nextItem, prevItem, firstItem, lastItem, findByAccount, findBySurname, listAll;
	private JButton firstItemButton, lastItemButton, nextItemButton, prevItemButton;
	private JTable jTable;

	public NavigateM() {

		initComponents();
	}

	public void initComponents() {

		navigateMenu = new JMenu("Navigate");

		nextItem = new JMenuItem("Next Item");
		prevItem = new JMenuItem("Previous Item");
		firstItem = new JMenuItem("First Item");
		lastItem = new JMenuItem("Last Item");
		findByAccount = new JMenuItem("Find by Account Number");
		findBySurname = new JMenuItem("Find by Surname");
		listAll = new JMenuItem("List All Records");

		nextItemButton = new JButton(new ImageIcon("next.png"));
		prevItemButton = new JButton(new ImageIcon("prev.png"));
		firstItemButton = new JButton(new ImageIcon("first.png"));
		lastItemButton = new JButton(new ImageIcon("last.png"));

		navigateMenu.add(nextItem);
		navigateMenu.add(prevItem);
		navigateMenu.add(firstItem);
		navigateMenu.add(lastItem);
		navigateMenu.add(findByAccount);
		navigateMenu.add(findBySurname);
		navigateMenu.add(listAll);

		findBySurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findSurname();
			}
		});

		findByAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findAccount();
			}
		});

		listAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list();
			}
		});

		firstItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});

		nextItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		lastItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});

		prevItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});

		nextItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});

		prevItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev();
			}
		});

		lastItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				last();
			}
		});

		firstItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				first();
			}
		});
	}

	public JMenu getNavigateMenu() {
		return navigateMenu;
	}

	public JButton getNextItemButton() {
		return nextItemButton;
	}

	public JButton getPrevItemButton() {
		return prevItemButton;
	}

	public JButton getFirstItemButton() {
		return firstItemButton;
	}

	public JButton getLastItemButton() {
		return lastItemButton;
	}

	public void next() {
		ArrayList<Integer> keyList = new ArrayList<Integer>();
		int i = 0;

		while (i < BankApplication.TABLE_SIZE) {
			i++;

			if (BankApplication.table.containsKey(i))
				keyList.add(i);
		}

		int maxKey = Collections.max(keyList);

		Util.saveOpenValues();

		if (BankApplication.currentItem < maxKey) {
			BankApplication.currentItem++;
			while (!BankApplication.table.containsKey(BankApplication.currentItem)) {
				BankApplication.currentItem++;
			}
		}
		Util.displayDetails(BankApplication.currentItem);
	}

	public void prev() {

		ArrayList<Integer> keyList = new ArrayList<Integer>();
		int i = 0;

		while (i < BankApplication.TABLE_SIZE) {
			i++;
			if (BankApplication.table.containsKey(i))
				keyList.add(i);
		}

		int minKey = Collections.min(keyList);

		if (BankApplication.currentItem > minKey) {
			BankApplication.currentItem--;
			while (!BankApplication.table.containsKey(BankApplication.currentItem)) {

				BankApplication.currentItem--;
			}
		}
		Util.displayDetails(BankApplication.currentItem);
	}

	public void first() {
		Util.saveOpenValues();

		BankApplication.currentItem = 0;
		while (!BankApplication.table.containsKey(BankApplication.currentItem)) {
			BankApplication.currentItem++;
		}
		Util.displayDetails(BankApplication.currentItem);

	}

	public void last() {
		Util.saveOpenValues();

		BankApplication.currentItem = BankApplication.TABLE_SIZE;

		while (!BankApplication.table.containsKey(BankApplication.currentItem)) {
			BankApplication.currentItem--;

		}

		Util.displayDetails(BankApplication.currentItem);
	}

	public void findSurname() {
		String surname = JOptionPane.showInputDialog("Search for surname: ");
		boolean found = false;

		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {

			if (surname.equalsIgnoreCase((entry.getValue().getSurname().trim()))) {
				found = true;
				BankApplication.accountIDTextField.setText(entry.getValue().getAccountID() + "");
				BankApplication.accountNumberTextField.setText(entry.getValue().getAccountNumber());
				BankApplication.surnameTextField.setText(entry.getValue().getSurname());
				BankApplication.firstNameTextField.setText(entry.getValue().getFirstName());
				BankApplication.accountTypeTextField.setText(entry.getValue().getAccountType());
				BankApplication.balanceTextField.setText(entry.getValue().getBalance() + "");
				BankApplication.overdraftTextField.setText(entry.getValue().getOverdraft() + "");
			}
		}
		if (found)
			JOptionPane.showMessageDialog(null, "Surname  " + surname + " found.");
		else
			JOptionPane.showMessageDialog(null, "Surname " + surname + " not found.");
	}

	public void findAccount() {
		String accNum = JOptionPane.showInputDialog("Search for account number: ");
		boolean found = false;

		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {

			if (accNum.equals(entry.getValue().getAccountNumber().trim())) {
				found = true;
				BankApplication.accountIDTextField.setText(entry.getValue().getAccountID() + "");
				BankApplication.accountNumberTextField.setText(entry.getValue().getAccountNumber());
				BankApplication.surnameTextField.setText(entry.getValue().getSurname());
				BankApplication.firstNameTextField.setText(entry.getValue().getFirstName());
				BankApplication.accountTypeTextField.setText(entry.getValue().getAccountType());
				BankApplication.balanceTextField.setText(entry.getValue().getBalance() + "");
				BankApplication.overdraftTextField.setText(entry.getValue().getOverdraft() + "");

			}
		}
		if (found)
			JOptionPane.showMessageDialog(null, "Account number " + accNum + " found.");
		else
			JOptionPane.showMessageDialog(null, "Account number " + accNum + " not found.");
	}

	public void list() {
		JFrame frame = new JFrame("TableDemo");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String col[] = { "ID", "Number", "Name", "Account Type", "Balance", "Overdraft" };

		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		jTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(jTable);
		jTable.setAutoCreateRowSorter(true);

		for (Map.Entry<Integer, BankAccount> entry : BankApplication.table.entrySet()) {

			Object[] objs = { entry.getValue().getAccountID(), entry.getValue().getAccountNumber(),
					entry.getValue().getFirstName().trim() + " " + entry.getValue().getSurname().trim(),
					entry.getValue().getAccountType(), entry.getValue().getBalance(), entry.getValue().getOverdraft() };

			tableModel.addRow(objs);
		}
		frame.setSize(600, 500);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

}
