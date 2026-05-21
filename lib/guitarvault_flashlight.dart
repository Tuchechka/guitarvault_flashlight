import 'package:flutter/services.dart';

/// GuitarVault flashlight plugin.
///
/// Provides a thin Dart bridge over a platform-native torch implementation.
/// Native logic is implemented only for Android. On other platforms calls
/// will throw [MissingPluginException], which the caller should handle.
class GuitarvaultFlashlight {
  static const MethodChannel _channel = MethodChannel('guitarvault_flashlight');

  /// Toggles the flashlight. Returns the new state (true = on).
  static Future<bool> toggle() async {
    final result = await _channel.invokeMethod<bool>('toggle');
    return result ?? false;
  }

  /// Turns the flashlight on.
  static Future<void> turnOn() async {
    await _channel.invokeMethod('on');
  }

  /// Turns the flashlight off.
  static Future<void> turnOff() async {
    await _channel.invokeMethod('off');
  }
}
