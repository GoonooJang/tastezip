package com.htbeyond.tastezip.service

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Place
import com.htbeyond.tastezip.repository.PlaceRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PlaceServiceImpl(
    private val placeRepository: PlaceRepository
) : PlaceService {

    override fun readAll(): List<PlaceDTO> = placeRepository.findAll().map { it.toPlaceDTO() }

    override fun create(newPlace: PlaceDTO): PlaceDTO {
        if (placeRepository.existsByNameAndAddressAndCategory(
                name = newPlace.name,
                address = newPlace.address,
                category = newPlace.category
            )
        ) throw Exception("That place already exists ")

        return placeRepository.save(newPlace.toPlace()).toPlaceDTO()

    }


    override fun update(revisedPlace: PlaceDTO): PlaceDTO {
        //        val befPlace = placeRepository.findById(revisedPlace.id)

        return placeRepository.save(revisedPlace.toPlace()).toPlaceDTO()
    }

    override fun delete(placeId: Long) {
        placeRepository.deleteById(placeId)
    }

    override fun read(id: Long): Place? = placeRepository.findByIdOrNull(id)


}

