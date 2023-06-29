N = int(input())
array = list(map(int, input().split()))
S = int(input())


counter = 0
while S > 0:
    if counter >= len(array) - 1:
        break
    index = counter
    max_num = array[index]
    if counter + S >= len(array) - 1: # s가 49이상이면 1~50 가능
        ran = len(array)
    else:
        ran = counter + S + 1
    for i in range(counter, ran):
        if max_num < array[i]:
            max_num = array[i]
            index = i

    array.pop(index)
    array.insert(counter, max_num)
    S -= index - counter
    counter += 1


for i in array:
    print(i, end=' ')