#include <iostream>
using namespace std;
class Node {
private:
    int data;
    Node* left,* right;
public:
    void set_left(Node* n){
        left=n;
    }
    void set_right(Node* n){
        right=n;
    }
    Node * get_left(){
            return left;
    }
    Node * get_right(){
            return right;
    }
    int get_data(){
            return data;
    }
    Node(int value) : data(value), left(nullptr),right(nullptr) {}
};
class BST{
public:
    Node * root;
    void insert(int n){
        Node node(n);
        insert(&node,root);
    }
    void insert(Node*n,Node*k){
        if(n->get_data()>k->get_data()){
            if(k->get_right()==nullptr){
                k->set_right(n);
            } else {
                insert(n, k->get_right());
            }
        } else{
            if(k->get_left()== nullptr){
                k->set_left(n);
            } else {
                insert(n, k->get_left());
            }
        }
    }
    void print(Node *n){
        if(n->get_left()!= nullptr){
            print(n->get_left());
        }
        if(n->get_right()!= nullptr){
            print(n->get_right());
        }
        printf("%d",n->get_data());
    }
};
int main(){
    BST bst;
    int n;
    cin>>n;
    Node k= Node(n);
    bst.root=&k;
    while (true){
        cin>>n;
        bst.insert(n);
    }
    bst.print(bst.root);
}