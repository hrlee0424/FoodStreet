package hyerim.my.foodstreet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.activity.EditReviewActivity;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.util.RecyclerViewDecoration;
import hyerim.my.foodstreet.adapter.ReviewRecyclerAdapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class DetailReview extends Fragment {
    private String TAG = DetailReview.class.getSimpleName();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        review_list.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        review_list.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(review_list.getContext(),new LinearLayoutManager(getContext()).getOrientation());
        review_list.addItemDecoration(dividerItemDecoration);

        final ReviewRecyclerAdapter reviewRecyclerAdapter = new ReviewRecyclerAdapter();
        review_list.setAdapter(reviewRecyclerAdapter);

        db.collection("reviews").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Log.i(TAG, "onComplete1: " + document.getData());
//                                reviewrating.add(Float.valueOf(document.get("rating").toString()));
                                reviewid.add(document.get("name").toString());
                                reviewtext.add(document.get("review").toString());
                                Log.i(TAG, "onComplete: " + document.getData());
                            }
                            reviewRecyclerAdapter.notifyDataSetChanged();
                        }else{
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
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
