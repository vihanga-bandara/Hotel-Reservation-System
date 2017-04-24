import java.io.*;
import java.util.*;
public class main {

	static String[]hotel = new String[11];	
	static String Cust_Name;
	static int Room_No;
	static String ans;
	static int check;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		initialise(hotel);
		while (ans!="Exit"){
			System.out.println("\n------------Welcome to Galle Face Hotel------------\n");
			System.out.println("\tSelect Your Choice and Enter Below :\n");
			System.out.println("\tA    : Add a customer to a room");
			System.out.println("\tV    : To view all rooms");
			System.out.println("\tE    : Display Empty Rooms");
			System.out.println("\tD    : Delete Customer From Room");
			System.out.println("\tF    : Find Room From Customer Name");
			System.out.println("\tS    : Store Program array data into a plain text");
			System.out.println("\tL    : Load Program Back From the file into the Array");
			System.out.println("\tO    : View Rooms Ordered Alphabetically By Name");
			System.out.println("\tExit : To Exit The Program");
			System.out.print("\n|Choice|: ");
			ans=sc.next();
			if (ans.equalsIgnoreCase("A")){
				Cust_add(hotel);
			} else if (ans.equalsIgnoreCase("V")){
				View_Room(hotel);
			} else if (ans.equalsIgnoreCase("E")){
				Empty_Room(hotel);
			} else if (ans.equalsIgnoreCase("D")){
				Del_Cust(hotel);
			}else if (ans.equalsIgnoreCase("F")){
				Find_Room(hotel);

			}else if (ans.equalsIgnoreCase("S")){
				Store_Array(hotel);		
			} else if (ans.equalsIgnoreCase("L")){
				Load_Array(hotel);
			}else if(ans.equalsIgnoreCase("O")){
				Order_View(hotel);
			}else if (ans.equalsIgnoreCase("Exit")){
				Leave();
			}
		}

	}
	private static void initialise(String hotelRef[]){
		for (int x=0;x<=10;x++){
			hotelRef[x]="empty";
		}
	}
	private static void Cust_add (String [] hotel){
		do {
			check=0;	
			System.out.print("Enter Room No : ");
			Room_No=sc.nextInt();
			if (hotel[Room_No].equals("empty")){
				check=1;
				System.out.println("Room is available!");
			} else 
				System.out.println("Room not available..Try another Room");

		}while (check!=1);

		System.out.print("Enter Customer Name : ");
		Cust_Name=sc.next();
		hotel[Room_No]=Cust_Name;
		System.out.println("Customer has been added to Room");
		Leave();
	}
	private static void View_Room(String [] hotel){
		for (int i=0;i<=10;i++){
			if (hotel[i].equals("empty")){
				System.out.println("Room "+i+" is empty");
			} else {
				System.out.println("Room "+i+" is occupied by "+hotel[i]);


			}
		}
		Leave();
	}
	private static void Empty_Room(String [] hotel){
		for (int i=0;i<=10;i++){
			if (hotel[i].equals("empty")){
				System.out.println("Room "+i+" is Empty");
			}
		}
	}
	private static void Del_Cust(String [] hotel){
		System.out.print("Name of Customer to be deleted : ");
		String delete=sc.next();
		for (int i=0;i<=10;i++){
			if (hotel[i].equalsIgnoreCase(delete)){
				hotel[i]="empty";
				System.out.println("Customer has been deleted");
				break;
			}
		}
		Leave();
	}
	private static void Find_Room (String [] hotel){
		System.out.print("Enter Customer Name to Find : ");
		String customer=sc.next();
		check=0;
		for (int i=0;i<=10;i++){
			if (hotel[i].equalsIgnoreCase(customer)){
				System.out.println("The Room Number of "+customer+" is " +i);
				check=1;
			}
		}
		if (check==0){
			System.out.println("Customer has not booked any room");
		}
		Leave();
	}
	private static void Store_Array (String [] hotel) {

		try {
			File bit= new File("Arraydata.txt");
			if (!bit.exists()) {
				bit.createNewFile();
			}
			FileWriter b=new FileWriter(bit);
			BufferedWriter lol=new BufferedWriter(b);
			for (int i=0;i<hotel.length;i++){
				lol.write(hotel[i]);
				lol.newLine();
			}
			lol.close();

		}catch (Exception e){
			System.out.println("There seems to be an error. Please Try Again");
		} 
		System.out.println("Data saved to a plain text file");
	}
	private static void Load_Array (String[] hotel){
		File c=new File("Arraydata.txt");
		Scanner z=null;
		try {
			z=new Scanner(c);
			int count=0;
			while (z.hasNextLine()){
				String arr=z.nextLine();
				hotel[count]=arr;
				count++;
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found. Please Try Again");
		}finally {
			System.out.println("The Program data has been successfully loaded");
		}
	}
	private static void Order_View(String [] hotel){
		int str;
		for (int j=0;j<hotel.length-1;j++){
			str=j;
			for(int i=j+1;i<hotel.length;i++){
				if (hotel[i].trim().toLowerCase().compareTo(hotel[str].trim().toLowerCase())<0){ //alphabetical order check...you can also use ignorecase
					str=i;
				}
			}
			
			if(str!=j){
				String temp=hotel[j];
				hotel[j]=hotel[str];
				hotel[str]=temp;
			}
		}
		System.out.println("You are now viewing all room ordered alphabetically");
		for (int i=0;i<=10;i++){
			if (!hotel[i].equals("empty")){
				System.out.println("Room "+i+" is occupied by "+(hotel[i].toUpperCase()));
			}
			
		}
		for (int i=0;i<=10;i++){
			if (hotel[i].equals("empty")){
				System.out.println("Room "+i+" is "+hotel[i].toUpperCase());
			}
			
		}
		Leave();
	}
	private static void Leave(){
		System.out.println("Do you want to Exit the Program ? [Yes] or [No]");
		String ans=sc.next();
		if (ans.equalsIgnoreCase("Yes")){
			System.out.println("Program is Closing.....");
			System.exit(0);
		}
		
	}

}

