package com.zerobase.api.product.service

import com.zerobase.api.product.model.ProductDto
import com.zerobase.domain.repository.ProductInfoRepository
import com.zerobase.domain.repository.ProductListRepository
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        private val productInfoRepository: ProductInfoRepository,
        private val productListRepository: ProductListRepository
): ProductService {
    override fun getProduct(
            organizationCode: String
    ): MutableList<ProductDto.GetProductResponseDto> {

        val productInfos = productInfoRepository.findByOrgCd(organizationCode)
        var productInfoResponses: MutableList<ProductDto.GetProductResponseDto>
            = mutableListOf();

        for (product in productInfos) {
            productInfoResponses.add(ProductDto.GetProductResponseDto(
                    product.orgCd, product.prodCd, product.prodMaxIntr,
                    product.prodMinIntr, product.prodNm
            ))
        }

        return productInfoResponses;
    }

    override fun saveProduct(
            saveProductRequestDto: ProductDto.SaveProductRequestDto
    ): String {
        saveProductInfo(saveProductRequestDto)
        saveProductList(saveProductRequestDto)
        return "등록 성공"
    }

    override fun saveProductInfo(saveProductRequestDto: ProductDto.SaveProductRequestDto) {
        productInfoRepository.save(saveProductRequestDto.toInfoEntity())
    }

    override fun saveProductList(saveProductRequestDto: ProductDto.SaveProductRequestDto) {
        productListRepository.save(saveProductRequestDto.toListEntity())
    }

}