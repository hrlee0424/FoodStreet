package hyerim.my.foodstreet;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.nhn.android.idp.common.util.HttpConnectionUtil;

import java.io.IOException;
import java.net.HttpURLConnection;

import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchTask extends AsyncTask {
    private final String category;
    private RecyclerView recyclerview;
    OkHttpClient client = new OkHttpClient();
    ResponseObject responseObject;

    public SearchTask(String category, RecyclerView recyclerView) {
        this.category = category;
        this.recyclerview = recyclerView;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {

            String text = category;

            String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&start=1&display=20";

            Request request = new Request.Builder()
                    .addHeader("X-Naver-Client-Id", "PsvNKTozM9di30ga2reO")
                    .addHeader("X-Naver-Client-Secret", "1vgaBvWjyA")
                    .url(apiURL)
                    .build();

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String result = null;
            try {
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseObject = new Gson().fromJson(result, ResponseObject.class);

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
//            Log.i(TAG, "onPostExecute: " + );
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
        recyclerview.setAdapter(mainRecyclerAdapter);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}