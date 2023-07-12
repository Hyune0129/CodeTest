import math

N = int(input())  # 0 <= N <= 500000
M = int(input())  # 0 <= M <= 10
if M != 0:
    numbers_list = list(map(int, input().split()))
else:
    numbers_list = []
numbers_map = [1 for i in range(10)]
for item in numbers_list:
    numbers_map[item] = 0  # 사용 못하는 것이 0으로 설정
chanel = 100
min_count = abs(N - chanel)  # 기본 채널 100에서 +, -인 경우
result = min_count

# brute force -> 목표 채널로부터 +1, -1을 각각 한다. 한 다음에 해당 숫자가 가능한지 체크.
def is_num_have(number: int, map: list, num_count: int, num_length: int):  # num_count starts 0
    if num_length <= num_count:
        return True
    digit = number // (10 ** (num_length - num_count - 1))  # 해당 자릿수의 숫자
    if map[digit] == 1:
        return is_num_have(number - (digit * (10 ** (num_length - num_count - 1))), map, num_count + 1, num_length)
    else:
        return False


def get_length(num: int):
    if num == 0:
        return 1
    return int(math.log10(num)) + 1


for i in range(min_count):
    if N - i >= 0:
        length = get_length(N - i)
        if is_num_have(N - i, numbers_map, 0, length):
            result = min(length + i, min_count)
            break
    length = get_length(N + i)
    if is_num_have(N + i, numbers_map, 0, length):
        result = min(length + i, min_count)
        break
print(result)
