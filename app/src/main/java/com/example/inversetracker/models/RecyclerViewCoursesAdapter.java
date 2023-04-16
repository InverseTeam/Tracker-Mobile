package com.example.inversetracker.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inversetracker.R;
import com.example.inversetracker.interfaces.RecyclerViewInterface;

import java.util.List;

public class RecyclerViewCoursesAdapter extends RecyclerView.Adapter<RecyclerViewCoursesAdapter.MyViewHolder> implements RecyclerViewInterface {

    private List<ResponseModelClass> list;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public RecyclerViewCoursesAdapter(List<ResponseModelClass> list, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String desc = list.get(position).getDescriptionText();
        String s = (desc.substring(0, Math.min(70, desc.length() - 1))) + "...";
        holder.textTitleItemCourses.setText(list.get(position).getName());
        holder.textDescriptionItemCourses.setText(s);
        holder.textCategoryItemCourses.setText(list.get(position).getCategory().getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemClick(int position) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitleItemCourses, textDescriptionItemCourses, textCategoryItemCourses;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            textTitleItemCourses = itemView.findViewById(R.id.textTitleItemCourses);
            textDescriptionItemCourses = itemView.findViewById(R.id.textDescriptionItemCourses);
            textCategoryItemCourses = itemView.findViewById(R.id.textCategoryItemCourses);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

}
