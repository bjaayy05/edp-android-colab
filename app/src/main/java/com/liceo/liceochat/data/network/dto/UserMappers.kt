package com.liceo.liceochat.data.network.dto

import com.liceo.liceochat.domain.model.User

fun UserDto.toDomain(): User = User(
    id = id ?: "",
    fullName = fullname?.trim() ?: "(no name)",
    email = email?.trim() ?: "",
    birthdate = birthdate ?: "(not set)"
)
