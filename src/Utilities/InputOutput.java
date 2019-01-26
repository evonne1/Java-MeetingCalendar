package Utilities;

import classes.Appointment;

import java.io.*;  //import all IO classes
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import classes.Appointment;

/**
 * This purpose of this Java class is processing to 1. save the data to binary
 * file from ArrayList. 2. read the data from binary file
 *
 * @author Evonne Chen Date: 8/5/2018
 */
public class InputOutput {

    /**
     * method - save data from array list to a binary file.
     *
     * @param String file, ArrayList<Appointment> list
     * @return
     */
    public static void saveData(String file, ArrayList<Appointment> list, int intDay, int intHour, String name)
            throws FileNotFoundException, IOException {

        //declare
        FileOutputStream fo;
        DataOutputStream dos;

        //write ArrayList data to file
        fo = new FileOutputStream(file, true); //true - save new data to binary file without reset the file
        dos = new DataOutputStream(fo);

        Appointment temp = new Appointment(intDay, intHour, name);
        //add the array to array list
        //list.add(new Appointment(intDay, intHour, name));
        list.clear();
        list.add(temp);

        for (Appointment ap : list) {
            dos.writeInt(ap.getDay());
            dos.writeInt(ap.getHour());
            dos.writeUTF(ap.getName());
        }

        dos.close();
        JOptionPane.showMessageDialog(null, "Data saved to file");

    }

    /**
     * method - take input data from AddAppointment.class to verify is it
     * already exiist in binary file.
     *
     * @param String file, ArrayList<Appointment> list
     * @return
     */
    public static void readData(String file, ArrayList<Appointment> list, int intDay, int intHour, String name)
            throws FileNotFoundException, IOException {

        //read Appointment data from file
        FileInputStream fi;
        DataInputStream di;

        fi = new FileInputStream(file);
        di = new DataInputStream(fi);

        //available() method - read bytes until none are left
        while (di.available() > 0) {
            list.add(new Appointment(
                    di.readInt(), di.readInt(), di.readUTF()));
        }

        //verify if the input data exist in binary file
        boolean duplicate = false;

        for (int i = 0; i < list.size(); i++) {
            if (intDay == list.get(i).getDay() && intHour == list.get(i).getHour()) {
                duplicate = true;
            }
        }
        if (duplicate == true) {
            JOptionPane.showMessageDialog(null, "This session is not available.");

        }
        //save data if it is not exist in binary file
        if (duplicate == false) {
            saveData(file, list, intDay, intHour, name);

        }

        di.close();

    }

    /**
     * method - read and sort data from binary file
     *
     * @param String file, ArrayList<Appointment> list
     * @return
     */
    public static void readRealData(String file, ArrayList<Appointment> list)
            throws FileNotFoundException, IOException {
        //read Appointment data from file
        FileInputStream fi;
        DataInputStream di;

        fi = new FileInputStream(file);
        di = new DataInputStream(fi);

        //current time: reference https://stackoverflow.com/questions/7182996/java-get-month-integer-from-date
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        int year = localDate.getYear();

        //output String
        String output = month + "/" + year + " MONTHY\nAPPOINTMENT"
                + "\nClient    Day    Hour\n";

        //available() method - read bytes until none are left
        while (di.available() > 0) {
            list.add(new Appointment(
                    di.readInt(), di.readInt(), di.readUTF()));
        }

        di.close();
        // run sort method in sortDay class and assign the sorted data to output String
        Collections.sort(list, new sortDay());

        // display sorted data in JTable
        String col[] = {"Name", "Day of " + localDate.getMonth() + " " + localDate.getYear(), "Hour"};// column header
        DefaultTableModel tableModel = new DefaultTableModel(col, 0); // The 0 argument is number rows.
        JTable table = new JTable(tableModel);
        table.setEnabled(false);// set the table can't be edited 
        table.setVisible(true);

        //loop - retrieve all data
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = {list.get(i).getName(), list.get(i).getDay(), list.get(i).getHour()};
            tableModel.addRow(objs);
        }
        // define ScrollPane
        JScrollPane spTable = new JScrollPane(table);

        JPanel panel = new JPanel();
      
        panel.add(spTable);
        JOptionPane.showMessageDialog(null, spTable);

    }
}
