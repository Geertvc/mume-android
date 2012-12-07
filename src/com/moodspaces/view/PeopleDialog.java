package com.moodspaces.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;

import com.moodspaces.InputActivity;
import com.moodspaces.R;

public class PeopleDialog extends DialogFragment {

    private String[] contacts;
    private boolean[] selectedContacts;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_people_title);

        builder.setMultiChoiceItems(getContacts(), getSelectedContacts(),
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        getSelectedContacts()[which] = isChecked;
                    }
                });
        builder.setPositiveButton(R.string.dialog_people_button_positive, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // add the person
                ((InputActivity) getActivity()).setMoodPeeps(getSelectedMoodPeeps());
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

    private String[] getContacts() {
        if (contacts == null) {
            List<String> people = new ArrayList<String>();
            Cursor cursor = getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                    new String[] { ContactsContract.Contacts.DISPLAY_NAME }, null, null, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                people.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
            contacts = people.toArray(new String[] {});
            Arrays.sort(contacts);
        }

        return contacts;

    }

    public boolean[] getSelectedContacts() {
        if (selectedContacts == null) {
            selectedContacts = new boolean[getContacts().length];
            Arrays.fill(selectedContacts, false);
        }
        return selectedContacts;
    }

    public List<String> getSelectedMoodPeeps() {
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < getSelectedContacts().length; i++) {
            if (getSelectedContacts()[i]) {
                result.add(getContacts()[i]);
            }
        }
        return result;
    }
}
