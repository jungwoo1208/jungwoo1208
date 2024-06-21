#include <iostream>
#include <cmath>
using namespace std;

void primeNumberSieve(int k,int n){
    int number = n;
    int primeNum[n];
    for (int i = 2; i <= number; i++)
    {
        primeNum[i] = i;
    }

    for (int i = 2; i <= sqrt(number); i++)
    {
        if (primeNum[i] == 0)
            continue;
        for (int j = i * i; j <= number; j += i)
            primeNum[j] = 0;
    }
    for(int i=k;i<=n;i++){
        if(primeNum[i]!=0)
            cout<<primeNum[i]<<endl;
    }
}
int main() {
    int a,b;
    cin>>a>>b;
    primeNumberSieve(a,b);
    return 0;
}
