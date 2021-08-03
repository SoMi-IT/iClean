package mindthehead.iclean.util;


import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;


public class NFCManager {


    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    private static String TAG = "XXX";


    public static String obtainTagFromIntent(Intent intent) {

        String action = intent.getAction();

        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            assert tag != null;

            StringBuilder sb = new StringBuilder();
            byte[] id = tag.getId();

            for (String tech : tag.getTechList()) {

                if (tech.equals(MifareUltralight.class.getName())) {

                    MifareUltralight mifareUlTag = MifareUltralight.get(tag);
                    return readTag(mifareUlTag);

                }

            }

        }

        return "";

    }

    public static String readTag(MifareUltralight mifareUlTag) {

        try {

            mifareUlTag.connect();
            byte[] payload = mifareUlTag.readPages(4);

            return new String(payload, Charset.forName("US-ASCII"));

        } catch (IOException e) {

            Log.d(TAG, "IOException while reading MifareUltralight message...", e);

        } finally {

            if (mifareUlTag != null) {

                try {
                    mifareUlTag.close();

                } catch (IOException e) {
                    Log.d(TAG, "Error closing tag...", e);
                }

            }

        }

        return null;

    }//readTag

}//NFCManager
