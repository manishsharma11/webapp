package com.ec.eventserver.service;

public class SystemProperties {

	private int port_number;
	private String time_zone;
	private double distance_to_find_stop;
	private double distance_to_find_bus_outof_stop;
	private int eta_send_time;
	private String is_crc_required;
	private String is_eta_required;
	private int message_worker_threads;
	private int message_queue_size;
	private String response_todevice;
	
	public String getResponse_todevice() {
		return response_todevice;
	}

	public void setResponse_todevice(String response_todevice) {
		this.response_todevice = response_todevice;
	}

	public int getMessage_queue_size() {
		return message_queue_size;
	}

	public void setMessage_queue_size(int message_queue_size) {
		this.message_queue_size = message_queue_size;
	}

	public int getMessage_worker_threads() {
		return message_worker_threads;
	}

	public void setMessage_worker_threads(int message_worker_threads) {
		this.message_worker_threads = message_worker_threads;
	}

	public int getEta_send_time() {
		return eta_send_time;
	}

	public void setEta_send_time(int eta_send_time) {
		this.eta_send_time = eta_send_time;
	}

	public int getPort_number() {
		return port_number;
	}

	public void setPort_number(int port_number) {
		this.port_number = port_number;
	}

	public String getTime_zone() {
		return time_zone;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public double getDistance_to_find_stop() {
		return distance_to_find_stop;
	}

	public void setDistance_to_find_stop(double distance_to_find_stop) {
		this.distance_to_find_stop = distance_to_find_stop;
	}

	public double getDistance_to_find_bus_outof_stop() {
		return distance_to_find_bus_outof_stop;
	}

	public void setDistance_to_find_bus_outof_stop(double distance_to_find_bus_outof_stop) {
		this.distance_to_find_bus_outof_stop = distance_to_find_bus_outof_stop;
	}

	public String getIs_crc_required() {
		return is_crc_required;
	}

	public void setIs_crc_required(String is_crc_required) {
		this.is_crc_required = is_crc_required;
	}

	public String getIs_eta_required() {
		return is_eta_required;
	}

	public void setIs_eta_required(String is_eta_required) {
		this.is_eta_required = is_eta_required;
	}

	@Override
	public String toString() {
		return "SystemProperties [port_number=" + port_number + ", time_zone=" + time_zone + ", distance_to_find_stop="
				+ distance_to_find_stop + ", distance_to_find_bus_outof_stop=" + distance_to_find_bus_outof_stop
				+ ", eta_send_time=" + eta_send_time + ", is_crc_required=" + is_crc_required + ", is_eta_required="
				+ is_eta_required + ", message_worker_threads=" + message_worker_threads + ", message_queue_size="
				+ message_queue_size + ", response_todevice=" + response_todevice + "]";
	}

	

}
