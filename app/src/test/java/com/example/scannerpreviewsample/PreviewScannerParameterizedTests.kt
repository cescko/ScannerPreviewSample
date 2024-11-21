package com.example.scannerpreviewsample

import com.github.takahirom.roborazzi.ExperimentalRoborazziApi
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import com.github.takahirom.roborazzi.roborazziDefaultNamingStrategy
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import sergio.sastre.composable.preview.scanner.android.AndroidComposablePreviewScanner
import sergio.sastre.composable.preview.scanner.android.AndroidPreviewInfo
import sergio.sastre.composable.preview.scanner.android.screenshotid.AndroidPreviewScreenshotIdBuilder
import sergio.sastre.composable.preview.scanner.core.preview.ComposablePreview

@Ignore("To run after removing generateComposePreviewRobolectricTests configuration from build.gradle.kts")
@RunWith(ParameterizedRobolectricTestRunner::class)
@OptIn(ExperimentalRoborazziApi::class)
class PreviewScannerParameterizedTests(
    private val preview: ComposablePreview<AndroidPreviewInfo>
) {

    @GraphicsMode(GraphicsMode.Mode.NATIVE)
    @Config(sdk = [33], qualifiers = RobolectricDeviceQualifiers.Pixel4a)
    @Test
    fun snapshot() {
        val name = roborazziDefaultNamingStrategy().generateOutputName(
            preview.declaringClass,
            AndroidPreviewScreenshotIdBuilder(preview).ignoreClassName().build()
        )

        preview.captureRoboImage("$OUTPUT_SNAPSHOT_PATH/$name.png")
    }

    companion object {
        private const val OUTPUT_SNAPSHOT_PATH = "src/test/resources/snapshots"
        private const val PACKAGE_TO_SCAN = "com.example.scannerpreviewsample"

        // Optimization: This avoids scanning for every test
        private val cachedPreviews: List<ComposablePreview<AndroidPreviewInfo>> by lazy {
            AndroidComposablePreviewScanner()
                .scanPackageTrees(PACKAGE_TO_SCAN)
                .includePrivatePreviews()
                .getPreviews()
        }

        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun values(): List<ComposablePreview<AndroidPreviewInfo>> = cachedPreviews
    }
}