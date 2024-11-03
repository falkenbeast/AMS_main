package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show;

    public JourneyDetails() {

        getContentPane().setBackground(new Color(15, 17, 26)); // Dark background color
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for responsiveness

        // Top panel for PNR input and show button
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        topPanel.setBackground(new Color(30, 30, 45)); // Dark gray for the panel background

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setForeground(Color.WHITE); // White text color
        topPanel.add(lblpnr);

        pnr = new JTextField(15); // Set width of text field instead of fixed bounds
        pnr.setBackground(new Color(45, 45, 60)); // Dark gray input background
        pnr.setForeground(Color.WHITE); // White text color for input
        pnr.setCaretColor(Color.WHITE); // Ensure the cursor is visible (white in color)
        pnr.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1)); // Subtle border around input
        topPanel.add(pnr);

        show = new JButton("Show Details");
        show.setBackground(new Color(45, 45, 60)); // Dark gray button background
        show.setForeground(Color.WHITE); // White button text
        show.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1)); // Matching border
        show.addActionListener(this);
        topPanel.add(show);

        add(topPanel, BorderLayout.NORTH); // Add top panel to the top of the window

        // Table for displaying data
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table non-editable
            }
        };
        table.setForeground(Color.WHITE); // White text for the table
        table.setBackground(new Color(45, 45, 60)); // Slightly lighter gray for table rows
        table.setGridColor(new Color(100, 100, 100)); // Grid lines in gray
        table.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Font for the table contents

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBackground(new Color(30, 30, 45)); // Dark gray background for the table
        jsp.getViewport().setBackground(new Color(30, 30, 45)); // Background for table cells
        jsp.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1)); // Border around the scroll pane

        add(jsp, BorderLayout.CENTER); // Add scroll pane to the center of the window for responsiveness

        // Window setup
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    // Action event handling
    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + pnr.getText() + "'");

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
