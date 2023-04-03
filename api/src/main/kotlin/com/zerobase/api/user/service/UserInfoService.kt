package com.zerobase.api.user.service

import com.zerobase.api.user.model.UserInfoDto
import com.zerobase.domain.domain.UserInfo

interface UserInfoService {
    fun createUserInfo(
            userInfoRequestDto: UserInfoDto.UserInfoRequestDto
    ): UserInfoDto.UserInfoResponseDto

    fun saveUserInfo(
            userInfoDBDto: UserInfoDto.UserInfoDBDto
    ): UserInfo

    fun getUserInfo(userKey: String): UserInfoDto.GetUserInfoResponseDto
}