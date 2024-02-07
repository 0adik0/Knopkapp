package com.knopkapp.director

import android.content.ContentValues
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Bitmap
import android.graphics.Color
import com.knopkapp.R

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.knopkapp.databinding.FragmentBottomQrCodeGenerationBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class BottomQrCodeGeneration : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomQrCodeGenerationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomQrCodeGenerationBinding.inflate(layoutInflater)
        binding.root.setBackgroundResource(R.drawable.rounded_bottom_sheer)

        binding.closeBtn.setOnClickListener {
            dismiss()
        }

        return binding.root
    }


    override fun onResume() {
        super.onResume()
        val receivedText = requireArguments().getString("key_text", "")

        //getting text from input text field.
        //initializing MultiFormatWriter for QR code
        val mWriter = MultiFormatWriter()
        try {
            //BitMatrix class to encode entered text and set Width & Height
            val mMatrix = mWriter.encode(receivedText, BarcodeFormat.QR_CODE, 400, 400)
            val mEncoder = BarcodeEncoder()
            val mBitmap =
                mEncoder.createBitmap(mMatrix) //creating bitmap of code
            binding.qrImageView.visibility = View.VISIBLE
            binding.qrImageView.setImageBitmap(mBitmap)
            binding.progressBar.visibility = View.GONE
            binding.loadingTextView.visibility = View.GONE
            binding.readyTextView.visibility = View.VISIBLE

            binding.qrCodeDowload.setOnClickListener {
                saveImageToGallery(requireContext(),mBitmap)
            }    //Setting generated QR code to imageView
            // to hide the keyboard
            /* val manager =
                 getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
             manager.hideSoftInputFromWindow(receivedText.applicationWindowToken, 0)*/
        } catch (e: WriterException) {
            e.printStackTrace()
        }


    }

    // Function to save the image to the gallery
    private fun saveImageToGallery(context: Context, bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"
        val imageOutStream: OutputStream

        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            val storageDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "YourAppDirectoryName"
            )

            if (!storageDir.exists()) storageDir.mkdirs()

            val imageFile = File(storageDir, filename)
            imageOutStream = FileOutputStream(imageFile)
        } else {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            val collection =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val imageUri = context.contentResolver.insert(collection, values)
            imageOutStream = context.contentResolver.openOutputStream(imageUri!!)!!
        }

        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageOutStream)
            imageOutStream.flush()
            imageOutStream.close()
            Toast.makeText(context, "QR Code saved to gallery", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Failed to save QR Code", Toast.LENGTH_SHORT).show()
        }
    }
}