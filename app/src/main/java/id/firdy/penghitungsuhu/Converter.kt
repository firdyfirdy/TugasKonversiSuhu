package id.firdy.penghitungsuhu

class Converter {

    fun kelvinToCelcius(i: Double): Double = i - 273.15

    fun kelvinToFahrenheit(i: Double): Double = (i - 273.15) * 9/5 + 32

    fun fahrenheitToCelcius(f: Double) = 5/9 * (f - 32)

    fun fahrenheitToKelvin(f: Double) = (f - 32) * 5/9 + 273.15

    fun celciusToKelvin(i: Double) = i + 273.15

    fun celciusToFahrenheit(c: Double) = (9/5.0 * c) + 32
}