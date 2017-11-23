package com.singulariti.os.ephemeris.domain;

/**
 *
 * @author John
 */
public class Coord3D {

    private double x;
    private double y;
    private double z;

    public Coord3D() {
    }

    public Coord3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

}
