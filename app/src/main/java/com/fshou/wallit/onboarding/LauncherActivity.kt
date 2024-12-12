package com.fshou.wallit.onboarding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fshou.wallit.databinding.ActivityLauncherBinding

class LauncherActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLauncherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
    }
}