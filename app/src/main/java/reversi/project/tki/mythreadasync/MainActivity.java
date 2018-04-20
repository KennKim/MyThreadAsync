package reversi.project.tki.mythreadasync;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import reversi.project.tki.mythreadasync.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_main);
        b.setActivity(this);




    }


    public void onClickbtn1(View view) {

        startActivity(new Intent(MainActivity.this, MyAsyncTestActivity.class));


    }


}
