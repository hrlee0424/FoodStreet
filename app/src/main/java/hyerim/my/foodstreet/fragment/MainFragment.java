package hyerim.my.foodstreet.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import hyerim.my.foodstreet.FoodListActivity;
import hyerim.my.foodstreet.GridViewAdapter;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.adapter.MainPagerAdapter;
import hyerim.my.foodstreet.adapter.ViewPagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.pm10.library.CircleIndicator;

import java.text.Format;
import java.util.Objects;

public class MainFragment extends Fragment {
    private GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_main,null);
        ViewPager viewPager = view.findViewById(R.id.main_viewpager);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getContext());         /*ViewPager 아답터*/
        viewPager.setAdapter(mainPagerAdapter);
        CircleIndicator circleIndicator = view.findViewById(R.id.circle_indicator);     /*ViewPager 인디게이터 캐스팅*/
        circleIndicator.setupWithViewPager(viewPager);

        int[] image = {
                R.drawable.rice_bowl,
                R.drawable.bento,
                R.drawable.friedchicken,
                R.drawable.pizza_100,
                R.drawable.hamburger_96,
                R.drawable.noodles,
                R.drawable.dimsum,
                R.drawable.cafe
        };

        String[] text = getResources().getStringArray(R.array.foodtext);

        gridView = view.findViewById(R.id.main_gridview);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(Objects.requireNonNull(getContext()),image,text);
        gridView.setAdapter(gridViewAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //***************** 메인화면 Main_gridview *****************************
        super.onViewCreated(view, savedInstanceState);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), FoodListActivity.class);
                TextView tv = view.findViewById(R.id.gridview_item_text);
                intent.putExtra("position",i);
                intent.putExtra("text",tv.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}
