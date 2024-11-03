package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BoardingPass extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;

    public BoardingPass() {
        // Set the dark background color
        getContentPane().setBackground(new Color(15, 17, 26));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Heading label
        JLabel heading = new JLabel("INDIGO");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(heading, gbc);

        // Subheading label
        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        subheading.setForeground(Color.CYAN);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(subheading, gbc);

        // PNR details label and text field
        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblaadhar.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(lblaadhar, gbc);

        tfpnr = new JTextField();
        tfpnr.setBackground(new Color(45, 45, 60));
        tfpnr.setForeground(Color.WHITE);
        tfpnr.setCaretColor(Color.WHITE);
        tfpnr.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(tfpnr, gbc);

        // Fetch button
        fetchButton = new JButton("Enter");
        fetchButton.setBackground(new Color(45, 45, 60));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        add(fetchButton, gbc);
        fetchButton.addActionListener(this);

        // Labels and details for boarding pass
        createLabelAndField("NAME", tfname = new JLabel(), gbc, 3);
        createLabelAndField("NATIONALITY", tfnationality = new JLabel(), gbc, 4);
        createLabelAndField("SRC", lblsrc = new JLabel(), gbc, 5);

        // Move DEST to the next line
        createLabelAndField("DEST", lbldest = new JLabel(), gbc, 6);

        createLabelAndField("Flight Name", labelfname = new JLabel(), gbc, 7);

        // Move Flight Code to the next line
        createLabelAndField("Flight Code", labelfcode = new JLabel(), gbc, 8);

        createLabelAndField("Date", labeldate = new JLabel(), gbc, 9);

        // Image section
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/indi.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 10;
        add(lblimage, gbc);

        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to create label and field pairs
    private void createLabelAndField(String labelText, JLabel labelField, GridBagConstraints gbc, int yPos) {
        createLabelAndField(labelText, labelField, gbc, yPos, false);
    }

    private void createLabelAndField(String labelText, JLabel labelField, GridBagConstraints gbc, int yPos, boolean isDoubleColumn) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        gbc.gridx = isDoubleColumn ? 2 : 0;
        gbc.gridy = yPos;
        gbc.gridwidth = 1;
        add(label, gbc);

        labelField.setForeground(Color.WHITE);
        gbc.gridx = isDoubleColumn ? 3 : 1;
        gbc.gridwidth = 2;
        add(labelField, gbc);
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = tfpnr.getText();

        try {
            Conn conn = new Conn();
            String query = "select * from reservation where PNR = '" + pnr + "'";

            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                lblsrc.setText(rs.getString("src"));
                lbldest.setText(rs.getString("des"));
                labelfname.setText(rs.getString("flightname"));
                labelfcode.setText(rs.getString("flightcode"));
                labeldate.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}
