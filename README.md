
#  Multi-File Content Copier - IntelliJ Plugin

IntelliJ Marketplace에서 설치하기 ([승인](https://plugins.jetbrains.com/plugin/28474-multi-file-content-copier/edit))

IntelliJ IDEA 플러그인으로, **여러 파일을 선택하고 그 내용을 한 번에 클립보드에 복사**할 수 있는 기능입니다.

![Multi Copy plug-in](https://github.com/user-attachments/assets/cd78e241-ffc1-4bb1-b4e4-f11b97f4f93b)



---
## 사용 기술 및 버전 (intellij_copy_multi_files_plugin 기준)

###  IDE
| IDE 종류 | 버전 |
|----------|-------|
| IntelliJ IDEA Ultimate | 2024.3.4.1 |

---

### ⚙ Plugin 개발 관련
| 기술 | 버전 / 정보 |
|------|--------------|
| Kotlin | 1.9.25 (IntelliJ 기본 지원) |
| Gradle | 8.x (IntelliJ Plugin Dev 환경) |
| IntelliJ Platform Plugin SDK | 243.19416.15 |
| IntelliJ Gradle Plugin | org.jetbrains.intellij 1.17.3 |
| IntelliJ Platform Plugin SDK | 2024.1.7 (Build #IC-241.x) |
| Plugin Build Compatibility | `sinceBuild = 241`, `untilBuild = 243.*` |
| Java | JDK 17 (plugin 개발에 사용됨) |
| 테스트 환경 | ✅ Sandbox 실행 성공 /  로컬 플러그인 설치 완료
| 로컬 설치 테스트 | ⚠️ 부분 실패 (빌드 후 설치 시 기능 미작동)
| 로컬 설치 오류 해결| 오류 해결 -> 정상 작동



---

### 📦 패키징 및 배포 관련
| 항목 | 값 |
|------|------|
| 플러그인 ID | `com.jay.intellij_copy_multi_files_plugin` |
| 플러그인 버전 | `1.0.0` |
| 사용 언어 | Kotlin |
| 주요 기능 | 선택된 다수 파일의 내용을 클립보드에 병합 복사 |
| 플러그인 실행 위치 | Project View 우클릭 메뉴 (`alt + F` 단축키 지원) |
| 배포 준비 | `signPlugin`, `publishPlugin` 설정 포함 (`ENV 환경변수 기반`) |

---

## 기획 의도



개발 중 여러 파일의 내용을 한꺼번에 복사할 일이 많았습니다.  
IDE 외부에서 수작업으로 복사하는 과정을 줄이기 위해  
직접 IntelliJ 플러그인을 만들어보게 되었습니다.

인텔리제이를 사용함에 있어 파일 전체를 복사할 일이 자주 있었는데 매번 하나하나 열면서 복붙하는 과정에서 
귀찮음을 해결할 수 있지 않을까? 라는 생각이 들어 생각이 드는김에 시도해 보았습니다.
소요시간은 약 14시간 걸렸고 생각이 들었던 당일 (2025.04.05) 해냈습니다

마켓플레이스 배포 완료 - 2025-09-16

## 아쉬움
샌드박스 테스트까지 성공했읕 때 기대에 부풀었지만 왜인지   
빌드 후 로컬에서 플러그인을 설치하고부터는 제대로 실행이 되질 않았습니다. (해결)
여러가지 서칭을 해보았지만 정확히 구현이 되지않아 아쉽지만 그럼에도 
내가 불편한 과정의 일을 내손으로 변화시키려했다는 시도와 
생소한 환경에서 코틀린, 샌드박스를 경험했다는 것에서 풍부한 경험을 할 수 있었습니다

(250914 해결 : 시스템 경로 의존방식 -> 인텔리제이 플랫폼 공식 권장방식으로 변경) 


---

## ⚙ 주요 기능

- 프로젝트 폴더에서 다수의 파일 선택
- 우클릭 메뉴에서 “Copy Multi File Contents” 실행
- 파일 제목과 함께 각 파일의 내용이 구분선과 함께 복사됨
- 클립보드에 자동 저장됨
- 각각 파일마다 구분할 수 있는 구분선 === file.name === 추가

---

##  기술 포인트

- `plugin.xml`을 통한 Action 등록 및 메뉴 바인딩
- `CommonDataKeys.VIRTUAL_FILE_ARRAY` 활용
- `ActionUpdateThread` 사용 (BGT)
- 샌드박스 환경에서 실행 테스트 완료
- `Toolkit.getDefaultToolkit().systemClipboard`로 클립보드 제어

---

##  개발 후기

단순 기능 구현이 아닌, IntelliJ 내부 구조와  
플러그인 동작 원리를 처음부터 학습하면서 진행했습니다.

- 처음엔 메뉴가 안 뜨는 이유를 몰랐지만 `ActionUpdateThread` 개념을 통해 해결했습니다.
- 파일이 복사되지 않는 문제를 디버깅하며 `virtualFileArray`의 개념도 알게 되었습니다.
- `plugin.xml` 설정 순서와 그룹 배치도 직접 테스트하면서 익혔습니다.

 이 프로젝트를 통해 **단순 구현이 아닌 구조적 이해와 문제 해결 능력**을 키웠습니다.

---

## 🚀 샌드박스 테스트 완료

플러그인은 샌드박스 실행으로 실제 IntelliJ 메뉴에 정상적으로 노출 및 작동 확인되었습니다.  
향후 기능 고도화 및 JetBrains 마켓 배포를 계획하고 있습니다.

## 플러그인 설치 적용 성공 

실제 환경에서 사용 가능함을 확인 

---

## 📸 사용 예시

![원하는 파일선택 -> 오른쪽 마우스 -> 메뉴] 

![sandbox success menu](https://github.com/user-attachments/assets/6384cf86-3c5a-4333-9e0f-037bdc988a1c)


![복사 , 메모장 붙여넣기 후]



![sandbox success copy result](https://github.com/user-attachments/assets/254916af-6ac7-438c-b57d-d6c25281db42)



이렇게 파일별 구분이 되면서 원하는대로 기능 구현 완성



### 🛠️ 트러블슈팅

## 트러블슈팅 1

![sandbox trouble shooting1](https://github.com/user-attachments/assets/91c70b39-acfc-449b-af13-4c5d3e38ab47)


GPT에 꼬리물기 시도

![sandbox trouble shooting1-1](https://github.com/user-attachments/assets/1a87d3bf-8b63-43bc-a01e-bbd68320bacf)



정리해보니 
1.file.inputStream.readText()
마켓 등록, JetBrains 공식 플러그인   => 선택

2.LocalFileSystem.getInstance().findFileByPath()
불안정

3.Path.of(file.path)  

하드코어(보안문제), 리눅스 or 윈도우 경로차이발생, 파일삭제시 예외 발생 가능
하드코어란..
인텔리제이 플랫폼을 완전무시하고 
운영체제(OS)의 로컬 파일 시스템에 직접 접근 
--> 인텔리제이가 허용하지 않음 -> 지양
--

## 트러블슈팅 2



![sandbox trouble shooting2 checking](https://github.com/user-attachments/assets/da5f8f44-8570-4b0a-b359-1e154112ebdb)


plugin.xml의 
<action>에 있는 group-id="ProjectViewPopupMenu로 변경


![sandbox trouble shooting 2-1](https://github.com/user-attachments/assets/f66487e2-d30b-48af-bc84-620c07917c9c)



![sandbox trouble shooting 2-2](https://github.com/user-attachments/assets/d112eeeb-8457-4dcf-8e30-d756401d3655)


해결


## 트러블슈팅 3


![sandbox trouble shooting3 error log](https://github.com/user-attachments/assets/17af5835-3058-4977-808e-db79c7a8f5ad)


분명 기능이 제대로 동작함에도 
다수의 파일 선택 후 오른쪽마우스를 눌러서 프로젝트 뷰팝업 메뉴를 띄우면 
저런 오류메시지가 출력되었다
기능은 제대로 동작하는데 왜??? 하고 찾아보니

**"ActionUpdateThread.OLD_EDT"가 곧 제거될 예정이므로,
플러그인 액션 클래스에서 명시적으로 `getActionUpdateThread()` 메서드를 오버라이드해라"**

라고 IntelliJ가 권장한다고 메시지를 띄우는 것

OLD.EDT가 무언가해서 또 찾아봄

 EDT vs BGT(Background)

- **EDT** : UI 이벤트 쓰레드 (UI 관련 작업)
- **BGT** : 백그라운드 (무거운 연산이나 I/O 등을 돌려도 되는 쓰레드)

플러그인 액션 대부분은 UI와 밀접해서 **EDT**가 적절

만약 파일 검색 같은 무거운 작업을 하면서 UI를 잠그고 싶지 않다면

**BGT**를 고려해볼 수도 있지만,

일반적인 **우클릭 메뉴 액션**이라면 `EDT`가 안전

그래서 코드를 새로 추가함 


![sandbox trouble shooting 3-1 add method](https://github.com/user-attachments/assets/adacfecc-8c3a-49bf-ad35-6cd30f91825d)


이후로 에러메시지는 표시되지 않았다.





















##  Sandbox 테스트 성공 후 로컬 설치 시 기능 미작동 문제   -> 해결 

**문제점:**  
Sandbox 환경에서는 플러그인이 정상적으로 작동했지만, 로컬 환경에서 직접 빌드하여 설치했을 때 기능이 제대로 동작하지 않는 문제가 발생했습니다.
-> 로컬 기준이 아니라 배포하게 될 Intelli J 권장설정대로 해줬어야 함 

<img width="759" height="706" alt="actionPerfomed Refactoring" src="https://github.com/user-attachments/assets/1b2d2dd2-b268-4b0a-8e35-4fb0d4042467" />


`
**원인 분석:**  
- Sandbox와 로컬 환경의 설정 차이 (O) -> IntelliJ 플랫폼 공식 권장 방식 (inputStream.bufferedReader) 
- 플러그인 메타데이터의 누락 또는 오류 (X)
- 의존성 문제   (X)

- 

**해결 시도:**  
- `plugin.xml` 파일의 설정을 재검토하고 수정
- Gradle 빌드 스크립트에서 의존성 및 버전 확인
- IntelliJ IDEA의 플러그인 로딩 방식 조사

**현재 상태:**  
여러 가지 시도를 했지만, 로컬 설치 시의 문제는 아직 완전히 해결되지 않았습니다. 앞으로도 지속적으로 원인을 분석하고 해결 방안을 모색할 예정입니다.  

    
- Email: berichmore@naver.com

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.



---



