N = int(input())
crains = list(map(int, input().split()))
M = int(input())
boxes = list(map(int, input().split()))
crains.sort()
boxes.sort()

def binary_search(value : int):
    left = 0
    right = len(boxes) - 1
    while left < right:
        middle = (left + right) // 2
        if value > boxes[middle]:
            left = middle + 1
        elif value < boxes[middle]:
            right = middle - 1
        else: # 같을 경우 -> 무조건 가장 최적의 값
            return middle
    # 같은 수가 없는 경우
    return right

time = 0
if boxes[-1] > crains[-1]:  # 가장 무거운 박스가 무게제한 초과
    print(-1)
else:
    while len(boxes) > 0:
        for i in range(-1, -(len(crains)) - 1, -1):  # 역순으로 조회
            for j in range(binary_search(crains[i]), -1, -1):  # 역순으로 조회
                if boxes[j] <= crains[i]:  # 허용 무게
                    boxes.remove(boxes[j])
                    break
        time += 1
    print(time)
