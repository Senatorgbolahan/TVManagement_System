package tvmanagementsystem;



public class Subscription {

    private int installFee;   // fixed fee
    private int nbTV;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String datee;
    
    // Total fee
    private int totalFee;
    
    public Subscription(int nbTV, Subscriber subscriber, SubscriptionCycle cycle, String datee){
        this.nbTV = nbTV;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.datee = datee;
        
        this.installFee = nbTV * 10;
    }

    public void setNbTV(int nbTV) {
        this.nbTV = nbTV;
    }

    public int getNbTV() {
        return nbTV;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getTotalFee() {
        totalFee = installFee + 5;
        return totalFee;
    }

    public String getDatee() {
        return datee;
    }

    @Override
    public String toString() {
          return "Subscription{" +
                "installFee='" + installFee + '\'' +
                ", nbTV='" + nbTV + '\'' +
                ", subscriber='" + subscriber + '\'' +
                ", today='" + datee + '\'' +
                ", cycle='" + cycle + '\'' +
                '}';
    }
    
    
    
    
    
    
}
