# ChatImageBundle

- RoundedCornerShape 가 적용된 카카오톡 묶음 이미지 말풍선을 compose 로 구현
- Development of Kakao Talk Bundle Image with Compose ui RoundedCorner Shape

## Overview

- 묶음 이미지에 대하여 모서리에 위치하는 이미지에 대해서만 BIG_RADIUS 적용 그 외의 이미지에 대해서는 SMALL_RADIUS 적용 
- 전체 이미리 리스트를 3개씩 묶은 후 나머지 값에 대해서 2개로 묶는 ui 구현
	- **즉 이미지 리스트를 3으로 나눈 나머지가 0이거나 2인 조합을 찾아야 함 (하단 preview 참조)** 
- 1장 일 경우 -> wrapContent && maxWidth = 250.dp
- 2장, 4장 묶음 일 경우 -> height = 124.dp, width = 250.dp  
- 3장, 5~N장 묶음 일 경우 -> height = 80.dp, width = 250.dp 
- 행에 3개씩 배치하며 

## Preview

|이미지 갯수|표시 화면|
|---|---|
|1장|<img src="https://user-images.githubusercontent.com/59822833/164371127-afbba604-1c2b-44a0-ba3c-e7f31ad91d36.jpg" width="200" height="200"/>|
|2장|<img src="https://user-images.githubusercontent.com/59822833/164371729-42ca3846-4946-423f-8c2a-6baf72f883de.jpg" width="200" height="200"/>|
|3장|<img src="https://user-images.githubusercontent.com/59822833/164371738-39619e6f-03ca-4e39-b09f-f5b831ff7db3.jpg" width="200" height="200"/>|
|4장|<img src="https://user-images.githubusercontent.com/59822833/164371742-705cb4d8-14d8-47dd-983a-f5991679166e.jpg" width="200" height="200"/>|
|n장|<img src="https://user-images.githubusercontent.com/59822833/165042875-64f645e6-075e-4e2c-b414-2c97e9291ec5.jpg" width="200" height="300"/>|

전체 동영상

https://user-images.githubusercontent.com/59822833/164371968-324edcaa-4d89-4a1e-92f1-29571a751180.mp4


## Usage

Step1.Add the JitPack repository to your build file

a. Arctic Fox 이전 버전 (root: build.gradle)

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
   
a. Arctic Fox 이후 버전  (setting.gradle)  
   
   	dependencyResolutionManagement { 
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories { 
			...
			maven { url "https://jitpack.io" }
		} 
	}

	
Step2. Add the dependency -> Check Latest Version [https://jitpack.io/olivia-soy/chat-image-bundle](https://jitpack.io/#olivia-soy/chat-image-bundle)

	dependencies {
	        implementation 'com.github.olivia-soy:chat-image-bundle:1.0.6'
	}
  
Step3. Use "ImageBundle" Composable -> Check [sample source](sample/src/main/java/com/olivia/imagechatbubbleapp/MainActivity.kt)

## Description

- UI
  - [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
  - [Material design](https://material.io/design)
- Tech/Tools
  - [Kotlin](https://kotlinlang.org/) 100% coverage
  - Jetpack
    - [Compose](https://developer.android.com/jetpack/compose)
    - [Coil](https://coil-kt.github.io/coil/compose/) image loader
