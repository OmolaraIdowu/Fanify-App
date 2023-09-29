package com.swancodes.fanify

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.swancodes.fanify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fanControllerView: FanControllerView
    private lateinit var switchButton: SwitchCompat
    private lateinit var fanStateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fanControllerView = binding.fanView
        switchButton = binding.switchButton
        fanStateText = binding.fanStateText

        fanStateText.text = getString(
            R.string.fan_state,
            resources.getString(fanControllerView.getFanSpeed().labelNumber)
        )

        switchButton.setOnCheckedChangeListener { _, isChecked ->
            fanControllerView.setTextMode(isChecked)
            updateFanStateText()
        }

        fanControllerView.setOnClickListener {
            fanControllerView.toggleFanSpeed()
            updateFanStateText()
        }
    }

    private fun updateFanStateText() {
        val fanSpeed = fanControllerView.getFanSpeed()
        val label = if (switchButton.isChecked) {
            resources.getString(fanSpeed.labelWord)
        } else {
            resources.getString(fanSpeed.labelNumber)
        }
        fanStateText.text = getString(R.string.fan_state, label)
    }
}