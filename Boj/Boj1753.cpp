#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int v, e, k;
vector<vector<int>> arr;
vector<int> visited;
vector<vector<int>> vec;

int dp() {
    priority_queue<pair<int, int>> pq;
    pq.push({0, k});
    while (!pq.empty()) {
        int cost = -pq.top().first;
        int cur = pq.top().second;
        pq.pop();
        if (visited[cur] != 0) continue;
        visited[cur] = cost;
        for (int i = 0; i < vec[cur].size(); i++) {
            int next = vec[cur][i];
            if (visited[next] != 0) continue;
            pq.push({-(cost + arr[cur][next]), next});
        }
    }
    for (int i = 1; i <= v; i++) {
        if (i == k) {
            cout << 0 << endl;
            continue;
        }
        if (visited[i] == 0) {
            cout << "INF" << endl;
        } else {
            cout << visited[i] << endl;
        }
    }
    return 0;
}

int main() {
    cin >> v >> e;
    cin >> k;
    vec.resize(v + 1);
    arr.resize(v + 1, vector<int>(v + 1, 0));
    visited.assign(v + 1, 0);
    for (int i = 0; i < e; i++) {
        int x, y, n;
        cin >> x >> y >> n;
        vec[x].push_back(y);
        arr[x][y] = n;
    }
    dp();
    return 0;
}
