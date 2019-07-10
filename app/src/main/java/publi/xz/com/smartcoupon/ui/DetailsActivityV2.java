package publi.xz.com.smartcoupon.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.logging.Logger;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.ui.view.IView;

import static publi.xz.com.smartcoupon.utils.CheckPackage.checkPackage;
import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

/**
 * 商品详情图2.0
 */

public class DetailsActivityV2 extends BaseActivity implements IView {
    private Baoyou9_9 baoyouData;
    private int position;

    private ImageView mainPic;
    private TextView actualPrice;
    private TextView originalPrice;
    private ImageView shopType;
    private TextView dtitle;
    private TextView desc;
    private TextView monthSales;
    private TextView hotPush;
    private TextView couponEndTime;
    private Button couponPrice;
    private ImageView marketingMainPic;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_details_v2;
    }

    @Override
    public void init_Data() {
        //设置透明状态栏
        makeStatusBarTransparent(this);
        baoyouData = (Baoyou9_9) getIntent().getSerializableExtra("BaoyouData");
        position = getIntent().getIntExtra("position", 1);
        findID();
        //加载主图
        Picasso.get().load(baoyouData.getData().getList().get(position).getMainPic()).fit().into(mainPic);
        //加载营销主图
//        Picasso.get().load(baoyouData.getData().getList().get(position).getMarketingMainPic()).into(marketingMainPic);
        Glide.with(this).load(baoyouData.getData().getList().get(position).getMarketingMainPic()).into(marketingMainPic);
        marketingMainPic.refreshDrawableState();//刷新试图状态
        //显示商家类型
        if (baoyouData.getData().getList().get(position).getShopType() == 1) {
            Picasso.get().load(R.mipmap.tianmaologo).into(shopType);
        } else {
            Picasso.get().load(R.mipmap.taobaologo).into(shopType);
        }
        actualPrice.setText(baoyouData.getData().getList().get(position).getActualPrice() + "￥");
        originalPrice.setText(baoyouData.getData().getList().get(position).getOriginalPrice() + "￥");
        dtitle.setText(baoyouData.getData().getList().get(position).getDtitle());
        desc.setText(baoyouData.getData().getList().get(position).getDesc());
        monthSales.setText("月销" + baoyouData.getData().getList().get(position).getMonthSales());
        hotPush.setText("热度" + baoyouData.getData().getList().get(position).getHotPush());
        couponEndTime.setText("结束时间" + baoyouData.getData().getList().get(position).getCouponEndTime());
        couponPrice.setText("领" + baoyouData.getData().getList().get(position).getCouponPrice() + "元券");
        couponPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String packageName = "com.taobao.taobao";
                Uri uri = Uri.parse(baoyouData.getData().getList().get(position).getCouponLink()); // 商品地址

                if (checkPackage(DetailsActivityV2.this, packageName)) {
                    //跳转到淘宝客户端优惠券界面
                    Intent intent = new Intent();
                    intent.setAction("Android.intent.action.VIEW");
                    intent.setData(uri);
                    intent.setClassName(packageName, "com.taobao.browser.BrowserActivity");
                    startActivity(intent);
                } else {

                    //跳转到浏览器优惠券界面
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    Toast.makeText(DetailsActivityV2.this, "推荐使用淘宝App进行领券", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPic = null;
        actualPrice = null;
        originalPrice = null;
        shopType = null;
        dtitle = null;
        desc = null;
        monthSales = null;
        hotPush = null;
        couponEndTime = null;
        couponPrice = null;
        marketingMainPic = null;
    }

    private void findID() {
        mainPic = findViewById(R.id.mainPic);
        actualPrice = findViewById(R.id.actualPrice);
        originalPrice = findViewById(R.id.originalPrice);
        shopType = findViewById(R.id.shopType);
        dtitle = findViewById(R.id.dtitle);
        desc = findViewById(R.id.desc);
        monthSales = findViewById(R.id.monthSales);
        hotPush = findViewById(R.id.hotPush);
        couponEndTime = findViewById(R.id.couponEndTime);
        couponPrice = findViewById(R.id.couponPrice);
        marketingMainPic = findViewById(R.id.marketingMainPic);
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void sToast(String msg) {

    }
}
