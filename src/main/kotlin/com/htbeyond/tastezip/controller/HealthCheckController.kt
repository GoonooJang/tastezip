package com.htbeyond.tastezip.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Goonoo Jang
 */
@RestController
@RequestMapping("/test")
class HealthCheckController {

    @GetMapping("/alive")
    fun healthCheck(): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(Unit)
    }

}