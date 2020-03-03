package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.MainDetailActivity;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    private String TAG = MainRecyclerAdapter.class.getSimpleName();
    private ArrayList<ItemObject> arrayList;
    private Context context;
    public TextView detail_title,info_description,info_adress,info_tel,info_homepage;
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
        holder.roadadress.setText(arrayList.get(position).roadAddress);
        holder.category.setText(arrayList.get(position).category);
        holder.telephone.setText(arrayList.get(position).telephone);

//        holder.adress = arrayList.get(position).address;
        holder.homepage = arrayList.get(position).link;
        holder.description = arrayList.get(position).description;
        holder.mapx = arrayList.get(position).mapx;
        holder.mapy = arrayList.get(position).mapy;
//        int description1 = arrayList.get(position).mapx;

//        Log.i(TAG, "onBindViewHolder: " + description1);
//        Log.i(TAG, "onBindViewHolder1: " + arrayList.get(position).title);

//         detail_title = arrayList.get(position).title;

//         info_description = arrayList.get(position).description;
//         info_adress = arrayList.get(position).address;
//         info_tel = arrayList.get(position).telephone;
//         info_homepage = arrayList.get(position).link;



//        Log.i(TAG, "onBindViewHolder: " + info_description);
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
        String description, homepage;
        int mapx, mapy;
        View.OnClickListener holderClick;


        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            title  = itemView.findViewById(R.id.main_recyclerview_title);
            roadadress  = itemView.findViewById(R.id.main_recyclerview_roadadress);
            category  = itemView.findViewById(R.id.main_recyclerview_category);
            telephone = itemView.findViewById(R.id.main_recyclerview_telephone);

//            description.setText(info_description);
//            adress.setText(info_adress);
//            homepage.setText(info_homepage);
//            info_description = itemView.findViewById(R.id.info_description);
//            info_tel = itemView.findViewById(R.id.info_tel);
//            info_adress = itemView.findViewById(R.id.info_adress);
//            info_homepage = itemView.findViewById(R.id.info_homepage);
//                    detail_title = itemView.findViewById(R.id.detail_title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), MainDetailActivity.class);
                    intent.putExtra("detail_title", title.getText().toString());
                    intent.putExtra("info_tel", telephone.getText().toString());
                    intent.putExtra("info_description", description);
                    intent.putExtra("info_adress", roadadress.getText().toString());
                    intent.putExtra("info_homepage", homepage);
                    intent.putExtra("info_mapx", mapx);
                    intent.putExtra("info_mapy", mapy);
//                    Log.i(TAG, "onClick1: " + title.getText().toString());
//                    Log.i(TAG, "onClick: " + description);
//                    Log.i(TAG, "onClick: " + homepage);

                    view.getContext().startActivity(intent);

//                    Intent intent1 = new Intent(view.getContext(), DetailInfo.class);
//                    intent1.putExtra("info_description", info_description);
//                    intent1.putExtra("info_tel", info_tel);
//                    intent1.putExtra("info_adress", info_adress);
//                    intent1.putExtra("info_homepage", info_homepage);
//                    Log.i(TAG, "onClick: " + info_description);
//                    Log.i(TAG, "onClick: " + info_tel);
//                    Log.i(TAG, "onClick: " + info_adress);
//                    Log.i(TAG, "onClick: " + info_homepage);
//
//                    view.getContext().startActivity(intent1);

                }
            });
        }
    }
}
