package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    private final String pin;
    private JTextField amountField;
    private JButton depositButton, backButton;

    public Deposit(String pin) {
        this.pin = pin;

        setTitle("Deposit Amount");
        setLayout(null);
        setSize(850, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        JLabel background = new JLabel(scaleImage(icon));
        background.setBounds(0, 0, 850, 800);
        add(background);

        JLabel text = new JLabel("Enter the amount to deposit:");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(360, 200, 400, 30);
        background.add(text);

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 16));
        amountField.setBounds(360, 240, 200, 30);
        background.add(amountField);

        depositButton = new JButton("Deposit");
        depositButton.setBounds(400, 300, 100, 30);
        depositButton.setBackground(new Color(65, 125, 128));
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(this);
        background.add(depositButton);

        backButton = new JButton("Back");
        backButton.setBounds(400, 340, 100, 30);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        background.add(backButton);

        setVisible(true);
    }

    private ImageIcon scaleImage(ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        Image scaled = img.getScaledInstance(850, 800, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == depositButton) {
            String amount = amountField.getText().trim();

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an amount to deposit.");
                return;
            }

            try {
                double parsedAmount = Double.parseDouble(amount);
                if (parsedAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid positive amount.");
                    return;
                }

                Date date = new Date();
                Con c = new Con();
                String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " deposited successfully.");

                // Move to main menu
                setVisible(false);
                try {
                    Main_Class mainScreen = new Main_Class(pin);
                    mainScreen.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error loading main menu.");
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please enter a numeric amount.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error. Try again.");
            }

        } else if (e.getSource() == backButton) {
            setVisible(false);
            try {
                Main_Class mainScreen = new Main_Class(pin);
                mainScreen.setVisible(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error returning to main menu.");
            }
        }
    }

    public static void main(String[] args) {
        new Deposit("1234"); // Test PIN
    }
}
