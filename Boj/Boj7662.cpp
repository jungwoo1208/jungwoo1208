#include <iostream>
#include <queue>
#include <vector>
using namespace std;
int main(){
    int t,k;
    cin>>t;
    for(int i=0;i<t;i++){
        cin>>k;
        int pcnt=0;
        int acnt=0;
        if(k==0){
            cout<<0<<endl;
            continue;
        }
        priority_queue<int, vector<int>, greater<int>> pq1;
        priority_queue<int, vector<int>, less<int>> pq2;
        for(int j=0;j<k;j++){
            char c;
            int n;
            cin>>c>>n;
            if(c=='I') {
                pq1.push(n);
                pq2.push(n);
                pcnt++;
            }else{
                if(n==1){
                    if(pq2.empty()) continue;
                    pq2.pop();
                }else{
                    if(pq1.empty()) continue;
                    pq1.pop();
                }
                acnt++;
            }
        }
        if(pcnt<=acnt)cout<<"EMPTY"<<endl;
        else cout<<pq2.top()<<" "<<pq1.top()<<endl;
    }
}