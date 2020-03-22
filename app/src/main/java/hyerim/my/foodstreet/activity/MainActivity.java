package hyerim.my.foodstreet.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.fragment.Main2Fragment;
import hyerim.my.foodstreet.fragment.Main3Fragment;
import hyerim.my.foodstreet.fragment.Main4Fragment;
import hyerim.my.foodstreet.fragment.MainFragment;
import hyerim.my.foodstreet.fragment.MainMypageFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private FragmentManager fr_mg  = getSupportFragmentManager();
    private FragmentTransaction fr_tr;
    private FirebaseAuth firebaseAuth;
    public Spinner spinner;
    private TextView  menu_home,menu_star,menu_my_page;
    private int maincolor, black;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, localitem);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        Log.i(TAG, "onCreate: MainActivity" + spinner.getSelectedItem().toString());

        menu_home = findViewById(R.id.menu_home);
        menu_star = findViewById(R.id.menu_star);
//        TextView menu_map = findViewById(R.id.menu_map);
//        menu_my_page = findViewById(R.id.menu_my_page);

        maincolor = ContextCompat.getColor(getApplicationContext(),R.color.titleBar);
        black = ContextCompat.getColor(getApplicationContext(),R.color.black);

        //실행 초기 홈 화면 생성.
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.fragment_main, new MainFragment());
        fr_tr.commit();
        menu_home.setTextColor(maincolor);
        menu_star.setTextColor(black);
        menu_my_page.setTextColor(black);

        //하단 메뉴 바 클릭시 화면 전환.
        menu_home.setOnClickListener(Click);
        menu_star.setOnClickListener(Click);
//        menu_map.setOnClickListener(Click);
//        menu_my_page.setOnClickListener(Click);

    }


    //메뉴 바 클릭시 실행되는 메소드.
    private View.OnClickListener Click = new View.OnClickListener() {
        @SuppressLint("ResourceAsColor")
        @Override
        public void onClick(View v) {
            fr_mg = getSupportFragmentManager();
            fr_tr = fr_mg.beginTransaction();

            switch (v.getId()) {
                case R.id.menu_home:
                    fr_tr.replace(R.id.fragment_main, new MainFragment());
                    fr_tr.commit();
                    menu_home.setTextColor(maincolor);
                    menu_star.setTextColor(black);
                    menu_my_page.setTextColor(black);
                    break;
                case R.id.menu_star:
                    //                Navigation.findNavController(MainActivity.this,R.id.fragment_main).navigate(R.id.action_mainFragment_to_main2Fragment);
                    fr_tr.replace(R.id.fragment_main, new Main2Fragment());
                    fr_tr.commit();
                    menu_home.setTextColor(black);
                    menu_star.setTextColor(maincolor);
                    menu_my_page.setTextColor(black);
                    break;
                    /*-------------------------로그인 기능 제거----------------------
                case R.id.menu_map:
                    fr_tr.replace(R.id.fragment_main, new Main3Fragment());
                    fr_tr.commit();
                    break;
                case R.id.menu_my_page:
                    if (firebaseAuth.getCurrentUser() != null){ //이전 가입한 아이디가 있으면
                        fr_tr.replace(R.id.fragment_main, new MainMypageFragment());
                    }else {
                        fr_tr.replace(R.id.fragment_main, new Main4Fragment());
                    }
                    fr_tr.commit();
                    menu_home.setTextColor(black);
                    menu_star.setTextColor(black);
                    menu_my_page.setTextColor(maincolor);
                    break;
                   */
            }
        }
    };
}
