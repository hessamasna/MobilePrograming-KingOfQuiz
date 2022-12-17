package com.cip.kingofquiz.api;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static void fetchData(String difficulty, int questionsCount, String category) {
        String url = "https://opentdb.com/api.php?";
        url += "amount=" + questionsCount+"&";

        if (!category.equals("Any Category")) {
            url += "category=" + getCategoryNumber(category)+"&";
        }

        if (!difficulty.equals("Any Difficulty")) {
            url += "difficulty=" + difficulty.toLowerCase();
        }
        Request request = new Request.Builder().url(url).build();
        Log.d("Api url: ", url);

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //todo zamani ke rid
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String res = response.body().string();
                    Log.d("Api response: ", res);
                }else {
                    //todo zamani ke rid
                }
            }
        });

    }

    private static String getCategoryNumber(String category) {
        String num = "";
        switch (category) {
            case "General Knowledge":
                num = "9";
                break;
            case "Science: Computers":
                num = "18";
                break;

            case "Science: Mathematics":
                num = "19";
                break;

            case "Sports":
                num = "21";
                break;

            case "History":
                num = "23";
                break;

            case "Politics":
                num = "24";
                break;

        }
        return num;
    }

}
