package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;

    public AddCustomer() {
        getContentPane().setBackground(new Color(15, 17, 26)); // Background color
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(Color.CYAN);
        add(heading);

        // Name Label and TextField
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setForeground(Color.WHITE);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 150, 25);
        tfname.setBackground(new Color(30, 30, 30)); // Darker background for text fields
        tfname.setForeground(Color.WHITE);
        tfname.setCaretColor(Color.WHITE);
        add(tfname);

        // Nationality Label and TextField
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblnationality.setForeground(Color.WHITE);
        add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 150, 25);
        tfnationality.setBackground(new Color(30, 30, 30));
        tfnationality.setForeground(Color.WHITE);
        tfnationality.setCaretColor(Color.WHITE);
        add(tfnationality);

        // Aadhar Number Label and TextField
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 180, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblaadhar.setForeground(Color.WHITE);
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 180, 150, 25);
        tfaadhar.setBackground(new Color(30, 30, 30));
        tfaadhar.setForeground(Color.WHITE);
        tfaadhar.setCaretColor(Color.WHITE);
        add(tfaadhar);

        // Address Label and TextField
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbladdress.setForeground(Color.WHITE);
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 150, 25);
        tfaddress.setBackground(new Color(30, 30, 30));
        tfaddress.setForeground(Color.WHITE);
        tfaddress.setCaretColor(Color.WHITE);
        add(tfaddress);

        // Gender Label
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setForeground(Color.WHITE);
        add(lblgender);

        // Gender Radio Buttons (unchanged from your original code)
        ButtonGroup gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(new Color(15, 17, 26)); // Original background
        rbmale.setForeground(Color.WHITE);
        add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 70, 25);
        rbfemale.setBackground(new Color(15, 17, 26)); // Original background
        rbfemale.setForeground(Color.WHITE);
        add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        // Phone Label and TextField
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblphone.setForeground(Color.WHITE);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 150, 25);
        tfphone.setBackground(new Color(30, 30, 30));
        tfphone.setForeground(Color.WHITE);
        tfphone.setCaretColor(Color.WHITE);
        add(tfphone);

        // Save Button
        JButton save = new JButton("SAVE");
        save.setBackground(new Color(0, 123, 255));
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(save);
        save.addActionListener(this);

        // Image
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cust.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450, 80, 280, 400);
        add(lblimage);

        // Frame settings
        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String aadhar = tfaadhar.getText();
        String gender = rbmale.isSelected() ? "Male" : "Female";

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO passenger VALUES('" + name + "', '" + nationality + "', '" + phone + "', '" + address + "', '" + aadhar + "', '" + gender + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
