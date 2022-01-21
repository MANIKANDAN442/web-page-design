import java.util.*;
public class Atm{
    public static int[] arr = {2000,3,500,3,200,3,100,3};
    static int tot = (arr[1]*arr[0])+(arr[3]*arr[2])+(arr[5]*arr[4])+(arr[7]*arr[6]);
    static String[] person1 = {"1234" , "same" , "0" , "20000","Unblock"};
    static String[] person2 = {"1234" , "not" , "0" , "20000","Unblock"};
    static String[] person3 = {"1234" , "not" , "0" , "20000","Unblock"};
    static String[] person4 = {"1234" , "same" , "0" , "20000","Unblock"};
    static ArrayList<String> mini1=new ArrayList<String>();
    static ArrayList<String> mini2=new ArrayList<String>();
    static ArrayList<String> mini3=new ArrayList<String>();
    static ArrayList<String> mini4=new ArrayList<String>();
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int a = 1;
        while(true){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            if(a!=1 && a!=2 && a!=3){
                System.out.println(a);
                System.out.println("Invalid input");
            }
            System.out.println("   ATM machine" +"\n" +"1.Admin" +"\n" +"2.User" +"\n" +"3.Exit");
            System.out.print("Enter Choice : ");
            a = sc.nextInt();
            if(a==1){
                int a1 = checkAdmin();
                adminBlock(a1);
            }else if(a==2){
                userWorks();
            }else if(a==3){
                System.out.println("Enter to logout");
                enterNeeded();
                break;
            }
            else
            continue;
        }
    }
    static void adminBlock(int a1){
        Scanner sc = new Scanner(System.in);
        if(a1==1){
                    System.out.print("\033[H\033[2J");  
                    System.out.flush();
                    System.out.println("Logged in Succesfully");
                    System.out.println("   WELCOME ADMIN     ");
                    System.out.println("1.Load" +"\n" +"2.Show" +"\n" +"3.Exit");
                    System.out.print("Enter Choice : ");
                    int adchoice= sc.nextInt();
                    if(adchoice==1){
                        loadMoney();
                        adminBlock(1);
                    }
                    else if(adchoice==2){
                        showMoney();
                        adminBlock(1);
                    }
                    else if(adchoice==3){
                    }
                    else{
                        adminBlock(a1);
                    }
            }
            else{
                System.out.println("Invalid Admin id");
                a1 = checkAdmin();
                adminBlock(a1);
            }
    }
    static int checkAdmin(){
        Scanner s = new Scanner(System.in);
        String pwd,adm;
        System.out.print("Enter Username : ");
        adm = s.nextLine();
        System.out.print("Enter Password : ");
        pwd = s.nextLine();
        if((adm.equals("mani")) && (pwd.equals("mani01"))){
            return 1;
        }
        else
        return 0;
    }
    static void loadMoney(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Amount you load : ");
        int tot=sc.nextInt();
        int inserted=0;
        for(int i = 0 ; i<(arr.length) ; i=i+2){
            if(inserted<tot){
                int amt = arr[i];
                System.out.print("Enter number of " +amt +"  you going to put in : ");
                int c=sc.nextInt();
                arr[i+1]+=c;
                inserted += amt*c;
                if(inserted==tot){
                System.out.println("Inserted Successfully");
                enterNeeded();
                }
            }
            else if(inserted>tot){
            System.out.println("Amount is Exceeded the total amount");
            enterNeeded();
            break;
            }
            else if(i==(arr.length-1) && inserted<tot){
                System.out.println("Given amount is not equal to inserted amount");
                enterNeeded();
            }
        }
    }
    static void showMoney(){
            for(int i = 0 ; i<arr.length ; i=i+2){
                System.out.println(arr[i] +" : " +arr[i+1]);
            }
            enterNeeded();
    }
    static void userWorks(){
        int a1 = 0;
        user(a1);
    }
    static void user(int a1){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Username : ");
        String st = sc.nextLine();
        if((!st.equals("person1")) && (!st.equals("person2")) && (!st.equals("person3")) && (!st.equals("person4"))){
            System.out.println("Invalid user");
            user(a1);
        }
        int a = passWord(userId(st));
        if(a==4){
            userActions(st);
        }
        else if(a==3){
            System.out.println("Your account is temporarily blocked");
            enterNeeded();
        }
        else{
            a1++;
            if(a1==3){
                System.out.println("Your account is temporarily blocked");
                enterNeeded();
                String[] st1=userId(st);
                st1[4]="block";
            }
            else{
            System.out.println("Attempt " +a1 +" of 3 attempts leads to block account");
            user(a1);
            }
        }
    }
    static int passWord(String st[]){
        int b = 0;
        Scanner sc = new Scanner(System.in); 
        if(blockCheck(st)==3){
            b=3;
        }
        else{
            System.out.print("Enter the password : ");
            String pwd = sc.nextLine();
            if(pwd.equals(st[0])){
                b=4;
            }
            else
            b=0;
        }
        return b;
    }
    static int blockCheck(String[] arr1){
        int a=0;
        if((arr1[(arr1.length)-1]).equals("block")){
            a=3;
        }
        else
        a=0;
        return a;
    }
    static String[] userId(String st){
        String arr[] = new String[5];
        if(st.equals("person1")){
            arr=person1;
        }
        else if(st.equals("person2")){
            arr=person2;
        }
        else if(st.equals("person3")){
            arr=person3;
        }
        else if(st.equals("person4")){
            arr=person4;
        }
        return arr;
    }
    static void penaltyCheck(String[] arr,String st1){
        if(arr[1].equals("not")){
            System.out.println("ATM of different bank so penalty of Rs.50");
            int a=StrtoInt(arr[3]);
            a=a-50;
            miniAppend4(50,userMiniId(st1));
            String st = InttoStr(a);
            arr[3]=st;
        }
    }
    static void dailyUse(String[] arr,String st1){
        int ar = StrtoInt(arr[2]);
        if(ar>5){
            System.out.println("You have Exceeded the daily limits" +"\n" +"So the penalty Amount of Rs.5");
            miniAppend4(5,userMiniId(st1));
            int a=StrtoInt(arr[3]);
            a=a-5;
            String st = InttoStr(a);
            arr[3]=st;
        }
        else{
            int a=StrtoInt(arr[2]);
            a++;
            String st = InttoStr(a);
            arr[2]=st;
        }
    }
    static void changePin(String[] arr,String str){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the password : ");
        String st = sc.nextLine();
        arr[0]= st;
        System.out.println("Password changed Successfully");
        System.out.println("Click enter to exit");
        enterNeeded();
        userActions(str);
    }
    static void moneyTransfer(String[] arr1,String str){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the username you want to transfer money : ");
        String st = sc.nextLine();
        if(((!st.equals("person1")) && (!st.equals("person2")) && (!st.equals("person3")) && (!st.equals("person4"))) || (str.equals(st))){
            System.out.println("Invalid user");
            moneyTransfer(arr1,str);
        }
        String arr2[]=userId(st);
        System.out.print("Enter the Amount you want to Transfer : ");
        int amo=sc.nextInt();
        if(amo<=(StrtoInt(arr1[3]))){
        int amo1=StrtoInt(arr1[3])-amo;
        int amo2=StrtoInt(arr2[3])+amo;
        arr1[3]=InttoStr(amo1);
        arr2[3]=InttoStr(amo2);
        System.out.println("Money Transfered Successfully" +"\n" +"Enter to exit");
        miniAppend3(amo,userMiniId(st));
        enterNeeded();
        userActions(str);
        return;
        }
        else
        System.out.println("The Amount exceeded your Balance");
        moneyTransfer(arr1,str);
    }
    static int userChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Logged in Successfully" +"\n" +"   WELCOME USER" +"\n" +"1.Withdraw" +"\n" 
        +"2.Show Balance" +"\n" +"3.Mini Statements" +"\n" +"4.Transfer Money" +"\n" +"5.Pin Change" +"\n" +"6.Deposit" +"\n" +"7.Exit" +"\n" +"Enter the Choice : ");
        int cho = sc.nextInt();
        return cho;
    }
    static void userActions(String st){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        String ar[] = userId(st);
        int amt = StrtoInt(ar[3]);
        int cho = userChoice();
        penaltyCheck(userId(st),st);
        dailyUse(userId(st),st);
        if(cho==1){
            withDraw(amt,st);
        }
        else if(cho==2){
            showUserAmt(userId(st),st);
        }
        else if(cho==3){
            miniStatements(userMiniId(st),st);
        }
        else if(cho==4){
            moneyTransfer(userId(st),st);
        }
        else if(cho==5){
            changePin(userId(st),st);
        }
        else if(cho==6){
            depositAmt(amt,st);
        }
        else if(cho==7){
            return;
        }
        else{
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            cho = userChoice();
        }
    }
    static void showUserAmt(String[] arr,String st){
        System.out.println("The Total Amount you have is " +arr[3]);
        enterNeeded();
        userActions(st);
    }  
    static void withDraw(int usrbal,String st){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Amount you withdraw : ");
        int need = sc.nextInt();
        if(usrbal>=need){
            if(((arr[1]*arr[0])+(arr[3]*arr[2])+(arr[5]*arr[4])+(arr[7]*arr[6]))>=need){
                if(need%100==0){
                    helperAtm(need,st);
                }
                else{
                    System.out.println("Enter Amount in Hundred's");
                    withDraw(usrbal,st);
                }
            }
            else{
                System.out.println("Insufficient Amount in the ATM");
                withDraw(usrbal,st);
            }
        }
        else{
            System.out.println("You have insufficient balance" +"\n" +"You currently have " +usrbal);
            withDraw(usrbal,st);
            
        }
    }
    static void helperAtm(int wid,String st){
        int temp1=wid;
        int need=0,bal=wid;
        for(int i = 1 ; i<8 ; i=i+2){
            wid=bal;
            if(wid!=0){
                need=wid/arr[i-1];
                bal = wid%arr[i-1];
                if(need==arr[i]){
                    getAmount1(need,arr[i-1],arr[i],i);
                }
                else if(need>arr[i]){
                    int bal1=getAmount2(need,arr[i-1],arr[i],i);
                    bal=bal+bal1;
                } 
                else if(need!=0){
                    getAmount3(need,arr[i-1],arr[i],i);
                }   
            }
            else
            continue;  
        }
        System.out.println("Amount Withdrawn Successfully");
        miniAppend1(temp1,userMiniId(st));
        userReduce(temp1,userId(st));
        enterNeeded();
        userActions(st);
    }
    static void getAmount1(int need ,int val ,int i,int j){
        int get=i*val;
        System.out.println(val +" " +get/val);
        arr[j]=0;
    }
    static int getAmount2(int need , int val , int i,int j){
        int get=i*val;
        System.out.println(val +" " +get/val);
        int bal = (need-i)*val;
        arr[j]=0;
        return bal;
    }
    static void getAmount3(int need , int val , int i,int j){
        int get = need*val;
        System.out.println(val +" " +get/val);
        arr[j]=i-need;
    }
    static void userReduce(int a ,String[] st2){
        int red =StrtoInt(st2[3]);
        st2[3]=InttoStr(red-a);
    }
    static ArrayList<String> userMiniId(String st){
        ArrayList<String> list = new ArrayList<String>();
        if(st.equals("person1")){
            list=mini1;
        }
        else if(st.equals("person2")){
            list  = mini2;
        }
        if(st.equals("person3")){
            list = mini3;
        }
        if(st.equals("person4")){
            list = mini4;
        }
        return list;
    }
    static void miniAppend1(int amt , ArrayList<String> st){
        st.add("You have withdrawn the Amount of " +amt);
    } 
    static void miniAppend3(int amt , ArrayList<String> st){
        st.add("You have transfered the Amount of " +amt);
    } 
    static void miniAppend2(int amt,ArrayList<String> st){
        st.add("You have debited the Amount of " +amt);
    }
    static void miniAppend4(int amt,ArrayList<String> st){
        st.add("The penalty the Amount of " +amt +" is reduced");
    }
    static void depositAmt(int amt,String st){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount you want to debit : ");
        int deb = sc.nextInt();
        if(deb%100==0){
        System.out.print("Enter the number of 2000 notes : ");
        int twok=sc.nextInt();
        System.out.print("Enter the number of 500 notes : ");
        int fivh=sc.nextInt();
        System.out.print("Enter the number of 200 notes : ");
        int twoh=sc.nextInt();
        System.out.print("Enter the number of 100 notes : ");
        int oneh=sc.nextInt();
        int div = (oneh*100)+(twoh*200)+(fivh*500)+(twok*2000); 
        if(deb==div){
            arr[1]+=twok;
            arr[3]+=fivh;
            arr[5]+=twoh;
            arr[7]+=oneh;
            String[] st1 = userId(st);
            int change = StrtoInt(st1[3]);
            String st2 =InttoStr(deb+change);
            st1[3]=st2;
            System.out.println("Your Amount debited Successfully");
            enterNeeded();
            miniAppend2(deb,userMiniId(st));
            userActions(st);
        }
        else{
            System.out.println("The Amount you enter in not Equal");
            depositAmt(amt,st);
        }
        }
        else{
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Enter the amount in Hundreds");
            depositAmt(amt,st);
        }
        
    }
    static void miniStatements(ArrayList<String> st,String st1){
        int stsize = st.size();
        if(stsize==0){
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("No mini Statements");
            enterNeeded();
            userActions(st1);
        }
        else{
        for(int i =stsize-1 ,j=0; i!=0 && j<=5 ; i--,j++){
            System.out.println(st.get(i));
        }
        enterNeeded();
        userActions(st1);
        }
    } 
        static String InttoStr(int a){
        String st =Integer.toString(a);
        return st;
    }
    static int StrtoInt(String st){
        int a = Integer.parseInt(st);
        return a;
    }   
    static void enterNeeded(){
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
    }
}
