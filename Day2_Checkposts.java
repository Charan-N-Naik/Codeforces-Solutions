import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Day2_Checkposts{
    static class Edge{
        int src;
        int dest;
        Edge(int src,int dest){
            this.src=src;
            this.dest=dest;
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                st = new StringTokenizer(br.readLine());
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }


    static void Topsort(ArrayList<Edge> graph[],int curr,boolean vis[],Stack<Integer> s){
        vis[curr]=true;

        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
            if(!vis[e.dest]){
                Topsort(graph,e.dest,vis,s);
            }
        }
        s.push(curr);
    }

    public static void tranposegraph(ArrayList<Edge> transpose[],int v,ArrayList<Edge> graph[],boolean vis[]) {
		for(int i=1;i<v+1;i++ ){
			vis[i]=false;
			transpose[i]=new ArrayList<>();
		}
		
		for(int i=1;i<graph.length;i++) {
			for(int j=0;j<graph[i].size();j++) {
				Edge e=graph[i].get(j);
				transpose[e.dest].add(new Edge(e.dest,e.src));
			}
			
		}
	}
    static long minCost=Long.MAX_VALUE;
    static long count=0;

    static void dfs(int curr,boolean vis[],ArrayList<Edge> graph[],long arr[]){
        vis[curr]=true;
        if(arr[curr]<minCost){
                    count=1;
                    minCost=arr[curr];
                }
               else if(arr[curr]==minCost){
                    count++;
        }
        for(int i=0;i<graph[curr].size();i++){
            Edge e=graph[curr].get(i);
          
          if(!vis[e.dest]){
             
                dfs(e.dest,vis,graph,arr);
               
            }
        }
        
    }
    

    public static void main(String  args[]) throws Exception{
       FastScanner sc = new FastScanner();
        int n=sc.nextInt();
        long  arr[]=new long [n+1];
        for(int i=1;i<n+1;i++){
            arr[i]=sc.nextLong();
        }


        ArrayList<Edge> graph[]=new ArrayList[n+1];

        for(int i=1;i<n+1;i++){
            graph[i]=new ArrayList<>();
        }
        int numEdges=sc.nextInt();

        for(int i=0;i<numEdges;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            graph[u].add(new Edge(u,v));
        }

        
        boolean vis[]=new boolean[n+1];
        Stack<Integer> s=new Stack<>();
        for(int i=1;i<n+1;i++){
            if(!vis[i]){
                Topsort(graph,i,vis,s);
            }
        }

        ArrayList<Edge> [] trans=new ArrayList[n+1];

        tranposegraph(trans,n,graph,vis);
         vis=new boolean[n+1];
         long ans=0;
         long ways=1;
         long MOD = 1000000007L;
        while(!s.isEmpty()){
            int node =s.pop();

            if(!vis[node]){
                minCost=Long.MAX_VALUE;
                count = 0;
                dfs(node,vis,trans,arr);
                ans+=minCost;
                ways = (ways * count) % MOD;
                count=0;
            }
        }

        System.out.print(ans+" "+ways);



        /// reverse the
       
        



    }
}