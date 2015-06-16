package cn.lnu.math;
//测试平面图形问题解决方案，方法将其看成一个二维数组,这里测试打印一个平面M图形
public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=13;
		int height=num/4+1;
		int width=num;
		int arr[][]=new int[height][width];
		int x=height-1;
		int y=0;
		boolean order=false;//表示初始x减方向
		for(int i=1;i<=num;i++){
			arr[x][y]=i;
			y++;
			if(!order){
				x--;
			}
			if(order){
				x++;
			}
			if(x<0){
				order=true;
				x+=2;
			}
			if(x>height-1){
				order=false;
				x-=2;
			}
		}
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<arr[i].length;j++){
				if(arr[i][j]==0){
					System.out.print(" ");
				}else{
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
	}

}
