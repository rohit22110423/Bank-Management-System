package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener {
    JPasswordField currentPinField, newPinField, confirmPinField;
    JButton changeBtn, backBtn;
    String currentPin;

    PinChange(String pin) {
        this.currentPin = pin;
        setTitle("Change PIN");
        setLayout(null);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(204, 255, 229));

        JLabel heading = new JLabel("Change Your PIN");
        heading.setFont(new Font("Raleway", Font.BOLD, 22));
        heading.setBounds(150, 40, 250, 30);
        add(heading);

        JLabel currentPinLabel = new JLabel("Current PIN:");
        currentPinLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        currentPinLabel.setBounds(80, 100, 150, 30);
        add(currentPinLabel);

        currentPinField = new JPasswordField();
        currentPinField.setFont(new Font("Raleway", Font.PLAIN, 16));
        currentPinField.setBounds(230, 100, 150, 30);
        add(currentPinField);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        newPinLabel.setBounds(80, 150, 150, 30);
        add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setFont(new Font("Raleway", Font.PLAIN, 16));
        newPinField.setBounds(230, 150, 150, 30);
        add(newPinField);

        JLabel confirmPinLabel = new JLabel("Confirm PIN:");
        confirmPinLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        confirmPinLabel.setBounds(80, 200, 150, 30);
        add(confirmPinLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setFont(new Font("Raleway", Font.PLAIN, 16));
        confirmPinField.setBounds(230, 200, 150, 30);
        add(confirmPinField);

        changeBtn = new JButton("Change");
        changeBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        changeBtn.setBackground(Color.BLACK);
        changeBtn.setForeground(Color.WHITE);
        changeBtn.setBounds(100, 270, 120, 30);
        changeBtn.addActionListener(this);
        add(changeBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(250, 270, 120, 30);
        backBtn.addActionListener(this);
        add(backBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changeBtn) {
            String currentEntered = new String(currentPinField.getPassword());
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (!currentEntered.equals(currentPin)) {
                JOptionPane.showMessageDialog(null, "Incorrect current PIN");
                return;
            }
            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(null, "New PIN entries do not match");
                return;
            }
            if (newPin.length() != 4 || !newPin.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "PIN must be 4 digits");
                return;
            }
            try {
                Con c = new Con();
                c.statement.executeUpdate("UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'");
                c.statement.executeUpdate("UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'");
                c.statement.executeUpdate("UPDATE signupthree SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'");

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Main_Class(newPin);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while changing PIN");
            }
        } else if (ae.getSource() == backBtn) {
            setVisible(false);
            new Main_Class(currentPin);
        }
    }

    public static void main(String[] args) {
        new PinChange("1234");
    }
}