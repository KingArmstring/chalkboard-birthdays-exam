package dependencies

object AndroidTestDependencies {

    val kotlin_test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    val test_runner = "androidx.test:runner:${Versions.test_runner}"
    val test_rules = "androidx.test:rules:${Versions.test_runner}"
    val text_core_ktx = "androidx.test:core-ktx:${Versions.test_core}"
    val androidx_test_ext = "androidx.test.ext:junit-ktx:${Versions.androidx_test_ext}"
    val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
}