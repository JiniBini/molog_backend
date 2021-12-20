package com.jblog.molog.service

import com.jblog.molog.dto.guestbook.GuestBookPageRes
import com.jblog.molog.dto.guestbook.GuestBookReq
import com.jblog.molog.entity.GuestBook
import com.jblog.molog.entity.User
import com.jblog.molog.exception.BaseException
import com.jblog.molog.exception.BaseRes
import com.jblog.molog.exception.BaseResponseCode
import com.jblog.molog.jpa.GuestBookRepository
import com.jblog.molog.jpa.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class GuestBookService(private val guestBookRepository: GuestBookRepository, private val userRepository: UserRepository) {
    fun findAll(page: Int): GuestBookPageRes {
        var guestBookList: Page<GuestBook> = guestBookRepository.findAll(PageRequest.of(page - 1, 10))

        return GuestBookPageRes(guestBookList.totalPages, guestBookList.content)
    }

    fun postGuestBooks(guestBookReq: GuestBookReq): BaseRes {
        val principal: Any = SecurityContextHolder.getContext().authentication.principal
        val userDetails: UserDetails = principal as UserDetails
        val email: String = userDetails.username

        val user: User = userRepository.findByEmail(email).orElseThrow{BaseException(BaseResponseCode.USER_NOT_FOUND)}

        guestBookRepository.save(GuestBook(user, guestBookReq.comment))

        return BaseRes(HttpStatus.OK, "방명록 생성 완료")
    }

    fun deleteGuestBooks(id: Long): BaseRes {
        // 해당하는 게시물이 있는지 확인
        guestBookRepository.findById(id).orElseThrow{BaseException(BaseResponseCode.GUEST_BOOK_NOT_FOUND)}
        // 있으면 삭제
        guestBookRepository.deleteById(id)

        return BaseRes(HttpStatus.OK, "방명록 삭제 완료")
    }

    fun patchGuestBooks(id: Long, guestBookReq: GuestBookReq): BaseRes {
        var guestBook: GuestBook = guestBookRepository.findById(id).orElseThrow{BaseException(BaseResponseCode.GUEST_BOOK_NOT_FOUND)}

        guestBook.comment = guestBookReq.comment
        guestBookRepository.save(guestBook)

        return BaseRes(HttpStatus.OK, "방명록 수정 완료")
    }
}