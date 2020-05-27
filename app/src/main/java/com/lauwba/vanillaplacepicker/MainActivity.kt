package com.lauwba.vanillaplacepicker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.vanillaplacepicker.presentation.builder.VanillaPlacePicker
import com.vanillaplacepicker.utils.MapType
import com.vanillaplacepicker.utils.PickerType
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vanillaPlacePicker.setOnClickListener {
            val placePicker = VanillaPlacePicker.Builder(this@MainActivity)
                .with(PickerType.MAP_WITH_AUTO_COMPLETE)
                .setMapType(MapType.NORMAL)
                .build()

            startActivityForResult(placePicker, 1052)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            when (requestCode) {
                1052 -> {
                    val vanillaAddress = VanillaPlacePicker.onActivityResult(data)
                    setToImageView(vanillaAddress?.latitude, vanillaAddress?.longitude)
                }
            }
        }
    }

    private fun setToImageView(latitude: Double?, longitude: Double?) {
        val key = getString(R.string.google_key)
        val imageMapsStatic = "http://maps.googleapis.com/maps/api/" +
                "staticmap?zoom=15&" +
                "size=2000x320&" +
                "markers=icon:http://illegal-trade.server411.tech/assets/map-marker-alt-solid.svg" +
                "|$latitude,$longitude&" +
                "key=$key"
        Glide.with(this)
            .load(imageMapsStatic)
            .into(imageMaps)
    }
}
