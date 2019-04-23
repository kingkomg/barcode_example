package com.pany.books

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.vision.Frame
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector


class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val barcodeDetector = BarcodeDetector.Builder(applicationContext)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.barcode)

        val textView = findViewById<TextView>(R.id.textView)
        findViewById<Button>(R.id.button).setOnClickListener {
            val frame = Frame.Builder().setBitmap(bitmap).build()
            val barcodes = barcodeDetector.detect(frame)
            if(barcodes.size() > 0) {
                textView.text = barcodes.valueAt(0).rawValue
            } else {
                textView.text = "no barcodes found"
            }
        }

        findViewById<ImageView>(R.id.imgview).setImageBitmap(bitmap)

    }

}
