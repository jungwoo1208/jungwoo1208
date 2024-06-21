#include <iostream>
#include <vector>
#include <iomanip>
using namespace std;

int main() {
    int N;
    cin >> N;

    vector<vector<double>> matrix(N, vector<double>(N));
    vector<vector<double>> L(N, vector<double>(N, 0));
    vector<vector<double>> U(N, vector<double>(N, 0));
    vector<int> P(N);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> matrix[i][j];
        }
    }
    for (int i = 0; i < N; i++) {
        L[i][i] = 1;
        P[i] = i;
    }

    for (int k = 0; k < N; k++) {
        double maxVal = 0;
        int maxIdx = k;
        for (int i = k; i < N; i++) {
            if (abs(matrix[i][k]) > maxVal) {
                maxVal = abs(matrix[i][k]);
                maxIdx = i;
            }
        }
        if (maxVal == 0) {
            cout << -1;
            return 0;
        }
        swap(P[k], P[maxIdx]);
        swap(matrix[k], matrix[maxIdx]);
        for (int i = k + 1; i < N; i++) {
            L[i][k] = matrix[i][k] / matrix[k][k];
            for (int j = k; j < N; j++) {
                matrix[i][j] -= L[i][k] * matrix[k][j];
            }
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (j < i) {
                L[i][j] = matrix[i][j];
            } else {
                U[i][j] = matrix[i][j];
            }
        }
    }
    cout << fixed << setprecision(3);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << L[i][j] << " ";
        }
        cout << endl;
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << U[i][j] << " ";
        }
        cout << endl;
    }

    return 0;
}
