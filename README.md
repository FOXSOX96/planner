# Planner Project

> 일정과 댓글을 함께 관리할 수 있는 간단한 API 입니다.  
> Spring Boot + JPA 구조로 설계되어 있습니다.

---

# API Documents
## 일정 생성

> 새로운 일정을 등록합니다.

**HTTP Method:** `POST`  
**URL:** `/planners`

### Request

| 이름 | 타입 | 설명 | 필수 |
|------|------|----|----|
| `title` | String | 제목 | O  |
| `contents` | String | 내용 | O |
| `name` | String | 작성자 | O  |
| `password` | String | 비밀번호 | O |

### Response

| 이름 | 타입 | 설명  |
|------|------|------|
| `id` | Long | 일정 ID |
| `title` | String | 제목  |
| `contents` | String | 내용  |
| `name` | String | 작성자 |
| `createdAt` | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## 일정 전체 조회

> 전체 일정 또는 특정 작성자(name)의 일정 목록을 조회합니다.

**HTTP Method:** `GET`  
**URL:** `/planners`  
**Query Parameter:** `name` (optional)

### Request

| 이름 | 타입 | 설명 |
|------|------|------|
| `name` | String | 특정 작성자 이름 (없으면 전체 조회) |

### Response
| 이름 | 타입 | 설명  |
|------|------|------|
| `id` | Long | 일정 ID |
| `title` | String | 제목  |
| `contents` | String | 내용  |
| `name` | String | 작성자 |
| `createdAt` | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## 선택 일정 조회

> 일정 하나를 조회하고, 해당 일정에 달린 **댓글 목록**도 함께 반환합니다.

**HTTP Method:** `GET`  
**URL:** `/planners/{plannerId}`

### Response

일정

| 이름 | 타입 | 설명  |
|------|------|------|
| `id` | Long | 일정 ID |
| `title` | String | 제목  |
| `contents` | String | 내용  |
| `name` | String | 작성자 |
| `createdAt` | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

댓글

| 이름           | 타입 | 설명    |
|--------------|------|-------|
| `id`         | Long | 댓글 ID |
| `plannerId`  | Long | 일정 ID |
| `contents`   | String | 내용    |
| `name`       | String | 작성자   |
| `createdAt`  | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## 일정 수정

> 일정의 내용을 수정합니다.  
> 비밀번호 일치 시에만 수정됩니다.

**HTTP Method:** `PATCH`  
**URL:** `/planners/{plannerId}`

### Request

| 이름 | 타입 | 설명 | 필수 |
|------|------|----|----|
| `title` | String | 제목 | O  |
| `contents` | String | 내용 | O |
| `name` | String | 작성자 | O  |
| `password` | String | 비밀번호 | O |

### Response

| 이름 | 타입 | 설명  |
|------|------|------|
| `id` | Long | 일정 ID |
| `title` | String | 제목  |
| `contents` | String | 내용  |
| `name` | String | 작성자 |
| `createdAt` | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## 일정 삭제

> 비밀번호가 일치할 경우 일정을 삭제합니다.  
> 해당 일정의 모든 댓글도 함께 삭제됩니다.

**HTTP Method:** `DELETE`  
**URL:** `/planners/{plannerId}`

### Request 
| 이름 | 타입 | 설명 |
|------|------|------|
| `password` | String | 비밀번호 (검증용) |

---

## 댓글 생성

> 특정 일정에 댓글을 등록합니다.  
> 댓글은 일정당 최대 10개까지 등록 가능합니다.

**HTTP Method:** `POST`  
**URL:** `/planners/{plannerId}/comments`

### Request
| 이름 | 타입 | 설명 |
|------|------|----|
| `contents` | String | 내용 |
| `name` | String | 작성자 |
| `password` | String | 비밀번호 |

### Response
| 이름           | 타입 | 설명    |
|--------------|------|-------|
| `id`         | Long | 댓글 ID |
| `plannerId`  | Long | 일정 ID |
| `contents`   | String | 내용    |
| `name`       | String | 작성자   |
| `createdAt`  | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## 댓글 조회

> 특정 일정에 달린 모든 댓글을 조회합니다.

**HTTP Method:** `GET`  
**URL:** `/planners/{plannerId}/comments`

### Response
| 이름           | 타입 | 설명    |
|--------------|------|-------|
| `id`         | Long | 댓글 ID |
| `plannerId`  | Long | 일정 ID |
| `contents`   | String | 내용    |
| `name`       | String | 작성자   |
| `createdAt`  | LocalDateTime | 생성 시각 |
| `modifiedAt` | LocalDateTime | 수정 시각 |

---

## ERD 구조

플래너(planners)와 댓글(comments)은 1:N 관계입니다.

| Table    | 설명        |
|----------|-----------|
| planners | 일정 정보     |
| comments | 일정에 달린 댓글 |

| **planners** | 
|--------------|
| created_at   |
| modified_at  | 
| title        | 
| contents     | 
| name         | 
| password     | 
| id (PK)      | 

^   
| planner_id:id

| **comments**    | 
|-----------------|
| created_at      |
| modified_at     | 
| planner_id (FK) | 
| contents        | 
| name            | 
| password        | 
| id (PK)         | 

---

## 주요 기능

### Planner (일정)

| 기능       | HTTP Method | URL              | 설명                     |
|----------|-------------|------------------|------------------------|
| 일정 생성    | `POST`      | `/planners`      | 일정 등록                  |
| 선택 일정 조회 | `GET`       | `/planners/{id}` | 특정 일정 상세 + 댓글 목록       |
| 전체 일정 조회 | `GET`       | `/planners`      | 전체 일정 상세 (이름값 입력시 필터링) |
| 일정 수정    | `PATCH`     | `/planners/{id}` | 특정 일정 수정               |
| 일정 삭제    | `DELETE`    | `/planners/{id}` | 특정 일정 삭제 + 연관 댓글 모두 삭제 |

---

### Comment (댓글)

| 기능       | HTTP Method | URL                              | 설명              |
|----------|-------------|----------------------------------|-----------------|
| 댓글 생성    | `POST`      | `/planners/{plannerId}/comments` | 특정 일정에 댓글 추가    |
| 댓글 목록 조회 | `GET`       | `/planners/{plannerId}/comments` | 해당 일정의 모든 댓글 조회 |

---