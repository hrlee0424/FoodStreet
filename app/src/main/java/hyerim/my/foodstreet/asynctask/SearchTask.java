package hyerim.my.foodstreet.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.nhn.android.idp.common.util.HttpConnectionUtil;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.net.HttpURLConnection;

import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.MyApplication;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.activity.MainActivity;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchTask extends AsyncTask {
    private String TAG = SearchTask.class.getSimpleName();
    private final String category;
    public RecyclerView recyclerview;
    OkHttpClient client = new OkHttpClient();
    public ResponseObject responseObject;
    public boolean listnofi;
    public int startpage;
    public MainRecyclerAdapter mainRecyclerAdapter;
    private ItemObject itemObject;

    public SearchTask(String category, RecyclerView recyclerView, int page) {
        this.category = category;
        this.recyclerview = recyclerView;
        this.startpage = page;
//        this.listnofi = listnofi;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            String text = category;
//            startpage = 2;
            Log.i(TAG, "onScrollStateChanged: " + startpage);
            int display = 20;
            String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&start=" + startpage + "&display=" + display;

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
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
//            Log.i(TAG, "onPostExecute: " + );
        if (mainRecyclerAdapter == null){
            mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
            mainRecyclerAdapter = new MainRecyclerAdapter(responseObject.items);
      recyclerview.setAdapter(mainRecyclerAdapter);
        }
        mainRecyclerAdapter.notifyDataSetChanged();

//                recyclerview.invalidate();
        listnofi = false;
        Log.i(TAG, "onPostExecute: " + listnofi);
//        recyclerview.getAdapter().notifyDataSetChanged();
    }

}

