package hyerim.my.foodstreet.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hyerim.my.foodstreet.MyApplication;
import hyerim.my.foodstreet.Object.ItemObject;
import hyerim.my.foodstreet.R;
import hyerim.my.foodstreet.adapter.DBAdapter;
import hyerim.my.foodstreet.util.RecyclerViewDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class Main2Fragment extends Fragment {
    private String TAG = Main2Fragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private DBAdapter dbAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_main2,container,false);
        recyclerView = view.findViewById(R.id.bookmarklist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerViewDecoration spaceDecoration = new RecyclerViewDecoration(30);
        recyclerView.addItemDecoration(spaceDecoration);

        //리사이클러뷰 구분선 추가.
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ArrayList<ItemObject> itemObjects = MyApplication.getInstance().dbManager.itemObjects();
        dbAdapter = new DBAdapter(getContext());
        dbAdapter.setList(itemObjects);
        recyclerView.setAdapter(dbAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
