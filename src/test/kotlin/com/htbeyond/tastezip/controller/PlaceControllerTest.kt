package com.htbeyond.tastezip.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Category
import com.htbeyond.tastezip.repository.PlaceRepository
import com.htbeyond.tastezip.service.PlaceService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


/**
 * @author Goonoo Jang
 */

// 참고한 링크 https://zzang9ha.tistory.com/382
// 참고한 파일 mid-reservation / EsManagerCommonRestControllerTest.kt
@WebMvcTest(PlaceController::class)
@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
class PlaceControllerTest @Autowired constructor( // constructor 관련 오류 해결: https://minkukjo.github.io/framework/2020/06/28/JUnit-23/
    private val mockMvc: MockMvc
) {
    @MockkBean(relaxed = true) // https://velog.io/@3210439/MockkMockito-Kotlin
    private lateinit var placeService: PlaceService

    @MockkBean
    private lateinit var placeRepository: PlaceRepository

    private lateinit var jacksonObjectMapper: ObjectMapper

    @BeforeEach
    fun init() {
        jacksonObjectMapper = ObjectMapper()
    }

    @Test
    fun `단일 맛집 조회`() {
        val place = PlaceDTO(
            id = 1, name = "dummy", address = "asd시 asd구 asd동", category = Category.Korean
        )

        every { placeRepository.findByIdOrNull(any()) } returns place.toPlace()
        every { placeService.read(any()) } returns place.toPlace()

        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/place/{id}", 3)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.content().string(place3.toString())) // todo: PlaceDTO로 변환하여 비교
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun `맛집 생성`() {
        val place = PlaceDTO(
            id = 4569489, name = "dummyasd", address = "asdasd시 asd구 asd동", category = Category.Korean
        )

        val jsonBody = jacksonObjectMapper.writeValueAsString(place)

        every { placeRepository.save(any()) } returns place.toPlace()
        every { placeService.create(any()) } returns place

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/place/")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andDo(MockMvcResultHandlers.log())
            // todo: andExpectAll로 확인까지 해보기
            .andExpect(MockMvcResultMatchers.status().isOk) // todo: Create 수행시 404 error 발생하는 부분 해결 (동일한 DTO로 Postman 수행했을 시엔 생성되었음)
//            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.content().string(place3.toString())) // todo: PlaceDTO로 변환하여 비교
    }
}