package demo.SellerAnsh;
import demo.DemoProduct;
import ecomm.*;

// Derived class of DemoProduct
public class Book extends DemoProduct
{
    private String name,productID;
    private int price;
    private int quantity;

    public Book(String name,String productID,int price,int quantity)
    {
        this.name=name;
        this.productID=productID;
        this.price=price;
        this.quantity=quantity;
    }

    // enum that contains the available books
    public enum SubCategoryBook 
    {
		Physics,
        Maths,
        Chemistry,
        Biology
	}

    // Getter methods for Book details
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public Globals.Category getCategory() 
    {
        return Globals.Category.Book;
    }
    @Override
    public String getProductID() 
    {
        return this.productID;
    }
    @Override
    public float getPrice() 
    {
        return this.price;
    }
    @Override
    public int getQuantity() 
    {
        return this.quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
}
