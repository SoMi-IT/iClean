package mindthehead.iclean.work.task.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class TaskItemEndDialog extends Dialog implements Button.OnClickListener {


    private TaskItemEndDialogListener listener;
    private Button b_manual, b_dismiss;


    public TaskItemEndDialog(WorkActivity _context) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_task_item_end);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_manual = findViewById(R.id.b_dialog_task_item_end_manual);
        b_manual.setOnClickListener(this);
        b_dismiss = findViewById(R.id.b_dialog_task_item_end_dismiss);
        b_dismiss.setOnClickListener(this);

    }//CheckPasswordDialog


    public void setListener(TaskItemEndDialogListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if(view == b_manual) {

            if (listener != null) listener.onEndDid();

        }else if(view == b_dismiss) {

            dismiss();

        }

    }//onClick



}//ForgotPswDialog