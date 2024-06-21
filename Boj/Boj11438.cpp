#include <iostream>
#include <vector>
#include <set>
using namespace std;
int main(){
    int n;
    cin>>n;
    vector<int>vec;
    vec.resize(n+1);
    vec[1]=1;
    for(int i=0;i<n-1;i++){
        int a,b;
        cin>>a>>b;
        vec[b]=a;
    }
    set<int>set;
    int t;
    cin>>t;
    for(int i=0;i<t;i++){
        int a,b;
        cin>>a>>b;
        while (true){
            if(!vec[a]==a){
                int size=set.size();
                set.insert(vec[a]);
                if(size==set.size()){
                    cout<<vec[a];
                    break;
                }
            }
            if(!vec[b]==b){
                int size=set.size();
                set.insert(vec[b]);
                if(size==set.size()){
                    cout<<vec[b];
                    break;
                }
            }
        }
    }
}
