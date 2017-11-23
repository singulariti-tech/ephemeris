package com.singulariti.os.ephemeris.domain;

import java.util.List;

/**
 *
 * @author John
 */
public class Planet {

    private final String name;
    private final List<Double> L;
    private final List<Double> a;
    private final List<Double> e;
    private final List<Double> i;
    private final List<Double> N;
    private final List<Double> P;

    public Planet(String name, List<Double> L, List<Double> a, List<Double> e, List<Double> i, List<Double> N, List<Double> P) {
        this.name = name;
        this.L = L;
        this.a = a;
        this.e = e;
        this.i = i;
        this.N = N;
        this.P = P;
    }

    public String getName() {
        return name;
    }

    public List<Double> getL() {
        return L;
    }

    public double getL(int index) {
        return L.get(index);
    }

    public List<Double> getA() {
        return a;
    }

    public Double getA(int index) {
        return a.get(index);
    }

    public List<Double> getE() {
        return e;
    }

    public Double getE(int index) {
        return e.get(index);
    }

    public List<Double> getI() {
        return i;
    }

    public Double getI(int index) {
        return i.get(index);
    }

    public List<Double> getN() {
        return N;
    }

    public double getN(int index) {
        return N.get(index);
    }

    public List<Double> getP() {
        return P;
    }

    public Double getP(int index) {
        return P.get(index);
    }

}
