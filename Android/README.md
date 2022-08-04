# Topas



## 0. 순서
[1. 배경](#1-배경)  
[2. 기술 및 환경](#2-기술-및-환경)  
[3. 패키지 구조](#3-패키지-구조)  
[4. 실행 화면](#4-실행-화면)  
[5. 이후 계획](#5-이후-계획)



---



## 1. 배경  
토이 프로젝트를 진행하기 위해 관련 커뮤니티를 찾아보던 중 토이 프로젝트나 스터디 팀원 구인을 위한 커뮤니티가 별로 없다는 것을 알았다.  
그래서 구인구직 앱처럼 팀 구성용 앱을 만들면 어떨까 하는 생각을 하여 진행하게 됐다.  
파이어베이스를 이용하여 회원가입, 로그인 팀원과 팀 검색, 채팅을 통한 만남까지 구현하는 것을 목표로 잡았다.  
무엇보다 최 우선으로 생각한것은 기능 구현도 좋지만 최대한 Clean Architecture를 구성하는 것이다.



---



## 2. 기술 및 환경
+ 클라이언트
    + 개발 환경  
        + Windows 10, macOS Monterey
        + Android Studio
    + 언어  
        + Kotlin
    + 프레임워크 & 라이브러리
        + Android(SDK Level: 31)
        + View Model
        + Data Binding
        + Live Data
        + Coroutine
        + Hilt
        + View Pager2
        + Glide
    + 빌드 툴
        + Gradle
    + 아키텍처
        + MVVM

<br/> 

+ 서버
    + Firebase
        + Authentication
        + Firestore Database
        + Storage

<br/> 

+ 협업
    + Jira
    + Github



---



## 3. 패키지 구조  
<details>
<summary>접기 / 펼치기</summary>

```  
📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂example
 ┃ ┃ ┃ ┗ 📂android
 ┃ ┃ ┃ ┃ ┣ 📂base
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📜BaseApplication.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseFragment.kt
 ┃ ┃ ┃ ┃ ┣ 📂chat
 ┃ ┃ ┃ ┃ ┃ ┣ 📂adapter
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRoomAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Chat.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRoom.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ChatRoomActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatRoomListFragment.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂viewmodel
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ChatViewModel.kt
 ┃ ┃ ┃ ┃ ┣ 📂contact
 ┃ ┃ ┃ ┃ ┃ ┗ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ContactActivity.kt
 ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┣ 📂adapter
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberHaveSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberSearchAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberSearchSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberContactFragment.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberSearchActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂viewmodel
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberViewModel.kt
 ┃ ┃ ┃ ┃ ┣ 📂onboard
 ┃ ┃ ┃ ┃ ┃ ┣ 📂adapter
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OnBoardingAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OnBoarding1Fragment.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OnBoarding2Fragment.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OnBoarding3Fragment.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OnBoardingActivity.kt
 ┃ ┃ ┃ ┃ ┣ 📂sign
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SignRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MainActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignInActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜SignUpActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SignUpGoogleActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂viewmodel
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SignViewModel.kt
 ┃ ┃ ┃ ┃ ┣ 📂team
 ┃ ┃ ┃ ┃ ┃ ┣ 📂adapter
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateRequireSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateSearchSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamDetailRequireSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamModifyRequireSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamModifySearchSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamRequireSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamSearchAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamSearchSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂doamin
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Team.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamContactFragment.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamCreateActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamDetailActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜TeamModifyActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamSearchActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂viewmodel
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜TeamViewModel.kt
 ┃ ┃ ┃ ┃ ┣ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📂adapter
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserHaveSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSearchSkillAdapter.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserRepository.kt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂view
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserSettingActivity.kt
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSkillFragment.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📂viewmodel
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserViewModel.kt
 ┃ ┃ ┃ ┃ ┗ 📂utility
 ┃ ┃ ┃ ┃ ┃ ┣ 📜LoadingDialog.kt
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Utility.kt
 ┣ 📂res
 ┃ ┣ 📂anim
 ┃ ┃ ┣ 📜enter_from_right.xml
 ┃ ┃ ┗ 📜exit_to_right.xml
 ┃ ┣ 📂animation
 ┃ ┣ 📂drawable
 ┃ ┃ ┣ 📜add_box.xml
 ┃ ┃ ┣ 📜add_photo_alternate.xml
 ┃ ┃ ┣ 📜brand_button_round_edge_background.xml
 ┃ ┃ ┣ 📜cancel.xml
 ┃ ┃ ┣ 📜default_profile_photo.png
 ┃ ┃ ┣ 📜disabled_by_default.xml
 ┃ ┃ ┣ 📜error_button_round_edge_background.xml
 ┃ ┃ ┣ 📜google.png
 ┃ ┃ ┣ 📜group_add.xml
 ┃ ┃ ┣ 📜ic_launcher_background.xml
 ┃ ┃ ┣ 📜my_skill_round_edge_background.xml
 ┃ ┃ ┗ 📜white_button_round_edge_background.xml
 ┃ ┣ 📂drawable-v24
 ┃ ┃ ┣ 📜account_circle.xml
 ┃ ┃ ┣ 📜chevron_left.xml
 ┃ ┃ ┣ 📜chevron_right.xml
 ┃ ┃ ┣ 📜exit.xml
 ┃ ┃ ┣ 📜groups.xml
 ┃ ┃ ┣ 📜ic_launcher_foreground.xml
 ┃ ┃ ┣ 📜person.xml
 ┃ ┃ ┗ 📜search.xml
 ┃ ┣ 📂font
 ┃ ┃ ┣ 📜font.xml
 ┃ ┃ ┣ 📜noto_sans_kr_bold.otf
 ┃ ┃ ┣ 📜noto_sans_kr_light.otf
 ┃ ┃ ┗ 📜noto_sans_kr_regular.otf
 ┃ ┣ 📂layout
 ┃ ┃ ┣ 📜activity_chat_room.xml
 ┃ ┃ ┣ 📜activity_contact.xml
 ┃ ┃ ┣ 📜activity_main.xml
 ┃ ┃ ┣ 📜activity_member_search.xml
 ┃ ┃ ┣ 📜activity_on_boarding.xml
 ┃ ┃ ┣ 📜activity_sign_in.xml
 ┃ ┃ ┣ 📜activity_sign_up.xml
 ┃ ┃ ┣ 📜activity_sign_up_google.xml
 ┃ ┃ ┣ 📜activity_team_create.xml
 ┃ ┃ ┣ 📜activity_team_detail.xml
 ┃ ┃ ┣ 📜activity_team_modify.xml
 ┃ ┃ ┣ 📜activity_team_search.xml
 ┃ ┃ ┣ 📜activity_user_setting.xml
 ┃ ┃ ┣ 📜fragment_chat_room_list.xml
 ┃ ┃ ┣ 📜fragment_member_contact.xml
 ┃ ┃ ┣ 📜fragment_on_boarding1.xml
 ┃ ┃ ┣ 📜fragment_on_boarding2.xml
 ┃ ┃ ┣ 📜fragment_on_boarding3.xml
 ┃ ┃ ┣ 📜fragment_team_contact.xml
 ┃ ┃ ┣ 📜fragment_user_skill.xml
 ┃ ┃ ┣ 📜item_chat_exit.xml
 ┃ ┃ ┣ 📜item_chat_my.xml
 ┃ ┃ ┣ 📜item_chat_room.xml
 ┃ ┃ ┣ 📜item_chat_your.xml
 ┃ ┃ ┣ 📜item_member.xml
 ┃ ┃ ┣ 📜item_member_have_skill.xml
 ┃ ┃ ┣ 📜item_member_search.xml
 ┃ ┃ ┣ 📜item_member_search_skill_all.xml
 ┃ ┃ ┣ 📜item_team.xml
 ┃ ┃ ┣ 📜item_team_create_require_skill.xml
 ┃ ┃ ┣ 📜item_team_create_search_skill_all.xml
 ┃ ┃ ┣ 📜item_team_detail_require_skill.xml
 ┃ ┃ ┣ 📜item_team_modify_require_skill.xml
 ┃ ┃ ┣ 📜item_team_modify_search_skill_all.xml
 ┃ ┃ ┣ 📜item_team_require_skill.xml
 ┃ ┃ ┣ 📜item_team_search.xml
 ┃ ┃ ┣ 📜item_team_search_skill_all.xml
 ┃ ┃ ┣ 📜item_user_have_skill.xml
 ┃ ┃ ┣ 📜item_user_search_skill_all.xml
 ┃ ┃ ┗ 📜sign_loading_dialog.xml
 ┃ ┣ 📂menu
 ┃ ┃ ┣ 📜menu_bottom_navigation_contact.xml
 ┃ ┃ ┣ 📜menu_chat_room.xml
 ┃ ┃ ┣ 📜menu_toolbar_contact.xml
 ┃ ┃ ┗ 📜menu_toolbar_contact_chat.xml
 ┃ ┣ 📂mipmap-anydpi-v26
 ┃ ┃ ┣ 📜ic_launcher.xml
 ┃ ┃ ┗ 📜ic_launcher_round.xml
 ┃ ┣ 📂mipmap-hdpi
 ┃ ┃ ┣ 📜ic_launcher.webp
 ┃ ┃ ┗ 📜ic_launcher_round.webp
 ┃ ┣ 📂mipmap-mdpi
 ┃ ┃ ┣ 📜ic_launcher.webp
 ┃ ┃ ┗ 📜ic_launcher_round.webp
 ┃ ┣ 📂mipmap-xhdpi
 ┃ ┃ ┣ 📜ic_launcher.webp
 ┃ ┃ ┗ 📜ic_launcher_round.webp
 ┃ ┣ 📂mipmap-xxhdpi
 ┃ ┃ ┣ 📜ic_launcher.webp
 ┃ ┃ ┗ 📜ic_launcher_round.webp
 ┃ ┣ 📂mipmap-xxxhdpi
 ┃ ┃ ┣ 📜ic_launcher.webp
 ┃ ┃ ┗ 📜ic_launcher_round.webp
 ┃ ┣ 📂values
 ┃ ┃ ┣ 📜colors.xml
 ┃ ┃ ┣ 📜item_team_spinner.xml
 ┃ ┃ ┣ 📜strings.xml
 ┃ ┃ ┣ 📜styles.xml
 ┃ ┃ ┗ 📜themes.xml
 ┃ ┣ 📂values-night
 ┃ ┃ ┗ 📜themes.xml
 ┃ ┗ 📂xml
 ┃ ┃ ┣ 📜backup_rules.xml
 ┃ ┃ ┗ 📜data_extraction_rules.xml
 ┗ 📜AndroidManifest.xml
```  
</details>



---



## 4. 실행 화면  
<details>
<summary>OnBoarding</summary>

- 온보딩  
![온보딩](https://user-images.githubusercontent.com/44915367/182833509-869d228d-04f0-456e-b288-4aa77e9a69e3.gif)
  
View Pager 2를 이용하여 좌우 스크롤을 구현하였고, Circle Indicator를 하단에 배치하여 현재 몇 번째 페이지를 보고있는지 알려준다.  
마지막 페이지에서는 로그인, 회원가입, 구글로 시작을 고를 수 있다.  
한번 보면 SharedPreference에 저장하여 앱을 지웠다 다시 깔기전엔 나오지 않는다.  
자세히 보면 앱바가 없고, 상단바와 하단바의 색을 배경색에 맞춰 깔끔한 느낌을 주도록 했다.  
</details>

<details>
<summary>Sign</summary>

- 회원가입  
![일반 회원가입 1](https://user-images.githubusercontent.com/44915367/182834417-3a5837b0-6044-490d-afc8-5b2a19cfc7a7.gif)
  
Firebase에서 제공하는 이메일/패스워드를 통한 가입이다.  
프로필 사진, 이메일, 패스워드, 이름, 닉네임을 받고 프로필 사진은 필수 값이 아니며 선택하지 않을 경우 기본 프로필 사진이 적용된다.  
프로필 사진을 선택할 때 권한 요청을 하며 Glide를 통해 이미지를 표시한다.  
정규식을 지정하여 값이 비었거나 맞지 않으면 에러 메세지와 스낵바를 표시한다.  
또한 닉네임과 이메일 중복을 확인한다.  
가입에 성공하면 정보 수정 화면으로 이동된다.  

- 로그인  
![일반 로그인 1](https://user-images.githubusercontent.com/44915367/182835917-299e2df5-db36-4d43-9cd0-eb941c391b2d.gif)
  
이메일/패스워드를 통한 로그인이며 Authentication인증에 성공할 경우 Firestore Database에서 정보를 가져온다.  
입력값이 비었으면 에러 메세지와 스낵바 표시하고, 계정이 없을 경우 스낵바를 표시한다.  

- 구글 로그인  
![구글 로그인](https://user-images.githubusercontent.com/44915367/182836494-ba2b9e72-fb38-44e2-b0ae-e861687ac446.gif)
  
구글 계정을 사용하여 로그인한다.  
해당 구글 계정이 이미 가입되어 있으면 바로 로그인이 되고, 아닌경우 프로필 사진, 이름, 닉네임을 받는 화면으로 이동한다.  
그 뒤로는 일반 회원가입과 똑같다.  
</details>

<details>
<summary>User</summary>

- 정보 수정  
![정보 수정 1](https://user-images.githubusercontent.com/44915367/182836931-254f435a-42e5-4c4a-b24d-22ecfa51a1ae.gif)
  
프로필 사진, 이름, 닉네임, 자기소개, 보유 스킬, 정보 노출 여부를 변경할 수 있다.  
스킬을 변경할 경우 글자를 입력할 때 마다 그 글자가 들어가는 스킬이 표시된다.  
스킬을 터치하면 선택이 되고, 완료 버튼을 누르면 적용된다.  
내 정보 노출을 체크하면 팀원 화면에서 자신의 정보가 다른 사람들에게 노출된다.  
저장 버튼을 누르면 최종적으로 서버에 저장 된다.  
</details>

<details>
<summary>Member</summary>

- 팀을 구하는 사람들 표시  
![멤버 1](https://user-images.githubusercontent.com/44915367/182837547-741fbc62-129f-48d6-ac25-5102e67b5a0b.gif)
  
정보 노출을 허용한 사람들이 표시 된다.  
Recycler View를 이용해 목록을 표시하며, 아이템들은 Card View 레이아웃을 이용했다.  
한번에 모든 사람들을 가져오지는 않고 5개씩 끊어서 최신순으로 가져오고, 맨 밑으로 내리면 로딩된다.  
맨 위로 스와이프하면 새로고침 된다.  
유저 목록 내부에도 중첩 Recycler View를 사용해 보유 스킬을 표시하며 Flexbox 레이아웃을 사용했다.  

- 스킬을 통한 팀원 검색  
![멤버 2](https://user-images.githubusercontent.com/44915367/182838263-e063cef5-dab2-422a-9fd9-e32d2472f1f2.gif)
  
앱바에 돋보기 버튼을 누르면 검색 화면으로 이동한다.  
이 곳에선 스킬을 통해 그 스킬을 보유한 사람을 검색 할 수 있다.  
</details>

<details>
<summary>Team</summary>

- 팀원을 구하는 팀 표시  
![팀 1](https://user-images.githubusercontent.com/44915367/182839017-311413d2-9b06-4701-9653-77fc6678fc29.gif)
  
팀원 표시와 유사하게 팀을 표시한다.  
하단에 Bottom Navigation View를 통해 이동한다.  
맨 위 Spinner를 통해 전체 팀과, 내가 만든 팀을 나눠서 볼 수 있다.  

- 팀 상세 보기  
![팀 3](https://user-images.githubusercontent.com/44915367/182839254-f00368f5-e65e-4d0b-a65a-599558b4493c.gif)
  
팀은 항목을 클릭하여 상세 화면으로 이동할 수 있다.  

- 스킬을 통한 팀 검색  
![팀 2](https://user-images.githubusercontent.com/44915367/182839443-ec847405-073d-4062-ac89-a2dc175acf77.gif)
  
팀원과 유사하게 팀도 검색할 수 있다.  

- 팀 생성, 수정, 삭제  
![팀 4](https://user-images.githubusercontent.com/44915367/182839628-e0e190d9-08f0-4a19-a948-9729a989587e.gif)
  
하단에 Floating Action Button을 이용해 팀을 생성할 수 있다.  
자신이 만든 팀이라면 수정과 삭제를 할 수 있다.  
</details>

<details>
<summary>Chat</summary>

- 채팅 화면으로 이동  
![채팅 1](https://user-images.githubusercontent.com/44915367/182839920-109404fa-1c79-42da-9cac-a13aa095952a.gif)
  
함께하기 버튼을 클릭하면 그 사람과의 채팅방으로 이동된다.  
이 때 바로 채팅방이 만들어지지는 않고, 채팅을 치면 그 때 채팅방이 생성되어 상대방에게도 표시된다.  
    
- 채팅  
![채팅 2](https://user-images.githubusercontent.com/44915367/182840335-3b4b0539-ccdb-4a1c-b3c4-21b3ca3cc3ed.gif)
  
흔히 알고있는 채팅처럼 진행되며 Snapshot Listener를 등록해 두었기 때문에 채팅이 오면 즉시 반영된다.  
view type을 이용하여 상대방의 채팅, 나의 채팅, 시스템 메세지를 다르게 표시한다.  
앱바에 나가기 버튼을 클릭하면 채팅방에서 나가지고 상대방에게 표시된다.  
  
- 채팅방 리스트  
![채팅 3](https://user-images.githubusercontent.com/44915367/182840743-c1771b3f-2b65-4a6d-a17e-c3495aa73b59.gif)
    
채팅방 리스트에도 Snapshot Listener를 등록해 두었기 때문에 나에게 채팅이 오거나 보내면 바로 반영된다.  
가장 최근 메세지가 오고간 채팅방 순서대로 표시된다.  
</details>

  


---



## 5. 이후 계획  
이메일/패스워드를 통한 로그인 기능 삭제.  
카카오톡, 네이버, 페이스북 등 다양한 소셜 로그인 추가.  
채팅 알람기능.  
UI, UX 개선.  



---



## 진행 기간  
2022.07.04 ~ 진행 중
