package com.htbeyond.tastezip.service

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Place

/**
 * @author Goonoo Jang
 */
interface PlaceService {
    fun readAll(): List<PlaceDTO>
    fun read(id: Long): Place?
    // todo: readWithFilter
    fun create(newPlace: PlaceDTO): PlaceDTO
    fun update(revisedPlace: PlaceDTO): PlaceDTO
    fun delete(placeId: Long)
}