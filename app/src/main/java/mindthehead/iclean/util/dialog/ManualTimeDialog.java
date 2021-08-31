package mindthehead.iclean.util.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import mindthehead.iclean.R;
import mindthehead.iclean.util.DateManager;
import mindthehead.iclean.work.WorkActivity;


public class ManualTimeDialog extends Dialog implements Button.OnClickListener {


    public static final int MANUAL_TYPE_IN = 0;
    public static final int MANUAL_TYPE_OUT = 1;

    private ManualDialogListener listener;
    private final Button b_dismiss, b_manual;


    public ManualTimeDialog(WorkActivity _context, int type) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_manual_time);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_manual = findViewById(R.id.b_dialog_manual_time_manual);
        b_manual.setOnClickListener(this);
        b_dismiss = findViewById(R.id.b_dialog_manual_time_dismiss);
        b_dismiss.setOnClickListener(this);

        TextView tv_type = findViewById(R.id.tv_dialog_manual_time_type);
        TextView tv_time = findViewById(R.id.tv_dialog_manual_time);
        tv_time.setText(DateManager.getCurrentItalianMoment());
        if (type == MANUAL_TYPE_IN){
            tv_type.setText(R.string.manual_time_dialog_check_in);
        } else if (type == MANUAL_TYPE_OUT){
            tv_type.setText(R.string.manual_time_dialog_check_out);
        }



    }//CheckPasswordDialog


    public void setListener(ManualDialogListener _listener){

        listener = _listener;

    }//setListener

    private void saveData(){

        listener.onManualPick(DateManager.getCurrentMoment());
        dismiss();

    }//saveData


    public void onClick(View view) {

        if(view == b_manual) {

            if (listener != null) saveData();

        }else if(view == b_dismiss) {

            dismiss();

        }

    }//onClick



}//ForgotPswDialog