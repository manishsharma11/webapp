package com.main.sts;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.PendingResult.Callback;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.GeocodingResult;
import com.main.sts.common.ServiceException;
import com.main.sts.dto.UserGPSDataDTO;
import com.main.sts.dto.VehicleGPSDataDTO;
import com.main.sts.entities.StopPosition;
import com.main.sts.entities.TrackingDetails;
import com.main.sts.service.ReturnCodes;
import com.main.sts.service.UserTrackingService;
import com.main.sts.service.VehicleTrackingService;

public class VehicleTrackingServiceTest extends BaseTest {

    @Resource
    protected VehicleTrackingService vehicleTrackingService;
    
    @Resource
    protected UserTrackingService userTrackingService;
    
  /*  @Resource
    private VehicleGpsTestDataDao testGpsDataDao;*/

    @Test
    public void testVehicleTrackingStore_1() {
        VehicleGPSDataDTO dto = new VehicleGPSDataDTO();
        dto.setEc_device_id("TEST");
        dto.setTrip_id(1);
        dto.setGps_lat("28.294");
        dto.setGps_long("12.1234");
        
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyD9hGyL2OZ9hdLdtwnznI4wsOzS3Fxo-YQ");
        DirectionsApiRequest req = DirectionsApi.getDirections(context, "17.4956659,78.3948759",
                "17.4196752,78.3470185");
        // Synchronous
        try {
            req.await();
            // Handle successful request.
        } catch (Exception e) {
            // Handle error
        }

        req.awaitIgnoreError(); // No checked exception.

        // Async
        req.setCallback(new Callback<DirectionsRoute[]>() {
            @Override
            public void onResult(DirectionsRoute[] result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable e) {
                // Handle error.
            }
        });

//        System.out.println("vehicleTrackingService:" + vehicleTrackingService);
//        ReturnCodes returnCode = vehicleTrackingService.storeVehiclePos(dto);
//        System.out.println("returnCode:" + returnCode);
    }
    
    @Test
    public void testVehicleTrackingStore() {
        VehicleGPSDataDTO dto = new VehicleGPSDataDTO();
        dto.setEc_device_id("TEST");
        dto.setTrip_id(1);
        dto.setGps_lat("28.294");
        dto.setGps_long("12.1234");

        System.out.println("vehicleTrackingService:" + vehicleTrackingService);
        ReturnCodes returnCode = vehicleTrackingService.storeVehiclePos(dto);
        System.out.println("returnCode:" + returnCode);
    }
    
    @Test
    public void testVehicleTracking() {
        int booking_id = 1433;
        TrackingDetails details = null;
        try {
            details = vehicleTrackingService.trackVehcileByBooking(booking_id);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println("stops:" + details);
        for (StopPosition sp : details.stops) {
            System.out.println(sp.getStop().getStop_name()+"---"+sp.getStop().latitude + "--" + sp.getStop().longitude);
        }
        System.out.println("tracking_points:" + details);
        for (StopPosition sp : details.tracking_points) {
            System.out.println(sp.getStop().latitude + "--" + sp.getStop().longitude);
        }
    }

    @Test
    public void testUserTracking() {
        UserGPSDataDTO dto = new UserGPSDataDTO();
        dto.setCommuter_id(1);
        dto.setGps_lat("28.294");
        dto.setGps_long("12.1234");

        ReturnCodes returnCode = userTrackingService.storeUserPos(dto);
        System.out.println("returnCode:" + returnCode);
    }
    
    @Test
    public void testInsertDummyDataFromTestTable(){
        
       /* java.util.List<VehicleGpsTestData> ls = testGpsDataDao.getAllRecords(VehicleGpsTestData.class);
        int trip_id =14;
        for (VehicleGpsTestData t : ls) {
            VehicleGPSDataDTO dto = new VehicleGPSDataDTO();
            dto.setEc_device_id(t.getEc_device_id());
            dto.setTrip_id(trip_id);
            dto.setGps_lat(t.getGps_lat());
            dto.setGps_long(t.getGps_long());
            System.out.println(dto);
            
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReturnCodes returnCode = vehicleTrackingService.storeVehiclePos(dto);
            System.out.println("returnCode:" + returnCode);
        }*/
    }

}
