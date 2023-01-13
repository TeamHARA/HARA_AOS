# 🤍 HARA-AOS 🤍

> 해라, THE SOPT 31TH 앱잼 Android팀 <br>
2023.01.02 - 2023.01.14

<br>

## 프로덕트 이름
해라(HARA)

<br>

## 해라의 목표
'고민', '선택' ,'결정'이 필요한 순간, 떠오르는 프로덕트 해라가 되겠습니다.

<br>

## 해라의 핵심가치
Enjoy, think, solve
- 스스로 결정하는 힘을 기를 수 있습니다.
- 자기주도적인 회고를 활용할 수 있으면서도, 같이 고민하는 즐거움을 느낄 수 있습니다.

<br>

## 해라의 궁극적인 목표
풍요로운 선택지 속에서 진짜 내가 원하는 것을 발견할 수 있는, 스스로의 힘을 믿어주는 서비스가 되고자 합니다.

<br>

## 아키텍처 및 프로젝트 구조

- 안드로이드에서 제공한 기본 빌드구조인 Groovy 형태의 build.gradle 빌드를 하였습니다.
- Git Action을 활용한 yaml기반 CI를 구축하여 Git에서의 협업과정에서 코드의 안정성을 높혔습니다. Push와 PullRequest에 자동적으로 검사를 하도록 설정하였으며, Slack WebHook을 통하여 팀원들이 모두 CI 결과를 공유받을 수 있도록 설정하였습니다.
- 프로젝트구조 :
    - 기본적으로 data-domain-presentation의 구조로 패키지를 나누었습니다.
    - 가능한 Mvvm구조를 채택하려고 하였으며, 필요에 따라 액티비티, 프래그먼트마다 생성위임자를 통한 ViewModel을 사용해주었습니다. 또한 최대한 Class에서의 로직을 분리하기 위해서 DataBinding과 BindingAdapter를 활용 하였습니다.
    - Service객체 / Repository의 의존성이 필요한 부분은 Hilt-dagger기반의 모듈을 활용하여 의존성 각각 Repository / ViewModel에
    - Local Properties를 활용하여 개인 데이터를 관리하였습니다.

## 기술스택

- CustomView
    - BottomSheetDialog
    - Picker
- 의존성 주입
- 서버통신
    - Retrofit2
        - Okhttp3
        - HttpInterceptor
    - Kotlinx Serialization
- Coroutine을 통하 비동기 동작
- LiveData를 활용한 반응형 UI
- ListAdapter

### 안드로이드 JetPack Library

- ViewBinding
- DataBinding
- ViewModel
- Coroutine
- Navigation Graph

### 서드파티 라이브러리

- Timber
- skydoves:progressview
    
    [GitHub - skydoves/ProgressView: 🌊 A polished and flexible ProgressView, fully customizable with animations.](https://github.com/skydoves/ProgressView)

## Contributors

| 김준우 [@IslandOfDream](https://github.com/IslandOfDream) | 장유진 [@wkdyujin](https://github.com/wkdyujin) | 이수현 [@skylartosf](https://github.com/skylartosf) |
| :---: | :---: | :---: |
|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428609-7cc6ae75-c31b-4ae6-9e4f-89437115b3dd.png">|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428550-3bb9068f-ed99-4cee-969f-cad3bcd75450.png">|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428639-0151f375-e0b6-458c-a9fe-1b440d99878f.png">|
<br>

## 개인 역할

### 🦦**준우**

- 기초세팅
    - git ignore
    - dependency
    - local propereties
    - package
    - application
    - theme
    - branch rule
- 바텀 네비게이션
- 앱바
- CI & slack 연동
- 상세보기 UI
- 1초 고민 UI & 비지니스 로직
- 글쓰기_카테고리
    - 바텀시트
    - 피커 커스텀
- 글쓰기_선택지 리사이클러뷰 로직
- 검색 UI
- Hilt 적용 / Server Connection 기초 세팅
- 글쓰기 함께/혼자 PUSH 통신
- 함께/혼자 상세조회 GET 통신

### 🐰**수현**

- 보관함 UI 및 비지니스 로직
- 최종 결정 UI 및 비지니스 로직
- 고민 해결 UI
- 상세보기 비지니스 로직
- 함께해라 UI 비지니스 로직
- 함께해라 글 목록 GET 통신
- 함께해라 투표 POST 통신

### 🐱**유진**

- 함께해라 UI
- 작성 액티비티 및 프래그먼트 UI
- 함께/혼자 보관함 GET 통신
- 1초 고민 해결 GET 통신
- 지난 고민 GET 통신
- 함께/혼자 최종결정 비지니스 로직
- 함께/혼자 최종결정 PATCH 통신

## Coding/Git Convnetions
[Convention 보러가기 Click ✔](https://daffy-lawyer-1b8.notion.site/Android-1c147c6c1e3c4e2eacdb1e781581d4aa)

<br>

## Foldering

```
📂 com.android.hara
┣ 📂 application
┃ ┣ 📜 Application.kt
┣ 📂 data
┃ ┣ 📂 datasource
┃ ┃ ┣ 📜 HaraService.kt
┃ ┣ 📂 model
┃ ┃ ┣ 📜 HaraDTO.kt
┃ ┣ 📂 repository
┃ ┃ ┣ 📜 HaraRepositoryImpl.kt
┣ 📂 di
┃ ┣ 📜 DataSourceModule.kt
┃ ┣ 📜 RepositroyModule.kt
┣ 📂 domain
┃ ┣ 📂 model
┃ ┃ ┣ 📜 HaraEntity.kt
┃ ┣ 📂 repository
┃ ┃ ┣ 📜 HaraRepository.kt
┣ 📂 presentation
┃ ┣ 📂 base
┃ ┃ ┣ 📜 AlbumCommentListAdapter.kt
┃ ┃ ┣ 📜 AlbumFragment.kt
┃ ┣ 📂 cardgame
┃ ┃ ┣ 📜 AlbumCommentListAdapter.kt
┃ ┃ ┣ 📜 AlbumFragment.kt
┃ ┣ 📂 detail
┃ ┃ ┣ 📜 AlbumCommentListAdapter.kt
┃ ┃ ┣ 📜 AlbumFragment.kt
┃ ┣ 📂 home
┃ ┃ ┣ 📂 fragment
┃ ┃ ┃ ┣ 📜 CategoryAdapter.kt
┃ ┃ ┃ ┣ 📜 StorageFragment.kt
┃ ┃ ┃ ┣ 📜 TogetherFragment.kt
┃ ┃ ┣ 📂 viewmodel
┃ ┃ ┃ ┣ 📜 HomeViewModel.kt
┃ ┃ ┣ 📜 HomeActivity.kt
┃ ┣ 📂 setting
┃ ┃ ┣ 📜 HomeActivity.kt
┃ ┣ 📂 util
┃ ┃ ┣ 📜 BindingConversion.kt
┃ ┃ ┣ 📜 ContentUriRequestBody.kt
┃ ┃ ┣ 📜 OnSingleClickListener.kt
┃ ┃ ┣ 📜 ViewExtension.kt.kt
┃ ┣ 📂 write
┃ ┃ ┣ 📜 WriteActivity.kt
```
