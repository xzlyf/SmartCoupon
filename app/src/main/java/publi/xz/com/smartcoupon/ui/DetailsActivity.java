package publi.xz.com.smartcoupon.ui;

import android.os.Handler;
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
import publi.xz.com.smartcoupon.ui.view.IView;

import static publi.xz.com.smartcoupon.utils.TaobaoUtil.jump2TaobaoQuan;

/**
 * 商品详情图
 */
public class DetailsActivity extends BaseActivity implements IView{
    private String id;
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
        presenter.getDetailFromNet(Local.DETAILS+id);
    }

    @Override
    public void showData(final Object object) {
        if (object instanceof Detail){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Log.d("xz", "run: "+((Detail)object).getResult().getPic());
                    Picasso.get().load(((Detail)object).getResult().getPic()).into(pic);
                    detail_short_title.setText(((Detail)object).getResult().getD_title());
                    detail_Org_Price.setText("原价"+((Detail)object).getResult().getOrg_Price()+"￥");
                    detail_title.setText(((Detail)object).getResult().getTitle());
                    detail_Price.setText("券后"+((Detail)object).getResult().getPrice()+"￥");
                    detail_Sales_num.setText("销量"+((Detail)object).getResult().getSales_num());
                    Quan_condition.setText("单笔满"+((Detail)object).getResult().getQuan_condition()+"元可用");
                    Quan_time.setText("结束时间"+((Detail)object).getResult().getQuan_time());
                    Quan_price.setText("立即领取"+((Detail)object).getResult().getQuan_price()+"元优惠券");
                    Quan_price.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //跳转至淘宝领券界面
                            jump2TaobaoQuan(DetailsActivity.this,((Detail)object).getResult().getQuan_link());
                        }
                    });
                    stopLoading();
                }
            });
        }else{
            sToast("致命错误");
        }
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

}
