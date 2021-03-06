package mindthehead.iclean.work.task.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import mindthehead.iclean.R;
import mindthehead.iclean.work.task.data.Task;


public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.TasksListAdapterItemView> {


    private ArrayList<Task> tasks;
    private final Context context;
    private TasksListAdapterListener listener;


    public TasksListAdapter(Context _context, ArrayList<Task> _schedules) {

        tasks = _schedules;
        context = _context;

    }//constructor

    public void updateTasks(ArrayList<Task> _schedules, int donePosition, int currentPosition, int newPosition) {


        tasks = _schedules;
        notifyItemChanged(donePosition);
        notifyItemChanged(currentPosition);
        notifyItemChanged(newPosition);

    }//constructor


    public void setListener(TasksListAdapterListener _tasksListAdapterListener){

        listener = _tasksListAdapterListener;

    }//setListener


    @NonNull
    public TasksListAdapterItemView onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);

        return new TasksListAdapterItemView(v);

    }//onCreateViewHolder


    public void onBindViewHolder(TasksListAdapterItemView holder, int position) {

        holder.itemView.setTag(tasks.get(position));

        Task task = tasks.get(position);


        holder.tv_date.setText(task.getDate());
        holder.tv_time.setText(task.getTimeStart() + " - " + task.getTimeEnd());
        holder.tv_task.setText(task.getId()+"");

        holder.iv_info.setOnClickListener(v -> {

            if (listener != null) listener.onItemInfoClicked(tasks.get(holder.getAdapterPosition()));

        });

        holder.iv_location.setOnClickListener(v -> {

            if (listener != null) listener.onItemLocationClicked(tasks.get(holder.getAdapterPosition()));

        });


        if (task.getStatus() == Task.STATUS_CURRENT_NOT_STARTED) {

            holder.ll_expand.setVisibility(View.VISIBLE);
            holder.v_divider.setVisibility(View.VISIBLE);
            holder.cv_card.setCardBackgroundColor(context.getColor(R.color.pink_700));

            toggleButtonState(holder.b_start, true);
            toggleButtonState(holder.b_end, false);

            holder.b_start.setOnClickListener(v -> {

                if (listener != null) listener.onItemStartClicked(tasks.get(holder.getAdapterPosition()));

            });

        }else if (task.getStatus() == Task.STATUS_CURRENT_STARTED) {

            holder.ll_expand.setVisibility(View.VISIBLE);
            holder.v_divider.setVisibility(View.VISIBLE);
            holder.cv_card.setCardBackgroundColor(context.getColor(R.color.pink_700));

            toggleButtonState(holder.b_start, false);
            toggleButtonState(holder.b_end, true);

            holder.b_end.setOnClickListener(v -> {

                if (listener != null) listener.onItemEndClicked(tasks.get(holder.getAdapterPosition()));

            });

        }else if (task.getStatus() == Task.STATUS_DONE) {

            holder.ll_expand.setVisibility(View.GONE);
            holder.v_divider.setVisibility(View.GONE);
            holder.cv_card.setCardBackgroundColor(context.getColor(R.color.dark_800));

        }else if (task.getStatus() == Task.STATUS_TODO) {

            holder.ll_expand.setVisibility(View.GONE);
            holder.v_divider.setVisibility(View.GONE);
            holder.cv_card.setCardBackgroundColor(context.getColor(R.color.pink_800));

        }

    }//onBindViewHolder


    private void toggleButtonState(Button b, boolean isEnable) {

        Drawable buttonDrawable = b.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);

        if (isEnable) {

            DrawableCompat.setTint(buttonDrawable, context.getColor(R.color.white_100));
            b.setBackground(buttonDrawable);
            b.setClickable(true);

        } else {

            DrawableCompat.setTint(buttonDrawable, context.getColor(R.color.light_transparent));
            b.setBackground(buttonDrawable);
            b.setClickable(false);

        }

    }//toggleButtonState

    public static class TasksListAdapterItemView extends RecyclerView.ViewHolder {

        LinearLayout ll_expand;
        View v_divider;
        CardView cv_card;
        TextView tv_task;
        TextView tv_date;
        TextView tv_time;
        ImageView iv_info;
        ImageView iv_location;
        Button b_start;
        Button b_end;

        TasksListAdapterItemView(View itemView) {

            super(itemView);
            ll_expand = itemView.findViewById(R.id.ll_task_item_expand);
            v_divider = itemView.findViewById(R.id.v_task_item_divider);
            cv_card = itemView.findViewById(R.id.cv_task_item);
            tv_task = itemView.findViewById(R.id.tv_task_item_info);
            tv_date = itemView.findViewById(R.id.tv_task_item_date);
            tv_time = itemView.findViewById(R.id.tv_task_item_time);
            iv_info = itemView.findViewById(R.id.iv_task_item_info);
            iv_location = itemView.findViewById(R.id.iv_task_item_location);
            b_start = itemView.findViewById(R.id.b_task_item_start);
            b_end = itemView.findViewById(R.id.b_task_item_end);

        }

    }//SheetsListAdapterItemView


    public int getItemCount() {

        return tasks.size();

    }//getItemCount


}//sheetsListAdapter
