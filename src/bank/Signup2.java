package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;
    JTextField panField, aadharField;
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    JButton next;
    String formno;

    Signup2(String formno) {
        this.formno = formno;
        setTitle("New Account - Page 2: Additional Details");
        setLayout(null);
        setSize(850, 700);
        setLocation(300, 40);
        getContentPane().setBackground(new Color(252, 208, 76));

        JLabel heading = new JLabel("Page 2: Additional Details");
        heading.setFont(new Font("Raleway", Font.BOLD, 24));
        heading.setBounds(280, 30, 600, 40);
        add(heading);

        addLabelAndCombo("Religion:", new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Other"}, 100, 100, religionBox = new JComboBox<>());
        addLabelAndCombo("Category:", new String[]{"General", "OBC", "SC", "ST", "Other"}, 100, 160, categoryBox = new JComboBox<>());
        addLabelAndCombo("Income:", new String[]{"Null", "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"}, 100, 220, incomeBox = new JComboBox<>());
        addLabelAndCombo("Educational:", new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Other"}, 100, 280, educationBox = new JComboBox<>());
        addLabelAndCombo("Occupation:", new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"}, 100, 340, occupationBox = new JComboBox<>());

        addTextField("PAN Number:", panField = new JTextField(), 100, 400);
        addTextField("Aadhar Number:", aadharField = new JTextField(), 100, 460);

        addRadioButtons("Senior Citizen:", seniorYes = new JRadioButton("Yes"), seniorNo = new JRadioButton("No"), 100, 520);
        addRadioButtons("Existing Account:", existingYes = new JRadioButton("Yes"), existingNo = new JRadioButton("No"), 100, 580);

        JLabel formLabel = new JLabel("Form No: " + formno);
        formLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        formLabel.setBounds(700, 10, 150, 30);
        add(formLabel);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setBounds(600, 630, 100, 30);
        next.addActionListener(this);
        add(next);

        setVisible(true);
    }

    private void addLabelAndCombo(String labelText, String[] options, int x, int y, JComboBox<String> comboBox) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);

        comboBox.setModel(new DefaultComboBoxModel<>(options));
        comboBox.setFont(new Font("Raleway", Font.PLAIN, 14));
        comboBox.setBounds(x + 250, y, 300, 30);
        comboBox.setBackground(Color.WHITE);
        add(comboBox);
    }

    private void addTextField(String labelText, JTextField textField, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);

        textField.setFont(new Font("Raleway", Font.PLAIN, 14));
        textField.setBounds(x + 250, y, 300, 30);
        add(textField);
    }

    private void addRadioButtons(String labelText, JRadioButton btn1, JRadioButton btn2, int x, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 200, 30);
        add(label);

        btn1.setFont(new Font("Raleway", Font.PLAIN, 14));
        btn1.setBackground(new Color(252, 208, 76));
        btn1.setBounds(x + 250, y, 100, 30);
        add(btn1);

        btn2.setFont(new Font("Raleway", Font.PLAIN, 14));
        btn2.setBackground(new Color(252, 208, 76));
        btn2.setBounds(x + 360, y, 100, 30);
        add(btn2);

        ButtonGroup group = new ButtonGroup();
        group.add(btn1);
        group.add(btn2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String religion = (String) religionBox.getSelectedItem();
        String category = (String) categoryBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String education = (String) educationBox.getSelectedItem();
        String occupation = (String) occupationBox.getSelectedItem();
        String pan = panField.getText();
        String aadhar = aadharField.getText();

        String senior = seniorYes.isSelected() ? "Yes" : (seniorNo.isSelected() ? "No" : "");
        String existing = existingYes.isSelected() ? "Yes" : (existingNo.isSelected() ? "No" : "");

        if (pan.isEmpty() || aadhar.isEmpty() || senior.isEmpty() || existing.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the required fields.");
            return;
        }

        try {
            Con c = new Con();
            String query = "INSERT INTO signuptwo VALUES ('" + formno + "', '" + religion + "', '" + category + "', '" + income + "', '" + education + "', '" + occupation + "', '" + pan + "', '" + aadhar + "', '" + senior + "', '" + existing + "')";
            c.statement.executeUpdate(query);
            setVisible(false);
            new Signup3(formno);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup2("1234");
    }
}
