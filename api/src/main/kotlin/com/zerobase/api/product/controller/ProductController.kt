package com.zerobase.api.product.controller

import com.zerobase.api.product.model.Organization
import com.zerobase.api.product.model.ProductDto
import com.zerobase.api.product.service.ProductServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fintech/v1/product")
class ProductController(
        private val productServiceImpl: ProductServiceImpl
) {
    @GetMapping("/{organizationCode}")
    fun getProduct(
            @PathVariable organizationCode: Organization
    ): ResponseEntity<MutableList<ProductDto.GetProductResponseDto>> {
        return ResponseEntity.ok(productServiceImpl.getProduct(organizationCode))
    }

    @PostMapping("/information")
    fun saveProduct(
            @RequestBody saveProductRequestDto: ProductDto.SaveProductRequestDto
    ): ResponseEntity<String> {
        return ResponseEntity.ok(
                productServiceImpl.saveProduct(saveProductRequestDto)
        )
    }
}