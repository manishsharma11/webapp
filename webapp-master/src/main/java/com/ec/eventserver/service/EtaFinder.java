package com.ec.eventserver.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EtaFinder {
	private static final Logger logger = Logger.getLogger(EtaFinder.class);
	@Bean
	public EtaFinder etaFinder(){
		return new EtaFinder();
	}
	public Double getEta(Double lat1,Double long1,Double lat2,Double long2,int speed) {
		//Double dis_to_arrive=null;
		Double min_to_arrive=null;
		String str = "https://maps.googleapis.com/maps/api/distancematrix/json?origins="
				+ lat1
				+ ","
				+ long1
				+ "&destinations="
				+ lat2
				+ "," + long2 + "&mode=driving&language=en-EN&sensor=false";

		//logger.info("API===>>"+str);

		String line;
		// String jsonstr;
		try {
			URL url = new URL(str);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			// get the response and show in console
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				// //System.out.println("The line is " + line);
			}
			// //System.out.println("The string is " + sb);
			JSONObject jsonResponse = new JSONObject(sb.toString());
			// //System.out.println("The status is " +jsonResponse.get("status"));
			/*****
			 * Returns the value mapped by name if it exists and is a JSONArray.
			 ***/
			/******* Returns null otherwise. *******/

			if (jsonResponse.get("status").equals("OK")) {
				JSONArray jsonMainNode = jsonResponse.getJSONArray("rows");

				/*********** Process each JSON Node ************/

				/****** Get Object for each JSON node. ***********/
				JSONObject jsonChildNode = jsonMainNode.optJSONObject(0);

				JSONArray secondNode = jsonChildNode.optJSONArray("elements");

				JSONObject secondChild = secondNode.getJSONObject(0);

				if (secondChild.get("status").equals("OK")) {

					JSONObject thirdChild = secondChild.getJSONObject("distance");

					//System.out.println("The distance is "
					// +thirdChild.getString("value"));

					//JSONObject fourthChild = secondChild
					//		.getJSONObject("duration");

					//String dis_in_mts_to_arrive = thirdChild.get("text").toString();
					String dis_in_mts_to_arrive = thirdChild.get("value").toString();
					String[] segments = dis_in_mts_to_arrive.split("\\s");
					double dis_to_arrive = Integer.parseInt(segments[0]);
					//logger.info("dis_to_arrive in find eta"+dis_to_arrive+"speed==>"+speed);
					//System.out.println("dis_to_arrive===>" + dis_to_arrive+"speed"+speed);
					if(speed==999)
					{		
						//logger.info("The distance travelled so far is"+dis_to_arrive);
						return dis_to_arrive;
					}
					else
					{
						if(speed==0)
						{
							//logger.info("The average speed is zero");
							min_to_arrive = (double) 0;
						}
						else
						{
							min_to_arrive = (dis_to_arrive/speed);
							//logger.info("min_to_arrive in ETA Finder"+min_to_arrive);
						}
					}
					/*String arrival_in_mins =fourthChild.get("text").toString();
					String[] segments = arrival_in_mins.split("\\s");
					min_to_arrive=Long.parseLong(segments[0]);*/
					//logger.info("min_to_arrive===>" + min_to_arrive);

				} else {
					logger.info("The api didnot return proper distance value");
				}
			} else {
				//System.out.println("The api didnot return proper json value");
			}

			/******* Fetch node values **********/
			reader.close();

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return min_to_arrive;
	}
}
