    package com.example.ktor.network.di

import com.example.ktor.data.PostsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WordpressApiRetro {

//    companion object {
//        const val BASE_URL = "https://pravnapomosht.bg/"
//    }


    @GET("/wp-json/wp/v2/posts")
    //зарязах стария плъг ин rest api и пробвах директно с wp-json
    suspend fun getPostByCat(
        @Query("categories") categories:Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("_embed") embed: Boolean
    ) :List<PostsModel>
   // @GET("/wp-json/wp/v2/categories")
 //   suspend fun getCategories():List<Categories>

}