package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {
        // Set background color and layout
        getContentPane().setBackground(new Color(15, 17, 26));
        setLayout(new GridBagLayout()); // Use GridBagLayout for responsive design
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding between components

        // Username label
        JLabel lblusername = new JLabel("Username");
        lblusername.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(lblusername, gbc);

        // Username text field
        tfusername = new JTextField(15); // Width of 15 columns
        gbc.gridx = 1;
        add(tfusername, gbc);

        // Password label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblpassword, gbc);

        // Password text field
        tfpassword = new JPasswordField(15); // Width of 15 columns
        gbc.gridx = 1;
        add(tfpassword, gbc);

        // Reset button
        reset = new JButton("Reset");
        reset.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(reset, gbc);

        // Submit button
        submit = new JButton("Submit");
        submit.addActionListener(this);
        gbc.gridx = 1;
        add(submit, gbc);

        // Close button
        close = new JButton("Close");
        close.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(close, gbc);

        // Set window properties
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = String.valueOf(tfpassword.getPassword()); // Get password securely
            try {
                Conn c = new Conn();
                String query = "SELECT * FROM login WHERE username = '" + username + "' AND password = '" + password + "'";
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        } else if (ae.getSource() == close) {
            System.exit(0); // Close the application
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
