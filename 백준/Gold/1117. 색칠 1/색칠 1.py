W, H, f, c, x1, y1, x2, y2 = map(int, input().split())
area = W * H
painted_sum = 0 # 색칠된 면적
# W * H 크기의 직사각형

# f만큼 접기
if f > (W / 2):
    f = W - f # 0~f => 세로로도 접힌 부분

y = y2 - y1
if f < x1:
    painted_sum += (x2 - x1) * y * (c + 1)
elif x2 < f:
    painted_sum += (x2-x1) * y * (c+1) * 2
else:
    painted_sum += (f - x1) * y * (c+1) * 2
    painted_sum += (x2 - f) * y * (c+1)

print(area - painted_sum)