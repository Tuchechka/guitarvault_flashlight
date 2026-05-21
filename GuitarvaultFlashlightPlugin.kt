package com.example.guitarvault_flashlight

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class GuitarvaultFlashlightPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var context: Context
    private var isOn: Boolean = false

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(binding.binaryMessenger, "guitarvault_flashlight")
        channel.setMethodCallHandler(this)
        context = binding.applicationContext
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        try {
            val cameraId = findFlashCameraId(cameraManager)
            if (cameraId == null) {
                result.error("NO_FLASH", "Device has no flash unit", null)
                return
            }
            when (call.method) {
                "on" -> {
                    cameraManager.setTorchMode(cameraId, true)
                    isOn = true
                    result.success(null)
                }
                "off" -> {
                    cameraManager.setTorchMode(cameraId, false)
                    isOn = false
                    result.success(null)
                }
                "toggle" -> {
                    isOn = !isOn
                    cameraManager.setTorchMode(cameraId, isOn)
                    result.success(isOn)
                }
                else -> result.notImplemented()
            }
        } catch (e: Exception) {
            result.error("FLASHLIGHT_ERROR", e.message ?: "Unknown error", null)
        }
    }

    private fun findFlashCameraId(manager: CameraManager): String? {
        for (id in manager.cameraIdList) {
            val available = manager.getCameraCharacteristics(id)
                .get(CameraCharacteristics.FLASH_INFO_AVAILABLE)
            if (available == true) return id
        }
        return null
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
