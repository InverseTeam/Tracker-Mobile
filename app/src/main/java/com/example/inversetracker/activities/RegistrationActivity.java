package com.example.inversetracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inversetracker.R;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener{

    private TextView buttonTextRegistrationHaveAccount;
    private MaterialButton buttonRegistration;
    private EditText editTextRegistrationSurname, editTextRegistrationName, editTextRegistrationPatronymic, editTextRegistrationEmail,
            editTextRegistrationPassword, editTextRegistrationClass, editTextRegistrationAge;
    private String surname, name, patronymic, email, password, classes, age;
    private ProgressBar progressBar;
    private TextView textRegistrationEmailError, textRegistrationPasswordError, textRegistrationClassError, textRegistrationAgeError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        buttonTextRegistrationHaveAccount = findViewById(R.id.buttonTextRegistrationHaveAccount);
        buttonTextRegistrationHaveAccount.setOnClickListener(this);
        buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(this);
        editTextRegistrationSurname = findViewById(R.id.editTextRegistrationSurname);
        editTextRegistrationSurname.setOnFocusChangeListener(this);
        editTextRegistrationName = findViewById(R.id.editTextRegistrationName);
        editTextRegistrationName.setOnFocusChangeListener(this);
        editTextRegistrationPatronymic = findViewById(R.id.editTextRegistrationPatronymic);
        editTextRegistrationPatronymic.setOnFocusChangeListener(this);
        editTextRegistrationEmail = findViewById(R.id.editTextRegistrationEmail);
        editTextRegistrationEmail.setOnFocusChangeListener(this);
        textRegistrationEmailError = findViewById(R.id.textRegistrationEmailError);
        editTextRegistrationPassword = findViewById(R.id.editTextRegistrationPassword);
        editTextRegistrationPassword.setOnFocusChangeListener(this);
        textRegistrationPasswordError = findViewById(R.id.textRegistrationPasswordError);
        editTextRegistrationClass = findViewById(R.id.editTextRegistrationClass);
        editTextRegistrationClass.setOnFocusChangeListener(this);
        textRegistrationClassError = findViewById(R.id.textRegistrationClassError);
        editTextRegistrationAge = findViewById(R.id.editTextRegistrationAge);
        editTextRegistrationAge.setOnFocusChangeListener(this);
        textRegistrationAgeError = findViewById(R.id.textRegistrationAgeError);
        progressBar = findViewById(R.id.progressRegistration);
        editInputText();
    }

    private void editInputText(){
        editTextRegistrationName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationName.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationSurname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationSurname.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }
        });
        editTextRegistrationPatronymic.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationPatronymic.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationEmail.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationPassword.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationClass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationClass.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
        editTextRegistrationAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s ) {
                if(editTextRegistrationAge.getText().length() >= 0) {
                    notErrorEdit();
                    for (UnderlineSpan span : s.getSpans(0, s.length(), UnderlineSpan.class)) {
                        s.removeSpan(span);
                    }
                }
            }

        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonTextRegistrationHaveAccount:
                onBackPressed();
                break;
            case R.id.buttonRegistration:
                checkRegistration();
                break;
        }
    }

    private void checkRegistration(){
        name = editTextRegistrationName.getText().toString();
        surname = editTextRegistrationSurname.getText().toString();
        patronymic = editTextRegistrationPatronymic.getText().toString();
        email = editTextRegistrationEmail.getText().toString();
        password = editTextRegistrationPassword.getText().toString();
        classes = editTextRegistrationClass.getText().toString();
        age = editTextRegistrationAge.getText().toString();
        if (name.isEmpty()){
            editTextRegistrationName.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (surname.isEmpty()){
            editTextRegistrationSurname.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (patronymic.isEmpty()){
            editTextRegistrationPatronymic.setBackgroundResource(R.drawable.edit_text_background_error);
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextRegistrationEmail.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationEmailError.setVisibility(View.VISIBLE);
        }
        if (password.isEmpty() || password.length() < 8){
            editTextRegistrationPassword.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationPasswordError.setVisibility(View.VISIBLE);
        }
        if (classes.isEmpty() || Integer.parseInt(classes) < 1 || Integer.parseInt(classes) > 11){
            editTextRegistrationClass.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationClassError.setVisibility(View.VISIBLE);
        }
        if (age.isEmpty() || Integer.parseInt(age) < 7 || Integer.parseInt(age) > 18){
            editTextRegistrationAge.setBackgroundResource(R.drawable.edit_text_background_error);
            textRegistrationAgeError.setVisibility(View.VISIBLE);
        }
        if (!(name.isEmpty()) && !(surname.isEmpty()) && !(patronymic.isEmpty()) && !(email.isEmpty()) && !(password.isEmpty()) &&
            !(age.isEmpty()) && !(classes.isEmpty()) && password.length() > 8 && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                (Integer.parseInt(age) > 6 && Integer.parseInt(age) < 19) && Integer.parseInt(classes) > 0 || Integer.parseInt(classes) < 12){
            progressBar.setVisibility(View.VISIBLE);
            buttonRegistration.setVisibility(View.INVISIBLE);
            postRequest();
        }
    }

    private void postRequest(){
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().postRegistration(email, name, surname, patronymic, password, Integer.parseInt(age), Integer.parseInt(classes), 1);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    postToken();
                    progressBar.setVisibility(View.GONE);
                    buttonRegistration.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show();
                    entryIntent();
                }else {
                    textRegistrationEmailError.setVisibility(View.VISIBLE);
                    textRegistrationEmailError.setText("Пользователь с такой почтой уже существует");
                    editTextRegistrationEmail.setBackgroundResource(R.drawable.edit_text_background_error);
                    progressBar.setVisibility(View.GONE);
                    buttonRegistration.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                textRegistrationEmailError.setVisibility(View.VISIBLE);
                textRegistrationEmailError.setText(t.getMessage());
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                buttonRegistration.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegistrationActivity.this, EntryActivity.class);
        startActivity(intent);
        finish();
    }

    private void entryIntent(){
        Intent intent = new Intent(RegistrationActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

    private void postToken(){
        Call<ResponseModelClass> call = APIController.getInstance()
                .getAPI()
                .postEntry(email, password);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()) {
                    ResponseModelClass responseModelClass = response.body();
                    saveToken(responseModelClass.getAuthToken());
                }else {
                    Toast.makeText(getApplicationContext(), "Ошибка: попробуйте ещё раз", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.editTextRegistrationName:
            case R.id.editTextRegistrationSurname:
            case R.id.editTextRegistrationPatronymic:
            case R.id.editTextRegistrationEmail:
            case R.id.editTextEntryPassword:
            case R.id.editTextRegistrationClass:
            case R.id.editTextRegistrationAge:
                notErrorEdit();
        }
    }

    private void saveToken(String token){
        SharedPreferences sharedPreferences = getSharedPreferences("saveToken", MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("tokenSave", token);
        editor.apply();
    }

    private void notErrorEdit(){
        editTextRegistrationName.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationSurname.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationPatronymic.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationEmail.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationPassword.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationClass.setBackgroundResource(R.drawable.edit_text_background);
        editTextRegistrationAge.setBackgroundResource(R.drawable.edit_text_background);
        textRegistrationEmailError.setVisibility(View.GONE);
        textRegistrationPasswordError.setVisibility(View.GONE);
        textRegistrationClassError.setVisibility(View.GONE);
        textRegistrationAgeError.setVisibility(View.GONE);
    }

}