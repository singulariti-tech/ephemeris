package com.singulariti.os.ephemeris.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author John
 */
public class PlanetEphemeris {

    private String name;
    private ZonedDateTime date;
    private String ra;
    private String dec;
    private String altitude;
    private String azimuth;
    private String distance;
    private String rise;
    private String transit;
    private String set;

    public PlanetEphemeris() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getTransit() {
        return transit;
    }

    public void setTransit(String transit) {
        this.transit = transit;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    @Override
    public String toString() {
        String d = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(date);
        //date ra dec alt az earthdist risetime settime civildawn civildusk nautdawn nautdusk astdawn astdusk
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s |",
                d, ra, dec, altitude, azimuth, distance, rise, transit, set);
        return str;
    }

    public static String header(){
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s |",
                "Date", "RA", "DEC", "Altitude", "Azimuth", "Distance", "Rise", "Transit", "Set");
        return str;
    }

}
