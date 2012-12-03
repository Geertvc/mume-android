package com.moodspaces.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.moodspaces.R;

public class PeopleDialog extends DialogFragment {

    private String[] contacts;
    private Boolean[] selectedContacts;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_people_title);
//        builder.setMultiChoiceItems(getContacts().toArray(), getSelectedPeople().toArray(),
//                new DialogInterface.OnMultiChoiceClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
//                        // TODO Auto-generated method stub
//
//                    }
//                });
        builder.setPositiveButton(R.string.dialog_people_button_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // add the location
            }
        });
        builder.setNegativeButton(R.string.dialog_people_button_negative, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public String[] getContacts() {
        if (contacts == null) {
            initialize();
        }
        
        return contacts;
    }

    public Boolean[] getSelectedContacts() {
        if (selectedContacts == null) {
            initialize();
        }
        return selectedContacts;
    }
    
    private void initialize() {
    }
    
}
