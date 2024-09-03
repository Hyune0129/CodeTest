#include <iostream>

int main(void) {
	int T;
	std::cin >> T;
	unsigned long P[101];
	int N = 0;
	P[0] = 0;
	P[1] = 1;
	P[2] = 1;
	P[3] = 1;
	P[4] = 2;
	for (int i = 5; i <= 100; i++) {
		P[i] = P[i - 1] + P[i - 5];
	}
	for (int i = 0; i < T; i++) {
		std::cin >> N;
		std::cout << P[N] << std::endl;
	}

	return 0;
}