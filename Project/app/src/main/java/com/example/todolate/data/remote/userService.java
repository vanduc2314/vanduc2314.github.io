package com.example.todolate.data.remote;

import com.example.todolate.model.User;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface userService {
    @POST("users.json")
    Call<JsonObject> sendUser(@Body User u);

    @POST("./accounts:signUp?key=AIzaSyA4KHd3paq5wDboGRCCQSO3yfTGbDrKB_w")
    Call<JsonObject> signUpWithEmail(@Body Map<String, String> m);

    @POST("./accounts:update?key=AIzaSyA4KHd3paq5wDboGRCCQSO3yfTGbDrKB_w")
    Call<JsonObject> updateAccount(@Body Map<String, String> m);

    @POST("./accounts:signInWithPassword?key=AIzaSyA4KHd3paq5wDboGRCCQSO3yfTGbDrKB_w")
    Call<JsonObject> signInWithEmail(@Body Map<String, String> m);

    @PATCH("users/{id}/lists.json")
    Call<JsonObject> updateListtoUser(@Path("id") String id, @Body Map<String, String> listID);

    @PATCH("users/{id}.json")
    Call<JsonObject> updateUser(@Path("id") String id, @Body User user);

    @GET("users/{id}/lists.json")
    Call<JsonObject> getListofUser(@Path("id") String id);

    @GET("users.json")
    Call<Map<String, User>> getUser(@Query("orderBy") String index,@Query("equalTo") String email);
    @DELETE("users/{userID}/lists/{listID}.json")
    Call<JsonObject> deleteListAtUser(@Path("userID") String userID,@Path("listID") String listID);
}
