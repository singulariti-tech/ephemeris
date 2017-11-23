package com.singulariti.os.ephemeris.domain;

import java.util.Objects;

/**
 *
 * @author John
 */
public class RiseSetTimes {

    private String rise;
    private String set;

    public RiseSetTimes() {
    }

    public RiseSetTimes(String rise, String set) {
        this.rise = rise;
        this.set = set;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.rise);
        hash = 97 * hash + Objects.hashCode(this.set);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RiseSetTimes other = (RiseSetTimes) obj;
        if (!Objects.equals(this.rise, other.rise)) {
            return false;
        }
        if (!Objects.equals(this.set, other.set)) {
            return false;
        }
        return true;
    }

}
