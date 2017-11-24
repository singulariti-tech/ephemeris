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

import java.time.ZonedDateTime;

/**
 *
 * @author John
 */
public class Observatory {

    private final Place location;
    private ZonedDateTime currentTime;

    public Observatory(Place location, ZonedDateTime currentTime) {
        this.location = location;
        this.currentTime = currentTime;
    }

    public ZonedDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(ZonedDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public Place getLocation() {
        return location;
    }

    public String getName() {
        return this.location.getName();
    }

    public int getFullYear() {
        return currentTime.getYear();
    }

    public int getMonth() {
        return currentTime.getMonthValue();
    }

    public int getDate() {
        return currentTime.getDayOfMonth();
    }

    public int getHours() {
        return currentTime.getHour();
    }

    public int getMinutes() {
        return currentTime.getMinute();
    }

    public int getSeconds() {
        return currentTime.getSecond();
    }

    public int getTimeDifferenceGMTMinutes() {
        return this.location.getTimeDifferenceGMTMinutes();
    }

    public double getLatitude() {
        return this.location.getLatitude();
    }

    public double getLongitude() {
        return this.location.getLongitude();
    }

    public Observatory copy() {
        Place placeCpy = this.location.copy();
        Observatory obs = new Observatory(placeCpy, currentTime);
        return obs;
    }

}
