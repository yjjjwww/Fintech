package com.zerobase.api.product.service

import com.zerobase.api.product.model.ProductDto

interface ProductService {
    fun getProduct(organizationCode: String): MutableList<ProductDto.GetProductResponseDto>

    fun saveProduct(saveProductRequestDto: ProductDto.SaveProductRequestDto): String

    fun saveProductInfo(saveProductRequestDto: ProductDto.SaveProductRequestDto)

    fun saveProductList(saveProductRequestDto: ProductDto.SaveProductRequestDto)
}