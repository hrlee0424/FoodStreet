package hyerim.my.foodstreet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ResponseObject;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    public TextView gridview_item_text;
    public ImageView gridview_item_image;
    public String searchmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //***************** 메인화면 Main_gridview *****************************
        gridview_item_image = findViewById(R.id.gridview_item_image);
        gridview_item_text = findViewById(R.id.gridview_item_text);


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

        gridView = findViewById(R.id.main_gridview);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,image,text);
        gridView.setAdapter(gridViewAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),FoodListActivity.class);
                startActivity(intent);
            }
        });



        //***************** 메인화면 Main_gridview 끝*****************************

    }



}
