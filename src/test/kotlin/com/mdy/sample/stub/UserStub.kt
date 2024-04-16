package com.mdy.sample.stub

import com.mdy.sample.entity.User
import com.mdy.sample.entity.UserId
import com.mdy.sample.utils.TestUtils.genString

object UserStub {
    fun getWithParams(
        email: String = "${genString()}@test.com",
        name: String = "${genString()} name",
    ): User {
        return User(
            userId = UserId(email),
            name = name,
        )
    }
}