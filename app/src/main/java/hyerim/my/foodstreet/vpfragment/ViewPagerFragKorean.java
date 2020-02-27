package hyerim.my.foodstreet.vpfragment;

import android.Manifest;
import android.app.ProgressDialog;
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
import androidx.core.view.NestedScrollingChild;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.RecyclerViewDecoration;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

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
import java.util.ArrayList;


public class ViewPagerFragKorean extends Fragment {
    private ArrayList<ItemObject> list;
    private RecyclerView kor_recyclerview;
    private RecyclerViewDecoration spaceDecoration;
    private View footerView;
    private LayoutInflater inflater;
    private int start = 1;
    private int display = 20;
    private ProgressDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_pager_korean, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        kor_recyclerview = view.findViewById(R.id.kor_recyclerview);

//        dialog = new ProgressDialog(getContext());
//        dialog.setMessage("loading..");
        // Inflate the layout for this fragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        kor_recyclerview.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        kor_recyclerview.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(kor_recyclerview.getContext(),new LinearLayoutManager(getContext()).getOrientation());
        kor_recyclerview.addItemDecoration(dividerItemDecoration);

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult= ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if(permissionResult == PackageManager.PERMISSION_GRANTED){  //권한이 있으면
            new SearchTask("한식").execute();
        }else if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)){  //권한 요청화면을 띄워줌
            new SearchTask("한식").execute();    //권한 허락이 되었을 때 실행
        }

//        inflater =(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        footerView = inflater.inflate(R.layout.footer, null);

//        footerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new SearchTask("한식").execute();
//            }
//        });
    }


//    public class SearchAsyncTask extends AsyncTask<>{
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            return null;
//        }
//
//                @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected void onPostExecute(Object o) {
//            super.onPostExecute(o);
//            MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
//            kor_recyclerview.setAdapter(mainRecyclerAdapter);
//        }
//
//        @Override
//        protected void onProgressUpdate(Object[] values) {
//            super.onProgressUpdate(values);
//        }
//    }


    public class SearchTask extends AsyncTask {
        private String category;
        ResponseObject responseObject;
        public SearchTask(String category){
            this.category = category;
        }



        @Override
        protected Object doInBackground(Object[] objects) {
            String text = "";
            try {
                text = URLEncoder.encode(category, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&start="+ start + "&display=" + display;
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
            kor_recyclerview.setAdapter(mainRecyclerAdapter);

//            kor_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                    super.onScrollStateChanged(recyclerView, newState);
//                }
//
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//                }
//            });
//            mainRecyclerAdapter.notifyDataSetChanged();


        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

    }

//
//    public void loaditem(NestedScrollView nestedScrollView, final Context context){
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//             if(scrollX == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()));
//                Toast.makeText(context,"loading", Toast.LENGTH_SHORT).show();
//                new SearchTask("한식").execute();
//            }
//            });
//    }



}
