package com.tm.arifin.timbangan.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Location;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.tm.arifin.timbangan.AppConfig;
import com.tm.arifin.timbangan.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class SurveyActivity extends AppCompatActivity  implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener{

    private TextView mTitleView;
    private Toolbar mToolbar;
    int awal=0;
    RelativeLayout info_pelanggan, data_survey, tab_maps;

    String[] JENIS_PEMAKAIAN = {"Rumah", "Kantor", "Hotel", "Pabrik", "Dll"};
    String[] STATUS_RUMAH = {"Tetap", "Berpindah"};
    String[] DIJUAL = {"Iya", "Tidak"};

    //================== map ====================
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LatLng latLng;
    GoogleMap mGoogleMap;
    Marker currLocationMarker;
    private GoogleMap mMap;

    SupportMapFragment mapFragment;
    CoordinatorLayout coordinatorLayout;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        awal=0;

        this.mToolbar = ((Toolbar)findViewById(R.id.my_toolbar));
        this.mTitleView = ((TextView)findViewById(R.id.toolbar_title));
        setSupportActionBar(this.mToolbar);
        this.mTitleView.setText(AppConfig.NAMA_PELANGGAN);

        mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        info_pelanggan = (RelativeLayout) findViewById(R.id.info_pelanggan);
        data_survey = (RelativeLayout) findViewById(R.id.data_survey);
        tab_maps = (RelativeLayout) findViewById(R.id.data_map);

        ArrayAdapter<String> jenis_pemakaianAdp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, JENIS_PEMAKAIAN);
        MaterialBetterSpinner jenis_pemakaian = (MaterialBetterSpinner)
                findViewById(R.id.jenis_pemakaian);
        jenis_pemakaian.setAdapter(jenis_pemakaianAdp);

        ArrayAdapter<String> status_rumahAdp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, STATUS_RUMAH);
        MaterialBetterSpinner status_rumah = (MaterialBetterSpinner)
                findViewById(R.id.status_rumah);
        status_rumah.setAdapter(status_rumahAdp);

        ArrayAdapter<String> dijualAdp = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, DIJUAL);
        MaterialBetterSpinner dijual = (MaterialBetterSpinner)
                findViewById(R.id.menjual_air);
        dijual.setAdapter(dijualAdp);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_info) {
                    if(awal == 1) {
                        Log.e("klik tab","info");
                        info_pelanggan.setVisibility(View.VISIBLE);
                        data_survey.setVisibility(View.GONE);
                        tab_maps.setVisibility(View.GONE);
                    }
                }
                else if (tabId == R.id.tab_survey) {
                    if(awal == 1) {
                        Log.e("klik tab","survey");
                        info_pelanggan.setVisibility(View.GONE);
                        tab_maps.setVisibility(View.GONE);
                        data_survey.setVisibility(View.VISIBLE);
                    }
                }
                else if (tabId == R.id.tab_maps) {
                    if(awal == 1) {
                        Log.e("klik tab","survey");
                        info_pelanggan.setVisibility(View.GONE);
                        data_survey.setVisibility(View.GONE);
                        tab_maps.setVisibility(View.VISIBLE);
                    }
                }

                awal =1;

            }
        });

    }



    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "onConnected", Toast.LENGTH_SHORT).show();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);

        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mGoogleMap.addMarker(markerOptions);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);


    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currLocationMarker = mGoogleMap.addMarker(markerOptions);

        //Toast.makeText(this, "Location Changed", Toast.LENGTH_SHORT).show();

        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(12).build();

        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        awal=1;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "onConnectionFailed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("sadasdas","ASdasd");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mGoogleMap.setMyLocationEnabled(true);

        buildGoogleApiClient();
        Log.e("sadasdas","2");

        mGoogleApiClient.connect();
    }

    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this,"buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        Log.e("sadasdas","build");
    }

}
