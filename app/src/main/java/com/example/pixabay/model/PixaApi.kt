package com.example.pixabay.model
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface PixaApi {
    @GET("api/")
    fun getImages(@Query("key")key:String="31287161-b437ebaadff291fbb2a65f404",
                  @Query("q")keyWord: String,
                  @Query("page") page:Int= 1,
                  @Query("per_page") perPage:Int = 5

    ): Call <PixaModel>
}