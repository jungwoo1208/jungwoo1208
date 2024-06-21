#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
typedef struct node {
    char* op;
    struct node* lefthand;
    struct node* righthand;
} Node;
int op_cnt = 0;
char *x = NULL, *y = NULL, *z = NULL;
Node* newNode(char* op, Node* lefthand, Node* righthand) {
    Node* n = (Node*)malloc(sizeof(Node));
    n->op = strdup(op);
    n->lefthand = lefthand;
    n->righthand = righthand;
    return n;
}
char** token(char* prefix_expr, int* token_count) {
    int max_tokens = 100;
    char** tokens = (char**)malloc(max_tokens * sizeof(char*));
    int idx = 0;
    char* expr_copy = strdup(prefix_expr);
    char* token = strtok(expr_copy, " ");
    while (token != NULL) {
        tokens[idx] = strdup(token);
        idx++;
        token = strtok(NULL, " ");
    }
    tokens[idx] = NULL;
    *token_count = idx;
    free(expr_copy);
    return tokens;
}
Node* parse_tokens(char** tokens, int* pos, int token_count) {
    if (*pos >= token_count) {
        return NULL;
    }
    while (*pos < token_count && strcmp(tokens[*pos], "(") == 0) {
        (*pos)++;
    }
    Node* n = newNode(tokens[*pos], NULL, NULL);
    (*pos)++;
    if (*pos < token_count && strcmp(tokens[*pos], "(") == 0) {
        n->lefthand = parse_tokens(tokens, pos, token_count);
    } else if (*pos < token_count && strcmp(tokens[*pos], ")") != 0) {
        n->lefthand = newNode(tokens[*pos], NULL, NULL);
        (*pos)++;
    }
    if (*pos < token_count && strcmp(tokens[*pos], "(") == 0) {
        n->righthand = parse_tokens(tokens, pos, token_count);
    } else if (*pos < token_count && strcmp(tokens[*pos], ")") != 0) {
        n->righthand = newNode(tokens[*pos], NULL, NULL);
        (*pos)++;
    }
    if (n->lefthand != NULL && n->righthand == NULL) {
        n->righthand = n->lefthand;
        n->lefthand = NULL;
    }
    while (*pos < token_count && strcmp(tokens[*pos], ")") == 0) {
        (*pos)++;
    }
    return n;
}
Node* base_parser(char * prefix_expr){
    int token_count;
    char** tokens = token(prefix_expr, &token_count);
    int pos = 0;
    Node* root = parse_tokens(tokens, &pos, token_count);
    for (int i = 0; i < token_count; i++) {
        free(tokens[i]);
    }
    free(tokens);
    return root;
}
Node* parser(char* prefix_expr) {
    if(prefix_expr==NULL){
        return 0;
    }
    return base_parser(prefix_expr);
}
int is_booleanOp(char *str){
    if(strcmp(str, "&") == 0||strcmp(str, "|") == 0||strcmp(str, "^") == 0){
        return 1;
    }else if(strcmp(str, "+") == 0||strcmp(str, "-") == 0||strcmp(str, "*") == 0||strcmp(str, "/") == 0){
        return 0;
    }
    return -1;
}
int is_arithmeticOp(char *str){
    if(strcmp(str, "+") == 0||strcmp(str, "-") == 0||strcmp(str, "*") == 0||strcmp(str, "/") == 0){
        return 1;
    } else if(strcmp(str, "&") == 0||strcmp(str, "|") == 0||strcmp(str, "^") == 0){
        return 0;
    }
    return -1;
}
void calc_complexity(Node* root){
    if(is_booleanOp(root->op)){
        if(root->righthand!=NULL) {
            if(is_booleanOp(root->righthand->op)>=0)
                if (is_arithmeticOp(root->righthand->op)) {
                    op_cnt++;
                }
        }
        if(root->lefthand!=NULL) {
            if(is_booleanOp(root->lefthand->op)>=0)
                if (is_arithmeticOp(root->lefthand->op)) {
                    op_cnt++;
                }
        }
    }else{
        if(root->righthand!=NULL) {
            if(is_booleanOp(root->righthand->op)>=0)
                if (is_booleanOp(root->righthand->op)) {
                    op_cnt++;
                }
        }
        if(root->lefthand!=NULL) {
            if(is_booleanOp(root->lefthand->op)>=0)
                if (is_booleanOp(root->lefthand->op)) {
                    op_cnt++;
                }
        }
    }
}
void pre2in(Node* root) {
    calc_complexity(root);
    if(root->lefthand!=NULL){
        pre2in(root->lefthand);
    }
    printf("%s ",root->op);
    if(root->lefthand==NULL&&root->righthand!=NULL){
        printf("( ");
    }
    if(root->righthand!=NULL){
        pre2in(root->righthand);
    }
    if(root->lefthand==NULL&&root->righthand!=NULL){
        printf(") ");
    }
}
int evaluate(Node* root) {
    if (root->op[0] == '0' && root->op[1] == 'x') {
        return strtol(root->op, NULL, 16);
    }
    else if (isdigit(root->op[0])) {
        return atoi(root->op);
    }
    else if (strcmp(root->op, "x") == 0) {
        return atoi(x);
    }
    else if (strcmp(root->op, "y") == 0) {
        return atoi(y);
    }
    else if (strcmp(root->op, "z") == 0) {
        return atoi(z);
    }
    else {
        int left = root->lefthand ? evaluate(root->lefthand) : 0;
        int right = root->righthand ? evaluate(root->righthand) : 0;
        switch (root->op[0]) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/': return right != 0 ? left / right : 0;
            case '&': return left & right;
            case '|': return left | right;
            case '^': return left ^ right;
            default: return 0;
        }
    }
}
void parse_assignments(char* assignments) {
    int token_count;
    char **t = token(assignments, &token_count);
    for (int i = 0; i < token_count; i++) {
        if (strcmp(t[i], "x") == 0 && i + 2 < token_count && strcmp(t[i + 1], "=") == 0) {
            x = strdup(t[i + 2]);
        } else if (strcmp(t[i], "y") == 0 && i + 2 < token_count && strcmp(t[i + 1], "=") == 0) {
            y = strdup(t[i + 2]);
        } else if (strcmp(t[i], "z") == 0 && i + 2 < token_count && strcmp(t[i + 1], "=") == 0) {
            z = strdup(t[i + 2]);
        }
    }
    for (int i = 0; i < token_count; i++) {
        free(t[i]);
    }
    free(t);
}
void freeNode(Node* n) {
    if (n) {
        free(n->op);
        freeNode(n->lefthand);
        freeNode(n->righthand);
        free(n);
    }
}
int main() {
    char s[100];
    if (fgets(s, sizeof(s), stdin) != NULL) {
        s[strcspn(s, "\n")] = '\0';
        size_t len = strlen(s);
        int idx = 0;
        int flag = 0;
        for (int i = 0; i < len; i++) {
            if (s[i] == ',') {
                idx = i;
                flag = 1;
                break;
            }
        }
        char substring[100];
        if (idx > 0) {
            strncpy(substring, s, idx - 1);
            substring[idx - 1] = '\0';
        } else {
            strncpy(substring, s, len);
            substring[len] = '\0';
        }
        Node* root = parser(substring);
        pre2in(root);
        printf("\n");
        printf("complexity: %d\n", op_cnt);
        if (flag) {
            char var[100];
            strncpy(var, s + idx + 2, len - idx - 2);
            var[len - idx - 2] = '\0';
            parse_assignments(var);
        }
        if (x || y || z) {
            printf("Evaluation result: %d\n", evaluate(root));
        }
        freeNode(root);
    }
    return 0;
}