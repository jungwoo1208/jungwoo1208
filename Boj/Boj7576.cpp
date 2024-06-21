#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int dx[4] = {0, 0, 1, -1};
int dy[4] = {1, -1, 0, 0};
int m,n;
int result=0;

queue<pair<pair<int,int>, int>> q;
vector<vector<int>> arr;

void dp(){
    while(!q.empty()){
        int x=q.front().first.first;
        int y=q.front().first.second;
        int day=q.front().second;
        q.pop();

        for(int i=0; i<4; i++){
            int nx=x+dx[i];
            int ny=y+dy[i];
            int tmp_day=day+1;

            if(nx>=0 && nx<n && ny>=0 && ny<m){
                if(arr[nx][ny]==0){
                    arr[nx][ny]=1;
                    q.push({{nx,ny}, tmp_day});
                    result=max(result,tmp_day);
                }
            }
        }
    }

}

int main(){
    cin>>m>>n;

    arr.resize(n,vector<int>(m));

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin>>arr[i][j];
        }
    }

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(arr[i][j]==1){
                q.push({{i,j}, 0});
            }
        }
    }

    dp();

    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            if(arr[i][j]== 0){
                result=-1;
            }
        }
    }

    cout<<result;

    return 0;
}
