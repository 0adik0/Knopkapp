package com.knopkapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.knopkapp.adapters.ImagePagerAdapter
import com.knopkapp.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnboardingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var dotsIndicator: DotsIndicator
    private lateinit var imagePagerAdapter: ImagePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.view_pager)
        dotsIndicator = findViewById(R.id.dots_indicator)

        val images = listOf(
            R.drawable.image1,
            R.drawable.image1,
            R.drawable.image1
        )

        imagePagerAdapter = ImagePagerAdapter(this, images)
        viewPager.adapter = imagePagerAdapter
        dotsIndicator.setViewPager(viewPager)

        findViewById<Button>(R.id.buttonContinueonboarding).setOnClickListener {
            if (viewPager.currentItem < images.size - 1) {
                viewPager.currentItem = viewPager.currentItem + 1
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}