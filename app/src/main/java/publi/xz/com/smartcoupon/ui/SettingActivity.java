package publi.xz.com.smartcoupon.ui;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import publi.xz.com.smartcoupon.R;

import static publi.xz.com.smartcoupon.utils.CacheInfo.cleanPhotoCache;
import static publi.xz.com.smartcoupon.utils.CacheInfo.getPhotoCacheSize;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button cleanCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findID();
        init();
    }

    private void init() {
        cleanCache.setText("清理缓存（"+getPhotoCacheSize(this)+"Mb）");

    }

    private void findID() {
        cleanCache = findViewById(R.id.clean_cache);
        cleanCache.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clean_cache:
                cleanPhotoCache(this);
                Toast.makeText(this,"清理完成",Toast.LENGTH_SHORT).show();
                cleanCache.setText("清理缓存（"+getPhotoCacheSize(this)+"Mb）");
                break;
        }
    }
}
