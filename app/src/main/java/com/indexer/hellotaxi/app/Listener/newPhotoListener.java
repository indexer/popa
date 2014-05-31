package com.indexer.hellotaxi.app.Listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

public class newPhotoListener implements View.OnClickListener {
    Activity mActivity;

    public newPhotoListener(Activity activity) {
        mActivity = activity;

    }

    @Override
    public void onClick(View v) {

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(mActivity);
        builderSingle.setIcon(android.R.drawable.ic_menu_camera);
        builderSingle.setTitle("Select Source");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1);
        arrayAdapter.add("Capture via Camera");
        arrayAdapter.add("Gallery");
        builderSingle.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
        //To Do add Photo Listener
        builderSingle.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            mActivity.startActivityForResult(cameraIntent, 0);

                        } else if (which == 1) {
                            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                            photoPickerIntent.setType("image/*");
                            mActivity.startActivityForResult(photoPickerIntent, 1);
                        }
                        dialog.dismiss();
                    }
                }
        );
        builderSingle.show();
    }


}
