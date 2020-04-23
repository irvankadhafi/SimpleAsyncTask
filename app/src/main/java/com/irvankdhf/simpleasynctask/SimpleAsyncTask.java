package com.irvankdhf.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultTextView;

    public SimpleAsyncTask(TextView tv, TextView result) {
        mTextView = new WeakReference<>(tv);
        mResultTextView = new WeakReference<>(result);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int s = n * 200;
        publishProgress(n);

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleep " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mResultTextView.get().setText("Current sleep time: " + values[0] + " ms");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }


}
