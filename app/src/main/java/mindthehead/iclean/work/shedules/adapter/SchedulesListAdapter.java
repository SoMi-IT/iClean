package mindthehead.iclean.work.shedules.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mindthehead.iclean.R;
import mindthehead.iclean.work.shedules.data.Schedule;


public class SchedulesListAdapter extends RecyclerView.Adapter<SchedulesListAdapter.SchedulesListAdapterItemView> {


    private final ArrayList<Schedule> schedules;
    private SchedulesListAdapterListener schedulesListAdapterListener;


    public SchedulesListAdapter(Context _context, ArrayList<Schedule> _schedules) {

        schedules = _schedules;

    }//sheetsListAdapter constructor


    public void setListener(SchedulesListAdapterListener _schedulesListAdapterListener){

        schedulesListAdapterListener = _schedulesListAdapterListener;

    }//setListener


    @NonNull
    public SchedulesListAdapterItemView onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule, parent, false);


        return new SchedulesListAdapterItemView(v);

    }//onCreateViewHolder


    public void onBindViewHolder(SchedulesListAdapterItemView holder, int position) {

        holder.itemView.setTag(schedules.get(position));

        Schedule schedule = schedules.get(position);

        holder.tv_date.setText(schedule.getDate());
        holder.tv_time.setText(schedule.getTimeStart() + " - " + schedule.getTimeEnd());

        holder.fb_info.setOnClickListener(v -> {

            if (schedulesListAdapterListener != null) schedulesListAdapterListener.onItemInfoClicked(schedules.get(holder.getAdapterPosition()));

        });

    }//onBindViewHolder


    public static class SchedulesListAdapterItemView extends RecyclerView.ViewHolder {

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
