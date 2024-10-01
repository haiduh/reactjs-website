plugins {
	java
	id("org.springframework.boot") version "3.2.2"
}

group = "com.github.group37"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.2"))
	implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation("org.springframework.security:spring-security-config:6.1.5")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.1.RELEASE")
	implementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("com.sun.mail:jakarta.mail:2.0.0")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-security")

	testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.boot:spring-test")
	testImplementation("io.projectreactor:reactor-test")
	testRuntimeOnly("com.h2database:h2")

	implementation("io.jsonwebtoken:jjwt-api:0.11.2")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.2")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
