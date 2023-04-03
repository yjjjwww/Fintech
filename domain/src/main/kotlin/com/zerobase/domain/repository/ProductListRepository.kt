package com.zerobase.domain.repository

import com.zerobase.domain.domain.ProductList
import org.springframework.data.jpa.repository.JpaRepository

interface ProductListRepository : JpaRepository<ProductList, Long> {
}