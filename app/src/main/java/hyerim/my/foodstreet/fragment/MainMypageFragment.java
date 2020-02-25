package hyerim.my.foodstreet.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import hyerim.my.foodstreet.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainMypageFragment extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView mainmypage_textid;
    private Button mainmypage_btn_logout;
    private FirebaseAuth firebaseAuth;
    private FragmentManager fr_mg ;
    private FragmentTransaction fr_tr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        mainmypage_textid = getView().findViewById(R.id.mainmypage_textid);
        mainmypage_btn_logout = getView().findViewById(R.id.mainmypage_btn_logout);

        fr_mg = getActivity().getSupportFragmentManager();
        fr_tr = fr_mg.beginTransaction();


        //마이페이지 회원 아이디 입력.
        DocumentReference docRef = db.collection("users").document(firebaseAuth.getCurrentUser().getUid());

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                mainmypage_textid.setText(documentSnapshot.get("name").toString());
            }
        });
        docRef.get().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MainMypageFragment.this, "정보를 불러오지 못했습니다.", Toast.LENGTH_LONG).show();
            }
        });

        mainmypage_btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                fr_tr.replace(R.id.fragment_main, new MainFragment());
                fr_tr.commit();
//                Toast.makeText(MainMypageFragment.this,"로그아웃 되었습니다.",Toast.LENGTH_LONG).show();
            }
        });

    }


}
