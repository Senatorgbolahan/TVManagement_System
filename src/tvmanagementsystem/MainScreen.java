package tvmanagementsystem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class MainScreen extends JFrame{

    // Panel1: User Registrator
    JPanel subscribePanel;
    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;
    
    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;
    
    // Panel 2: Cycle
    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    JPanel cyclePanel;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;
    
    // Panel 3: Channel's packages
    JCheckBox sportCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;
    JPanel packagesPanel;
    
    // Panel 4: Package details
    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;
    JPanel detailsPanel;
    
    // Panel 5: Check and Payment
    JLabel installFeeLBL;
    JPanel feePanel;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;
    
    // Panel 6: Table (Data of Subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6Panel;
    
    // Panel 7: Action Panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7Actionpanel;
    
    
    // Classes and Objects
    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice = 0;
    int totalPrice;
    
    
    // Saving
      ArrayList<Subscription> listToSave = new ArrayList<>();
      File file;
    
    

    // Constructor
    public MainScreen(){
        
        /******************************* PANEL 1 ***************************************/
        subscribePanel = new JPanel();
        Border panelBorder = BorderFactory.createTitledBorder("Subscriber Details");
        subscribePanel.setBorder(panelBorder);
        subscribePanel.setBounds(15, 15, 300, 200);
        subscribePanel.setLayout(new GridLayout(4,2));
        
        
        // JLabel
        nameLBL = new JLabel("First Name");
        lastLBL = new JLabel("Last Name");
        mobileLBL = new JLabel("Mobile: ");
        cityLBL = new JLabel("City: ");
        
        // TextFields
        subName = new JTextField();
        subName.setOpaque(false);
        subLastName = new JTextField();
        subLastName.setOpaque(false);
        subMobile = new JTextField();
        subMobile.setOpaque(false);
        subCity = new JTextField();
        subCity.setOpaque(false);
        
        // Adding component to panel
        subscribePanel.add(nameLBL);
        subscribePanel.add(subName);
        subscribePanel.add(lastLBL);
        subscribePanel.add(subLastName);
        subscribePanel.add(mobileLBL);
        subscribePanel.add(subMobile);
        subscribePanel.add(cityLBL);
        subscribePanel.add(subCity);
        
        /******************************* PANEL 2 ***************************************/
        
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15, 230, 300, 500);
        cyclePanel.setLayout(new GridLayout(14,1));
        
        Border cycleBorder = BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(cycleBorder);
        
        // Components of cycle panel
        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: " + df.format(currentDate));
        
        // start cycle date
        startCycleLBL = new JLabel("Start Cycle Date (DD/MM/YYYY) ");
        startCycleFLD = new JTextField();
        
        // start cycle date
        endCycleLBL = new JLabel("End Cycle Date (DD/MM/YYYY) ");
        endCycleFLD = new JTextField();
        
        // Number of TVs
        numberTVLBL = new JLabel("Number of TV");
        numberTVFLD = new JTextField();
        
        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);
       
        // make opacity foe fields
        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);
        
        
        /******************************* PANEL 3 ***************************************/
        
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330, 15, 300, 200);
        packagesPanel.setLayout(new GridLayout(5,1));
        
        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packBorder);
        
        JLabel packagesJLBL = new JLabel("Please select your Package");
        sportCHKBX = new JCheckBox("Sports Package");
        moviesCHKBX = new JCheckBox("Movies Package");
        docCHKBX = new JCheckBox("Documentary Package");
        
        JButton subscribeBTN = new JButton("Subscribe");
        
        packagesPanel.add(packagesJLBL);
        packagesPanel.add(sportCHKBX);
        packagesPanel.add(moviesCHKBX);
        packagesPanel.add(docCHKBX);
        packagesPanel.add(subscribeBTN);
        
            
        // Checkbox Item Listeners
        sportCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportCHKBX.isSelected()){
                    DisplaySportsChannels();
                    // make price changes
                }
                else{
                    
                }
            }
        });
        
        moviesCHKBX.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                if (moviesCHKBX.isSelected()) {
                    DisplayMoviesChannels();
                }
                else{
                    
                }
            } 
        });
        
        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (docCHKBX.isSelected()) {
                    DisplayDocumentaryChannels();;
                } else {
                }
            }
        });
        
        subscribeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GetSubscriberData();
            }
        });
        
        /******************************* Panel 4 ***************************************/
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330, 230, 300, 500);
        detailsPanel.setLayout(new GridLayout(3,1));
        
        Border p4Border = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(p4Border);
        
        channelsAreaS = new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);
        
        channelsAreaM = new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);
        
        channelsAreaD = new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);
        
        detailsPanel.add(channelsAreaS);
        detailsPanel.add(channelsAreaM);
        detailsPanel.add(channelsAreaD);
        
        /******************************* Panel 5 ***************************************/
        feePanel = new JPanel();
        feePanel.setBounds(645, 15, 200, 200);
        feePanel.setLayout(new GridLayout(3,1));
        
        Border blackline5 = BorderFactory.createTitledBorder("Fee & Check");
        feePanel.setBorder(blackline5);
        
        installFeeLBL = new JLabel("Installation Fee: ");
        packageFeeLBL = new JLabel("Packages Fee : ");
        totalFeeLBL = new JLabel("Total Amount to Pay");
        
        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);
        
        /******************************* Panel 6 ***************************************/
        
        p6Panel= new  JPanel();
        p6Panel.setBounds(654, 230, 515, 500);
        p6Panel.setLayout(new GridLayout(3,1));
        
        Border border6 = BorderFactory.createTitledBorder("Our Customer");
        p6Panel.setBorder(border6);
        
        // Table
        // 1 - table Model
        tableModel = new DefaultTableModel();
        
        // 2 - Columns
        table = new JTable(tableModel);
        tableModel.addColumn("First name");
        tableModel.addColumn("Last name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");
        
        // 3 -Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);
        
        /******************************* Panel 7 ***************************************/
        p7Actionpanel = new JPanel();
        p7Actionpanel.setBounds(860, 15, 300, 200);
        
        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        p7Actionpanel.setBorder(border7);
        p7Actionpanel.setLayout(new GridLayout(3,1));
        
        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");
        
        p7Actionpanel.add(newBTN);
        p7Actionpanel.add(saveBTN);
        p7Actionpanel.add(loadBTN);
        
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubcriptionToDisk();
            }
        });
        
        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });
        
        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadDataFromDisk();
            }
        });
        
        
        
        
           // Adding Panels to JFrame
        setLayout(null);        // null layout
        add(subscribePanel);      // panel 1
        add(cyclePanel);          // panel 2
        add(packagesPanel);       // panel 3
        add(detailsPanel);        // panel 4
        add(feePanel);            // panel 5
        add(p6Panel);             // panel 6
        add(p7Actionpanel);       // panel 7
    }
    
    
    
    /******************************* METHODS ***************************************/
    public void NewSubscription(){
        // All Fields are empty
        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");
        
        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");
        
        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total Amount to Pay: ");
        
        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);
        sportCHKBX.setSelected(false);
    }
    
    public void SaveSubcriptionToDisk(){
        
        listToSave.add(subscription);
        file = new File("src/tvmanagementsystem/tvList.txt");
        
        try {
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
                  
            // saving the list of subscriptions
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
    
    public void LoadDataFromDisk(){
        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("src/tvmanagementsystem/tvList.txt");
        
        try {
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            
            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        
        
        for (Subscription sub : s) {
            DisplaySubscriptionsInTable(sub);
        }
        
    }
    
    public  int DisplaySportsChannels(){
        SportChannel m1 = new SportChannel("AFN Sports", "EN", "SPRT", 5);
        SportChannel m2 = new SportChannel("EDG Sports", "FR", "SPRT", 3);
        SportChannel m3 = new SportChannel("ELEVEN Sports", "EN", "SPRT", 2);
        SportChannel m4 = new SportChannel("NBA TV", "EN", "SPRT", 6);
        SportChannel m5 = new SportChannel("NFL Network", "EN", "SPRT", 3);
        SportChannel m6 = new SportChannel("SKYE Sports", "EN", "SPRT", 2);
        
        
        ArrayList<SportChannel> sportList = new ArrayList<>();
        sportList.add(m1);
        sportList.add(m2);
        sportList.add(m3);
        sportList.add(m4);
        sportList.add(m5);
        sportList.add(m6);
        
        String sportChannelString = "";
        int packagePrice = 0;
        
        for (int i = 0; i < sportList.size(); i++) {
            sportChannelString += 
                    "    " + sportList.get(i).getChannelName()
                  + "    " + sportList.get(i).getLanguage()
                  + "    " + sportList.get(i).getPrice()
                  + "\n";
            packagePrice = sportList.get(i).getPrice();
        }
        channelsAreaS.setText(sportChannelString);
        
        return  packagePrice;

    }
    
    public int DisplayMoviesChannels(){
        MovieChannel m1 = new MovieChannel("MBC Bundle", "EN", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema One", "EN", "MOV", 5);
        MovieChannel m3 = new MovieChannel("Cinema Pro", "RU", "MOV", 6);
        MovieChannel m4 = new MovieChannel("Cinema 414", "AR", "MOV", 2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV", 4);
        MovieChannel m6 = new MovieChannel("Film 44", "FR", "MOV", 2);
        
        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);
        
        String movieChannelString = "";
        int packagePrice = 0;
        
        for (int i = 0; i < movieList.size(); i++) {
            movieChannelString += 
                    "    " + movieList.get(i).getChannelName()
                  + "    " + movieList.get(i).getLanguage()
                  + "    " + movieList.get(i).getPrice()
                  + "\n";
            packagePrice = movieList.get(i).getPrice();
        }
        
        channelsAreaM.setText(movieChannelString);
        
        return packagePrice;
    }
    public int DisplayDocumentaryChannels(){
        DocumentaryChannel m1 = new DocumentaryChannel("MTvBase", "EN", "Entertainment", 3);
        DocumentaryChannel m2 = new DocumentaryChannel("NAT. GEO", "EN", "Doc", 2);
        DocumentaryChannel m3 = new DocumentaryChannel("AL Jazeera", "USA", "News", 3);
        DocumentaryChannel m4 = new DocumentaryChannel("Canal D", "GR", "Entertainment", 4);
        DocumentaryChannel m5 = new DocumentaryChannel("Discovery", "IN", "Entertainment", 5);
        DocumentaryChannel m6 = new DocumentaryChannel("World Documentary", "AR", "Doc", 1);
        DocumentaryChannel m7 = new DocumentaryChannel("Discovery Historia", "AR", "Doc", 8);
        
        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);
        documentaryChannels.add(m7);
        
        
        String docChannelString = "";
         int packagePrice = 0;
         
        for (int i = 0; i < documentaryChannels.size(); i++) {
            docChannelString += 
                    "    " + documentaryChannels.get(i).getChannelName()
                  + "    " + documentaryChannels.get(i).getLanguage()
                  + "    " + documentaryChannels.get(i).getPrice()
                  + "\n";
            
            packagePrice = documentaryChannels.get(i).getPrice();
        }
        channelsAreaD.setText(docChannelString);
        
        return packagePrice;
    }
    
    public void GetSubscriberData() throws ParseException{
        Date currentDate = new Date();
        
        // Subscriber Data
        subscriber = new Subscriber(subName.getText(), subLastName.getText(), subCity.getText(), Integer.parseInt(subMobile.getText()));
        
        
        // Cycle
        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle = df.parse(endCycleFLD.getText());
        
        SubscriptionCycle cycle = new SubscriptionCycle(df.format(startCycle), df.format(endCycle));
        
        // Subscription
        subscription = new Subscription(Integer.parseInt(numberTVLBL.getText()), subscriber, cycle, df.format(currentDate));
        installFeeLBL.setText("Installation Fee: " + subscription.getTotalFee() + " $");
        
    }
    
    public void showPrice(){
        
        if (docCHKBX.isSelected()) {
            packagesSelectedPrice += DisplayDocumentaryChannels();
        }
        else if (moviesCHKBX.isSelected()) {
            packagesSelectedPrice += DisplayMoviesChannels();
        }
        else if (sportCHKBX.isSelected()) {
            packagesSelectedPrice += DisplaySportsChannels();
        }
        packageFeeLBL.setText("Packages Fee: " + packagesSelectedPrice + " $");
        totalPrice = subscription.getTotalFee() + packagesSelectedPrice;
        
        totalFeeLBL.setText("Total Amount to Pay: " + totalPrice + " $");
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
        mainScreen.setBounds(20, 10, 1200, 800);
    }

    private void DisplaySubscriptionsInTable(Subscription sub) {
        
        // Displaying Data from disk into table
        tableModel.addRow(new Object[]{
            sub.getSubscriber().getfName(),
            sub.getSubscriber().getlName(),
            sub.getSubscriber().getPhone(),
            sub.getCycle().getStartDate(),
            sub.getCycle().getEndDate(),
            sub.getTotalFee()
        });
    }
    
}
