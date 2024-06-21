#include <iostream>
#include <vector>
using namespace std;
#define INF 1000000;
int main(){
    int n,m,r,result=0;
    vector<vector<int>> map;
    for(int i=0;i<n;i++)
        map.push_back(vector<int>());
    cin>>n>>m>>r;
    int item[n];
    int weight[n][n];
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            weight[i][j]=INF;
        }
    }
    for(int i=0;i<n;i++) cin>>item[i];
    for(int i=0;i<r;i++){
        int a,b,c;
        cin>>a>>b>>c;
        weight[a-1][b-1]=c;
        weight[b-1][a-1]=c;
        map[a-1].push_back(b-1);
        map[b-1].push_back(a-1);
    }
    for(int i=0;i<n;i++) {
        int tmp[n][n];
        bool visited[n];
        for(int j=0;j<n;j++) visited[j]=false;
        for(int j=0;j<n;j++){
            for(int k=0;k<n;k++){
                tmp[j][k]=weight[j][k];
            }
        }
        for(int j=0;j<n;j++){
            if(tmp[i][j]<=m){
                for(int k=0;k<map[j].size();k++){
                    if(tmp[i][map[j][k]]>tmp[i][j]+weight[j][map[j][k]]){
                        tmp[i][map[j][k]]=tmp[i][j]+weight[j][map[j][k]];
                    }
                }
            }
        }
        int sum=0;
        for(int j=0;j<n;j++){
            if(tmp[i][j]<=m){
                sum+=item[j];
            }
        }
        if(result<sum) result=sum;
    }
    cout<<result;
    return 0;
}
