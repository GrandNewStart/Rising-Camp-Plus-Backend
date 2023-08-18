# AWS 시작하기

## AWS 계정 생성하기

1. [https://aws.amazon.com/ko](https://aws.amazon.com/ko) 접속, 'AWS 계정 생성' 클릭
![browser](./browser-01.png)

2. 이메일과 계정 이름을 입력한다.
![browser](./browser-02.png)

3. 입력한 이메일로 인증코드가 날아갔을 것이다. 코드를 입력해주자.
![browser](./browser-03.png)
![browser](./browser-04.png)

4. 패스워드를 두번 입력한다.
![browser](./browser-05.png)

5. 개인정보를 입력한다. 전부 써야하고 영어로 써야 한다. 주소 같은건 대충 아무 말이나 써도 넘어간다.
![browser](./browser-06.png)

6. 결제 정보를 입력한다. 해외결제 되는 카드를 준비해야 할 것이다.
![browser](./browser-07.png)

7. 계속 결제 정보를 입력한다. 모두 입력하고 다음을 누르면 입력한 카드로부터 100원이 출급됐다가 취소될 것이다. 100원도 없는 카드면 어떻게 되는거지...?
![browser](./browser-08.png)

8. 전하번호 인증의 시간이다.
![browser](./browser-09.png)

9. 인증번호를 받아서 입력하자.
![browser](./browser-10.png)

10. 마지막으로 멤버십을 선택한다. 무료로 한다.
![browser](./browser-11.png)

11. 가입 완료. 이제 루트 사용자로써 계정이 생성되었다.
![browser](./browser-12.png)

---

## IAM 사용자 생성하기

IAM: Identity and Access Management. 인증(Authentication)과 인가(Authorization)

루트 유저는 모든 권한을 가진다. IAM 유저는 인증과 인가에 관련된, 제한적인 권한을 가진다. 따라서 가급적이면 IAM 계정을 사용하는 것이 보안상 안전하다. 루트 계정에서 개발자 그룹을 생성한 후, 그 그룹 소속의 IAM 계정을 만들어보도록 하자. 

### IAM 그룹 생성
1. 우선 루트 계정으로 로그인한 후, AWS 콘솔에서 검색창에 IAM을 친다. IAM 대시보드로 들어간다. 
![browser](./browser-13.png)

2. 액세스 관리 > 사용자 그룹 > 그룹 생성 
![browser](./browser-14.png)

3. 그룹 이름을 지정하고 권한을 선택하자. 추가할 권한은 AdministrationAccess이다. 
![browser](./browser-15.png)
![browser](./browser-16.png)

4. '그룹 생성'을 클릭하여 그룹을 생성한다. 아직 사용자는 아무도 없다.
![browser](./browser-17.png)

### IAM 유저 추가

1. 액세스 관리 > 사용자 > 사용자 생성
![browser](./browser-18.png)

2. 사용자 정보를 입력해준다. 이름을 대충 입력하고, AWS Management Console 액세스를 체크하고, IAM 사용자 생성을 체크하고, 비밀번호를 입력해준다.
![browser](./browser-19.png)

3. 아까 만든 그룹을 체크하고 다음을 클릭한다.
![browser](./browser-20.png)

4. 사용자 생성을 클릭한다.
![browser](./browser-21.png)

5. 사용자가 생성되었다.
![browser](./browser-22.png)

6. IAM 대시보드로 돌아가면 사용자가 늘어난 것을 확인할 수 있다.
![browser](./browser-23.png)

7. 이쪽의 로그인 URL을 카피하여 접속해보자.
![browser](./browser-24.png)

8. 로그인 화면이 뜰 것이다. 아까 생성한 IAM 계정을 입력한다.
![browser](./browser-25.png)

9. IAM 계정으로 로그인 된 것을 확인할 수 있다.
![browser](./browser-26.png)

---

## Regions & Zones

Region: 서버가 설치된 지역. 여러 Region을 설정 할 수록 글로벌 스케일에서 일정한 서비스를 제공하기 용이해진다.

Availability Zone: 같은 Region 내에도 여러 서버를 설치할 수 있다. 이를 Availability Zone으로 구분한다.

AWS는 전세계에 25개 이상의 Region에 데이터센터를 구축하였다. 이 숫자는 매년 늘어나고있다. 덕분에 전세계 어디서나 균일한 퀄리티로 서비스를 제공하면서 각국의 정부 규제에 맞추기에도 용이해진다.

Region과 Availability Zone의 예시
| Region Code | Region | Availability Zones | Availability Zones List | 
| ----------- | ------ | ------------------ | ----------------------- |
| us-east-1 | US East (N. Virginia) | 6 | us-east-1a-east-1b, us-east-1c-east-1d,s us-east-1e-east-1f |
| eu-west-2 | Europe (London) | 3 | eu-west-2a-west-2b, eu-west-2c |
| ap-south-1 | Asia Pacific (Mumbai) | 3 | ap-south-1a, ap-south-1b, ap-south-1c |
