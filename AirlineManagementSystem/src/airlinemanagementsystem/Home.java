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
        headingPanel.setBackground(new Color(15, 17, 26, 150)); // Semi-transparent dark background
        headingPanel.setLayout(new FlowLayout());

        JLabel heading = new JLabel("Hi there, where would you like to IndiGo today?");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 36));
        headingPanel.add(heading);

        image.add(headingPanel, BorderLayout.NORTH); // Add heading at the top

        // Create the menu bar
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(15, 17, 26));
        menubar.setForeground(Color.WHITE);
        setJMenuBar(menubar);

        JMenu details = new JMenu("Details");
        menubar.add(details);
        details.setForeground(Color.white);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);
        flightDetails.setBackground(new Color(15, 17, 26));
        flightDetails.setForeground(Color.white);

        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);
        customerDetails.setBackground(new Color(15, 17, 26));
        customerDetails.setForeground(Color.white);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);
        bookFlight.setBackground(new Color(15, 17, 26));
        bookFlight.setForeground(Color.white);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);
        journeyDetails.setBackground(new Color(15, 17, 26));
        journeyDetails.setForeground(Color.white);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);
        ticketCancellation.setBackground(new Color(15, 17, 26));
        ticketCancellation.setForeground(Color.white);

        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);
        ticket.setForeground(Color.white);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this); // Added action listener for boarding pass
        ticket.add(boardingPass);
        boardingPass.setBackground(new Color(15, 17, 26));
        boardingPass.setForeground(Color.white);

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
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}
