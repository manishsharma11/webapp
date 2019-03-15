package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "stop_types")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "stop_types")
public class StopType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "stop_types_id_seq_gen")
    @SequenceGenerator(name = "stop_types_id_seq_gen", sequenceName = "stop_types_id_seq")
    private Integer id;
    private String stop_type_name;
    private String stop_icon_path;
    private Boolean enabled;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getStop_type_name() {
        return stop_type_name;
    }
    public void setStop_type_name(String stop_type_name) {
        this.stop_type_name = stop_type_name;
    }

    public String getStop_icon_path() {
        return stop_icon_path;
    }
    public void setStop_icon_path(String stop_icon_path) {
        this.stop_icon_path = stop_icon_path;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}
