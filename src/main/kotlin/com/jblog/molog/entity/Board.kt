package com.jblog.molog.entity

class Board(user: User, title:String, content: String): BaseTime()  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    var user: User = user

    @Column(nullable = false)
    var title: String = title

    @Column(nullable = false)
    var content: String = content
}