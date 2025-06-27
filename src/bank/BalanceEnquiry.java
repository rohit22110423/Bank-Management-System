package bank;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame {
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        setTitle("Balance Enquiry");
        setLayout(null);
        setSize(600, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(204, 255, 255));

        JLabel titleLabel = new JLabel("Balance Information");
        titleLabel.setFont(new Font("Raleway", Font.BOLD, 24));
        titleLabel.setBounds(180, 30, 300, 30);
        add(titleLabel);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Raleway", Font.PLAIN, 20));
        balanceLabel.setBounds(180, 100, 400, 30);
        add(balanceLabel);

        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(240, 200, 100, 30);
        add(backBtn);

        int balance = 0;
        try {
            Con c = new Con();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));
                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }
            balanceLabel.setText("Your Current Balance is Rs. " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        backBtn.addActionListener(e -> {
            setVisible(false);
            new Main_Class(pin);
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("1234");
    }
}
