from collections import deque
"""
2 <= M <= 100
2 <= N <= 100
1 <= H <= 100

N개의 줄에서 M개의 정수로 입력받음
1 = 익은 토마토
0 = 익지 않은 토마토
-1 = 토마토 x

"""
M, N, H = map(int, input().split())
boxes = []  # boxes[h][n][m]
riped_tomatoes = []  # 익은 토마토의 좌표 입력
counter = 0  # 날마다 변경된 토마토
updated_tomatoes = [0]
not_riped_tomatoes = 0  # call by ref를 위해 list로 설정
# 위, 아래, 왼쪽, 오른쪽, 뒤, 앞
m_direction = [0, 0, -1, 1, 0, 0]
n_direction = [0, 0, 0, 0, -1, 1]
h_direction = [1, -1, 0, 0, 0, 0]
for i in range(H):
    box = []
    for j in range(N):
        temp = list(map(int, input().split()))
        for k in range(M):
            if temp[k] == 1:
                riped_tomatoes.append((i, j, k))
            elif temp[k] == 0:
                not_riped_tomatoes += 1
        box.append(temp)
    boxes.append(box)


def bfs(_boxes: list, _riped_tomatoes: list, _updated_tomatoes: list):
    """
    queue를 이용한 bfs 사용.
    queue에는 tuple 형식으로 (h, n, m) 형태로 저장하여
    0인 값 갱신시 현재 좌표의 숫자 + 1로하고, 최댓값(day)인지 확인한다.
    """
    _day = 0
    queue = deque(_riped_tomatoes)
    while len(queue) != 0:
        _h, _n, _m = queue.popleft()
        for u in range(6):
            if 0 <= _h + h_direction[u] < H and 0 <= _n + n_direction[u] < N and 0 <= _m + m_direction[u] < M and \
                    _boxes[_h + h_direction[u]][_n + n_direction[u]][_m + m_direction[u]] == 0:
                boxes[_h + h_direction[u]][_n + n_direction[u]][_m + m_direction[u]] = \
                    boxes[_h][_n][_m] + 1
                queue.append((_h + h_direction[u], _n + n_direction[u], _m + m_direction[u]))
                _day = max(_day, boxes[_h][_n][_m])
                _updated_tomatoes[0] += 1
    return _day


day = bfs(boxes, riped_tomatoes, updated_tomatoes)

if not_riped_tomatoes - updated_tomatoes[0] > 0:
    print(-1)
else:
    print(day)
