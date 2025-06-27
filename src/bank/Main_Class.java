package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_Class extends JFrame implements ActionListener {
    JButton depositBtn, withdrawBtn, fastCashBtn, miniStatementBtn, pinChangeBtn, balanceEnquiryBtn, exitBtn;
    String pin;

    public Main_Class(String pin) {
        this.pin = pin;

        setTitle("ATM Services Dashboard");
        setLayout(null);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Welcome to ATM Services", JLabel.CENTER);
        title.setFont(new Font("System", Font.BOLD, 24));
        title.setBounds(200, 40, 400, 30);
        add(title);

        depositBtn = createButton("Deposit", 100, 120);
        withdrawBtn = createButton("Withdraw", 400, 120);
        fastCashBtn = createButton("Fast Cash", 100, 200);
        miniStatementBtn = createButton("Mini Statement", 400, 200);
        pinChangeBtn = createButton("Pin Change", 100, 280);
        balanceEnquiryBtn = createButton("Balance Enquiry", 400, 280);
        exitBtn = createButton("Exit", 250, 360);

        getContentPane().setBackground(new Color(200, 230, 255));
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("System", Font.BOLD, 16));
        button.setBounds(x, y, 250, 40);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == depositBtn) {
            setVisible(false);
            new Deposit(pin);
        } else if (ae.getSource() == withdrawBtn) {
            setVisible(false);
            new Withdrawal(pin);
        } else if (ae.getSource() == fastCashBtn) {
            setVisible(false);
            new FastCash(pin);
        } else if (ae.getSource() == miniStatementBtn) {
            new Mini(pin);
        } else if (ae.getSource() == pinChangeBtn) {
            setVisible(false);
            new PinChange(pin);
        } else if (ae.getSource() == balanceEnquiryBtn) {
            new BalanceEnquiry(pin);
        } else if (ae.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main_Class("1234");
    }
}
