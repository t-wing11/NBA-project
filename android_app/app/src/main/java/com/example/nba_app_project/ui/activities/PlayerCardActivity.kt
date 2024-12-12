package com.example.nba_app_project.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nba_app_project.data.PlayerItem
import com.example.nba_app_project.databinding.PlayerCardBinding

class PlayerCardActivity: AppCompatActivity() {

    private lateinit var binding: PlayerCardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerCardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle : Bundle? = intent.extras
        val playerItem : PlayerItem? = bundle?.getParcelable("player")
        if (playerItem != null) {
            updatePlayer(playerItem)
        }
    }

    private fun updatePlayer(playerItem: PlayerItem){
        binding.fullName.text = playerItem.first_name+ playerItem.last_name
        binding.number.text = playerItem.number.toString()
        binding.position.text = playerItem.position

    }


}