#include <iostream>
using namespace std;
int T,f[1000001];
long long s[1000001];
int main(){
    cin>>T;
    for(int i=1;i<=1000000;i++)
        for(int j=i;j<=1000000;j+=i) f[j]+=i;
    for(int i=1;i<=1000000;i++) s[i]=f[i]+s[i-1];
    for(int i=0;i<T;i++){
        int n;
        cin>>n;
        cout<<s[n]<<"\n";
    }
}