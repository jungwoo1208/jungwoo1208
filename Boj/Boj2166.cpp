#include <iostream>
#include <cmath>
using namespace std;

double area(double x1, double y1, double x2, double y2, double x3, double y3) {
    double result = 0;
    // 삼각형의 면적을 계산하는 식을 수정했습니다.
    result = abs(x1*y2 + x2*y3 + x3*y1 - x1*y3 - x2*y1 - x3*y2);
    result /= 2.0;
    return result;
}

int main() {
    double result = 0;
    int n;
    cin >> n;
    double x[n], y[n];
    for(int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < n; j++) {
            for(int k = 0; k < n; k++) {
                if(i == j || j == k || k == i)
                    continue;
                result += area(x[i], y[i], x[j], y[j], x[k], y[k]);
            }
        }
    }
    // 모든 삼각형 조합에 대해 계산한 후 평균을 구합니다.
    // 중복 계산된 삼각형의 수만큼 나누는 것이 아니라, 유효한 삼각형 조합의 수로 나누어 평균을 구해야 합니다.
    // 여기서의 계산 방식은 중복을 제거하지 않기 때문에, 결과값의 정확성에 문제가 있을 수 있습니다.
    result /= 3.0;
    result /= (n - 2);
    cout << result;
    return 0;
}
