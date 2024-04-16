package com.mdy.sample.integration

import com.mdy.sample.annotation.IntegrationTest
import com.mdy.sample.entity.UserId
import com.mdy.sample.repository.UserRepository
import com.mdy.sample.stub.UserStub
import com.mdy.sample.utils.TestUtils.genString
import com.mdy.sample.utils.logger
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

@DisplayName("[IntegrationTest] UserRepository")
@IntegrationTest
class UserRepositoryIntegrationTest(
    private val userRepository: UserRepository,
) {

    @BeforeEach
    fun deleteAll() {
        userRepository.deleteAll()
    }

    @DisplayName("findIdOrNull() test")
    @Test
    fun test() {
        // given
        val givenEmail = genString()
        val givenName = genString()
        log.info("# res: (0) givenEmail: $givenEmail / givenName: $givenName")
        userRepository.saveAll(
            listOf(
                userRepository.save(UserStub.getWithParams()),
                userRepository.save(UserStub.getWithParams()),
                userRepository.save(UserStub.getWithParams(email = givenEmail, name = givenName)),
            )
        )
        // when


        val res = userRepository.findAll()

        log.info("# res: (1) $res")
        log.info("# res: (2) size: ${res.size}")

        val givenId = res.first { it.userId.email == givenEmail }.userId.id
        log.info("# res: (3) givenId: $givenId")

        val res2 = userRepository.findByIdOrNull(UserId(email = givenEmail))

        log.info("# res: (4) $res2")
//        val res = userRepository.find()
//
//        // then
//        Assertions.assertEquals(5, res.size)
    }

    companion object {
        private val log = logger()
    }
}