N = int(input())
l = [0] * (N + 2)
T = [0] * (N + 2)
P = [0] * (N + 2)
for i in range(1,N+1):
    T[i], P[i] = (map(int, input().split()))

for i in range(1, N+2):
    l[i] = max(l[i-1], l[i])
    if i + T[i] <= N + 1:
        l[i + T[i]] = max(l[i + T[i]], P[i] + l[i])
print(l[N+1])