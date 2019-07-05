package publi.xz.com.smartcoupon.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button test;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void init_Data() {
        findID();
    }

    private void findID() {
        test = findViewById(R.id.test);
        test.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test:
                startActivity(new Intent(MainActivity.this, Top100Activity.class));
                break;
        }
    }
}
