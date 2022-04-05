package ru.samsung.itschool.mdev.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewRow> {

    private ArrayList<Answer> answers;

    public MyAdapter(ArrayList<Answer> arrayList) {
        this.answers = arrayList;
    }

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new ViewRow(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        holder.textView.setText(answers.get(position).getElementPureHtml());
    }

    @Override
    public int getItemCount() {
        if(answers == null) {
            return 0;
        } else
        return answers.size();
    }

    class ViewRow extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewRow(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
