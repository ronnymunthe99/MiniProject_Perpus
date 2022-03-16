package com.praktikum.perpus

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @GET("api/buku")
    fun getBook(): Call<ArrayList<Book>>

    @FormUrlEncoded
    @POST("api/buku")
    fun createBook(
        @Field("id") id: Int,
        @Field("buku_id") buku_id: String,
        @Field("id_kategori") id_kategori: Int,
        @Field("id_rak") id_rak: Int,
        @Field("title") title: String,
        @Field("isbn") isbn: String,
        @Field("penerbit") penerbit: String,
        @Field("pengarang") pengarang: String,
        @Field("thn_buku") thn_buku: String,
        @Field("jml") jml: Int
    ): Call<Book>
}