package com.ec.eventserver.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.main.sts.entities.Buses;
import com.main.sts.entities.Drivers;

@Entity
@Table(name = "device_info")
public class DeviceInfo implements Serializable {

    // @GeneratedValue(strategy = GenerationType.IDENTITY, generator =
    // "device_seq_gen")
    // @SequenceGenerator(name = "device_seq_gen", sequenceName =
    // "device_id_seq")
    // private int id;

    @Id
    private String ec_device_id;
    
    private String hw_device_id;

    @Column(name = "vehicle_id")
    private int vehicle_id;

    @Transient
    private String vehicle_number;
    
//    @Transient
//    @OneToOne(targetEntity = Drivers.class)
//    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", insertable = false, updatable = false)
//    public Buses vehicles;

    private Date created_at;
    private Date updated_at;

    // public int getId() {
    // return id;
    // }
    // public void setId(int id) {
    // this.id = id;
    // }
    public String getHw_device_id() {
        return hw_device_id;
    }
    public void setHw_device_id(String hw_device_id) {
        this.hw_device_id = hw_device_id;
    }
    public String getEc_device_id() {
        return ec_device_id;
    }
    public void setEc_device_id(String ec_device_id) {
        this.ec_device_id = ec_device_id;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public int getVehicle_id() {
        return vehicle_id;
    }
    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }
    public String getVehicle_number() {
        return vehicle_number;
    }
    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }
    @Override
    public String toString() {
        return "DeviceInfo [ec_device_id=" + ec_device_id + ", hw_device_id=" + hw_device_id + ", vehicle_id="
                + vehicle_id + ", vehicle_number=" + vehicle_number + ", created_at=" + created_at + ", updated_at="
                + updated_at + "]";
    }

}
