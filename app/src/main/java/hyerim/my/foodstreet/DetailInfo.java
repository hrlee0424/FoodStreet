package hyerim.my.foodstreet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailInfo extends Fragment {
    private String TAG = DetailInfo.class.getSimpleName();
    TextView info_description, info_tel, info_adress, info_homepage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        int mapx = intent1.getIntExtra("info_mapx",0);
        int mapy = intent1.getIntExtra("info_mapy",0);

        info_description.setText(description);
        info_tel.setText(tel);
        info_adress.setText(adress);
        info_homepage.setText(homepage);

        Log.i(TAG, "onClick: " + mapx);
        Log.i(TAG, "onClick: " + mapy);

    }
}
