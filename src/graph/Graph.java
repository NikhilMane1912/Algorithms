package graph;

/*
 * 图的邻接表类型
 */

//弧的节点结构类型
class ANode{
    int adjVertex;      //指向顶点在adjList的下标
    ANode nextArc;      //指向下一条弧
    int value;          //存放权值
}

//邻接表节点的类型
class VNode{
    String name;        //顶点信息
    ANode firstArc;     //指向第一条弧
}

//图的邻接表类型
class ALGraph {
    VNode[] adjList;
    int n;              //图的顶点数n
    int e;              //图的边数e
    
    public ALGraph(int n,int e){
        this.n = n;
        this.e = e;
        adjList = new VNode[n+1];
        for(int i=1;i<=n;i++)
            adjList[i] = new VNode();
    }
    
    public void displayALGraph(ALGraph G){
        for(int i=1;i<=G.n;i++){
            VNode v = G.adjList[i];
            System.out.print(v.name+"->");
            ANode e= v.firstArc;
            while(e!=null){
                System.out.print(G.adjList[e.adjVertex].name+"   ");
                e = e.nextArc;
            }
            System.out.println();
        }
    }
}


//---------------------------------------------------------------------

/*
 * 图的邻接矩阵
 */

class MGraph {
    int n;              //图的顶点数n
    int e;              //图的边数e
    int[] vex ;         //顶点数组
    int[][] edges ;     //邻接矩阵
    
    public MGraph(int n,int e){
        this.n = n;
        this.e = e;
        vex = new int[n+1];
        edges = new int[n+1][n+1];
        for(int i=1;i<=n;i++)
            edges[i] = new int[n+1];
    }
    
    public void displayMGraph(MGraph G){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                System.out.print(edges[i][j]+" ");
            }
            System.out.println();
        }
    }
}



public class Graph {

    public static ALGraph MatrixToList(MGraph MG){
        int n = MG.n;
        ALGraph AG = new ALGraph(MG.n,MG.e);
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++){
                if(MG.edges[i][j]!=0){
                    AG.adjList[i].name = i+"";
                    ANode arc = new ANode();
                    arc.adjVertex = j;
                    arc.nextArc = AG.adjList[i].firstArc;
                    arc.value = MG.edges[i][j];
                    AG.adjList[i].firstArc = arc;
                }
            }
        return AG;
    }
    
    public static MGraph ListToMatrix(ALGraph AG){
        MGraph MG = new MGraph(AG.n,AG.e);
        for(int i=1;i<=AG.n;i++){
            VNode v = AG.adjList[i];
            ANode e= v.firstArc;
            while(e!=null){
                MG.edges[i][e.adjVertex] = e.value;
                e = e.nextArc;
            }
        }
        return MG;
    }
    
    
    public static void main(String args[]){
        MGraph MG = new MGraph(6,10);
        int A[][]=
            {
                {0,5,0,7,0,0},
                {0,0,4,0,0,0},
                {8,0,0,0,0,9},
                {0,0,5,0,0,6},
                {0,0,0,5,0,0},
                {3,0,0,0,1,0}
            };
        
        for(int i=0;i<=5;i++)
            for(int j=0;j<=5;j++){
                MG.edges[i+1][j+1] = A[i][j];
            }
        
        //MG.displayMGraph(MG);
        ALGraph AG ;
        AG = MatrixToList(MG); 
        AG.displayALGraph(AG);
    }
    
}
