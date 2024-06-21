#include <iostream>
#include <algorithm>
using namespace std;
int N, a, b;
int dp[1000001];
int solve(int i) {
    if (i == 0) {
        return 0;
    }
    else if (dp[i]) {
        return dp[i];
    }
    else {
        dp[i] = solve(i - 1) + 1;
        if (i>a) {
            dp[i] = min(dp[i], solve(i - a - 1) + 1);
        }
        if (i>b) {
            dp[i] = min(dp[i], solve(i - b - 1) + 1);
        }
        return dp[i];
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> a >> b;
    solve(N);
    cout << dp[N];
}