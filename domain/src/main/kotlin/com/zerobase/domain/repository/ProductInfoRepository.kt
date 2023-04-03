package com.zerobase.domain.repository

import com.zerobase.domain.domain.ProductInfo
import org.springframework.data.jpa.repository.JpaRepository

interface ProductInfoRepository : JpaRepository<ProductInfo, Long> {
}