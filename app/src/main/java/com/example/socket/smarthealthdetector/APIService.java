package com.example.socket.smarthealthdetector;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by socket on 9/2/17.
 */
public interface APIService {
@GET("0/man")
    Call<smarthealthdetector> getSymptons(@Query("token") String token,
                                          @Query("language") String language,
                                          @Query("format") String format);
@GET("{sym}")
    Call<smarthealth> getDisease(@Path("sym") String sym, @Query("token") String token,
                                 @Query("language") String language,
                                 @Query("format") String format);
}