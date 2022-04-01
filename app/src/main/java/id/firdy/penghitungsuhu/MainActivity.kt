package id.firdy.penghitungsuhu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var etTemperatureResult: EditText
    private lateinit var spnTemperature: Spinner
    private lateinit var spnTemperatureResult: Spinner

    private var selectedTemperature = "C"
    private var selectedTemperatureResult = "C"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView(){
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Suhu Converter"

        spnTemperature = findViewById(R.id.spn_temperature)
        spnTemperatureResult = findViewById(R.id.spn_temperature_result)
        etTemperatureResult = findViewById(R.id.et_temperature_result)
        val etTemperature = findViewById<EditText>(R.id.et_temperature)

        val listTemperature = insertTemperature()
        val temperatureAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listTemperature.map{
            it.name
        })
        spnTemperature.apply{
            adapter = temperatureAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedTemperature = listTemperature[p2].code
                    convert(etTemperature.text.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
        spnTemperatureResult.apply {
            adapter = temperatureAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    selectedTemperatureResult = listTemperature[p2].code
                    convert(etTemperature.text.toString())
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        val btnSubmit = findViewById<Button>(R.id.btn_submit)
        btnSubmit.setOnClickListener {
            convert(etTemperature.text.toString())
        }
    }

    private fun convert(temperature: String){
        if(temperature.isNotEmpty()){
            val temperatureDouble = temperature.toDouble()
            val resultTemperature: Double
            val converter = Converter()
            // Celcius
            if (selectedTemperature == "C" && selectedTemperatureResult == "F"){
                resultTemperature = converter.celciusToFahrenheit(temperatureDouble)
            }else if(selectedTemperature == "C" && selectedTemperatureResult == "K"){
                resultTemperature = converter.celciusToKelvin(temperatureDouble)
            }else if(selectedTemperature == "C" && selectedTemperatureResult == "C"){
                resultTemperature = temperatureDouble
            }
            // Fahrenheit
            else if(selectedTemperature == "F" && selectedTemperatureResult == "C"){
                resultTemperature = converter.fahrenheitToCelcius(temperatureDouble)
            }else if(selectedTemperature == "F" && selectedTemperatureResult == "K"){
                resultTemperature = converter.fahrenheitToKelvin(temperatureDouble)
            }else if(selectedTemperature == "F" && selectedTemperatureResult == "F"){
                resultTemperature = temperatureDouble
            }
            // Kelvin
            else if(selectedTemperature == "K" && selectedTemperatureResult == "C"){
                resultTemperature = converter.kelvinToCelcius(temperatureDouble)
            }
            else if(selectedTemperature == "K" && selectedTemperatureResult == "F"){
                resultTemperature = converter.kelvinToFahrenheit(temperatureDouble)
            }else{
                resultTemperature = temperatureDouble
            }
            etTemperatureResult.setText(resultTemperature.toBigDecimal().toPlainString())
        }
    }

    private fun insertTemperature() : List<Temperature>{
        val listTemperature: MutableList<Temperature> = mutableListOf()
        listTemperature.add(Temperature(
            "C",
            "Celcius"
        ))
        listTemperature.add(Temperature(
            "F",
            "Fahrenheit"
        ))
        listTemperature.add(Temperature(
            "K",
            "Kelvin"
        ))
        return listTemperature
    }

    data class Temperature(
        var code: String,
        var name: String
    )
}