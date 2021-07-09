/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.bst;

import domain.Food;
import java.util.ArrayList;

/**
 *
 */
public class BST implements Tree {

    private BTreeNode root; //representa la unica entrada al arbol

    //Constructor
    public BST() {
        this.root = null;
    }

    public BTreeNode getRoot() {
        return root;
    }

    @Override
    public int size() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return size(root);
    }

    private int size(BTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch(root, element);
    }

    private boolean binarySearch(BTreeNode node, Object element) {
        if (node == null) {
            return false;
        } else if (util.Utility.equals(node.data, element)) {
            return true; //ya lo encontro
        } else if (util.Utility.lessT(element, node.data)) {
            return binarySearch(node.left, element);
        } else {
            return binarySearch(node.right, element);
        }
    }

    @Override
    public void add(Object element) {
        root = add(root, element);
    }

    private BTreeNode add(BTreeNode node, Object element) {
        if (node == null) { //el arbol esta vacio
            node = new BTreeNode(element);
        } else if (util.Utility.lessT(element, node.data)) {
            node.left = add(node.left, element);
        } else if (util.Utility.greaterT(element, node.data)) {
            node.right = add(node.right, element);
        }
        return node;
    }

    @Override
    public void remove(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        root = remove(root, element);
    }

    private BTreeNode remove(BTreeNode node, Object element) {
        if (node != null) {
            if (util.Utility.lessT(element, node.data)) {
                node.left = remove(node.left, element);
            } else if (util.Utility.greaterT(element, node.data)) {
                node.right = remove(node.right, element);
            } else if (util.Utility.equals(node.data, element)) {
                //Caso 1. El nodo a suprimir no tiene hijos
                if (node.left == null && node.right == null) {
                    return node = null;
                } else //Caso 2. El nodo a suprimir solo tiene un hijo
                if (node.left == null && node.right != null) {
                    node = node.right;
                } else if (node.left != null && node.right == null) {
                    node = node.left;
                } else //Caso 3. El nodo a suprimir tiene dos hijos
                if (node.left != null && node.right != null) {
                    //se debe sustituir la data del nodo, por el la data
                    //alguna hoja (nodo externo) del subarbol derecho
                    Object value = min(node.right);
                    node.data = value;
                    node.right = remove(node.right, value);
                }
            }
        }
        return node;
    }

    @Override
    public int height(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root, element, 0);
    }

    private int height(BTreeNode node, Object element, int count) {
        if (node == null) {
            return 0; //significa q el elemento no existe
        } else if (util.Utility.equals(node.data, element)) {
            return count;
        } //si no lo encontro debe buscar por la izq y la der
        else if (util.Utility.lessT(element, node.data)) {
            return height(node.left, element, ++count);
        } else {
            return height(node.right, element, ++count);
        }
    }

    @Override
    public int height() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return height(root);
    }

    private int height(BTreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(height(node.left),
                    height(node.right) + 1);
        }
    }

    public int height2() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Tree is empty");
        }
        return height2(root, 0);
    }

    private int height2(BTreeNode node, int count) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return count;
        } else {
            return Math.max(height2(node.left, ++count),
                    height2(node.right, count));

        }
    }

    @Override
    public Object min() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return min(root);
    }

    private Object min(BTreeNode node) {
        if (node.left != null) {
            return min(node.left);
        }
        return node.data;
    }

    @Override
    public Object max() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return max(root);
    }

    private Object max(BTreeNode node) {
        if (node.right != null) {
            return max(node.right);
        }
        return node.data;
    }

    @Override
    public String preOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PreOrder Transversal Tour: \n"
                + preOrder(root);
    }

    //Tranversal tour: N-L-R
    private String preOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = node.data + " ";
            result += preOrder(node.left);
            result += preOrder(node.right);
        }
        return result;
    }

    @Override
    public String InOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "InOrder Transversal Tour: \n"
                + InOrder(root);
    }

    //Tranversal tour: L-N-R
    private String InOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = InOrder(node.left);
            result += node.data + " ";
            result += InOrder(node.right);
        }
        return result;
    }

    @Override
    public String postOrder() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return "PostOrder Transversal Tour: \n"
                + postOrder(root);
    }

    //Tranversal tour: L-R-N
    private String postOrder(BTreeNode node) {
        String result = "";
        if (node != null) {
            result = postOrder(node.left);
            result += postOrder(node.right);
            result += node.data + " ";
        }
        return result;
    }

    public boolean isBalanced() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return isBalanced(root);
    }

    private boolean isBalanced(BTreeNode node) {

        if (root == null) {
            return true;
        } else {
            int lh = height(root.left);
            int rh = height(root.right);
            return lh - rh == 1 || lh - rh == -1 || lh - rh == 0;
        }
    }

    public void modify(Object a, Object b) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        if (contains(a) && !contains(b)) {
            System.out.println("Element " + a + " due changed to element " + b);
            remove(a);
            add(b);
        }
    }

    @Override
    public String toString() {
        String result = "BINARY SEARCH TREE TOUR...";
        result += "\nPreOrder: " + preOrder(root);
        result += "\nInOrder: " + InOrder(root);
        result += "\nPostOrder: " + postOrder(root);
        return result;
    }

    public ArrayList<Object> preOrder2() throws TreeException {
        ArrayList<Object> bstList = new ArrayList<Object>();
        if (isEmpty()) {

        }
        preOrder2(root, bstList);
        return bstList;
    }

    //Tranversal tour: N-L-R
    private Object preOrder2(BTreeNode node, ArrayList<Object> bstList) {
        String result = "";
        if (node != null) {
            bstList.add(node.data);

            result = node.data + " ";
            result += preOrder2(node.left, bstList);
            result += preOrder2(node.right, bstList);
        }
        return result;
    }

    public boolean contains2(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch2(root, element);
    }

    private boolean binarySearch2(BTreeNode node, Object element) {
        if (node == null) {
            return false;
        } else if (util.Utility.equals2(node.data, element)) {
            return true; //ya lo encontro
        } else if (util.Utility.lessT2(element, node.data)) {
            return binarySearch2(node.left, element);
        } else {
            return binarySearch2(node.right, element);
        }
    }

    private boolean binarySearch3(BTreeNode node, Object element) {
        if (node == null) {
            return false;
        } else if (util.Utility.equals3(node.data, element)) {
            return true; //ya lo encontro
        } else if (util.Utility.lessT3(element, node.data)) {
            return binarySearch3(node.left, element);
        } else {
            return binarySearch3(node.right, element);
        }
    }

    public boolean contains3(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("Binary Search Tree is empty");
        }
        return binarySearch3(root, element);
    }

}
