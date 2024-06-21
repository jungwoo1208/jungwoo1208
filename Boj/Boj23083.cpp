#include <iostream>
#include <vector>
using namespace std;
vector<pair<int,int>>d1={{-1,0},{-1,1},{0,1},{1,0},{0,-1},{-1,-1}};
vector<pair<int,int>>d2={{-1,0},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
int main(){
    int n,m;
    cin>>n>>m;
    int k;
    cin>>k;
    int map[n][m];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            map[i][j]=0;
        }
    }
    for(int i=0;i<k;i++){
        int x,y;
        cin>>x>>y;
        map[x][y]=2;
    }
}
