"""
입력받을때 R -> G로 replace하는 배열, 입력받은 배열 2개로 bfs를 사용하여 개수를 따로 나타낸다.
1 <= N <= 100
"""
from collections import deque

N = int(input())
area = []
red_green_area = []
# 상하좌우
x_direction = [0, 0, -1, 1]
y_direction = [-1, 1, 0, 0]
for _ in range(N):
    temp = input()
    area.append(list(temp))
    red_green_area.append(list(temp.replace('R', 'G')))
# call by ref를 위한 list로 count
count = [0]
red_green_count = [0]


def bfs(_area: list, x: int, y: int, _count: list):
    another_queue = deque()
    _queue = deque()
    color = _area[x][y]
    _count[0] += 1
    _queue.append((x, y))
    while _queue:
        _x, _y = _queue.popleft()
        if _area[_x][_y] == 'C':
            continue
        _area[_x][_y] = 'C'  # checked
        for i in range(4):
            if not (0 <= _x + x_direction[i] < N and 0 <= _y + y_direction[i] < N)\
                    or _area[_x + x_direction[i]][_y + y_direction[i]] == 'C':
                #  범위를 넘은 경우
                continue
            if _area[_x + x_direction[i]][_y + y_direction[i]] != color:
                another_queue.append((_x + x_direction[i], _y + y_direction[i]))
            else:
                _queue.append((_x + x_direction[i], _y + y_direction[i]))
    while another_queue:
        _x, _y = another_queue.popleft()
        if _area[_x][_y] == 'C':
            continue
        bfs(_area, _x, _y, _count)


bfs(area, 0, 0, count)
bfs(red_green_area, 0, 0, red_green_count)
print(count[0], red_green_count[0])
