package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.DetailV2;
import publi.xz.com.smartcoupon.entity.Search;
import publi.xz.com.smartcoupon.ui.DetailsActivityV2;

/**
 * 搜索列表适配器
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Search search;

    public SearchAdapter(Context mContext) {
        context = mContext;


    }

    /**
     * 给外部刷新数据
     * @param mSearch
     */
    public void refresh(Search mSearch){
        search = null;
        search = mSearch;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commodity, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Picasso.get().load(search.getData().get(position).getMainPic()).fit().into(((ViewHolder) holder).commPic);
        ((ViewHolder) holder).shortTitle.setText(search.getData().get(position).getDtitle());
        ((ViewHolder) holder).salesNum.setText("月销"+search.getData().get(position).getMonthSales());
        ((ViewHolder) holder).quanPrice.setText("领"+search.getData().get(position).getCouponPrice()+"元券");
        ((ViewHolder) holder).orgPrice.setText("原价"+search.getData().get(position).getOriginalPrice());
        ((ViewHolder) holder).price.setText("券后："+search.getData().get(position).getActualPrice());
        ((ViewHolder) holder).quanPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //转载数据到实体类
                DetailV2 detail = new DetailV2();
                detail.setActualPrice(search.getData().get(position).getActualPrice()+"");
                detail.setCouponEndTime(search.getData().get(position).getCouponEndTime());
                detail.setCouponPrice(search.getData().get(position).getCouponPrice()+"");
                detail.setDesc(search.getData().get(position).getDesc());
                detail.setDtitle(search.getData().get(position).getDtitle());
                detail.setHotPush(search.getData().get(position).getHotPush()+"");
                detail.setMainPic(search.getData().get(position).getMainPic());
                detail.setMarketingMainPic(search.getData().get(position).getMarketingMainPic());
                detail.setMonthSales(search.getData().get(position).getMonthSales()+"");
                detail.setOriginalPrice(search.getData().get(position).getOriginalPrice()+"");
                detail.setShopType(search.getData().get(position).getShopType()+"");
                detail.setCouponLink(search.getData().get(position).getCouponLink());
                //传输自定义对象类型
                context.startActivity(new Intent(context, DetailsActivityV2.class).putExtra("DetailV2",detail));
            }
        });

    }

    @Override
    public int getItemCount() {
        return search.getData().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView commPic;
        private TextView shortTitle;
        private TextView salesNum;
        private Button quanPrice;
        private TextView orgPrice;
        private TextView price;


        public ViewHolder(View itemView) {
            super(itemView);
            commPic = itemView.findViewById(R.id.comm_pic);
            shortTitle = itemView.findViewById(R.id.short_title);
            salesNum = itemView.findViewById(R.id.sales_num);
            quanPrice = itemView.findViewById(R.id.quan_price);
            orgPrice = itemView.findViewById(R.id.org_Price);
            price = itemView.findViewById(R.id.price);
        }
    }
}
