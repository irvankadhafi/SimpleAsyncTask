package com.irvankdhf.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    //    private WeakReference<TextView> mResultTextView;
    private WeakReference<ProgressBar> mProgressBar;
    private static final int CHUNK_SIZE = 5;

    public SimpleAsyncTask(TextView tv, ProgressBar bar) {
        mTextView = new WeakReference<>(tv);
//        mResultTextView = new WeakReference<>(result);
        mProgressBar = new WeakReference<>(bar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 200;

        int chunkSize = s / CHUNK_SIZE;

//        publishProgress(n);
        for (int i = 0; i < CHUNK_SIZE; i++) {
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(((i + 1) * 100) / CHUNK_SIZE);
        }
        return "Awake at last after sleep " + s + " milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        mResultTextView.get().setText("Current sleep time: " + values[0] + " ms");
          mProgressBar.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }


}
