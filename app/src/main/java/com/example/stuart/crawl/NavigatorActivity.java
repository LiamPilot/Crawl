package com.example.stuart.crawl;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NavigatorActivity extends FragmentActivity implements OnMapReadyCallback {

  private GoogleMap mMap;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_navigator);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    GoogleMapOptions mapOptions = new GoogleMapOptions()
        .compassEnabled(false)
        .rotateGesturesEnabled(false)
        .tiltGesturesEnabled(false);
    MapFragment.newInstance(mapOptions);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

  }

  private static String getParamsString(Map<String, String> params)
      throws UnsupportedEncodingException {
    StringBuilder result = new StringBuilder();

    for (Map.Entry<String, String> entry : params.entrySet()) {
      result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
      result.append("=");
      result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
      result.append("&");
    }

    String resultString = result.toString();
    return resultString.length() > 0
        ? resultString.substring(0, resultString.length() - 1)
        : resultString;
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


    // Add a marker in Sydney and move the camera
    String startInput = getIntent().getStringExtra("Start");
    String endInput = getIntent().getStringExtra("End");
    String[] startLatLng = startInput.split(",");
    LatLng start = new LatLng(Double.parseDouble(startLatLng[0]), Double.parseDouble(startLatLng[1]));
    String[] endLatLng = endInput.split(",");
    URL url;
    try {
      url = new URL("https://maps.googleapis.com");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      Map<String, String> parameters = new HashMap<>();
      parameters.put("origin", "40,40");
      parameters.put("destination", "50,50");
      connection.setDoInput(true);
      DataOutputStream out = new DataOutputStream(connection.getOutputStream());
      out.writeBytes(getParamsString(parameters));
      connection.setConnectTimeout(5000);
      connection.setReadTimeout(5000);

      BufferedReader in = new BufferedReader(
          new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer content = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        content.append(inputLine);
      }
      in.close();
      connection.disconnect();
      Log.v("result", content.toString());
    } catch (Exception e) {
      e.getStackTrace();
      return;
    }



    LatLng end = new LatLng(Double.parseDouble(endLatLng[0]), Double.parseDouble(endLatLng[1]));
    mMap.addMarker(new MarkerOptions().position(start).title("Marker is on start"));
    mMap.addMarker(new MarkerOptions().position(end).title("Marker is on end"));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(start));
  }
}
