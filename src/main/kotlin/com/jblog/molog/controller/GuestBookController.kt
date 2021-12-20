package com.jblog.molog.controller

import com.jblog.molog.dto.guestbook.GuestBookPageRes
import com.jblog.molog.dto.guestbook.GuestBookReq
import com.jblog.molog.entity.GuestBook
import com.jblog.molog.exception.BaseException
import com.jblog.molog.exception.BaseRes
import com.jblog.molog.service.GuestBookService
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GuestBookController(private val guestBookService: GuestBookService) {

    // guest book 조회
    @GetMapping("/guest-books/{page}")
    fun findAll(@PathVariable page: Int): ResponseEntity<GuestBookPageRes> {
        return ResponseEntity.ok(guestBookService.findAll(page))
    }

    // guest book 저장
    @PostMapping("/guest-books")
    fun postGuestBooks(@RequestBody guestBookReq: GuestBookReq): BaseRes {
        return guestBookService.postGuestBooks(guestBookReq)
    }

    // guest book 수정
    @PatchMapping("/guest-books/{id}")
    fun patchGuestBooks(@PathVariable id: Long, @RequestBody guestBookReq: GuestBookReq): BaseRes {
        return guestBookService.patchGuestBooks(id, guestBookReq)
    }

    // guest book 삭제
    @DeleteMapping("/guest-books/{id}")
    fun deleteGuestBooks(@PathVariable id: Long): BaseRes {
        return guestBookService.deleteGuestBooks(id)
    }
}