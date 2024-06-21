#include <iostream>
#include <queue>
#include <vector>
using namespace std;
vector<vector<int>> map;
vector<vector<int>> visited;
int n, m;
int cheesecnt=0;
int dx[4]={0,0,1,-1};
int dy[4]={1,-1,0,0};
vector<pair<int,int>> inair;
int inside_air(){
    queue<pair<int,int>> q;
    q.push(make_pair(0,0));
    visited[0][0]=1;
    while(!q.empty()){
        int x=q.front().first;
        int y=q.front().second;
        q.pop();
        for(int i=0;i<4;i++){
            int nx=x+dx[i], ny=y+dy[i];
            if(nx<0||nx>=n||ny<0||ny>=m) continue;
            if(visited[nx][ny]==1||visited[nx][ny]==2) continue;
            if(map[nx][ny]==1){
                inair.push_back(make_pair(nx,ny));
                continue;
            }
            visited[nx][ny]=1;
            q.push(make_pair(nx,ny));
        }
    }
}
int main(){
    cin>>n>>m;
    map.resize(n);
    visited.resize(n);

    for(int i=0;i<n;i++){
        map[i].resize(m);
        visited[i].resize(m);
        for(int j=0;j<m;j++){
            cin>>map[i][j];
            if(map[i][j]==1) cheesecnt++;
        }
    }
    int time=0;
    while(cheesecnt>0){

    }
}
