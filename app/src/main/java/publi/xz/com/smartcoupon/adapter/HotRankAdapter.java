package publi.xz.com.smartcoupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import publi.xz.com.smartcoupon.R;
import publi.xz.com.smartcoupon.entity.HotWord;

public class HotRankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private HotWord hotWord;

    public HotRankAdapter(Context context,HotWord hotWord){
        this.context = context;
        this.hotWord = hotWord;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_hot_rank,null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).rank.setText("第"+hotWord.getData().get(position).getRank()+"名");
            ((ViewHolder) holder).changeNum.setText(hotWord.getData().get(position).getChange());
            ((ViewHolder) holder).word.setText(hotWord.getData().get(position).getWord());
            ((ViewHolder) holder).total.setText("搜索量："+hotWord.getData().get(position).getTotal());
        }
    }

    @Override
    public int getItemCount() {
        return hotWord.getData().size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
         TextView rank;
         ImageView change;
         TextView changeNum;
         TextView word;
         TextView total;



        public ViewHolder(View itemView) {
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            change = itemView.findViewById(R.id.change);
            changeNum = itemView.findViewById(R.id.change_num);
            word = itemView.findViewById(R.id.word);
            total = itemView.findViewById(R.id.total);
        }
    }
}
