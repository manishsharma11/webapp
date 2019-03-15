package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "route_stops_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "route_stops_list")
public class RouteStops implements Comparable<RouteStops> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "route_stops_list_id_seq_gen")
    @SequenceGenerator(name = "route_stops_list_id_seq_gen", sequenceName = "route_stops_list_id_seq")
    private Integer id;
    private Integer stop_number;
    private String stop_time;
    private Integer route_id;
    private Integer stop_id;
    
    @OneToOne()
    @JoinColumn(name = "stop_id", referencedColumnName = "id", insertable = false, updatable = false)
    @BatchSize(size = 30) 
    public Stops stop;

    private Boolean enabled = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStop_number() {
        return stop_number;
    }

    public void setStop_number(Integer stop_number) {
        this.stop_number = stop_number;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public Integer getStop_id() {
        return stop_id;
    }

    public void setStop_id(Integer stop_id) {
        this.stop_id = stop_id;
    }

    public Stops getStop() {
        return stop;
    }

    public void setStop(Stops stop) {
        this.stop = stop;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
    @Override
    public String toString() {
        return "RouteStops [id=" + id + ", stop_number=" + stop_number + ", stop_time=" + stop_time + ", route_id="
                + route_id + ", stop_id=" + stop_id + ", stop=" + stop + "]";
    }

    @Override
    public int compareTo(RouteStops o) {
        return this.stop_number.compareTo(o.stop_number);
    }

}
