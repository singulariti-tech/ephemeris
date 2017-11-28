# ephemeris
A Java port of Ephemeris calculations by Peter Hayes. Since the original JavaScript version of the code could not be 
found, we are unable to link to it. There are a few versions available on github and a few hosted versions too. 
This version is more or less an exact port of the original (specifically the calculations which are based on 
Jean Meeus' calculations). This version is being provided only as a library and so does not include a front-end.

The code provides calculators for determining the positions of the Sun, Moon, Planets and several stars as viewed from 
a particular position on Earth.

### Github repo of the JS version

[clicktrend/ephemeris](https://github.com/clicktrend/ephemeris)

### Hosted versions

[Stargazing](http://www.stargazing.net/mas/hayes/ephemeris.html)

# Building the code

mvn clean package

# Usage

Sample code below shows how you can use each of the calculators to generate the Ephemeris.

## Observatory

The location on Earth from where you want to view the astronomical body

~~~~

// Create an instance of the observatory
double latitude = 13.0068;
double longitude = 76.0996;

ZonedDateTime time = ZonedDateTime.of(2017, 11, 7, 0, 0, 0, 0, ZoneId.of("UTC")); //Date and time in UTC

Place place = new Place("Hassan", latitude, Pole.NORTH, longitude, Pole.EAST, TimeZone.getTimeZone("Asia/Calcutta"), "", "");
Observatory hassan = new Observatory(place, time); //observatory is a place at a specific time

~~~~

## Sun Position

Calculating position of the sun as viewed from the Observatory

~~~~

// Create an instance of the Sun position calculator
SunPositionCalculator solarCalc = new SunPositionCalculator();
SunPosition sunPosition = solarCalc.getPosition(hassan);

sunPosition.getRiseTime(); //Rise time
sunPosition.getSetTime(); //Set time
sunPosition.getAzimuth(); //Azimuth
sunPosition.getAltitude(); //Elevation

~~~~

There's a convenience method to generate the Ephemeris for a period.

~~~~

int timeIntervalInMinutes = 10; //Positions to be calculated every 10 minutes from the start time
ZonedDateTime anotherTime = ZonedDateTime.of(2017, 11, 8, 0, 0, 0, 0, ZoneId.of("UTC")); //Date and time in UTC
List<SunPosition> ephemerides = sun.getEphemeris(obs, time, anotherTime, timeIntervalInMinutes);

~~~~

## Moon Position

Calculating position of the moon as viewed from the Observatory

~~~~

MoonPositionCalculator lunarCalc = new MoonPositionCalculator();
MoonPosition moonPosition = lunarCalc.getPosition(hassan);

~~~~

You can generate the Ephemeris with a specified interval as with the SunCalculator.

## Planet Position

Calculating position of a planet as viewed from the Observatory

~~~~

PlanetPositionCalculator planetCalc = new PlanetPositionCalculator();
Planet mars = PlanetCatalog.byName("Mars");
PlanetPosition marsPosition = planetCalc.getPosition(mars, hassan);

~~~~

The Ephemeris can be generated as with the SunCalculator.

## Star Position

Calculating the position of a Star as viewed from the Observatory

~~~~

StarCalculator starCalculator = new StarCalculator();
Star casA = StarCatalog.byIdAndConstellation("a", "cas");
StarPosition casAPosition = starCalc.getPosition(casA, hassan);

~~~~

The Ephemeris can be generated as with the SunCalculator.


# License

This project is licensed under the terms of the Apache 2 license.