### What?

- 로또 번호 1-45 숫자 사이 6개 숫자 뽑기
- 당첨 번호 추첨
    - 1-45 숫자 사이 6개 숫자 뽑기, 보너스 번호 뽑기
- 번호 일치 개수 계산
- 사용자 입력
    - 구입 금액
      - 금액 % 1000 ≠ 0 이면 예외 처리
      - long 범위 보다 크면 예외 처리
    - 당첨 번호 쉼표 기준으로 6개
      - , (쉼표)사이 null이면 예외 처리
      - 쉼표와 숫자(1-45)말고 다른 문자 나오면 예외 처리
      - 중복된 숫자 예외 처리
    - 보너스 번호 1개
      - 당첨 번호와 중복되면 예외 처리
      - 1-45 사이 숫자 아니면 예외 처리

- 금액 / 1000 만큼 로또 개수 계산


- 출력
    - “X”개 구매했습니다. 출력
    - [X,X,X,X,X,X] 로또 번호를 로또 수량 만큼 오름차순 정렬하여 출력
    - 당첨 내역 출력
        - 일치한 개수 당 몇 개 있는지
    - 수익률 출력

- 수익률 계산
    - 소수점 둘째 자리에서 반올림

• 예외 상황 시 에러 문구를 출력해야 한다. 단, 에러 문구는 "[ERROR]"로 시작해야 한다.

### How
#### 1. 사용자 입력 처리
- `InputView`에서 사용자 입력 받기
  - `purchaseAmount()`: 구입 금액 입력, NumberFormatException 처리
  - `winningNumbers()`: 당첨 번호 문자열 입력
  - `inputBonusNumber()`: 보너스 번호 입력, NumberFormatException 처리

#### 2. 구입 금액 검증 및 로또 개수 계산
- `DivisibleAmount.divisibleByThousand()`
  - 금액이 1000으로 나누어떨어지는지 검증
  - 나누어떨어지지 않으면 IllegalArgumentException 발생
  - 로또 개수 반환 (금액 / 1000)

#### 3. 로또 발행
- `LottoMachine.drawLotto(lottoCount)`
  - `Randoms.pickUniqueNumbersInRange(1, 45, 6)`로 중복 없는 6개 번호 생성
  - 생성된 번호로 `Lotto` 객체 생성
  - lottoCount만큼 반복하여 List에 저장

#### 4. 당첨 번호 입력 및 검증
- `LottoController.parseNumbers()`
  - 쉼표로 split하여 문자열 배열 생성
  - 각 문자열을 trim 후 Integer로 파싱
  - List<Integer>로 변환하여 Lotto 객체 생성
- `ValidateWinningNumber.validateWinningNumber()`
  - 6개인지 검증
  - 중복 없는지 검증 (stream().distinct().count())
  - 1-45 범위 내인지 검증
- `Lotto` 생성자에서 추가 검증
  - 6개, 중복 없음, 1-45 범위 확인

#### 5. 보너스 번호 입력 및 검증
- `ValidateBonusNumber.validateBonusNumber()`
  - 1-45 범위 내인지 검증
  - 당첨 번호와 중복되지 않는지 검증 (contains 사용)

#### 6. 당첨 결과 계산
- `LottoResult` 클래스
  - `countMatchingNumbers()`: 사용자 로또와 당첨 로또의 일치 개수 계산
    - stream().filter()로 당첨 번호에 포함된 번호만 필터링
    - count()로 개수 반환
  - `isMatchBonus()`: 보너스 번호 일치 여부 확인
    - contains()로 보너스 번호 포함 여부 확인
  - `calculateRank()`: 일치 개수와 보너스 일치 여부로 등수 결정
- `LottoRank` enum
  - 각 등수별 일치 개수, 보너스 일치 여부, 상금 저장
  - `valueOf(matchCount, matchBonus)`로 등수 반환

#### 7. 당첨 통계 집계
- `WinningStatistics` 클래스
  - EnumMap<LottoRank, Integer>로 각 등수별 당첨 개수 저장
  - `addResult(rank)`: 해당 등수의 count 증가
  - `calculateTotalPrize()`: 각 등수 상금 * 당첨 개수의 합계
  - `calculateProfitRate()`: (총 상금 / 구입 금액) * 100

#### 8. 결과 출력
- `OutputView.printWinningStatistics()`
  - 5등부터 1등까지 순회하며 출력
  - DecimalFormat("#,###")로 상금 포맷팅
  - printf로 "X개 일치 (X원) - X개" 형식 출력
  - 수익률은 "%.1f%%"로 소수점 첫째 자리까지 출력

#### 9. 예외 처리
- 모든 예외 메시지는 "[ERROR]"로 시작
- IllegalArgumentException 발생 시점
  - 금액이 1000원 단위가 아닐 때
  - 로또 번호가 6개가 아닐 때
  - 로또 번호에 중복이 있을 때
  - 로또 번호가 1-45 범위 밖일 때
  - 보너스 번호가 1-45 범위 밖일 때
  - 보너스 번호가 당첨 번호와 중복될 때
  - 숫자가 아닌 값 입력 시