package hyerim.my.foodstreet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.EditReviewActivity;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.RecyclerViewDecoration;
import hyerim.my.foodstreet.adapter.ReviewRecyclerAdapter;

import android.provider.DocumentsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.Document;

import java.util.ArrayList;


public class DetailReview extends Fragment {
    private TextView btn_review;
    private RecyclerView review_list;
    private RecyclerViewDecoration spaceDecoration;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;
    private ArrayList<String> reviewtext = new ArrayList<>();
    private ArrayList<String> reviewid = new ArrayList<>();
    private ArrayList<Float> reviewrating = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_review = view.findViewById(R.id.btn_review);
        review_list = view.findViewById(R.id.detail_reviewlist);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        review_list.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        review_list.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(review_list.getContext(),new LinearLayoutManager(getContext()).getOrientation());
        review_list.addItemDecoration(dividerItemDecoration);

        ReviewRecyclerAdapter reviewRecyclerAdapter = new ReviewRecyclerAdapter();
        review_list.setAdapter(reviewRecyclerAdapter);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("name");
        databaseReference.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

                btn_review.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (firebaseAuth.getCurrentUser() == null){
                            Toast.makeText(requireContext(), "로그인이 필요합니다.", Toast.LENGTH_SHORT).show();
//                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                            fragmentManager.beginTransaction().remove(DetailReview.this).commit();
//                            Intent intent = new Intent(getActivity(), MainMypageFragment.class);
//                            startActivity(intent);
                        }else {
                            Intent intent = new Intent(getActivity(), EditReviewActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
