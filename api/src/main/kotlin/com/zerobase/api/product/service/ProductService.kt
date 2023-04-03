package com.zerobase.api.product.service

import com.zerobase.api.product.model.Organization
import com.zerobase.api.product.model.ProductDto
import com.zerobase.domain.domain.ProductInfo
import java.util.*

interface ProductService {
    fun getProduct(organizationCode: Organization): MutableList<ProductDto.GetProductResponseDto>

    fun getProductList(organizationCode: String): Optional<List<ProductInfo>>

    fun saveProduct(saveProductRequestDto: ProductDto.SaveProductRequestDto): String

    fun saveProductInfo(saveProductRequestDto: ProductDto.SaveProductRequestDto)

    fun saveProductList(saveProductRequestDto: ProductDto.SaveProductRequestDto)
}