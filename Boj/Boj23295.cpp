#include <iostream>
#include <vector>
using namespace std;
int main(){
    int n,t,k;
    cin>>n>>t;
    vector<int> time(100001,0);//time[i] = i초에 몇명이 가능한지
    for(int i=0;i<n;i++){
        cin>>k;
        for(int j=0;j<k;j++){
            int a,b;
            cin>>a>>b;
            time[a]++;
            time[b]--;
        }
    }
    for(int i=1;i<=100000;i++) {//스위핑 알고리즘
        time[i]+=time[i-1];
    }
    for(int i=1;i<=100000;i++) {//누적합 적용
        time[i]+= time[i-1];
    }
    int result = time[t-1];//t초까지 가능한 사람 수
    int start =0;//시작 시간
    int end =t;//끝나는 시간
    for(int i=1;i<=100000-t;i++) {//t초부터 시작해서 가장 많은 사람이 가능한 구간 찾기
        if(result < time[i+t-1]- time[i-1]) {//최대값 갱신
            start = i;
            end = i + t;
            result = time[i+t-1]- time[i-1];
        }
    }
    cout<< start <<" "<< end;//출력
    return 0;
}