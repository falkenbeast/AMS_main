package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat; // **Import for date formatting**

public class AddHotelBooking extends JFrame implements ActionListener {

    JTextField tfBookingID, tfName, tfPhone, tfAadhar, tfHotelName, tfRoomType, tfTotalCost;
    JDateChooser checkInDate, checkOutDate;

    public AddHotelBooking() {
        getContentPane().setBackground(new Color(15, 17, 26)); // Background color
        setLayout(new GridBagLayout()); // **Change: Use GridBagLayout for responsiveness**
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally

        // Heading
        JLabel heading = new JLabel("ADD HOTEL BOOKING");
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(Color.CYAN);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(heading, gbc);

        // Booking ID Label and TextField
        addLabelAndTextField(gbc, "Booking ID", tfBookingID = new JTextField(15), 1);
        // Name Label and TextField
        addLabelAndTextField(gbc, "Name", tfName = new JTextField(15), 2);
        // Aadhar Number Label and TextField
        addLabelAndTextField(gbc, "Aadhar Number", tfAadhar = new JTextField(15), 3);
        // Phone Label and TextField
        addLabelAndTextField(gbc, "Phone", tfPhone = new JTextField(15), 4);
        // Hotel Name Label and TextField
        addLabelAndTextField(gbc, "Hotel Name", tfHotelName = new JTextField(15), 5);
        // Check-in Date Label
        addLabelAndDateChooser(gbc, "Check-in Date", checkInDate = new JDateChooser(), 6);
        // Check-out Date Label
        addLabelAndDateChooser(gbc, "Check-out Date", checkOutDate = new JDateChooser(), 7);
        // Room Type Label and TextField
        addLabelAndTextField(gbc, "Room Type", tfRoomType = new JTextField(15), 8);
        // Total Cost Label and TextField
        addLabelAndTextField(gbc, "Total Cost", tfTotalCost = new JTextField(15), 9);

        // Save Button
        JButton save = new JButton("SAVE");
        save.setBackground(new Color(0, 123, 255));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("Tahoma", Font.BOLD, 16));
        save.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(save, gbc);

        // **Change: Logo Area with Image**
        gbc.gridx = 1; // Move to second column for logo
        gbc.gridy = 0; // Reset to first row
        gbc.gridheight = 11; // Occupy all rows
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel logoLabel = new JLabel();
        logoLabel.setPreferredSize(new Dimension(150, 150)); // Set preferred size for logo area

        // **Load your logo image here**
        ImageIcon logoIcon = new ImageIcon("hoin.png"); // Adjust the path
        logoLabel.setIcon(logoIcon); // Set the logo image
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Center the logo
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        add(logoLabel, gbc); // Add logo area

        // Frame settings
        setSize(800, 600); // **Change: Increased width for logo space**
        setLocationRelativeTo(null); // Center the frame on screen
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addLabelAndTextField(GridBagConstraints gbc, String labelText, JTextField textField, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        add(label, gbc);

        textField.setBackground(new Color(30, 30, 30));
        textField.setForeground(Color.WHITE);
        textField.setCaretColor(Color.WHITE);
        gbc.gridx = 1;
        add(textField, gbc);
    }

    private void addLabelAndDateChooser(GridBagConstraints gbc, String labelText, JDateChooser dateChooser, int gridY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        add(label, gbc);

        dateChooser.setBackground(new Color(30, 30, 30));
        gbc.gridx = 1;
        add(dateChooser, gbc);
    }

    public void actionPerformed(ActionEvent ae) {
        String bookingID = tfBookingID.getText();
        String name = tfName.getText();
        String phone = tfPhone.getText();
        String aadhar = tfAadhar.getText();
        String hotelName = tfHotelName.getText();

        // Get the selected dates and format them
        java.util.Date checkInDateUtil = checkInDate.getDate();
        java.util.Date checkOutDateUtil = checkOutDate.getDate();

        // Use SimpleDateFormat to format the dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkIn = checkInDateUtil != null ? dateFormat.format(checkInDateUtil) : null;
        String checkOut = checkOutDateUtil != null ? dateFormat.format(checkOutDateUtil) : null;

        String roomType = tfRoomType.getText();
        String totalCost = tfTotalCost.getText();

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO hotel_booking VALUES('" + bookingID + "', '" + name + "', '" + aadhar + "', '" + phone + "', '" + hotelName + "', '" + checkIn + "', '" + checkOut + "', '" + roomType + "', " + totalCost + ")";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Hotel Booking Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddHotelBooking();
    }
}
