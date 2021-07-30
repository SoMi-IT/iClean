package mindthehead.iclean.work.shedules;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import mindthehead.iclean.R;


public class SchedulesListAdapter extends RecyclerView.Adapter<SchedulesListAdapter.SchedulesListAdapterItemView> {


    private ArrayList<Schedule> schedules;
    private Context context;
    private SchedulesListListener schedulesListListener;


    public SchedulesListAdapter(Context _context, ArrayList<Schedule> _schedules) {

        schedules = _schedules;
        context = _context;

    }//sheetsListAdapter constructor


    public void setListener(SchedulesListListener _schedulesListListener){

        schedulesListListener = _schedulesListListener;

    }//setListener


    public SchedulesListAdapterItemView onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);

        SchedulesListAdapterItemView viewHolder = new SchedulesListAdapterItemView(v);

        return viewHolder;

    }//onCreateViewHolder


    public void onBindViewHolder(SchedulesListAdapterItemView holder, int position) {

        holder.itemView.setTag(schedules.get(position));

        Schedule schedule = schedules.get(position);

        holder.tv_date.setText(schedule.getDate());
        holder.tv_time.setText(schedule.getTimeStart() + " - " + schedule.getTimeEnd());

        holder.fb_info.setOnClickListener(v -> {

            if (schedulesListListener != null) schedulesListListener.onItemInfoClicked(schedules.get(holder.getAdapterPosition()));

        });

    }//onBindViewHolder




    public class SchedulesListAdapterItemView extends RecyclerView.ViewHolder {

        TextView tv_date;
        TextView tv_time;
        ImageView fb_info;

        SchedulesListAdapterItemView(View itemView) {

            super(itemView);
            tv_date = itemView.findViewById(R.id.tv_schedule_item_date);
            tv_time = itemView.findViewById(R.id.tv_schedule_item_time);
            fb_info = itemView.findViewById(R.id.fb_schedule_item_info);

        }


    }//SheetsListAdapterItemView



    public int getItemCount() {

        return schedules.size();

    }//getItemCount


}//sheetsListAdapter
