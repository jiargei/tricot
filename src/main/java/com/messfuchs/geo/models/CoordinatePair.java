/*
 * Copyright 2017 jurgen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.messfuchs.geo.models;

import java.util.Objects;

/**
 *
 * @author jurgen
 */
public class CoordinatePair implements StringComparable {
    
    private LocalCoordinate local;
    private GeocentricCoordinate geocentric;
    private GeographicCoordinate geographic;
    private String name;
    
    public CoordinatePair(LocalCoordinate local, GeocentricCoordinate geocentric, GeographicCoordinate geographic) {
        this.local = local;
        this.geocentric = geocentric;
        this.geographic = geographic;
        this.name = local.getName() + "::" + geocentric.getName();
    }
    
    public CoordinatePair(LocalCoordinate local, GeocentricCoordinate geocentric) {
        this(local, geocentric, null);
    }

    @Override
    public String toString() {
        return "CoordinatePair{" + "local=" + local + ", geocentric=" + geocentric + "geographic=" + geographic + ", name=" + name + '}';
    }
    
    @Override
    public String getCompareString() {return this.getName(); }
    
    public void setPair(LocalCoordinate local, GeocentricCoordinate geocentric) {
        this.setPair(local, geocentric, null);
    }
    
    public void setPair(LocalCoordinate local, GeocentricCoordinate geocentric, GeographicCoordinate geographic) {
        this.setName(local.getName() + " :: " + geocentric.getName());
        this.setLocal(local);
        this.setGeocentric(geocentric);
        this.setGeographic(geographic);
    }
    
    public LocalCoordinate getLocal() {
        return local;
    }

    public void setLocal(LocalCoordinate local) {
        this.local = local;
    }

    public GeocentricCoordinate getGeocentric() {
        return geocentric;
    }

    public void setGeocentric(GeocentricCoordinate geocentric) {
        this.geocentric = geocentric;
    }

    public GeographicCoordinate getGeographic() {
        return geographic;
    }

    public void setGeographic(GeographicCoordinate geographic) {
        this.geographic = geographic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.local);
        hash = 97 * hash + Objects.hashCode(this.geocentric);
        hash = 97 * hash + Objects.hashCode(this.geographic);
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
        final CoordinatePair other = (CoordinatePair) obj;
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.geocentric, other.geocentric)) {
            return false;
        }
        return true;
    }
    
    
}
