package com.olivia.imagebundlelibrary

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/**
 * # Image Bundle Library
 * @author LEESOYOUNG
 * @since 2022-04-18
 */


/**
 * # ImageBundle
 *
 * @param modifier      ImageBundle modifier
 * @param images        ImageBundle imageUrl List
 * @param onClickItem   ImageBundle onClick listener
 */
@Composable
fun ImageBundle(
    modifier: Modifier = Modifier,
    images: List<String>,
    onClickItem: (String) -> Unit
) {
    if (images.size == 1) {
        Box(
            modifier = modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(BIG_RADIUS.dp)).size(MAX_WIDTH.dp)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(BIG_RADIUS.dp)
                )
        ) {
            ImageSection(
                imageUrl = images.first(),
                onClick = { onClickItem(images.first()) },
                contentDescription = "ImageSection"
            )
        }
    } else {
        val sectionCount = getImageSectionCount(images.size)
        val imageSections = getImageSections(images, sectionCount)

        //2장, 4장 섹션일 경우 height 값 BIG_HEIGHT 아닐 경우 height 값 SMALL_HEIGHT
        val height = if (imageSections.first().size == 2) BIG_HEIGHT.dp else SMALL_HEIGHT.dp
        val space = 2.dp
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(space)
        ) {
            imageSections.forEachIndexed { index, list ->
                Row(
                    modifier = Modifier.size(
                        width = MAX_WIDTH.dp,
                        height = height
                    ).clip(
                        setRoundedCornerShape(index, imageSections.size)
                    ),
                    horizontalArrangement = Arrangement.spacedBy(space)
                ) {
                    list.forEach { imageUrl ->
                        ImageSection(
                            modifier = Modifier.clip(RoundedCornerShape(SMALL_RADIUS)).weight(1f),
                            imageUrl = imageUrl,
                            contentDescription = "ImageSection",
                            onClick = { onClickItem(imageUrl) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * AsyncImage Composable
 *
 * @param modifier              AsyncImage modifier
 * @param onClick               AsyncImage onClick listener
 * @param imageUrl               AsyncImage model
 * @param contentDescription    AsyncImage contentDescription
 */
@Composable
private fun ImageSection(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageUrl: String,
    contentDescription: String
) {
    AsyncImage(
        modifier = modifier.clickable {
            onClick()
        },
        contentScale = ContentScale.Crop,
        model = imageUrl,
        contentDescription = contentDescription
    )
}


/**
 * row 의 RoundedCornerShape 값 계산
 *
 * @param index     현재 아이템 index 값
 * @param listSize  전체 리스트 사이즈
 * @return RoundedCornerShape
 */
private fun setRoundedCornerShape(index: Int, listSize: Int): RoundedCornerShape = when {
    /**
     * 첫번째 아이템 이면서 마지막 아이템 일 경우
     * ALL_ROUNDED_TYPE
     */
    index == 0 && index.inc() == listSize -> {
        RoundedCornerShape(
            BIG_RADIUS.dp,
            BIG_RADIUS.dp,
            BIG_RADIUS.dp,
            BIG_RADIUS.dp
        )
    }
    /**
     * 첫번째 아이템 일 경우
     * TOP_ROUNDED_TYPE
     */
    index == 0 && index.inc() != listSize -> {
        RoundedCornerShape(
            BIG_RADIUS.dp,
            BIG_RADIUS.dp,
            SMALL_RADIUS.dp,
            SMALL_RADIUS.dp
        )
    }
    /**
     * 마지막 아이템 일 경우
     * BOTTOM_ROUNDED_TYPE
     */
    index.inc() == listSize -> {
        RoundedCornerShape(
            SMALL_RADIUS.dp,
            SMALL_RADIUS.dp,
            BIG_RADIUS.dp,
            BIG_RADIUS.dp
        )
    }
    /**
     * 중간 아이템 일 경우
     * NO_ROUNDED_TYPE
     */
    else -> {
        RoundedCornerShape(
            SMALL_RADIUS.dp,
            SMALL_RADIUS.dp,
            SMALL_RADIUS.dp,
            SMALL_RADIUS.dp
        )
    }
}

/**
 * row 당 이미지 갯수 계산
 *
 * 전체 리스트를 3개씩 묶은 후 나머지 값에 대해서 2로 묶을 때
 * 3개씩 묶는 최대 값 찾는 동작
 *
 * @param listSize 나눠지게 될 리스트 사이즈
 * @return pair(threeSectionCount, twoSectionCount)
 *
 * threeSectionCount  = row 당 3개짜리 이미지 갯수
 * twoSectionCount  = row 당 2개짜리 이미지 갯수
 */
private fun getImageSectionCount(listSize: Int): Pair<Int, Int> {
    val div = listSize.div(3) // 리스트 사이즈 를 3으로 나눈 몫 == threeSectionCount 최대값
    var threeSectionCount = 0
    var twoSectionCount = 0
    for (i in div downTo 0) {   // 3개씩 묶는 최대 값을 찾아야 하므로 downTo 동작 진행

        /**
         * 리스트 사이즈 에서 3*i을 뺀 나머지 값을 2로 나눴을 때 나머지 값이 0인지 확인
         * true ->
         *  threeSectionCount == i
         *  twoSectionCount == 리스트 사이즈 에서 3*i을 뺀 나머지 값을 2 나눈 몫
         * false -> 하나씩 줄여가면서 확인
         */
        if ((listSize - (3 * i)).rem(2) == 0) {
            threeSectionCount = i
            twoSectionCount = (listSize - (3 * i)).div(2)
            break
        }
    }
    return Pair(threeSectionCount, twoSectionCount)
}

/**
 * row 당 3개 이미지, 2개 이미지로 묶은 후 하나의 리스트를 생성
 *
 * @param imageInfoList imageInfo list
 * @return pair getImageSectionCount()
 * @return List<List<ImageInfo>>
 *
 * pair.first == threeSectionCount
 *  -> row 당 3개짜리 이미지 갯수
 * pair.second === twoSectionCount
 *  -> row 당 2개짜리 이미지 갯수
 */
private fun getImageSections(
    imageInfoList: List<String>,
    pair: Pair<Int, Int>
): List<List<String>> {
    /**
     * imageInfoList 를 앞에서 부터 threeSectionCount*3 만큼 자른 후(take) chunked 를 통해 3개씩 묶음
     */
    val threeSectionList = imageInfoList.take(pair.first * 3).chunked(3)

    /**
     * imageInfoList 를 뒤에서 부터 twoSectionCount*2 만큼 자른 후(take) chunked 를 통해 2개씩 묶음
     */
    val twoSectionList = imageInfoList.takeLast(pair.second * 2).chunked(2)
    val allSectionList = arrayListOf<List<String>>() //전체 리스트 에 각각의 리스트 주입
    if (threeSectionList.isNotEmpty()) {
        threeSectionList.forEach {
            allSectionList.add(it)
        }
    }
    if (twoSectionList.isNotEmpty()) {
        twoSectionList.forEach {
            allSectionList.add(it)
        }
    }
    return allSectionList
}

private const val SMALL_RADIUS = 4
private const val BIG_RADIUS = 20
private const val SMALL_HEIGHT = 80
private const val BIG_HEIGHT = 124
private const val MAX_WIDTH = 250