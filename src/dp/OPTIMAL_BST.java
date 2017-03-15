package dp;

/** 
 *  最优二叉查找树:
 *  
 *  给定n个互异的关键字组成的序列K=<k1,k2,...,kn>，且关键字有序（k1<k2<...<kn），
 *  我们想从这些关键字中构造一棵二叉查找树。对每个关键字ki，一次搜索搜索到的概率为pi。
 *  可能有一些搜索的值不在K内，因此还有n+1个“虚拟键”d0,d1,...,dn，他们代表不在K内的值。
 *  d0代表所有小于k1的值，dn代表所有大于kn的值。而对于i = 1,2,...,n-1,虚拟键di代表
 *  所有位于ki和ki+1之间的值。
 *  
 *  对于每个虚拟键，一次搜索对应于di的概率为qi。要使得查找一个节点的期望代价
 *  （代价可以定义为：比如从根节点到目标节点的路径上节点数目）最小，就需要建立
 *  一棵最优二叉查找树。
 *  
 *  
 *
 */

public class OPTIMAL_BST {
    static double[] P = {0,0.15, 0.10, 0.05, 0.10, 0.20};
    static double[] Q = {0.05, 0.10, 0.05, 0.05, 0.05, 0.10};
    
    static int N = 5;
    
    
    //e[i][j] 表示搜索一棵包含关键字 ki,...,kj 的最优二叉查找树的期望代价。
    //e[i][i-1] = q(i-1)
    static double[][] e = new double[N+2][N+2]; //期望 
    static double[][] w = new double[N+2][N+2]; //概率  
    static int[][] root = new int[N+2][N+2];
    
    public static void Optimal_Bst(){
        int j;
        double t,a,b,c;
        for(int i=1;i<=N+1;i++){
            e[i][i-1] = Q[i-1];
            w[i][i-1] = Q[i-1];
        }
        
        for(int l=1;l<=N;l++){
            for(int i=1;i<=N-l+1;i++){
                j = i+l-1;
                e[i][j] = 9999;
                w[i][j] = w[i][j-1] +P[j]+Q[j];
                for(int r=i;r<=j;r++){
                    a = e[i][r-1];
                    b = e[r+1][j];
                    c = w[i][j];
                    t = e[i][r-1]+e[r+1][j]+w[i][j];
                    if(t<e[i][j]){
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Optimal_Bst();
        for(int i=0;i<=N+1;i++){
            for(int j=0;j<=N+1;j++)
                {System.out.print(root[i][j]+" ");}
            System.out.println();
        }
    }
}
