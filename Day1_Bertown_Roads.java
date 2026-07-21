
import java.lang.reflect.Array;
import java.util.*;
 
 
public  class Day1_Bertown_Roads{
 
   static  class Edge{
        int src;
        int dest;
        int idx;
        Edge(int src,int dest,int idx){
            this.dest=dest;
            this.src=src;
            this.idx=idx;
        }
    }
 
    static int time =0;
    static ArrayList<int []> res=new ArrayList<>();
 
   static boolean isEdgeVis[];
    static boolean bridgeFound;
   static void dfs(ArrayList<Edge> graph[],int low[],int dt[],int par,int curr,boolean vis[],boolean isEdgeVis[]){
 
        vis[curr]=true;
 
        low[curr]=dt[curr]=++time;
        
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(par==e.dest)continue;
            if(isEdgeVis[e.idx])continue;
            if(!vis[e.dest])
            {   
                res.add(new int[]{curr,e.dest});
                isEdgeVis[e.idx]=true;
 
                dfs(graph, low,dt, par,e.dest, vis,isEdgeVis);
                low[curr]=Math.min(low[curr], low[e.dest]);
                if(low[e.dest] > dt[curr]){
                    bridgeFound=true;
                    return;
                }
            }
            else if(dt[curr]>dt[e.dest]){
                low[curr]=Math.min(low[curr], dt[e.dest]);
                res.add(new int[]{curr,e.dest});
                isEdgeVis[e.idx]=true;
            }
 
 
        }
    }
 
 
 
 
 
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            int V=sc.nextInt();
            int numEedges=sc.nextInt();
 
            ArrayList<Edge> graph[]=new ArrayList[V+1];
            for(int i=1;i<=V;i++){
                graph[i]=new ArrayList<>();
            }
 
 
            for(int i=0;i<numEedges;i++){
               int u=sc.nextInt();
                int v=sc.nextInt();
                graph[u].add(new Edge(u, v,i));
                graph[v].add(new Edge(v, u,i));
            }
 
            boolean vis[]=new boolean [V+1];
            int low[]=new int[V+1];
            int dt[]=new int [V+1];
            isEdgeVis=new boolean[numEedges];
 
            for(int i=1;i<V+1;i++){
                if(!vis[i]){
                    dfs( graph, low, dt, -1, i, vis,isEdgeVis);
                }
            }
 
            if(bridgeFound){
                System.out.print(0);
            }else{
                System.err.println("--------------------------------------");
                for(int arr[]:res){
                    System.out.println(arr[0]+" "+arr[1]);
                }
            }
 
 
        }
 
}