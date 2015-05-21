package christopherfrida.christopherfridasweddingapp;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends AppCompatActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleMapOptions mapOptions;

    //KYRKAN
    private final static double LONGITUDE_CHURCH = 15.724973;
    private final static double LATITUDE_CHURCH = 58.369521;

    //HEMBYGDSGÅRDEN
    private final static double LONGITUDE_PARTY = 15.715126;
    private final static double LATITUDE_PARTY = 58.378309;

    private LatLng churchLocation = new LatLng(LATITUDE_CHURCH, LONGITUDE_CHURCH);
    private LatLng partyLocation = new LatLng(LATITUDE_PARTY, LONGITUDE_PARTY);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker").snippet("Snippet"));


        // Enable MyLocation Layer of Google Map
        //mMap.setMyLocationEnabled(true);

        // Get LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Create a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Get the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Get Current Location
        Location myLocation = locationManager.getLastKnownLocation(provider);

        // set map type
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Show the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(churchLocation));
        mMap.addMarker(new MarkerOptions().position(churchLocation).
                title("Landeryds kyrka").snippet("Här gifter vi oss!")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.wedding_icon2)));

        mMap.addMarker(
                new MarkerOptions().position(partyLocation)
                        .title("Landeryds hembygdsgård").snippet("Här är festen")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.party_icon2)));


    }
}
