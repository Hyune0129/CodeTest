import copy ## deepcopy를 위한 모듈
from collections import deque
dx = [0,0,-1,1]
dy = [-1,1,0,0]
def bfs(graph,virus,min_virus):
    vcount = len(virus)  # virus 카운트
    for start in virus:  ## virus 좌표를 start 지점으로
        queue = deque()
        queue.append((start[0],start[1]))
        while queue:    ##queue가 존재할 때까지 
            y, x = queue.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]
                if nx < 0 or ny < 0 or nx >= m or ny >= n:       # 해당 graph 초과일 경우 무시
                    continue
                elif graph[ny][nx] == 2 or graph[ny][nx] == 1: # 벽, 바이러스일 경우 무시
                    continue
                graph[ny][nx] = 2
                vcount += 1
                if vcount >= min_virus:
                    return min_virus
                queue.append((ny,nx))
    return vcount

def getArea(graph):
    virus = [] #(y, x)인 바이러스 좌표를 넣을 공간
    space = [] #(y, x)인 빈 공간 좌표를 넣을 공간
    wall = 0
    for x in range(m):   ## 순회하며 virus 좌표 표시 및 벽 개수 카운트 ## 시간복잡도 n * m
        for y in range(n):
            if graph[y][x] == 1:
                wall += 1
            elif graph[y][x] == 2:
                virus.append((y,x))
            else:
                space.append((y,x))
    
    
    min_virus = m*n-wall   # MAX
    for i in range(len(space)-2):               ## n^3
        for j in range(i+1,len(space)-1):
            for k in range(j+1, len(space)):
                cgraph = copy.deepcopy(graph)
                cgraph[space[i][0]][space[i][1]] = 1
                cgraph[space[j][0]][space[j][1]] = 1
                cgraph[space[k][0]][space[k][1]] = 1
                temp = bfs(cgraph,virus,min_virus)
                min_virus = min(min_virus, temp)
    return m * n - min_virus - wall - 3

n,m = map(int, input().split())
graph = []
for x in range(n):
    graph.append(list(map(int, input().split())))
   
print(getArea(graph))