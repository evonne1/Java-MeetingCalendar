package GraphicalUserInterfaces;

import Utilities.*;
import classes.Appointment;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This GUI interface class is a main menu has options to add appointment or
 * display record.
 *
 * @author Evonne Chen Date: 8/5/2018
 */
public class FrontEnd extends JFrame implements ActionListener {

    //declare components
    JButton btnHelp = new JButton("Help");
    JButton btnAdd = new JButton("Add Appointment");
    JButton btnSearch = new JButton("Search Appointment");
    JLabel lblTitle = new JLabel("Meeting Calendar");
    JLabel lblSubTitle = new JLabel("Enter or Search Appointment");

    //implement components to layout
    public FrontEnd() {
        //Layout
        super("Meeting Calendar");//header
        this.setVisible(true);
        this.setBounds(100, 350, 300, 200);//setBounds(x, y, width, height)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //components
        this.add(lblTitle);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(lblSubTitle);
        this.add(btnHelp);
        this.add(btnAdd);
        this.add(btnSearch);

        this.setLayout(new FlowLayout());

        //run method when button is clicked
        btnHelp.addActionListener(this);
        btnAdd.addActionListener(this);
        btnSearch.addActionListener(this);

    }

    /**
     * method - what activities will be triggered when button clicked.
     *
     * @param
     * @return
     */
    public void actionPerformed(ActionEvent e) {
        //Help button - display the info about how to use this application
        if (e.getSource() == btnHelp) {
            JOptionPane.showMessageDialog(null, "HELP INFORMATION\n\n"
                    + "The meeting calendar app saves your appointments to file for the current month.\n\n"
                    + "Click \"Add appointment\" to add the clients name, day and hour of the appointment\n\n"
                    + "Click Search appointment\" to see a list of all the appointments for the month");
        }
        //Add button - Open the AddAppointment GUI interface and set the menu invisible.
        if (e.getSource() == btnAdd) {
            AddAppointment app = new AddAppointment(this);//run next dialog box once add button is sel
            this.setVisible(false);//set the current JFrame to invisible             
        }
        //Search button - run readRealData method in InputOutput class to retrieve all data in binary file
        if (e.getSource() == btnSearch) {
            String file = "Appointment.bin";
            ArrayList<Appointment> list = new ArrayList<>();
            try {
                InputOutput.readRealData(file, list);
                list.clear();  //clear all objects from ArrayList

            } catch (FileNotFoundException fnfE) {
                System.err.println("Problems with the Appointment.bin file");
            } catch (IOException ioE) {
                System.err.println("Issues with the input data stream");
            }
        }
    }

}
