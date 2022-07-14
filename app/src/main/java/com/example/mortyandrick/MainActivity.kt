package com.example.mortyandrick

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/") //API used
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()



        val mortyandRickInterface: MortyandRickInterface = retrofit.create(MortyandRickInterface::class.java)

        mortyandRickInterface.characterById().enqueue(object : Callback<Any>{

            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.i("Main activity", response.toString())
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.i("Main activity", t.message ?: "Null message")
            }
        })
    }
}