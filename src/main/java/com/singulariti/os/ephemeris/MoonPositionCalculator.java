package com.singulariti.os.ephemeris;

import com.singulariti.os.ephemeris.domain.MoonPosition;
import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.RiseSetTimes;
import com.singulariti.os.ephemeris.utils.DateTimeUtils;
import com.singulariti.os.ephemeris.utils.FormatUtils;
import com.singulariti.os.ephemeris.utils.MathUtils;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author John
 */
public class MoonPositionCalculator {

    // Meeus first edition table 45.A Longitude and distance of the moon
    public static final int[] T45AD = new int[]{
        0, 2, 2, 0, 0, 0, 2, 2, 2, 2,
        0, 1, 0, 2, 0, 0, 4, 0, 4, 2,
        2, 1, 1, 2, 2, 4, 2, 0, 2, 2,
        1, 2, 0, 0, 2, 2, 2, 4, 0, 3,
        2, 4, 0, 2, 2, 2, 4, 0, 4, 1,
        2, 0, 1, 3, 4, 2, 0, 1, 2, 2
    };

    public static final int[] T45AM = new int[]{
        0, 0, 0, 0, 1, 0, 0, -1, 0, -1,
        1, 0, 1, 0, 0, 0, 0, 0, 0, 1,
        1, 0, 1, -1, 0, 0, 0, 1, 0, -1,
        0, -2, 1, 2, -2, 0, 0, -1, 0, 0,
        1, -1, 2, 2, 1, -1, 0, 0, -1, 0,
        1, 0, 1, 0, 0, -1, 2, 1, 0, 0
    };

    public static final int[] T45AMP = new int[]{
        1, -1, 0, 2, 0, 0, -2, -1, 1, 0,
        -1, 0, 1, 0, 1, 1, -1, 3, -2, -1,
        0, -1, 0, 1, 2, 0, -3, -2, -1, -2,
        1, 0, 2, 0, -1, 1, 0, -1, 2, -1,
        1, -2, -1, -1, -2, 0, 1, 4, 0, -2,
        0, 2, 1, -2, -3, 2, 1, -1, 3, -1
    };

    public static final int[] T45AF = new int[]{
        0, 0, 0, 0, 0, 2, 0, 0, 0, 0,
        0, 0, 0, -2, 2, -2, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 2, 0,
        0, 0, 0, 0, 0, -2, 2, 0, 2, 0,
        0, 0, 0, 0, 0, -2, 0, 0, 0, 0,
        -2, -2, 0, 0, 0, 0, 0, 0, 0, -2
    };

    public static final int[] T45AL = new int[]{
        6288774, 1274027, 658314, 213618, -185116,
        -114332, 58793, 57066, 53322, 45758,
        -40923, -34720, -30383, 15327, -12528,
        10980, 10675, 10034, 8548, -7888,
        -6766, -5163, 4987, 4036, 3994,
        3861, 3665, -2689, -2602, 2390,
        -2348, 2236, -2120, -2069, 2048,
        -1773, -1595, 1215, -1110, -892,
        -810, 759, -713, -700, 691,
        596, 549, 537, 520, -487,
        -399, -381, 351, -340, 330,
        327, -323, 299, 294, 0
    };

    public static final int[] T45AR = new int[]{
        -20905355, -3699111, -2955968, -569925, 48888,
        -3149, 246158, -152138, -170733, -204586,
        -129620, 108743, 104755, 10321, 0,
        79661, -34782, -23210, -21636, 24208,
        30824, -8379, -16675, -12831, -10445,
        -11650, 14403, -7003, 0, 10056,
        6322, -9884, 5751, 0, -4950,
        4130, 0, -3958, 0, 3258,
        2616, -1897, -2117, 2354, 0,
        0, -1423, -1117, -1571, -1739,
        0, -4421, 0, 0, 0,
        0, 1165, 0, 0, 8752
    };

    // Meeus table 45B latitude of the moon
    public static final int[] T45BD = new int[]{0, 0, 0, 2, 2, 2, 2, 0, 2, 0,
        2, 2, 2, 2, 2, 2, 2, 0, 4, 0,
        0, 0, 1, 0, 0, 0, 1, 0, 4, 4,
        0, 4, 2, 2, 2, 2, 0, 2, 2, 2,
        2, 4, 2, 2, 0, 2, 1, 1, 0, 2,
        1, 2, 0, 4, 4, 1, 4, 1, 4, 2};

    public static final int[] T45BM = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        -1, 0, 0, 1, -1, -1, -1, 1, 0, 1,
        0, 1, 0, 1, 1, 1, 0, 0, 0, 0,
        0, 0, 0, 0, -1, 0, 0, 0, 0, 1,
        1, 0, -1, -2, 0, 1, 1, 1, 1, 1,
        0, -1, 1, 0, -1, 0, 0, 0, -1, -2};

    public static final int[] T45BMP = new int[]{0, 1, 1, 0, -1, -1, 0, 2, 1, 2,
        0, -2, 1, 0, -1, 0, -1, -1, -1, 0,
        0, -1, 0, 1, 1, 0, 0, 3, 0, -1,
        1, -2, 0, 2, 1, -2, 3, 2, -3, -1,
        0, 0, 1, 0, 1, 1, 0, 0, -2, -1,
        1, -2, 2, -2, -1, 1, 1, -1, 0, 0};

    public static final int[] T45BF = new int[]{1, 1, -1, -1, 1, -1, 1, 1, -1, -1,
        -1, -1, 1, -1, 1, 1, -1, -1, -1, 1,
        3, 1, 1, 1, -1, -1, -1, 1, -1, 1,
        -3, 1, -3, -1, -1, 1, -1, 1, -1, 1,
        1, 1, 1, -1, 3, -1, -1, 1, -1, -1,
        1, -1, 1, -1, -1, -1, -1, -1, -1, 1};

    public static final int[] T45BL = new int[]{5128122, 280602, 277693, 173237, 55413,
        46271, 32573, 17198, 9266, 8822,
        8216, 4324, 4200, -3359, 2463,
        2211, 2065, -1870, 1828, -1794,
        -1749, -1565, -1491, -1475, -1410,
        -1344, -1335, 1107, 1021, 833,
        777, 671, 607, 596, 491,
        -451, 439, 422, 421, -366,
        -351, 331, 315, 302, -283,
        -229, 223, 223, -220, -220,
        -185, 181, -177, 176, 166,
        -164, 132, -119, 115, 107};

    // MoonPos calculates the Moon position, based on Meeus chapter 45
    // and the illuminated percentage from Meeus equations 46.4 and 46.1
    //// returns an array containing rise and set times or one of the
    // following codes.
    // -1 rise or set event not found and moon was down at 00:00
    // -2 rise or set event not found and moon was up   at 00:00
    public static List<Double> moonpos(Observatory obs) {
        // julian date
        double jdobs = DateTimeUtils.jd(obs);
        double T = (jdobs - 2451545.0) / 36525;
        double T2 = T * T;
        double T3 = T2 * T;
        double T4 = T3 * T;
        // Moons mean longitude L'
        double LP = 218.3164477 + 481267.88123421 * T - 0.0015786 * T2 + T3 / 538841.0 - T4 / 65194000.0;
        // Moons mean elongation Meeus first edition
        // var D=297.8502042+445267.1115168*T-0.0016300*T2+T3/545868.0-T4/113065000.0;
        // Moons mean elongation Meeus second edition
        double D = 297.8501921 + 445267.1114034 * T - 0.0018819 * T2 + T3 / 545868.0 - T4 / 113065000.0;

        // Moons mean anomaly M' Meeus first edition
        // var MP=134.9634114+477198.8676313*T+0.0089970*T2+T3/69699.0-T4/14712000.0;
        // Moons mean anomaly M' Meeus second edition
        double MP = 134.9633964 + 477198.8675055 * T + 0.0087414 * T2 + T3 / 69699.0 - T4 / 14712000.0;
        // Moons argument of latitude
        double F = 93.2720950 + 483202.0175233 * T - 0.0036539 * T2 - T3 / 3526000.0 + T4 / 863310000.0;
        // Suns mean anomaly
        double M = 357.5291092 + 35999.0502909 * T - 0.0001536 * T2 + T3 / 24490000.0;
        // Additional arguments
        double A1 = 119.75 + 131.849 * T;
        double A2 = 53.09 + 479264.290 * T;
        double A3 = 313.45 + 481266.484 * T;
        double E = 1 - 0.002516 * T - 0.0000074 * T2;
        double E2 = E * E;
        // Sums of periodic terms from table 45.A and 45.B
        double Sl = 0.0;
        double Sr = 0.0;
        for (int i = 0; i < 60; i++) {
            double Eterm = 1;
            if (Math.abs(T45AM[i]) == 1) {
                Eterm = E;
            }
            if (Math.abs(T45AM[i]) == 2) {
                Eterm = E2;
            }
            Sl += T45AL[i] * Eterm * MathUtils.sind(MathUtils.rev(T45AD[i] * D + T45AM[i] * M + T45AMP[i] * MP + T45AF[i] * F));
            Sr += T45AR[i] * Eterm * MathUtils.cosd(MathUtils.rev(T45AD[i] * D + T45AM[i] * M + T45AMP[i] * MP + T45AF[i] * F));
        }

        double Sb = 0.0;
        for (int i = 0; i < 60; i++) {
            double Eterm = 1;
            if (Math.abs(T45BM[i]) == 1) {
                Eterm = E;
            }
            if (Math.abs(T45BM[i]) == 2) {
                Eterm = E2;
            }
            Sb += T45BL[i] * Eterm * MathUtils.sind(MathUtils.rev(T45BD[i] * D + T45BM[i] * M + T45BMP[i] * MP + T45BF[i] * F));
        }

        // Additional additive terms
        Sl = Sl + 3958 * MathUtils.sind(MathUtils.rev(A1)) + 1962 * MathUtils.sind(MathUtils.rev(LP - F)) + 318 * MathUtils.sind(MathUtils.rev(A2));
        Sb = Sb - 2235 * MathUtils.sind(MathUtils.rev(LP)) + 382 * MathUtils.sind(MathUtils.rev(A3)) + 175 * MathUtils.sind(MathUtils.rev(A1 - F))
                + 175 * MathUtils.sind(MathUtils.rev(A1 + F)) + 127 * MathUtils.sind(MathUtils.rev(LP - MP)) - 115 * MathUtils.sind(MathUtils.rev(LP + MP));

        // geocentric longitude, latitude and distance
        double mglong = MathUtils.rev(LP + Sl / 1000000.0);
        double mglat = MathUtils.rev(Sb / 1000000.0);
        if (mglat > 180.0) {
            mglat = mglat - 360;
        }

        double mr = Math.round(385000.56 + Sr / 1000.0);
        // Obliquity of Ecliptic
        double obl = 23.4393 - 3.563E-7 * (jdobs - 2451543.5);
        // RA and dec
        double ra = MathUtils.rev(MathUtils.atan2d(MathUtils.sind(mglong) * MathUtils.cosd(obl) - MathUtils.tand(mglat) * MathUtils.sind(obl),
                MathUtils.cosd(mglong))) / 15.0;
        double dec = MathUtils.rev(MathUtils.asind(MathUtils.sind(mglat) * MathUtils.cosd(obl) + MathUtils.cosd(mglat) * MathUtils.sind(obl) * MathUtils.sind(mglong)));
        if (dec > 180.0) {
            dec = dec - 360;
        }
        // phase angle
        double pa = 180.0 - D - 6.289 * MathUtils.sind(MP) + 2.1 * MathUtils.sind(M) - 1.274 * MathUtils.sind(2 * D - MP)
                - 0.658 * MathUtils.sind(2 * D) - 0.214 * MathUtils.sind(2 * MP) - 0.11 * MathUtils.sind(D);
        // Altitude and azimuth
        double[] altaz = MathUtils.radtoaa(ra, dec, obs);
        return Arrays.asList(ra, dec, mr, altaz[0], altaz[1], MathUtils.rev(pa));
    }

    public RiseSetTimes moonrise(Observatory obs) {
        Observatory obsReset = obs.copy();
        obsReset.setCurrentTime(obs.getCurrentTime().withHour(0).withMinute(0).withSecond(0).withNano(0));

        RiseSetTimes times = new RiseSetTimes();
        // elh is the elevation at the hour elhdone is true if elh calculated
        List<Double> elh = new ArrayList<>(25);
        List<Boolean> elhDone = new ArrayList<>(25);
        List<Double> moontab = moonpos(obsReset);
        for (int i = 0; i < 25; i++) {
            elh.add(0.0);
            elhDone.add(false);
        }

        elh.set(0, moontab.get(3));
        elhDone.set(0, true);
        // set the return code to allow for always up or never rises
        if (elh.get(0) >= 0.0) {
            times = new RiseSetTimes("-2", "-2");
        } else {
            times = new RiseSetTimes("-1", "-1");
        }

        ZonedDateTime tm = obsReset.getCurrentTime();
        obsReset.setCurrentTime(tm.withHour(0).withDayOfMonth(tm.getDayOfMonth() + 1));
        moontab = moonpos(obsReset);
        elh.set(24, moontab.get(3));
        elhDone.set(24, true);

        for (int rise = 0; rise < 2; rise++) {
            boolean found = false;
            int hfirst = 0;
            int hlast = 24;
            obsReset.setCurrentTime(tm.withMinute(0).withSecond(0));
            // Try a binary chop on the hours to speed the search
            while (Math.ceil((hlast - hfirst) / 2.0) > 1) {
                int hmid = (int) (hfirst + Math.round((hlast - hfirst) / 2.0));
                System.out.printf("rise: %s, hfirst: %s, hlast: %s\n", rise, hfirst, hlast);
                if (!elhDone.get(hmid)) {
                    System.out.printf("Set: %s\n", hmid);
                    obsReset.setCurrentTime(obsReset.getCurrentTime().withHour(hmid));
                    moontab = moonpos(obsReset);
                    elh.set(hmid, moontab.get(3));
                    elhDone.set(hmid, true);
                }

                if (((rise == 0) && (elh.get(hfirst) <= 0.0) && (elh.get(hmid) >= 0.0))
                        || ((rise == 1) && (elh.get(hfirst) >= 0.0) && (elh.get(hmid) <= 0.0))) {
                    hlast = hmid;
                    found = true;
                    System.out.println("first continue");
                    continue;
                }

                if (((rise == 0) && (elh.get(hmid) <= 0.0) && (elh.get(hlast) >= 0.0))
                        || ((rise == 1) && (elh.get(hmid) >= 0.0) && (elh.get(hlast) <= 0.0))) {
                    hfirst = hmid;
                    found = true;
                    System.out.println("second continue");
                    continue;
                }

                System.out.println("break");
                break;
            }

            // If the binary chop did not find a 1 hour interval
            if ((hlast - hfirst) > 1) {
                System.out.println("did not find a 1 hr interval");
                for (int i = hfirst; i < hlast; i++) {
                    found = false;
                    if (!elhDone.get(i + 1)) {
                        System.out.printf("Set: %s\n", (i + 1));
                        obsReset.setCurrentTime(obsReset.getCurrentTime().withHour(i + 1));
                        moontab = moonpos(obsReset);
                        elh.set(i + 1, moontab.get(3));
                        elhDone.set(i + 1, true);
                    }

                    if (((rise == 0) && (elh.get(i) <= 0.0) && (elh.get(i + 1) >= 0.0))
                            || ((rise == 1) && (elh.get(i) >= 0.0) && (elh.get(i + 1) <= 0.0))) {
                        hfirst = i;
                        hlast = i + 1;
                        found = true;
                        System.out.printf("found: hfirst: %s, hlast: %s. break.\n", hfirst, hlast);
                        break;
                    }
                }
            }

            // simple linear interpolation for the minutes
            if (found) {
                System.out.println("found: " + Arrays.toString(elh.toArray()));
                double elfirst = elh.get(hfirst);
                double ellast = elh.get(hlast);
                obsReset.setCurrentTime(obsReset.getCurrentTime().withHour(hfirst).withMinute(30));
                moontab = moonpos(obsReset);

                double hf = 0.0;
                double hl = 0.0;

                if ((rise == 0) && (moontab.get(3) <= 0.0)) {
                    hf = hfirst + 0.5;
                    elfirst = moontab.get(3);
                    System.out.println(" c1: " + hf);
                }

                if ((rise == 0) && (moontab.get(3) > 0.0)) {
                    hl = hfirst + 0.5;
                    ellast = moontab.get(3);
                    System.out.println(" c2: " + hl);;
                }

                if ((rise == 1) && (moontab.get(3) <= 0.0)) {
                    hl = hfirst + 0.5;
                    ellast = moontab.get(3);
                    System.out.println(" c3: " + hl);
                }

                if ((rise == 1) && (moontab.get(3) > 0.0)) {
                    hf = hfirst + 0.5;
                    elfirst = moontab.get(3);
                    System.out.println(" c4: " + hf);
                }

                double eld = Math.abs(elfirst) + Math.abs(ellast);
                double value = hf + (hl - hf) * Math.abs(elfirst) / eld;
                System.out.println("eld: " + eld);
                System.out.println("hf: " + hf);
                System.out.println("hl: " + hl);

                if (rise == 0) {
                    System.out.println("setting rise: " + value);
                    times.setRise(String.valueOf(value));
                }

                if (rise == 1) {
                    System.out.println("setting set: " + value);
                    times.setSet(String.valueOf(value));
                }
            }
        }

        return times;
    }

    public List<MoonPosition> getEphemeris(Observatory obs, ZonedDateTime startDate, ZonedDateTime endDate, int intervalMinutes) {
        List<MoonPosition> ephemerides = new ArrayList<>();
        ZonedDateTime currentTime = startDate;
        while (currentTime.isBefore(endDate)) {
            obs.setCurrentTime(currentTime);
            MoonPosition eph = getPosition(obs);
            ephemerides.add(eph);

            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        obs.setCurrentTime(endDate);
        MoonPosition eph = getPosition(obs);
        ephemerides.add(eph);

        return ephemerides;
    }

    public MoonPosition getPosition(Observatory obs) {
        String siteName = FormatUtils.sitename(obs);

        List<Double> moontab = moonpos(obs);
        String ra = FormatUtils.hmdstring(moontab.get(0));
        String dec = FormatUtils.anglestring(moontab.get(1), false);
        String altitude = FormatUtils.anglestring(moontab.get(3), false);
        String azimuth = FormatUtils.anglestring(moontab.get(4), true);
        String dist = String.valueOf(moontab.get(2));

        String pa = String.valueOf(Math.round(moontab.get(5)));
        String ip = String.valueOf(Math.round(100.0 * (1.0 + MathUtils.cosd(moontab.get(5))) / 2.0));

        RiseSetTimes riseSetTimes = moonrise(obs);

        MoonPosition eph = new MoonPosition();
        eph.setSiteName(siteName);
        eph.setDate(obs.getCurrentTime());
        eph.setRa(ra);
        eph.setDec(dec);
        eph.setAltitude(altitude);
        eph.setAzimuth(azimuth);
        eph.setDistance(dist);
        eph.setPhaseAngle(pa);
        eph.setIlluminatedPercentage(ip);
        eph.setRiseTime(riseSetTimes.getRise());
        eph.setSetTime(riseSetTimes.getSet());

        return eph;
    }
}
