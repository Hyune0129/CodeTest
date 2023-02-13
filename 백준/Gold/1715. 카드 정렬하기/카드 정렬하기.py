def binary_search(size, cards, data):
    right = size - 1
    left = 0
    middle = size // 2
    while True:    
        if (right - left) < 1 :
            if cards[middle] <= data:
                cards.insert(middle,data)
            else:
                cards.insert(middle + 1,data)
            return
        if cards[middle] < data:  ## data가 크면 왼쪽으로
            right = middle
            
        elif cards[middle] > data: ## data가 작으면 오른쪽으로
            left = middle + 1
            
        else: ## 같을 시( 해당하는 곳에 insert )
            cards.insert(middle, data)
            return
        middle = (right + left) // 2
        

## pop의 연산. pop할시 shift 연산 필요 따라서 reverse.
def card(cards):
    answer = 0
    cards.sort(reverse = True) # quicksort
    if len(cards) == 1: ## 1개는 비교할 것이 없으므로, 0을 출력
        return answer
    while True:
        sum = cards.pop() + cards.pop()
        answer += sum
        if len(cards) != 0:
            binary_search(len(cards), cards, sum)
        else:
            return answer
    
cards = []
n = int(input())
for _ in range(n):
    cards.append(int(input()))
print(card(cards))