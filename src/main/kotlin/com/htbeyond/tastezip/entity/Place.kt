package com.htbeyond.tastezip.entity

import com.htbeyond.tastezip.dto.PlaceDTO
import javax.persistence.*

/**
 * @author Goonoo Jang
 */

enum class Category {
    Korean, Japanese, Chinese, American, Mexican, Vietnamese,
}

@Entity
data class Place(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    val name: String,
    val address: String,
    @Enumerated(EnumType.STRING)
    val category: Category,
    // id, 이름, 주소, 카테고리,
) {
    fun toPlaceDTO(): PlaceDTO {
        return PlaceDTO(
            id = id,
            name = name,
            address = address,
            category = category
        )
    }

}