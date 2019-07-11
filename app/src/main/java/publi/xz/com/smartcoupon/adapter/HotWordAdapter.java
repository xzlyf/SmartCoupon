package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.HotWord;
import publi.xz.com.smartcoupon.ui.SearchActivity;

public class HotWordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<HotWord.DataBean> list;
    private Context context;
    public HotWordAdapter(Context context, List<HotWord.DataBean> list){
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot_word,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).hotWord.setText(list.get(position).getWord());
        ((ViewHolder) holder).hotWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                context.startActivity(new Intent(context, SearchActivity.class).putExtra("ant",list.get(position).getWord()));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView hotWord;
        public ViewHolder(View itemView) {
            super(itemView);
            hotWord = itemView.findViewById(R.id.hot_word);
        }
    }
}
