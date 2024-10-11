#include <string>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;
int getMaxDay(vector<string> &maps, vector<vector<bool>> &isVisited, int row, int col);

vector<int> solution(vector<string> maps) {
    vector<int> answer;
    int n = maps.size();
    int m = maps[0].length();
    vector<vector<bool>> isVisited(n, vector<bool>(m, false));
    for(int row = 0; row < n; row++) {
        for(int col = 0; col < m; col++) {
            if(isVisited[row][col]) {
                continue;
            }
            isVisited[row][col] = true;
            if(maps[row][col] == 'X') {
                continue;
            }
            answer.push_back(getMaxDay(maps, isVisited, row, col));
        }
        
    }
    
    if(answer.empty()) {
        answer.push_back(-1);
    }
    std::sort(answer.begin(), answer.end());
    return answer;
}
int getMaxDay(vector<string> &maps, vector<vector<bool>> &isVisited, int row, int col) {
    const int dx[4] = {-1, 1, 0, 0};
    const int dy[4] = {0, 0, -1, 1};
    
    int day = maps[row][col] - '0';
    queue<pair<int, int>> q;
    q.push(make_pair(row, col));
    while(!q.empty()) {
        pair<int, int> pos = q.front();
        q.pop();
        int y = pos.first;
        int x = pos.second;
        for(int d=0; d<4; d++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;
            if(ny < 0 || ny >= maps.size() || nx < 0 || nx >= maps[0].length()) {
                continue;
            }
            if(isVisited[ny][nx]) {
                continue;
            }
            isVisited[ny][nx] = true;
            if(maps[ny][nx] == 'X') {
                continue;
            }
            q.push(make_pair(ny, nx));
            day += maps[ny][nx] - '0';
            
        }
    }
    return day;
}

