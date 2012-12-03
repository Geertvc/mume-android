package com.moodspaces.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.moodspaces.R;

public class ActivityDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_activity_title);
        //builder.setMessage(R.string.dialog_activity_message);
        // builder.setMultiChoiceItems(items, checkedItems, listener);
        builder.setPositiveButton(R.string.dialog_activity_button_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // add the location
            }
        });
        builder.setNegativeButton(R.string.dialog_activity_button_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
