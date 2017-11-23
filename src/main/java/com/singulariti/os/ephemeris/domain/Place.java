package com.singulariti.os.ephemeris.domain;

import com.singulariti.os.ephemeris.utils.FormatUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.TimeZone;

/**
 *
 * @author John
 */
public class Place {

    DecimalFormat df = new DecimalFormat("#.####");
    private String name;
    private double latitude;
    private Pole latitudePole;
    private double longitude;
    private Pole longitudePole;
    private int timeDifferenceGMTMinutes;
    private String daylightSavingsStart;
    private String daylightSavingsEnd;
    private TimeZone timeZone;

    private double latitudeCtorArg;
    private double longitudeCtorArg;

    public Place(String name, String latitude, Pole latitudePole, String longitude, Pole longitudePole,
            TimeZone tz, String daylightSavingsStart, String daylightSavingsEnd) {
        this.name = name;
        this.latitude = convertLatitude(latitude, latitudePole);
        this.latitudePole = latitudePole;
        this.longitude = convertLongitude(longitude, longitudePole);
        this.longitudePole = longitudePole;
        this.timeZone = tz;
        this.timeDifferenceGMTMinutes = timeZone.getRawOffset() / (60 * 1000);
        if (longitudePole == Pole.EAST) {
            this.timeDifferenceGMTMinutes *= -1;
        }
        this.daylightSavingsStart = daylightSavingsStart;
        this.daylightSavingsEnd = daylightSavingsEnd;
    }

    final double convertLatitude(String l, Pole p) {
        double col = FormatUtils.parseCol(l);

//        df.setRoundingMode(RoundingMode.HALF_EVEN);
//        BigDecimal lat = new BigDecimal(df.format(col));
//        col = lat.doubleValue();
        return setLatitudeSign(col, p);
    }

    final double setLatitudeSign(double l, Pole p) {
        if (p.getVal() == 1) {
            l = -1 * l;
        }

        return l;
    }

    final double convertLongitude(String l, Pole p) {
        double col = FormatUtils.parseCol(l);
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        BigDecimal lat = new BigDecimal(df.format(col));
        col = lat.doubleValue();

        return setLongitudeSign(col, p);
    }

    final double setLongitudeSign(double l, Pole p) {
        if (p.getVal() == 1) {
            l = -1 * l;
        }

        return l;
    }

    public Place(String name, double latitude, Pole latitudePole, double longitude, Pole longitudePole,
            TimeZone tz, String daylightSavingsStart, String daylightSavingsEnd) {
        this.name = name;
        this.latitudePole = latitudePole;
        this.latitudeCtorArg = latitude;
        this.latitude = setLatitudeSign(latitude, latitudePole);
        this.longitudePole = longitudePole;
        this.longitudeCtorArg = longitude;
        this.longitude = setLongitudeSign(longitude, longitudePole);
        this.timeZone = tz;
        this.timeDifferenceGMTMinutes = timeZone.getRawOffset() / (60 * 1000);
        if (longitudePole == Pole.EAST) {
            this.timeDifferenceGMTMinutes *= -1;
        }
        this.daylightSavingsStart = daylightSavingsStart;
        this.daylightSavingsEnd = daylightSavingsEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Pole getLatitudePole() {
        return latitudePole;
    }

    public void setLatitudePole(Pole latitudePole) {
        this.latitudePole = latitudePole;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Pole getLongitudePole() {
        return longitudePole;
    }

    public void setLongitudePole(Pole longitudePole) {
        this.longitudePole = longitudePole;
    }

    public int getTimeDifferenceGMTMinutes() {
        return timeDifferenceGMTMinutes;
    }

    public void setTimeDifferenceGMTMinutes(int timeDifferenceGMTMinutes) {
        this.timeDifferenceGMTMinutes = timeDifferenceGMTMinutes;
    }

    public String getDaylightSavingsStart() {
        return daylightSavingsStart;
    }

    public void setDaylightSavingsStart(String daylightSavingsStart) {
        this.daylightSavingsStart = daylightSavingsStart;
    }

    public String getDaylightSavingsEnd() {
        return daylightSavingsEnd;
    }

    public void setDaylightSavingsEnd(String daylightSavingsEnd) {
        this.daylightSavingsEnd = daylightSavingsEnd;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Place copy() {
        return new Place(name, latitudeCtorArg, latitudePole, longitudeCtorArg, longitudePole, timeZone, daylightSavingsStart, daylightSavingsEnd);
    }

}
