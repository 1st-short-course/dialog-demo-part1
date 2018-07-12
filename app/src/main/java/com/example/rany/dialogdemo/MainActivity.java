package com.example.rany.dialogdemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnProgress, btnSimpleDialog, btnListDialog;
    private String[] model;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find reference
        initView();

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
            }
        });

        btnSimpleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSimpleDialog();
            }
        });

        btnListDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListDialog();
            }
        });
    }

    private void showListDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.s_title)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_background)
                .setItems(model, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        tvResult.setText(model[position]);
                    }
                })
                .show();

    }

    private void showSimpleDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.s_title)
                .setMessage("Welcome to Simple Dialog")
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_background)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Ok Click", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Deny Click", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Cancel Click", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();

    }

    private void showProgressDialog() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(R.string.p_title);
        dialog.setMessage("Downloading ...");
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // set button to progress dialog
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress = 0;
               while (dialog.getProgress() < dialog.getMax()){
                   try {
                       Thread.sleep(50);
                       progress += 1;
                       dialog.setProgress(progress);
//                        Dismiss dialog when downloading success
//                       if(dialog.getProgress() == dialog.getMax()){
//                           dialog.dismiss();
//                       }
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
            }
        }).start();
    }

    private void initView() {
        btnProgress = findViewById(R.id.btnProgress);
        btnSimpleDialog = findViewById(R.id.btnSimpleDialog);
        btnListDialog = findViewById(R.id.btnList);
        tvResult = findViewById(R.id.tvResult);

        // initialize value
        model = getResources().getStringArray(R.array.model);

    }
}
