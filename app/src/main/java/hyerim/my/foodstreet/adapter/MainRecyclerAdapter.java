package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private ArrayList<ItemObject> arrayList;
    private Context context;

    public MainRecyclerAdapter(ArrayList<ItemObject> itemObjects) {
        arrayList = itemObjects;
//        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item,null);
        ViewHolder viewHodler = new ViewHolder(view);

        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(Html.fromHtml(arrayList.get(position).title));
        holder.roadadress.setText(arrayList.get(position).address);
        holder.category.setText(arrayList.get(position).category);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,roadadress,category;
        View.OnClickListener holderClick;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title  = itemView.findViewById(R.id.main_recyclerview_title);
            roadadress  = itemView.findViewById(R.id.main_recyclerview_roadadress);
            category  = itemView.findViewById(R.id.main_recyclerview_category);
        }

    }
}
