package com.dataorganizer.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dataorganizer.model.VanStock;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by b_ashish on 20-Dec-16.
 */

public abstract class DataManager<T> extends BaseDataManager<T> {

    private static final String TAG = DataManager.class.getSimpleName();

    public DataManager(@NonNull Context context) {
        super(context);
    }

    /**
     * e.g:
     * http://sunaap06.axiom.local:8009/AxPosSyncRest/VanDataTransferRest.svc/DownloadOracleVanDataStockBulk/219/257/04601
     *
     * @param orgId
     * @param organizationCode
     * @param locationCode
     */
    public void loadVanDataStock(@NonNull String orgId, @NonNull String organizationCode, @NonNull String locationCode) {
        Call<ResponseBody> vanDataStockBulk = getDataOrganizerApi().getVanDataStockBulk(orgId, organizationCode, locationCode);
        vanDataStockBulk.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String body = response.body().string();
                        String jsonResponse = body.replaceAll("\\<.*?>", "");
                        VanStock[] vanStock = new Gson().fromJson(jsonResponse, VanStock[].class);
                        sourceLoaded((T) vanStock);
                    } catch (Exception e) {
                        e.printStackTrace();
                        loadFailed();
                    }
                } else {
                    loadFailed();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, call.toString());
            }
        });
    }


    private void onFailureHandler(Call<T> call, Throwable t) {
        final String errorType;
        final String errorDesc;
        if (t instanceof IOException) {
            errorType = "Timeout";
            errorDesc = String.valueOf(t.getMessage());
        } else if (t instanceof IllegalStateException) {
            errorType = "ConversionError";
            errorDesc = String.valueOf(t.getMessage());
        } else {
            errorType = "Other Error";
            errorDesc = String.valueOf(t.getLocalizedMessage());
        }

        HttpUrl url = call.request().url();
        loadFailed();
    }

    private void sourceLoaded(T data) {
        loadFinished();
        if (data != null) {
            onDataLoaded(data);
        }
    }

    private void loadFailed() {
        loadFinished();
    }

    @Override
    public void cancelLoading() {

    }
}
