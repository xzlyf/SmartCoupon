package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.PPBrand;
import publi.xz.com.smartcoupon.ui.SearchActivity;

public class PPAdapter extends RecyclerView.Adapter<PPAdapter.ViewHolder>{
    private Context context;
    private PPBrand brand;
    public PPAdapter(Context context){
        this.context = context;
        brand = new PPBrand();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pp_brand,parent,false));//这样写解决item的宽不能全屏
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(brand.getData().get(position).getBrandLogo()).into(holder.brandLogo);
        holder.brandName.setText(brand.getData().get(position).getBrandName());
        holder.consumer.setText("定位："+brand.getData().get(position).getConsumer());
    }


    @Override
    public int getItemCount() {
        return brand.getData().size();
    }
    public void refresh(PPBrand newBrand){
        brand.add(newBrand);
        notifyDataSetChanged();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView brandLogo;
        private TextView brandName;
        private TextView consumer;


        public ViewHolder(View itemView) {
            super(itemView);
            brandLogo = itemView.findViewById(R.id.brandLogo);
            brandName = itemView.findViewById(R.id.brandName);
            consumer = itemView.findViewById(R.id.consumer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, SearchActivity.class).putExtra("ant",brandName.getText()));
                }
            });
        }
    }
}
