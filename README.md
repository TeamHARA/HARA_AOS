# 🤍 HARA-AOS 🤍

> 해라, THE SOPT 31TH 앱잼 Android팀 <br>
2023.01.02 - 2023.01.14

<br>

## Video
<img width="270" src="https://.gif">

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

## Contributors

| 김준우 [@IslandOfDream](https://github.com/IslandOfDream) | 장유진 [@wkdyujin](https://github.com/wkdyujin) | 이수현 [@skylartosf](https://github.com/skylartosf) |
| :---: | :---: | :---: |
|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428609-7cc6ae75-c31b-4ae6-9e4f-89437115b3dd.png">|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428550-3bb9068f-ed99-4cee-969f-cad3bcd75450.png">|<img width="1400" src="https://user-images.githubusercontent.com/70648111/210428639-0151f375-e0b6-458c-a9fe-1b440d99878f.png">|
|**바텀네비게이션/앱바 , 투표 상세화면**|**홈화면 , 게시물 쓰기/수정**|**보관함**|

<br>

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
