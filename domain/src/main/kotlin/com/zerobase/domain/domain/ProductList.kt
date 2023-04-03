package com.zerobase.domain.domain

import javax.persistence.*

@Entity
@Table(name = "PRODUCT_LIST")
class ProductList(
        @Column(name = "org_cd")
        val orgCd: String,

        @Column(name = "prod_cd")
        val prodCd: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}