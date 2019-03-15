package com.main.sts.entities;

import java.util.Comparator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "routes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "routes")
public class Routes implements Comparator {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "routes_id_seq_gen")
    @SequenceGenerator(name = "routes_id_seq_gen", sequenceName = "routes_id_seq")
    private int id;
    private String route_name;
    private String route_type;
    private String isAssigned;
    private Boolean enabled = true;
    
    private String display_name;

    public int getId() {
        return id;
    }
    public String getRoute_name() {
        return route_name;
    }
    public String getRoute_type() {
        return route_type;
    }
    public String getIsAssigned() {
        return isAssigned;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }
    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }
    public void setIsAssigned(String isAssigned) {
        this.isAssigned = isAssigned;
    }

    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
    
    @Override
    public String toString() {
        return "Routes [id=" + id + ", route_name=" + route_name + ", route_type=" + route_type + ", isAssigned="
                + isAssigned + "]";
    }
    @Override
    public int compare(Object o1, Object o2) {
        Routes r1 = (Routes) o1;
        Routes r2 = (Routes) o2;
        if (r1.id > r2.id)
            return 1;
        else

            return 0;
    }

}
