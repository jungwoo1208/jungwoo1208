#include <iostream>
#include <vector>
using namespace std;
int main(){
    int n,k;
    cin>>n;
    cin>>k;
    vector<int>v;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            v.push_back(i*j);
        }
    }

    return 0;
}