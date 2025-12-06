package com.example.recruitment_task.data.remote

import com.example.recruitment_task.data.remote.dto.AdsDto
import retrofit2.http.GET

interface MartketplaceApi {

    @GET("harishsrikantaiah-max/3449269056f83445b364e9242b9f5959\\n\" +\n" +
            "            \"/raw/b7ada1c4eb6d418e49f2110b01c45f775a1a2bd4/ad.json")
    suspend fun getAds() : AdsDto
}