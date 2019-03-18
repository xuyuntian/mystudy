public class MsergeSort{
    //将区间[p,q](q,r]的数据排序
    private static void merge(int[] a,int p,int q,int r){
        int[] temp = new int[r-p+1];
        int index = 0;//纪录temp数组起始位置。
        int i = p,j =q+1;
        while(i<=q&&j<=r){
            if(a[i]>a[j]){
                temp[index++] = a[i++];
            }else{
                temp[index++] = a[j++];
            }
        }
        //将剩下的内容复制到temp中。(这2个循环最多存在一个。已经只有其中一个为空上面的while循环才会退出)
        while(i<=q){
            temp[index++] = a[i++];
        }
        while(j<=r){
            temp[index++] = a[j++];
        }
        for(int k = 0;k < temp.length;k++){
            a[p+k] = temp[k];
        }
        
    }
    //递归拆分，直到最小元素个数为1进行合并
    public static void mergeSort(int a[],int p,int r){
        if(p>=r)
            return;
        int q = ( r + p)/2 ;//这个地方我之前居然写成了r-p.找了很久才发现
        mergeSort(a, p, q);
        mergeSort(a, q+1, r);
        merge(a, p, q, r);    
    }
    public static void main(String[] args) {
        int[] a = {1,23,4,4,5,6,7,8,99,3,0};
        mergeSort(a,0,a.length-1);//忘记改成length-1出过错误。。。之前是length
        for(int e : a){
            System.out.println(e);
        }
    }
    //归并排序算法我都没有顺利地写正确过，特别是面对一些边界条件，很容易出错。
}