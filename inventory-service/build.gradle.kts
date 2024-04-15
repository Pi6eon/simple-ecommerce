plugins {
    id("java")
}

group = "org.fallinnadim"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
    testImplementation("org.testcontainers:postgresql")
    implementation("org.flywaydb:flyway-core")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}

tasks.test {
    useJUnitPlatform()
}