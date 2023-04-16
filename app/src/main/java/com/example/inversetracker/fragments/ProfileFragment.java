package com.example.inversetracker.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inversetracker.R;
import com.example.inversetracker.activities.WelcomeActivity;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private MaterialButton buttonGoOutProfile, buttonDialogExitYes, buttonDialogExitNo;
    private LinearLayout mainLayoutProfile, layoutEmptyProfile;
    private TextView textNameProfile, textClassProfile, textAgeProfile, textRoleProfile;
    private ProgressBar progressProfile;
    private ArrayList<String> listClasses;
    private Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }
    private void init(){
        buttonGoOutProfile = getActivity().findViewById(R.id.buttonGoOutProfile);
        buttonGoOutProfile.setOnClickListener(this);
        textNameProfile = getActivity().findViewById(R.id.textNameProfile);
        textClassProfile = getActivity().findViewById(R.id.textClassProfile);
        textAgeProfile = getActivity().findViewById(R.id.textAgeProfile);
        textRoleProfile = getActivity().findViewById(R.id.textRoleProfile);
        mainLayoutProfile = getActivity().findViewById(R.id.mainLayoutProfile);
        progressProfile = getActivity().findViewById(R.id.progressProfile);
        layoutEmptyProfile = getActivity().findViewById(R.id.layoutEmptyProfile);
        getAccount();
    }

    private void getAccount(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getMeAccount("Token " + token);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    ResponseModelClass responseModelClass = response.body();
                    textNameProfile.setText(responseModelClass.getLastname() + " " + responseModelClass.getFirstname() + " " + responseModelClass.getPatronymic());
                    textAgeProfile.setText(responseModelClass.getAge());
                    textClassProfile.setText(responseModelClass.getSchoolClass());
                    textRoleProfile.setText(responseModelClass.getRole().getName());
                    progressProfile.setVisibility(View.GONE);
                    mainLayoutProfile.setVisibility(View.VISIBLE);
                }else {
                    layoutEmptyProfile.setVisibility(View.VISIBLE);
                    progressProfile.setVisibility(View.GONE);
                    Log.d("MyLog", response.toString());
                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету" , Toast.LENGTH_SHORT).show();
                layoutEmptyProfile.setVisibility(View.VISIBLE);
                progressProfile.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonGoOutProfile:
                dialog();
                break;
        }
    }

    private void dialog(){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_out_account);
        dialog.show();
        buttonDialogExitYes = dialog.findViewById(R.id.buttonDialogExitYes);
        buttonDialogExitYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.putString("tokenSave", "");
                editor.apply();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
        buttonDialogExitNo = dialog.findViewById(R.id.buttonDialogExitNo);
        buttonDialogExitNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}