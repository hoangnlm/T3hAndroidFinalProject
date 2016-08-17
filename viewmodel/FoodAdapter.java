package vn.t3h.project1.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.model.Food;
import vn.t3h.project1.view.InputActivity;

/**
 * Created by Hoang on 8/10/16.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private List<Food> foods;
    private Context context;
    private int tableId;

    public FoodAdapter(Context context, List<Food> foods, int tableId){
        this.context = context;
        this.foods = foods;
        this.tableId = tableId;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        return new FoodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        holder.ivImage.setImageResource(R.drawable.food01+position);
        holder.tvName.setText(String.format("%02d. %s", foods.get(position).getId(), foods.get(position).getName()));
        NumberFormat formatter = new DecimalFormat("#,###đ");
        holder.tvPrice.setText("Giá: " + formatter.format(foods.get(position).getPrice()));

        // Diem danh gia, lay rate chia 2 lam tron roi xuat hinh ngoi sao ra (toi da 5 sao)

        // Gan thong so cho view holder de chuyen activity
        holder.context = context;
        holder.tableId = tableId;
        holder.foodId = foods.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        ImageView ivImage;
        TextView tvName;
        TextView tvPrice;
        LinearLayout vRate;
        Context context;
        int tableId;
        int foodId;

        public FoodViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView;
            ivImage = (ImageView) cv.findViewById(R.id.ivImage);
            tvName = (TextView) cv.findViewById(R.id.tvName);
            tvPrice = (TextView) cv.findViewById(R.id.tv1);
            vRate = (LinearLayout) cv.findViewById(R.id.vRate);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, InputActivity.class);
            intent.putExtra("tableId", tableId);
            intent.putExtra("foodId", foodId);
            context.startActivity(intent);
        }
    }
}
