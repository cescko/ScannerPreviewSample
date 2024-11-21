# ScannerPreviewSample
Sample project used to replicate a bug that occurred while recording the test screenshot with the `ComposablePreviewScanner library`.
1. Run screenshot registration.
```
./gradlew app:testDebugUnitTest -Proborazzi.test.record=true
```
2. Modify `GreetingPreview` to make the test fail.
3. Run  screenshot verification.
```
./gradlew app:testDebugUnitTest -Proborazzi.test.verify=true
```
4. Delete the `*_actual.png` and `*_compare.png` images from `build/outputs/roborazzi` folder.
5. From this moment on, every time the command for recording screenshots is executed, images for comparison will also be generated in the output folder.

## Note
If you remove the Compose preview test screenshot generation feature `generateComposePreviewRobolectricTests` from `build.gradle.kts` and run the `PreviewScannerParameterizedTests` test, comparison images will not be saved during execution for recording test screenshots and everything will work as expected. feature `generateComposePreviewRobolectricTests` from `build.gradle.kts` and run the `PreviewScannerParameterizedTests` test, comparison images will not be saved during execution for recording test screenshots and everything will work as expected.