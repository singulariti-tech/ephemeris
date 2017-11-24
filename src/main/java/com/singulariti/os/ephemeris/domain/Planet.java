/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
