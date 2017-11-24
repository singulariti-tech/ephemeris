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
package com.singulariti.os.ephemeris;

import com.singulariti.os.ephemeris.domain.Coord3D;
import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.Planet;
import com.singulariti.os.ephemeris.domain.RiseSetTimes;
import com.singulariti.os.ephemeris.domain.SunPosition;
import com.singulariti.os.ephemeris.utils.DateTimeUtils;
import com.singulariti.os.ephemeris.utils.FormatUtils;
import com.singulariti.os.ephemeris.utils.MathUtils;
import com.singulariti.os.ephemeris.utils.PlanetCatalog;
import static com.singulariti.os.ephemeris.utils.DateTimeUtils.jd;
import static com.singulariti.os.ephemeris.utils.FormatUtils.hmstring;
import static com.singulariti.os.ephemeris.utils.MathUtils.cosd;
import static com.singulariti.os.ephemeris.utils.MathUtils.rev;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author John
 */
public class SunPositionCalculator {

    public SunPositionCalculator() {
    }

    // Nutation in longitude and obliquity, returns seconds
    public List<Double> nutation(Observatory obs) {
        double T = (DateTimeUtils.jd(obs) - 2451545.0) / 36525.0;
//        double T2 = T * T;
//        double T3 = T2 * T;
        double omega = MathUtils.rev(125.04452 - 1934.136261 * T);
        double L = MathUtils.rev(280.4665 + 36000.7698 * T);
        double LP = MathUtils.rev(218.3165 + 481267.8813 * T);
        double deltaL = -17.20 * MathUtils.sind(omega) - 1.32 * MathUtils.sind(2 * L) - 0.23 * MathUtils.sind(2 * LP) + 0.21 * MathUtils.sind(2 * omega);
        double deltaO = 9.20 * MathUtils.cosd(omega) + 0.57 * MathUtils.cosd(2 * L) + 0.10 * MathUtils.cosd(2 * LP) - 0.09 * MathUtils.cosd(2 * omega);
        return new ArrayList<Double>() {
            {
                add(deltaL);
                add(deltaO);
            }
        };
    }

    // Obliquity of ecliptic
    public double obl_eql(Observatory obs) {
        double T = (DateTimeUtils.jd(obs) - 2451545.0) / 36525;
        double T2 = T * T;
        double T3 = T2 * T;
        double e0 = 23.0 + (26.0 / 60.0) + (21.448 - 46.8150 * T - 0.00059 * T2 + 0.001813 * T3) / 3600.0;
        List<Double> nut = nutation(obs);
        double e = e0 + nut.get(1) / 3600.0;
        return e;
    }

    public double earth_ecc(Observatory obs) {
        double T = (DateTimeUtils.jd(obs) - 2451545.0) / 36525;
        double T2 = T * T;
//        double T3 = T2 * T;
        double e = 0.016708617 - 0.000042037 * T - 0.0000001236 * T2;
        return e;
    }

    public double EoT(Observatory obs, Planet earth) {
        Coord3D sun_xyz = new Coord3D(0.0, 0.0, 0.0);
        Coord3D earth_xyz = PlanetPositionCalculator.helios(earth, obs);
        List<Double> radec = PlanetPositionCalculator.radecr(sun_xyz, earth_xyz, obs);
        double T = (jd(obs) - 2451545.0) / 365250;
        double T2 = T * T;
        double T3 = T2 * T;
        double T4 = T3 * T;
        double T5 = T4 * T;
        double L0 = rev(280.4664567 + 360007.6982779 * T + 0.03032028 * T2
                + T3 / 49931.0 - T4 / 15299.0 - T5 / 1988000.0);
        List<Double> nut = nutation(obs);
        double delta = nut.get(0) / 3600.0;
        double e = obl_eql(obs);
        double E = 4 * (L0 - 0.0057183 - (radec.get(0) * 15.0) + delta * cosd(e));
        while (E < -1440) {
            E += 1440;
        }

        while (E > 1440) {
            E -= 1440;
        }

        return E;
    }

    public RiseSetTimes sunrise(Observatory obs, double twilight, Planet earth) {
        Observatory obscopy = obs.copy();
        RiseSetTimes riseset = new RiseSetTimes("", "");
        ZonedDateTime obsDT = obscopy.getCurrentTime();
        obscopy.setCurrentTime(obsDT.withHour(12).withMinute(0).withSecond(0).withNano(0));
        double lst = DateTimeUtils.local_sidereal(obscopy);
        Coord3D earth_xyz = PlanetPositionCalculator.helios(earth, obscopy);
        Coord3D sun_xyz = new Coord3D(0.0, 0.0, 0.0);
        List<Double> radec = PlanetPositionCalculator.radecr(sun_xyz, earth_xyz, obscopy);
        double UTsun = 12.0 + radec.get(0) - lst;
        if (UTsun < 0.0) {
            UTsun += 24.0;
        }

        if (UTsun > 24.0) {
            UTsun -= 24.0;
        }

        double cosLHA = (MathUtils.sind(twilight) - MathUtils.sind(obs.getLatitude()) * MathUtils.sind(radec.get(1)))
                / (MathUtils.cosd(obs.getLatitude()) * MathUtils.cosd(radec.get(1)));
        // Check for midnight sun and midday night.
        if (cosLHA > 1.0) {
            riseset.setRise("----");
            riseset.setSet("----");
        } else if (cosLHA < -1.0) {
            riseset.setRise("++++");
            riseset.setSet("++++");
        } else {
            // rise/set times allowing for not today.
            double lha = MathUtils.acosd(cosLHA) / 15.0;
            if ((UTsun - lha) < 0.0) {
                riseset.setRise(hmstring(24.0 + UTsun - lha));
            } else {
                riseset.setRise(hmstring(UTsun - lha));
            }
            if ((UTsun + lha) > 24.0) {
                riseset.setSet(hmstring(UTsun + lha - 24.0));
            } else {
                riseset.setSet(hmstring(UTsun + lha));
            }
        }

        return riseset;
    }

    public List<SunPosition> getPosition(Observatory obs, ZonedDateTime startDate, ZonedDateTime endDate,
            int intervalMinutes) {
        List<SunPosition> ephemerides = new ArrayList<>();
        ZonedDateTime currentTime = startDate;
        while (currentTime.isBefore(endDate)) {
            obs.setCurrentTime(currentTime);
            SunPosition eph = getEphemeride(obs);
            ephemerides.add(eph);

            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        obs.setCurrentTime(endDate);
        SunPosition eph = getEphemeride(obs);
        ephemerides.add(eph);

        return ephemerides;
    }

    public SunPosition getEphemeride(Observatory obs) {
        String siteName = FormatUtils.sitename(obs);
        Planet earth = PlanetCatalog.byName("Earth");

        Coord3D sun_xyz = new Coord3D(0.0, 0.0, 0.0);
        Coord3D earth_xyz = PlanetPositionCalculator.helios(earth, obs);
        List<Double> radec = PlanetPositionCalculator.radecr(sun_xyz, earth_xyz, obs);
        double[] altaz = MathUtils.radtoaa(radec.get(0), radec.get(1), obs);

        String ra = FormatUtils.hmdstring(radec.get(0));
        String dec = FormatUtils.anglestring(radec.get(1), false);
        String altitude = FormatUtils.anglestring(altaz[0], false);
        String azimuth = FormatUtils.anglestring(altaz[1], true);
        String earthDist = String.valueOf(Math.round(radec.get(2) * 1000.0) / 1000.0);

        RiseSetTimes riseSetTimes = sunrise(obs, -0.833, earth);
        RiseSetTimes civilRiseSetTimes = sunrise(obs, -6.0, earth);
        RiseSetTimes nauticalRiseSetTimes = sunrise(obs, -12.0, earth);
        RiseSetTimes astronomicalRiseSetTimes = sunrise(obs, -18.0, earth);

        SunPosition eph = new SunPosition();
        eph.setSiteName(siteName);
        eph.setDate(obs.getCurrentTime());
        eph.setRa(ra);
        eph.setDec(dec);
        eph.setAltitude(altitude);
        eph.setAzimuth(azimuth);
        eph.setEarthDistance(earthDist);
        eph.setRiseTime(riseSetTimes.getRise());
        eph.setSetTime(riseSetTimes.getSet());
        eph.setCivilDawnTime(civilRiseSetTimes.getRise());
        eph.setCivilDuskTime(civilRiseSetTimes.getSet());
        eph.setNauticalDawnTime(nauticalRiseSetTimes.getRise());
        eph.setNauticalDuskTime(nauticalRiseSetTimes.getSet());
        eph.setAstronomicalDawnTime(astronomicalRiseSetTimes.getRise());
        eph.setAstronomicalDuskTime(astronomicalRiseSetTimes.getSet());

        return eph;
    }

}
