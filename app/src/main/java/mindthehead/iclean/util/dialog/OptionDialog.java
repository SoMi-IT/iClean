package mindthehead.iclean.util.dialog;


import android.app.Activity;
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
import mindthehead.iclean.auth.AuthFragmentListener;


public class OptionDialog extends Dialog implements Button.OnClickListener {


    private Button b_yes, b_not;
    private OptionDialogListener listener;

    public OptionDialog(Activity _context, String description, String yes, String not) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_option);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_yes = findViewById(R.id.b_dialog_option_yes);
        b_yes.setText(yes);
        b_yes.setOnClickListener(this);
        b_not = findViewById(R.id.b_dialog_option_not);
        b_not.setText(not);
        b_not.setOnClickListener(this);

        TextView tv_info;

        tv_info = findViewById(R.id.tv_dialog_option_info);
        tv_info.setText(description);

    }//CheckPasswordDialog

    public void setListener(OptionDialogListener _listener){

        listener = _listener;

    }//setListener

    public void onClick(View view) {

        if(view == b_yes) {
            listener.onYesChoice();
            dismiss();
        } else if(view == b_not) {
            listener.onNotChoice();
            dismiss();
        }

    }//onClick



}//WarningDialog