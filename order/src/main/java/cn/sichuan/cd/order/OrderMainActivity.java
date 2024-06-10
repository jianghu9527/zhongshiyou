package cn.sichuan.cd.order;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.logging.LogManager;

public class OrderMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.module_order_layout);
        Log.d("----------OrderMainActivity-------------","-----------OrderMainActivity------111------");
    }
}
