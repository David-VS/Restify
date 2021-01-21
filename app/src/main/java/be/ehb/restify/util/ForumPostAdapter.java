package be.ehb.restify.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import be.ehb.restify.R;
import be.ehb.restify.model.ForumPost;

public class ForumPostAdapter extends RecyclerView.Adapter<ForumPostAdapter.ForumHolder> {

    class ForumHolder extends RecyclerView.ViewHolder{

        TextView titleTV, contentTV;

        public ForumHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.tv_title);
            contentTV = itemView.findViewById(R.id.tv_content);
        }
    }

    ArrayList<ForumPost> data;

    public ForumPostAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        View row = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        return new ForumHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumHolder holder, int position) {
        ForumPost mForumPost = data.get(position);

        holder.titleTV.setText(mForumPost.getSpecialisation());
        holder.contentTV.setText(mForumPost.getBody());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(ForumPost newPost){
        Log.d("REST", newPost.toString());
        data.add(newPost);
        notifyDataSetChanged();
    }
}
