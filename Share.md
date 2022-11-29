# About Test

## Description
### how to execute
- (1) docker로 local db 실행: `$ docker compose up db` (docker-compose.yml)
- (2) `$ ./gradlew test`

<br><br><br>

### check list
#### 1. JUnitFunctionTest
- `@BeforeEach`, `@BeforeAll`, `@AfterEach`, `@AfterAll`

<br>

#### 2. `MockK` / `Mockito`
- `MockK`: https://mockk.io/
```groovy
dependencies {
  testImplementation("io.mockk:mockk:1.13.2")
}
```

- `Mockito`: https://site.mockito.org/
  - 참고: https://codechacha.com/ko/mockito-best-practice/


<br>

#### 3. 테스트 종류
- (1) UnitTest
  - mocking
    - `every { }`
    - `assertThrows<>{ }`: exception check
    - `verify { }`
  - `ProductServiceUnitTest`
  - `Base64UtilsTest`
- (2) IntegrationTest
  - 멱등성 유지 (`@AfterEach`)
  - `ProductServiceIntegrationTest`
  - `ProductRepositoryIntegrationTest`
- (3) E2E Test
  - `TestRestTemplate` 사용
    - 참고: https://meetup.toast.com/posts/124
  - `ProductE2ETest`
- (4) Scenario Test
  - `ProductScenarioTest`

<br>

#### 4. 테스트 유틸
- `stub`: https://martinfowler.com/bliki/TestDouble.html
- test util function

<br>

#### 5. SpringBoot 지원 테스트 annotation
- `@SpringBootTest`: 전체 테스트 어노테이션 (애플리케이션에 주입된 Bean 전체)
- `@WebMvcTest`: Controller Layer 테스트 (MVC 관련 Bean (Controller, Service))
- `@DataJpaTest`: Jpa (DB I/O) 테스트 (JPA 관련 Bean (EntityManger))
- `@RestClientTest`: Rest API 테스트 (RestTemplate 등 일부 Bean)
- `@JsonTest`: Json 데이터 테스트 (Json 관련 일부 Bean)


<br>


#### 6. @MockBean
- 참고
  - https://jojoldu.tistory.com/226
  - https://velog.io/@sproutt/MockBean%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%ED%86%B5%ED%95%A9Controller%ED%85%8C%EC%8A%A4%ED%8A%B8


#### 7. Spy
- 참고: https://coco-log.tistory.com/194

<br><br><br>


## ETC
- (1) querydsl method 추가 후, 테스트 추가
- (2) 리팩토링(또는 기능 수정)후, 테스트로 기능 확인
- (3) `Shortcut`
  - `control + R`: 이전에 실행했던 테스트 다시 실행
  - `control + shift + R`: 커서가 있는 곳의 테스트 실행