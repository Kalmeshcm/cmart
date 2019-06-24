package com.example.abc.cmart;

/**
 * Created by ABC on 11-02-2018.
 */

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

  //this is a comment


   // @FormUrlEncoded
    @POST("index.php")
    Call<Products> saveProduct(@Field("type") String ntype,
                            @Field("name") String nname,
                           @Field("desc") String ndesc,
                           @Field("price") String nprice,
                           @Field("seller") String sell);
    @Multipart
    @POST("image.php")
    Call<Products> uploadFile(@Part MultipartBody.Part file, @Part("file") RequestBody name);

    @GET("addproduct.php")
    Call<List<Products>> showProduct();
}

