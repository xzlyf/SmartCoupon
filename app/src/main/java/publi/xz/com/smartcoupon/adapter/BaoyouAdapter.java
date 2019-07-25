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

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.Baoyou9_9;
import publi.xz.com.smartcoupon.entity.DetailV2;
import publi.xz.com.smartcoupon.entity.PPBrand;
import publi.xz.com.smartcoupon.ui.DetailsActivityV2;
import publi.xz.com.smartcoupon.ui.SearchActivity;
import publi.xz.com.smartcoupon.utils.ItemOnclickListener;

public class BaoyouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    private Context context;
    private PPBrand brand;
    private Baoyou9_9.DataBean baoyou;

    private ItemOnclickListener onclickListener;

    public BaoyouAdapter(Context context) {
        this.context = context;
        brand = new PPBrand();
        baoyou = new Baoyou9_9.DataBean();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_commodity, parent, false));//指定根部据

        } else if (viewType == TYPE_FOOTER) {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            Picasso
                    .get()
                    .load(baoyou.getList().get(position).getMainPic())
                    .fit()
                    .into(((ViewHolder) holder).comm_pic);
            ((ViewHolder) holder).short_title.setText(baoyou.getList().get(position).getDtitle());
            ((ViewHolder) holder).sales_num.setText("月销" + baoyou.getList().get(position).getMonthSales());
            ((ViewHolder) holder).org_Price.setText("原价" + baoyou.getList().get(position).getOriginalPrice());
            ((ViewHolder) holder).price.setText("券后" + baoyou.getList().get(position).getActualPrice());
            ((ViewHolder) holder).quan_price.setText("领" + baoyou.getList().get(position).getCouponPrice() + "元券");
            ((ViewHolder) holder).quan_price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //转载数据到实体类
                    DetailV2 detail = new DetailV2();
                    detail.setCouponEndTime(baoyou.getList().get(position).getCouponEndTime());
                    detail.setCouponPrice(baoyou.getList().get(position).getCouponPrice() + "");
                    detail.setDesc(baoyou.getList().get(position).getDesc());
                    detail.setDtitle(baoyou.getList().get(position).getDtitle());
                    detail.setHotPush(baoyou.getList().get(position).getHotPush() + "");
                    detail.setMainPic(baoyou.getList().get(position).getMainPic());
                    detail.setMarketingMainPic(baoyou.getList().get(position).getMarketingMainPic());
                    detail.setMonthSales(baoyou.getList().get(position).getMonthSales() + "");
                    detail.setOriginalPrice(baoyou.getList().get(position).getOriginalPrice() + "");
                    detail.setShopType(baoyou.getList().get(position).getShopType() + "");
                    detail.setCouponLink(baoyou.getList().get(position).getCouponLink());
                    //传输自定义对象类型
                    context.startActivity(new Intent(context, DetailsActivityV2.class).putExtra("DetailV2", detail));
                }
            });
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount())
            return TYPE_FOOTER;  //最后一项则显示为脚布局
        else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return baoyou.getList().size()+1;
    }

    public void refresh(Baoyou9_9.DataBean baoyou9_9) {
        baoyou.list.addAll(baoyou9_9.getList());

        notifyDataSetChanged();
    }
    public void clean(){
        baoyou.list.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置点击监听器，外部
     *
     * @param listener
     */
    public void setOnclickListener(ItemOnclickListener listener) {
        this.onclickListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView comm_pic;
        TextView short_title;
        TextView sales_num;
        TextView org_Price;
        TextView price;
        Button quan_price;

        public ViewHolder(View itemView) {
            super(itemView);
            comm_pic = itemView.findViewById(R.id.comm_pic);
            short_title = itemView.findViewById(R.id.short_title);
            sales_num = itemView.findViewById(R.id.sales_num);
            org_Price = itemView.findViewById(R.id.org_Price);
            price = itemView.findViewById(R.id.price);
            quan_price = itemView.findViewById(R.id.quan_price);
        }
    }

    /**
     * 脚布局
     */
    class FootHolder extends RecyclerView.ViewHolder {


        public FootHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickListener.OnClick(view, getAdapterPosition());
                }
            });
        }
    }
}
