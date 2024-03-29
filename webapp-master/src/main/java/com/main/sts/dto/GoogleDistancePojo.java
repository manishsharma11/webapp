/* Generated by JavaFromJSON */
/*http://javafromjson.dashingrocket.com*/

package com.main.sts.dto;

import java.util.Arrays;

public class GoogleDistancePojo {
	@org.codehaus.jackson.annotate.JsonProperty("rows")
	private RowElement[] rows;

 	public void setRows(RowElement[] rows) {
		this.rows = rows;
	}

	public RowElement[] getRows() {
		return rows;
	}

	@org.codehaus.jackson.annotate.JsonProperty("status")
	private java.lang.String status;

 	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getStatus() {
		return status;
	}

	@org.codehaus.jackson.annotate.JsonProperty("destination_addresses")
	private java.lang.String[] destination_addresses;

 	public void setDestination_addresses(java.lang.String[] destination_addresses) {
		this.destination_addresses = destination_addresses;
	}

	public java.lang.String[] getDestination_addresses() {
		return destination_addresses;
	}

	@org.codehaus.jackson.annotate.JsonProperty("origin_addresses")
	private java.lang.String[] origin_addresses;

 	public void setOrigin_addresses(java.lang.String[] origin_addresses) {
		this.origin_addresses = origin_addresses;
	}

	public java.lang.String[] getOrigin_addresses() {
		return origin_addresses;
	}

	@Override
	public String toString() {
		return "GoogleDistancePojo [rows=" + Arrays.toString(rows)
				+ ", status=" + status + ", destination_addresses="
				+ Arrays.toString(destination_addresses)
				+ ", origin_addresses=" + Arrays.toString(origin_addresses)
				+ "]";
	}
	
	

}