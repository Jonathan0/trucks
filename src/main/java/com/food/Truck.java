package com.food;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Truck {
    /**
     * DayOrder,DayOfWeekStr,starttime,endtime,permit,PermitLocation,locationdesc,optionaltext,locationid,scheduleid,start24,end24,CNN,Addr_Date_Create,Addr_Date_Modified,block,lot,ColdTruck,Applicant,X,Y,Latitude,Longitude,Location
     * 0,Sunday,12PM,6PM,21MFF-00015,3750 18TH ST,"Facility located on the north side of 18th Street, approximately 170 feet west of Dolores Street","Snow Cones, Soft Serve Ice Cream & Frozen Virgin Daiquiris",1571753,,12:00,18:00,887000,11/15/2021 01:30:17 PM,11/15/2021 01:33:58 PM,3579,006,N,The Geez Freeze,6004590.10061802,2105440.47834342,37.761398102006275,-122.427241066455551,"(37.761398102006275, -122.42724106645555)"
     */
    private @Id @GeneratedValue Long id;
    private int dayOrder;
    private String dayOfWeekStr;
    private String starttime;
    private String endtime;
    private String permitLocation;
    private String locationdesc;
    private String optionaltext;
    private Long locationid;
    private String start24;
    private String end24;
    private String applicant;
    private double latitude;
    private double longitude;
    private String location;

    Truck() {
    }

    public Truck(String permitLocation, double latitude, double longitude) {
        this.permitLocation = permitLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLocationdesc() {
        return this.locationdesc;
    }

    public void setPermitLocation(String permitLocation) {
        this.permitLocation = permitLocation;
    }

    public String getPermitLocation() {
        return this.permitLocation;
    }

    public void setDayOrder(int dayOrder) {
        this.dayOrder = dayOrder;
    }

    public int getDayOrder() {
        return this.dayOrder;
    }

    public void setDayOfWeekStr(String dayOfWeekStr) {
        this.dayOfWeekStr = dayOfWeekStr;
    }

    public String getDayOfWeekStr() {
        return this.dayOfWeekStr;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getStarttime() {
        return this.starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Truck))
            return false;
        Truck employee = (Truck) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.latitude, employee.latitude)
                && Objects.equals(this.longitude, employee.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + this.id +
                ", permitLocation=" + this.permitLocation +
                ", latitude='" + this.latitude + '\'' +
                ", longitude='" + this.longitude + '\'' + '}';
    }
}

