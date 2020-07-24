package de.lars;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ParameterHolder {

    public String startStation;
    public String destinationStation;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;

    public ParameterHolder(String startStation, String destinationStation, int day, int month, int year, int hour, int minute) {
        this.startStation = startStation;
        this.destinationStation = destinationStation;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public ParameterHolder() {
    }

    /**
     * Encode String values as url query parameters
     */
    public String urlEncode(String in) {
        try {
            return URLEncoder.encode(in, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("Error while encoding string value: " + e.getMessage());
        }
        return in;
    }

    public String getStartStationEncoded() {
        return urlEncode(startStation);
    }

    public String getDestinationStationEncoded() {
        return urlEncode(destinationStation);
    }

}
