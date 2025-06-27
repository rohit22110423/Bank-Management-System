package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class FastCash extends JFrame implements ActionListener {
    JButton b100, b500, b1000, b2000, b5000, b10000, back;
    String pin;

    public FastCash(String pin) {
        this.pin = pin;

        setTitle("Fast Cash Withdrawal");
        setSize(900, 900);
        setLocation(300, 0);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(215, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        b100 = createButton("Rs 100", 170, 415, image);
        b500 = createButton("Rs 500", 355, 415, image);
        b1000 = createButton("Rs 1000", 170, 450, image);
        b2000 = createButton("Rs 2000", 355, 450, image);
        b5000 = createButton("Rs 5000", 170, 485, image);
        b10000 = createButton("Rs 10000", 355, 485, image);
        back = createButton("BACK", 355, 520, image);

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, Container container) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setBackground(new Color(65, 125, 128));
        button.setForeground(Color.WHITE);
        button.addActionListener(this);
        container.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String amount = ((JButton) ae.getSource()).getText().substring(3); // remove 'Rs '

        if (ae.getSource() == back) {
            setVisible(false);
            new Main_Class(pin);
            return;
        }

        try {
            Con c = new Con();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            c.statement.executeUpdate("INSERT INTO bank VALUES ('" + pin + "', NOW(), 'Withdraw', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");
            setVisible(false);
            new Main_Class(pin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
