package Mini_Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private Map<String, String> passwords = new HashMap<>();
    private JList<String> accountList;
    private DefaultListModel<String> listModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        final JFrame frame = new JFrame("Lock & Loaded");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        JOptionPane.showMessageDialog(frame, "Welcome to the Lock & Loaded Password Manager\n A Secure wallet for Your passwords \nMade By:\nHarshit Saini  22BCS15895\nSagar Saini    22BCS15934\nSection:809-A", "Lock & Loaded", JOptionPane.INFORMATION_MESSAGE);
        listModel = new DefaultListModel<>();
        accountList = new JList<>(listModel);
        frame.add(new JScrollPane(accountList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Password");
        addButton.addActionListener(new ActionListener() {  
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = JOptionPane.showInputDialog("Enter account:");
                if (account != null && !account.isEmpty()) {
                    String password = JOptionPane.showInputDialog("Enter password:");
                    if (password != null) {
                        passwords.put(account, password);
                        refreshAccountList();
                    }
                }
            }
        });

        JButton viewButton = new JButton("View Password");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAccount = accountList.getSelectedValue();
                if (selectedAccount != null) {
                    String password = passwords.get(selectedAccount);
                    JOptionPane.showMessageDialog(frame, "Password for " + selectedAccount + ": " + password);
                } else {
                    JOptionPane.showMessageDialog(frame, "No account selected. Please select an account to view its password.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton deleteButton = new JButton("Delete Account");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAccount = accountList.getSelectedValue();
                if (selectedAccount != null) {
                    passwords.remove(selectedAccount);
                    refreshAccountList();
                } else {
                    JOptionPane.showMessageDialog(frame, "No account selected. Please select an account to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void refreshAccountList() {
        listModel.removeAllElements();
        for (String account : passwords.keySet()) {
            listModel.addElement(account);
        }
    }
}

