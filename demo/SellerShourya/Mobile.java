package demo.SellerShourya;
import demo.*;
import ecomm.*;

// Derived class of DemoProduct
public class Mobile extends DemoProduct
{
    private String name,productID;
    private int price;
    private int quantity;

    public Mobile(String name,String productID,int price,int quantity)
    {
        this.name=name;
        this.productID=productID;
        this.price=price;
        this.quantity=quantity;
    }

    // enum that contains the available mobiles
    public enum SubCategoryMobile 
    {
		Realme,
        Oneplus,
        Nothing,
        Iphone
	}

    // Getter methods for Mobile details
    @Override
    public String getName()
    {
        return this.name;
    }
    @Override
    public Globals.Category getCategory() 
    {
        return Globals.Category.Mobile;
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
