package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {
    String pin;

    TextField textField;

    JButton b1,b2;


    Withdrawal(String pin) {
        this.pin=pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        JLabel l3 = new JLabel(scaleImage(i1));
        // Use layout manager to position the image label
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(l3, BorderLayout.CENTER);
        setContentPane(contentPane);
        // Set the JFrame size to match the image's scaled size plus the requested increments
        int width = l3.getIcon().getIconWidth() + 100; // Increase width by 20 pixels
        int height = l3.getIcon().getIconHeight() + 30; // Increase height by 10 pixels
        setSize(width, height);
        // Set a preferred size for the label to allow increasing the frame size
        l3.setPreferredSize(new Dimension(l3.getIcon().getIconWidth(), l3.getIcon().getIconHeight()));

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System",Font.BOLD,14));
        label1.setBounds(365,135,500,35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System",Font.BOLD,14));
        label2.setBounds(365,170,400,35);
        l3.add(label2);

        textField=new TextField();
        textField.setBounds(365,205,280,25);
        textField.setFont(new Font("Raleway",Font.BOLD,20));
        textField.setBackground(new Color(65,125,128));
        textField.setForeground(Color.WHITE);
        l3.add(textField);

        b1=new JButton("WITHDRAW");
        b1.setBounds(560,282,115,25);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2=new JButton("BACK");
        b2.setBounds(560,317,115,25);
        b2.setBackground(new Color(65,125,128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);


        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }
    // Scale the image to fit within the screen resolution
    private ImageIcon scaleImage(ImageIcon imageIcon) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxWidth = (int) (screenSize.getWidth() * 0.9);
        int maxHeight = (int) (screenSize.getHeight() * 0.9);

        int width = imageIcon.getIconWidth();
        int height = imageIcon.getIconHeight();

        if (width > maxWidth || height > maxHeight) {
            float scaleFactor = Math.min((float) maxWidth / width, (float) maxHeight / height);
            width = (int) (width * scaleFactor);
            height = (int) (height * scaleFactor);
            Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        }

        return imageIcon;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
        try {
            String amount=textField.getText();
            Date date=new Date();

            if(textField.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"PLease enter the amount you want to withdraw");
            } else{
                Con c=new Con();
                ResultSet resultSet= c.statement.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance =0;

                while (resultSet.next()) {
                    if(resultSet.getString("type").equals("Deposit")){
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }
                if(balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    return;
                }

                c.statement.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null,"Rs. "+amount+" Debited Successfully");
                new Main_Class(pin);

            }
        }catch (Exception E){
            E.printStackTrace();
        } } else if (e.getSource()==b2) {
            setVisible(false);
            new Main_Class(pin);

        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
