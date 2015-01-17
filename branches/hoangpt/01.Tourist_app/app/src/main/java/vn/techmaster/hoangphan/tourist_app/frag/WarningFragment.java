package vn.techmaster.hoangphan.tourist_app.frag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import vn.techmaster.hoangphan.tourist_app.act.MainActivity;

/**
 * Created by hoangpt on 1/16/15.
 */
public class WarningFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MainActivity parent = (MainActivity) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);

        builder.setTitle("Warning").setMessage("Do not have internet connection, use offline mode");
        builder.setIcon(android.R.drawable.ic_delete);

        return builder.create();
    }
}
