package com.olivia.imagechatbubbleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.olivia.imagebundlelibrary.ImageBundle
import com.olivia.imagechatbubbleapp.ui.theme.ImageChatBubbleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageChatBubbleAppTheme {
                val imageList = listOf(
                    "https://cdn.pixabay.com/photo/2017/02/15/13/40/tulips-2068692_1280.jpg",
                    "https://cdn.pixabay.com/photo/2018/10/01/09/21/pets-3715733_1280.jpg",
                    "https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_1280.jpg",
                    "https://cdn.pixabay.com/photo/2017/07/25/01/22/cat-2536662_1280.jpg",
                    "https://cdn.pixabay.com/photo/2016/01/05/17/51/maltese-1123016_1280.jpg"
                )
                Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        //show one image
                        ImageBundle(
                            images = listOf(
                                imageList.random()
                            )
                        ) {}

                        //show two images
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random()
                            )
                        ) {}

                        //show three images
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random(), imageList.random()
                            )
                        ) {}

                        //show four images
                        ImageBundle(
                            images = listOf(
                                imageList.random(),
                                imageList.random(),
                                imageList.random(),
                                imageList.random()
                            )
                        ) {}

                        //show five images
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(),
                                imageList.random()
                            )
                        ) {}
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        //show a lot of images
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random()
                            )
                        ) {}
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random()
                            )
                        ) {}
                        ImageBundle(
                            images = listOf(
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random(), imageList.random(),
                                imageList.random(), imageList.random()
                            )
                        ) {}
                    }
                }
            }
        }
    }
}