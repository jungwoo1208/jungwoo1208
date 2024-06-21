package Locker;

class locker implements java.io.Serializable{
    private int number;
    private String renter;
    private String time;
    boolean isRented;
    locker(int number,boolean isRented){
        this.number = number;
        this.isRented = isRented;
        this.renter = "";
        this.time = "";
    }
    locker(int number, boolean isRented, String renter, String time){
        this.number = number;
        this.isRented = isRented;
        this.renter = renter;
        this.time = time;
    }
    public int getNumber(){
        return this.number;
    }
    public String getRenter(){
        return this.renter;
    }
    public String getTime(){
        return this.time;
    }
    public boolean getIsRented(){
        return this.isRented;
    }
    public void setRenter(String renter){
        this.renter = renter;
    }
    public void setTime(String time){
        this.time = time;
    }
    public void setIsRented(boolean isRented){
        this.isRented = isRented;
    }


}
