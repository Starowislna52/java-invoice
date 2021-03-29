package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

	private Map<Product, Integer> products = new HashMap<Product, Integer>();

	private static int invoicesCounter = 0;

	private final int invoiceNumber;

	public Invoice() {

		invoiceNumber = ++invoicesCounter;
	}

	public void addProduct(Product product) {

		if (isProductAlreadyInTheInvoice(product)) {

			products.put(product, products.get(product) + 1);

		} else {

			products.put(product, 1);
		}
	}

	public void addProduct(Product product, Integer quantity) {
		if (product == null || quantity <= 0) {
			throw new IllegalArgumentException();
		}

		if (isProductAlreadyInTheInvoice(product)) {

			products.put(product, products.get(product) + quantity);

		} else {

			products.put(product, quantity);
		}
	}

	private boolean isProductAlreadyInTheInvoice(Product product) {

		boolean result = false;

		for (Map.Entry<Product, Integer> entry : products.entrySet()) {

			if (entry.getKey().getName().equals(product.getName())) {

				result = true;

			}
		}

		return result;
	}

	public BigDecimal getNetTotal() {
		BigDecimal totalNet = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalNet = totalNet.add(product.getPrice().multiply(quantity));
		}
		return totalNet;
	}

	public BigDecimal getTaxTotal() {
		return getGrossTotal().subtract(getNetTotal());
	}

	public BigDecimal getGrossTotal() {
		BigDecimal totalGross = BigDecimal.ZERO;
		for (Product product : products.keySet()) {
			BigDecimal quantity = new BigDecimal(products.get(product));
			totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
		}
		return totalGross;

	}

	public int getInvoiceNumber() {

		return invoiceNumber;

	}

	public Map<Product, Integer> getProductList() {

		return products;
	}

	public void printInvoice() {

		System.out.printf("%35s %5d", "Numer faktury:", invoiceNumber);
		System.out.println();
		System.out.println();
		System.out.printf("%15s %20s %20s %20s", "Numer kolejny", "Nazwa pozycji", "Sztuk", "Cena jednostkowa");
		System.out.println();
		getProductListForPrinting();

	}

	private void getProductListForPrinting() {

		int invoicePosition = 1;
		int productCounter = 0;
		for (Map.Entry<Product, Integer> entry : products.entrySet()) {

			System.out.printf("%15d %20s %20d %20.2f", invoicePosition, entry.getKey().getName(), entry.getValue(),
					entry.getKey().getPrice());
			System.out.println();
			productCounter++;
			invoicePosition++;
		}

		System.out.println();
		System.out.println();
		System.out.printf("%55s %20d", "Liczba pozycji na fakturze", productCounter);
	}

}
