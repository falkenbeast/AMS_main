package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight() {
        // Window setup
        getContentPane().setBackground(new Color(15, 17, 26)); // Set background color
        setLayout(null);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.WHITE); // Change text color to white
        add(heading);

        // Aadhar field
        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblaadhar.setForeground(Color.WHITE); // Change text color to white
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        tfaadhar.setBackground(new Color(30, 30, 45)); // Dark background for text field
        tfaadhar.setForeground(Color.WHITE); // Text color white
        add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.WHITE);
        fetchButton.setForeground(new Color(15, 17, 26)); // Button text color dark
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        // Passenger details labels
        setupLabel("Name", 60, 130);
        tfname = setupDisplayLabel(220, 130);

        setupLabel("Nationality", 60, 180);
        tfnationality = setupDisplayLabel(220, 180);

        setupLabel("Address", 60, 230);
        tfaddress = setupDisplayLabel(220, 230);

        setupLabel("Gender", 60, 280);
        labelgender = setupDisplayLabel(220, 280);

        // Flight source and destination choice components
        setupLabel("Source", 60, 330);
        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        add(source);

        setupLabel("Destination", 60, 380);
        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        // Fetch flight button
        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.WHITE);
        flight.setForeground(new Color(15, 17, 26)); // Button text color dark
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);

        // Flight details
        setupLabel("Flight Name", 60, 430);
        labelfname = setupDisplayLabel(220, 430);

        setupLabel("Flight Code", 60, 480);
        labelfcode = setupDisplayLabel(220, 480);

        // Date chooser for travel
        setupLabel("Date of Travel", 60, 530);
        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);

        // Flight booking button
        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.WHITE);
        bookflight.setForeground(new Color(15, 17, 26)); // Button text color dark
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        // Background image for aesthetics
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/book.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        add(lblimage);

        // Window size and visibility
        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);

        // Fetch flight source and destination data from database
        loadFlightData();
    }

    // Helper method to setup labels
    private void setupLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setForeground(Color.WHITE); // Change text color to white
        add(label);
    }

    // Helper method to setup display labels
    private JLabel setupDisplayLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setBounds(x, y, 150, 25);
        label.setForeground(Color.WHITE); // Change text color to white
        add(label);
        return label;
    }

    // Load available flight data (source and destination)
    private void loadFlightData() {
        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);

            while(rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Action handling logic
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            fetchPassengerData();
        } else if (ae.getSource() == flight) {
            fetchFlightDetails();
        } else {
            bookFlight();
        }
    }

    // Fetch passenger data by Aadhar
    private void fetchPassengerData() {
        String aadhar = tfaadhar.getText();
        try {
            Conn conn = new Conn();
            String query = "select * from passenger where aadhar = '" + aadhar + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                tfaddress.setText(rs.getString("address"));
                labelgender.setText(rs.getString("gender"));
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct Aadhar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fetch flight details based on source and destination
    private void fetchFlightDetails() {
        String src = source.getSelectedItem();
        String dest = destination.getSelectedItem();
        try {
            Conn conn = new Conn();
            String query = "select * from flight where source = '" + src + "' and destination = '" + dest + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                labelfname.setText(rs.getString("f_name"));
                labelfcode.setText(rs.getString("f_code"));
            } else {
                JOptionPane.showMessageDialog(null, "No Flights Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Book a flight based on selected details
    private void bookFlight() {
        Random random = new Random();

        String aadhar = tfaadhar.getText();
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String flightname = labelfname.getText();
        String flightcode = labelfcode.getText();
        String src = source.getSelectedItem();
        String des = destination.getSelectedItem();
        String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

        try {
            Conn conn = new Conn();
            String query = "insert into reservation values('PNR-" + random.nextInt(1000000) + "', 'TIC-" + random.nextInt(10000) + "', '" + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" + flightcode + "', '" + src + "', '" + des + "', '" + ddate + "')";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Ticket Booked Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
