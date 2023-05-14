package tvmanagementsystem;



public abstract class TvChannel {

    String channelName;
    String language;
    String category;
    int price;
    
    public TvChannel(String channelName, String language, String category, int price){
        this.channelName = channelName;
        this.language = language;
        this.category = category;
        this.price = price;
    }
    
    public  void setChannelName(String channelName){
        this.channelName = channelName;
    }
    
    public  String getChannelName(){
        return  channelName;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    
    
 
    
}
