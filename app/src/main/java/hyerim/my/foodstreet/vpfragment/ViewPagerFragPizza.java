package hyerim.my.foodstreet.vpfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import hyerim.my.foodstreet.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ViewPagerFragPizza extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager_pizza, container, false);
    }


}
