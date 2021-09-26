package vreme;

import javax.jws.WebService;
import javax.jws.WebMethod;
import users.User;
import prognoza.Prognoza;

import java.util.ArrayList;
import java.util.List;

@WebService
public class Vreme {
    private List<Prognoza> prognozi;
    private List<User> users;
    
    public Vreme() {
    	prognozi = new ArrayList<Prognoza>();
        Prognoza p1 = new Prognoza();
        p1.setId(1);
        p1.setgrad("Skopje");
        p1.setmomentalna("36stepeni");
        p1.setmomentalnastatus("soncevo");
        p1.setin3hours("33stepeni");
        p1.setstatus3h("soncevo");
        p1.setin1day("32stepeni");
        p1.setstatus1d("oblacno");
        p1.setin2days("29stepeni");
        p1.setstatus2d("vrnezlivo");
        p1.setin3days("30stepeni");
        p1.setstatus3d("oblacno");
        prognozi.add(p1);
        Prognoza p2 = new Prognoza();
        p2.setId(2);
        p2.setgrad("Tetovo");
        p2.setmomentalna("36stepeni");
        p2.setmomentalnastatus("soncevo");
        p2.setin3hours("33stepeni");
        p2.setstatus3h("soncevo");
        p2.setin1day("32stepeni");
        p2.setstatus1d("oblacno");
        p2.setin2days("29stepeni");
        p2.setstatus2d("vrnezlivo");
        p2.setin3days("30stepeni");
        p2.setstatus3d("oblacno");
        prognozi.add(p2);
        Prognoza p3 = new Prognoza();
        p3.setId(3);
        p3.setgrad("Gostivar");
        p3.setmomentalna("36stepeni");
        p3.setmomentalnastatus("soncevo");
        p3.setin3hours("33stepeni");
        p3.setstatus3h("soncevo");
        p3.setin1day("32stepeni");
        p3.setstatus1d("oblacno");
        p3.setin2days("29stepeni");
        p3.setstatus2d("vrnezlivo");
        p3.setin3days("30stepeni");
        p3.setstatus3d("oblacno");
        prognozi.add(p3);
        Prognoza p4 = new Prognoza();
        p4.setId(4);
        p4.setgrad("Struga");
        p4.setmomentalna("36stepeni");
        p4.setmomentalnastatus("soncevo");
        p4.setin3hours("33stepeni");
        p4.setstatus3h("soncevo");
        p4.setin1day("32stepeni");
        p4.setstatus1d("oblacno");
        p4.setin2days("29stepeni");
        p4.setstatus2d("vrnezlivo");
        p4.setin3days("30stepeni");
        p4.setstatus3d("oblacno");
        prognozi.add(p4);
        Prognoza p5 = new Prognoza();
        p5.setId(5);
        p5.setgrad("Ohrid");
        p5.setmomentalna("36stepeni");
        p5.setmomentalnastatus("soncevo");
        p5.setin3hours("33stepeni");
        p5.setstatus3h("soncevo");
        p5.setin1day("32stepeni");
        p5.setstatus1d("oblacno");
        p5.setin2days("29stepeni");
        p5.setstatus2d("vrnezlivo");
        p5.setin3days("30stepeni");
        p5.setstatus3d("oblacno");
        prognozi.add(p5);
        users = new ArrayList<User>();
        User user1 = new User();
        user1.setUsername("sara");
        user1.setPassword("sara123");
        List<Integer> user1searches = new ArrayList<Integer>();
        user1searches.add(1);
        user1searches.add(2);
        user1searches.add(4);
        user1searches.add(5);
        user1.setSearches(user1searches);
        users.add(user1);
        User user2 = new User();
        user2.setUsername("angela");
        user2.setPassword("angela123");
        List<Integer> user2searches = new ArrayList<Integer>();
        user2searches.add(1);
        user2.setSearches(user2searches);
        users.add(user2);
    }

    public void getAllUsers() {
        for (int i=0; i<users.size(); i++) {
            System.out.println(users.get(i).getUsername());
        }
    }

    public void getAllPrognozi() {
        for (int i=0; i < prognozi.size(); i++) {
        	String grad = prognozi.get(i).getgrad();
        	String mom = prognozi.get(i).getmomentalna();
        	String momstat = prognozi.get(i).getmomentalnastatus();
        	String tricasa = prognozi.get(i).getin3hours();
        	String tricasastat = prognozi.get(i).getstatus3h();
        	String den1 = prognozi.get(i).getin1day();
        	String den1stat = prognozi.get(i).getstatus1d();
        	String den2 = prognozi.get(i).getin2days();
        	String den2stat = prognozi.get(i).getstatus2d();
        	String den3 = prognozi.get(i).getin3days();
        	String den3stat = prognozi.get(i).getstatus3d();
            System.out.println("Prognoza za:" + " " + grad  + "/n " + "Momentalna temperatura:" + " " +  mom + ", " + momstat + "/n " + "Temperaturata za 3h:" + " " +  tricasa + ", " + tricasastat + "/n " + "Temperatura za slednite 3 dena:" + " " + den1  + ", " + den1stat +  "/n "  + den2  + ", " + den2stat + "/n" + den3   + ", " + den3stat);
		}
	}
	
	public void getMomentalnaByCity(String city) {
		if (city.equals(" ") ) {
			return;
		}
        for(int i = 0; i < prognozi.size(); i++){
            if(city == prognozi.get(i).getgrad()){
                String grad = prognozi.get(i).getgrad();
                String momentalna = prognozi.get(i).getmomentalna();
                String statusmom = prognozi.get(i).getmomentalnastatus();
                System.out.println("Momentalna temperatura vo" + grad + ":" + momentalna + "," + statusmom);
            }
        }		
}

public void getin3HoursByCity(String city) {
    if (city.equals(" ") ) {
        return;
    }
    for(int i = 0; i < prognozi.size(); i++){
        if(city == prognozi.get(i).getgrad()){
            String grad = prognozi.get(i).getgrad();
            String momentalna = prognozi.get(i).getin3hours();
            String statusmom = prognozi.get(i).getstatus3h();
            System.out.println("Temperatura za 3h vo" + grad + ":" + momentalna + "," + statusmom);
        }  
    }   
} 
public void get3DaysByCity(String city) {
    if (city.equals(" ") ) {
        return;
    }
    for(int i = 0; i < prognozi.size(); i++){
        if(city == prognozi.get(i).getgrad()){
            String grad = prognozi.get(i).getgrad();
                String prv = prognozi.get(i).getin1day();
                String statusprv = prognozi.get(i).getstatus1d();
                String vtor = prognozi.get(i).getin2days();
                String statusvtor = prognozi.get(i).getstatus2d();
                String tret = prognozi.get(i).getin3days();
                String statustret = prognozi.get(i).getstatus3d();
        System.out.println("Temperaturata vo narednite 3 dena vo" + grad + "/n" + "Den 1:" + " " + prv + "," + statusprv + "/n" + "Den 2:" + " " + vtor + "," + statusvtor + "/n" + "Den 3:" + " " + tret + "," + statustret );
            }
        }
    } 
	
    
    public void getPrognoziByUser(String username) {
		List<Integer> prognoziByUser = new ArrayList<Integer>();
		Integer x = 0;
		for (int i=0; i<users.size(); i++){
			if (users.get(i).getUsername().equals(username)){
				x = 1;
				if(users.get(i).getSearches() != null) {
					prognoziByUser = users.get(i).getSearches();
				} 
			} 
		}
		if (x == 0) {
			System.out.println("The user does not exist.");
			return;
		}
		if (prognoziByUser.size() > 0) {
			for(int j=0; j<prognoziByUser.size(); j++) {
				for (int z=0; z<prognozi.size(); z++) {
					if (prognoziByUser.get(j) == prognozi.get(z).getId()) {
						System.out.println(prognozi.get(z).getgrad());
						break;
					}
				}
			}
		} else {
			System.out.println("The user has no searches.");
		}
	}
}

