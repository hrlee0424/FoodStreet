package hyerim.my.foodstreet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hyerim.my.foodstreet.MyApplication;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.fragment.DetailInfo;
import hyerim.my.foodstreet.fragment.DetailReview;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainDetailActivity extends AppCompatActivity {
    private String TAG = MainDetailActivity.class.getSimpleName();
    private FragmentManager fr_mg ;
    private FragmentTransaction fr_tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        TextView dttext_info = findViewById(R.id.dttext_info);
        TextView detail_title = findViewById(R.id.detail_title);

        Button addfavorite = findViewById(R.id.btn_bookmark);

        final Intent intent = getIntent();
        final ItemObject item = new Gson().fromJson(intent.getStringExtra("info"), ItemObject.class);

        Intent intent1 = new Intent(getApplicationContext(), DetailInfo.class);
        intent1.putExtra("info", new Gson().toJson(item));

        //실행 초기 홈 화면 생성.
        fr_mg = getSupportFragmentManager();
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.detail_frame, new DetailInfo());
        fr_tr.commit();

        String title = String.valueOf(Html.fromHtml(item.title));
        detail_title.setText(title);
        Log.i(TAG, "onCreate: " + title);
        Log.i(TAG, "onCreate1: " + detail_title);


        dttext_info.setOnClickListener(Click);
//        dttext_review.setOnClickListener(Click);
        findViewById(R.id.btn_bookmark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                    MyApplication.getInstance().dbManager.insertItem(item);
                Toast.makeText(MainDetailActivity.this, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show();
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

    private View.OnClickListener Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fr_mg = getSupportFragmentManager();
            fr_tr = fr_mg.beginTransaction();
            switch (v.getId()){
                case R.id.dttext_info:
                    fr_tr.replace(R.id.detail_frame, new DetailInfo());
                    fr_tr.commit();
                    break;
//                case R.id.dttext_review:
//                    fr_tr.replace(R.id.detail_frame, new DetailReview());
//                    fr_tr.commit();
//                    break;
            }
        }
    };
}
