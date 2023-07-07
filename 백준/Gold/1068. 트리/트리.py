class Node:
    parent = -1
    def __init__(self):
        self.childs = []
    def insert_node(self, child):
        self.childs.append(child)
    def is_leafnode(self):
        return (len(self.childs) == 0)
    def delete_child(self, child_value:int):
        self.childs.remove(child_value)



n = int(input()) # 노드 갯수
nodelist = [Node() for i in range(n)]
leaf_node = 0
parent = list(map(int, input().split()))
for i in range(n):
    if parent[i] == -1: # 루트 노드
        root_node_id = i
    else:
        nodelist[i].parent = parent[i]
        nodelist[parent[i]].insert_node(i)

delete_node = int(input()) # 삭제할 노드
if nodelist[delete_node].parent != -1: ## 부모가 없는 노드가 아닐 경우만 제거
    nodelist[nodelist[delete_node].parent].delete_child(delete_node)

### inorder ###
def get_leafnode_by_inorder(pointer : int, counter_list : list): # call by ref
    if nodelist[pointer].is_leafnode():
        counter_list[0] += 1
    else:
        for child_id in nodelist[pointer].childs:
            get_leafnode_by_inorder(child_id, counter_list)
        

counter_list = [0]
if delete_node == root_node_id:
    result = 0
else:
    get_leafnode_by_inorder(root_node_id, counter_list)
    result = counter_list[0]
print(result)