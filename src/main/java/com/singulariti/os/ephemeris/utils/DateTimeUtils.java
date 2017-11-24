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
package com.singulariti.os.ephemeris.utils;

import com.singulariti.os.ephemeris.domain.Observatory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author John
 */
public class DateTimeUtils {

    public static final ArrayList<Integer> MONTH_LENGTHS = new ArrayList<>();

    static {
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(28);
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(30);
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(30);
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(30);
        MONTH_LENGTHS.add(31);
        MONTH_LENGTHS.add(30);
        MONTH_LENGTHS.add(31);
    }

    private DateTimeUtils() {
    }

    public static int getFullYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static boolean isLeapYear(int year) {
        boolean leap = false;
        if (year % 4 == 0) {
            leap = true;
        }

        if (year % 100 == 0) {
            leap = false;
        }

        if (year % 400 == 0) {
            leap = true;
        }

        return leap;
    }

    public static boolean localdst() {
        return TimeZone.getDefault().inDaylightTime(new Date());
    }

    public static double jd(Observatory obs) {
        double jd0 = jd0(obs.getFullYear(), obs.getMonth(), obs.getDate());
        jd0 += (obs.getHours() + ((obs.getMinutes() + obs.getTimeDifferenceGMTMinutes()) / 60.0) + (obs.getSeconds() / 3600.0)) / 24;
        return jd0;
    }

    public static double jd0(int year, int month, int day) {
        int y = year;
        int m = month;
        if (m < 3) {
            m += 12;
            y -= 1;
        }

        double a = Math.floor(y / 100);
        double b = 2 - a + Math.floor(a / 4);
        double j = Math.floor(365.25 * (y + 4716)) + Math.floor(30.6001 * (m + 1)) + day + b - 1524.5;
        return j;
    }

    public static int[] jdtocd(double jd) {
        int[] result = new int[7];
        double Z = Math.floor(jd + 0.5);
        double F = jd + 0.5 - Z;
        double A;
        if (Z < 2299161) {
            A = Z;
        } else {
            double alpha = Math.floor((Z - 1867216.25) / 36524.25);
            A = Z + 1 + alpha - Math.floor(alpha / 4);
        }

        double B = A + 1524;
        double C = Math.floor((B - 122.1) / 365.25);
        double D = Math.floor(365.25 * C);
        double E = Math.floor((B - D) / 30.6001);
        double d = B - D - Math.floor(30.6001 * E) + F;
        double month;
        if (E < 14) {
            month = E - 1;
        } else {
            month = E - 13;
        }

        double year;
        if (month > 2) {
            year = C - 4716;
        } else {
            year = C - 4715;
        }

        double day = Math.floor(d);
        double h = (d - day) * 24;
        double hours = Math.floor(h);
        double m = (h - hours) * 60;
        double minutes = Math.floor(m);
        double seconds = Math.round((m - minutes) * 60);
        if (seconds >= 60) {
            minutes = minutes + 1;
            seconds = seconds - 60;
        }

        if (minutes >= 60) {
            hours = hours + 1;
            minutes = 0;
        }

        double dw = Math.floor(jd + 1.5) - 7 * Math.floor((jd + 1.5) / 7);

        result[0] = MathUtils.intr(year);
        result[1] = MathUtils.intr(month);
        result[2] = MathUtils.intr(day);
        result[3] = MathUtils.intr(dw);
        result[4] = MathUtils.intr(hours);
        result[5] = MathUtils.intr(minutes);
        result[6] = MathUtils.intr(seconds);

        return result;
    }

    public static double g_sidereal(int year, int month, int day) {
        double T = (jd0(year, month, day) - 2451545.0) / 36525;
        double res = 100.46061837 + T * (36000.770053608 + T * (0.000387933 - T / 38710000.0));
        return MathUtils.rev(res) / 15.0;
    }

    public static int checkdst(Observatory obs, Date date) {
        TimeZone tz = obs.getLocation().getTimeZone();
        if (tz.inDaylightTime(date)) {
            return -60;
        }
        return 0;
    }

    public static double local_sidereal(Observatory obs) {
        double res = g_sidereal(obs.getFullYear(), obs.getMonth(), obs.getDate());
        res += 1.00273790935 * (obs.getHours() + (obs.getMinutes() + obs.getTimeDifferenceGMTMinutes() + (obs.getSeconds() / 60.0)) / 60.0);
        res -= obs.getLongitude() / 15.0;

        while (res < 0) {
            res += 24.0;
        }

        while (res > 24) {
            res -= 24.0;
        }

        return res;
    }

}
