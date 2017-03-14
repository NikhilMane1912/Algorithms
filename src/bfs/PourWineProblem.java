package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 *  一个8L杯子装满了酒，有一个3L空杯子和一个5L空杯子，问怎样才能用最少的次数倒出4L的酒。（不能倒掉）
 * 
 *  解：      
 *              A       B       C
 *      杯子：         8       5       3
 * ---------------------------------------
 *              8       0       0
 *              3       5       0
 *              3       2       3
 *              6       2       0       
 *              6       0       2
 *              1       5       2
 *              1       4       3
 */

class State{
    static int[] cups = {-1,8,5,3};
    int Index = 1;
    int[] vals = new int[4];
    State parent;
    
    public boolean checkState(){
        for(int i=1;i<=3;i++){
            if(vals[i]==4)
                return true;
        }
        return false;
    }
    
    public State getState1(int[] visited,State a){
        int valA = 0;
        int valB = 0;
        int valC = 0;
        int index = a.Index;
        int rest = 0;
        int val = 0;
        boolean flag = false;
        int num = 0;
        //every time just pour wine 
        //  A->B    , B->C  ,   C->A
        while(flag!=true && num<=3){
            
            valA = a.vals[1];
            valB = a.vals[2];
            valC = a.vals[3];
            
            if(index==1){   //A->B
                rest = cups[2]-valB;
                if(rest>valA){
                    valB += valA;
                    valA = 0;
                }
                else{
                    valA -= rest;
                    valB += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 2;
                num ++;
                
            }
            else if(index==2){  //B->C
                
                rest = cups[3]-valC;
                if(rest>valB){
                    valC += valB;
                    valB = 0;
                }
                else{
                    valB -= rest;
                    valC += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 3;
                num ++;
            }
            else{               //C->A
                rest = cups[1]-valA;
                if(rest>valC){
                    valA += valC;
                    valC = 0;
                }
                else{
                    valC -= rest;
                    valA += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 1;
                num ++;
            }
        }
        
        if(num>3)
            return null;
        
        State b =  new State();
        b.vals[1] = valA;
        b.vals[2] = valB;
        b.vals[3] = valC;
        b.Index = index;
        b.parent = a;
        return b;
    }
    
    public State getState2(int[] visited,State a){
        int valA = 0;
        int valB = 0;
        int valC = 0;
        int index = a.Index;
        int rest = 0;
        int val = 0;
        boolean flag = false;
        int num = 0;
        //every time just pour wine 
        //  A->C    , C->B  ,   B->A
        while(flag!=true && num<=3){
            
            valA = a.vals[1];
            valB = a.vals[2];
            valC = a.vals[3];
            
            if(index==1){   //A->C
                rest = cups[3]-valC;
                if(rest>valA){          
                    valC += valA;
                    valA = 0;
                }
                else{
                    valA -= rest;
                    valC += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 2;
                num ++;
                
            }
            else if(index==2){  //C->B
                
                rest = cups[2]-valB;
                if(rest>valC){
                    valB += valC;
                    valC = 0;
                }
                else{
                    valC -= rest;
                    valB += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 3;
                num ++;
            }
            else{               //B->A
                rest = cups[1]-valA;
                if(rest>valB){
                    valA += valB;
                    valB = 0;
                }
                else{
                    valB -= rest;
                    valA += rest;
                }
                
                val = count(valA,valB,valC);
                if(visited[val]!=1){
                    flag = true;
                    visited[val]=1;
                }
                
                index = 1;
                num ++;
            }
        }
        
        if(num>3)
            return null;
        
        State b =  new State();
        b.vals[1] = valA;
        b.vals[2] = valB;
        b.vals[3] = valC;
        b.Index = index;
        b.parent = a;
        return b;
    }
    
    
    @Override
    public String toString() {
        String s = "[";
        
        for(int i=1;i<=3;i++){
            s += vals[i]+" ";
        }
        s += "] ("+Index+")";
        
        return s;
    }
    
    public int count(int x,int y,int z){
        int result = 0;
        result += 100*x + 10*y+z;
        return result;
    }
}

public class PourWineProblem {
    static int A = 10*10;
    static int B = 10;
    static int C = 1;
    
    public static State BFS(int[] visited){
        State s = new State();
        s.vals[1] = 8;
        s.vals[2] = 0;
        s.vals[3] = 0;
        s.Index = 1;
        
        Queue<State> Q = new LinkedList<State>();
        Q.offer(s);
        
        int c = s.count(8, 0, 0);
        visited[c] = 1;
        
        State p = null,q = null;
        while(!Q.isEmpty()){
            s = Q.poll();
            if(s.checkState()){
                System.out.println(s);
                return s;
            }
            p = s.getState1(visited,s);
            if(p!=null)
                Q.offer(p);
            q = s.getState2(visited,s);
            if(q!=null)
                Q.offer(q);
        }
        return null;
    }
    
    public static void printPath(State a){
        if(a.parent==null){
            System.out.print(a+"->");
            return ;
        }
        
        printPath(a.parent);
        System.out.print(a);
    }
    
    public static void main(String[] args){
        int[] visited = new int[10*10*10+1];
        State p = BFS(visited);
        printPath(p);
    }
}
