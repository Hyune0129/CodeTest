#include <iostream>
#include <string>

int main(void) {
  std::string str;
  std::cin >> str;
  int sum = 0;
  int start = 0;
  bool haveMinus = false;
  for (int i = 0; i < str.size(); i++) {
    if (str[i] == '-' || str[i] == '+') {

      if (haveMinus) {
        sum -= std::stoi(str.substr(start, i));
      } else {
        sum += std::stoi(str.substr(start, i));
      }
      start = i + 1;
      if (str[i] == '-') {
        haveMinus = true;
      }
    }
  }
  if (haveMinus) {
    sum -= std::stoi(str.substr(start, str.size()));
  } else {
    sum += std::stoi(str.substr(start, str.size()));
  }

  std::cout << sum;
  return 0;
}