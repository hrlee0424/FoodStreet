package hyerim.my.foodstreet.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.MyApplication;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.activity.MainDetailActivity;

public class DBAdapter extends RecyclerView.Adapter<DBAdapter.ViewHolder> {
    private String TAG = DBAdapter.class.getSimpleName();
    public ArrayList<ItemObject> itemObjects;
    private Context context;
    public DBAdapter(Context context){
        this.context = context;
    }

    public void setList(ArrayList<ItemObject> item){
        itemObjects = item;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DBAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main2_recyclerview_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DBAdapter.ViewHolder holder, int position) {
        holder.title.setText(Html.fromHtml(itemObjects.get(position).title));
        holder.roadadress.setText(itemObjects.get(position).roadAddress);
        holder.category.setText(itemObjects.get(position).category);
        holder.telephone.setText(itemObjects.get(position).telephone);
//        ItemObject itemObject = itemObjects.get(position);
//        holder.adress = arrayList.get(position).address;
        holder.homepage = itemObjects.get(position).link;
        holder.description = itemObjects.get(position).description;
        holder.mapx = itemObjects.get(position).mapx;
        holder.mapy = itemObjects.get(position).mapy;

    }

    @Override
    public int getItemCount() {
        if (itemObjects != null){
            return itemObjects.size();
        }else{
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,roadadress,category, telephone;
        String description, homepage;
        int mapx, mapy;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            title  = itemView.findViewById(R.id.main2_recyclerview_title);
            roadadress  = itemView.findViewById(R.id.main2_recyclerview_roadadress);
            category  = itemView.findViewById(R.id.main2_recyclerview_category);
            telephone = itemView.findViewById(R.id.main2_recyclerview_telephone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(view.getContext(), MainDetailActivity.class);
                    intent.putExtra("info", new Gson().toJson(itemObjects.get(getPosition())));
                    view.getContext().startActivity(intent);
                }
            });

            Button btn_cancel = itemView.findViewById(R.id.btn_cancel);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyApplication.getInstance().dbManager.deleteItem(title.getText().toString());
                    Toast.makeText(context,"즐겨찾기가 해제되었습니다.", Toast.LENGTH_SHORT).show();
                    itemObjects = MyApplication.getInstance().dbManager.itemObjects();
                    setList(itemObjects);
                }
            });
        }
    }
}
