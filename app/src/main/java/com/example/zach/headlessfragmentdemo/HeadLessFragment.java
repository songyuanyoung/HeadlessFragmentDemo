package com.example.zach.headlessfragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhangwenpurdue on 8/17/2017.
 */

public class HeadLessFragment extends Fragment {
    HeadlessFragmentListener mCallback;
    MyAsynTask myAsynTask;
    public  HeadLessFragment() {
        myAsynTask = new MyAsynTask();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(HeadLessFragment.class.getSimpleName(), "Headless fragment");

        return null;
    }
    public interface HeadlessFragmentListener {
        public void sendProgress(int progress);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mCallback = (HeadlessFragmentListener)context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Mush implement HeadlessFragmentListener ");
        }
    }


    public class MyAsynTask extends AsyncTask<String, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            for (int i = 0; !isCancelled() && i < 100; i++) {
                SystemClock.sleep(100);
                publishProgress(i);

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mCallback.sendProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
