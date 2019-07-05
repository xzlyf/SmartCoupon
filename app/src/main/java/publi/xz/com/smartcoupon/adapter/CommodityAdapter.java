package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.Popular;

public class CommodityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Popular popular;
    private final Context mContext;

    public CommodityAdapter(Popular popular, Context context){
        this.popular = popular;
        this.mContext = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.item_commodity,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolder){

            ((ViewHolder) holder).short_title.setText(popular.getResult().get(position).getD_title());
            ((ViewHolder) holder).sales_num.setText("销量："+popular.getResult().get(position).getSales_num());
            ((ViewHolder) holder).org_Price.setText("原价："+popular.getResult().get(position).getOrg_Price());
            ((ViewHolder) holder).price.setText("券后："+popular.getResult().get(position).getPrice()+"");
            ((ViewHolder) holder).quan_price.setText("领"+popular.getResult().get(position).getQuan_price()+"元券");
        }
    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
