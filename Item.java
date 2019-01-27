package VendingMachine;

public enum  Item {
	CANDY("Candy",10) , SNACK("Snack",50),NUTS("Nuts",90), Coke("Coke",25), Pepsi("pepsi",35), Soda("Soda",45) ;
private String name ;
private int price ;
private Item (String name , int price) {
	this.name = name ; 
	this.price = price ;
	}
public String getName () {
	return name ;
	
	}

public int getPrice () {
	return price ;
	
}

}
