package com.htbeyond.tastezip.dto

import com.htbeyond.tastezip.entity.Category
import com.htbeyond.tastezip.entity.Place

/**
 * @author Goonoo Jang
 */
class PlaceDTO(
    val id: Long = 0L,
    val name: String,
    val address: String,
    val category: Category
) {
    fun toPlace() =
        Place(id = id, name = name, address = address, category = category)


}