"""
dynamic programming
O(C^2) -> C가 1000일때, 1000 + 999 + 998 + ... + 1 => C(C+1)/2 만큼 순회
"""

MAX_COST = 1000000  # cost가 100, people 1일때 1000까지?
MAX_PEOPLE = 1101  # 0~100의 입력값 -> 99 * 11 이라면 1000을 넘는다. 따라서 input 최댓값 +100하여 1101로 크기 지정
C, N = map(int, input().split())  # 적어도 C명 늘이기 위한 인원
arr = [MAX_COST] * (MAX_PEOPLE + 1)  # 배열의 index를 사람, value를 비용으로 하여 최소한의 비용으로 계산
for _ in range(N):  # 이 문제는 정수배만큼 계속 사용할 수 있으므로, 모든 값을 입력받고 계산
    cost, people = map(int, input().split())  # 비용, 그 비용으로 얻을 수 있는 사람
    arr[people] = min(cost, arr[people])

for i in range(1, MAX_PEOPLE):
    for j in range(i, MAX_PEOPLE):
        if i + j > MAX_PEOPLE:
            break
        arr[i + j] = min(arr[i + j], arr[i] + arr[j])
print(min(arr[C:]))
