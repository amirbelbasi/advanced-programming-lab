import java.util.Scanner;

public class IsCoprime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            int flag = 0;
            String inp = sc.nextLine();
            int a = Integer.parseInt(inp.split(" ")[0]);
            int b = Integer.parseInt(inp.split(" ")[1]);
            if (a == 1 || b == 1)
            {
                System.out.println("true");
                continue;
            }
            for (int i = 2; i <= (a < b ? a : b); i++)
            {
                if (a % i == 0 && b % i == 0)
                {
                    flag = 1;
                    break;
                }
            }
            System.out.println(flag == 0 ? "true" : "false");
        }
    }
}