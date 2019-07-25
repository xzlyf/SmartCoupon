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
import publi.xz.com.smartcoupon.utils.ItemOnclickListener;

public class PPAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    private Context context;
    private PPBrand brand;
    private ItemOnclickListener onclickListener;

    public PPAdapter(Context context) {
        this.context = context;
        brand = new PPBrand();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pp_brand, parent, false));//指定根部据

        } else if (viewType == TYPE_FOOTER) {
            return new FootHolder(LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Picasso.get().load(brand.getData().get(position).getBrandLogo()).into(((ViewHolder) holder).brandLogo);
            ((ViewHolder) holder).brandName.setText(brand.getData().get(position).getBrandName());
            ((ViewHolder) holder).consumer.setText("定位：" + brand.getData().get(position).getConsumer());
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
        return brand.getData().size() + 1;//返回数据项+1（加上脚布局）
    }

    public void refresh(PPBrand newBrand) {
        brand.add(newBrand);
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
                    context.startActivity(new Intent(context, SearchActivity.class).putExtra("ant", brandName.getText()));
                }
            });
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
