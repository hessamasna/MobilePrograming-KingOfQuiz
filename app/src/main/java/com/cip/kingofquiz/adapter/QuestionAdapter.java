package com.cip.kingofquiz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.kingofquiz.R;
import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.LoggedInUser;
import com.cip.kingofquiz.model.Question;
import com.google.gson.Gson;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private Context context;
    private List<Api.QuestionAPI> questionArrayList;
    private AppDatabase db;


    public QuestionAdapter(Context context, List<Api.QuestionAPI> questionArrayList, AppDatabase db) {
        this.context = context;
        this.questionArrayList = questionArrayList;
        this.db = db;
    }

    @NonNull
    @Override
    public QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.question_item, parent, false);

        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionHolder holder, @SuppressLint("RecyclerView") int position) {
        Api.QuestionAPI question = questionArrayList.get(position);
        holder.setData(question);

        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) holder.itemView.findViewById(checkedId);

                if (radioButton.getText().equals(question.getCorrect_answer_API())) {
                    LoggedInUser.loggedInUser.getUser().setScore(calculateScore(3, question.getDifficulty_API()) + LoggedInUser.loggedInUser.getUser().getScore());
                    holder.question_answer.setText("You answered correctly ✌️");
                    holder.question_answer.setTextColor(Color.parseColor("#4CBB17"));
                    radioButton.setTextColor(Color.parseColor("#4CBB17"));

                } else {
                    LoggedInUser.loggedInUser.getUser().setScore(calculateScore(-1, question.getDifficulty_API()) + LoggedInUser.loggedInUser.getUser().getScore());
                    holder.question_answer.setText("You guessed wrong 😫,Correct answer is : " + question.getCorrect_answer_API());
                    holder.question_answer.setTextColor(Color.parseColor("#960019"));
                    radioButton.setTextColor(Color.parseColor("#960019"));
                }

                db.userDao().update(LoggedInUser.loggedInUser.getUser());

                holder.radioButton1.setEnabled(false);
                holder.radioButton2.setEnabled(false);
                holder.radioButton3.setEnabled(false);
                holder.radioButton4.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    /////////////

    class QuestionHolder extends RecyclerView.ViewHolder {

        private TextView Question_textView, question_answer;
        private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
        private RadioGroup radioGroup;


        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            Question_textView = itemView.findViewById(R.id.Question_textView);
            question_answer = itemView.findViewById(R.id.question_answer);
            radioButton1 = itemView.findViewById(R.id.radioButton1);
            radioButton2 = itemView.findViewById(R.id.radioButton2);
            radioButton3 = itemView.findViewById(R.id.radioButton3);
            radioButton4 = itemView.findViewById(R.id.radioButton4);
            radioGroup = itemView.findViewById(R.id.RadioGroup);
        }

        public void setData(Api.QuestionAPI question) {
            Question_textView.setText(question.getQuestion_API());
            question_answer.setText("");

            radioButton1.setText(question.getCorrect_answer_API());
            radioButton2.setText(question.getIncorrect_answers_API().get(0));
            radioButton3.setText(question.getIncorrect_answers_API().get(1));
            radioButton4.setText(question.getIncorrect_answers_API().get(2));
        }
    }

    private int calculateScore(int mainScore, String difficulty) {
        if (difficulty.toLowerCase().equals("easy")) {
            return mainScore;
        } else if (difficulty.toLowerCase().equals("medium")) {
            return mainScore * 2;
        } else if (difficulty.toLowerCase().equals("hard")) {
            return mainScore * 3;
        } else {
            return mainScore;
        }
    }


}
