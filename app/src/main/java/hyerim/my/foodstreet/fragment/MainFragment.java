package hyerim.my.foodstreet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import hyerim.my.foodstreet.activity.FoodListActivity;
import hyerim.my.foodstreet.adapter.GridViewAdapter;
import hyerim.my.foodstreet.activity.MainActivity;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.adapter.MainPagerAdapter;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.pm10.library.CircleIndicator;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainFragment extends Fragment {
    private String TAG = MainFragment.class.getSimpleName();
    private GridView gridView;
    private String local;
    public MainActivity activity;
    private int currentpage;
    private final long DELAY = 500;
    private final long PERIOD = 3000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view  = inflater.inflate(R.layout.fragment_main,null);
        final ViewPager viewPager = view.findViewById(R.id.main_viewpager);
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getContext());         /*ViewPager 아답터*/
        viewPager.setAdapter(mainPagerAdapter);
        CircleIndicator circleIndicator = view.findViewById(R.id.circle_indicator);     /*ViewPager 인디게이터 캐스팅*/
        circleIndicator.setupWithViewPager(viewPager);

        /*뷰페이저 자동 스크롤*/
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentpage == 4){
                    currentpage = 0;
                }
                viewPager.setCurrentItem(currentpage++,true);
            }
        };
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        },DELAY,PERIOD);

        /*그리드 뷰 이미지,텍스트 셋팅*/
        int[] image = {
                R.drawable.rice_bowl,
                R.drawable.bento,
                R.drawable.friedchicken,
                R.drawable.pizza_100,
                R.drawable.hamburger_96,
                R.drawable.noodles,
                R.drawable.cafe
        };

        String[] text = getResources().getStringArray(R.array.foodtext);

        gridView = view.findViewById(R.id.main_gridview);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(Objects.requireNonNull(getContext()),image,text);
        gridView.setAdapter(gridViewAdapter);

        activity = (MainActivity)getActivity();
        local = activity.spinner.getSelectedItem().toString(); //스피너 초기값 받아오기

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //***************** 메인화면 Main_gridview *****************************
        super.onViewCreated(view, savedInstanceState);
        activity.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    local = adapterView.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), FoodListActivity.class);
                TextView tv = view.findViewById(R.id.gridview_item_text);
                intent.putExtra("position",i);
                intent.putExtra("text",tv.getText().toString().trim());
                intent.putExtra("local", local);
                startActivity(intent);
            }
        });
    }
}
