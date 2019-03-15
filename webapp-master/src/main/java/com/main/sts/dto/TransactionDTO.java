package com.main.sts.dto;

import java.io.Serializable;

public class TransactionDTO implements Serializable {

    Integer commuter_id;
    Integer offset;
    Integer limit;
    
    public Integer getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(Integer commuter_id) {
        this.commuter_id = commuter_id;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
