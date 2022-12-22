package com.cip.kingofquiz.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.kingofquiz.R;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.Question;
import com.cip.kingofquiz.model.User;
import com.google.gson.Gson;

import java.util.List;

public class ScoreboardAdapter extends RecyclerView.Adapter<ScoreboardAdapter.ScoreboardHolder> {

    private Context context;
    private List<User> UserArrayList;
    private AppDatabase db;


    public ScoreboardAdapter(Context context, List<User> UserArrayList, AppDatabase db) {
        this.context = context;
        this.UserArrayList = UserArrayList;
        this.db = db;
    }

    @NonNull
    @Override
    public ScoreboardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scoreboard_item, parent, false);
        return new ScoreboardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreboardHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = UserArrayList.get(position);
        holder.setData(user , position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopup(v, UserArrayList.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return UserArrayList.size();
    }

    /////////////

    class ScoreboardHolder extends RecyclerView.ViewHolder {

        private TextView scoreboardUsername, scoreboardScore , scoreboardRank;


        public ScoreboardHolder(@NonNull View itemView) {
            super(itemView);
            scoreboardUsername = itemView.findViewById(R.id.scoreboardUsername);
            scoreboardScore = itemView.findViewById(R.id.scoreboardScore);
            scoreboardRank = itemView.findViewById(R.id.scoreboardRank);
        }

        public void setData(User user , int position) {
            position ++;
            if (position == 1) {
                itemView.setBackgroundResource(R.drawable.first_man);
                scoreboardUsername.setTextColor(Color.parseColor("#000000"));
                scoreboardScore.setTextColor(Color.parseColor("#000000"));
                scoreboardRank.setTextColor(Color.parseColor("#000000"));
            }
            else if (position == 2) {
                itemView.setBackgroundResource(R.drawable.second_one);
                scoreboardUsername.setTextColor(Color.parseColor("#000000"));
                scoreboardScore.setTextColor(Color.parseColor("#000000"));
                scoreboardRank.setTextColor(Color.parseColor("#000000"));
            }
            else if (position == 3) {
                itemView.setBackgroundResource(R.drawable.third_guy);
                scoreboardUsername.setTextColor(Color.parseColor("#000000"));
                scoreboardScore.setTextColor(Color.parseColor("#000000"));
                scoreboardRank.setTextColor(Color.parseColor("#000000"));
            }
            scoreboardUsername.setText(user.getEmail());
            scoreboardScore.setText("" + user.getScore());
            scoreboardRank.setText("" + position);
        }
    }

    public void showPopup(View v, User user) {

        TextView popup_username;
        TextView popup_email;
        TextView popup_phone;
        Button cancelBtn;

        Dialog myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.scoreboard_popup);
        myDialog.getWindow().setBackgroundDrawableResource(R.drawable.shap);

        popup_username = (TextView) myDialog.findViewById(R.id.popup_userame);
        popup_email = (TextView) myDialog.findViewById(R.id.popup_email);
        popup_phone = (TextView) myDialog.findViewById(R.id.popup_phone);
        cancelBtn = (Button) myDialog.findViewById(R.id.cancelBtn);

        popup_username.setText(user.getName());
        popup_email.setText(user.getEmail());
        popup_phone.setText(user.getPhone());

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}
