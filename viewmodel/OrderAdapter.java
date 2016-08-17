package vn.t3h.project1.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import vn.t3h.project1.R;
import vn.t3h.project1.model.Order;

/**
 * Created by Hoang on 8/11/16.
 */

public class OrderAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;

    public OrderAdapter(Context context, List<Order> orders) {
        super(context, R.layout.order_item_layout, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
            viewHolder.tvQty = (TextView) convertView.findViewById(R.id.tvQty);
            viewHolder.tvSum = (TextView) convertView.findViewById(R.id.tvSum);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Order order = orders.get(position);
        NumberFormat formatter = new DecimalFormat("#,###đ");
        if (order.getFoodId() != 0){ // Order mon an
            viewHolder.tvName.setText((position+1) + ". " + order.getFoodName());
            viewHolder.tvPrice.setText(formatter.format(order.getFoodPrice()));
            viewHolder.tvQty.setText(order.getFoodQty()+"");
            viewHolder.tvSum.setText(formatter.format(order.getFoodSum()));
        } else { // Order mon uong
            viewHolder.tvName.setText((position+1) + ". " + order.getDrinkName());
            viewHolder.tvPrice.setText(formatter.format(order.getDrinkPrice()));
            viewHolder.tvQty.setText(order.getDrinkQty()+"");
            viewHolder.tvSum.setText(formatter.format(order.getDrinkSum()));
        }
        return convertView;
    }

    private class ViewHolder{
        TextView tvName, tvPrice, tvQty, tvSum;
    }
}
