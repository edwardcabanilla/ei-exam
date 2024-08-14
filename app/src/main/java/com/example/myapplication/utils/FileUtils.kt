package com.example.myapplication.utils

import android.app.DownloadManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import com.shockwave.pdfium.PdfDocument
import com.shockwave.pdfium.PdfiumCore
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object FileUtils {

    fun getBitmap(context: Context, file: File?): Bitmap? {
        val pageNum = 0
        val pdfiumCore = PdfiumCore(context)
        try {
            val pdfDocument: PdfDocument = pdfiumCore.newDocument(openFile(file))
            pdfiumCore.openPage(pdfDocument, pageNum)

            val width = pdfiumCore.getPageWidthPoint(pdfDocument, pageNum)
            val height = pdfiumCore.getPageHeightPoint(pdfDocument, pageNum)

            val bitmap = Bitmap.createBitmap(
                width, height,
                Bitmap.Config.RGB_565
            )
            pdfiumCore.renderPageBitmap(
                pdfDocument, bitmap, pageNum, 0, 0,
                width, height
            )
            pdfiumCore.closeDocument(pdfDocument)
            return bitmap
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return null
    }

    private fun openFile(file: File?): ParcelFileDescriptor? {
        val descriptor: ParcelFileDescriptor
        try {
            descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
        return descriptor
    }

    fun downloadFIle(context: Context, file: com.example.myapplication.viewmodels.response.File): File? {

        val fileTest = File(
            context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                .toString() + File.separator + file.fileName
        )
        try {
            if (fileTest.exists()) {
                return fileTest
            } else {
                val downloadManager = context.getSystemService(DownloadManager::class.java)
                val request = DownloadManager.Request(Uri.parse("https:${file.url}"))
                    .setMimeType(file.contentType)
                    .setTitle(file.fileName)
                    .setDescription("Downloading...")
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalFilesDir(
                        context,
                        Environment.DIRECTORY_DOWNLOADS,
                        File.separator + file.fileName
                    )
                downloadManager.enqueue(request)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}