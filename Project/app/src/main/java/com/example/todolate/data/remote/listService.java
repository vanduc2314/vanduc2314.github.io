package com.example.todolate.data.remote;

import com.example.todolate.model.ActList;
import com.example.todolate.model.Task;
import com.google.gson.JsonObject;

import java.util.Date;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface listService {

    @POST("lists.json")
    Call<JsonObject> createList(@Body ActList actList);
    @POST("lists/{id}/Task.json")
    Call<JsonObject> addTaskToList(@Path("id") String id, @Body Task task);
    @GET("lists/{id}/Task/.json")
    Call<Map<String,Task>> getTaskByListID(@Path("id")String id);
    @DELETE("lists/{listID}/Task/{taskID}.json")
    Call<JsonObject> deleteTaskatList(@Path("listID") String listID, @Path("taskID") String taskID);
    @PATCH("lists/{listID}/Task/{id}.json")
    Call<JsonObject> updateStatusTask(@Path("listID")String id,@Path("id") String taskID,@Body Map<String, Boolean> task);
    @PATCH("lists/{listID}/Task/{id}.json")
    Call<JsonObject> updateTaskTitle(@Path("listID")String id,@Path("id") String taskID,@Body Map<String, String> task);
    @PATCH("lists/{listID}/Task/{id}.json")
    Call<JsonObject> updateDueDate(@Path("listID")String id,@Path("id") String taskID,@Body Map<String, Date> task);
    @PATCH("lists/{listID}/Task/{id}.json")
    Call<JsonObject> updateTaskNote(@Path("listID")String id,@Path("id") String taskID,@Body Map<String, String> task);
    @DELETE("lists/{listID}.json")
    Call<JsonObject> deleteList(@Path("listID") String listID);
}
