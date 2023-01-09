package com.htbeyond.tastezip.service

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Category
import com.htbeyond.tastezip.entity.Place
import com.htbeyond.tastezip.repository.PlaceRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

/**
 * @author Goonoo Jang
 */

// https://basketdeveloper.tistory.com/87
@ExtendWith(MockKExtension::class)
@SpringBootTest(classes = [PlaceServiceImpl::class])
class PlaceServiceImplTest {
    @MockkBean(relaxed = true) // https://velog.io/@3210439/MockkMockito-Kotlin
    private lateinit var placeService: PlaceService

    @MockkBean
    private lateinit var placeRepository: PlaceRepository

    private val dummyPlaceDto: PlaceDTO =
        PlaceDTO(id = 123456789, name = "dummy", address = "asd시 asd구 asd동", category = Category.Korean)

    private val dummyPlace: Place = dummyPlaceDto.toPlace()

    @Test
    fun `단일 맛집 조회`() {
        // given
        every { placeRepository.findByIdOrNull(any()) } returns dummyPlaceDto.toPlace()
        every { placeService.read(any()) } returns dummyPlaceDto.toPlace()

        // when
        val readTestResult = placeService.read(dummyPlaceDto.id)

        // then
        Assertions.assertThat(readTestResult).isEqualTo(dummyPlace)
    }

    @Test
    fun `맛집 생성`() {
        // given
        val expectedResult = dummyPlaceDto.toPlace()
        every { placeRepository.save(any()) } returns expectedResult
        every { placeService.create(dummyPlaceDto) } returns dummyPlaceDto

        // when
        val createTestResult = placeService.create(dummyPlaceDto)

        // then
        Assertions.assertThat(createTestResult).isEqualTo(dummyPlaceDto)
    }

}