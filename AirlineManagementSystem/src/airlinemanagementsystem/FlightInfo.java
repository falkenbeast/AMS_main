package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {

        // Set the background color using rgb(15, 17, 26)
        Color darkBackground = new Color(15, 17, 26);
        getContentPane().setBackground(darkBackground);

        // Set the layout manager to BorderLayout, which is responsive
        setLayout(new BorderLayout());

        // Create a JTable to hold the flight information
        JTable table = new JTable();

        // Set dark mode colors for the table
        table.setBackground(new Color(30, 33, 45));  // Slightly lighter than the main background
        table.setForeground(Color.WHITE);            // White text for better contrast
        table.setGridColor(Color.GRAY);              // Gray grid lines
        table.setSelectionBackground(new Color(45, 50, 65));  // Highlight with a slightly lighter color
        table.setSelectionForeground(Color.WHITE);   // White text on selected rows

        // Try to connect to the database and load flight data
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from flight");

            // Use DbUtils to set the ResultSet data into the JTable
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Wrap the table in a JScrollPane for scrollable content
        JScrollPane jsp = new JScrollPane(table);
        jsp.getViewport().setBackground(darkBackground); // Set the scroll pane background to match the main background
        add(jsp, BorderLayout.CENTER); // Add it to the center of the layout

        // Set the window size and make it resizable by default
        setPreferredSize(new Dimension(800, 500));
        pack(); // Adjusts size based on components' preferred sizes
        setLocationRelativeTo(null); // Centers the window
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ensure dark mode colors are applied using UIManager defaults for consistency
        UIManager.put("Table.background", new Color(30, 33, 45));
        UIManager.put("Table.foreground", Color.WHITE);
        UIManager.put("Table.selectionBackground", new Color(45, 50, 65));
        UIManager.put("Table.selectionForeground", Color.WHITE);
        UIManager.put("Table.gridColor", Color.GRAY);
        UIManager.put("TableHeader.background", new Color(20, 23, 35));
        UIManager.put("TableHeader.foreground", Color.WHITE);

        SwingUtilities.invokeLater(() -> new FlightInfo()); // Thread-safe UI initialization
    }
}
