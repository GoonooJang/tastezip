package com.htbeyond.tastezip.controller

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.service.PlaceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Goonoo Jang
 */
@RestController
@RequestMapping("/place")
class PlaceController(
    private val placeService: PlaceService
) {
    @GetMapping("/{placeId}")
    fun getPlaceById(
        @PathVariable placeId: Long
    ): ResponseEntity<PlaceDTO> = ResponseEntity.ok(placeService.read(placeId)?.toPlaceDTO())

    @GetMapping("/")
    fun getTotalPlaceList(): ResponseEntity<List<PlaceDTO>> = ResponseEntity.ok(placeService.readAll())

    @PostMapping("/")
    fun addPlace(
        @RequestBody place: PlaceDTO
    ): ResponseEntity<PlaceDTO> {
        val createdPlace = placeService.create(place)
        return ResponseEntity.ok(createdPlace)
    }

    @PutMapping("/")
    fun updatePlace(
        @RequestBody place: PlaceDTO
    ): ResponseEntity<PlaceDTO> {
        val updatedPlace = placeService.update(place)
        return ResponseEntity.ok(updatedPlace)
    }

    @DeleteMapping("/{placeId}")
    fun deletePlaceById(
        @PathVariable placeId: Long
    ): ResponseEntity<String> = ResponseEntity.ok("""${placeService.delete(placeId)}""")

}