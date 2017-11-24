/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singulariti.os.ephemeris.domain;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author John
 */
public class SunPosition {

    private String siteName;
    private ZonedDateTime date;
    private String ra;
    private String dec;
    private String altitude;
    private String azimuth;
    private String earthDistance;
    private String riseTime;
    private String setTime;
    private String civilDawnTime;
    private String civilDuskTime;
    private String nauticalDawnTime;
    private String nauticalDuskTime;
    private String astronomicalDawnTime;
    private String astronomicalDuskTime;
    private String dstCorrected;

    public SunPosition() {
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public String getEarthDistance() {
        return earthDistance;
    }

    public void setEarthDistance(String earthDistance) {
        this.earthDistance = earthDistance;
    }

    public String getRiseTime() {
        return riseTime;
    }

    public void setRiseTime(String riseTime) {
        this.riseTime = riseTime;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public String getCivilDawnTime() {
        return civilDawnTime;
    }

    public void setCivilDawnTime(String civilDawnTime) {
        this.civilDawnTime = civilDawnTime;
    }

    public String getCivilDuskTime() {
        return civilDuskTime;
    }

    public void setCivilDuskTime(String civilDuskTime) {
        this.civilDuskTime = civilDuskTime;
    }

    public String getNauticalDawnTime() {
        return nauticalDawnTime;
    }

    public void setNauticalDawnTime(String nauticalDawnTime) {
        this.nauticalDawnTime = nauticalDawnTime;
    }

    public String getNauticalDuskTime() {
        return nauticalDuskTime;
    }

    public void setNauticalDuskTime(String nauticalDuskTime) {
        this.nauticalDuskTime = nauticalDuskTime;
    }

    public String getAstronomicalDawnTime() {
        return astronomicalDawnTime;
    }

    public void setAstronomicalDawnTime(String astronomicalDawnTime) {
        this.astronomicalDawnTime = astronomicalDawnTime;
    }

    public String getAstronomicalDuskTime() {
        return astronomicalDuskTime;
    }

    public void setAstronomicalDuskTime(String astronomicalDuskTime) {
        this.astronomicalDuskTime = astronomicalDuskTime;
    }

    public String getDstCorrected() {
        return dstCorrected;
    }

    public void setDstCorrected(String dstCorrected) {
        this.dstCorrected = dstCorrected;
    }

    @Override
    public String toString() {
        String d = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(date);
        //date ra dec alt az earthdist risetime settime civildawn civildusk nautdawn nautdusk astdawn astdusk
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                d, ra, dec, altitude, azimuth, earthDistance, riseTime, setTime, civilDawnTime, civilDuskTime, nauticalDawnTime, nauticalDuskTime, astronomicalDawnTime, astronomicalDuskTime);
        return str;
    }

    public static String header() {
        String str = String.format("%20s | %15s | %10s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s | %15s |",
                "Date", "RA", "DEC", "Altitude", "Azimuth", "Earth Distance", "Rise", "Set", "Civil Rise", "Civil Set", "Naut Rise", "Naut Set", "Astr Rise", "Astr Set");
        return str;
    }

}
