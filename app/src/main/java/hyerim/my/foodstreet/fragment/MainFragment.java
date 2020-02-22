package hyerim.my.foodstreet.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hyerim.my.foodstreet.FoodListActivity;
import hyerim.my.foodstreet.GridViewAdapter;
import hyerim.my.foodstreet.R;

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
    public static TextView gridview_item_text;
    public ImageView gridview_item_image;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_main,null);
        gridview_item_image = view.findViewById(R.id.gridview_item_image);
        gridview_item_text = view.findViewById(R.id.gridview_item_text);

        int image[] = {
                R.drawable.bento,
                R.drawable.rice_bowl,
                R.drawable.friedchicken,
                R.drawable.pizza_100,
                R.drawable.hamburger_96,
                R.drawable.noodles,
                R.drawable.dimsum,
                R.drawable.cafe
        };

        String text[] = {
                "돈가스.일식",
                "한식",
                "치킨",
                "피자",
                "패스트푸드",
                "중국집",
                "아시안",
                "카페"
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
                intent.putExtra("text",gridview_item_text.getText().toString().trim());
                startActivity(intent);
            }
        });


    }


}
