package refactoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RecordM {

	private JMenu recordsMenu;

	private JMenuItem createItem, modifyItem, deleteItem, setOverdraft, setInterest;
	static int currentItem = 1;

	public RecordM() {

		initComponents();
	}

	public void initComponents() {

		recordsMenu = new JMenu("Records");

		createItem = new JMenuItem("Create Item");
		modifyItem = new JMenuItem("Modify Item");
		deleteItem = new JMenuItem("Delete Item");
		setOverdraft = new JMenuItem("Set Overdraft");
		setInterest = new JMenuItem("Set Interest");

		recordsMenu.add(createItem);
		recordsMenu.add(modifyItem);
		recordsMenu.add(deleteItem);
		recordsMenu.add(setOverdraft);
		recordsMenu.add(setInterest);

		setOverdraft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				overdraftSetting();

			}
		});

		deleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteItem();
			}
		});

		createItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateBankDialog(BankApplication.table);
			}
		});

		modifyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyTheItem();
			}
		});

		setInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setInterestRate();
			}
		});

	}

	public JMenu getRecordsMenu() {
		return recordsMenu;
	}

	public void setInterestRate() {
		String interestRateStr = JOptionPane.showInputDialog("Enter Interest Rate: (do not type the % sign)");
		if (interestRateStr != null) {
			BankApplication.interestRate = Double.parseDouble(interestRateStr);
		}
	}

	public void overdraftSetting() {
		if (BankApplication.table.get(BankApplication.currentItem).getAccountType().trim().equals("Current")) {
			String newOverdraftStr = JOptionPane.showInputDialog(null, "Enter new Overdraft",
					JOptionPane.OK_CANCEL_OPTION);
			BankApplication.overdraftTextField.setText(newOverdraftStr);
			BankApplication.table.get(BankApplication.currentItem).setOverdraft(Double.parseDouble(newOverdraftStr));
		} else
			JOptionPane.showMessageDialog(null, "Overdraft only applies to Current Accounts");
	}

	public void deleteItem() {
		BankApplication.table.remove(currentItem);
		JOptionPane.showMessageDialog(null, "Account Deleted");

		currentItem = 0;
		while (!BankApplication.table.containsKey(currentItem)) {
			currentItem++;
		}
		Util.displayDetails(currentItem);

	}

	public void modifyTheItem() {
		BankApplication.surnameTextField.setEditable(true);
		BankApplication.firstNameTextField.setEditable(true);

		BankApplication.openValues = true;
	}

}