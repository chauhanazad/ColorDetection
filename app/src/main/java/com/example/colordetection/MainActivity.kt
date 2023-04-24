package com.example.colordetection

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.example.colordetection.databinding.ActivityMainBinding
import java.lang.String

class MainActivity : AppCompatActivity(){

    lateinit var binding: ActivityMainBinding

    private val someActivityResultLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result?.resultCode == Activity.RESULT_OK) {
            val data: Bitmap = result.data?.extras?.get("data") as Bitmap
            binding.img.setImageBitmap(data)
            createPaletteAsync(data)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            val pickIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (pickIntent.resolveActivity(packageManager) != null) {
                someActivityResultLauncher.launch(pickIntent)
            } else {
                Toast.makeText(this, "null", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            if (palette?.lightVibrantSwatch!=null) {
                binding.lightVibrant.apply {
                    val r = Color.red(palette.lightVibrantSwatch!!.rgb)
                    val g = Color.green(palette.lightVibrantSwatch!!.rgb)
                    val b = Color.blue(palette.lightVibrantSwatch!!.rgb)
                    val hex = String.format("#%02x%02x%02x", r, g, b)
                    setBackgroundColor(palette?.lightVibrantSwatch!!.rgb)
                    setText("$hex")
                }
            }
            if (palette?.vibrantSwatch!=null) {
                binding.vibrant.apply {
                    val r = Color.red(palette.vibrantSwatch!!.rgb)
                    val g = Color.green(palette.vibrantSwatch!!.rgb)
                    val b = Color.blue(palette.vibrantSwatch!!.rgb)
                    val hex = String.format("#%02x%02x%02x", r, g, b)
                    setBackgroundColor(palette?.vibrantSwatch!!.rgb)
                    setText("$hex")
                }
            }

            binding.lightMuted.apply {
                if(palette?.lightVibrantSwatch != null ){
                    if (palette?.lightMutedSwatch!=null) {
                        val r = Color.red(palette.lightMutedSwatch!!.rgb)
                        val g = Color.green(palette.lightMutedSwatch!!.rgb)
                        val b = Color.blue(palette.lightMutedSwatch!!.rgb)
                        val hex = String.format("#%02x%02x%02x", r, g, b)
                        setBackgroundColor(palette.lightMutedSwatch!!.rgb)
                        setText("$hex")
                    }
                }
                else{
                    setBackgroundColor(Color.GRAY)
                }
            }

            binding.muted.apply {
                if (palette?.mutedSwatch!=null) {
                    val r = Color.red(palette?.mutedSwatch!!.rgb)
                    val g = Color.green(palette?.mutedSwatch!!.rgb)
                    val b = Color.blue(palette?.mutedSwatch!!.rgb)
                    val hex = String.format("#%02x%02x%02x", r, g, b)
                    setBackgroundColor(palette?.mutedSwatch!!.rgb)
                    setText("$hex")
                }
            }

            binding.darkMuted.apply {
                if (palette?.darkMutedSwatch!=null) {
                    val r = Color.red(palette?.darkMutedSwatch!!.rgb)
                    val g = Color.green(palette.darkMutedSwatch!!.rgb)
                    val b = Color.blue(palette.darkMutedSwatch!!.rgb)
                    val hex = String.format("#%02x%02x%02x", r, g, b)
                    setBackgroundColor(palette?.darkMutedSwatch!!.rgb)
                    setText("$hex")
                }
            }

            binding.darkVibrant.apply {
                if (palette?.darkVibrantSwatch!=null) {
                    val r = Color.red(palette?.darkVibrantSwatch!!.rgb)
                    val g = Color.green(palette.darkVibrantSwatch!!.rgb)
                    val b = Color.blue(palette.darkVibrantSwatch!!.rgb)
                    val hex = String.format("#%02x%02x%02x", r, g, b)
                    setBackgroundColor(palette?.darkVibrantSwatch!!.rgb)
                    setText("$hex")
                }
            }
        }
    }
}