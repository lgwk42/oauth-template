package com.project.oauthtemplate.domain.user.domain.repository.jpa

import com.project.oauthtemplate.domain.user.domain.entity.UserEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.lang.Exception
import java.util.*

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): Optional<UserEntity>
    @Transactional(rollbackOn = [Exception::class])
    fun deleteByEmail(email: String)
}