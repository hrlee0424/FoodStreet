package hyerim.my.foodstreet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainDetailActivity extends AppCompatActivity {
    private String TAG = MainDetailActivity.class.getSimpleName();
    private TextView dttext_info,dttext_review, detail_title;
    private FragmentManager fr_mg ;
    private FragmentTransaction fr_tr;
    private Toolbar toolbar;
//    private String description, tel, adress, homepage;
    private TextView info_description, info_tel, info_adress, info_homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        dttext_info = findViewById(R.id.dttext_info);
        dttext_review = findViewById(R.id.dttext_review);
        detail_title = findViewById(R.id.detail_title);

        final Intent intent = getIntent();
        final String title = intent.getStringExtra("detail_title");
        String description = intent.getStringExtra("info_description");
        String tel = intent.getStringExtra("info_tel");
        String adress = intent.getStringExtra("info_adress");
        String homepage = intent.getStringExtra("info_homepage");
        int mapx = intent.getIntExtra("info_mapx",0);
        int mapy = intent.getIntExtra("info_mapy",0);

        Intent intent1 = new Intent(getApplicationContext(),DetailInfo.class);
        intent1.putExtra("info_description", description);
        intent1.putExtra("info_tel", tel);
        intent1.putExtra("info_adress", adress);
        intent1.putExtra("info_homepage", homepage);
        intent1.putExtra("info_mapx", mapx);
        intent1.putExtra("info_mapy", mapy);

//        Log.i(TAG, "onCreate2: " + description);
//        Log.i(TAG, "onCreate2: " + tel);
//        Log.i(TAG, "onCreate2: " + adress);
//        Log.i(TAG, "onCreate2: " + homepage);


        //실행 초기 홈 화면 생성.
        fr_mg = getSupportFragmentManager();
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.detail_frame, new DetailInfo());
        fr_tr.commit();

        detail_title.setText(title);

        dttext_info.setOnClickListener(Click);
        dttext_review.setOnClickListener(Click);

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
                case R.id.dttext_review:
                    fr_tr.replace(R.id.detail_frame, new DetailReview());
                    fr_tr.commit();
                    break;
            }
        }
    };
}
