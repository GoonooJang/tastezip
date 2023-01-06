package com.htbeyond.tastezip.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.htbeyond.tastezip.dto.PlaceDTO
import com.htbeyond.tastezip.entity.Category
import com.htbeyond.tastezip.repository.PlaceRepository
import com.htbeyond.tastezip.service.PlaceService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


/**
 * @author Goonoo Jang
 */

// 참고한 링크 https://zzang9ha.tistory.com/382
// 참고한 파일 mid-payment / PayOpenControllerTest.kt
//@SpringBootTest
@WebMvcTest(PlaceController::class)
@ExtendWith(MockKExtension::class)
@AutoConfigureMockMvc
class PlaceControllerTest @Autowired constructor( // constructor 관련 오류 해결: https://minkukjo.github.io/framework/2020/06/28/JUnit-23/
    private val mockMvc: MockMvc
) {
    @MockkBean
    private lateinit var placeService: PlaceService

    @MockkBean
    private lateinit var placeRepository: PlaceRepository

    private lateinit var jacksonObjectMapper: ObjectMapper

    @BeforeEach
    fun init() {
        jacksonObjectMapper = ObjectMapper()
    }

    @Test
    fun getPlace() {
        val place = PlaceDTO(
            id = 1, name = "dummy", address = "asd시 asd구 asd동", category = Category.Korean
        )

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
    fun createPlace() {
        val place = PlaceDTO(
            id = 4569489, name = "dummyasd", address = "asdasd시 asd구 asd동", category = Category.Korean
        )

        val jsonBody = jacksonObjectMapper.writeValueAsString(place)

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/place")
                .content(jsonBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andDo(MockMvcResultHandlers.log())
            // todo: andExpectAll로 확인까지 해보기
            .andExpect(MockMvcResultMatchers.status().isCreated) // todo: Create 수행시 404 error 발생하는 부분 해결 (동일한 DTO로 Postman 수행했을 시엔 생성되었음)
        // MockMvc에도 every같은 문법이 있는지 확인할 필요
//            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.content().string(place3.toString())) // todo: PlaceDTO로 변환하여 비교
    }
}