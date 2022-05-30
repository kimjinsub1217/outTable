# outTable
우리의식탁 클론 (완성률 : 30%)
\
📌사용 글꼴
\
https://hangeul.naver.com/font 마루부리 글꼴 사용

📌활용 기술
- 카카오로그인API : 소셜로그인을 구현하기 위해 사용
- circleimageview : 원형 이미지 뷰를 사용해 카카오톡 프로필 사진을 보여주기 위해 사용
- glide : 카카오에서 받아온 프로필 사진 URI를 이미지로 표시하기 위해 사용
- retrofit : 조리식품의 레시피 DB API 에서  데이터를 받아오기 위해서 사용
- 
📌API 등록방법

local.properties 파일로 들어가서 아래와 같이 적어줍니다.
> kakaoApi = "자신의 카카오API"
> 
>kakaoApiManifest = 자신의 카카오API 
> 
> ourTable = 조리식품의 레시피 DB http://www.foodsafetykorea.go.kr/api/openApiInfo.do?menu_grp=MENU_GRP31&menu_no=661&show_cnt=10&start_idx=1&svc_no=COOKRCP01