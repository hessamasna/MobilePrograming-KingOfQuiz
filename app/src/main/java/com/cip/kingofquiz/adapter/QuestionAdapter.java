package com.cip.kingofquiz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.kingofquiz.R;
import com.cip.kingofquiz.api.Api;
import com.cip.kingofquiz.db.AppDatabase;
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v, questionArrayList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    /////////////

    class QuestionHolder extends RecyclerView.ViewHolder {

        private TextView Question_textView;
        private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;


        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            Question_textView = itemView.findViewById(R.id.Question_textView);
            radioButton1 = itemView.findViewById(R.id.radioButton1);
            radioButton2 = itemView.findViewById(R.id.radioButton2);
            radioButton3 = itemView.findViewById(R.id.radioButton3);
            radioButton4 = itemView.findViewById(R.id.radioButton4);

        }

        public void setData(Api.QuestionAPI question) {
            Question_textView.setText(question.getQuestion_API());

            radioButton1.setText(question.getCorrect_answer_API());
            radioButton2.setText(question.getIncorrect_answers_API().get(0));
            radioButton3.setText(question.getIncorrect_answers_API().get(1));
            radioButton4.setText(question.getIncorrect_answers_API().get(2));
        }
    }

    public void showPopup(View v, Api.QuestionAPI question) {
//        TextView courseName;
//        TextView courseTime;
//        TextView courseExam;
//        Button saveBtn;
//        Button cancelBtn;
//
//        Dialog myDialog = new Dialog(context);
//        myDialog.setContentView(R.layout.activity_popup);
//
//        courseName = (TextView) myDialog.findViewById(R.id.courseName);
//        courseTime = (TextView) myDialog.findViewById(R.id.courseTime);
//        courseExam = (TextView) myDialog.findViewById(R.id.courseExam);
//        cancelBtn = (Button) myDialog.findViewById(R.id.cancelBtn);
//        saveBtn = (Button) myDialog.findViewById(R.id.saveBtn);
//        courseName.setText(course.getName());
//        courseExam.setText(course.getExam_time());
//        courseTime.setText(course.getInfo());
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//
//        saveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (db.courseDao().checkCourse(course.uid)) {
//                    Toast.makeText(context, "قبلا اضافه شده است", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (!checkCourseDate(course)) {
//                        Toast.makeText(context, "تداخل زمانی وجود دارد", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "اضافه شد»" + course.getName(), Toast.LENGTH_SHORT).show();
//
//                        course.setHasCourse(true);
//                        db.courseDao().update(course);
//                        myDialog.dismiss();
//                    }
//
//                }
//
//            }
//        });
//
//
//        myDialog.show();
    }

}
