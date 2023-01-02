package com.htbeyond.tastezip.service

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Category

/**
 * @author Goonoo Jang
 */
interface PlaceService {
    fun readWithParam(searchText: String, category: Category)
    fun read(id: Long)
    fun create(newPlace: PlaceDTO)
    fun update(befPlace: PlaceDTO)
    fun delete(deletedList: List<PlaceDTO>)
}