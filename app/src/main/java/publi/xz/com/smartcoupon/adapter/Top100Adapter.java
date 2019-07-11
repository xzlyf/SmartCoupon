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

import com.orhanobut.logger.Logger;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.Popular;
import publi.xz.com.smartcoupon.ui.DetailsActivity;
import publi.xz.com.smartcoupon.ui.Top100Activity;

public class Top100Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  List<Popular.ResultBean> popular;
    private final Context mContext;

    public Top100Adapter(List<Popular.ResultBean> popular, Context context) {
        this.popular = popular;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_commodity, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
//         Picasso 缓存目录就在 /data/data/<application package>/cache/picasso-cache/下边
        if (holder instanceof ViewHolder) {
            Picasso.get()
                    .load(popular.get(position).getPic())
                    .fit()
//                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(((ViewHolder) holder).comm_pic);
            ((ViewHolder) holder).short_title.setText(popular.get(position).getD_title());
            ((ViewHolder) holder).sales_num.setText("销量：" + popular.get(position).getSales_num());
            ((ViewHolder) holder).org_Price.setText("原价：" + popular.get(position).getOrg_Price());
            ((ViewHolder) holder).price.setText("券后：" + popular.get(position).getPrice() + "");
            ((ViewHolder) holder).quan_price.setText("领" + popular.get(position).getQuan_price() + "元券");
            ((ViewHolder) holder).quan_price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(
                            new Intent(mContext, DetailsActivity.class)
                                    .putExtra("id",popular.get(position).getID()));
                }
            });
        }
    }


    @Override
    public int getItemCount() {
//        return popular.getResult().size();
        return popular.size();
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
     * 刷新数据
     * @param total
     */
    public void refresh(List<Popular.ResultBean> total){
        popular = total;
        notifyDataSetChanged();
    }
}
