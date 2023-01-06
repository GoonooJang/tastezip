package com.htbeyond.tastezip.exception

import javax.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * @author Goonoo Jang
 */
@ControllerAdvice
class GlabalExceptionHandler() {
    // mid-payment / config.web.exception
    @ExceptionHandler(Exception::class)
    protected fun handlerAnythingElse(
        e: Exception,
        request: HttpServletRequest
    ): ResponseEntity<Exception> {
        return ResponseEntity.internalServerError().body(e)
    }
}