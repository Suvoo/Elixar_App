package com.example.splash2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    String s1[],s2[];
    int images[] = {R.drawable.gray_leaf_spot,R.drawable.northern_leaf_blight,R.drawable.common_rust,R.drawable.goss_wilt,R.drawable.eyespot,R.drawable.southern_rust};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false) ;

        recyclerView = v.findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Disease_names);
        s2 = getResources().getStringArray(R.array.Description);

        MyAdapter myAdapter = new MyAdapter(getActivity(),s1,s2,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}

