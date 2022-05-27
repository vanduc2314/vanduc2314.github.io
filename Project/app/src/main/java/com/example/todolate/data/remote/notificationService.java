package com.example.todolate.data.remote;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface notificationService {
   @Headers({"Content-Type: application/json",
           "Authorization: key=AAAA26PoLz0:APA91bHstPMQHyBT74mwDIcLGkA-9gfwdGDQ-Px_FJydMRtPp9zxfgJHuDuuKwPbM7FPA4L-oFNqfoTXoGoxUXDjmDjIv59JdkjmJeGxoEH2-4fWVCdxzxI2W4ToxaReL-fIyEX_iO3R"})
    @POST("fcm/send")
    Call<JsonObject> setNotificationDueDate(@Body JsonObject noti);
}
