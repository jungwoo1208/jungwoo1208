import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MainJava {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader("C:\\Users\\gjw19\\IdeaProjects\\DS_02_202302521\\src\\새 텍스트 문서.txt"));
        String line= br.readLine();
        while((line != null)){
            StringTokenizer st= new StringTokenizer(line);
            int n= st.countTokens();
            int [] arr= new int[n];
            for(int i=0; i<arr.length; i++) {
                arr[i]= Integer.parseInt(st.nextToken());
            }
            int idx1=0;
            int idx2=n-1;
            while(true){
                if(idx1==idx2) {
                    break;
                }
                if(arr[idx2]%2==1) {
                    idx2--;
                }
                else if(arr[idx1]%2==0) {
                    idx1++;
                }
                else {
                    int temp= arr[idx1];
                    arr[idx1]= arr[idx2];
                    arr[idx2]= temp;
                }
            }
            for(int i=0;i<n;i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            line= br.readLine();
        }
    }
}
