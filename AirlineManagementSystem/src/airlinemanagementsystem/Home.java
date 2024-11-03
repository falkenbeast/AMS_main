package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {
    public Home() {
        // Set the layout to BorderLayout for responsiveness
        setLayout(new BorderLayout());

        // Load the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/mainf.jpg"));
        JLabel image = new JLabel(i1);
        image.setLayout(new BorderLayout()); // Ensure JLabel can hold components
        add(image, BorderLayout.CENTER);

        // Create a panel for heading
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(0, 128, 128, 150)); // Vibrant semi-transparent teal
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));

        // Main heading
        JLabel heading = new JLabel("Hi there, where would you like to IndiGo today?");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(heading);

        // Subheading line
        JLabel subheading = new JLabel("India by IndiGo");
        subheading.setForeground(Color.LIGHT_GRAY); // Soft contrast to white
        subheading.setFont(new Font("Tahoma", Font.ITALIC, 28));
        subheading.setAlignmentX(Component.CENTER_ALIGNMENT);
        headingPanel.add(subheading);

        image.add(headingPanel, BorderLayout.NORTH); // Add heading at the top

        // Create the menu bar
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(72, 61, 139)); // Vibrant slate blue
        menubar.setForeground(Color.WHITE);
        setJMenuBar(menubar);

        JMenu details = new JMenu("Details");
        menubar.add(details);
        details.setForeground(Color.white);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        flightDetails.setBackground(new Color(72, 61, 139));
        flightDetails.setForeground(Color.white);

        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        customerDetails.setBackground(new Color(72, 61, 139));
        customerDetails.setForeground(Color.white);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        bookFlight.setBackground(new Color(72, 61, 139));
        bookFlight.setForeground(Color.white);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);
        journeyDetails.setBackground(new Color(72, 61, 139));
        journeyDetails.setForeground(Color.white);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);
        ticketCancellation.setBackground(new Color(72, 61, 139));
        ticketCancellation.setForeground(Color.white);

        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        ticket.setForeground(Color.white);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);
        ticket.add(boardingPass);
        boardingPass.setBackground(new Color(72, 61, 139));
        boardingPass.setForeground(Color.white);

        // **New Code: Create Hotel Menu**
        JMenu hotelMenu = new JMenu("Hotel");
        menubar.add(hotelMenu);
        hotelMenu.setForeground(Color.white);

        JMenuItem hotelReservation = new JMenuItem("Hotel Reservation");
        hotelReservation.addActionListener(this);
        hotelMenu.add(hotelReservation);
        hotelReservation.setBackground(new Color(72, 61, 139));
        hotelReservation.setForeground(Color.white);

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();
        if (text.equals("Add Customer Details")) {
            new AddCustomer();
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();
        } else if (text.equals("Hotel Reservation")) {
            new AddHotelBooking(); // Open the AddHotelBooking window
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
