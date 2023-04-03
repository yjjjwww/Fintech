package com.zerobase.api.product.service

import com.zerobase.api.exception.CustomErrorCode
import com.zerobase.api.exception.CustomException
import com.zerobase.api.product.model.Organization
import com.zerobase.api.product.model.ProductDto
import com.zerobase.domain.domain.ProductInfo
import com.zerobase.domain.repository.ProductInfoRepository
import com.zerobase.domain.repository.ProductListRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
        private val productInfoRepository: ProductInfoRepository,
        private val productListRepository: ProductListRepository
): ProductService {
    override fun getProduct(
            organizationCode: Organization
    ): MutableList<ProductDto.GetProductResponseDto> {

        val productInfos = getProductList(organizationCode.organizationCode)

        var productInfoResponses: MutableList<ProductDto.GetProductResponseDto>
            = mutableListOf();

        for (product in productInfos.get()) {
            productInfoResponses.add(ProductDto.GetProductResponseDto(
                    product.orgCd, product.prodCd, product.prodMaxIntr,
                    product.prodMinIntr, product.prodNm
            ))
        }

        return productInfoResponses;
    }

    @Cacheable(cacheNames = ["PRODUCT"], key = "#organizationCode")
    override fun getProductList(organizationCode: String): Optional<List<ProductInfo>> {
        return productInfoRepository.findByOrgCd(organizationCode)
    }

    @CacheEvict(cacheNames = ["PRODUCT"], key= "#organizationCode")
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