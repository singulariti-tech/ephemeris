
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
import java.util.Date;

/**
 *
 * @author John
 */
public class FormatUtils {

    private FormatUtils() {
    }

    public static String datestring(Observatory obs) {
        String dateStr = "";
        dateStr += obs.getFullYear();
        dateStr += ((obs.getMonth() < 10) ? ":0" : ":") + obs.getMonth();
        dateStr += ((obs.getDate() < 10) ? ":0" : ":") + obs.getDate();
        return dateStr;
    }

    public static String UTString(Observatory obs) {
        int hours = obs.getHours();
        int minutes = obs.getMinutes();
        int seconds = obs.getSeconds();
        minutes += obs.getTimeDifferenceGMTMinutes();
        hours += Math.floor(minutes / 60.0);

        int min = MathUtils.intr(minutes - Math.floor(minutes / 60.0) * 60);

        while (hours > 24) {
            hours -= 24;
        }

        while (hours < 0) {
            hours += 24;
        }

        String timestr = ((hours < 10) ? "0" : "") + hours;
        timestr += ((min < 10) ? ":0" : ":") + min;
        timestr += ((seconds < 10) ? ":0" : ":") + seconds;
        return timestr;
    }

    public static String timestring(Observatory obs, boolean local) {
        String timeStr = "";
        double hours = obs.getHours();
        double minutes = obs.getMinutes();
        double seconds = obs.getSeconds();

        if (local) {
            // Correct for zone time including DST
            minutes += obs.getTimeDifferenceGMTMinutes();
            // correct for longitude to nearest second
            seconds = Math.round(seconds - 240 * obs.getLongitude());
            while (seconds < 0) {
                seconds += 60;
                minutes -= 1;
            }

            while (seconds >= 60) {
                seconds -= 60;
                minutes += 1;
            }

            // Put the daylight saving correction back
            minutes -= DateTimeUtils.checkdst(obs, new Date());
            while (minutes < 0) {
                minutes += 60;
                hours -= 1;
            }

            while (minutes >= 60) {
                minutes -= 60;
                hours += 1;
            }

            while (hours > 24) {
                hours -= 24;
            }

            while (hours < 0) {
                hours += 24;
            }
        }

        timeStr = ((hours < 10) ? "0" : "") + MathUtils.intr(hours);
        timeStr += ((minutes < 10) ? ":0" : ":") + MathUtils.intr(minutes);
        timeStr += ((seconds < 10) ? ":0" : ":") + MathUtils.intr(seconds);

        return timeStr;
    }

    public static String hmsstring(double t) {
        double hours = Math.abs(t);
        double minutes = 60.0 * (hours - Math.floor(hours));
        hours = Math.floor(hours);
        double seconds = Math.round(60.0 * (minutes - Math.floor(minutes)));
        minutes = Math.floor(minutes);
        if (seconds >= 60) {
            minutes += 1;
            seconds -= 60;
        }

        if (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }

        if (hours >= 24) {
            hours -= 24;
        }

        String hmsstr = (t < 0) ? "-" : "";

        int hrsFloor = (int) hours;
        int minFloor = (int) minutes;
        int secFloor = (int) seconds;

        hmsstr += ((hours < 10) ? "0" : "") + hrsFloor;
        hmsstr += ((minutes < 10) ? ":0" : ":") + minFloor;
        hmsstr += ((seconds < 10) ? ":0" : ":") + secFloor;
        return hmsstr;
    }

    public static String hmstring(double t) {
        double hours = Math.abs(t);
        double minutes = Math.round(60.0 * (hours - Math.floor(hours)));
        hours = Math.floor(hours);
        if (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }

        if (hours >= 24) {
            hours -= 24;
        }

        String hmstr = (t < 0) ? "-" : "";

        int hrsFloor = (int) hours;
        int minFloor = (int) minutes;
        hmstr += ((hours < 10) ? "0" : "") + hrsFloor;
        hmstr += ((minutes < 10) ? ":0" : ":") + minFloor;
        return hmstr;
    }

    /**
     * hmdstring returns hours minutes as hh:mm.m used to print RA
     *
     * @param t
     * @return
     */
    public static String hmdstring(double t) {
        double hours = Math.abs(t);
        double minutes = 60.0 * (hours - Math.floor(hours));
        hours = Math.floor(hours);
        if (minutes >= 60) {
            hours += 1;
            minutes -= 60;
        }

        if (hours >= 24) {
            hours -= 24;
        }

        String hmstr = (t < 0) ? "-" : "";
        int hrsFloor = (int) hours;
        hmstr += ((hours < 10) ? "0" : "") + hrsFloor;

        int minFloor = (int) Math.floor(minutes);
        hmstr += (minFloor < 10) ? ":0" : ":" + minFloor;
        int secFloor = (int) Math.floor(10 * (minutes - minFloor));
        hmstr += "." + secFloor;
        return hmstr;
    }

    /**
     * llstring returns latitude or longitude as degrees:minutes:seconds without sign
     *
     * @param a
     * @return
     */
    public static String llstring(double a) {
        double deg = Math.abs(a);
        double min = (60.0 * (deg - Math.floor(deg)));
        double sec = Math.floor(60.0 * (min - Math.floor(min)));
        String dmsstr = "";
        deg = Math.floor(deg);
        min = Math.floor(min);
        sec = Math.floor(sec);

        int degFloor = (int) deg;
        int minFloor = (int) min;
        int secFloor = (int) sec;

        dmsstr += ((deg < 10) ? "0" : "") + degFloor;
        dmsstr += ((min < 10) ? ":0" : ":") + minFloor;
        dmsstr += ((sec < 10) ? ":0" : ":") + secFloor;
        return dmsstr;
    }

    /**
     * anglestring return angle as degrees:minutes circle is true for range between 0 and 360 and false for -90 to +90
     *
     * @param a
     * @param circle
     * @return
     */
    public static String anglestring(double a, boolean circle) {
        double ar = Math.round(a * 60);
        ar = ar / 60;
        double deg = Math.abs(ar);
        double min = Math.round(60.0 * (deg - Math.floor(deg)));
        if (min >= 60) {
            deg += 1;
            min = 0;
        }

        String anglestr = "";
        if (!circle) {
            anglestr += (ar < 0 ? "-" : "+");
        }

        int degFloor = (int) Math.floor(deg);
        int minFloor = (int) min;
        if (circle) {
            anglestr += (degFloor < 100) ? "0" : "";
        }

        anglestr += ((degFloor < 10) ? "0" : "") + degFloor;
        anglestr += ((minFloor < 10) ? ":0" : ":") + (minFloor);
        return anglestr;
    }

    /**
     * parseCol converts deg:min:sec or hr:min:sec to a number
     *
     * @param str
     * @return
     */
    public static double parseCol(String str) {
        double val = 0;
        boolean colonExists = str.contains(":");
        if (colonExists) {
            String[] split = str.split(":");
            int noSplits = split.length;
            switch (noSplits) {
                case 1:
                    val = Double.valueOf(str);
                    break;
                case 2: {
                    String first = split[0];
                    String second = split[1];
                    val = Double.valueOf(first);
                    val += (int) (Double.valueOf(second) / 60.0);
                    break;
                }
                case 3: {
                    String first = split[0];
                    String second = split[1];
                    String third = split[2];
                    val = Double.valueOf(first);
                    val += Double.valueOf(second) / 60.0;
                    val += Double.valueOf(third) / 3600.0;
                    break;
                }
                default:
                    break;
            }
        } else {
            val = Integer.parseInt(str);
        }

        return val;
    }

    public static String sitename(Observatory obs) {
        String sname = obs.getName();
        double latd = Math.abs(obs.getLatitude());
        int latdi = MathUtils.intr(Math.floor(latd));
        sname += ((latdi < 10) ? " 0" : " ") + latdi;

        double latm = 60 * (latd - latdi);
        int latmi = MathUtils.intr(Math.floor(latm));
        sname += ((latmi < 10) ? ":0" : ":") + latmi;

        double lats = 60 * (latm - latmi);
        int latsi = MathUtils.intr(Math.floor(lats));
        sname += ((latsi < 10) ? ":0" : ":") + latsi;
        sname += ((obs.getLatitude() >= 0) ? "N " : "S ");

        double longd = Math.abs(obs.getLongitude());
        int longdi = MathUtils.intr(Math.floor(longd));
        sname += ((longdi < 10) ? "0" : "") + longdi;

        double longm = 60 * (longd - longdi);
        int longmi = MathUtils.intr(Math.floor(longm));
        sname += ((longmi < 10) ? ":0" : ":") + longmi;

        double longs = 60 * (longm - longmi);
        int longsi = MathUtils.intr(Math.floor(longs));
        sname += ((longsi < 10) ? ":0" : ":") + longsi;

        sname += ((obs.getLongitude() >= 0) ? "W" : "E");
        return sname;
    }
}
