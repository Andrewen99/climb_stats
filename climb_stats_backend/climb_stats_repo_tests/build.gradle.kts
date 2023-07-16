val coroutinesVersion: String by project
val kotestVersion: String by project
plugins {
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":climb_stats_common"))
    implementation("io.kotest:kotest-assertions-core:$kotestVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}
tasks.getByName<Test>("test") {
    useJUnitPlatform()
}