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


public class WarningDialog extends Dialog implements Button.OnClickListener {


    private final Button b_dismiss;


    public WarningDialog(Activity _context, String description) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_warning);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_dismiss = findViewById(R.id.b_dialog_warning_dismiss);
        b_dismiss.setOnClickListener(this);

        TextView tv_info;

        tv_info = findViewById(R.id.tv_dialog_warning_info);
        tv_info.setText(description);

    }//CheckPasswordDialog



    public void onClick(View view) {

        if(view == b_dismiss) {

            dismiss();

        }

    }//onClick



}//WarningDialog