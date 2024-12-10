> 문의사항은 개인 연락 부탁드립니다. 에러나 수정 사항은 이슈로 올려주세요.
> 사용 시 STAR 한 번씩 해주세요, 큰 힘이 됩니다 😊
## API DOCS
### 인증 
POST (/auth)
#### Request
```json
{
  "idToken" : "string"
}
```
#### Response
```json
{
  "accessToken" : "string",
  "refreshToken" : "string"
}
```

### 토큰 재발급
POST (/auth/refresh)
#### Request
```json
{
  "refreshToken" : "string"
}
```
#### Response
```json
{
  "accessToken" : "string"
}
```

## ETC
### YML
사용자가 별도로 설정이 필요한 부분은 { } 가 쳐 있으며 내용은 다음과 같습니다
|이름| 설명|
|--|---|
| schema | 사용자의 DB Schema|
| password | 사용자 DB 비밀번호 |
| id | 구글 id 키|
| secret | 구글 secret 키|
| secret-key | jwt secret 키 |
### Gradle
해당 템플릿은 다음과 같은 라이브러리들을 사용하였으며 버전 관리는 build.gradle.kts 를 사용하였습니다
```gradle
implementation("org.springframework.boot:spring-boot-starter-data-jpa")
implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
implementation("org.springframework.boot:spring-boot-starter-security")
implementation("org.springframework.boot:spring-boot-starter-web")
implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
implementation("org.springframework.boot:spring-boot-starter-validation")
implementation("org.jetbrains.kotlin:kotlin-reflect")
implementation ("org.apache.httpcomponents.client5:httpclient5:5.2.1")
compileOnly("org.projectlombok:lombok")
runtimeOnly("com.mysql:mysql-connector-j")
annotationProcessor("org.projectlombok:lombok")
testImplementation("org.springframework.boot:spring-boot-starter-test")
testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
testImplementation("org.springframework.security:spring-security-test")
testRuntimeOnly("org.junit.platform:junit-platform-launcher")
implementation("io.jsonwebtoken:jjwt:0.12.5")
implementation("org.springframework.boot:spring-boot-starter-data-redis")
```
