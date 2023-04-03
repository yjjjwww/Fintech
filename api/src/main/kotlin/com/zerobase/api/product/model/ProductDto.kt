package com.zerobase.api.product.model

import com.zerobase.api.product.service.ProductService
import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.domain.ProductList

class ProductDto {
    data class GetProductResponseDto(
            val organizationCode: String,
            val productCode: String,
            val productMaximumInterest: Double,
            val productMinimumInterest: Double,
            val productName: String
    )

    data class SaveProductRequestDto (
        val organizationCode: String,
        val productCode: String,
        val productMaximumInterest: Double,
        val productMinimumInterest: Double,
        val productName: String
    ) {
        fun toInfoEntity(): ProductInfo =
                ProductInfo(organizationCode, productCode, productName, productMinimumInterest, productMaximumInterest)

        fun toListEntity(): ProductList =
                ProductList(organizationCode, productCode)
    }
}