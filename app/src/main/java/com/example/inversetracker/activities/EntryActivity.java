package com.example.inversetracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inversetracker.R;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private TextView buttonTextForgetPassword, buttonDialogForgetPassword, textEntryError, entryTextRegistration;
    private Dialog dialog;
    private EditText editTextEntryName, editTextEntryPassword;
    private MaterialButton buttonEntry;
    private String name, password;
    private ProgressBar progressEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonTextForgetPassword = findViewById(R.id.buttonTextForgetPassword);
        buttonTextForgetPassword.setOnClickListener(this);
        editTextEntryName = findViewById(R.id.editTextEntryName);
        editTextEntryName.setOnFocusChangeListener(this);
        editTextEntryPassword = findViewById(R.id.editTextEntryPassword);
        editTextEntryPassword.setOnFocusChangeListener(this);
        textEntryError = findViewById(R.id.textEntryError);
        progressEntry = findViewById(R.id.progressEntry);
        entryTextRegistration = findViewById(R.id.entryTextRegistration);
        entryTextRegistration.setOnClickListener(this);
        editInputText();
        buttonEntry = findViewById(R.id.buttonEntry);
        buttonEntry.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EntryActivity.this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void editInputText(){
        editTextEntryName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextEntryName.getText().length() >= 0) {
                    editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                    editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
                    textEntryError.setVisibility(View.GONE);

                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextEntryPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editTextEntryPassword.getText().length() >= 0) {
                    editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                    editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
                    textEntryError.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonTextForgetPassword:
                dialog();
                break;
            case R.id.buttonEntry:
                checkInput();
                break;
            case R.id.entryTextRegistration:
                Intent intent = new Intent(EntryActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void checkInput(){
        name = editTextEntryName.getText().toString();
        password = editTextEntryPassword.getText().toString();
        if (name.isEmpty()){
            editTextEntryName.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (password.isEmpty()){
            editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (!(name.isEmpty()) && !(password.isEmpty())){
            progressEntry.setVisibility(View.VISIBLE);
            buttonEntry.setVisibility(View.INVISIBLE);
            postRequest();
        }
    }

    private void postRequest(){
         Call<ResponseModelClass> call = APIController.getInstance()
                 .getAPI()
                 .postEntry(name, password);
         call.enqueue(new Callback<ResponseModelClass>() {
             @Override
             public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                 if (response.isSuccessful()) {
                     ResponseModelClass responseModelClass = response.body();
                     Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                     saveToken(responseModelClass.getAuthToken());
                     progressEntry.setVisibility(View.GONE);
                     buttonEntry.setVisibility(View.VISIBLE);
                     entryIntent();
                 }else {
                     progressEntry.setVisibility(View.GONE);
                     buttonEntry.setVisibility(View.VISIBLE);
                     textEntryError.setVisibility(View.VISIBLE);
                 }
             }

             @Override
             public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                 Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
                 progressEntry.setVisibility(View.GONE);
                 buttonEntry.setVisibility(View.VISIBLE);
             }
         });
    }

    private void entryIntent(){
        Intent intent = new Intent(EntryActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("saveToken", MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("tokenSave", token);
        editor.apply();
    }

    private void dialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_forget_password);
        dialog.show();
        buttonDialogForgetPassword = dialog.findViewById(R.id.buttonDialogForgetPassword);
        buttonDialogForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.editTextEntryName:
            case R.id.editTextEntryPassword:
                textEntryError.setVisibility(View.GONE);
                editTextEntryName.setBackgroundResource(R.drawable.edit_text_background);
                editTextEntryPassword.setBackgroundResource(R.drawable.edit_text_background);
        }
    }
}