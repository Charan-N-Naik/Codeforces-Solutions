import java.io.BufferedReader;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.*;
public class Day3_Fox_and_names {


    static class Edge{
        int src;
        int dest;
        Edge(int src,int dest){
            this.src=src;
            this.dest=dest;
        }
    }

    // static class fastscanner{
    //     BufferedReader br;
    //     StringTokenizer st;
    //     String next() throws IOException {
    //         while (st == null || !st.hasMoreElements()) {
    //             st = new StringTokenizer(br.readLine());
    //         }
    //         return st.nextToken();
    //     }

    //     int nextInt() throws IOException {
    //         return Integer.parseInt(next());
    //     }
    //     long nextLong() throws IOException {
    //         return Long.parseLong(next());
    //     }
    // }

    static int indegree[]=new int[26];
    static StringBuffer sb=new StringBuffer();
    static void topsort(ArrayList<Edge> graph[]){

        Queue<Integer> q=new LinkedList<>();
        

        for(int i=0;i<26;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int curr=q.remove();
            // System.out.print((char)(curr+'a'));
            sb.append((char)(curr+'a'));
            for(int i=0;i<graph[curr].size();i++){
                Edge e=graph[curr].get(i);
                indegree[e.dest]--;
                if(indegree[e.dest]==0) {
                    q.add(e.dest);
                }
            }
        }
        
    }
    


    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String arr[]=new String[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.next();
        }



        ArrayList<Edge> graph[]=new ArrayList[26];

        for(int i=0;i<26;i++){
            graph[i]=new ArrayList<>();
        }

        for(int i=0;i<n-1;i++){
            String str1=arr[i];
            String str2=arr[i+1];
            int j=0;
            while (j < Math.min(str1.length(), str2.length())
            && str1.charAt(j) == str2.charAt(j)) {
                j++;
            }

            if(j==Math.min(str1.length(), str2.length()) && str1.length()>str2.length()){
                System.out.println("Impossible");
                return;
            }


            if (j < Math.min(str1.length(), str2.length())){
                    char ch1=str1.charAt(j);
            char ch2=str2.charAt(j);
            graph[ch1-'a'].add(new Edge(ch1-'a',ch2-'a'));
            }
            
        }

        for(int i=0;i<26;i++){
            for(int j=0;j<graph[i].size();j++){
                Edge e=graph[i].get(j);
                indegree[e.dest]++;
            }
        }
        

        topsort(graph);

        if(sb.length()!=26){
            System.out.println("Impossible");
        }
        else {
            System.out.print(sb);
        }
    }

}