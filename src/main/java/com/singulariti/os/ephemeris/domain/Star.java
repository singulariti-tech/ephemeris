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
