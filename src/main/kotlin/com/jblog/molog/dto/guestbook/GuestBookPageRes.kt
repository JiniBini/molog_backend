package com.jblog.molog.dto.guestbook

import com.jblog.molog.entity.GuestBook

class GuestBookPageRes(totalPage: Int, guestBookList: List<GuestBook>) {
    var totalPage: Int = totalPage
    var content: List<GuestBook> = guestBookList

//    init {
//        this.totalPage = 0
//        this.guestBookList = null
//    }
}