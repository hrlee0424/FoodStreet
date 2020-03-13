package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.activity.MainDetailActivity;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.vpfragment.ViewPagerFragKorean;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private String TAG = MainRecyclerAdapter.class.getSimpleName();
    public ArrayList<ItemObject> arrayList;
    private Context context;

    public MainRecyclerAdapter(ArrayList<ItemObject> itemObjects) {
        arrayList = itemObjects;
//        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item,null);
        ViewHolder viewHodler = new ViewHolder(view);
//        LayoutInflater inflater =context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {

        holder.title.setText(Html.fromHtml(arrayList.get(position).title));
        holder.roadadress.setText(arrayList.get(position).roadAddress);
        holder.category.setText(arrayList.get(position).category);
        holder.telephone.setText(arrayList.get(position).telephone);
        holder.homepage = arrayList.get(position).link;
        holder.description = arrayList.get(position).description;
        holder.mapx = arrayList.get(position).mapx;
        holder.mapy = arrayList.get(position).mapy;
    }

    public int getItemCount() {
        if (arrayList != null){
            return arrayList.size();
        }else{
            return 0;
        }
    }
        class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,roadadress,category, telephone;
        String description, homepage;
        int mapx, mapy;
        View.OnClickListener holderClick;


        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            title  = itemView.findViewById(R.id.main_recyclerview_title);
            roadadress  = itemView.findViewById(R.id.main_recyclerview_roadadress);
            category  = itemView.findViewById(R.id.main_recyclerview_category);
            telephone = itemView.findViewById(R.id.main_recyclerview_telephone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainDetailActivity.class);
                    intent.putExtra("info", new Gson().toJson(arrayList.get(getPosition())));
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
