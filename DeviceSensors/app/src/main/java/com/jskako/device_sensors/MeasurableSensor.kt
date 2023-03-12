package com.jskako.device_sensors

abstract class MeasurableSensor(
    protected val sensorType: Int
) {

    protected var onSensorValuesChanges: ((List<Float>) -> Unit)? = null

    abstract val doesSensorExist: Boolean

    abstract fun startListening()
    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(lister: (List<Float>) -> Unit) {
        onSensorValuesChanges = lister
    }
}