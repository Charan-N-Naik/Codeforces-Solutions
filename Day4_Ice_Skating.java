import java.util.ArrayList;
import java.util.Scanner;

public class Day4_Ice_Skating {

    static class DSU{
        int par[];
        int rank[];

        DSU(int n){
            par=new int[n];
            rank=new int[n];
            for(int i=0;i<n;i++){
                par[i]=i;
            }
        }
        int find(int x) {
            int root = x;

            while (par[root] != root) {
                root = par[root];
            }

            while (par[x] != x) {
                int next = par[x];
                par[x] = root;
            x = next;
            }

        return root;
        }

        void union(int a,int b){
            int parA=find(a);
            int parB=find(b);

            if(parA==parB){
                return;
            }
            if(rank[parA]==rank[parB]){
                par[parA]=parB;
                rank[parB]++;
            }
            else if(rank[parA]>rank[parB]){
                par[parB]=parA;
            }
            else{
                par[parA]=parB;
            }
        }

    }
    public static void  main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n= sc.nextInt();
        DSU dsu=new DSU(n);

        int x[]=new int[n];
        int y[]=new int[n];
        for(int i=0;i<n;i++){
            x[i]=sc.nextInt();
            y[i]=sc.nextInt();
        }

        // for(int ede[]:)
        // ArrayList<ArrayList<Integer>> list=new ArrayList<>();
        // for(int i=)
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(x[i]==x[j]|| y[i]==y[j]){
                    dsu.union(i,j);
                }
            }
        }

        int connectedComp=0;
        for(int i=0;i<n;i++){
            if(dsu.find(i)==i){
                connectedComp++;
            }
        }
        System.out.println(connectedComp-1);



    }
}