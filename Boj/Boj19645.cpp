#include <iostream>
#include "algorithm"
using namespace std;
bool compare(int a,int b){
    return a>b;
}
int main(){
    int n;
    cin>>n;
    int arr[n];
    for(int i=0;i<n;i++)
        cin>>arr[i];
    sort(arr,arr+n, compare);
    int res[3];
    fill_n(res,3,0);
    for(int i=0;i<n;i++){
        if(res[2]>=res[1]||res[2]>=res[0]){
            if(res[0]>=res[1]){
                res[1]+=arr[i];
            } else{
                res[0]+=arr[i];
            }
        }else{
            res[2]+=arr[i];
        }
    }
    cout<<res[2];
}