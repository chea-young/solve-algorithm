# solve_Algorithm

## 알고리즘 Note
- 최단 경로를 찾는 경우
  - 음수 존재: 벨만포드
    - 알고리즘 참고: https://github.com/chea-young/solve_Algorithm/blob/master/%EB%B0%B1%EC%A4%80/11657.java 
  - 음수 존재 X: 다익스트라

## ./programmers

- https://programmers.co.kr/

### 순위 검색

    1. info 정보를 split()를 사용하여 list를 만들고 info에 대한 dict을 만든다.
    2. info 순서대로 number list에 넣고 해당 정보의 index를 추가하여 준다.
        - 예를 들면 'java backend junior pizza 150'가 첫 번쨰로 나왔을 때 java, backen, junior, pizza를 key로 value에 0을 넣어준다
    3. query 정보를 split()를 사용하여 query_info list를 만든다.
    4. -가 아닌 것들에 대한 정보만을 모아 해당 value를 찾아 그 list들 끼리 교집합을 찾아준다.
    5. 찾은 교집합 리스트에 수들이 quer_info[7]의 수보다 크거나 같으면 answer list에 추가하여 준다.

### 메뉴 리뉴얼 -> dict이 나을수도 -> 여러가지 시도해보기

    1. menu list와 order_people list를 만든다.
    2. for문에 orders를 넣어 menu list에 단품메뉴가 없으면 추가하고 order_people에 사람 번호를 추가
        - 혹은 단품메뉴에 있으면 menu list에서 해당 단품메뉴의 index를 찾아 order_people[index]에 사람 번호를 추가한다.
    3. for문에 course를 돌아가게 하여 코스메뉴 갯수로 만들어질 수 있는 코스요리후보들을 모두 구한다.
    4. 코스요리 후보들의 각 알파벳 마다의 menu list에 index를 구하여 oder_people에 해당하는 list를 찾고 교집합을 구한다.
    5. 해당 코스요리 후보들에서 가장 인기가 많은 후보들을 answer에 추가한다.
        -  만약에 가장 인기가 많은 후보가 1명일 경우 추가하지 않는다.

### pytest

    - pytest python파일 -s
    => 자세한 정보를 볼 수 있다.

### 참고하는 사이트 & 책


## C++

- 실행
- ```
  g++ -o 5073 5073.cpp
  PS C:\Users\user\Documents\00_cheayoung.lee\100_etc\solve_Algorithm\백준\c++> ./5073
  ```

<!---LeetCode Topics Start-->
# LeetCode Topics
## Array
|  |
| ------- |
| [3790-fruits-into-baskets-ii](https://github.com/chea-young/solve-algorithm/tree/master/3790-fruits-into-baskets-ii) |
## Binary Search
|  |
| ------- |
| [3790-fruits-into-baskets-ii](https://github.com/chea-young/solve-algorithm/tree/master/3790-fruits-into-baskets-ii) |
## Segment Tree
|  |
| ------- |
| [3790-fruits-into-baskets-ii](https://github.com/chea-young/solve-algorithm/tree/master/3790-fruits-into-baskets-ii) |
## Simulation
|  |
| ------- |
| [3790-fruits-into-baskets-ii](https://github.com/chea-young/solve-algorithm/tree/master/3790-fruits-into-baskets-ii) |
## Ordered Set
|  |
| ------- |
| [3790-fruits-into-baskets-ii](https://github.com/chea-young/solve-algorithm/tree/master/3790-fruits-into-baskets-ii) |
<!---LeetCode Topics End-->