package hyerim.my.foodstreet;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
//    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
//    private static final int PERMISSIONS_REQUEST_CODE = 100;
//    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private FragmentManager fr_mg  = getSupportFragmentManager();
    private FragmentTransaction fr_tr;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        Spinner spinner = findViewById(R.id.spinner);
        String[] localitem = getResources().getStringArray(R.array.local);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,localitem);
        spinner.setAdapter(adapter);


        TextView menu_home = findViewById(R.id.menu_home);
        TextView menu_star = findViewById(R.id.menu_star);
        TextView menu_map = findViewById(R.id.menu_map);
        TextView menu_my_page = findViewById(R.id.menu_my_page);

        //실행 초기 홈 화면 생성.
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

//    public class Spinner extends Activity implements AdapterView.OnItemSelectedListener{
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSIONS_REQUEST_CODE && grantResults.length == REQUIRED_PERMISSIONS.length){
//            boolean chresult = true;
//
//            for (int result : grantResults){
//                if (result != PackageManager.PERMISSION_GRANTED){
//                    chresult = false;
//                    break;
//                }
//            }
//
//            if (chresult){
//
//            }else {
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,REQUIRED_PERMISSIONS[0])
//                        || ActivityCompat.shouldShowRequestPermissionRationale(this,REQUIRED_PERMISSIONS[1])){
//                    Toast.makeText(MainActivity.this,"퍼미션 거부",Toast.LENGTH_SHORT).show();
//                    finish();
//                }else {
//                    Toast.makeText(MainActivity.this,"퍼시면 거부 설정확인", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

//    void checkRunTimePermission(){
//
//        //런타임 퍼미션 처리
//        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
//        int hasFineLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.ACCESS_FINE_LOCATION);
//        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(MainActivity.this,
//                Manifest.permission.ACCESS_COARSE_LOCATION);
//
//
//        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
//                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
//
//            // 2. 이미 퍼미션을 가지고 있다면
//            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)
//
//
//            // 3.  위치 값을 가져올 수 있음
//
//
//
//        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.
//
//            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
//            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, REQUIRED_PERMISSIONS[0])) {
//
//                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
//                Toast.makeText(MainActivity.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
//                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
//                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
//                        PERMISSIONS_REQUEST_CODE);
//
//
//            } else {
//                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
//                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
//                ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
//                        PERMISSIONS_REQUEST_CODE);
//            }
//
//        }
//
//    }
}
