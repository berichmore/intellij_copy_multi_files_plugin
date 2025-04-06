#  Multi Copy - IntelliJ Plugin

IntelliJ IDEA 플러그인으로, **여러 파일을 선택하고 그 내용을 한 번에 클립보드에 복사**할 수 있는 기능입니다.

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
| 테스트 환경 | ✅ Sandbox 실행 성공 / ⚠️ 로컬 플러그인 설치 실패
| 로컬 설치 테스트 | ⚠️ 부분 실패 (빌드 후 설치 시 기능 미작동)




---

### 📦 패키징 및 배포 관련
| 항목 | 값 |
|------|------|
| 플러그인 ID | `com.jay.intellij_copy_multi_files_plugin` |
| 플러그인 버전 | `1.0-SNAPSHOT` |
| 사용 언어 | Kotlin |
| 주요 기능 | 선택된 다수 파일의 내용을 클립보드에 병합 복사 |
| 플러그인 실행 위치 | Project View 우클릭 메뉴 (`alt + F` 단축키 지원) |
| 배포 준비 | `signPlugin`, `publishPlugin` 설정 포함 (`ENV 환경변수 기반`) |

---

## 기획 의도

개발 중 여러 파일의 내용을 한꺼번에 복사할 일이 많았습니다.  
IDE 외부에서 수작업으로 복사하는 과정을 줄이기 위해  
직접 IntelliJ 플러그인을 만들어보게 되었습니다.

왜 이걸 만들었나? 
인텔리제이를 사용함에 있어 파일 전체를 복사할 일이 자주 있었는데 매번 하나하나 열면서 복붙하는 과정에서 
귀찮음을 해결할 수 있지 않을까? 라는 생각이 들어 생각이 드는김에 시도해 보았습니다.
소요시간은 약 14시간 걸렸고 생각이 들었던 당일 (2025.04.05) 해냈습니다

## 아쉬움
샌드박스 테스트까지 성공했읕 때 기대에 부풀었지만 왜인지 
빌드 후 로컬에서 플러그인을 설치하고부터는 제대로 실행이 되질 않았습니다.
여러가지 서칭을 해보았지만 정확히 구현이 되지않아 아쉽지만 그럼에도 
내가 불편한 과정의 일을 내손으로 변화시키려했다는 시도와 
생소한 환경에서 코틀린, 샌드박스를 경험했다는 것에서 풍부한 경험을 할 수 있었습니다


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

---

## 📸 사용 예시

![원하는 파일선택 -> 오른쪽 마우스 -> 메뉴] 

![sandbox success menu](https://github.com/user-attachments/assets/6384cf86-3c5a-4333-9e0f-037bdc988a1c)


![복사 , 메모장 붙여넣기 후]



![sandbox success copy result](https://github.com/user-attachments/assets/254916af-6ac7-438c-b57d-d6c25281db42)



이렇게 파일별 구분이 되면서 원하는대로 기능 구현 완성




---



