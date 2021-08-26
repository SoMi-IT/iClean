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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;


public class InfoDialog extends Dialog implements Button.OnClickListener {


    private final Button b_dismiss;


    public InfoDialog(WorkActivity _context, String info, String site, String floor, String department) {

        super(_context);

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_info);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_dismiss = findViewById(R.id.b_dialog_info_dismiss);
        b_dismiss.setOnClickListener(this);

        ImageView iv_icon;
        TextView tv_info, tv_siteLabel, tv_site, tv_floorLabel, tv_floor, tv_departmentLabel, tv_department;

        iv_icon = findViewById(R.id.iv_dialog_info);
        tv_info = findViewById(R.id.tv_dialog_info);
        tv_siteLabel = findViewById(R.id.tv_dialog_info_site_label);
        tv_site = findViewById(R.id.tv_dialog_info_site);
        tv_floorLabel = findViewById(R.id.tv_dialog_info_floor_label);
        tv_floor = findViewById(R.id.tv_dialog_info_floor);
        tv_departmentLabel = findViewById(R.id.tv_dialog_info_department_label);
        tv_department = findViewById(R.id.tv_dialog_info_department);

        if (info.equals("")) {

            iv_icon.setImageDrawable(ContextCompat.getDrawable(_context, R.drawable.icon_location));

            tv_info.setVisibility(View.GONE);

            tv_site.setText(site);

            if(floor.equals("")) {
                tv_floorLabel.setVisibility(View.GONE);
                tv_floor.setVisibility(View.GONE);

            }else {
                tv_floor.setText(floor);
            }

            if(department.equals("")) {
                tv_departmentLabel.setVisibility(View.GONE);
                tv_department.setVisibility(View.GONE);

            }else {
                tv_department.setText(department);
            }

        } else {

            iv_icon.setImageDrawable(ContextCompat.getDrawable(_context, R.drawable.icon_info));

            tv_info.setText(info);

            tv_siteLabel.setVisibility(View.GONE);
            tv_site.setVisibility(View.GONE);
            tv_floorLabel.setVisibility(View.GONE);
            tv_floor.setVisibility(View.GONE);
            tv_departmentLabel.setVisibility(View.GONE);
            tv_department.setVisibility(View.GONE);
        }

    }//CheckPasswordDialog



    public void onClick(View view) {

        if(view == b_dismiss) {

            dismiss();

        }

    }//onClick

}//InfoDialog