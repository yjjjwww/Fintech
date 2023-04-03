package com.zerobase.domain.repository

import com.zerobase.domain.domain.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserInfoRepository : JpaRepository<UserInfo, Long> {
    fun findByUserKey(userKey: String): Optional<UserInfo>
}