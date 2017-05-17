package com.govind.algo.tree;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by govindp on 11/11/2015.
 */
public class Serialization {
    public static void main(String[] args) {

        try {

            PrintWriter printWriter= new PrintWriter("SerailizedTree.txt","UTF-8");
            TreeNode TreeNode10 = new TreeNode(10);
            TreeNode TreeNode1 = new TreeNode(10);
            TreeNode TreeNode2 = new TreeNode(8);
            TreeNode TreeNode3 = new TreeNode(12);
            TreeNode TreeNode4 = new TreeNode(7);
            TreeNode TreeNode5 = new TreeNode(9);
            TreeNode TreeNode6 = new TreeNode(11);
            TreeNode TreeNode7 = new TreeNode(13);

            //TreeNode10.left = TreeNode1;
            //TreeNode10.right = TreeNode2;
            TreeNode1.left = TreeNode2;
            TreeNode1.right = TreeNode3;
            TreeNode2.left = TreeNode4;
            TreeNode2.right = TreeNode5;
            TreeNode3.left = TreeNode6;
            TreeNode3.right = TreeNode7;

            new Serialization().serialize(TreeNode1, printWriter);
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void serialize(TreeNode treeNode, PrintWriter fileOutputStream) throws IOException {
        if (treeNode == null){
            fileOutputStream.write("-1");
            return;
        }
        fileOutputStream.write(String.valueOf(treeNode.val));
        serialize(treeNode.left, fileOutputStream);
        serialize(treeNode.right, fileOutputStream);
    }
}
