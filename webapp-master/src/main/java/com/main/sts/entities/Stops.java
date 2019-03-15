package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "stops")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "stops")
public class Stops implements Comparable<Stops> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "stops_id_seq_gen")
    @SequenceGenerator(name = "stops_id_seq_gen", sequenceName = "stops_id_seq")
    private Integer id;
    private String stop_name;
    private String latitude;
    private String longitude;
    private String geofence;
    private String isAssigned;
    private Boolean enabled;
    private String shortcode;
    private String help_text;
    // type of the stop.
    private Integer stop_type;
    private String image_path;
    private String display_name;
    
    private String type;
    private Integer sibling_stop_id;
    
    @Transient
    private String image_icon_path;
    
    @Transient
    private String stop_type_name;
    
    // it will be comma seperated field in database.
    private String tags;

    @Transient
    private Boolean stop_icon_enabled = false;

    public int getId() {
        return id;
    }

    public String getStop_name() {
        return stop_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getGeofence() {
        return geofence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setGeofence(String geofence) {
        this.geofence = geofence;
    }

    @Override
    public int compareTo(Stops o) {
        // TODO Auto-generated method stub
        return (this.stop_name.compareTo(o.stop_name));
    }

    public String getIsAssigned() {
        return isAssigned;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getHelp_text() {
        return help_text;
    }

    public void setHelp_text(String help_text) {
        this.help_text = help_text;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getDisplay_name() {
        if(display_name==null){
        }
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSibling_stop_id() {
        return sibling_stop_id;
    }

    public void setSibling_stop_id(Integer sibling_stop_id) {
        this.sibling_stop_id = sibling_stop_id;
    }
    
    public Integer getStop_type() {
        return stop_type;
    }

    public void setStop_type(Integer stop_type) {
        this.stop_type = stop_type;
    }

    public String getImage_icon_path() {
        return image_icon_path;
    }

    public void setImage_icon_path(String image_icon_path) {
        this.image_icon_path = image_icon_path;
    }
    
    public String getStop_type_name() {
        return stop_type_name;
    }

    public void setStop_type_name(String stop_type_name) {
        this.stop_type_name = stop_type_name;
    }
    
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String[] getTagsArr() {
        if (tags == null || tags.length()==0) {
            return new String[]{};
        }
        return tags.split(",");
    }
    
    public Boolean getStop_icon_enabled() {
        return stop_icon_enabled;
    }

    public void setStop_icon_enabled(Boolean stop_icon_enabled) {
        this.stop_icon_enabled = stop_icon_enabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stop_name == null) ? 0 : stop_name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stops other = (Stops) obj;
        if (stop_name == null) {
            if (other.stop_name != null)
                return false;
        } else if (!stop_name.equals(other.stop_name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Stops [id=" + id + ", stop_name=" + stop_name + ", latitude=" + latitude + ", longitude=" + longitude
                + ", geofence=" + geofence + ", isAssigned=" + isAssigned + "]";
    }

}
