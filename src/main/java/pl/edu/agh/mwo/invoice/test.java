package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;

import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;

public class test {

	public static void main(String[] args) {

		Product ksiazka = new OtherProduct("ksiazka", new BigDecimal("42"));
		Product ksiazka2 = new OtherProduct("ksiazka2", new BigDecimal("420"));
		Product plyta = new OtherProduct("plyta", new BigDecimal("20"));
		Invoice invoice = new Invoice();
		invoice.addProduct(ksiazka);
		invoice.addProduct(ksiazka);
		invoice.addProduct(ksiazka2);
		invoice.addProduct(plyta);
		invoice.addProduct(plyta, 2);
		invoice.printInvoice();
		
	}

}