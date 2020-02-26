package hyerim.my.foodstreet.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.DetailInfo;
import hyerim.my.foodstreet.MainDetailActivity;
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
        holder.telephone.setText(arrayList.get(position).telephone);

//        holder.detail_title.setText(Html.fromHtml(arrayList.get(position).title));
//        holder.info_description.setText(arrayList.get(position).description);
//        holder.info_adress.setText(arrayList.get(position).address);
//        holder.info_tel.setText(arrayList.get(position).telephone);
//        holder.info_homepage.setText(arrayList.get(position).link);

    }

    public int getItemCount() {
        return arrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,roadadress,category, telephone;
        TextView detail_title,info_description, info_tel, info_adress, info_homepage;
        View.OnClickListener holderClick;

        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            title  = itemView.findViewById(R.id.main_recyclerview_title);
            roadadress  = itemView.findViewById(R.id.main_recyclerview_roadadress);
            category  = itemView.findViewById(R.id.main_recyclerview_category);
            telephone = itemView.findViewById(R.id.main_recyclerview_telephone);

//            info_description = itemView.findViewById(R.id.info_description);
//            info_tel = itemView.findViewById(R.id.info_tel);
//            info_adress = itemView.findViewById(R.id.info_adress);
//            info_homepage = itemView.findViewById(R.id.info_homepage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//
//                    detail_title = itemView.findViewById(R.id.detail_title);
//                    info_description = itemView.findViewById(R.id.info_description);
//                    info_tel = itemView.findViewById(R.id.info_tel);
//                    info_adress = itemView.findViewById(R.id.info_adress);
//                    info_homepage = itemView.findViewById(R.id.info_homepage);

                    Intent intent = new Intent(view.getContext(), MainDetailActivity.class);
                    intent.putExtra("detail_title", title.getText().toString());
                    view.getContext().startActivity(intent);

                    Intent intent1 = new Intent(view.getContext(), DetailInfo.class);
//                    intent1.putExtra("info_description", info_description.getText().toString());
//                    intent1.putExtra("info_tel", info_tel.getText().toString());
//                    intent1.putExtra("info_adress", info_adress.getText().toString());
//                    intent1.putExtra("info_homepage", info_homepage.getText().toString());

                }
            });
        }
    }
}
