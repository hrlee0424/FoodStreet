package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
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

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    public ArrayList<ItemObject> arrayList;
    private Context context;
    public MainRecyclerAdapter(Context context, ArrayList<ItemObject> itemObjects) {
        this.context = context;
        this.arrayList = itemObjects;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item,null,false);
        View view = layoutInflater.inflate(R.layout.main_recyclerview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerAdapter.ViewHolder holder, int position) {
        ItemObject vo =arrayList.get(position);

        holder.title.setText(Html.fromHtml(vo.title));
        holder.roadadress = vo.roadAddress;
        holder.category.setText(vo.category);
        holder.telephone = vo.telephone;
        holder.homepage = vo.link;
        holder.description = vo.description;
        holder.mapx = vo.mapx;
        holder.mapy = vo.mapy;
    }

    public int getItemCount() {
        if (arrayList != null){
            return arrayList.size();
        }else{
            return 0;
        }
    }
        class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,category;
        String description, homepage, telephone, roadadress;
        int mapx, mapy;


        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            title  = itemView.findViewById(R.id.main_recyclerview_title);
            category  = itemView.findViewById(R.id.main_recyclerview_category);

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
