package GraphicalUserInterfaces;

import Utilities.InputOutput;
import classes.Appointment;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

/**
 * This GUI interface class let user do - 1. enter appointment name, day, time
 * 2. store data to binary file.
 *
 * @author Evonne Chen Date: 8/5/2018
 */
public class AddAppointment extends JFrame implements ActionListener {
    //declare binary file

    String file = "Appointment.bin";

    //declare components
    JLabel lblTitle = new JLabel("Add appointment");

    JLabel lblName = new JLabel("Name");
    JLabel lblDay = new JLabel("Day");
    JLabel lblHour = new JLabel("Hour");

    // Bug - can't figure out how to add new line to avoid the label next to the text field,
    //so I adjust the text field width
    JTextField txfName = new JTextField(17);
    JTextField txfDay = new JTextField(18);
    JTextField txfHour = new JTextField(17);

    JButton btnAdd = new JButton("Add");
    JButton btnExit = new JButton("Exit");

    FrontEnd mainMenu;

    //set components to layout
    public AddAppointment(FrontEnd menu) {
        //layout
        super("Add Appintment");
        this.setTitle("And");
        this.setVisible(true);
        this.setBounds(500, 350, 250, 200);
        this.setResizable(false);

        mainMenu = menu;//back to main menu when data added or this window closed

        //content
        this.add(lblTitle);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 30));

        this.add(lblName);

        this.add(txfName);

        this.add(lblDay);
        this.add(txfDay);

        this.add(lblHour);
        this.add(txfHour);
        this.add(btnAdd);
        this.add(btnExit);

        this.setLayout(new FlowLayout());

        btnAdd.addActionListener(this);
        btnExit.addActionListener(this);

    }

    /**
     * method - what activities will be triggered when button clicked.
     *
     * @param
     * @return
     */
    public void actionPerformed(ActionEvent ev) {
        //add button - add data input to array list and run save data to binary file method
        //do{
        if (ev.getSource() == btnAdd) {

            //declare
            ArrayList<Appointment> list = new ArrayList<>();
            String day, hour;
            String name;
            int intDay, intHour;

            //get value
            day = txfDay.getText();
            hour = txfHour.getText();
            name = txfName.getText();

            //transfer String value to integer
            intDay = Integer.parseInt(day);
            intHour = Integer.parseInt(hour);

            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            boolean leapYear = localDate.isLeapYear();

            //int year = localDate.getYear();
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (intDay > 31 || intDay < 1) {
                    JOptionPane.showMessageDialog(null, "Error - not a valid day.");
                    txfDay.setText("");

                } else if (intHour > 24 || intHour < 1) {
                    JOptionPane.showMessageDialog(null, "Error - not a valid time.");
                    txfHour.setText("");
                } else if (name.length() < 1) {
                    JOptionPane.showMessageDialog(null, "Error - name required.");
                } else {
                    addToDB(list, intDay, intHour, name);
                    txfName.setText("");
                    txfDay.setText("");
                    txfHour.setText("");
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (intDay > 30 || intDay < 1) {
                    JOptionPane.showMessageDialog(null, "Error - not a valid day.");
                    txfDay.setText("");

                } else if (intHour > 24 || intHour < 1) {
                    JOptionPane.showMessageDialog(null, "Error - not a valid time.");
                    txfHour.setText("");
                } else if (name.length() < 1) {
                    JOptionPane.showMessageDialog(null, "Error - name required.");
                } else {
                    addToDB(list, intDay, intHour, name);
                    txfName.setText("");
                    txfDay.setText("");
                    txfHour.setText("");
                }

            } else if (month == 2) {
                if (leapYear = true) {
                    if (intDay > 29 || intDay < 1) {
                        JOptionPane.showMessageDialog(null, "Error - not a valid day.");
                        txfDay.setText("");

                    } else if (intHour > 24 || intHour < 1) {
                        JOptionPane.showMessageDialog(null, "Error - not a valid time.");
                        txfHour.setText("");
                    } else if (name.length() < 1) {
                        JOptionPane.showMessageDialog(null, "Error - name required.");
                    } else {
                        addToDB(list, intDay, intHour, name);
                        txfName.setText("");
                        txfDay.setText("");
                        txfHour.setText("");
                    }

                }
                if (leapYear = false) {
                    if (intDay > 28 || intDay < 1) {
                        JOptionPane.showMessageDialog(null, "Error - not a valid day.");
                        txfDay.setText("");

                    } else if (intHour > 24 || intHour < 1) {
                        JOptionPane.showMessageDialog(null, "Error - not a valid time.");
                        txfHour.setText("");
                    } else if (name.length() < 1) {
                        JOptionPane.showMessageDialog(null, "Error - name required.");
                    } else {
                        addToDB(list, intDay, intHour, name);
                        txfName.setText("");
                        txfDay.setText("");
                        txfHour.setText("");
                    }

                }

            }

        }

        // exit button - leave this interface and back to main menu.
        if (ev.getSource() == btnExit) {
            FrontEnd menu = new FrontEnd();
            this.setVisible(false);
        }
    }

    /**
     * method - take the data from ArrayList and run method in InputOutput class
     * to save it to binary file
     *
     * @param ArrayList<Appointment> list
     * @return
     */
    public void addToDB(ArrayList<Appointment> list, int intDay, int intHour, String name) {

        try {

            InputOutput.readData(file, list, intDay, intHour, name);

        } catch (FileNotFoundException fnfE) {
            System.err.println("Problems with the Appointment.bin file");
        } catch (IOException ioE) {
            System.err.println("Issues with the input data stream");
        }
    }

}
