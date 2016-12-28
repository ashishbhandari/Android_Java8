package com.dataorganizer.data.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by b_ashish on 20-Dec-16.
 */

public interface DataOrganizerService {

    String ENDPOINT = "http://sunaap06.axiom.local:8009/AxPosSyncRest/VanDataTransferRest.svc/";

//    api.openweathermap.org/data/2.5/weather?lat=35&lon=139

    @GET("DownloadOracleVanDataStockBulk/{OrgId}/{OrganizationCode}/{LocationCode}")
    Call<ResponseBody> getVanDataStockBulk(@Path("OrgId") String orgId,
                                           @Path("OrganizationCode") String organizationCode,
                                           @Path("LocationCode") String locationCode);

}
