package com.zambo.activity6

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.System.DATE_FORMAT
import android.provider.Settings.System.TIME_12_24
import android.widget.Toast
import com.zambo.activity6.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    companion object {
        var selectedChoice = "No alert result"
        var selectedDate = "No date selected"
        var selectedTime = "No selected time"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultButton.setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }

        binding.alertDialogButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle("Receive Updates?")
                .setPositiveButton("Yes") { _, _ ->
                    selectedChoice = "Receive updates? Yes"
                    Toast.makeText(this, selectedChoice, Toast.LENGTH_SHORT).show()
                }.setNegativeButton("No") { _, _ ->
                    selectedChoice = "Receive updates? No"
                    Toast.makeText(this, selectedChoice, Toast.LENGTH_SHORT).show()
                }
            alertDialog.show()
        }

        binding.datePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    selectedDate = formatSelectedDate(selectedYear, selectedMonth, selectedDayOfMonth)
                    Toast.makeText(this, "Selected Date: $selectedDate", Toast.LENGTH_SHORT).show()
                },
                year,
                month,
                dayOfMonth
            )

            datePickerDialog.setTitle("Select Date")
            datePickerDialog.show()
        }

        binding.timePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                { _, selectedHourOfDay, selectedMinute ->
                    selectedTime = "$selectedHourOfDay:$selectedMinute"
                    Toast.makeText(this, "Time selected: $selectedTime", Toast.LENGTH_SHORT).show()
                },
                hourOfDay,
                minute,
                true
            )

            timePickerDialog.setTitle("Select Time")
            timePickerDialog.show()
        }

    }
    private fun formatSelectedDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}