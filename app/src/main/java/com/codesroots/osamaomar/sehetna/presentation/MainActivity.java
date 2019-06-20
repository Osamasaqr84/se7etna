package com.codesroots.osamaomar.sehetna.presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.codesroots.osamaomar.sehetna.Entities.SearchEntity;
import com.codesroots.osamaomar.sehetna.R;
import com.codesroots.osamaomar.sehetna.Utils.PreferenceHelper;
import com.codesroots.osamaomar.sehetna.presentation.Favourites.FavouritesFragment;
import com.codesroots.osamaomar.sehetna.presentation.details.CenterFragment;
import com.codesroots.osamaomar.sehetna.presentation.mainfragment.MainFragment;
import com.codesroots.osamaomar.sehetna.presentation.morefragment.MenuFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener,
        GoogleMap.OnInfoWindowClickListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 2;
    static TextView tvNameB;
    static EditText etSearch;
    BottomNavigationView bottomNavigationView;
    LocationManager locationManager;
    LatLng currentPlace;
    String placeId;
    private Marker searchresultMarker;

    View view;

    public Integer getUserID() {
        return userID;
    }

    Integer userID = 1;
    static View search_view, search_result;
    private static final String TAG = "message";
    private static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    private GoogleMap mMap;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/JF-Flat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).commit();
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_more:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MenuFragment()).addToBackStack(null).commit();
                    break;

                case R.id.discover:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new MainFragment()).addToBackStack(null).commit();
                    break;
                case R.id.favorit:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new FavouritesFragment()).addToBackStack(null).commit();
                    break;
            }
            return true;
        });
        search_view = findViewById(R.id.search_view);

        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView)).getMapAsync(this);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        search_view.setOnClickListener(v -> {
            try {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                        .build(this);
                startActivityForResult(intent, 1);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                Snackbar.make(getCurrentFocus(), "Can't search for address now", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setGoogleItem(true);
        searchEntity.setPlace_Id(placeId);
        CenterFragment fragment = new CenterFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("currItem", searchEntity);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.main_view, fragment, null).commit();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.d(TAG, "Place: " + place.getName());
                LatLng latLng = place.getLatLng();
                searchresultMarker = mMap.addMarker(new MarkerOptions().position(latLng).title(place.getName().toString()).snippet("اضغط هنا للتفاصيل "));
                searchresultMarker.showInfoWindow();
                placeId = place.getId();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latLng.latitude, latLng.longitude), 16.0f));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.d(TAG, status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }


        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {
            if (resultCode == RESULT_OK) {
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastKnownLocation != null && lastKnownLocation.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
                    currentPlace = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
                }
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentPlace.latitude, currentPlace.longitude), 16.0f));

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Toast.makeText(this, "تعذر الحصول على موقعك الحالي", Toast.LENGTH_LONG);

            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.setOnInfoWindowClickListener(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return;
        } else {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                PreferenceHelper.setCurrentLat((float) lastKnownLocation.getLatitude());
                PreferenceHelper.setCurrentLang((float) lastKnownLocation.getLongitude());
                currentPlace = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                if (mMap != null) {
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(currentPlace).title("currentLocation").snippet(""));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentPlace.latitude, currentPlace.longitude), 16.0f));
                }
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        }
    }

    public void changeBNVisibility(Integer i) {
        if (i == 0) {
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }

    public void changeSBVisibility(Integer i) {
        if (i == 0) {
            search_view.setVisibility(View.GONE);
        } else {
            search_view.setVisibility(View.VISIBLE);
        }

    }

    ////used in mainfragment
    public void gotoCurrentLocation() {
        currentPlace = new LatLng(PreferenceHelper.geCurrentlat(), PreferenceHelper.getCurrentlang());
        if (mMap != null) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(currentPlace).title("currentLocation").snippet(""));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentPlace.latitude, currentPlace.longitude), 16.0f));
        }
    }

    public void drawOnMap(List<LatLng> coords, List<String> names) {

        for (int i = 0; i < names.size(); i++) {
            mMap.addMarker(new MarkerOptions().position(coords.get(i))
                    .title(names.get(i)).snippet("")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }
        final LatLngBounds bounds = new LatLngBounds.Builder().include(coords.get(0)).include(coords.get(coords.size() - 1)).build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 40));
    }

    public void clearMap() {
        mMap.clear();
    }

    public LatLng getCurrentPlace() {
        return currentPlace;
    }


    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            Toast.makeText(this, "Location", Toast.LENGTH_LONG);
            currentPlace = new LatLng(location.getLatitude(), location.getLongitude());
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}
