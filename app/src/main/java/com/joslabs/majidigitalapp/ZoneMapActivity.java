package com.joslabs.majidigitalapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.joslabs.majidigitalapp.SnackClass.setSnackBar;

public class ZoneMapActivity extends FragmentActivity  implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleMap mMap;

    private GoogleMap googleMap;
    GoogleApiClient mGoogleApiClient;
    MapView mapView;
    String latsLongs, dates, times, datestime, servicetypename, firebaseId, clientId, serviceId, serviceProvidename, markertype,markert, sproviderId, sphourpay,url,image;
    List<MarkerTag> nexts;
    Marker mymarker, advertmarker,advertmarkerb;
    Button btndate, btntime, btnschedule, btnorder, btnnext;
    private int mYear, mMonth, mDay, mHour, mMinute,x,maxPoints,p;
    RelativeLayout layercost, layertime, relativemap,layerservice;
    TextView servicetype, servicepay, paymode, timedate, spname, sproviderIdview,tvTitle,tvSnippet,advname,advurl,advdesc,txtsfireid,txtnext,txtprev;
    ImageView imgadvert,advimg,imgnext,imgprevious;
    List<UserModel>userModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();
        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        buildGoogleApiClient();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Log.e("markx", sphourpay + "\n" + sproviderId + "\n" + marker.getTitle());
                        Log.e("MARKERID", marker.getId());

                        // if (marker.getTag() instanceof MarkerTag)
                        // {

                        // if (marker.equals(mymarker)) {
                        markert = marker.getTitle();
                        MarkerTag markerTag = (MarkerTag) marker.getTag();
                        // markertype = markerTag.getMarkertype();
                        // if (markertype.equals("1")) {
                        sproviderId = markerTag.getSproviderId();
                        sphourpay = markerTag.getSpname();
                        firebaseId = markerTag.getFirebaseId();
                        serviceProvidename = markerTag.getUsername();

                        marker.hideInfoWindow();
                        showmarker(marker.getTitle(), sphourpay, sproviderId, firebaseId);
                        //  }
                        //was return false
                        return false;

                    }
                });
                googleMap = mMap;
                mGoogleApiClient.connect();
userModelList=new ArrayList<>();
                LatLng petrols = new LatLng(-1.2179869, 36.8902669);
                populateData();


            }
        });





        timedate = (TextView) findViewById(R.id.txtstimedate);
        servicepay = (TextView) findViewById(R.id.txthourpayvalue);
        spname = (TextView) findViewById(R.id.txtspname);
        servicetype = (TextView) findViewById(R.id.txtstypename);
        relativemap = (RelativeLayout) findViewById(R.id.relativemap);
        Bundle extras = getIntent().getExtras();
        //servicetypename = extras.getString("servicename");
        servicetypename="Cakes";
        //serviceId = extras.getString("serviceId");


        // sproviderIdview = (TextView) findViewById(R.id.txtsproviderId);
        imgnext = (ImageView) findViewById(R.id.imgnext);
        imgprevious = (ImageView) findViewById(R.id.imgprevious);

        txtnext = (TextView) findViewById(R.id.txtnext);
        txtprev = (TextView) findViewById(R.id.txtprev);

        imgnext.setOnClickListener(this);
        imgprevious.setOnClickListener(this);



        dates = "";
        times = "";

        x = 0;


    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        System.out.println("ABC buildGoogleApiClient map was invoked: ");
    }
    private void populateData() {
        nexts = new ArrayList<MarkerTag>();

//        new LatLng(-1.198501943525175,36.90164655447006),
//                new LatLng(-1.199922197330275,36.90031751990318),
//                new LatLng(-1.200915402467943,36.898950934410095),
//                new LatLng(-1.2021301759964402,36.897456273436546),
//                new LatLng(-1.2160577973145459,36.892214566469185),
//                new LatLng(-1.2730771918082914,36.900918669998646),
//                new LatLng(-1.2828743952053982,36.9014598056674),
//                new LatLng(-1.2927128283142088,36.90180849283934),
//                new LatLng(-1.302754393241491,36.90216757357121);
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching...", "Please wait...", false, false);
        Call<UserCallback> responseCallbackCall=RestClient.getApiService().user_list();
        responseCallbackCall.enqueue(new Callback<UserCallback>() {
            @Override
            public void onResponse(Call<UserCallback> call, Response<UserCallback> response) {
                loading.dismiss();
                userModelList=response.body().getUsers();
                for (UserModel user:userModelList
                     ) {
                    String []latslong=user.getCity().split(",");
                    Log.e("coods",latslong[0]+"and longs\n"+latslong[1]);
                    String lats=latslong[0];
                    float lat=Float.valueOf(latslong[0]);
                    float llong=Float.valueOf(latslong[1]);
                    //   googleMap.setMyLocationEnabled(true);
                    //flat=new LatLng(flat)
                    LatLng splocation = new LatLng(lat,llong);
                    MarkerTag t1=new MarkerTag(user.getZone(),"ooprovi","2","1","username",user.getFirst_name(),"Nairobi West", splocation,"fid");
                    nexts.add(t1);
                    mymarker=googleMap.addMarker(new MarkerOptions().position(splocation).title(user.getZone()).snippet(user.getFirst_name()));
                    mymarker.setTag(t1);
                }

                Log.e("listuser",response.body().getUsers()+"emp");
                CameraPosition cameraPosition = new CameraPosition.Builder().target(nexts.get(1).getLats()).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                showmarker(nexts.get(1).getSpname(), nexts.get(1).getDesc(), "id", "firebase");
            }

            @Override
            public void onFailure(Call<UserCallback> call, Throwable t) {
                loading.dismiss();
                Log.e("error",t.getMessage());
            }
        });
      /*  MarkerTag t1=new MarkerTag("Nairobi Area","ooprovi","2","1","username","opposite shop","Nairobi West", new LatLng(-1.198501943525175,36.90164655447006),"fid");
        MarkerTag t2=new MarkerTag("Kasarani","ooprovi","2","1","username","opposite shop1","Kasarani", new LatLng(-1.199922197330275,36.90031751990318),"fid");
        MarkerTag t3=new MarkerTag("Roysambu","ooprovi","2","1","username","opposite shop2","Roysambu",  new LatLng(-1.2021301759964402,36.897456273436546),"fid");
        MarkerTag t4=new MarkerTag("Githiurai","ooprovi","2","1","username","opposite shop3","Githiurai", new LatLng(-1.2730771918082914,36.900918669998646),"fid");
        MarkerTag t5=new MarkerTag("Kwa Maiko","ooprovi","2","1","username","opposite shop4","Maiko",  new LatLng(-1.302754393241491,36.90216757357121),"fid");
        nexts.add(t1);
        nexts.add(t2);
        nexts.add(t3);
        nexts.add(t4);
        nexts.add(t5);

        x=0;
        for (MarkerTag m:nexts
        ) {
            mymarker=googleMap.addMarker(new MarkerOptions().position(m.getLats()).title(m.getSpname()).snippet("Zone location"));
            mymarker.setTag(m);
        }
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_mapicon);

*/

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private void showmarker(String markert, String sphourpay, String sproviderId, String firebaseId) {

        //Toast.makeText(this, ""+markert, Toast.LENGTH_SHORT).show();
        servicepay.setText(markert);
        spname.setText(sphourpay);
        //sproviderIdview.setText("pid");
        servicetypename="Meter zone ";
        servicetype.setText(servicetypename);

//        txtsfireid.setText(firebaseId);


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setTrafficEnabled(true);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        if (v==imgnext){
            Log.e("maxp",maxPoints+"\narrysz"+nexts.size());
            x++;
            if (x < nexts.size()) {

                try {
                    Log.e("logx", nexts.get(x).getImgadvert() + "\n" + nexts.get(x).getSpname() + "\t" + nexts.get(x).getFirebaseId());
                    //mymarker=googleMap.addMarker(new MarkerOptions().position(nexts.get(x).getLats()).title(nexts.get(x).getSpname()).snippet("Towerservices").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapicon)));

                    CameraPosition cameraPosition = new CameraPosition.Builder().target(nexts.get(x).getLats()).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    showmarker(nexts.get(x).getUsername(), nexts.get(x).getSpname(), nexts.get(x).getSproviderId(), nexts.get(x).getFirebaseId());
                    p = x;
                }catch (Exception e)
                {

                }
            }
            else {
                Toast.makeText(this, "No previous data", Toast.LENGTH_SHORT).show();
                setSnackBar(relativemap,"No more data");
                imgnext.setVisibility(View.GONE);
                txtnext.setVisibility(View.GONE);
                imgprevious.setVisibility(View.VISIBLE);
                txtprev.setVisibility(View.VISIBLE);
            }

        }if (v==imgprevious){

            if (p> 0 && p < nexts.size()) {
                p--;
                Log.e("logx", nexts.get(p).getImgadvert() + "\n" + nexts.get(p).getSpname()+"\t"+nexts.get(p).getFirebaseId()+"\n xvalue"+x+"\np"+p);
                // mymarker=googleMap.addMarker(new MarkerOptions().position(nexts.get(x).getLats()).title(nexts.get(x).getSpname()).snippet("Towerservices").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mapicon)));

                CameraPosition cameraPosition = new CameraPosition.Builder().target(nexts.get(p).getLats()).zoom(15).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                showmarker(nexts.get(p).getUsername(), nexts.get(p).getSpname(), nexts.get(p).getSproviderId(), nexts.get(p).getFirebaseId());
                x=p;


            }
            else {
                Toast.makeText(this, "No previous data", Toast.LENGTH_SHORT).show();
                setSnackBar(relativemap,"No previous data");
                imgprevious.setVisibility(View.GONE);
                txtprev.setVisibility(View.GONE);
                imgnext.setVisibility(View.VISIBLE);
                txtnext.setVisibility(View.VISIBLE);

            }

        }
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
}
