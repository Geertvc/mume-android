package com.moodspaces.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.moodspaces.InputActivity;
import com.moodspaces.R;

public class LocationDialog extends DialogFragment {

    private String locationName = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_location_title);
        builder.setIcon(android.R.drawable.ic_menu_compass);
        builder.setView(getEditText());
        builder.setPositiveButton(R.string.dialog_location_button_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // create the location
                ((InputActivity) getActivity()).createLocation(locationName);
            }
        });
        builder.setNegativeButton(R.string.dialog_location_button_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled

            }
        });

        return builder.create();
    }

    private View getEditText() {
        EditText text = new EditText(getActivity());
        text.setPadding(10, 10, 10, 10);
        text.setHint(R.string.dialog_location_input_hint);
        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                locationName = s.toString();
            }
        });
        return text;
    }
}
