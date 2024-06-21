#include <iostream>
#include <algorithm>
using namespace std;


int GCD(int x,int y){
    if(y==0) return x;
    else return GCD(y,x%y);
}
int main(){
    int a,b,c,d;
    cin>>a>>b>>c>>d;
    int m=b*d;
    int n=a*d+b*c;
    int k=GCD(m,n);
    cout<<n/k<<" "<<m/k;
    return 0;
}
