package com.example.inversetracker.interfaces;

import com.example.inversetracker.models.ResponseModelClass;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APISet {

    @FormUrlEncoded
    @POST("api/users/auth/token/login/")
    Call<ResponseModelClass> postEntry(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/users/auth/users/me/")
    Call<ResponseModelClass> getMeAccount(@Header("Authorization") String token);

    @GET("api/news/")
    Call<List<ResponseModelClass>> getNews();

    @FormUrlEncoded
    @POST("api/users/auth/users/")
    Call<ResponseModelClass> postRegistration(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("patronymic") String patronymic,
            @Field("password") String password,
            @Field("age") int age,
            @Field("school_class") int schoolClass,
            @Field("role") int role
    );

    @GET("api/courses/")
    Call<List<ResponseModelClass>> getAllCourses(
            @Header("Authorization") String token
    );

    @GET("api/courses/{Id}/")
    Call<ResponseModelClass> getCoursesIndex(
            @Header("Authorization") String token,
            @Path("Id") int id
    );

    @GET("api/courses/{Id}/document/")
    Call<ResponseModelClass> getDocumentExample(
            @Header("Authorization") String token,
            @Path("Id") int id
    );
}
