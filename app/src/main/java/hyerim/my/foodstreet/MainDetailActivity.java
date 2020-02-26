package hyerim.my.foodstreet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainDetailActivity extends AppCompatActivity {
    private String TAG = MainDetailActivity.class.getSimpleName();
    private TextView dttext_info,dttext_review, detail_title;
    private FragmentManager fr_mg ;
    private FragmentTransaction fr_tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        dttext_info = findViewById(R.id.dttext_info);
        dttext_review = findViewById(R.id.dttext_review);
        detail_title = findViewById(R.id.detail_title);

        final Intent intent = getIntent();
        final String title = intent.getStringExtra("detail_title");

        detail_title.setText(title);
        Log.i(TAG, "onCreate: " + title);

        //실행 초기 홈 화면 생성.
        fr_mg = getSupportFragmentManager();
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.detail_frame, new DetailInfo());
        fr_tr.commit();

        dttext_info.setOnClickListener(Click);
        dttext_review.setOnClickListener(Click);

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
