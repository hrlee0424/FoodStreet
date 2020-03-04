package hyerim.my.foodstreet.vpfragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.MainDetailActivity;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.RecyclerViewDecoration;
import hyerim.my.foodstreet.SearchTask;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.UserInfo;
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


public class ViewPagerFragAsian extends Fragment {
    private String TAG = ViewPagerFragAsian.class.getSimpleName();
    private RecyclerViewDecoration spaceDecoration;
    private RecyclerView asian_recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_asian, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        asian_recyclerview = view.findViewById(R.id.asian_recyclerview);

        // Inflate the layout for this fragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        asian_recyclerview.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        asian_recyclerview.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(asian_recyclerview.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        asian_recyclerview.addItemDecoration(dividerItemDecoration);

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if (permissionResult == PackageManager.PERMISSION_GRANTED) {  //권한이 있으면
            new SearchTask("아시안음식", asian_recyclerview).execute();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)) {  //권한 요청화면을 띄워줌
            new SearchTask("아시안음식", asian_recyclerview).execute();    //권한 허락이 되었을 때 실행
        }

    }

//    public class SearchTask extends AsyncTask {
//        private final String category;
//        private RecyclerView recyclerview;
//        OkHttpClient client = new OkHttpClient();
//        ResponseObject responseObject;
//
//        public SearchTask(String category, RecyclerView recyclerView) {
//            this.category = category;
//            this.recyclerview = recyclerView;
//        }
//
//        @Override
//        protected Object doInBackground(Object[] objects) {
//           try {
//
//            String text = category;
//
//            String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&start=1&display=20";
//
//            Request request = new Request.Builder()
//                    .addHeader("X-Naver-Client-Id", getString(R.string.client_id))
//                    .addHeader("X-Naver-Client-Secret", getString(R.string.client_secret))
//                    .url(apiURL)
//                    .build();
//
//            Response response = null;
//            try {
//                response = client.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String result = null;
//            try {
//                result = response.body().string();
//                Log.i(TAG, "doInBackground: " + result);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//               responseObject = new Gson().fromJson(result, ResponseObject.class);
//
//            return true;
//           }catch (Exception e){
//               e.printStackTrace();
//           }
//           return false;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
////            Log.i(TAG, "onPostExecute: " + );
//            MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
//            recyclerview.setAdapter(mainRecyclerAdapter);
//        }
//
//        @Override
//        protected void onProgressUpdate(Object[] values) {
//            super.onProgressUpdate(values);
//        }
//    }
}
