package com.example.inversetracker.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.inversetracker.CourseActivity;
import com.example.inversetracker.R;
import com.example.inversetracker.interfaces.RecyclerViewInterface;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.RecyclerViewCoursesAdapter;
import com.example.inversetracker.models.RecyclerViewNewsAdapter;
import com.example.inversetracker.models.ResponseModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesFragment extends Fragment{

    private RecyclerView recyclerAllCourses;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewCoursesAdapter recyclerViewCoursesAdapter;
    private List<ResponseModelClass> responseRecyclerCourses;
    private ProgressBar progressAllCourses;
    private LinearLayout layoutEmptyAllCourses, layoutRecyclerAllCourses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        recyclerAllCourses = getActivity().findViewById(R.id.recyclerAllCourses);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerAllCourses.setLayoutManager(layoutManager);
        progressAllCourses = getActivity().findViewById(R.id.progressAllCourses);
        layoutRecyclerAllCourses = getActivity().findViewById(R.id.layoutRecyclerAllCourses);
        layoutEmptyAllCourses = getActivity().findViewById(R.id.layoutEmptyAllCourses);
        getAllCourses();
    }

    private void getAllCourses(){
        SharedPreferences sPref1 = getActivity().getSharedPreferences("saveToken", MODE_PRIVATE);
        String token = sPref1.getString("tokenSave", "");
        Call<List<ResponseModelClass>> call = APIController.getInstance().getAPI().getAllCourses("Token " + token);
        call.enqueue(new Callback<List<ResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ResponseModelClass>> call, Response<List<ResponseModelClass>> response) {
                if (response.isSuccessful()){
                    responseRecyclerCourses = response.body();
                    recyclerViewCoursesAdapter = new RecyclerViewCoursesAdapter(responseRecyclerCourses, getActivity(), new RecyclerViewInterface() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(getActivity(), CourseActivity.class);
                            intent.putExtra("ID", responseRecyclerCourses.get(position).getIdResponse());
                            startActivity(intent);
                        }
                    });
                    recyclerAllCourses.setAdapter(recyclerViewCoursesAdapter);
                    progressAllCourses.setVisibility(View.GONE);
                    layoutRecyclerAllCourses.setVisibility(View.VISIBLE);
                    if (responseRecyclerCourses.size() == 0){
                        layoutEmptyAllCourses.setVisibility(View.VISIBLE);
                        layoutRecyclerAllCourses.setVisibility(View.GONE);
                    }
                }else{
                    progressAllCourses.setVisibility(View.GONE);
                    layoutEmptyAllCourses.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModelClass>> call, Throwable t) {
                progressAllCourses.setVisibility(View.GONE);
                layoutEmptyAllCourses.setVisibility(View.VISIBLE);
                Log.d("MyLog", t.getMessage());
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
            }
        });
    }
}