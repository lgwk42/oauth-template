package com.project.oauthtemplate.domain.user.domain.entity

import com.project.oauthtemplate.global.common.entity.BaseEntity
import com.project.oauthtemplate.domain.user.domain.enum.UserRole
import jakarta.persistence.*
import lombok.experimental.SuperBuilder
import org.hibernate.annotations.DynamicUpdate

@Entity
@SuperBuilder
@DynamicUpdate
@Table(name = "tb_user")
class UserEntity(

    @Id
    @Column( unique = true)
    var email: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    var userRole: UserRole,

    ) : BaseEntity()