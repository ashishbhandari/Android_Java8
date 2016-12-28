package com.dataorganizer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dataorganizer.data.DataManager;
import com.dataorganizer.model.VanStock;
import com.dataorganizer.persistence.DataOrganizerDatabaseHelper;
import com.dataorganizer.util.TimingLoggerUtility;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DataManager dataManager;
    private TimingLoggerUtility timeLogUtility;
    private TextView mTimeTaken;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataOrganizerDatabaseHelper.getInstance(this).emptyVanStockTable(this);

        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setMessage("Data loading (wait for the dialog dismiss!)");
        mProgressDialog.setCancelable(false);

        mTimeTaken = (TextView) findViewById(R.id.time_taken);
        mTimeTaken.setText("Hit button to retrieve data...");
        timeLogUtility = new TimingLoggerUtility("MyTag", "MyMethodName");

        dataManager = new DataManager(this) {
            @Override
            public void onDataLoaded(Object data) {
                Log.e(TAG, "Data received!");
                timeLogUtility.addSplit("Load data from network(API)");
                VanStock[] vanStocks = (VanStock[]) data;

                new Thread(() -> {
                    // executing this operation in background thread
                    DataOrganizerDatabaseHelper.saveVanStocks(MainActivity.this, vanStocks);
                    Log.e(TAG, "Data persisted!");
                    timeLogUtility.addSplit("Data has been saved in DB");
                    Log.e(TAG, "Finished!");
                    timeLogUtility.dumpToLog();
                    runOnUiThread(() -> {
                        // executing this operation in Ui thread
                        mTimeTaken.setText(timeLogUtility.getLogs());
                        mProgressDialog.dismiss();
                    });
                }).start();
            }
        };

        initApiFetcherButton();
    }


    private void initApiFetcherButton() {
        Button apiFetcherButt = (Button) findViewById(R.id.hit_butt);
        apiFetcherButt.setOnClickListener((View view) -> {
            mProgressDialog.show();
            mTimeTaken.setText("Data is loading...");
            dataManager.loadVanDataStock("219", "257", "04601");
        });
    }
}
