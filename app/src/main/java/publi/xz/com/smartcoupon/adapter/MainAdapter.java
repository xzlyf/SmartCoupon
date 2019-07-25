package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.DetailV2;
import publi.xz.com.smartcoupon.entity.MainCNXH;
import publi.xz.com.smartcoupon.ui.DetailsActivityV2;
import publi.xz.com.smartcoupon.utils.ItemOnclickListener;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private MainCNXH.DataBean cnxh = null;
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    private ItemOnclickListener onclickListener;

    public MainAdapter(Context context) {
        this.context = context;
        cnxh = new MainCNXH.DataBean();
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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof ViewHolder) {
            Picasso
                    .get()
                    .load(cnxh.getList().get(position).getMainPic())
                    .fit()
                    .into(((MainAdapter.ViewHolder) holder).comm_pic);
            ((MainAdapter.ViewHolder) holder).short_title.setText(cnxh.getList().get(position).getDtitle());
            ((MainAdapter.ViewHolder) holder).sales_num.setText("月销" + cnxh.getList().get(position).getMonthSales());
            ((MainAdapter.ViewHolder) holder).org_Price.setText("原价" + cnxh.getList().get(position).getOriginalPrice());
            ((MainAdapter.ViewHolder) holder).price.setText("券后" + cnxh.getList().get(position).getActualPrice());
            ((MainAdapter.ViewHolder) holder).quan_price.setText("领" + cnxh.getList().get(position).getCouponPrice() + "元券");
            ((MainAdapter.ViewHolder) holder).quan_price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //传输自定义对象类型
                    DetailV2 detail = new DetailV2();
                    detail.setActualPrice((cnxh.getList().get(position).getActualPrice() + ""));
                    detail.setCouponEndTime(cnxh.getList().get(position).getCouponEndTime());
                    detail.setCouponPrice(cnxh.getList().get(position).getCouponPrice() + "");
                    detail.setDesc(cnxh.getList().get(position).getDesc());
                    detail.setDtitle(cnxh.getList().get(position).getDtitle());
                    detail.setHotPush(cnxh.getList().get(position).getHotPush() + "");
                    detail.setMainPic(cnxh.getList().get(position).getMainPic());
                    detail.setMarketingMainPic(cnxh.getList().get(position).getMarketingMainPic());
                    detail.setMonthSales(cnxh.getList().get(position).getMonthSales() + "");
                    detail.setOriginalPrice(cnxh.getList().get(position).getOriginalPrice() + "");
                    detail.setShopType(cnxh.getList().get(position).getShopType() + "");
                    detail.setCouponLink(cnxh.getList().get(position).getCouponLink());
                    //传输自定义对象类型
                    context.startActivity(new Intent(context, DetailsActivityV2.class).putExtra("DetailV2", detail));

                }


            });
        }
    }

    @Override
    public int getItemCount() {
        return cnxh.getList().size() + 1;//返回数据项+1（加上脚布局）
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount())
            return TYPE_FOOTER;  //最后一项则显示为脚布局
        else {
            return TYPE_ITEM;
        }
    }

    public void refresh(MainCNXH.DataBean fromJson) {
//        cnxh = fromJson;
        cnxh.list.addAll(fromJson.getList());

        notifyDataSetChanged();
    }

    public void cleanList() {
        cnxh.list.clear();
        notifyDataSetChanged();
    }

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
