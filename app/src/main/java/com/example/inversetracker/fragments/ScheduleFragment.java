package com.example.inversetracker.fragments;

import android.app.DownloadManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inversetracker.R;
import com.google.android.material.button.MaterialButton;

public class ScheduleFragment extends Fragment implements View.OnClickListener{

    private MaterialButton buttonDownloadDocument1, buttonDownloadDocument2, buttonDownloadDocument3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        buttonDownloadDocument1 = getActivity().findViewById(R.id.buttonDownloadDocument1);
        buttonDownloadDocument2 = getActivity().findViewById(R.id.buttonDownloadDocument2);
        buttonDownloadDocument3 = getActivity().findViewById(R.id.buttonDownloadDocument3);
        buttonDownloadDocument1.setOnClickListener(this);
        buttonDownloadDocument2.setOnClickListener(this);
        buttonDownloadDocument3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonDownloadDocument1:
                break;
            case R.id.buttonDownloadDocument2:
                break;
            case R.id.buttonDownloadDocument3:
                break;
        }
    }
}