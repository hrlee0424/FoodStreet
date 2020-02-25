package hyerim.my.foodstreet.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import hyerim.my.foodstreet.auth.JoinActivity;
import hyerim.my.foodstreet.auth.LoginActivity;
import hyerim.my.foodstreet.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Main4Fragment extends Fragment {
    private Button main4_btn_join,main4_btn_login;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main4_btn_join = view.findViewById(R.id.main4_btn_join);
        main4_btn_login = view.findViewById(R.id.main4_btn_login);

        main4_btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JoinActivity.class);
                startActivity(intent);
            }
        });

        main4_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main4, container, false);

            }




}
