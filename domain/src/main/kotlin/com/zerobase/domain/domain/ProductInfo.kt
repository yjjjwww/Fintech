package com.zerobase.domain.domain

import javax.persistence.*

@Entity
@Table(name = "PRODUCT_INFO")
class ProductInfo(
        @Column(name = "org_cd")
        val orgCd: String,

        @Column(name = "prod_cd")
        val prodCd: String,

        @Column(name = "prod_nm")
        val prodNm: String,

        @Column(name = "prod_min_intr")
        val prodMinIntr: Double,

        @Column(name = "prod_max_intr")
        val prodMaxIntr: Double,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}