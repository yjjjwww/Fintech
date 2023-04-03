package com.zerobase.api.user.service

import com.zerobase.api.user.GenerateKey
import com.zerobase.api.user.encrypt.EncryptComponent
import com.zerobase.api.user.model.UserInfoDto
import com.zerobase.domain.domain.UserInfo
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class UserInfoServiceImpl(
        private val generateKey: GenerateKey,
        private val userInfoRepository: UserInfoRepository,
        private val encryptComponent: EncryptComponent
): UserInfoService {
    override fun createUserInfo(
            userInfoRequestDto: UserInfoDto.UserInfoRequestDto
    ): UserInfoDto.UserInfoResponseDto {
        val userKey = generateKey.generateUserKey()

        userInfoRequestDto.userRegistrationNumber =
                encryptComponent.encryptString(userInfoRequestDto.userRegistrationNumber)

        val userInfoDBDto = userInfoRequestDto.toUserInfoDBDto(userKey)

        saveUserInfo(userInfoDBDto)

        return UserInfoDto.UserInfoResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDBDto: UserInfoDto.UserInfoDBDto): UserInfo {
        return userInfoRepository.save(userInfoDBDto.toEntity())
    }

    override fun getUserInfo(userKey: String): UserInfoDto.GetUserInfoResponseDto {
        val userInfo = userInfoRepository.findByUserKey(userKey)

        return UserInfoDto.GetUserInfoResponseDto(
                userKey = userKey,
                userRegistrationNumber = encryptComponent.decryptString(userInfo.userRegistrationNumber)
        )
    }
}