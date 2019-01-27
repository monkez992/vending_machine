package VendingMachine;

public enum  Coin {
	PENNY(1),NICKLE(5), DIME(10), QUARTER(25),HALFDOLLAR(50),ONEDOLLAR(100),TWODOLLAR(200);
	
	private int catgory ;
	private Coin(int catgory ) {
		this.catgory =catgory ;
		}
	
	public int getCatgory() {
		return catgory ;
		
	}

}
