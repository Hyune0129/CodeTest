MAX_WEIGHT = 100001
N, K = map(int, input().split())
bag = [0 for _ in range(K + 1)] # bag[weight]
for _ in range(N):
    temp_weight, temp_value = map(int, input().split())
    if temp_weight > K: # 가용 무게를 넘은 물건
        continue
    for j in range(K,0, -1):    # 입력한 물건에 대해 적용하여 max값을 업데이트
        if j + temp_weight <= K and bag[j] != 0:
            bag[j + temp_weight] = max(bag[j + temp_weight], bag[j] + temp_value)
    bag[temp_weight] = max(temp_value, bag[temp_weight]) # 다른 물건들과 업데이트 뒤에 입력한 물건 입력

print(max(bag))

"""
8번라인에서 range(1, K+1)로 하였으나, 이렇게 한다면 같은 물건을 두번 사용한다.
따라서, 물건이 한번만 사용될 수 있도록 조정
"""