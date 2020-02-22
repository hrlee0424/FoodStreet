package hyerim.my.foodstreet.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import hyerim.my.foodstreet.MainActivity;
import hyerim.my.foodstreet.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.DatabaseId;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class JoinActivity extends AppCompatActivity {
    private String TAG = JoinActivity.class.getSimpleName();
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$"); //4~16자리까지 가능
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DatabaseId database;
    private FirebaseAuth firebaseAuth;
    private EditText join_id,join_password;
    private Button join_btn;
    private String email = "";
    private String password = "";
    private String user;
    private Map<String, Object> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        firebaseAuth = FirebaseAuth.getInstance();

        join_id = findViewById(R.id.join_email);
        join_password = findViewById(R.id.join_password);
        join_btn = findViewById(R.id.join_btn);

        join_btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                join();
            }
        });

    }

    public void join() {
        email = join_id.getText().toString();
        password = join_password.getText().toString();
        if(isValidEmail() && isValidPasswd()) {
            createUser(email, password);
        }
    }

    // 이메일 유효성 검사
    public boolean isValidEmail() {
        if (email.isEmpty()) {
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    public boolean isValidPasswd() {
        if (password.isEmpty()) {
            // 비밀번호 공백
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            return false;
        } else {
            return true;
        }
    }
    // 회원가입
    private void createUser(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 회원가입 성공

                            String str = join_id.getText().toString();
                            user = str.substring(0,str.indexOf("@")); //이메일 @앞까지 문자 자르기.
                            Log.i(TAG, "onComplete: " + user);
                            hashMap.put("name",user);

                            db.collection("users").document(firebaseAuth.getCurrentUser().getUid())
                                    .set(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.i(TAG, "onSuccess: DocumentSnapshot successfully written!");
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i(TAG, "onfail: DocumentSnapshot successfully written!");
                                            Toast.makeText(JoinActivity.this, "회원가입 실패", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            Toast.makeText(JoinActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);

                        } else {
                            // 회원가입 실패
                            Toast.makeText(JoinActivity.this, "정보를 다시 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
