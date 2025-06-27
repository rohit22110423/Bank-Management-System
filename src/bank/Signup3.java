package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;

    Signup3(String formno) {
        this.formno = formno;

        setTitle("New Account - Page 3: Account Details");
        setLayout(null);
        setSize(850, 750);
        setLocation(350, 10);
        getContentPane().setBackground(new Color(215, 252, 252));

        JLabel heading = new JLabel("Page 3: Account Details");
        heading.setFont(new Font("Raleway", Font.BOLD, 24));
        heading.setBounds(280, 40, 400, 40);
        add(heading);

        addAccountTypeOptions();
        addCardDetailsSection();
        addServicesSection();
        addButtons();

        setVisible(true);
    }

    private void addAccountTypeOptions() {
        JLabel label = new JLabel("Account Type:");
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(100, 100, 200, 30);
        add(label);

        r1 = createRadioButton("Saving Account", 100, 140);
        r2 = createRadioButton("Fixed Deposit Account", 350, 140);
        r3 = createRadioButton("Current Account", 100, 180);
        r4 = createRadioButton("Recurring Deposit Account", 350, 180);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(r1);
        accountGroup.add(r2);
        accountGroup.add(r3);
        accountGroup.add(r4);
    }

    private void addCardDetailsSection() {
        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        cardLabel.setBounds(100, 240, 200, 30);
        add(cardLabel);

        JLabel cardInfo = new JLabel("XXXX-XXXX-XXXX-1234");
        cardInfo.setFont(new Font("Raleway", Font.BOLD, 18));
        cardInfo.setBounds(330, 240, 300, 30);
        add(cardInfo);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        pinLabel.setBounds(100, 290, 200, 30);
        add(pinLabel);

        JLabel pinInfo = new JLabel("XXXX");
        pinInfo.setFont(new Font("Raleway", Font.BOLD, 18));
        pinInfo.setBounds(330, 290, 200, 30);
        add(pinInfo);
    }

    private void addServicesSection() {
        JLabel serviceLabel = new JLabel("Services Required:");
        serviceLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        serviceLabel.setBounds(100, 340, 200, 30);
        add(serviceLabel);

        c1 = createCheckbox("ATM CARD", 100, 380);
        c2 = createCheckbox("Internet Banking", 350, 380);
        c3 = createCheckbox("Mobile Banking", 100, 420);
        c4 = createCheckbox("Email Alerts", 350, 420);
        c5 = createCheckbox("Cheque Book", 100, 460);
        c6 = createCheckbox("e-Statement", 350, 460);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct.", true);
        c7.setFont(new Font("Raleway", Font.PLAIN, 12));
        c7.setBounds(100, 510, 500, 30);
        c7.setBackground(new Color(215, 252, 252));
        add(c7);
    }

    private void addButtons() {
        submit = new JButton("Submit");
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(250, 560, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(420, 560, 100, 30);
        cancel.addActionListener(this);
        add(cancel);
    }

    private JRadioButton createRadioButton(String label, int x, int y) {
        JRadioButton button = new JRadioButton(label);
        button.setFont(new Font("Raleway", Font.BOLD, 16));
        button.setBackground(new Color(215, 252, 252));
        button.setBounds(x, y, 250, 30);
        add(button);
        return button;
    }

    private JCheckBox createCheckbox(String label, int x, int y) {
        JCheckBox box = new JCheckBox(label);
        box.setFont(new Font("Raleway", Font.BOLD, 16));
        box.setBackground(new Color(215, 252, 252));
        box.setBounds(x, y, 250, 30);
        add(box);
        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String accType = null;
            if (r1.isSelected()) accType = "Saving Account";
            else if (r2.isSelected()) accType = "Fixed Deposit Account";
            else if (r3.isSelected()) accType = "Current Account";
            else if (r4.isSelected()) accType = "Recurring Deposit Account";

            StringBuilder facilities = new StringBuilder();
            if (c1.isSelected()) facilities.append("ATM CARD, ");
            if (c2.isSelected()) facilities.append("Internet Banking, ");
            if (c3.isSelected()) facilities.append("Mobile Banking, ");
            if (c4.isSelected()) facilities.append("Email Alerts, ");
            if (c5.isSelected()) facilities.append("Cheque Book, ");
            if (c6.isSelected()) facilities.append("e-Statement, ");

            if (accType == null || !c7.isSelected()) {
                JOptionPane.showMessageDialog(null, "Please select account type and agree to declaration.");
                return;
            }

            try {
                String cardno = "" + Math.abs((new java.util.Random().nextLong() % 90000000L) + 1409963000000000L);
                String pin = "" + Math.abs((new java.util.Random().nextLong() % 9000L) + 1000);

                Con c = new Con();
                String q1 = "INSERT INTO signupthree VALUES('" + formno + "', '" + accType + "', '" + cardno + "', '" + pin + "', '" + facilities + "')";
                String q2 = "INSERT INTO login VALUES('" + formno + "', '" + cardno + "', '" + pin + "')";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);

                JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\nPIN: " + pin);
                setVisible(false);
                new Deposit(pin);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancel) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Signup3("1234");
    }
}
