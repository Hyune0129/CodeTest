#include <iostream>

int main(void) {
  int n;
  std::cin >> n;
  long *arr = new long[n + 1];
  for (int i = 0; i <= n; i++) {
    arr[i] = 0;
  }
  arr[1] = 1;
  arr[2] = 3;
  for (int i = 3; i <= n; i++) {
    arr[i] += arr[i - 1];
    arr[i] += arr[i - 2] * 2;
    arr[i] %= 10007;
  }
  std::cout << arr[n];
  return 0;
}