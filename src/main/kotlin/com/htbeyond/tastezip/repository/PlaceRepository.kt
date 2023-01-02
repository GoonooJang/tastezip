package com.htbeyond.tastezip.repository

import com.htbeyond.tastezip.entity.Place
import org.springframework.data.repository.CrudRepository

/**
 * @author Goonoo Jang
 */
interface PlaceRepository : CrudRepository<Place, Long> {
    fun findAllBy(): List<Place>
}