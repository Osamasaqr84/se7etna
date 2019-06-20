package com.codesroots.osamaomar.sehetna.Utils;

import android.location.Location;

public class LocationUtils {
    public static Integer getDistance(Double srcLat, Double srcLng, Double desLat, Double desLng) {

        Location srcLocation = new Location("a");
        srcLocation.setLatitude(srcLat);
        srcLocation.setLongitude(srcLng);
        Location desLocation = new Location("b");
        desLocation.setLatitude(desLat);
        desLocation.setLongitude(desLng);
        float distance = srcLocation.distanceTo(desLocation);

        int dis =  Math.round(srcLocation.distanceTo(desLocation) / 1000) /*Integer.valueOf(Float.valueOf().toString())*/;
        return dis;

    }

   /* private Location getLastKnownLocation(LocationManager mLocationManager , Context context) {
        mLocationManager = (LocationManager) context.getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return ;
            }
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }*/

}
