package com.example.mortyandrick;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MortyandRickInterface {

    @GET("character/2")
    fun characterById(): Call<Any>
}
