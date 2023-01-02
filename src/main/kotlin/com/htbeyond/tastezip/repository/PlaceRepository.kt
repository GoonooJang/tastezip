package com.htbeyond.tastezip.repository

import com.htbeyond.tastezip.entity.Place
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Goonoo Jang
 */
interface PlaceRepository : JpaRepository<Place, Long>