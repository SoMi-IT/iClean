package mindthehead.iclean.util.dialog;


import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Parcelable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.io.UnsupportedEncodingException;

import mindthehead.iclean.R;
import mindthehead.iclean.work.WorkActivity;
import mindthehead.iclean.work.WorkActivityListener;


public class NFCDialog extends Dialog implements Button.OnClickListener, WorkActivityListener {


    private WorkActivity activity;
    private NFCDialogListener listener;
    private Button b_manual, b_dismiss;

    public NFCDialog(WorkActivity _activity) {

        super(_activity);

        activity = _activity;

        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 20);

        this.getWindow().setBackgroundDrawable(inset);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.dialog_nfc);

        Window window = this.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        b_manual = findViewById(R.id.b_dialog_nfc_manual);
        b_manual.setOnClickListener(this);
        b_dismiss = findViewById(R.id.b_dialog_nfc_dismiss);
        b_dismiss.setOnClickListener(this);


        activity.setListener(this);

    }//CheckPasswordDialog


    protected void onStop() {

        super.onStop();
        activity.setListener(null);

    }//onStop

    public void setListener(NFCDialogListener _listener){

        listener = _listener;

    }//setListener


    public void onClick(View view) {

        if(view == b_manual) {

            if (listener != null) listener.onManual();
            activity.setListener(null);
            dismiss();

        }else if(view == b_dismiss) {

            activity.setListener(null);
            dismiss();

        }

    }//onClick


    public void onNFCTagFound(String id) {

        activity.setListener(null);
        listener.onNFCFind(id);
        dismiss();

    }//onNFCFind

}//ForgotPswDialog