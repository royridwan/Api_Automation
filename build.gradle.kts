plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20231013")
    testImplementation("org.testng:testng:7.9.0")
    testImplementation("io.rest-assured:rest-assured:5.4.0")
}

tasks.test {
    useTestNG()
}