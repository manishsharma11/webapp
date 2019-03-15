package com.main.sts;

import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;

import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.dto.VehicleGPSDataDTO;
import com.main.sts.entities.RouteStops;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.RouteStopsService;
import com.main.sts.service.VehicleTrackingService;

public class VehicleTrackingServiceSimulatorTest extends VehicleTrackingServiceTest {

    @Resource
    private RouteStopsService routeStopsService;

    @Test
    public void insertVehicleTrackingSimulatedParameters() {

        int route_id = 32;
        Set<RouteStops> rsSet = routeStopsService.getAllAvailableFromStops(route_id);

        for (RouteStops rs : rsSet) {
            VehicleGPSDataDTO dto = new VehicleGPSDataDTO();
            dto.setEc_device_id("58UU");//TEST
            //dto.setVehicle_number("AP281234");
            dto.setTrip_id(2);//6634;//13496
            dto.setGps_lat(rs.getStop().getLatitude());
            dto.setGps_long(rs.getStop().getLongitude());

            ReturnCodes returnCode = vehicleTrackingService.storeVehiclePos(dto);
            System.out.println("returnCode:" + returnCode);
        }
    }

    @Test
    public void testUserTracking() {
        UserGPSDataDTO dto = new UserGPSDataDTO();
        dto.setCommuter_id(766);
        dto.setGps_lat("17.416916");
        dto.setGps_long("78.346227");

        ReturnCodes returnCode = userTrackingService.storeUserPos(dto);
        System.out.println("returnCode:" + returnCode);
    }

}
