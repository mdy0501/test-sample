package com.mdy.sample.repository

import com.mdy.sample.entity.User
import com.mdy.sample.entity.UserId
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: UserJpaRepository
interface UserJpaRepository: JpaRepository<User, UserId>