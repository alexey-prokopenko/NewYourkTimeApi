package com.example.new_yourk_times_api

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.new_yourk_times_api.databinding.SplashScreenBinding

class SplashScreen: AppCompatActivity(){

    private lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val newsAnimation = AnimationUtils.loadAnimation(this, R.anim.news_animation)
        val andAnimation = AnimationUtils.loadAnimation(this, R.anim.and_animation)
        val booksAnimation = AnimationUtils.loadAnimation(this, R.anim.books_animation)
        with(binding) {
            NewsTextView.startAnimation(newsAnimation)
            AndTextView.startAnimation(andAnimation)
            BooksTextView.startAnimation(booksAnimation)

            val timeOut = 5000
            val intent = Intent(this@SplashScreen, MainActivity::class.java)

            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(intent)
                finish()
            }, timeOut.toLong())
        }
    }
}