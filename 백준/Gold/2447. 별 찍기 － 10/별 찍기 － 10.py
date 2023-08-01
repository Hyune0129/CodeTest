"""
재귀를 활용한 별그리기.
분할정복법을 생각하며 작성.
"""
N = int(input())  # N = 3^k and 1 <= k < 8
matrix = [['*'] * N for _ in range(N)]  # 기본적으로 *로 다 채운 배열 생성


def make_star(n: int, sx, sy, ex, ey, _matrix: list):
    """
    가운데의 *를 공백으로 변경
    """
    dn = n // 3
    for i in range(sy + dn, ey - dn):
        for j in range(sx + dn, ex - dn):
            _matrix[i][j] = ' '
    if n == 3:  # n == 3일때는 재귀를 할 필요가 없다.
        return
    for i in range(3):  # y
        for j in range(3):  # x
            if i == 1 and j == 1:
                continue
            make_star(n // 3, sx + j * dn, sy + i * dn, sx + (j + 1) * dn, sy + (i + 1) * dn, _matrix)


make_star(N, 0, 0, N, N, matrix)
for item in matrix:
    print("".join(item))
