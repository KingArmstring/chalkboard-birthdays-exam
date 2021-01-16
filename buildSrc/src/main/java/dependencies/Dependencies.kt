package dependencies

object Dependencies {
    val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    val http_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_http_logging_interceptor}"
    val square_okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.square_okhttp3}"
    val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
}