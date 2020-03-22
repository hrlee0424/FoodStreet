package hyerim.my.foodstreet.vpfragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.activity.FoodListActivity;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.adapter.MainRecyclerAdapter;
import hyerim.my.foodstreet.util.RecyclerViewDecoration;
import hyerim.my.foodstreet.asynctask.SearchTask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ViewPagerFragPizza extends Fragment {
    private RecyclerView pizza_recyclerview;
    private RecyclerViewDecoration spaceDecoration;
    private String localRead;
    private int page=1;
    public ArrayList<ItemObject> itemObjects;
    private MainRecyclerAdapter mainRecyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_pizza, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FoodListActivity activity = (FoodListActivity)getActivity();
        localRead = activity.local;

        pizza_recyclerview = view.findViewById(R.id.pizza_recyclerview);

        // Inflate the layout for this fragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        pizza_recyclerview.setLayoutManager(linearLayoutManager);

        itemObjects = new ArrayList<>();
        mainRecyclerAdapter = new MainRecyclerAdapter(itemObjects);
        pizza_recyclerview.setAdapter(mainRecyclerAdapter);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        pizza_recyclerview.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(pizza_recyclerview.getContext(),new LinearLayoutManager(getContext()).getOrientation());
        pizza_recyclerview.addItemDecoration(dividerItemDecoration);

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult= ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if(permissionResult == PackageManager.PERMISSION_GRANTED){  //권한이 있으면
            new SearchTask(localRead + "피자", page, mainRecyclerAdapter, itemObjects).execute();
        }else if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)){  //권한 요청화면을 띄워줌
            new SearchTask(localRead + "피자", page, mainRecyclerAdapter, itemObjects).execute();    //권한 허락이 되었을 때 실행
        }
        pizza_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!pizza_recyclerview.canScrollVertically(1)){
                    page += 1;
                    new SearchTask(localRead+"피자", page, mainRecyclerAdapter, itemObjects).execute();
                }
//                else if (!pizza_recyclerview.canScrollVertically(-1)){
//                    page = 1;
//                    new SearchTask(localRead+"피자",pizza_recyclerview, page).execute();
//                }
            }
        });
    }
}
