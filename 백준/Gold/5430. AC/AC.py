# 전체 test case의 p + n < 700000
T = int(input())  # T <= 100
for _ in range(T):
    p = input()  # 1 <= len(p) <= 100000
    n = int(input())  # 0 <= n <= 100000
    if n == 0:
        _arr = input()
        arr = []
    else:
        _arr = input()[1:-1]
        arr = list(map(int, _arr.split(',')))
    is_reversed = False
    front_index = 0
    back_index = n
    for func in p:
        if func == 'R':  # reverse func
            if is_reversed:
                is_reversed = False
            else:
                is_reversed = True
        else:   # discard func
            n -= 1
            if n < 0:   # error
                break
            if is_reversed:
                back_index -= 1
            else:
                front_index += 1
    if n < 0:   # error
        print("error")
    elif is_reversed:
        arr = arr[front_index:back_index]
        arr.reverse()
        print(str(arr).replace(" ", ""))
    else:
        print(str(arr[front_index:back_index]).replace(" ", ""))
