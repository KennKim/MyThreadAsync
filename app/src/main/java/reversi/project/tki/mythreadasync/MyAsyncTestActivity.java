package reversi.project.tki.mythreadasync;

import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.lang.ref.WeakReference;

import reversi.project.tki.mythreadasync.databinding.ActivityMyAsyncTestBinding;

public class MyAsyncTestActivity extends AppCompatActivity {

    private ActivityMyAsyncTestBinding b;


    private int value;
    private MyAsync myAsync;


    private void handleMessage(Message msg) {
        int i = msg.arg1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_my_async_test);
        b.setActivity(this);



    }


    public void onClickbtn1(View view) {

        myAsync = new MyAsync();
        myAsync.execute(100);

    }
    public void onClickbtn2(View view) {

        myAsync.cancel(true);

    }





    private MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<MyAsyncTestActivity> mActivity;

        private MyHandler(MyAsyncTestActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MyAsyncTestActivity activity = mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }

    class MyAsync extends AsyncTask<Integer, Integer, Integer> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            value = 0;
            b.progress.setProgress(0);
            b.tv1.setText("준비");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            value = 100;
            b.progress.setProgress(value);
            b.tv1.setText("onPostExecute");

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            b.progress.setProgress(values[0].intValue());
            b.tv1.setText("진행중 : " + values[0]);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while (!isCancelled()) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

    }




}
