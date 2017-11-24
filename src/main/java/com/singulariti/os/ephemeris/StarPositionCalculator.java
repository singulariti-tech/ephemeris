package com.singulariti.os.ephemeris;

import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.Star;
import com.singulariti.os.ephemeris.domain.StarPosition;
import com.singulariti.os.ephemeris.utils.FormatUtils;
import com.singulariti.os.ephemeris.utils.MathUtils;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John
 */
public class StarPositionCalculator {

    public StarPositionCalculator() {
    }

    public List<StarPosition> getEphemeris(Star star, Observatory obs, ZonedDateTime startDate, ZonedDateTime endDate,
            int intervalMinutes) {
        List<StarPosition> ephemerides = new ArrayList<>();
        ZonedDateTime currentTime = startDate;
        while (currentTime.isBefore(endDate)) {
            obs.setCurrentTime(currentTime);
            StarPosition eph = getPosition(star, obs);
            ephemerides.add(eph);

            currentTime = currentTime.plusMinutes(intervalMinutes);
        }

        obs.setCurrentTime(endDate);
        StarPosition eph = getPosition(star, obs);
        ephemerides.add(eph);

        return ephemerides;
    }

    public StarPosition getPosition(Star star, Observatory obs) {
        String siteName = FormatUtils.sitename(obs);

        String ra = star.getRa();
        String de = star.getDe();
        int mgn = Integer.valueOf(star.getMg());
        double rac = FormatUtils.parseCol(ra);
        double dec = FormatUtils.parseCol(de);
        double[] altaz = MathUtils.radtoaa(rac, dec, obs);
        String azimuth = FormatUtils.anglestring(altaz[1], true);
        String altitude = FormatUtils.anglestring(altaz[0], false);

        StarPosition eph = new StarPosition();
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
