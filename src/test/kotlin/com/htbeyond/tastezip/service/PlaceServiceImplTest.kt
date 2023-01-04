package com.htbeyond.tastezip.service

import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Category
import com.htbeyond.tastezip.repository.PlaceRepository
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author Goonoo Jang
 */
@ExtendWith(MockKExtension::class)
class PlaceServiceImplTest {
    @InjectMockKs
    private lateinit var placeService: PlaceService
    @MockK
    private lateinit var placeRepository: PlaceRepository

    @Test
    fun `하나 생성`() {
        // given
        val dummyPlaceDto =
            PlaceDTO(id = 123456789, name = "dummy", address = "asd시 asd구 asd동", category = Category.Korean)
        val expectedResult = dummyPlaceDto.toPlace()
        every { placeRepository.save(any()) } returns expectedResult

        // when
        val createTestResult = placeService.create(dummyPlaceDto)

        // then
        Assertions.assertThat(createTestResult).isEqualTo(expectedResult)
        verify(exactly = 1) {
            placeRepository.save(expectedResult)
        }
        confirmVerified(placeRepository)
    }


}