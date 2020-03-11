package hyerim.my.foodstreet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.util.GeoTransPoint;
import hyerim.my.foodstreet.util.GeoTrans;
import hyerim.my.foodstreet.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;


public class DetailInfo extends Fragment implements OnMapReadyCallback {
    private String TAG = DetailInfo.class.getSimpleName();
    private TextView info_description, info_tel, info_adress, info_homepage;
    private MapView mapView;
    private GeoTransPoint katec_pt, out_pt;
    private FragmentManager fm ;
    private ItemObject itemObject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_info, container, false);

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.detail_map);
        if (mapFragment == null){
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.detail_map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        /*mapview 사용시
        mapView = view.findViewById(R.id.detail_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        mapView.setFocusable(true);
        mapView.setTranslationX(mapx);
        mapView.setTranslationY(mapy);
         */

        info_description = view.findViewById(R.id.info_description);
        info_tel = view.findViewById(R.id.info_tel);
        info_adress = view.findViewById(R.id.info_adress);
        info_homepage = view.findViewById(R.id.info_homepage);

        Intent intent = getActivity().getIntent();
        itemObject = new Gson().fromJson(intent.getStringExtra("info"), ItemObject.class);

        info_description.setText(itemObject.description);
        info_tel.setText(itemObject.telephone);
        info_adress.setText(itemObject.roadAddress);
        info_homepage.setText(itemObject.link);

        return view;
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        Marker marker = new Marker();
        katec_pt = new GeoTransPoint(itemObject.mapx,itemObject.mapy);
//        System.out.println("katec : xKATEC=" + katec_pt.getX() + ", yKATEC=" + katec_pt.getY());
        out_pt = GeoTrans.convert(GeoTrans.KATEC, GeoTrans.GEO, katec_pt);
//        System.out.println("geo out : xGeo=" + out_pt.getX() + ", yGeo=" + out_pt.getY());
        LatLng latLng = new LatLng(out_pt.getY(),out_pt.getX());
        CameraPosition cameraPosition = new CameraPosition(latLng,16);
        naverMap.setCameraPosition(cameraPosition);
        marker.setPosition(new LatLng(out_pt.getY(),out_pt.getX()));
        marker.setMap(naverMap);
    }

    /*mapview 사용시
    *
    *        @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

*/



}
