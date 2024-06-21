#include <iostream>
#include <vector>
#include <sstream>
using namespace std;
int main(){
    int n;
    int result=0;
    string s;
    cin >> n;
    cin.ignore();
    getline(cin, s);
    vector<int> tokens;
    int token;
    stringstream ss(s);
    while (ss >> token) {
        tokens.push_back(token);
    }
    for(int i=1;i<n-1;i++){
        vector<int> arr;
        arr=tokens;
        int k=arr[i];
        for(int j=0;j<n;j++){
            arr[j]-=k;
        }
        int idx1=i-1,idx2=i+1;
        while(!(idx1==-1&&idx2==i+1)){
            if(arr[idx1]==-1*arr[idx2]) {
                result++;
            }
            idx2++;
            if(idx2==n) {
                idx1--;
                idx2=i+1;
            }
        }
    }
    cout<<result;
    return 0;
}
