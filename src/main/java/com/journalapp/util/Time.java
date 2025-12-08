package main.java.com.journalapp.util;

import java.time.LocalTime;

public class Time {
    /**
     * Return a string representing general time of day
     * based on the current system time.
     * @return "morning", "afternoon", or "evening"
     */
    public static String getPeriodOfDay() {
        LocalTime localTime = LocalTime.now();
        int hour = localTime.getHour();
        if (hour >= 5 && hour < 12)
            return "morning";
        else if (hour >= 12 && hour < 18)
            return "afternoon";
        return "evening";
    }
    /**
     * Demonstrate the usage of getPeriodOfDay method.
     */
    public static void main(String[] args) {
        System.out.println(getPeriodOfDay());
    }
}
