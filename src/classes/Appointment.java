package classes;


/**
 * This Java Class includes constructor, getter and setter and toString.
 * @author Evonne Chen
 * Date: 8/5/2018
 */
public class Appointment {
    private int day;
    private int hour;
    private String name;

    public Appointment(int day, int hour, String name) {
        this.day = day;
        this.hour = hour;
        this.name = name;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + day + "  " + hour;
    }
    
    
    
    
}
     