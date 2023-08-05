package demo;
import java.util.*;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

// Derived class of Platform
public class DemoPlatform extends Platform
{ 
    private String str;
    private ArrayList<String> return_response=new ArrayList<>();
    private int count;
    private String portalID,requestID;
	private ArrayList<Seller> seller_details=new ArrayList<>();

	// Links Seller to Platform
	public boolean addSeller(Seller aSeller)
    {
        for(int i=0;i<seller_details.size();i++)
        {
            if(aSeller==seller_details.get(i))
            count++;
        }
        if(count==0)
        {
            seller_details.add(aSeller);
            return true;
        }
        else    
            return false;
    }

    // Stores current customer request to str
    public void setRequest(String command)
    {
        this.str=command;
    }

    // method to return response as per customer request
    public ArrayList<String> sendResponse()
    {
        ArrayList<String> temp=new ArrayList<>();
        temp.addAll(return_response);
        return_response.clear();
        return temp;
    }

    // method that processes customer requests
	public void processRequests() 
    {
        String req=str;     // customer request
        String response="";
        String arr[]=req.split(" ");
        if(arr.length==1)
        {
            return;
        }
        portalID=arr[0];
        requestID=arr[1];
        /*
        Response format:
        <PortalID> <RequestID> category1 category2 ... (where categories are as in Globals getCategoryName()
         */
        if(arr[2].equals("Start"))
        {
            String cat="";
            response=portalID+" "+requestID+" ";
            for(Globals.Category e: Globals.Category.values())
            {
                cat+=e.toString()+" ";
            }
            return_response.add(response+cat);
        }
        /*
        Respose format :
        <Portal ID> <RequestID> ProductName1 productUniqueName1 price1 quanitityAvailable1
        <Portal ID> <RequestID> ProductName2 productUniqueName2 price2 quanitityAvailable2
        ...
        ...
         */
        else if(arr[2].equals("List"))
        {
            ArrayList<Product> list_command_details=new ArrayList<Product>();
            String last_one=arr[3]; 
            if(last_one.equals("Mobile"))
            {
                for(Seller q:seller_details)
                {
                    ArrayList<Product> temp =new ArrayList<Product>();
                    temp=q.findProducts(Globals.Category.Mobile);    
                    list_command_details.addAll(temp);
                }
                for (Product q: list_command_details)
                {   
                    response=portalID+" "+requestID+" "+q.getName()+" "+q.getProductID()+" "+Float.toString(q.getPrice())+" "+Integer.toString(q.getQuantity());
                    return_response.add(response);
                }
            }

            else if(last_one.equals("Book"))
            {
                for(Seller q:seller_details)
                {
                    ArrayList<Product> temp =new ArrayList<Product>();
                    temp=q.findProducts(Globals.Category.Book);    
                    list_command_details.addAll(temp);
                }
            for (Product q: list_command_details)
                {
                    response=portalID+" "+requestID+" "+q.getName()+" "+q.getProductID()+" "+Float.toString(q.getPrice())+" "+Integer.toString(q.getQuantity());
                    return_response.add(response);
                }
            }

        }
        /*
        Response format :
         <PortalID> <ResponseID> Success 
         or
         <PortalID> <ResponseID> Failure
         */
        else if(arr[2].equals("Buy"))
        {
            String productID=arr[3];
            int numItems=Integer.parseInt(arr[4]);
            String target_seller=productID.split("-")[0];
            for( Seller q: seller_details)
            {
                if(target_seller.equals(q.getID().split("_")[0]))
                {
                    if(q.buyProduct(productID, numItems))
                    { 
                        response=portalID+" "+requestID+" "+"Success";
                        return_response.add(response);
                    }
                    else
                    {
                        response=portalID+" "+requestID+" "+"Failure";
                        return_response.add(response);
                    }
                }
            }
        }
    }
    
	
}
