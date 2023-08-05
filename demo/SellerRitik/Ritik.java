package demo.SellerRitik;
import java.util.*;

import demo.*;
import demo.SellerRitik.Book.SubCategoryBook;
import demo.SellerRitik.Mobile.SubCategoryMobile;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

// derived class of Seller
public class Ritik extends Seller
{
    private ArrayList<DemoProduct> Mobile_dummy=new ArrayList<>(), Book_dummy = new ArrayList<>();
    private ArrayList<Product> Mobile=new ArrayList<>(), Book=new ArrayList<>();
    private ArrayList<String> Mobile_ID=new ArrayList<>(), Book_ID=new ArrayList<>();
    private ArrayList<Integer> Mobile_quantity=new ArrayList<>(), Book_quantity=new ArrayList<>();

    public Ritik(String ID)
    {
        super(ID);    // Seller ID
        this.addSubProducts();
    }
    
    // method to add products to Seller
    // Create appropriate ArrayLists for Product, ProductID, Product Quantity
    public void addSubProducts()
    {
        for(SubCategoryMobile var: SubCategoryMobile.values())
        {
            int price = 0, quantity = 0;
            switch(var)
            {
                case Vivo:
                price = 450;
                quantity = 17;
                break;
                case Iphone:
                price = 620;
                quantity = 19;
                break;
                case Oppo:
                price = 410;
                quantity = 21;
                break;
                case Oneplus:
                price = 530;
                quantity = 28;
                break;
                default:
                price = 0;
                quantity = 0;
                break;
            }
            DemoProduct obj = new Mobile(var.toString(), "Ritik-Mobile-"+var.toString(), price, quantity);
            Mobile_dummy.add(obj);
            Mobile_ID.add("Ritik-Mobile-"+var.toString());
            Mobile_quantity.add(quantity);
        }
        Mobile.addAll(Mobile_dummy);
        for(SubCategoryBook var: SubCategoryBook.values())
        {
            int price = 0, quantity = 0;
            switch(var)
            {
                case Physics:
                price = 29;
                quantity = 30;
                break;
                case Maths:
                price = 40;
                quantity = 31;
                break;
                case Java:
                price = 36;
                quantity = 42;
                break;
                case Python:
                price = 53;
                quantity = 20;
                break;
                default:
                price = 0;
                quantity = 0;
                break;
            }
            DemoProduct obj = new Book(var.toString(), "Ritik-Book-"+var.toString(), price, quantity);
            Book_dummy.add(obj);
            Book_ID.add("Ritik-Book-"+var.toString());
            Book_quantity.add(quantity);
        }
        Book.addAll(Book_dummy);
    }

    // method that links seller to platform
    public void addPlatform(Platform thePlatform)
    {
        thePlatform.addSeller(this);                                       
    }

    // method that returns ArrayList of available products
    public ArrayList<Product> findProducts(Globals.Category whichOne)
    {
        switch(whichOne) 
        {
            case Book:
              return Book;
            case Mobile:
               return Mobile;
            default:
                return null;
        }
    }; 

    // this method is called when customer buys a product
    // makes changes in the available stock of Seller accordingly
    public boolean buyProduct(String productID, int quantity)
    {
        SubCategoryMobile mobileCate = null;
        SubCategoryBook bookCate = null;
        for (SubCategoryMobile myVar : SubCategoryMobile.values()) 
        {
            if(productID.equals("Ritik-Mobile-"+myVar.toString()))
            {
                mobileCate=myVar;
                break;
            }
            else
            {
                mobileCate=null;
            }
          }
        for (SubCategoryBook myVar : SubCategoryBook.values()) 
        {
            if(productID.equals("Ritik-Book-"+myVar.toString()))
            {
                bookCate=myVar;
                break;
            }
            else
            {
                bookCate=null;
            }
        }
        
        if(mobileCate==null && bookCate==null)
        {
            return false;
        }
        else if(mobileCate==null && bookCate!=null)
        {
            int counter = 0;
            for(String bookId: Book_ID)
            {
                if(bookId.equals(productID))
                {
                    if(Book_quantity.get(counter)>=quantity)
                    {
                        Book_quantity.set(counter,(Book_quantity.get(counter)-quantity));
                        DemoProduct obj = Book_dummy.get(counter);
                        obj.setQuantity(Book_quantity.get(counter));
                        return true;
                    }
                    else return false;
                }
                counter++;
            }
        }
        else if(mobileCate!=null && bookCate==null)
        {
            int counter = 0;
            for(String mobileId: Mobile_ID)
            {
                if(mobileId.equals(productID))
                {
                    if(Mobile_quantity.get(counter)>=quantity)
                    {
                        Mobile_quantity.set(counter,(Mobile_quantity.get(counter)-quantity));
                        DemoProduct obj = Mobile_dummy.get(counter);
                        obj.setQuantity(Mobile_quantity.get(counter));
                        return true;
                    }
                    else return false;
                }
                counter++;
            }
        }
        return false;
    }; 
    
}
