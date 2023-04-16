package com.example.inversetracker.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.inversetracker.R;
import com.example.inversetracker.models.APIController;
import com.example.inversetracker.models.RecyclerViewNewsAdapter;
import com.example.inversetracker.models.ResponseModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {

    private RecyclerView recyclerNews;
    private RecyclerViewNewsAdapter adapter;
    private List<ResponseModelClass> responseRecyclerNews;
    private LinearLayout layoutEmptyNews, linearRecyclerNews;
    private ProgressBar progressNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        recyclerNews = getActivity().findViewById(R.id.recyclerNews);
        recyclerNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        responseRecyclerNews = new ArrayList<>();
        layoutEmptyNews = getActivity().findViewById(R.id.layoutEmptyNews);
        linearRecyclerNews = getActivity().findViewById(R.id.linearRecyclerNews);
        progressNews = getActivity().findViewById(R.id.progressNews);
        Call<List<ResponseModelClass>> call = APIController.getInstance().getAPI().getNews();
        call.enqueue(new Callback<List<ResponseModelClass>>() {
            @Override
            public void onResponse(Call<List<ResponseModelClass>> call, Response<List<ResponseModelClass>> response) {
                if (response.isSuccessful()){
                    responseRecyclerNews = response.body();
                    adapter = new RecyclerViewNewsAdapter(responseRecyclerNews, getActivity());
                    recyclerNews.setAdapter(adapter);
                    progressNews.setVisibility(View.GONE);
                    linearRecyclerNews.setVisibility(View.VISIBLE);
                    if (responseRecyclerNews.size() == 0){
                        layoutEmptyNews.setVisibility(View.VISIBLE);
                        linearRecyclerNews.setVisibility(View.GONE);
                    }
                }else{
                    progressNews.setVisibility(View.GONE);
                    layoutEmptyNews.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseModelClass>> call, Throwable t) {
                Toast.makeText(getActivity(), "Ошибка: проверьте подключение к интернету", Toast.LENGTH_LONG).show();
                progressNews.setVisibility(View.GONE);
                layoutEmptyNews.setVisibility(View.VISIBLE);
            }
        });
    }

}