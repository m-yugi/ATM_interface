import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
class ATM {
    String username = null;
    String password = null;
    long account_number;
    long balance = 10000;
    int limit = 1;
    int w_count=1,d_count=1,t_count=1;
    HashMap<String, Integer> hm = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public void create_account(String username, String password, long account_number) {
        this.username = username;
        this.password = password;
        this.account_number = account_number;
    }

    public boolean account_validation() {
//        Scanner sc= new Scanner(System.in);
        System.out.print("please enter the username: ");
        String un = sc.nextLine();
        System.out.print("please enter the password: ");
        String pass = sc.nextLine();
        return Objects.equals(this.username, un) && Objects.equals(this.password, pass);
    }

    public boolean validate() {
        if (account_validation()) {
            return true;
        }
        if (this.limit >= 3) {
            System.out.println("maximum limit reached please try after some time");
            return false;
        } else {
            System.out.println("the credentials you have entered are wrong please try again");
            this.limit++;
            validate();
        }
        return false;
    }

    public void user_menu() {
        while (true) {
            System.out.println();
            System.out.println("please select the options provided below");
            System.out.println("1.withdraw\n2.deposit\n3.transaction history\n4.transfer\n5.balance enquiry\n6.exit");
            int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    System.out.println("please enter the amount to withdraw: ");
                    int amount = sc.nextInt();
                    withdraw(amount);
                    break;
                case 2:
                    System.out.println("please enter the amount you want to deposit: ");
                    int amount_w = sc.nextInt();
                    deposit(amount_w);
                    break;
                case 3:
                    if (hm.isEmpty()) {
                        System.out.println("no transaction done yet");
                    } else {
                        for (String s : hm.keySet()) {
                            System.out.println(s+ " : " +hm.get(s) );
                        }
                    }
                    break;
                case 4:
                    System.out.println("please enter the name of the person: ");
                    String name = sc.nextLine();
                    System.out.println("please enter the amount to be transferred: ");
                    int money = Integer.parseInt(sc.nextLine());
                    transfer(name, money);
                    break;
                case 5:
                    System.out.println("your account balance "+this.balance);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("please select a valid operation");
                    break;
            }
        }
    }

    public void deposit(int amount_w) {
        this.balance+=amount_w;
        System.out.println(amount_w+"has been credited to your account");
        hm.put(this.d_count++ +"."+"credited",amount_w);
    }

    public void withdraw(int amount) {
        if(amount>this.balance){
            System.out.println("insufficient balance");
            return;
        }
        this.balance-=amount;
        System.out.println(amount+" has been debited from your account");
        hm.put(this.w_count++ +"."+"debited",amount);
    }
    public void transfer(String name, int amount){
        this.balance-=amount;
        System.out.println(amount+" had been transferred to "+name);
        hm.put(this.t_count++ +"."+"transferred to "+name,amount);
    }
}
public class ATm_interface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATM user = new ATM();
            if (user.username == null || user.password == null) {
                System.out.println("1.register\n 2.exit");
                System.out.print("please select an operation: ");
                int n = Integer.parseInt(sc.nextLine());
                if (n == 1) {
                    System.out.println("please enter the username: ");
                    String username = sc.nextLine();
                    System.out.println("please enter the password: ");
                    String password = sc.nextLine();
                    System.out.print("please enter the account_number: ");
                    long account_number = sc.nextLong();
                    user.create_account(username, password, account_number);
                    System.out.println("REGISTERED SUCCESSFULLY");
                } else {
                    System.out.println("thanks visit again");
                }
            }
                System.out.println("1.login\n2.exit");
                System.out.print("please select an  operation: ");
                int n1=sc.nextInt();
                if(n1==1){
                    if(user.validate()){
                        user.user_menu();
                    }
                    else {
                        System.out.println("thanks visit again");
                    }
                }
        }
    }
