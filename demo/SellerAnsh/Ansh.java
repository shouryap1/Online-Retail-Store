package demo.SellerAnsh;
import java.util.*;

import demo.*;
import demo.SellerAnsh.Book.SubCategoryBook;
import demo.SellerAnsh.Mobile.SubCategoryMobile;
import ecomm.Globals;
import ecomm.Platform;
import ecomm.Product;
import ecomm.Seller;

// derived class of Seller
public class Ansh extends Seller
{
    private ArrayList<Product> Mobile=new ArrayList<>(), Book=new ArrayList<>();                   
    private ArrayList<DemoProduct> Mobile_dummy=new ArrayList<>(), Book_dummy = new ArrayList<>();
    private ArrayList<String> Mobile_ID=new ArrayList<>(), Book_ID=new ArrayList<>();
    private ArrayList<Integer> Mobile_quantity=new ArrayList<>(), Book_quantity=new ArrayList<>();

    public Ansh(String ID)
    {
        super(ID);  // Seller ID
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
                case Xiaomi:
                price = 299;
                quantity = 27;
                break;
                case Iphone:
                price = 799;
                quantity = 66;
                break;
                case Redmi:
                price = 359;
                quantity = 9;
                break;
                case Oneplus:
                price = 499;
                quantity = 19;
                break;
                default:
                price = 0;
                quantity = 0;
                break;
            }
            DemoProduct obj = new Mobile(var.toString(), "Ansh-Mobile-"+var.toString(), price, quantity);
            Mobile_dummy.add(obj);
            Mobile_ID.add("Ansh-Mobile-"+var.toString());
            Mobile_quantity.add(quantity);
        }
        Mobile.addAll(Mobile_dummy);
        for(SubCategoryBook var: SubCategoryBook.values())
        {
            int price = 0, quantity = 0;
            switch(var)
            {
                case Physics:
                price = 33;
                quantity = 28;
                break;
                case Maths:
                price = 12;
                quantity = 43;
                break;
                case Chemistry:
                price = 59;
                quantity = 4;
                break;
                case Biology:
                price = 90;
                quantity = 2;
                break;
                default:
                price = 0;
                quantity = 0;
                break;
            }
            DemoProduct obj = new Book(var.toString(), "Ansh-Book-"+var.toString(), price, quantity);
            Book_dummy.add(obj);
            Book_ID.add("Ansh-Book-"+var.toString());
            Book_quantity.add(quantity);
        }
        Book.addAll(Book_dummy);
        
    }

    // method that links seller to platform
    public void addPlatform(Platform thePlatform)
    {
        thePlatform.addSeller(this);                                       

    };

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
            if(productID.equals("Ansh-Mobile-"+myVar.toString()))
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
            if(productID.equals("Ansh-Book-"+myVar.toString()))
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
                if(mobileId.equals(productID)){
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
