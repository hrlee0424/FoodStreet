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
import hyerim.my.foodstreet.activity.FoodListActivity;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.util.RecyclerViewDecoration;
import hyerim.my.foodstreet.asynctask.SearchTask;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ViewPagerFragJapan extends Fragment {
    private String TAG = ViewPagerFragJapan.class.getSimpleName();
    private RecyclerView japan_recyclerview;
    private RecyclerViewDecoration spaceDecoration;
    private String localRead;
    private int page=1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_japan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FoodListActivity activity = (FoodListActivity)getActivity();
        localRead = activity.local;
        Log.i(TAG, "onViewCreated: " + localRead);
        japan_recyclerview = view.findViewById(R.id.japan_recyclerview);

        // Inflate the layout for this fragment
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        japan_recyclerview.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 높이 여백 지정.
        spaceDecoration = new RecyclerViewDecoration(30);
        japan_recyclerview.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(japan_recyclerview.getContext(),new LinearLayoutManager(getContext()).getOrientation());
        japan_recyclerview.addItemDecoration(dividerItemDecoration);

        //인터넷 권한이 있을 떄만 asyndTask 실행.
        int permissionResult= ContextCompat.checkSelfPermission(getContext(), Manifest.permission.INTERNET); //현재 권한을 갖고 있는지 확인 후
        if(permissionResult == PackageManager.PERMISSION_GRANTED){  //권한이 있으면
            new SearchTask(localRead + "일식집",japan_recyclerview, page).execute();
        }else if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.INTERNET)){  //권한 요청화면을 띄워줌
            new SearchTask(localRead + "일식집", japan_recyclerview, page).execute();    //권한 허락이 되었을 때 실행
        }
        japan_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!japan_recyclerview.canScrollVertically(1)){
                    page += 1;
                    new SearchTask(localRead+"일식집",japan_recyclerview, page).execute();
                }
            }
        });
    }
}
