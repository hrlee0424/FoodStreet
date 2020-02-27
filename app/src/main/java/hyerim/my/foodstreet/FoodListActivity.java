package hyerim.my.foodstreet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;
import hyerim.my.foodstreet.adapter.ViewPagerAdapter;
import hyerim.my.foodstreet.fragment.MainFragment;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragKorean;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class FoodListActivity extends AppCompatActivity {
    private String TAG = FoodListActivity.class.getSimpleName();
    private RecyclerView kor_recyclerView;
    private RecyclerViewDecoration spaceDecoration;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Context context;
    private Toolbar toolbar;
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

        Log.i(TAG, "onCreate: " + position);

        context = getApplicationContext();
        tabLayout = findViewById(R.id.title_tab);
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("한식")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("일식")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("치킨")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("피자")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("패스트푸드")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("중국집")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView("아시안")));
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


        /*
                recyclerView = findViewById(R.id.main_recyclerview);

        //리사이클러뷰매니저 = LinearLayoutManager로 지정.
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);


        Intent intent = getIntent();    //데이터 수신
        String category = intent.getStringExtra("text");
        TextView title = findViewById(R.id.title_tab);
        title.setText(category);

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult= ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if(permissionResult == PackageManager.PERMISSION_GRANTED){  //권한이 있으면
            new SearchTask(category).execute();
        }else if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){  //권한 요청화면을 띄워줌
            new SearchTask(category).execute();    //권한 허락이 되었을 때 실행
        }
        */
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

//1.    TODO: 그리드뷰의 아이템 각 항목을 클릭 했을 떄 해당되는 아이템 네임으로 검색어가 바뀌어 리스트뷰에 띄우기.
//2.    TODO: 리사이클러뷰의 아이템을 클릭했을 때 액티비티 화면으로 넘겨 상세정보 및 리뷰화면 띄우기.
//3.    TODO: 리사이클러뷰의 스크롤 가능하게.
/*
    public class SearchTask extends AsyncTask{
        private final String category;
        ResponseObject responseObject;
        public SearchTask(String category){
            this.category = category;
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            String text = "";
            try {
                text = URLEncoder.encode(category, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&start=1&display=20";
                //+ "&start=1&display=100"

                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", getString(R.string.client_id));
                con.setRequestProperty("X-Naver-Client-Secret", getString(R.string.client_secret));
                // response 수신
                int responseCode = con.getResponseCode();
                System.out.println("responseCode=" + responseCode);
                if (responseCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    responseObject = new Gson().fromJson(response.toString(), ResponseObject.class);
//                    Log.i(TAG, "doInBackground response : "+responseObject.lastBuildDate);
                    System.out.println(response.toString());
                    publishProgress(null);
                } else {
                    System.out.println("API 호출 에러 발생 : 에러코드=" + responseCode);
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    System.out.println(response.toString());
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
            kor_recyclerView.setAdapter(mainRecyclerAdapter);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

    }
*/

}
