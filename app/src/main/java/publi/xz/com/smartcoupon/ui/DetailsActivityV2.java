package publi.xz.com.smartcoupon.ui;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.base.BaseActivity;
import publi.xz.com.smartcoupon.entity.DetailV2;
import publi.xz.com.smartcoupon.ui.view.IView;

import static publi.xz.com.smartcoupon.utils.TaobaoUtil.jump2TaobaoQuan;
import static publi.xz.com.smartcoupon.utils.TransparentBarUtil.makeStatusBarTransparent;

/**
 * 商品详情图2.0
 * 兼容更多adapter的接入
 */

public class DetailsActivityV2 extends BaseActivity implements IView {
    private DetailV2 detailV2;

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
        detailV2 = (DetailV2) getIntent().getSerializableExtra("DetailV2");
        findID();
        //加载主图
        Picasso.get().load(detailV2.getMainPic()).fit().into(mainPic);
        //加载营销主图
        Glide.with(this).load(detailV2.getMarketingMainPic()).into(marketingMainPic);
        marketingMainPic.refreshDrawableState();//刷新试图状态
        //显示商家类型
        if (detailV2.getShopType().equals(1)) {
            Picasso.get().load(R.mipmap.tianmaologo).into(shopType);
        } else {
            Picasso.get().load(R.mipmap.taobaologo).into(shopType);
        }
        actualPrice.setText(detailV2.getActualPrice() + "￥");
        originalPrice.setText(detailV2.getOriginalPrice() + "￥");
        dtitle.setText(detailV2.getDtitle());
        desc.setText(detailV2.getDesc());
        monthSales.setText("月销" + detailV2.getMonthSales());
        hotPush.setText("热度" + detailV2.getHotPush());
        couponEndTime.setText("结束时间" + detailV2.getCouponEndTime());
        couponPrice.setText("领" + detailV2.getCouponPrice() + "元券");
        couponPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转至淘宝领券界面
                jump2TaobaoQuan(DetailsActivityV2.this, detailV2.getCouponLink());

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

    public void findID() {
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
