package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField cardField;
    JPasswordField pinField;
    JButton loginBtn, clearBtn, signupBtn;

    Login() {
        setTitle("Bank Login");
        setLayout(null);
        setSize(800, 480);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // Bank Logo
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image scaledIcon = icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel logo = new JLabel(new ImageIcon(scaledIcon));
        logo.setBounds(50, 30, 100, 100);
        add(logo);

        // Welcome Label
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 30));
        text.setBounds(200, 40, 400, 40);
        add(text);

        // Card No
        JLabel cardLabel = new JLabel("Card No:");
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        cardLabel.setBounds(120, 150, 150, 30);
        add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(250, 150, 250, 30);
        cardField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(cardField);

        // PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        pinLabel.setBounds(120, 200, 150, 30);
        add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(250, 200, 250, 30);
        pinField.setFont(new Font("Arial", Font.PLAIN, 14));
        add(pinField);

        // Buttons
        loginBtn = new JButton("Sign In");
        loginBtn.setBounds(250, 260, 100, 30);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        add(loginBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(400, 260, 100, 30);
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.addActionListener(this);
        add(clearBtn);

        signupBtn = new JButton("Sign Up");
        signupBtn.setBounds(250, 310, 250, 30);
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.addActionListener(this);
        add(signupBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clearBtn) {
            cardField.setText("");
            pinField.setText("");
        } else if (ae.getSource() == loginBtn) {
            String cardNo = cardField.getText();
            String pin = String.valueOf(pinField.getPassword());

            try {
                Con c = new Con();
                String query = "SELECT * FROM login WHERE card_number = '" + cardNo + "' AND pin_number = '" + pin + "'";
                ResultSet rs = c.statement.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Main_Class(pin);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == signupBtn) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}