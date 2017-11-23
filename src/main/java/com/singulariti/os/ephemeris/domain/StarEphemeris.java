package com.singulariti.os.ephemeris.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author John
 */
public class StarEphemeris {

    private String siteName;
    private ZonedDateTime date;
    private String constellation;
    private String star;
    private String name;
    private String ra;
    private String dec;
    private String altitude;
    private String azimuth;
    private String mg;
    private String type;
    private String spectralClass;

    public StarEphemeris() {
    }

    public String getSiteName() {
        return siteName;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getConstellation() {
        return constellation;
    }

    public String getStar() {
        return star;
    }

    public String getName() {
        return name;
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

    public String getMg() {
        return mg;
    }

    public String getType() {
        return type;
    }

    public String getSpectralClass() {
        return spectralClass;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setMg(String mg) {
        this.mg = mg;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSpectralClass(String spectralClass) {
        this.spectralClass = spectralClass;
    }

    @Override
    public String toString() {
        String d = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(date);
        //date ra dec alt az earthdist risetime settime civildawn civildusk nautdawn nautdusk astdawn astdusk
        String str = String.format("%20s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                d, star, constellation, name, ra, dec, altitude, azimuth, mg, type, spectralClass);
        return str;
    }

    public static String header() {
        String str = String.format("%20s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                "Date", "Star", "Constellation", "Name", "RA", "DEC", "Altitude", "Azimuth", "Mg", "Type", "Spectral Class");
        return str;
    }

}
