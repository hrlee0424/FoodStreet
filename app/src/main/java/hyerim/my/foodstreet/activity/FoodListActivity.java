package hyerim.my.foodstreet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.adapter.ViewPagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class FoodListActivity extends AppCompatActivity {
    private String TAG = FoodListActivity.class.getSimpleName();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Context context;
    private Toolbar toolbar;
    public String local = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recyclerview);
        toolbar = findViewById(R.id.mainlist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        final Intent intent = getIntent();    //데이터 수신
        final String category = intent.getStringExtra("text");
        final int position = intent.getIntExtra("position",0);
        local = intent.getStringExtra("local");

        Log.i(TAG, "onCreate: " + local);

        context = getApplicationContext();
        tabLayout = findViewById(R.id.title_tab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("한식")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("일식")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("치킨")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("피자")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("패스트푸드")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("중국집")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("카페")));

        viewPager = findViewById(R.id.frag_viewpager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(position); //그리드뷰 클릭시 넘겨준 position에 맞는 뷰페이저 설정.


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(context).inflate(R.layout.tab_menu,null);
        TextView tab_item = tabView.findViewById(R.id.tab_item);
        tab_item.setText(tabName);
        return tabView;
    }
}
