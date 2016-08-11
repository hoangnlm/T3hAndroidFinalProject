package vn.t3h.project1.viewmodel;

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
import vn.t3h.project1.model.MyTable;

/**
 * Created by Hoang on 8/10/16.
 */

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder> {
    private List<MyTable> myTables;

    public TableAdapter(List<MyTable> myTables){
        this.myTables = myTables;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_layout, parent, false);
        return new TableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        holder.ivImage.setImageResource(R.drawable.table2);
        holder.tvNumber.setText(String.format("Số thứ tự: %02d", myTables.get(position).getId()));
        holder.tvStatus.setText(myTables.get(position).getStatus()==0?"Trạng thái: đang trống":"Trạng thái: đang đặt");
        NumberFormat formatter = new DecimalFormat("#,###đ");
        holder.tvPrice.setText("Tổng tiền: " + formatter.format(myTables.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return myTables.size();
    }

    public static class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private static final String TAG = "TableViewHolder";
        CardView cv;
        ImageView ivImage;
        TextView tvNumber;
        TextView tvStatus;
        TextView tvPrice;

        public TableViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView;
            ivImage = (ImageView) cv.findViewById(R.id.ivTable);
            tvNumber = (TextView) cv.findViewById(R.id.tvNumber);
            tvStatus = (TextView) cv.findViewById(R.id.tvStatus);
            tvPrice = (TextView) cv.findViewById(R.id.tvPrice);
            cv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "You click on number: " + tvNumber.getText());
        }
    }
}
