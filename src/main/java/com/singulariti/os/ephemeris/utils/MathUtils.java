package com.singulariti.os.ephemeris.utils;

import com.singulariti.os.ephemeris.domain.Observatory;
import static com.singulariti.os.ephemeris.utils.DateTimeUtils.local_sidereal;

/**
 *
 * @author John
 */
public class MathUtils {

    private MathUtils() {
    }

    public static double rev(double angle) {
        int floor = (int) Math.floor(angle / 360.0);
        return angle - floor * 360.0;
    }

    public static double sind(double angle) {
        return Math.sin((angle * Math.PI) / 180.0);
    }

    public static double cosd(double angle) {
        return Math.cos((angle * Math.PI) / 180.0);
    }

    public static double tand(double angle) {
        return Math.tan((angle * Math.PI) / 180.0);
    }

    public static double asind(double c) {
        return (180.0 / Math.PI) * Math.asin(c);
    }

    public static double acosd(double c) {
        return (180.0 / Math.PI) * Math.acos(c);
    }

    public static double atan2d(double y, double x) {
        double val = (x < 0) ? 180.0 : 0;
        return (180.0 / Math.PI) * Math.atan(y / x) - val;
    }

    public static int SGN(int x) {
        return (x < 0) ? -1 : +1;
    }

    public static int intr(double num) {
        int n = (int) Math.floor(Math.abs(num));
        if (num < 0) {
            n = n * -1;
        }
        return n;
    }

    public static float numFloat(String num) {
        return Float.valueOf(num);
    }

    /**
     * radtoaa converts ra and dec to altitude and azimuth
     *
     * @param ra
     * @param dec
     * @param obs
     * @return
     */
    public static double[] radtoaa(double ra, double dec, Observatory obs) {
        double lst = local_sidereal(obs);
        double x = MathUtils.cosd(15.0 * (lst - ra)) * MathUtils.cosd(dec);
        double y = MathUtils.sind(15.0 * (lst - ra)) * MathUtils.cosd(dec);
        double z = MathUtils.sind(dec);
        // rotate so z is the local zenith
        double xhor = x * MathUtils.sind(obs.getLatitude()) - z * MathUtils.cosd(obs.getLatitude());
        double yhor = y;
        double zhor = x * MathUtils.cosd(obs.getLatitude()) + z * MathUtils.sind(obs.getLatitude());
        double azimuth = MathUtils.rev(MathUtils.atan2d(yhor, xhor) + 180.0); // so 0 degrees is north
        double altitude = MathUtils.atan2d(zhor, Math.sqrt(xhor * xhor + yhor * yhor));
        return new double[]{altitude, azimuth};
    }

    public static double[] aatorad(double alt, double az, Observatory obs) {
        double lst = local_sidereal(obs);
        double lat = obs.getLatitude();
        double j = MathUtils.sind(alt) * MathUtils.sind(lat) + MathUtils.cosd(alt) * MathUtils.cosd(lat) * MathUtils.cosd(az);
        double dec = MathUtils.asind(j);
        j = (MathUtils.sind(alt) - MathUtils.sind(lat) * MathUtils.sind(dec)) / (MathUtils.cosd(lat) * MathUtils.cosd(dec));
        double s = MathUtils.acosd(j);
        j = MathUtils.sind(az);

        if (j > 0) {
            s = 360 - s;
        }

        double ra = lst - s / 15;
        if (ra < 0) {
            ra += 24;
        }

        if (ra >= 24) {
            ra -= 24;
        }

        return new double[]{ra, dec};
    }

}
