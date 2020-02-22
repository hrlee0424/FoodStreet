package hyerim.my.foodstreet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import hyerim.my.foodstreet.fragment.Main2Fragment;
import hyerim.my.foodstreet.fragment.Main3Fragment;
import hyerim.my.foodstreet.fragment.Main4Fragment;
import hyerim.my.foodstreet.fragment.MainFragment;
import hyerim.my.foodstreet.fragment.MainMypageFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.nhn.android.naverlogin.OAuthLogin;

public class MainActivity extends AppCompatActivity {
    private TextView menu_home, menu_star, menu_map, menu_my_page;
    private FragmentManager fr_mg ;
    private FragmentTransaction fr_tr;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        menu_home = findViewById(R.id.menu_home);
        menu_star = findViewById(R.id.menu_star);
        menu_map = findViewById(R.id.menu_map);
        menu_my_page = findViewById(R.id.menu_my_page);

        //실행 초기 홈 화면 생성.
        fr_mg = getSupportFragmentManager();
        fr_tr = fr_mg.beginTransaction();
        fr_tr.add(R.id.fragment_main, new MainFragment());
        fr_tr.commit();

        //하단 메뉴 바 클릭시 화면 전환.
        menu_home.setOnClickListener(Click);
        menu_star.setOnClickListener(Click);
        menu_map.setOnClickListener(Click);
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
                case R.id.menu_map:
                    fr_tr.replace(R.id.fragment_main, new Main3Fragment());
                    fr_tr.commit();
                    break;
                case R.id.menu_my_page:
                    if (firebaseAuth.getCurrentUser() != null){
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
