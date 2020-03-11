package hyerim.my.foodstreet.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.fragment.Main2Fragment;
import hyerim.my.foodstreet.fragment.Main3Fragment;
import hyerim.my.foodstreet.fragment.Main4Fragment;
import hyerim.my.foodstreet.fragment.MainFragment;
import hyerim.my.foodstreet.fragment.MainMypageFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
//    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
//    private static final int PERMISSIONS_REQUEST_CODE = 100;
//    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private FragmentManager fr_mg  = getSupportFragmentManager();
    private FragmentTransaction fr_tr;
    private FirebaseAuth firebaseAuth;
    public Spinner spinner;
    public String local = "";
    public String test = "";
    public String locationValue = "";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");


        spinner = findViewById(R.id.spinner);
        String[] localitem = getResources().getStringArray(R.array.local);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,localitem);
        spinner.setAdapter(adapter);

        TextView menu_home = findViewById(R.id.menu_home);
        TextView menu_star = findViewById(R.id.menu_star);
//        TextView menu_map = findViewById(R.id.menu_map);
        TextView menu_my_page = findViewById(R.id.menu_my_page);

        //실행 초기 홈 화면 생성.
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.fragment_main, new MainFragment());
        fr_tr.commit();

        //하단 메뉴 바 클릭시 화면 전환.
        menu_home.setOnClickListener(Click);
        menu_star.setOnClickListener(Click);
//        menu_map.setOnClickListener(Click);
        menu_my_page.setOnClickListener(Click);

    }


    //메뉴 바 클릭시 실행되는 메소드.
    private View.OnClickListener Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            fr_mg = getSupportFragmentManager();
            fr_tr = fr_mg.beginTransaction();
            switch (v.getId()) {
                case R.id.menu_home:
                    fr_tr.replace(R.id.fragment_main, new MainFragment());
                    fr_tr.commit();
                    break;
                case R.id.menu_star:
                    //                Navigation.findNavController(MainActivity.this,R.id.fragment_main).navigate(R.id.action_mainFragment_to_main2Fragment);
                    fr_tr.replace(R.id.fragment_main, new Main2Fragment());
                    fr_tr.commit();
                    break;
//                case R.id.menu_map:
//                    fr_tr.replace(R.id.fragment_main, new Main3Fragment());
//                    fr_tr.commit();
//                    break;
                case R.id.menu_my_page:
                    if (firebaseAuth.getCurrentUser() != null){ //이전 가입한 아이디가 있으면
                        fr_tr.replace(R.id.fragment_main, new MainMypageFragment());
                    }else {
                        fr_tr.replace(R.id.fragment_main, new Main4Fragment());
                    }
                    fr_tr.commit();
                    break;
            }
        }
    };
}
