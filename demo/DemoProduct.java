package demo;
import ecomm.Product;

// Derived class of Product
public abstract class DemoProduct extends Product 
{
    // method to maintain the correct quantity of products on purchase
    public abstract void setQuantity(int quantity);
}
