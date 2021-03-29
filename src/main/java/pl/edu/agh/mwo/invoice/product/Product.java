package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	private final boolean isAkcyza;

	private final static BigDecimal akcyza = new BigDecimal("5.56");

	protected Product(String name, BigDecimal price, BigDecimal tax, boolean akcyza) {
		if (name == null || name.equals("") || price == null || tax == null || tax.compareTo(new BigDecimal(0)) < 0
				|| price.compareTo(new BigDecimal(0)) < 0) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.price = price;
		this.taxPercent = tax;
		this.isAkcyza = akcyza;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
        
    	if (isAkcyza) {
    		
    		return price.multiply(taxPercent).add(price).add(akcyza);
    		
    	} else {
    		
    		return price.multiply(taxPercent).add(price);
    	}
    	
    }
}
