package io.github.gcdd1993.chapter4.btree;

/**
 * Created by gcdd1993 on 2021/3/16.
 */
public class BTreeTest {

    public static void main(String[] args) {
        BTree<String, String> tree = new BTree<>();

        tree.put("www.cs.princeton.edu", "128.112.136.12");
        tree.put("www.cs.princeton.edu", "128.112.136.11");
        tree.put("www.princeton.edu", "128.112.128.15");
        tree.put("www.yale.edu", "130.132.143.21");
        tree.put("www.simpsons.com", "209.052.165.60");
        tree.put("www.apple.com", "17.112.152.32");
        tree.put("www.amazon.com", "207.171.182.16");
        tree.put("www.ebay.com", "66.135.192.87");
        tree.put("www.cnn.com", "64.236.16.20");
        tree.put("www.google.com", "216.239.41.99");
        tree.put("www.nytimes.com", "199.239.136.200");
        tree.put("www.microsoft.com", "207.126.99.140");
        tree.put("www.dell.com", "143.166.224.230");
        tree.put("www.slashdot.org", "66.35.250.151");
        tree.put("www.espn.com", "199.181.135.201");
        tree.put("www.weather.com", "63.111.66.11");
        tree.put("www.yahoo.com", "216.109.118.65");

        System.out.println("cs.princeton.edu:  " + tree.get("www.cs.princeton.edu"));
        System.out.println("hardvardsucks.com: " + tree.get("www.harvardsucks.com"));
        System.out.println("simpsons.com:      " + tree.get("www.simpsons.com"));
        System.out.println("apple.com:         " + tree.get("www.apple.com"));
        System.out.println("ebay.com:          " + tree.get("www.ebay.com"));
        System.out.println("dell.com:          " + tree.get("www.dell.com"));
        System.out.println();

        System.out.println("size:    " + tree.size());
        System.out.println("height:  " + tree.height());
        System.out.println(tree);
        System.out.println();
    }
}
