package com.zerobase.api.user.service

import com.zerobase.api.exception.CustomErrorCode
import com.zerobase.api.exception.CustomException
import com.zerobase.api.user.GenerateKey
import com.zerobase.api.user.encrypt.EncryptComponent
import com.zerobase.api.user.model.UserInfoDto
import com.zerobase.domain.domain.UserInfo
import com.zerobase.domain.repository.UserInfoRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

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
        val userInfo = getUser(userKey)
        if (!userInfo.isPresent) {
            throw CustomException(CustomErrorCode.USER_NOT_FOUND)
        }

        return UserInfoDto.GetUserInfoResponseDto(
                userKey = userKey,
                userRegistrationNumber = encryptComponent.decryptString(userInfo.get().userRegistrationNumber)
        )
    }

    override fun getUser(userKey: String): Optional<UserInfo> {
        return userInfoRepository.findByUserKey(userKey)
    }
}