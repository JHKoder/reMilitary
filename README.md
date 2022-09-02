
### 내부망 모니터링
#### 프로젝트 목표
군 대규모 훈련상황떄 사용될 군 망 모니터링을 만들어</br>
훈련을 좀더 효율적으로 할수 있도록 도와주는 서비스
</br>
#### 기대효과
기존의 cmd창 한 화면 가득 띄어 보는 불편함을 
한 사이트 에 보기 편하게 만들어 피로감을 덜수있다. 
</br>
</br>

### (V.1) - 2021 3.25 ~ 4.4 (10일)
![Java7](https://img.shields.io/badge/-Java7-yellowgreen)
![Jsp](https://img.shields.io/badge/-Jsp-blue)
![JS](https://img.shields.io/badge/-Javascript-green)
![tomcat](https://img.shields.io/badge/tomcat-4.1-lightgrey)
![ide](https://img.shields.io/badge/IDE-%EB%A9%94%EB%AA%A8%EC%9E%A5-brightgreen)
```
- 전반적인 코드 개선
- 컨벤션, 코드 분리, 일급 컬렉션 사용
- 1.7 -> 1.8 으로 자바 업그레이드
```

### (V.2) 
![Java](https://img.shields.io/badge/Java-red?logo=java) 
![ide](https://img.shields.io/badge/IDE-Intellij-brightgreen)
```
- windows cmd 모든 명령어 입력가능 하도록 변경
- 시스템 정보 모니터링 할수 있도록 구현 중... 
```

#### (V.3 예상 변경)
- spring boot gradle으로 마이그레이션 교체 
- 실시간 화면 전달 방식 udp And Json 고려중...


## 프로그램 동작 방식 
![핑테스트](https://user-images.githubusercontent.com/105915960/187028697-0736ad17-0152-4b89-908a-bfffdb32f955.png)


## 이전에 사용했던 UI
기존에는 서버 연결을 확인할떄 모니터링을 아래 방식으로 했습니다. (대략 30~40 ) </br>

![군IP조회](https://user-images.githubusercontent.com/105915960/186712395-94087687-fa8e-46c0-abf5-bddda8d249f2.png)
