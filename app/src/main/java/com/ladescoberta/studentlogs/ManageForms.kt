package com.ladescoberta.studentlogs

import android.app.Activity
import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import kotlinx.serialization.Serializable
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

private const val TAG = "ManageForms"

@Serializable
object ManageForms

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ManageFormsScreen(
    onDone: () -> Unit
) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "manage forms")
        Button(modifier = Modifier.fillMaxWidth()
            .padding(20.dp),
            onClick = {
                generatePDF(context)
            }) {
            Text(text = "generate report")
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
fun generatePDF(context: Context){
    var pageHeight = 1120
    var pageWidth = 792
    lateinit var bmp: Bitmap
    lateinit var scaledbmp: Bitmap

    var pdfDocument: PdfDocument = PdfDocument()

    var paint: Paint = Paint()
    var title: Paint = Paint()

    bmp = BitmapFactory.decodeResource(context.resources, R.drawable.service_encounter_verification_form)
    scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false)

    var myPageInfo: PdfDocument.PageInfo? = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

    var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

    var canvas: Canvas = myPage.canvas

    canvas.drawBitmap(scaledbmp, 56F, 40F, paint)

    title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))
    title.textSize = 15F

    title.setColor(ContextCompat.getColor(context, R.color.purple_200))

    canvas.drawText("First test of filling out", 209F, 100F, title)
    canvas.drawText("a form for sunny days!", 209F, 80F, title)

    title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
    title.setColor(ContextCompat.getColor(context, R.color.purple_200))

    title.textSize = 15F

    title.textAlign = Paint.Align.CENTER
    canvas.drawText("did it work?", 396F, 560F, title)

    pdfDocument.finishPage(myPage)

    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply{
        put(MediaStore.MediaColumns.DISPLAY_NAME, "sunnydays.pdf")
        put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
    }

    val uri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
    if(uri != null){
        var outputStream: OutputStream? = null
        try{
            outputStream = contentResolver.openOutputStream(uri)
            pdfDocument.writeTo(outputStream)
            Toast.makeText(context, "file created!", Toast.LENGTH_LONG).show()
        }
        catch( e: Exception ){
            e.printStackTrace()
            Toast.makeText(context, "failed to generate file", Toast.LENGTH_LONG).show()
        }
        finally{
            outputStream?.close()
        }
    }

    pdfDocument.close()
}