package com.liceo.liceochat.data.network

import com.liceo.liceochat.data.network.dto.MessageDto
import com.liceo.liceochat.data.network.dto.NewMessageDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApiService {
    @GET("messages")
    suspend fun getMessages(): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(@Body message: NewMessageDto): MessageDto

    @DELETE("messages/{id}")
    suspend fun deleteMessage(@Path("id") id: String)
}