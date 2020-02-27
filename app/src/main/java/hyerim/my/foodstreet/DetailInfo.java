package hyerim.my.foodstreet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.util.Map;


public class DetailInfo extends Fragment implements OnMapReadyCallback {
    private String TAG = DetailInfo.class.getSimpleName();
    private TextView info_description, info_tel, info_adress, info_homepage;
    private int mapx,mapy;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_info, container, false);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        marker.setPosition(new LatLng( 36.763685,127.281796));
        Log.i(TAG, "onMapReady: " + new LatLng(mapx,mapy));
        marker.setMap(naverMap);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = getActivity().findViewById(R.id.detail_map);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        info_description = view.findViewById(R.id.info_description);
        info_tel = view.findViewById(R.id.info_tel);
        info_adress = view.findViewById(R.id.info_adress);
        info_homepage = view.findViewById(R.id.info_homepage);

        Intent intent1 = getActivity().getIntent();
///       String title = intent.getStringExtra("detail_title");
        String description = intent1.getStringExtra("info_description");
        String tel = intent1.getStringExtra("info_tel");
        String adress = intent1.getStringExtra("info_adress");
        String homepage = intent1.getStringExtra("info_homepage");
        mapx = intent1.getIntExtra("info_mapx",0);
        mapy = intent1.getIntExtra("info_mapy",0);

        info_description.setText(description);
        info_tel.setText(tel);
        info_adress.setText(adress);
        info_homepage.setText(homepage);

//        MapFragment mapFragment = (MapFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.detail_map);
//        if (mapFragment == null){
//            mapFragment = MapFragment.newInstance();
//            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.detail_map,mapFragment).commit();
//        }
//        mapFragment.getMapAsync(this);

    }
}
