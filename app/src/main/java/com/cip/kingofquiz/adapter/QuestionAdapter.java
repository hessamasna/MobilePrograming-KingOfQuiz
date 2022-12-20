package com.cip.kingofquiz.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.kingofquiz.R;
import com.cip.kingofquiz.db.AppDatabase;
import com.cip.kingofquiz.model.Question;
import com.google.gson.Gson;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionHolder> {

    private Context context;
    private List<Question> questionArrayList;
    private AppDatabase db;


    public QuestionAdapter(Context context, List<Question> questionArrayList, AppDatabase db) {
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
        Question question = questionArrayList.get(position);
        holder.setData(question);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(context, "clicked" + courseArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Course clickedCourse = courseArrayList.get(position);
//                clickedCourse.setHasCourse(true);
//                db.courseDao().update(clickedCourse);
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


        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            Question_textView = itemView.findViewById(R.id.Question_textView);
//            course_time_1 = itemView.findViewById(R.id.course_time_1);
//            course_time_2 = itemView.findViewById(R.id.course_time_2);
//            course_instructor = itemView.findViewById(R.id.course_instructor);
//            course_units = itemView.findViewById(R.id.course_units);
        }

        public void setData(Question question) {
            Gson gson = new Gson();
//            CourseTime[] courseTimes = gson.fromJson(String.valueOf(course.getClass_times()), CourseTime[].class);
//
//
            Question_textView.setText(question.getQuestion());
//            course_time_1.setText(courseTimes[0].getStart());
//            course_time_2.setText(courseTimes[0].getEnd());
//            course_instructor.setText(course.getInstructor());
//            course_units.setText(Integer.toString(course.getUnits()));
        }
    }

    public void showPopup(View v, Question question) {
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

//    private boolean checkCourseDate(Q crs) {
//        Gson gson = new Gson();
//        List<Course> courses = db.courseDao().selectHasCourse();
//
//        if (courses.size() == 0)
//            return true;
//
//        CourseTime[] currentCourseTimes = gson.fromJson(String.valueOf(crs.getClass_times()), CourseTime[].class);
//
//        for (CourseTime currentCourseTime : currentCourseTimes) {
//            for (Course course : courses) {
//                CourseTime[] courseTimes = gson.fromJson(String.valueOf(course.getClass_times()), CourseTime[].class);
//                for (CourseTime courseTime : courseTimes) {
//                    if (courseTime.getDay().equals(currentCourseTime.getDay())) {
//                        if (Double.parseDouble(courseTime.getStart()) < Double.parseDouble(currentCourseTime.getStart()) && Double.parseDouble(courseTime.getEnd()) > Double.parseDouble(currentCourseTime.getStart())) {
//                            return false;
//                        }
//                        if (Double.parseDouble(courseTime.getStart()) < Double.parseDouble(currentCourseTime.getEnd()) && Double.parseDouble(courseTime.getEnd()) > Double.parseDouble(currentCourseTime.getEnd())) {
//                            return false;
//                        }
//                    }
//                }
//            }
//        }
//
//        return true;
//    }
}
