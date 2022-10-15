package com.food;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Truck implements Serializable, Comparable<Truck> {
    /**
     * DayOrder,DayOfWeekStr,starttime,endtime,permit,PermitLocation,locationdesc,optionaltext,locationid,scheduleid,start24,end24,CNN,Addr_Date_Create,Addr_Date_Modified,block,lot,ColdTruck,Applicant,X,Y,Latitude,Longitude,Location
     * 0,Sunday,12PM,6PM,21MFF-00015,3750 18TH ST,"Facility located on the north side of 18th Street, approximately 170 feet west of Dolores Street","Snow Cones, Soft Serve Ice Cream & Frozen Virgin Daiquiris",1571753,,12:00,18:00,887000,11/15/2021 01:30:17 PM,11/15/2021 01:33:58 PM,3579,006,N,The Geez Freeze,6004590.10061802,2105440.47834342,37.761398102006275,-122.427241066455551,"(37.761398102006275, -122.42724106645555)"
     */
    private @Id @GeneratedValue Long id;
    private volatile int dayOrder;
    private volatile String dayOfWeekStr;
    private volatile String starttime;
    private volatile String endtime;
    private volatile String permitLocation;
    private volatile String locationdesc;
    private volatile String optionaltext;
    private volatile Long locationid;
    private volatile String applicant;
    private volatile double latitude;
    private volatile double longitude;
    private volatile double distance;

    Truck() {
    }

    public Truck(String permitLocation, double latitude, double longitude, String starttime, String endtime, int dayOrder, String dayOfWeekStr, String optionaltext, String applicant, Long locationid) {
        this.permitLocation = permitLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.starttime = starttime;
        this.endtime = endtime;
        this.dayOrder = dayOrder;
        this.dayOfWeekStr = dayOfWeekStr;
        this.optionaltext = optionaltext;
        this.applicant = applicant;
        this.locationid = locationid;
    }

    public synchronized Long getId() {
        return this.id;
    }

    public synchronized void setId(Long id) {
        this.id = id;
    }

    public synchronized double getLatitude() {
        return this.latitude;
    }

    public synchronized void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public synchronized double getLongtitude() {
        return this.longitude;
    }

    public synchronized void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public synchronized void setLocationdesc(String locationdesc) {
        this.locationdesc = locationdesc;
    }

    public synchronized String getLocationdesc() {
        return this.locationdesc;
    }

    public synchronized void setOptionaltext(String optionaltext) {
        this.optionaltext = optionaltext;
    }

    public synchronized String getOptionaltext() {
        return this.optionaltext;
    }

    public synchronized void setPermitLocation(String permitLocation) {
        this.permitLocation = permitLocation;
    }

    public synchronized String getPermitLocation() {
        return this.permitLocation;
    }

    public synchronized void setDayOrder(int dayOrder) {
        this.dayOrder = dayOrder;
    }

    public synchronized int getDayOrder() {
        return this.dayOrder;
    }

    public synchronized void setDayOfWeekStr(String dayOfWeekStr) {
        this.dayOfWeekStr = dayOfWeekStr;
    }

    public synchronized String getDayOfWeekStr() {
        return this.dayOfWeekStr;
    }

    public synchronized void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public synchronized String getStarttime() {
        return this.starttime;
    }

    public synchronized void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public synchronized String getEndtime() {
        return this.endtime;
    }

    public synchronized void setDistance(double distance) {
        this.distance = distance;
    }

    public synchronized double getDistance() {
        return this.distance;
    }

    public synchronized void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public synchronized String getApplicant() {
        return this.applicant;
    }

    public synchronized void setLocationid(Long locationid) {
        this.locationid = locationid;
    }

    public synchronized Long getLocationid() {
        return this.locationid;
    }

    @Override
    public synchronized boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Truck))
            return false;
        Truck truck = (Truck) o;
        return Objects.equals(this.id, truck.id) && Objects.equals(this.latitude, truck.latitude)
                && Objects.equals(this.longitude, truck.longitude);
    }

    @Override
    public synchronized int hashCode() {
        return Objects.hash(this.id, this.latitude, this.longitude);
    }

    @Override
    public synchronized String toString() {
        return "Truck{" +
                "id=" + this.id +
                ", permitLocation=" + this.permitLocation +
                ", optionalText=" + this.optionaltext +
                ", startTime=" + this.starttime +
                ", endTime=" + this.endtime +
                ", latitude='" + this.latitude + '\'' +
                ", longitude='" + this.longitude + '\'' + '}';
    }

    /**
     * check if the truck has the favor.
     * @param favor     input parameter
     * @return boolean  true/false
     */
    public synchronized boolean hasFavor(String favor) {
        return this.optionaltext.toLowerCase().contains(favor.toLowerCase());
    }

    /*
     * This method sort automatically by the distance in incremental order.
     * It is used by Collections.sort(List<Truck>);
     */
    @Override
    public synchronized int compareTo(Truck truck) {
        if(this.distance > truck.getDistance()) {
            return 1;
        } else if( this.distance == truck.getDistance()) {
            return 0;
        }
        return -1;
    }
}

