package com.htbeyond.tastezip.dto

import com.htbeyond.tastezip.entity.Category

/**
 * @author Goonoo Jang
 */
class PlaceDTO(
    val id: Long? = null,
    val name: String,
    val address: String,
    val category: Category
) {

}