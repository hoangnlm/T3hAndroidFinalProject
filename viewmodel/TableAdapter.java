package vn.t3h.project1.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.model.Table;
import vn.t3h.project1.view.OrderActivity;
import vn.t3h.project1.view.TableActivity;

/**
 * Created by Hoang on 8/10/16.
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<Table> tables;
    private Context context;

    public TableAdapter(Context context, List<Table> tables){
        this.context = context;
        this.tables = tables;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_layout, parent, false);
        return new TableViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        holder.ivImage.setImageResource(R.drawable.table2);
        holder.tvNumber.setText(String.format("Số thứ tự: %02d", tables.get(position).getId()));
        holder.tvStatus.setText(tables.get(position).getStatus()==0?"Trạng thái: trống":"Trạng thái: đang đặt");
        NumberFormat formatter = new DecimalFormat("#,###đ");
        holder.tvPrice.setText("Tổng tiền: " + formatter.format(tables.get(position).getPrice()));

        // Ghi nho id cua table
        holder.tableId = tables.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        ImageView ivImage;
        TextView tvNumber;
        TextView tvStatus;
        TextView tvPrice;
        Context context;
        int tableId;

        public TableViewHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            cv = (CardView) itemView;
            ivImage = (ImageView) cv.findViewById(R.id.ivTable);
            tvNumber = (TextView) cv.findViewById(R.id.tvNumber);
            tvStatus = (TextView) cv.findViewById(R.id.tvStatus);
            tvPrice = (TextView) cv.findViewById(R.id.tv1);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, OrderActivity.class);
            intent.putExtra("tableId", tableId);
            context.startActivity(intent);
        }
    }
}
