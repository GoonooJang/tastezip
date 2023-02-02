package com.htbeyond.tastezip.controller

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.service.PlaceService
import java.lang.Exception
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
    ): ResponseEntity<PlaceDTO> {
        val findPlace = placeService.read(placeId)
        return if(findPlace != null) ResponseEntity.ok(placeService.read(placeId)?.toPlaceDTO()) else throw Exception("there is no place which id is $placeId")
    }

    @GetMapping("/")
    fun getTotalPlaceList(): ResponseEntity<List<PlaceDTO>> = ResponseEntity.ok(placeService.readAll())

    @PostMapping("/")
    fun addPlace(
        @RequestBody place: PlaceDTO
    ): ResponseEntity<PlaceDTO> {
        val createdPlace: PlaceDTO
        try {
            createdPlace = placeService.create(place)
        } catch (e: Exception) {
            throw e
        }

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