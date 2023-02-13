n  = int(input())
m = int(input())
MAX = 100000000
graph = [[MAX] * (n+1) for _ in range(n+1)]
for _ in range(m):
    a,b,c = map(int, input().split())
    graph[a][b] = min(graph[a][b] , c)
for i in range(n+1):
    graph[i][i] = 0

for k in range(1, n+1):
    for a in range(1, n+1):
        for b in range(1, n+1):
            graph[a][b] = min(graph[a][b], graph[a][k] + graph[k][b])

for a in range(1, n+1):
    for b in range(1, n+1):
        if graph[a][b] != MAX:
            print(graph[a][b], end = ' ')
        else:
            print(0, end=' ')
    print()