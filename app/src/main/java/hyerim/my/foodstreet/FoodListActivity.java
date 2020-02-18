package hyerim.my.foodstreet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;

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
    private RecyclerView recyclerView;
    private RecyclerViewDecoration spaceDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_recyclerview);

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

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult= ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if(permissionResult == PackageManager.PERMISSION_GRANTED){  //권한이 있으면
            asyncTask.execute();
        }else if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)){  //권한 요청화면을 띄워줌
            asyncTask.execute();    //권한 허락이 되었을 때 실행
        }
    }

//1.    TODO: 그리드뷰의 아이템 각 항목을 클릭 했을 떄 해당되는 아이템 네임으로 검색어가 바뀌어 리스트뷰에 띄우기.
//2.    TODO: 리사이클러뷰의 아이템을 클릭했을 때 액티비티 화면으로 넘겨 상세정보 및 리뷰화면 띄우기.
//3.    TODO: 리사이클러뷰의 스크롤 가능하게.

    //naver 지역 검색 실행.
    public AsyncTask asyncTask = new AsyncTask() {
        ResponseObject responseObject;
        @Override
        protected Object doInBackground(Object[] objects) {
            String text = "";
            try {
                text = URLEncoder.encode("광명", "UTF-8");
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
            recyclerView.setAdapter(mainRecyclerAdapter);
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }
    };

}
