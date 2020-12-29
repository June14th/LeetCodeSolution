package pkg048;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 *
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 *
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 *
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 */
public class Solution {
    public static void main(String[] args) {
        int[][] m = {{1,2,3},{4,5,6},{7,8,9}};
        rotate(m);
        for(int[] i : m){
            for( int j:i){
                System.out.print(j+", ");
            }
            System.out.println();
        }
    }
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        for(int i=0;i<length/2;i++){
            for(int j=0;j<(length+1)/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length-j-1][i];
                matrix[length-j-1][i] = matrix[length-i-1][length-j-1];
                matrix[length-i-1][length-j-1] = matrix[j][length-i-1];
                matrix[j][length-i-1] = temp;
            }
        }
    }
}
