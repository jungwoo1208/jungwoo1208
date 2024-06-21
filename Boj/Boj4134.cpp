#include <iostream>
using namespace std;
bool confirm(int k) {
    bool confirm = false;
    for (int i = 2; i < k; i++) {
        if (k % i == 0) {
            confirm = true;
            break;
        }
    }
    return confirm;
}

int main(){
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        int k;
        cin >> k;
        while (confirm(k)) {
            k++;
        }
        cout << k << endl;
    }
    return 0;
}