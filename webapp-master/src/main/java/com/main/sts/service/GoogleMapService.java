package com.main.sts.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.main.sts.dto.GoogleDistancePojo;
import com.main.sts.service.VehicleTrackingService.DistanceMatrix;

@Service("googleMapService")
public class GoogleMapService {

	// String myURL =
	// "https://maps.googleapis.com/maps/api/distancematrix/json?"
	// + "origins=17.446251,78.349546&destinations=17.451533,78.380947";
	public static final String GOOGLEMAP_DISTANCE_URL = "https://maps.googleapis.com/maps/api/distancematrix/json?"
			+ "origins=%s,%s&destinations=%s,%s";

    public DistanceAndTimeDuration getDistanceAndTimeDuration(DistanceMatrix dm) {

        GoogleDistancePojo distance = findDistance(dm);
        System.out.println(distance);

        // distance
        System.out.println(distance.getRows()[0].getElements()[0].getDistance().getText());
        // duration
        System.out.println(distance.getRows()[0].getElements()[0].getDuration().getText());

        DistanceAndTimeDuration dtd = new DistanceAndTimeDuration();
        dtd.distance = distance.getRows()[0].getElements()[0].getDistance().getText();
        dtd.time_duration = distance.getRows()[0].getElements()[0].getDuration().getText();

        System.out.println(dtd);
        return dtd;
    }
    
	public GoogleDistancePojo findDistance(DistanceMatrix dm) {

		String urlStr = String.format(GOOGLEMAP_DISTANCE_URL, dm.vehicle_lat,
				dm.vehicle_long, dm.booking_stop_lat, dm.booking_stop_long);

		System.out.println("Requeted URL:" + urlStr);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(urlStr);
			urlConn = url.openConnection();
			if (urlConn != null)
				//urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + urlStr,
					e);
		}
		String json = sb.toString();
		if (json != null) {
			return new Gson().fromJson(json, GoogleDistancePojo.class);
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		GoogleMapService mapService = new GoogleMapService();
		DistanceMatrix dm = new DistanceMatrix();
		dm.vehicle_lat = "17.446251";
		dm.vehicle_long = "78.349546";
		dm.booking_stop_lat = "17.451533";
		dm.booking_stop_long = "78.380947";

		GoogleDistancePojo distance = mapService.findDistance(dm);
		System.out.println(distance);

		// distance
		System.out.println(distance.getRows()[0].getElements()[0].getDistance().getText());
		// duration
		System.out.println(distance.getRows()[0].getElements()[0].getDuration().getText());
		
		DistanceAndTimeDuration dtd = new DistanceAndTimeDuration();
		dtd.distance = distance.getRows()[0].getElements()[0].getDistance().getText();
    dtd.time_duration = distance.getRows()[0].getElements()[0].getDuration().getText();

    System.out.println(dtd);

	}
	
    public static class DistanceAndTimeDuration {
        public String distance;
        public String time_duration;
        
        @Override
        public String toString() {
            return "DistanceAndTimeDuration [distance=" + distance + ", timeduration=" + time_duration + "]";
        }
    }
}
