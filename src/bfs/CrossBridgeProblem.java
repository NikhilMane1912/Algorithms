package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  四个女人过桥，夜间有一火把，每次最多过两个，必需带火把，过桥速度不一样 1min, 2min, 5min, 10min; 
 *  两个人过用最慢一个的速度，火把不能扔，如何在最快的时间内让四个女人都过桥？
 * 
 *  用 [0,0,0,0] 表示四个人的状态 (都未过桥)
 *  用 [1,1,1,1] 表示四个人的状态 (都已过桥)
 */

class CrossBridgeState{
    static int[] a = {0,2,4,8,16};
    
    static int[] speed = {-1,1,2,5,10};
    
    int[] S = new int[5];
    int fire = 0 ;      //表示火把在桥的哪一边，初始为0 表示未过桥的一边。
    int time;           //表示从初始状态到目前状态所用的时间.
    
    CrossBridgeState next;
    CrossBridgeState parent;
    
    
    @Override
    public String toString() {
        String s = "[";
        
        for(int i=1;i<=4;i++){
            s += S[i]+" ";
        }
        s += "](fire:"+fire+" time:"+time+")";
        
        return s;
    }

    public boolean checkState(){
        for(int i=1;i<=4;i++)
            if(S[i]==0)
                return false;
        
        return true;
    }
    
    public int count(int[] A){
        
        int result = 0;
        for(int i=1;i<=4;i++){
            result += A[i]*a[i];
        }
        
        return result;
    }
    
    public CrossBridgeState getNextStates(CrossBridgeState a,int[] visited){
        int fire = a.fire;
        int[] people = a.S;
        int time = a.time;
        int i=0,j=0,k=0;
        int[] tmp = new int[5];
        CrossBridgeState Head = null,tail = null;
        CrossBridgeState p = null;
        int val = 0;
        if(fire==0){        //从源端过去桥的目的端,挑选两个人
            for(i=1;i<=3;i++){
                if(people[i]!=0)
                    continue;
                for(j=i+1;j<=4;j++){
                    
                    if(people[j]!=0)
                        continue;
                    people[i] = 1;
                    people[j] = 1;
                    val = count(people);
                    if(visited[val]==1)
                        continue;
                    else{
                        p = new CrossBridgeState();
                        p.S = new int[5];
                        p.parent = a;
                        for(k=1;k<=4;k++){
                            p.S[k] = people[k];
                        }
                        p.fire = 1;
                        p.time = a.time + speed[i]+speed[j];
                        if(Head==null){
                            Head = p;
                            tail = p;
                        }
                        else{
                            tail.next = p;
                            tail = p;
                        }
                        visited[val]=1;
                    }
                    people[i] = 0;
                    people[j] = 0;
                }
            }
        }
        else{               //挑选一个人,将火把带回另一端
            
            for(i=1;i<=4;i++){
                if(people[i]!=1)
                    continue;
                people[i] = 0;
                val = count(people);
                if(visited[val]==1)
                    continue;
                else{
                    p = new CrossBridgeState();
                    p.parent = a;
                    p.S = new int[5];
                    for(k=1;k<=4;k++){
                        p.S[k] = people[k];
                    }
                    p.fire = 0;
                    p.time = a.time + speed[i];
                    if(Head==null){
                        Head = p;
                        tail = p;
                    }
                    else{
                        tail.next = p;
                        tail = p;
                    }
                    visited[val]=1;
                }
                people[i] = 1;
            }   
        }
        return Head;
    }
    
}

public class CrossBridgeProblem {
    
    
    public static void printPath(CrossBridgeState a){
        if(a==null)return;
        if(a.parent==null){
            System.out.println(a+"->");
            return ;
        }
        printPath(a.parent);
        System.out.println(a+"->");
    }
    
    public static CrossBridgeState BFS(int[] visited){
        CrossBridgeState s = new CrossBridgeState();
        s.S = new int[5];
        s.fire = 0;
        s.time = 0;
        Queue<CrossBridgeState> Q = new LinkedList<CrossBridgeState>();
        Q.offer(s);
        
        int val = s.count(s.S);
        visited[val] = 1;
        
        CrossBridgeState p = null,c = null;
        
        while(!Q.isEmpty()){
            p = Q.poll();
            if(p.checkState()==true){
                //System.out.println(p);
                return p;
            }
            c = p.getNextStates(p, visited);
            while(c!=null){
                Q.offer(c);
                c = c.next;
            }
        }
        
        return null;
    }
    
    public static void main(String[] args){
        int[] visited = new int[32];
        
        CrossBridgeState p = BFS(visited);
        
        printPath(p);
    }

}
