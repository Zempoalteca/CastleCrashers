package com.example.zaira.castlecrashers;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener  {

    private GoogleMap mMap;
    Localizacion local;
    Marker marcador;
    DBHelper db;
    int cont =0;

    ArrayList<Animal> animales= new ArrayList<>();
    public static final int VERPERFIL = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        db = new DBHelper(this);
        animales = db.getAllAnimales();

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
    public void onMapReady  (GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(19.4083302, -99.1729543);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.caballero_mapa);
        Bitmap b = bitmapDrawable.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, 150, 150, false);

        marcador = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        marcador.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        for (Animal Animal:animales){

            LatLng ubicacion = new LatLng(Animal.getLatitud(), Animal.getLongitud());
            BitmapDrawable bitmapDrawable1 = (BitmapDrawable) getResources().getDrawable(Animal.getImagen());
            Bitmap b1 = bitmapDrawable1.getBitmap();
            Bitmap smallMarker1 = Bitmap.createScaledBitmap(b1, 150, 150, false);
            Marker marcador1 = mMap.addMarker(new MarkerOptions().position(ubicacion));
            marcador1.setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
            int id = Animal.getId_animal();
            marcador1.setTag(Animal.getId_animal());
            mMap.setOnMarkerClickListener(this);

        }



    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();
        Intent intento = new Intent(MapsActivity.this, ArenaActivity.class);
        intento.putExtra("id_animal", clickCount);
        startActivity(intento);
        return false;
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

            marcador.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 18.5f));



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