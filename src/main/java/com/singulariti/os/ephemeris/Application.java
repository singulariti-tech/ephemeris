package com.singulariti.os.ephemeris;

import com.singulariti.os.ephemeris.domain.MoonEphemeris;
import com.singulariti.os.ephemeris.domain.Observatory;
import com.singulariti.os.ephemeris.domain.Place;
import com.singulariti.os.ephemeris.domain.Planet;
import com.singulariti.os.ephemeris.domain.PlanetEphemeris;
import com.singulariti.os.ephemeris.domain.Pole;
import com.singulariti.os.ephemeris.domain.Star;
import com.singulariti.os.ephemeris.domain.StarEphemeris;
import com.singulariti.os.ephemeris.domain.SunEphemeris;
import com.singulariti.os.ephemeris.utils.FormatUtils;
import com.singulariti.os.ephemeris.utils.MoonUtils;
import com.singulariti.os.ephemeris.utils.PlanetCatalog;
import com.singulariti.os.ephemeris.utils.PlanetUtils;
import com.singulariti.os.ephemeris.utils.StarCatalog;
import com.singulariti.os.ephemeris.utils.StarUtils;
import com.singulariti.os.ephemeris.utils.SunUtils;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

/**
 * ?Name=%27Hyderabad%27&Lat=17.3848&Lon=78.4906&TZ=5.5&DST=false
 * ?Name=%27Hassan%27&Lat=13.0068&Lon=76.0996&TZ=5.5&DST=false
 *
 * @author John
 */
public class Application {

    public Application() {
    }

    public void generateSunEphemeris(Observatory obs, ZonedDateTime start, ZonedDateTime end) {
        SunUtils sun = new SunUtils();
        List<SunEphemeris> ephemerides = sun.getEphemeris(obs, start, end, 10);
        printSunEphemeris(ephemerides);
    }

    void printSunEphemeris(List<SunEphemeris> ephs) {
        System.out.println(SunEphemeris.header());
        ephs.stream().forEach(e -> System.out.println(e));
    }

    public void generatePlanetEphemeris(Planet planet, Observatory obs, ZonedDateTime start, ZonedDateTime end) {
        PlanetUtils planetCalc = new PlanetUtils();
        List<PlanetEphemeris> ephemerides = planetCalc.getEphemeris(planet, obs, start, end, 10);
        printPlanetEphemeris(ephemerides);
    }

    void printPlanetEphemeris(List<PlanetEphemeris> ephs) {
        System.out.println(PlanetEphemeris.header());
        ephs.stream().forEach(e -> System.out.println(e));
    }

    public void generateMoonEphemeris(Observatory obs, ZonedDateTime start, ZonedDateTime end) {
        MoonUtils moonCalc = new MoonUtils();
        List<MoonEphemeris> ephemerides = moonCalc.getEphemeris(obs, start, end, 10);
        printMoonEphemeris(ephemerides);
    }

    void printMoonEphemeris(List<MoonEphemeris> ephs) {
        System.out.println(MoonEphemeris.header());
        ephs.stream().forEach(e -> System.out.println(e));
    }

    public void generateStarEphemeris(Star star, Observatory obs, ZonedDateTime start, ZonedDateTime end) {
        StarUtils starCalc = new StarUtils();
        List<StarEphemeris> ephemerides = starCalc.getEphemeris(star, obs, start, end, 10);
        printStarEphemeris(ephemerides);
    }

    void printStarEphemeris(List<StarEphemeris> ephs) {
        System.out.println(StarEphemeris.header());
        ephs.stream().forEach(e -> System.out.println(e));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Math.ceil((12 - 9) / 2.0));
        double latitude = 13.0068;
        double longitude = 76.0996;

        String strLat = FormatUtils.llstring(latitude);
        String strLng = FormatUtils.llstring(longitude);

        ZonedDateTime start = ZonedDateTime.of(2017, 11, 7, 0, 0, 0, 0, ZoneId.of("UTC"));
        ZonedDateTime end = start.plusHours(1);

        Place place = new Place("Hassan", latitude, Pole.NORTH, longitude, Pole.EAST, TimeZone.getTimeZone("Asia/Calcutta"), "", "");
        Observatory obs = new Observatory(place, start);

        System.out.println("Latitude: " + obs.getLatitude() + " - " + strLat);
        System.out.println("Longitude: " + obs.getLongitude() + " - " + strLng);

        Application application = new Application();

        System.out.println("=========================================================================================");
        System.out.println(" SUN");
        System.out.println("=========================================================================================");
        
        application.generateSunEphemeris(obs, start, end);

        System.out.println("=========================================================================================");
        System.out.println(" MARS");
        System.out.println("=========================================================================================");

        Optional<Planet> marsContainer = PlanetCatalog.byName("Mars");
        if (marsContainer.isPresent()) {
            application.generatePlanetEphemeris(marsContainer.get(), obs, start, end);
        }

        System.out.println("=========================================================================================");
        System.out.println(" MOON");
        System.out.println("=========================================================================================");

        application.generateMoonEphemeris(obs, start, end);

        System.out.println("=========================================================================================");
        System.out.println(" CAS A");
        System.out.println("=========================================================================================");

        Optional<Star> casAContainer = StarCatalog.byIdAndConstellation("a", "cas");
        if (casAContainer.isPresent()) {
            Star casA = casAContainer.get();
            application.generateStarEphemeris(casA, obs, start, end);
        }

    }

}
