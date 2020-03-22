package hyerim.my.foodstreet.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.R;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class EditReviewActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String name;
    private RatingBar ratingBar;
    private String rating;
    private EditText review;
    private HashMap<String,Object> datamap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editreview);

        Toolbar toolbar = findViewById(R.id.review_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.review_edit);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating = ratingBar.toString();
            }
        });
        //사용자 닉네임 받아오기.
        DocumentReference nic = db.collection("users").document(firebaseAuth.getCurrentUser().getUid().toString());
        nic.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                name = documentSnapshot.get("name").toString();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.review_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.reviewbtn_save:
                datamap.put("name",name);
                datamap.put("rating",ratingBar.getRating());
                datamap.put("review", review.getText().toString());
                db.collection("reviews").add(datamap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(), "성공" , Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), MainDetailActivity.class);
//                                startActivity(intent);
//                                ReviewRecyclerAdapter reviewRecyclerAdapter = new ReviewRecyclerAdapter();
//                                review_list.setAdapter(reviewRecyclerAdapter);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "실패" , Toast.LENGTH_SHORT).show();
                            }
                        });
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
