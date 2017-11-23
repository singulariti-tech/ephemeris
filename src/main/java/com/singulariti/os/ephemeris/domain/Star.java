package com.singulariti.os.ephemeris.domain;

/**
 *
 * @author John
 */
public class Star {

    private final String id;
    private final String constellation;
    private final String traditionalName;
    private final String ra;
    private final String de;
    private final String mg;
    private final String type;
    private final String spec;

    public Star(String id, String constellation, String name, String ra, String de, String mg, String type, String spec) {
        this.id = id;
        this.constellation = constellation;
        this.traditionalName = name;
        this.ra = ra;
        this.de = de;
        this.mg = mg;
        this.type = type;
        this.spec = spec;
    }

    public String getId() {
        return id;
    }

    public String getConstellation() {
        return constellation;
    }

    public String getTraditionalName() {
        return traditionalName;
    }

    public String getRa() {
        return ra;
    }

    public String getDe() {
        return de;
    }

    public String getMg() {
        return mg;
    }

    public String getType() {
        return type;
    }

    public String getSpec() {
        return spec;
    }

}
