package Utilities;

import classes.Appointment;
import java.util.Comparator;

/**
 * This Java Class processes comparing 2 value and sort it. reference code:
 * https://www.tutorialspoint.com/java/java_string_equalsignorecase.htm
 *
 * @author Evonne Chen Date: 9/5/2018
 */
public class sortDay implements Comparator<Appointment> {

    @Override
    public int compare(Appointment a1, Appointment a2) {
        int ap1 = a1.getDay();
        int ap2 = a2.getDay();
//        int ap3 = a1.getDay();
//        int ap4 = a2.getDay();

        // because compareTo only sort String, if there are 5,12, it will sort it as 12, 5, so add 10 to day if it < 10
        if (ap1 < 10 || ap2 < 10) {
            ap1 += 10;
            ap2 += 10;
        }

        //transfer integer to string because .compareTo can't compare integer
        String StringAp1 = String.valueOf(ap1);
        String StringAp2 = String.valueOf(ap2);

        // ascending order (descending order would be: name2.compareTo(name1))
        return StringAp1.compareTo(StringAp2);
    }
}
