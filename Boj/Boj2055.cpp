#include <iostream>
using namespace std;
long long combination(int k,int j){
    if(k<j){
        return 0;
    }
    long long result=1;
    int a=k;
    int b=1;
    while (!(a<=k-j&&b>j)){
        if(a>k-j){
            result*=a;
            a--;
        }
        if(b<=j){
            result/=b;
            b++;
        }
    }
    return result;
}
int main(){
    int n,m;
    cin>>n>>m;
    n++;
    m++;
    long long res=0;
    res+= combination(m*n,3);
    res-= combination(n,3)*m;
    res-= combination(m,3)*n;
    cout<<res;
}