package com.dataorganizer.data;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by b_ashish on 30-Aug-16.
 */

public interface DataLoadingSubject {

    boolean isDataLoading();

    void registerCallback(DataLoadingCallbacks callbacks);

    void unregisterCallback(DataLoadingCallbacks callbacks);

    interface DataLoadingCallbacks {

        void dataStartedLoading();

        void dataFinishedLoading();
    }

//    /**
//     * Java 8 feature, Now when a class will implement this interface, it is not mandatory to provide implementation for default methods of interface,
//     * it's not supportable below than N preview
//     *
//     * @param str
//     */
//    @TargetApi(Build.VERSION_CODES.N)
//    default void log(String str) {
//        System.out.println("I1 logging::" + str);
//    }
}
