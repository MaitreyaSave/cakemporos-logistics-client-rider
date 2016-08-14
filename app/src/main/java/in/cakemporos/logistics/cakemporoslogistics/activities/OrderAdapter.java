package in.cakemporos.logistics.cakemporoslogistics.activities;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import in.cakemporos.logistics.cakemporoslogistics.R;
import in.cakemporos.logistics.cakemporoslogistics.web.webmodels.entities.Order;

/**
 * Created by maitr on 31-Jul-16.
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Order[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView order_id_oh,booking_date_oh,pickup_date_oh,drop_date_oh,order_status_oh,customer_name_oh,phone_no_oh,pickup_locaion_oh,drop_location_oh;
        public ImageButton ok_oh,cancel_oh;
        public ViewHolder(View v) {
            super(v);
            order_id_oh=(TextView)v.findViewById(R.id.order_id_order_history);
            booking_date_oh=(TextView)v.findViewById(R.id.booking_date_oh);
            pickup_date_oh=(TextView)v.findViewById(R.id.pickup_date_oh);
            drop_date_oh=(TextView)v.findViewById(R.id.drop_date_oh);
            order_status_oh=(TextView)v.findViewById(R.id.order_status_oh);
            customer_name_oh=(TextView)v.findViewById(R.id.customer_name_oh);
            phone_no_oh=(TextView)v.findViewById(R.id.phone_no_oh);
            pickup_locaion_oh=(TextView)v.findViewById(R.id.pickup_location_oh);
            drop_location_oh=(TextView)v.findViewById(R.id.drop_location_oh);
            ok_oh= (ImageButton) v.findViewById(R.id.change_order_status_oh);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public OrderAdapter(Order[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_order_history, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String customer_name=mDataset[position].getCustomer().getFirstName()+" "+mDataset[position].getCustomer().getLastName();
        String customer_phone=mDataset[position].getCustomer().getPhone()+"";
        //Dates
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        //
        String date=sdf.format(mDataset[position].getBookingDate() );
        String booking_date="Booking Date\n"+date;
        //
        date=sdf.format(mDataset[position].getPickUpDate());
        String pickup_date="Pick Up Date\n"+date;
        //
        date=sdf.format(mDataset[position].getDropDate());
        String drop_date="Drop Date\n"+date;
        holder.order_id_oh.setText(mDataset[position].getOrderCode());
        holder.booking_date_oh.setText(booking_date);
        holder.pickup_date_oh.setText(pickup_date);
        holder.order_status_oh.setText(mDataset[position].getStatus().toString());
        holder.drop_date_oh.setText(drop_date);
        holder.customer_name_oh.setText(customer_name);
        holder.phone_no_oh.setText(customer_phone);
        holder.pickup_locaion_oh.setText(mDataset[position].getAddress());
        holder.drop_location_oh.setText(mDataset[position].getCustomer().getAddress());
        //
        holder.phone_no_oh.setTextColor(Color.parseColor("#845a9a"));
        //onclick
        if(!mDataset[position].getId().equals("PEN")){
            //holder.cancel_oh.setVisibility(View.INVISIBLE);
            //holder.ok_oh.setVisibility(View.INVISIBLE);
            if (mDataset[position].getId().equals("CAN")){
                holder.order_status_oh.setBackgroundColor(Color.RED);
            }
            else if (mDataset[position].getId().equals("DISP")){
                holder.order_status_oh.setBackgroundColor(Color.GREEN);
            }
            else if(mDataset[position].getId().equals("DEL")){
                holder.order_status_oh.setBackgroundColor(Color.BLUE);
            }
        }
        else {
            holder.cancel_oh.setVisibility(View.VISIBLE);
            holder.ok_oh.setVisibility(View.VISIBLE);
            holder.phone_no_oh.setTextColor(Color.YELLOW);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if(mDataset!=null)
        return mDataset.length;
        else return 0;
    }

    public void setmDataset(Order[] mDataset1)
    {
        this.mDataset=mDataset1;
    }
}