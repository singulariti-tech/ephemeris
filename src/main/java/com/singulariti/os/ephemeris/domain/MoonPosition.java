package com.singulariti.os.ephemeris.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author John
 */
public class MoonPosition {

    private String siteName;
    private ZonedDateTime date;
    private String ra;
    private String dec;
    private String altitude;
    private String azimuth;
    private String distance;
    private String phaseAngle;
    private String illuminatedPercentage;
    private String riseTime;
    private String setTime;

    public MoonPosition() {
    }

    public String getSiteName() {
        return siteName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getRa() {
        return ra;
    }

    public String getDec() {
        return dec;
    }

    public String getAltitude() {
        return altitude;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public String getDistance() {
        return distance;
    }

    public String getPhaseAngle() {
        return phaseAngle;
    }

    public String getIlluminatedPercentage() {
        return illuminatedPercentage;
    }

    public String getRiseTime() {
        return riseTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setPhaseAngle(String phaseAngle) {
        this.phaseAngle = phaseAngle;
    }

    public void setIlluminatedPercentage(String illuminatedPercentage) {
        this.illuminatedPercentage = illuminatedPercentage;
    }

    public void setRiseTime(String riseTime) {
        this.riseTime = riseTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    @Override
    public String toString() {
        String d = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(date);
        //date ra dec alt az earthdist risetime settime civildawn civildusk nautdawn nautdusk astdawn astdusk
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                d, ra, dec, altitude, azimuth, distance, phaseAngle, illuminatedPercentage, riseTime, setTime);
        return str;
    }

    public static String header() {
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                "Date", "RA", "DEC", "Altitude", "Azimuth", "Distance (kM)", "Phase Angle", "Illuminated %", "Rise", "Set");
        return str;
    }

}
