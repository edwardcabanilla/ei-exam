package com.example.myapplication.viewmodels.ui

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.utils.FileUtils.downloadFIle
import com.example.myapplication.utils.FileUtils.getBitmap
import com.example.myapplication.viewmodels.ActivitiesViewModel
import com.example.myapplication.viewmodels.response.Activities

@Composable
fun ImageBuilder(
    context: Context,
    activity: Activities,
    openedDocument: List<String>,
    viewModel: ActivitiesViewModel,
    bitmapHash: HashMap<String, Bitmap>
) {
    val file = activity.icon.file
    ConstraintLayout(
        modifier = Modifier
            .wrapContentSize()
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                viewModel.setOpenedDocument(activity.id)
            }
    ) {
        val (icon, image, text) = createRefs()
        val bitmap = if (bitmapHash.containsKey(file.fileName)) {
            bitmapHash[file.fileName]
        } else {
            val fileDownloaded = downloadFIle(context , file)
            fileDownloaded?.let {
                val bit = getBitmap(context, fileDownloaded)
                bit?.let { viewModel.setOpenedDocument(file.fileName, it) }
                bit
            }
        }

        bitmap?.let {
            Image(
                painter = rememberAsyncImagePainter(bitmap),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp, 150.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(image.top, margin = 10.dp)
                    end.linkTo(image.end)
                },
            visible = openedDocument.contains(activity.id)
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp, 40.dp),
                painter = painterResource(id = R.drawable.opened),
                contentDescription = "Translated description of what the image contains"
            )
        }

        Text(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(130.dp)
                .constrainAs(text) {
                    top.linkTo(image.bottom, margin = 10.dp)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                },
            text = activity.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Black
        )
    }
}