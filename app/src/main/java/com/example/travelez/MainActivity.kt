package com.example.travelez

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    private var step = 0 // Track the current step/question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        yesButton = findViewById(R.id.yesButton)
        noButton = findViewById(R.id.noButton)

        // Set initial question
        setQuestion(step)

        yesButton.setOnClickListener {
            // Move to the next question on "Yes" button click
            step++
            setQuestion(step)
        }

        noButton.setOnClickListener {
            // Handle "No" button click if needed
        }
    }

    private fun setQuestion(step: Int) {
        when (step) {
            0 -> {
                // First question
                questionTextView.text = "Are you ready to travel?"
            }
            1 -> {
                // Second question
                questionTextView.text = "Beach or Rainforest?"
                // Change button text accordingly
                yesButton.text = "Beach"
                noButton.text = "Rainforest"
            }
            2 -> {
                // Generate and display countries on selecting Beach or Rainforest
                val countries = if (yesButton.text == "Beach") {
                    generateSouthAmericanCountries("Beach")
                } else {
                    generateSouthAmericanCountries("Rainforest")
                }
                displayCountries(countries)
            }
            else -> {
                // End of questions or handle additional steps if needed
                questionTextView.text = "Thank you!"
                yesButton.isEnabled = false
                noButton.isEnabled = false
            }
        }
    }

    private fun generateSouthAmericanCountries(choice: String): List<String> {
        // Generate a list of South American countries based on choice
        val beachCountries = listOf("Brazil", "Colombia", "Uruguay", "Ecuador", "Peru")
        val rainforestCountries = listOf("Brazil", "Colombia", "Peru", "Ecuador", "Bolivia")

        return if (choice == "Beach") {
            beachCountries.shuffled().take(10)
        } else {
            rainforestCountries.shuffled().take(10)
        }
    }

    private fun displayCountries(countries: List<String>) {
        // Display the generated countries
        val countriesString = countries.joinToString("\n")
        questionTextView.text = countriesString
        yesButton.isEnabled = false
        noButton.isEnabled = false
    }
}
