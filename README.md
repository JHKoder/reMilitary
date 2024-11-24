
### 내부망 모니터링
```
군 망을 효과적으로 모니터링 할 수 있는 프로젝트 
```

#### 기대효과
기존의 cmd창 한 화면 가득 띄어 보는 불편함을 
한 사이트 에 보기 편하게 만들어 피로감을 덜수있다. 
</br>
</br>


### (V.1) - 21.3.25 ~ 21.4.4 (10일)
![Java7](https://img.shields.io/badge/Java-7-yellow)
![Jsp](https://img.shields.io/badge/-Jsp-blue)
![JS](https://img.shields.io/badge/-Javascript-green)
![tomcat](https://img.shields.io/badge/tomcat-4.1-lightgrey)
![ide](https://img.shields.io/badge/IDE-%EB%A9%94%EB%AA%A8%EC%9E%A5-brightgreen)
```
- ping 확인 기능 로직 구현 
- upd 사용하여 모니터링 구현
- 사용자 쪽 통신은(jsp) 제거했으므로 콘솔 출력으로 대처 
```



### (V.2) - 22.8.25 ~ 22.8.31
![Java8](https://img.shields.io/badge/Java-8-yellow)
![ide](https://img.shields.io/badge/IDE-Intellij-brightgreen)
```
- 1.7 -> 1.8 으로 자바 업그레이드
- 전반적인 코드 개선
- 컨벤션, 코드 분리, 일급 컬렉션 사용
```


#### (V.3 예상 변경점)
- spring boot 2.7.3 gradle으로 마이그레이션

다른 프로젝트에서 이어서 진행 
https://github.com/oiNeh/Monitoring 


## 프로그램 동작 방식 (V.1)
![핑테스트](https://user-images.githubusercontent.com/105915960/187028697-0736ad17-0152-4b89-908a-bfffdb32f955.png)


## 이전에 사용했던 UI
기존에는 서버 연결을 확인할때 모니터링을 아래 방식으로 했습니다. (대략 30~40 ) </br>

![군IP조회](https://user-images.githubusercontent.com/105915960/186712395-94087687-fa8e-46c0-abf5-bddda8d249f2.png)
