#include <iostream>
#include <vector>
#include <math.h>
using namespace std;
int n;
vector<int>vec;
vector<bool>primeV;
int primeNum[1000001];
void prime(){
    for (int i = 2; i <= 1000001; i++){
        primeNum[i] = i;
    }
    for (int i = 2; i <= sqrt(1000001); i++){
        if (primeNum[i] == 0)
            continue;
        for (int j = i * i; j <= 1000001; j += i)
            primeNum[j] = 0;
    }
}
int main(){
    cin>>n;
    prime();
    int pcnt=0;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        vec.push_back(num);
        if(primeNum[num]!=0){
            pcnt++;
            primeV.push_back(true);
        } else{
            primeV.push_back(false);
        }
    }

}