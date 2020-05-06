import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter MAL username");
        MyList m = new MyList(scan.next());

        do
        {
            System.out.println(m.getTitle(m.getRand()));
            System.out.println("Enter r for another one");
        }
        while(scan.next().equalsIgnoreCase("r"));
    }
}
