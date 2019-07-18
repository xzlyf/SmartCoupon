package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.constant.Local;
import publi.xz.com.smartcoupon.entity.Detail;
import publi.xz.com.smartcoupon.ui.presenter.Presenter_Details;
import publi.xz.com.smartcoupon.ui.view.IView;

import static publi.xz.com.smartcoupon.utils.TaobaoUtil.jump2TaobaoQuan;

/**
 * 商品详情图
 */
public class DetailsActivity extends BaseActivity implements IView{
    private String id;
    private Presenter_Details presenter;
    private ImageView pic;
    private TextView detail_short_title;
    private TextView detail_title;
    private TextView detail_Org_Price;
    private TextView detail_Price;
    private TextView detail_Sales_num;
    private TextView Quan_condition;
    private TextView Quan_time;
    private Button Quan_price;
    Handler handler = new Handler();
    @Override
    public int getLayoutResource() {
        return R.layout.activity_details;
    }

    @Override
    public void init_Data() {
        startLoading();
        id = getIntent().getStringExtra("id");
        findID();
        presenter = new Presenter_Details(this);
        presenter.getDetailFromNet(Local.DETAILS+id);
    }

    public void findID() {
        pic = findViewById(R.id.detail_pic);
        detail_short_title = findViewById(R.id.detail_short_title);
        detail_title = findViewById(R.id.detail_title);
        detail_Org_Price = findViewById(R.id.detail_Org_Price);
        detail_Price = findViewById(R.id.detail_Price);
        detail_Sales_num = findViewById(R.id.detail_Sales_num);
        Quan_condition = findViewById(R.id.Quan_condition);
        Quan_time = findViewById(R.id.Quan_time);
        Quan_price = findViewById(R.id.Quan_price);
    }

    @Override
    public void startLoading() {
        showLoading();
    }

    @Override
    public void stopLoading() {
        dismissLoading();
    }

    @Override
    public void sToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setDetailData(final Detail detailData) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("xz", "run: "+detailData.getResult().getPic());
                Picasso.get().load(detailData.getResult().getPic()).into(pic);
                detail_short_title.setText(detailData.getResult().getD_title());
                detail_Org_Price.setText("原价"+detailData.getResult().getOrg_Price()+"￥");
                detail_title.setText(detailData.getResult().getTitle());
                detail_Price.setText("券后"+detailData.getResult().getPrice()+"￥");
                detail_Sales_num.setText("销量"+detailData.getResult().getSales_num());
                Quan_condition.setText("单笔满"+detailData.getResult().getQuan_condition()+"元可用");
                Quan_time.setText("结束时间"+detailData.getResult().getQuan_time());
                Quan_price.setText("立即领取"+detailData.getResult().getQuan_price()+"元优惠券");
                Quan_price.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //跳转至淘宝领券界面
                        jump2TaobaoQuan(DetailsActivity.this,detailData.getResult().getQuan_link());
                    }
                });
                stopLoading();
            }
        });

    }
}
