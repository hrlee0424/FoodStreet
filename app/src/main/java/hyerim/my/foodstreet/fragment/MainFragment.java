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

import java.text.Format;

public class MainFragment extends Fragment {
    private GridView gridView;
    private static TextView gridview_item_text;
    private ImageView gridview_item_image;
    private ViewPager viewPager;
    private MainPagerAdapter mainPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view  = inflater.inflate(R.layout.fragment_main,null);
        gridview_item_image = view.findViewById(R.id.gridview_item_image);
        gridview_item_text = view.findViewById(R.id.gridview_item_text);
        viewPager = view.findViewById(R.id.main_viewpager);
        mainPagerAdapter = new MainPagerAdapter(getContext());
        viewPager.setAdapter(mainPagerAdapter);

        int image[] = {
                R.drawable.rice_bowl,
                R.drawable.bento,
                R.drawable.friedchicken,
                R.drawable.pizza_100,
                R.drawable.hamburger_96,
                R.drawable.noodles,
                R.drawable.dimsum,
                R.drawable.cafe
        };

        String text[] = getResources().getStringArray(R.array.foodtext);{

        };

        gridView = view.findViewById(R.id.main_gridview);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(),image,text);
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
