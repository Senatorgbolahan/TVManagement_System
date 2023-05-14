package tvmanagementsystem;



    public class Subscriber {

        private String fName;
        private String lName;
        private String city;
        private int phone;
        
        public Subscriber(String fName, String lName, String city, int phone){
            this.fName = fName;
            this.lName = lName;
            this.city = city;
            this.phone = phone;
        }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfName() {
        return fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getlName() {
        return lName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
        
    
}
