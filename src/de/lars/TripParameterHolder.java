package de.lars;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * used for a TRIPSTOPTIMES request
 */
public class TripParameterHolder {

    public int tripCode;
    public int stopID;
    public int lastStopID;
    public int day;
    public int month;
    public int year;
    public int hour;
    public int minute;
    public String line;

    public TripParameterHolder(int tripCode, int stopID, int lastStopID, int day, int month, int year, int hour, int minute, String line) {
        this.tripCode = tripCode;
        this.stopID = stopID;
        this.lastStopID = lastStopID;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.line = line;
    }

    public TripParameterHolder() {
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

    public String getLineEncoded() {
        return urlEncode(line);
    }

}
