package mindthehead.iclean.work.shedules.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class ScheduleItemInfoDialog extends Dialog implements Button.OnClickListener {


    private Button b_dismiss;


    public ScheduleItemInfoDialog(WorkActivity _context, String site) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_schedule_item_info);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_dismiss = findViewById(R.id.b_dialog_schedule_item_info_dismiss);
        b_dismiss.setOnClickListener(this);

        TextView tv_site;

        tv_site = findViewById(R.id.tv_dialog_schedule_item_info_site);
        tv_site.setText(site);

    }//CheckPasswordDialog



    public void onClick(View view) {

        if(view == b_dismiss) {

            dismiss();

        }

    }//onClick



}//ForgotPswDialog