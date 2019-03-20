public class QuickSort{
    //以temp为基准进行交换。temp = (right+left)/2;
    public static void quickSort(int[] a,int left,int right){
        if(left >= right){
            return;
        }
        int i =left,j = right;
        int temp = a[(right + left)/2];
        while(i<=j){ //易错点，我的条件是i<j.
            while(a[i]<temp){
                ++i;
            }
            while(a[j]>temp){
                --j;
            }
            if(i<j){ //我没有写判断语句
                swap(a,i,j);
                ++i;  
                --j;
            }else if(i==j){ //else 语句也没有
                i++;
                break;
            }
            
        }
        quickSort(a, left, i-1); 
        quickSort(a, j+1, right);
    }
    public static void swap(int a[],int x,int y){
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    } 
    public static void main(String[] args) {
        int[] a = {4,3,11,55,6,7,888,223,45,6,9,0,1};
        quickSort(a, 0, a.length-1);
        for(int e : a){
            System.out.println(e);
        }
    }
}