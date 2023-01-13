package com.htbeyond.tastezip.entity

import com.htbeyond.tastezip.dto.PlaceDTO
import javax.persistence.*

/**
 * @author Goonoo Jang
 */

enum class Category {
    Korean, Japanese, Chinese, American, Mexican, Vietnamese, Italian, Cafe, Pub, Etc
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
    // todo: 어느나라 음식인지 애매하다면 비워두는걸로 설정
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