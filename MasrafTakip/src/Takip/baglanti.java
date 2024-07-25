package Takip;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class baglanti implements Runnable {
	private static Map<String, String> users = new HashMap<String,String>();
	private static final String AUTH_USERNAME = "admin";
    private static final String AUTH_PASSWORD = "admin";
	private String kullanici_adi = "root";
	private String parola = "";
	private String db_ismi = "demo";
	private String host = "localhost";
	private int port = 3500;
	public Statement statement = null;
	
	public boolean SadeceHarfMi(String str) {
	    return str != null && str.matches("[a-zA-Z]+");
	}
	public static boolean IntegerMı(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
	
	Connection con = null;
	static {
		users.put("hasan", "1350005");
    }
	public static boolean authenticate(String username, String password) {
        String storedPassword = users.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
	
	
	public baglanti() {
		String url = "jdbc:mysql://" + host + ":" + port + "/" +db_ismi;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Driver bulunamadı");
		}
		
		try {
			con = DriverManager.getConnection(url,kullanici_adi,parola);
			System.out.println("Baglanti basarili");
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("Baglanti basarisiz");
			
		}
	}
	public void BilgileriGetir() {
		String sorgu = "Select * from MasrafTakip";
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sorgu);
			
			while(rs.next()) {
				String isim = rs.getString("isim");
				int masraf = rs.getInt("masraf");
				System.out.println("isim : " + isim + " masraf: " + masraf );
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void VeriGüncelle() {
		try {
			statement = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("isim :");
			String isim = sc.nextLine();
			System.out.println("masraf : ");
			int masraf = sc.nextInt();
			String sorgu = "Update MasrafTakip Set masraf = " + "'" + masraf + "'"  + " where isim = " + "'" + isim + "'";
			statement.executeUpdate(sorgu);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void KisiEkle() {
		try {
			statement = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("isim :");
			String isim = sc.nextLine();
			if (SadeceHarfMi(isim) ) {
				System.out.println("masraf : ");
				String masraf = sc.nextLine();
				if (IntegerMı(masraf)) {
		            int number = Integer.parseInt(masraf);
		            String sorgu = "Insert into MasrafTakip (isim,masraf) VALUES(" +"'" + isim +"'" + "," + "'" + number + "'"; 
					statement.executeUpdate(sorgu);
		        } else {
		            System.out.println("Geçersiz giriş! Lütfen sadece rakamları kullanın.");
		        }
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void KisiSil() {
		try {
			statement = con.createStatement();
			Scanner sc = new Scanner(System.in);
			System.out.println("isim :");
			String isim = sc.nextLine();
			String sorgu = "Delete FROM MasrafTakip where isim = " + "'" + isim + "'"; 
			statement.executeUpdate(sorgu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void toplamHarcama() {
		String sorgu = "Select * from MasrafTakip";
		int toplamMasraf = 0;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sorgu);
			
			while(rs.next()) {
				String isim = rs.getString("isim");
				int masraf = rs.getInt("masraf");
				System.out.println("isim : " + isim + " masraf: " + masraf );
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void GünlükHaftalıkAylıkMasraf() {
		String sorgu = "Select * from MasrafTakip";
		int haftalıkMasraf = 0;
		int aylıkMasraf = 0;
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sorgu);
			
			while(rs.next()) {
				String isim = rs.getString("isim");
				int masraf = rs.getInt("masraf");
				haftalıkMasraf = masraf * 7;
				aylıkMasraf = masraf * 30;
				System.out.println("isim : " + isim + " günlük masraf : " + masraf + " haftalık masraf : " + haftalıkMasraf + " aylık masraf : " + aylıkMasraf);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	 public void scheduleTask() {
	     
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        Runnable task = new Runnable() {
	            @Override
	            public void run() {
	                GünlükHaftalıkAylıkMasraf();
	            }
	        };
	        
	        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);
	    }

	
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
        System.out.print("Kullanıcı adınızı girin: ");
        String username = scanner.nextLine();

        System.out.print("Parolanızı girin: ");
        String password = scanner.nextLine();

        if (authenticate(username, password)) {
            System.out.println("Giriş başarılı!");
        } else {
            System.out.println("Giriş başarısız! Kullanıcı adı veya parola yanlış.");
        }
        System.out.print("Http Kullanıcı adınızı girin: ");
        String as = scanner.nextLine();
        System.out.print("Parolanızı girin: ");
        String b = scanner.nextLine();
        if (as.equals(AUTH_USERNAME) && b.equals(AUTH_PASSWORD)) {
        	 System.out.println("Giriş başarılı!");
        	try {
                HttpServer1.startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
        else {
        	 System.out.println("Giriş başarısız! Kullanıcı adı veya parola yanlış.");
		}
        
		baglanti a = new baglanti();
		 
       
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
