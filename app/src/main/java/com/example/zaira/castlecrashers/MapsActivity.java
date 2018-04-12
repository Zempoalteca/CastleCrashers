package com.example.zaira.castlecrashers;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Localizacion local;
    Marker marcador;
    boolean marcadorlisto = false;
    public static final int VERPERFIL = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
             Manifest.permission.ACCESS_FINE_LOCATION},43);
        final LocationManager mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        local = new Localizacion();

        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ((LocationListener)local));

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        marcador=
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public  class Localizacion implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            String text = "Mi ubicacion actual es : \n" +
                    "Latitud: " + location.getLatitude()
                    + "\nLongitud: " + location.getLongitude();
            //  mensaje2.setText(text);
           //
            LatLng ubicacion = new LatLng(location.getLatitude(), location.getLongitude());
            setLocation(location);

            if (!marcadorlisto){
                BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.caballero_mapa);
                Bitmap b = bitmapDrawable.getBitmap();
                Bitmap smallMarker = Bitmap.createScaledBitmap(b, 200, 200, false);

                Marker marcador = mMap.addMarker(new MarkerOptions().position(ubicacion));

                marcador.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 18.5f));
                marcadorlisto=true;
            }else {
                marcador.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 18.5f));
            }


        }
        public void setLocation(Location loc){
            if (loc.getLongitude()!=0.0 && loc.getLatitude()!=0.0){
                try{
                    Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                    List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(),1);
                    if (!list.isEmpty()){
                        Address calle = list.get(0);

                        //mensajes3.setText("Mi direccion actual es:"+ calle.getAddressLine(0)+"\n"+ calle.getAddressLine(1));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {
            Toast.makeText(MapsActivity.this, "El GPS esta activado", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String s) {
            Toast.makeText(MapsActivity.this, "El GPS esta desactivado :C", Toast.LENGTH_SHORT).show();

        }
    }

    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, VERPERFIL, Menu.NONE, "Ver Perfil ");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case VERPERFIL:
                Toast.makeText(this, "ver perfil", Toast.LENGTH_SHORT).show();
                verperfil();
                break;
        }

        return true;
    }

    public void verperfil(){
        Intent intento = new Intent(MapsActivity.this, DetalleJugadorActivity.class);
        //intento.putExtra("carrito", carrito);
        startActivity(intento);
    }


}
