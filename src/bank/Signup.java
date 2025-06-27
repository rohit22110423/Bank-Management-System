package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    JTextField nameField, fnameField, emailField, addressField, cityField, stateField, pincodeField;
    JRadioButton male, female, other, married, unmarried;
    JButton next;
    String formno;

    Signup() {
        setTitle("New Account Application Form - Page 1");
        setLayout(null);
        setSize(850, 700);
        setLocation(300, 40);
        getContentPane().setBackground(new Color(255, 255, 204));

        Random ran = new Random();
        formno = "" + Math.abs((ran.nextLong() % 9000L) + 1000L);

        JLabel formNoLabel = new JLabel("APPLICATION FORM NO. " + formno);
        formNoLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        formNoLabel.setBounds(250, 20, 400, 40);
        add(formNoLabel);

        JLabel personalDetails = new JLabel("Page 1: Personal Details");
        personalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personalDetails.setBounds(250, 60, 400, 40);
        add(personalDetails);

        addLabelAndField("Name:", nameField = new JTextField(), 100, 120);
        addLabelAndField("Father's Name:", fnameField = new JTextField(), 100, 180);
        addLabelAndField("Email:", emailField = new JTextField(), 100, 240);
        addLabelAndField("Address:", addressField = new JTextField(), 100, 300);
        addLabelAndField("City:", cityField = new JTextField(), 100, 360);
        addLabelAndField("State:", stateField = new JTextField(), 100, 420);
        addLabelAndField("Pin Code:", pincodeField = new JTextField(), 100, 480);

        addRadioButtons("Gender:", male = new JRadioButton("Male"), female = new JRadioButton("Female"), other = new JRadioButton("Other"), 100, 540);
        addRadioButtons("Marital Status:", married = new JRadioButton("Married"), unmarried = new JRadioButton("Unmarried"), null, 100, 590);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(600, 630, 100, 30);
        next.addActionListener(this);
        add(next);

        setVisible(true);
    }

    private void addLabelAndField(String labelText, JTextField field, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);

        field.setFont(new Font("Raleway", Font.PLAIN, 14));
        field.setBounds(x + 250, y, 300, 30);
        add(field);
    }

    private void addRadioButtons(String labelText, JRadioButton rb1, JRadioButton rb2, JRadioButton rb3, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);

        ButtonGroup group = new ButtonGroup();
        int offset = 250;

        if (rb1 != null) {
            rb1.setBounds(x + offset, y, 100, 30);
            rb1.setBackground(getContentPane().getBackground());
            rb1.setFont(new Font("Raleway", Font.PLAIN, 14));
            group.add(rb1);
            add(rb1);
            offset += 110;
        }

        if (rb2 != null) {
            rb2.setBounds(x + offset, y, 120, 30);
            rb2.setBackground(getContentPane().getBackground());
            rb2.setFont(new Font("Raleway", Font.PLAIN, 14));
            group.add(rb2);
            add(rb2);
            offset += 130;
        }

        if (rb3 != null) {
            rb3.setBounds(x + offset, y, 100, 30);
            rb3.setBackground(getContentPane().getBackground());
            rb3.setFont(new Font("Raleway", Font.PLAIN, 14));
            group.add(rb3);
            add(rb3);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText();
        String fname = fnameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String pincode = pincodeField.getText();

        String gender = male.isSelected() ? "Male" : (female.isSelected() ? "Female" : (other.isSelected() ? "Other" : ""));
        String marital = married.isSelected() ? "Married" : (unmarried.isSelected() ? "Unmarried" : "");

        if (name.isEmpty() || fname.isEmpty() || email.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || pincode.isEmpty() || gender.isEmpty() || marital.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields.");
            return;
        }

        try {
            Con c = new Con();
            String query = "INSERT INTO signup VALUES ('" + formno + "', '" + name + "', '" + fname + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + pincode + "', '" + state + "')";
            c.statement.executeUpdate(query);
            setVisible(false);
            new Signup2(formno);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());  // ✅ Corrected here
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
