package com.example.inversetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inversetracker.activities.MainMenuActivity;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.ResponseModelClass;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton buttonBackCourse;
    private LinearLayout layoutCourseActivity;
    private ProgressBar progressCourseActivity;
    private int id;
    private MaterialButton buttonCourseDownloadDocument, buttonCourseGo;
    private TextView textCourseName, textCourseIsOpen, textDescriptionCourse, textPlaceCourse, textTeacherCourse, nameGroupsCourse, textDateOne, textDateTwo, textDateThree, textLimitCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        id = Integer.parseInt(getIntent().getStringExtra("ID"));
        buttonBackCourse = findViewById(R.id.buttonBackCourse);
        buttonBackCourse.setOnClickListener(this);
        layoutCourseActivity = findViewById(R.id.layoutCourseActivity);
        progressCourseActivity = findViewById(R.id.progressCourseActivity);
        textCourseName = findViewById(R.id.textCourseName);
        textDescriptionCourse = findViewById(R.id.textDescriptionCourse);
        textPlaceCourse = findViewById(R.id.textPlaceCourse);
        textTeacherCourse = findViewById(R.id.textTeacherCourse);
        nameGroupsCourse = findViewById(R.id.nameGroupsCourse);
        textDateOne = findViewById(R.id.textDateOne);
        textDateThree = findViewById(R.id.textDateThree);
        textDateTwo = findViewById(R.id.textDateTwo);
        buttonCourseDownloadDocument = findViewById(R.id.buttonCourseDownloadDocument);
        buttonCourseDownloadDocument.setOnClickListener(this);
        textLimitCourse = findViewById(R.id.textLimitCourse);
        buttonCourseGo = findViewById(R.id.buttonCourseGo);
        buttonCourseGo.setOnClickListener(this);
        textCourseIsOpen = findViewById(R.id.textCourseIsOpen);
        getRequest();
        progressCourseActivity.setVisibility(View.GONE);
        layoutCourseActivity.setVisibility(View.VISIBLE);
    }

    private void getRequest(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getCoursesIndex("Token " + token, id);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()){
                    ResponseModelClass responseModelClass = response.body();
                    textCourseName.setText(responseModelClass.getName());
                    textDescriptionCourse.setText(responseModelClass.getDescriptionText());
                    textPlaceCourse.setText(responseModelClass.getPlace());
                    textTeacherCourse.setText(responseModelClass.getTeacher().getLastname() + " " + responseModelClass.getTeacher().getFirstname()+ " " + responseModelClass.getTeacher().getPatronymic());
                    nameGroupsCourse.setText(responseModelClass.getGroups().get(0).getName());
                    String date = getDate(Integer.parseInt(responseModelClass.getGroups().get(0).getSchedule().get(0).getDay()));
                    String time = responseModelClass.getGroups().get(0).getSchedule().get(0).getTime();
                    String s = time.substring(0, Math.min(5, time.length() - 1));
                    textDateOne.setText(date + " - " + s);
                    String date2 = getDate(Integer.parseInt(responseModelClass.getGroups().get(0).getSchedule().get(1).getDay()));
                    String time2 = responseModelClass.getGroups().get(0).getSchedule().get(1).getTime();
                    String s2 = time2.substring(0, Math.min(5, time2.length() - 1));
                    textDateTwo.setText(date2 + " - " + s2);
                    String date3 = getDate(Integer.parseInt(responseModelClass.getGroups().get(0).getSchedule().get(2).getDay()));
                    String time3 = responseModelClass.getGroups().get(0).getSchedule().get(2).getTime();
                    String s3 = time3.substring(0, Math.min(5, time3.length() - 1));
                    textDateThree.setText(date3 + " - " + s3);
                    String now = String.valueOf(responseModelClass.getGroups().get(0).getMembers().size());
                    String limit = responseModelClass.getGroups().get(0).getLimit();
                    textLimitCourse.setText(now + " из " + limit + " учеников записались");
                    if (!responseModelClass.getGroups().get(0).isOpen()){
                        buttonCourseGo.setVisibility(View.INVISIBLE);
                        textCourseIsOpen.setVisibility(View.VISIBLE);
                    }
                }else{
                    progressCourseActivity.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                progressCourseActivity.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getDate(int i){
        switch (i){
            case 1:
                return "Пн";
            case 2:
                return "Вт";
            case 3:
                return "Ср";
            case 4:
                return "Чт";
            case 5:
                return "Пт";
            case 6:
                return "Сб";
            case 7:
                return "Вс";
        }
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackCourse:
                buttonBackCourse.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_click));
                Intent intent = new Intent(CourseActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.buttonCourseDownloadDocument:
                getBaseDocument();
        }
    }

    private void getBaseDocument(){
        Toast.makeText(getApplicationContext(), "sddgjkrnkg" , Toast.LENGTH_SHORT).show();
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<ResponseModelClass> call = APIController.getInstance().getAPI().getDocumentExample("Token " + token, id);
        call.enqueue(new Callback<ResponseModelClass>() {
            @Override
            public void onResponse(Call<ResponseModelClass> call, Response<ResponseModelClass> response) {
                if (response.isSuccessful()){
                    ResponseModelClass responseModelClass = response.body();
                    String base = responseModelClass.getDocument();
                }else{
                    Toast.makeText(getApplicationContext(), "Ошибка скачивания" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelClass> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ошибка: проверьте подключение к интернету" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}