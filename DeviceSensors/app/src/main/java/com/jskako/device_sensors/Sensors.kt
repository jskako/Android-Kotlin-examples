package com.jskako.device_sensors

import android.content.Context
import android.content.pm.PackageManager.FEATURE_SENSOR_LIGHT
import android.hardware.Sensor.TYPE_LIGHT

class LightSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = FEATURE_SENSOR_LIGHT,
    sensorType = TYPE_LIGHT
)