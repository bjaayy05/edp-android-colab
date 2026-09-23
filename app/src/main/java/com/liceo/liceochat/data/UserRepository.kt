package com.liceo.liceochat.data

import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.NetworkModule
import com.liceo.liceochat.data.network.UserApiService
import com.liceo.liceochat.data.network.dto.NewUserDto
import com.liceo.liceochat.data.network.dto.toDomain
import com.liceo.liceochat.domain.model.User
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class  UserRepository(
    private val api: UserApiService = NetworkModule.userApiService
) {


    suspend fun login(email: String, pass: String): AppResult<User> {
        return try {
            val list = try {
                api.findByEmail(email)
            } catch (e: HttpException) {
                if (e.code() == 404) emptyList() else throw e
            }
            val userDto = list.firstOrNull()
            if (userDto != null && userDto.password == pass) {
                AppResult.Success(userDto.toDomain())
            } else {
                AppResult.Failure.WrongLogin
            }
        } catch (e: Exception) {
            mapException(e)
        }
    }

    suspend fun register(
        fullName: String,
        email: String,
        pass: String,
        birthdate: String
    ): AppResult<User> {
        return try {
            val existing = try {
                api.findByEmail(email)
            } catch (e: HttpException) {
                if (e.code() == 404) emptyList() else throw e
            }
            if (existing.isNotEmpty()) {
                return AppResult.Failure.EmailTaken
            }
            val dto = NewUserDto(fullName, email, pass, birthdate)
            val created = api.createUser(dto).toDomain()
            AppResult.Success(created)
        } catch (e: Exception) {
            mapException(e)
        }
    }

    private fun mapException(e: Exception): AppResult.Failure = when (e) {
        is UnknownHostException -> AppResult.Failure.NoInternet
        is SocketTimeoutException -> AppResult.Failure.Timeout
        is IOException -> AppResult.Failure.NoInternet
        is SerializationException -> AppResult.Failure.Unknown("Data format error")
        is HttpException -> AppResult.Failure.Unknown("HTTP ${e.code()}")
        else -> AppResult.Failure.Unknown(e.message ?: "Unknown error")
    }
}