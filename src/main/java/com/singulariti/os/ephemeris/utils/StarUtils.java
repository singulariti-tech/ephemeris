package com.singulariti.os.ephemeris.utils;

import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.Star;
import com.singulariti.os.ephemeris.domain.StarEphemeris;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John
 */
public class StarUtils {

    public StarUtils() {
    }

    public List<StarEphemeris> getEphemeris(Star star, Observatory obs, ZonedDateTime startDate, ZonedDateTime endDate,
            int intervalMinutes) {
        List<StarEphemeris> ephemerides = new ArrayList<>();
        ZonedDateTime currentTime = startDate;
        while (currentTime.isBefore(endDate)) {
            obs.setCurrentTime(currentTime);
            StarEphemeris eph = getEphemeride(star, obs);
            ephemerides.add(eph);

            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        obs.setCurrentTime(endDate);
        StarEphemeris eph = getEphemeride(star, obs);
        ephemerides.add(eph);

        return ephemerides;
    }

    public StarEphemeris getEphemeride(Star star, Observatory obs) {
        String siteName = FormatUtils.sitename(obs);

        String ra = star.getRa();
        String de = star.getDe();
        int mgn = Integer.valueOf(star.getMg());
        double rac = FormatUtils.parseCol(ra);
        double dec = FormatUtils.parseCol(de);
        double[] altaz = MathUtils.radtoaa(rac, dec, obs);
        String azimuth = FormatUtils.anglestring(altaz[1], true);
        String altitude = FormatUtils.anglestring(altaz[0], false);

        StarEphemeris eph = new StarEphemeris();
        eph.setSiteName(siteName);
        eph.setStar(star.getId());
        eph.setName(star.getTraditionalName());
        eph.setConstellation(star.getConstellation());
        eph.setSpectralClass(star.getSpec());
        eph.setDate(obs.getCurrentTime());
        eph.setRa(ra);
        eph.setDec(de);
        eph.setMg(String.valueOf(mgn));
        eph.setType(star.getType());
        eph.setSpectralClass(star.getSpec());
        eph.setAzimuth(azimuth);
        eph.setAltitude(altitude);

        return eph;
    }

}
