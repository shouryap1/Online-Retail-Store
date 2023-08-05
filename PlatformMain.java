import java.util.*;
import demo.DemoPlatform;
import demo.SellerAnsh.*;
import demo.SellerRitik.*;
import demo.SellerShourya.*;
import java.io.*;
import ecomm.Seller;

// Main class that functions as Platform
public class PlatformMain 
{
	public static void main(String[] args) throws Exception
	{
		DemoPlatform pf = new DemoPlatform();  
		
		// Generating Seller IDs for all Sellers
		String shourya_ID=generate_ID("Shourya");
		String ansh_ID=generate_ID("Ansh");
		String ritik_ID=generate_ID("Ritik");
		
		// Adding Sellers to platform
		Seller shourya = new Shourya(shourya_ID); 
		shourya.addPlatform(pf);
		
		Seller ansh = new Ansh(ansh_ID);
		ansh.addPlatform(pf);
		
		Seller ritik = new Ritik(ritik_ID);
		ritik.addPlatform(pf);
		
		int lineNo=1;  // line iterator for .txt file
        int no=lineNo; // keeps track of the line no. from where we need to process the requests (excluding the requests that have already been processed)
    	while(true)
		{
			try 
			{
				// sending the actual Thread of execution to sleep 1 millisecond
				// executes main every 1 milli-second
				Thread.sleep(1);
			} 
       		// catch block to handle exceptions 
			catch(InterruptedException ie) {}

			try 
			{
				FileReader fr = new FileReader("PortalToPlatform.txt");
				BufferedReader br = new BufferedReader(fr);
				int count = 0;  // stores the no. of lines in .txt
				LineNumberReader ln = new LineNumberReader(new FileReader("PortalToPlatform.txt"));
				// counting no. of lines in .txt file
				while (ln.readLine() != null)
				{
					count++;   
				}
				ln.close();

				for (lineNo = 1; lineNo <= count; lineNo++) 
				{
					if(lineNo==no)
					{
						for (lineNo = no; lineNo <= count; lineNo++) 
						{
							pf.setRequest(br.readLine());
							pf.processRequests();
						}
					}
					else
						br.readLine();	// if the requests have already been processed, just read the line without processing it
				}
			no=count+1;
			} 
			catch (IOException e) {}
			ArrayList<String> returnList=new ArrayList<>();
			// ArrayList that contains response 
			returnList=pf.sendResponse();
			try 
			{
				// Writing to PlatformToPortal.txt
				BufferedWriter f_writer= new BufferedWriter(new FileWriter("PlatformToPortal.txt",true));
				for(int i=0;i<returnList.size();i++)
				{
					f_writer.write(returnList.get(i)+"\n");
				}
				f_writer.close();
        	}
 
        // catch block to handle exceptions 
        catch (IOException e) 
		{
            System.out.print(e.getMessage());
        }
		}	
	}

	//method that generates unique ID for each seller
	static String generate_ID(String name)
	{
		Random rand = new Random();                 
		int rand_no=rand.nextInt(1000);                //Generates a random number between 0 and 999
		String no=Integer.toString(rand_no);           
		String Unique_ID=name+"_"+no;                      
		return Unique_ID;                            
	}
}
