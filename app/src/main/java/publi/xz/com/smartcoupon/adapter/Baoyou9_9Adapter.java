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
import publi.xz.com.smartcoupon.ui.DetailsActivityV2;

public class Baoyou9_9Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Baoyou9_9 baoyou;
    Context context;
    public Baoyou9_9Adapter(Context context,Baoyou9_9 baoyou9_9){
        this.baoyou = baoyou9_9;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commodity,null);
        return new Baoyou9_9Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Picasso
                .get()
                .load(baoyou.getData().getList().get(position).getMainPic())
                .fit()
                .into( ((ViewHolder) holder).comm_pic);
        ((ViewHolder) holder).short_title.setText(baoyou.getData().getList().get(position).getDtitle());
        ((ViewHolder) holder).sales_num.setText("月销"+baoyou.getData().getList().get(position).getMonthSales());
        ((ViewHolder) holder).org_Price.setText("原价"+baoyou.getData().getList().get(position).getOriginalPrice());
        ((ViewHolder) holder).price.setText("券后"+baoyou.getData().getList().get(position).getActualPrice());
        ((ViewHolder) holder).quan_price.setText("领"+baoyou.getData().getList().get(position).getCouponPrice()+"元券");
        ((ViewHolder) holder).quan_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //传输自定义对象类型
                context.startActivity(
                        new Intent(context, DetailsActivityV2.class)
                                .putExtra("BaoyouData",baoyou)
                                .putExtra("position",position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return baoyou.getData().getList().size();
    }
    class ViewHolder extends  RecyclerView.ViewHolder{
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
}
