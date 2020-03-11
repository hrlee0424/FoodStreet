package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.R;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ViewHolder>{
    private ArrayList<String> reviewtext = new ArrayList<>();
    private ArrayList<String> reviewid = new ArrayList<>();
    private ArrayList<Float> reviewrating = new ArrayList<>();
//    private ArrayList<String> reviewlist = new ArrayList<>();
    public Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_detail_review,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textId.setText(reviewid.get(position));
        holder.eidttext.setText(reviewtext.get(position));
        holder.ratingBar.setRating(reviewrating.get(position));
    }

    @Override
    public int getItemCount() {
        return reviewid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textId;
        EditText eidttext;
        RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textId = itemView.findViewById(R.id.reviewlist_userid);
            eidttext = itemView.findViewById(R.id.reviewlist_text);
            ratingBar = itemView.findViewById(R.id.reviewlist_star);
        }
    }
}
