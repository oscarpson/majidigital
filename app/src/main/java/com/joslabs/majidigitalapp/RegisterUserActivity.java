package com.joslabs.majidigitalapp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private GoogleMap googleMap;
    MapView mapView;
    LatLng meterLocation;
    Button btnreg;
    EditText cid, cname, cphone, cemail, ctype, czone, meterid, edtlocation;
    String locationdata;

    private static final int REQUEST_LOCATION_PERMISSION_CODE = 101;
    private GoogleApiClient googleApiClient;
    final static String ORDERS_URL = "http://olsen.joslabs.co.ke/mobileapp/getmyorders.php";
    private static final String TAG = "MainActivity";
    LocationRequest mLocationRequest;
    Location mLastKnownLocation;
    String locationgeodata;
    CoordinatorLayout relativemap;
    //FollowMeLocationSource
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapView = findViewById(R.id.map);


        mapView.onCreate(savedInstanceState);

        mapView.onResume();
        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION_CODE);
        }
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
        locationChecker(googleApiClient, this);
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        return false;

                    }
                });
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                Log.e("mlastb", "" + LocationServices.FusedLocationApi
                        .getLastLocation(googleApiClient));

                if (location==null) {
                    //LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
                    mLastKnownLocation = LocationServices.FusedLocationApi
                            .getLastLocation(googleApiClient);
                    // handleNewLocation(mLastKnownLocation);
                    //  location.getLatitude();
                    // location.getLongitude();
                    // LatLng near=new LatLng(location.getLatitude(),location.getLongitude());
                    //CameraPosition cameraPosition=new CameraPosition.Builder().target(near).zoom(15).build();
                    // googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                       // Intent meterLocation=new Intent(getApplicationContext(),MeterLocation.class);
                       // startActivity(meterLocation);
                    }
                });
                googleMap = mMap;
                String coords="-1.381811,38.002383";
                String[] latslong = coords.split(",");
                Log.e("coods", latslong[0] + "and longs\n" + latslong[1]);
                String lats = latslong[0];
                float lat = Float.valueOf(latslong[0]);
                float llong = Float.valueOf(latslong[1]);
                LatLng petrol = new LatLng(lat, llong);

                CameraPosition cameraPosition=new CameraPosition.Builder().target(petrol).zoom(8).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        return false;
                    }
                });
                googleMap.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
                    @Override
                    public void onMyLocationClick(@NonNull Location location) {
                        Log.e("locationclick",location+"");
                        LatLng loc=new LatLng(location.getLatitude(),location.getLongitude());
                        googleMap.clear();
                        CameraPosition cameraPosition=new CameraPosition.Builder().target(loc).zoom(15).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        googleMap.addCircle(new CircleOptions()
                                .center(loc)
                                .radius(250)
                                .strokeWidth(0f)
                                .fillColor(0x1100CCFF) //this is a half transparent blue, change "88" for the transparency
                                .strokeColor(R.color.colorPrimaryDark)
                                .strokeWidth(3)
                        );
                        locationgeodata=location.getLatitude()+","+location.getLongitude();
                        edtlocation.setText(locationgeodata);
                    }
                });

            }

        });
        cid=findViewById(R.id.edtcid);
        cname=findViewById(R.id.cname);
        cphone=findViewById(R.id.cphone);
        cemail=findViewById(R.id.cemail);
        ctype=findViewById(R.id.edt_customer_type);
        czone=findViewById(R.id.czone);
        meterid=findViewById(R.id.edt_meter_id);
        edtlocation=findViewById(R.id.edt_location);
        btnreg=findViewById(R.id.btnsave);
        relativemap=findViewById(R.id.relativemap);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationgeodata != null || locationgeodata != " ") {
                    String id = cid.getText().toString();
                    String name = cname.getText().toString();
                    String phone = cphone.getText().toString();
                    String type = ctype.getText().toString();
                    String email = cemail.getText().toString();
                    String meter = meterid.getText().toString();
                    String zone = czone.getText().toString();
                    final ProgressDialog loading = ProgressDialog.show(v.getContext(), "Fetching...", "Please wait...", false, false);
                    Call<ResponseCallback> response = RestClient.getApiService().add_user(id, name, phone, email, type, zone, meter, locationgeodata);
                    response.enqueue(new Callback<ResponseCallback>() {
                        @Override
                        public void onResponse(Call<ResponseCallback> call, Response<ResponseCallback> responseb) {
                            loading.dismiss();
                            Log.e("msg", responseb.body().getMessage());
                        }

                        @Override
                        public void onFailure(Call<ResponseCallback> call, Throwable t) {
                            Log.e("msg", t.getMessage());
                        }
                    });
                }
                else {
                    SnackClass.setErrorSnackbar(relativemap,"Geo data is empty please click location icon on map and press blue circle");
                }
            }
        });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Location changed", Toast.LENGTH_SHORT).show();
        Log.e("locationChange","location"+location);
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
    public void locationChecker(final GoogleApiClient mGoogleApiClient, final Activity activity) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.

                        //  Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                        //  handleNewLocation(location);


                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    activity, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }
}
