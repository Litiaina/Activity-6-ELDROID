package com.zambo.activity6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zambo.activity6.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alertDialogResult.text = MainActivity.selectedChoice
        binding.datePickerResult.text = MainActivity.selectedDate
        binding.timePickerResult.text = MainActivity.selectedTime

        binding.backButton.setOnClickListener {
            finish()
        }

    }
}