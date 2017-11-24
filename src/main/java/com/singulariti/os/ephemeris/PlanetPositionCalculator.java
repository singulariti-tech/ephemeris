package com.singulariti.os.ephemeris;

import com.singulariti.os.ephemeris.domain.Coord3D;
import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.Planet;
import com.singulariti.os.ephemeris.domain.PlanetPosition;
import com.singulariti.os.ephemeris.utils.DateTimeUtils;
import com.singulariti.os.ephemeris.utils.FormatUtils;
import com.singulariti.os.ephemeris.utils.MathUtils;
import com.singulariti.os.ephemeris.utils.PlanetCatalog;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author John
 */
public class PlanetPositionCalculator {

    // heliocentric xyz for planet p and observer obs
    public static Coord3D helios(Planet p, Observatory obs) {

        double T = (DateTimeUtils.jd(obs) - 2451545.0) / 36525;
        double T2 = T * T;
        double T3 = T2 * T;
        // longitude of ascending node
        double N = MathUtils.rev(p.getN(0) + p.getN(1) * T + p.getN(2) * T2 + p.getN(3) * T3);
        // inclination
        double i = p.getI(0) + p.getI(1) * T + p.getI(2) * T2 + p.getI(3) * T3;
        // Mean longitude
        double L = MathUtils.rev(p.getL(0) + p.getL(1) * T + p.getL(2) * T2 + p.getL(3) * T3);
        // semimajor axis
        double a = p.getA(0) + p.getA(1) * T + p.getA(2) * T2 + p.getA(3) * T3;
        // eccentricity
        double e = p.getE(0) + p.getE(1) * T + p.getE(2) * T2 + p.getE(3) * T3;
        // longitude of perihelion
        double P = MathUtils.rev(p.getP(0) + p.getP(1) * T + p.getP(2) * T2 + p.getP(3) * T3);
        double M = MathUtils.rev(L - P);
        double w = MathUtils.rev(L - N - M);
        // Eccentric anomaly
        double E0 = M + (180.0 / Math.PI) * e * MathUtils.sind(M) * (1 + e * MathUtils.cosd(M));
        double E = E0 - (E0 - (180.0 / Math.PI) * e * MathUtils.sind(E0) - M) / (1 - e * MathUtils.cosd(E0));
        while (Math.abs(E0 - E) > 0.0005) {
            E0 = E;
            E = E0 - (E0 - (180.0 / Math.PI) * e * MathUtils.sind(E0) - M) / (1 - e * MathUtils.cosd(E0));
        };
        double x = a * (MathUtils.cosd(E) - e);
        double y = a * Math.sqrt(1 - e * e) * MathUtils.sind(E);
        double r = Math.sqrt(x * x + y * y);
        double v = MathUtils.rev(MathUtils.atan2d(y, x));

        double xeclip = r * (MathUtils.cosd(N) * MathUtils.cosd(v + w) - MathUtils.sind(N) * MathUtils.sind(v + w) * MathUtils.cosd(i));
        double yeclip = r * (MathUtils.sind(N) * MathUtils.cosd(v + w) + MathUtils.cosd(N) * MathUtils.sind(v + w) * MathUtils.cosd(i));;
        double zeclip = r * MathUtils.sind(v + w) * MathUtils.sind(i);

        return new Coord3D(xeclip, yeclip, zeclip);
    }

    // radecr returns ra, dec and earth distance
    // obj and base are Heliocentric Ecliptic Rectangular Coordinates
    // for the object and earth and obs is the observer
    public static List<Double> radecr(Coord3D obj, Coord3D base, Observatory obs) {
        // Equatorial co-ordinates
        double x = obj.getX();
        double y = obj.getY();
        double z = obj.getZ();
        // julian date
        double jdobs = DateTimeUtils.jd(obs);
        // Obliquity of Ecliptic
        double obl = 23.4393 - 3.563E-7 * (jdobs - 2451543.5);
        // Convert to Geocentric co-ordinates
        double x1 = x - base.getX();
        double y1 = (y - base.getY()) * MathUtils.cosd(obl) - (z - base.getZ()) * MathUtils.sind(obl);
        double z1 = (y - base.getY()) * MathUtils.sind(obl) + (z - base.getZ()) * MathUtils.cosd(obl);
        // RA and dec
        double ra = MathUtils.rev(MathUtils.atan2d(y1, x1)) / 15.0;
        double dec = MathUtils.atan2d(z1, Math.sqrt(x1 * x1 + y1 * y1));
        // Earth distance
        double r = Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
        return new ArrayList<Double>() {
            {
                add(ra);
                add(dec);
                add(r);
            }
        };
    }

    public List<PlanetPosition> getEphemeris(Planet planet, Observatory obs, ZonedDateTime startDate, ZonedDateTime endDate, int intervalMinutes) {
        List<PlanetPosition> ephemerides = new ArrayList<>();
        ZonedDateTime currentTime = startDate;
        while (currentTime.isBefore(endDate)) {
            obs.setCurrentTime(currentTime);
            PlanetPosition eph = getPosition(planet, obs);
            ephemerides.add(eph);

            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        obs.setCurrentTime(endDate);
        PlanetPosition eph = getPosition(planet, obs);
        ephemerides.add(eph);

        return ephemerides;
    }

    public PlanetPosition getPosition(Planet planet, Observatory obs) {
        String siteName = planet.getName();
        Planet earth = PlanetCatalog.byName("Earth");

        Coord3D planet_xyz = PlanetPositionCalculator.helios(planet, obs);
        Coord3D earth_xyz = PlanetPositionCalculator.helios(earth, obs);
        List<Double> radec = PlanetPositionCalculator.radecr(planet_xyz, earth_xyz, obs);
        double[] altaz = MathUtils.radtoaa(radec.get(0), radec.get(1), obs);

        String ra = FormatUtils.hmdstring(radec.get(0));
        String dec = FormatUtils.anglestring(radec.get(1), false);
        String alt = FormatUtils.anglestring(altaz[0], false);
        String az = FormatUtils.anglestring(altaz[1], false);
        String dist = String.valueOf(Math.round(radec.get(2) * 1000.0) / 1000.0);

        Observatory obsReset = obs.copy();
        obsReset.setCurrentTime(obs.getCurrentTime().withHour(12).withMinute(0).withSecond(0).withNano(0));

        planet_xyz = PlanetPositionCalculator.helios(planet, obsReset);
        double lst = DateTimeUtils.local_sidereal(obsReset);
        radec = PlanetPositionCalculator.radecr(planet_xyz, earth_xyz, obsReset);

        double dblRa = radec.get(0);
        double dblDec = radec.get(1);
        double UTplanet = 12.0 + dblRa - lst;
        if (UTplanet < 0.0) {
            UTplanet += 24.0;
        }

        if (UTplanet > 24.0) {
            UTplanet -= 24.0;
        }

        // refraction correction 0.583
        double cosLHA = (MathUtils.sind(-0.583) - MathUtils.sind(obs.getLatitude()) * MathUtils.sind(dblDec))
                / (MathUtils.cosd(obs.getLatitude()) * MathUtils.cosd(dblDec));

        String rise = "";
        String transit = "";
        String set = "";

        if (cosLHA > 1.0) {
            rise = "----";
            transit = "----";
            set = "----";
        } else if (cosLHA < -1.0) {
            rise = "++++";
            transit = "++++";
            set = "++++";
        } else {
            // Print rise/set times allowing for not today.
            double lha = MathUtils.acosd(cosLHA) / 15.04107;
            if ((UTplanet - lha) < 0.0) {
                rise = FormatUtils.hmstring(24.0 + UTplanet - lha);
            } else {
                rise = FormatUtils.hmstring(UTplanet - lha);
            }

            transit = FormatUtils.hmstring(UTplanet);

            if ((UTplanet + lha) > 24.0) {
                set = FormatUtils.hmstring(UTplanet + lha - 24.0);
            } else {
                set = FormatUtils.hmstring(UTplanet + lha);
            }
        }

        PlanetPosition eph = new PlanetPosition();
        eph.setName(siteName);
        eph.setRa(ra);
        eph.setDec(dec);
        eph.setAltitude(alt);
        eph.setAzimuth(az);
        eph.setDistance(dist);
        eph.setRise(rise);
        eph.setTransit(transit);
        eph.setSet(set);
        eph.setDate(obs.getCurrentTime());

        return eph;
    }

}
