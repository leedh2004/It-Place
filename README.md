# SW 중심대학 해커톤 - 위플레이:musical_note: 팀
## 비대면 영상 기반 서비스 :microphone: It-Place :headphones: 
- 코로나 이슈로 대면 만남이 어려워진 언택트(Untact) 시대.<br>
- 각자의 공간에서도 친밀감을 형성하고, 추억을 쌓을 수 있는 새로운 방안을 제시합니다.<br>

## 잇-플의 이용방법 :page_facing_up:
- 메인화면에는 현재 생성된 플레이스들이 있는데 제목, 참여중인 인원수, 카테고리를 살펴 보거나 검색 기능을 통해 자기가 원하는 플레이스에 참여할 수 있습니다.<br>
<div>
<img src="https://user-images.githubusercontent.com/69130921/107108094-d1ca6a80-6878-11eb-8bc3-8dca733d761f.PNG">
<img src="https://user-images.githubusercontent.com/69130921/107108106-df7ff000-6878-11eb-83a0-48e89ba12fb0.PNG">
<img src="https://user-images.githubusercontent.com/69130921/107082545-ad9a6980-6837-11eb-82d0-984d6837b168.PNG">
<img src="https://user-images.githubusercontent.com/69130921/107108137-27067c00-6879-11eb-952c-be4eb4efc10f.PNG"><br>
 </div><br>
 
- 플레이스 안에선 노래, 시청, 주문, 효과, 게임, 촬영과 같은 기능들을 이용할수 있습니다.<br>
<div>
 <img src="https://user-images.githubusercontent.com/69130921/107084996-2b13a900-683b-11eb-955f-7de247c3cd89.PNG">
 <img src="https://user-images.githubusercontent.com/69130921/107085071-467eb400-683b-11eb-8fc7-b51529d4b7eb.PNG">
 <img src="https://user-images.githubusercontent.com/69130921/107084828-e982fe00-683a-11eb-8f31-2042592f7e9f.PNG">
 <img src="https://user-images.githubusercontent.com/69130921/107084834-eab42b00-683a-11eb-80b3-7eb78b5bfe49.PNG">
 </div><br>

- 플레이스에서 친구를 사귈수 있으며 친구 상태에선 서로의 플레이스에 초대할 수 있습니다.<br><br>
- 플레이스를 만들고 싶을땐 이름, 제한인원수, 태그, 자신의 썸네일 이미지, 플레이스의 커버 이미지를 지정해 개설할 수 있습니다.<br>
<div>
<img src="https://user-images.githubusercontent.com/69130921/107082961-44ffbc80-6838-11eb-9ea5-0eae9d4319fa.PNG">
<img src="https://user-images.githubusercontent.com/69130921/107083209-a6279000-6838-11eb-9e5c-00c48429b2e3.PNG"></div><br>
 :warning: 아직 구현되지 않은 기능도 존재합니다.
 <br><br>
 
## 현재 잇-플이 제공하는 서비스는 :low_brightness:
 ### 여러명과 고화질 화상통화
 - 플레이스를 개설하고 모르는 친구들과 함께 고화질 화상통화를 즐길수 있습니다.<br>
 
 ### 종강파티
- 파티 플레이스 기능을 통해 종강 파티를 집에서도 즐길 수 있습니다.<br>
- 다양한 게임과 얼굴인식을 통한 닮은꼴 찾기 등 재미있는 기능들을 이용할 수 있습니다.<br>
 
 ### 친구추가 및 초대
 - 친구를 추가할 수 있으며 추가한 친구를 플레이스로 초대할 수 있습니다.<br>

<br>

## 개발자 :computer:
- 2021 SW중심대학 공동해커톤 산출물
- 개발기간: \`21.02.04 ~ \`21.02.06

#### :snowman:김소령(서울여자대학교, 디자이너, 팀장)<br>
#### :soccer:김동규(숭실대학교, )<br>
#### :two_hearts:정선아(충북대학교, 백엔드)<br>
#### :earth_americas:이도현(한양대학교, 백엔드)<br>
#### :christmas_tree:이범준(호서대학교, 프론트)<br>
<br>

## 개발 배경 :clipboard:
코로나 이후로 우리는 더 이상 한 공간에 모이기 조차 조심스럽게 되었습니다.

사람들은 소통을 위한 대안으로 온라인 화상 채팅 앱을 사용하기 시작했고, 이제는 다양한 모임의 성격에 맞는

맞춤형 커뮤니티 서비스가 필요해졌습니다. 이에 각 모임의 특성에 맞는 기능을 반영한 '잇-플'을 기획하였습니다.
<br>
<br>
## 개발 문서 :page_with_curl:
### 초기구상

#### ERD
<img src="https://user-images.githubusercontent.com/31717177/107100880-f1ea3180-6858-11eb-84c2-c8916052a7c2.jpeg" width=300 >

#### SQL

```sql
CREATE TABLE `user` (
	`uid` varchar(64) NOT NULL PRIMARY KEY,
	`name` varchar(20) NOT NULL,
	`profile_url` varchar(128) DEFAULT "https://itplace.s3.ap-northeast-2.amazonaws.com/profile.png"
);

CREATE TABLE `friend` (
	`from` varchar(64) NOT NULL,
	`to` varchar(64) NOT NULL,
	PRIMARY KEY (`from`, `to`),
	CONSTRAINT `fk_from` FOREIGN KEY (`from`) REFERENCES `user` (`uid`) ON DELETE CASCADE,
	CONSTRAINT `fk_to` FOREIGN KEY (`to`) REFERENCES `user` (`uid`) ON DELETE CASCADE
);

CREATE TABLE `room` (
	`rid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(20) NOT NULL,
 `max_num` int DEFAULT 4,
 `landscape_url` varchar(128),
 `host_uid` varchar(64) NOT NULL,
 CONSTRAINT fk_host_uid FOREIGN KEY (host_uid) REFERENCES user (uid) ON DELETE CASCADE;
);

CREATE TABLE `album` (
	`image_url` varchar(128) NOT NULL PRIMARY KEY,
	`rid` int NOT NULL,
	CONSTRAINT `fk_album_rid` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`) ON DELETE CASCADE
);


CREATE TABLE `user_room` (
	`uid` varchar(64) NOT NULL,
	`rid` int NOT NULL,
	PRIMARY KEY (`uid`, `rid`),
	CONSTRAINT `fk_ur_uid` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE,
	CONSTRAINT `fk_ur_rid` FOREIGN KEY (`rid`) REFERENCES `room` (`rid`) ON DELETE CASCADE
);
```

<!--### 초기목표
...-->


<br>

## 기술스택 :bulb: 
### SDK, API
- RemoteMonster
- Naver CLOVA Face Recognition

### 백엔드
- Node.js(Express), RESTful API, AWS Hosting
- AWS MYSQL Server
- AWS S3 Container

### 프론트엔드
- Android
- HTML, CSS, JS

<br>

## 참고자료 :scroll:
- RemoteMonster Offical Documnet
- Express Official Document
- AWS Official Documnet


## 향후 계획 :calendar:

### 1. 프로젝트 관련
- 몇 가지 구현하지 못한 RESTful API 구현 예정
- 주요 기능인 화상회의를 API 사용이 아닌, WebRTC 직접 구축을 목표로 함.
- Face Recgonition Model 자체 모델로 변환

### 2. 서비스 관련
화상회의 관련 컨텐츠 확장
- 노래방, 식사, 게임, 파티 등의 기능 추가

## 최종 목표 :triangular_flag_on_post:
식사, 노래방, 게임, 파티까지 평소 우리가 즐길 수 있는 모든 활동을 비대면으로 진행할 수 있는 서비스

뉴노멀 시대에 현명하게 즐기자:)

비대면 영상 기반 커뮤니티 서비스, 잇- 플

## 정보 :notebook_with_decorative_cover:

### 컴퓨터 구성 / 필수 조건 안내 (Prerequisites)
- Node.js(예시)
- Recommende Chrome

### 설치 안내 (Installation)

```
$ npm i
$ npm i -g pm2
```

.env 파일이 존재해야 합니다.

### 사용법 (Getting Started)

```
$ npm run-script run
```

### 저작권 및 사용권 정보 (Copyright / End User License)
- :copyright: MIT License

### 배포자 및 개발자의 연락처 정보 (Contact Infomration)
- thfud333@gmail.com
- leedh2008@naver.com
- ptsaturn68@gmail.com
- jungsuna99@gmail.com
- qjawnswkd0717@gmail.com

### 알려진 버그 (Known Issue)
- :x:

### 문제 발생에 대한 해결책 (Troubleshooting)
- :white_check_mark:

### 크레딧 (Credit)
- :moneybag:

### 업데이트 정보 (Change Log)
- `21.02.04 0.0.1
- `21.02.06 0.1.0

<br>

## Git 관리 방법 :notebook:

팀원들끼리의 협업을 위해 git-flow 약식 모델을 사용하였습니다.

1. Issue 생성
> Issues -> New issue

2. 이슈번호 생성이 되면 Feature 브런치 작성
```
$ git branch feature/#1
```

3. 개발은, 해당 브런치에서 작업
```
git checkout feature/#1
```

4. 개발 완료시 해당 브런치에 푸쉬
```
git commit -m "... 구현완료 #1"
git push origin feature/#1
```

5. develop 브런치에 merge하기 위해 Pull requests 작성 

> Pull requests > New pull requests 
feature/#1을 develop으로 merge 요청 후 수락

