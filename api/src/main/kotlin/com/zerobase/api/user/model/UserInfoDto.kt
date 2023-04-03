package com.zerobase.api.user.model

import com.zerobase.domain.domain.UserInfo

class UserInfoDto {
    data class UserInfoRequestDto(
            val userName: String,
            val userIncomeAmount: Long,
            var userRegistrationNumber: String
    ) {
        fun toUserInfoDBDto(userKey: String) =
                UserInfoDBDto(
                        userKey, userName, userRegistrationNumber, userIncomeAmount
                )
    }

    data class UserInfoResponseDto(
            val userKey: String
    )

    data class UserInfoDBDto(
            val userKey: String,
            val userName: String,
            val userRegistrationNumber: String,
            val userIncomeAmount: Long
    ) {
        fun toEntity(): UserInfo =
                UserInfo(userKey, userRegistrationNumber, userName, userIncomeAmount)
    }

    data class GetUserInfoResponseDto(
            val userKey: String,
            val userRegistrationNumber: String
    )
}