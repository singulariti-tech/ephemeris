package com.singulariti.os.ephemeris.domain;

import java.time.ZonedDateTime;

/**
 *
 * @author John
 */
public class Observatory {


    private final Place location;
    private ZonedDateTime currentTime;

    public Observatory(Place location, ZonedDateTime currentTime) {
        this.location = location;
        this.currentTime = currentTime;
    }

    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(ZonedDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public Place getLocation() {
        return location;
    }

    public String getName() {
        return this.location.getName();
    }

    public int getFullYear() {
        return currentTime.getYear();
    }

    public int getMonth() {
        return currentTime.getMonthValue();
    }

    public int getDate() {
        return currentTime.getDayOfMonth();
    }

    public int getHours() {
        return currentTime.getHour();
    }

    public int getMinutes() {
        return currentTime.getMinute();
    }

    public int getSeconds() {
        return currentTime.getSecond();
    }

    public int getTimeDifferenceGMTMinutes() {
        return this.location.getTimeDifferenceGMTMinutes();
    }

    public double getLatitude() {
        return this.location.getLatitude();
    }

    public double getLongitude() {
        return this.location.getLongitude();
    }

    public Observatory copy() {
        Place placeCpy = this.location.copy();
        Observatory obs = new Observatory(placeCpy, currentTime);
        return obs;
    }

}
