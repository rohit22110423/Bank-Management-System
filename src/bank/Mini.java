package bank;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Mini extends JFrame {
    String pin;

    Mini(String pin) {
        this.pin = pin;

        setTitle("Mini Statement");
        setLayout(null);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel bankLabel = new JLabel("Indian Bank");
        bankLabel.setFont(new Font("System", Font.BOLD, 24));
        bankLabel.setBounds(150, 20, 200, 30);
        add(bankLabel);

        JLabel cardLabel = new JLabel("Card Number: ");
        cardLabel.setFont(new Font("System", Font.BOLD, 16));
        cardLabel.setBounds(30, 70, 400, 20);
        add(cardLabel);

        JLabel statementLabel = new JLabel();
        statementLabel.setFont(new Font("System", Font.PLAIN, 14));
        statementLabel.setBounds(30, 100, 400, 200);
        add(statementLabel);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(30, 310, 400, 30);
        add(balanceLabel);

        try {
            Con c = new Con();

            ResultSet rs = c.statement.executeQuery("SELECT * FROM login WHERE pin = '" + pin + "'");
            if (rs.next()) {
                String cardNo = rs.getString("cardno");
                cardLabel.setText("Card Number: " + cardNo.substring(0, 4) + "-XXXX-XXXX-" + cardNo.substring(12));
            }

            ResultSet rs2 = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            StringBuilder transactions = new StringBuilder("<html>");
            int balance = 0;

            while (rs2.next()) {
                transactions.append(rs2.getString("date")).append(" | ")
                            .append(rs2.getString("type")).append(" | Rs. ")
                            .append(rs2.getString("amount")).append("<br>");
                
                if (rs2.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs2.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs2.getString("amount"));
                }
            }

            transactions.append("</html>");
            statementLabel.setText(transactions.toString());
            balanceLabel.setText("Current Balance: Rs. " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Mini("");
    }
}
