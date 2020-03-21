package com.example.splash2;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info,container,false) ;

        TextView feedback = view.findViewById(R.id.emailTxt1);
        feedback.setText(Html.fromHtml("<a href=\"mailto:sunny.gajjar2728@gmail.com\">sunny.gajjar2728@gmail.com</a>"));
        feedback.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }
}

