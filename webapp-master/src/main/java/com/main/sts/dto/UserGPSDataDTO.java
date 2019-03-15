package com.main.sts.dto;

public class UserGPSDataDTO extends GPSDataDTO {

    Integer commuter_id;

    public Integer getCommuter_id() {
        return commuter_id;
    }

    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }

    @Override
    public int compareTo(GPSDataDTO o) {
        UserGPSDataDTO o1 = (UserGPSDataDTO) o;
        int i = this.commuter_id.compareTo(o1.commuter_id);
        if (i != 0) {
            return i;
        }

        return super.compareTo(o1);
    }

}
