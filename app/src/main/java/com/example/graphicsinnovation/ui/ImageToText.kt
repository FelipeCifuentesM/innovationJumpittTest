package com.example.graphicsinnovation.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.graphicsinnovation.R
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText
import kotlinx.android.synthetic.main.image_to_text.*

class ImageToText : AppCompatActivity() {

    lateinit var ocrImage: ImageView
    lateinit var resultEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_to_text)

        resultEditText = findViewById(R.id.ocrResultEt)
        ocrImage = findViewById(R.id.ocrImageView)

        //set an onclick listener on the button to trigger the @pickImage() method
        selectImageBtn.setOnClickListener{
            pickImage()
        }

        //set an onclick listener on the button to trigger the @processImage method
        processImageBtn.setOnClickListener{
            processImage(processImageBtn)
        }

        btnCapturePhoto.setOnClickListener {
            pickPhoto()
        }

    }


    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }

    private fun pickPhoto(){
        val REQUEST_IMAGE_CAPTURE = 2
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            ocrImage.setImageURI(data!!.data)
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            ocrImageView.setImageBitmap(imageBitmap)
        }
    }

    fun processImage(v: View) {
        if (ocrImage.drawable != null) {
            resultEditText.setText("")
            v.isEnabled = false
            val bitmap = (ocrImage.drawable as BitmapDrawable).bitmap
            val image = FirebaseVisionImage.fromBitmap(bitmap)
            val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

            detector.processImage(image)
                .addOnSuccessListener { firebaseVisionText ->
                    v.isEnabled = true
                    processResultText(firebaseVisionText)
                }
                .addOnFailureListener {
                    v.isEnabled = true
                    resultEditText.setText("Failed")
                }
        } else {
            Toast.makeText(this, "Select an Image First", Toast.LENGTH_LONG).show()
        }

    }


    private fun processResultText(resultText: FirebaseVisionText) {
        if (resultText.textBlocks.size == 0) {
            resultEditText.setText("No Text Found")
            return
        }
        for (block in resultText.textBlocks) {
            val blockText = block.text
            resultEditText.append(blockText + "\n")
        }
    }
}
