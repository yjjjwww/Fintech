package com.zerobase.api.user.controller

import com.zerobase.api.user.model.UserInfoDto
import com.zerobase.api.user.service.UserInfoServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/v1/user")
class UserController(
        private val userInfoServiceImpl: UserInfoServiceImpl
) {
    @PostMapping("/information")
    fun createUserInfo(
            @RequestBody userInfoRequestDto: UserInfoDto.UserInfoRequestDto
    ): ResponseEntity<UserInfoDto.UserInfoResponseDto> {
        return ResponseEntity.ok(
                userInfoServiceImpl.createUserInfo(userInfoRequestDto)
        )
    }

    @GetMapping("/private-info/{userKey}")
    fun getUserInfo(
            @PathVariable userKey: String
    ): ResponseEntity<UserInfoDto.GetUserInfoResponseDto> {
        return ResponseEntity.ok(
            userInfoServiceImpl.getUserInfo(userKey)
        )
    }
}