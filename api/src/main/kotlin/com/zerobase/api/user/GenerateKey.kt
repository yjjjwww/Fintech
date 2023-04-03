package com.zerobase.api.user

import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateKey {
    fun generateUserKey() = UUID.randomUUID().toString().replace("-", "")
}