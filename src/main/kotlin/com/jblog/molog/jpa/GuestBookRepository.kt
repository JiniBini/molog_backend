package com.jblog.molog.jpa

import com.jblog.molog.entity.GuestBook
import org.springframework.data.jpa.repository.JpaRepository

interface GuestBookRepository: JpaRepository<GuestBook, Long> {
    //
}