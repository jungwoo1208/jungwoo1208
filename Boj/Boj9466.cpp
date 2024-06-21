#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int main() {
    int t;
    cin >> t;
    while (t--) {
        int n;
        cin >> n;
        vector<int> arr(n + 1);
        vector<bool> visit(n + 1, false);
        for (int j = 1; j <= n; j++) {
            cin >> arr[j];
        }
        for (int j = 1; j <= n; j++) {
            if (visit[j]) continue;
            stack<int> stack;
            vector<bool> visit1(n + 1, false);
            bool flag= false;
            int start = j;
            stack.push(start);
            visit1[start]=true;
            while (!stack.empty()) {
                int k = stack.top();
                stack.pop();
                if(arr[k]==start){
                    flag = true;
                    break;
                }
                int next=arr[k];
                if (!visit[next]&&!visit1[next]) {
                    visit1[next] = true;
                    stack.push(next);
                }
            }
            if(flag) {
                for (int i = 1; i <= n; i++) {
                    if (visit1[i]) {
                        visit[i] = true;
                    }
                }
            }
        }
        int cnt = 0;
        for (int j = 1; j <= n; j++) {
            if (!visit[j]) cnt++;
        }
        cout << cnt << "\n";
    }
}
