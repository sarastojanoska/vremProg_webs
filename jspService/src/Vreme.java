package vreme;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.*;
import java.io.*;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.beans.XMLEncoder;
import javax.servlet.ServletContext;
import java.util.Arrays;
import users.User;
import prognoza.Prognoza;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Vreme {
	private List<Prognoza> prognozi;
	private List<User> users;
	private ServletContext sctx;

	public Vreme() {
	}

	public void setServletContext(ServletContext sctx) {
		this.sctx = sctx;
	}

	public ServletContext getServletContext() {
		return this.sctx;
	}

	public void populatePrognozi() {
		if (getServletContext() == null)
			return;
		String filename = "/WEB-INF/data/prognoza.db";
		InputStream in = sctx.getResourceAsStream(filename);

		if (in != null) {
			try {
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(isr);
				prognozi = new ArrayList<Prognoza>();
				String record = null;
				while ((record = reader.readLine()) != null) {
					String[] parts = record.split("!");
					Prognoza p = new Prognoza();
					p.setId(Integer.valueOf(parts[0]));
					p.setgrad(parts[1]);
					p.setmomentalna(parts[2]);
					p.setmomentalnastatus(parts[3]);
					p.setin3hours(parts[4]);
                    p.setstatus3h(parts[5]);
                    p.setin1day(parts[6]);
                    p.setstatus1d(parts[7]);
                    p.setin2days(parts[8]);
                    p.setstatus2d(parts[9]);
                    p.setin3days(parts[10]);
                    p.setstatus3d(parts[11]);
					String usersArray = parts[12];
					if (!usersArray.equals("/")) {
						List<String> usersByProg = new ArrayList<String>();
						String[] users = usersArray.split(",");
						for (int i = 0; i < users.length; i++) {
							usersByProg.add(users[i]);
						}
						p.setUsers(usersByProg);
					}
					prognozi.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void populateUsers() {
		if (getServletContext() == null)
			return;
		String filename = "/WEB-INF/data/users.db";
		InputStream in = sctx.getResourceAsStream(filename);

		if (in != null) {
			try {
				InputStreamReader isr = new InputStreamReader(in);
				BufferedReader reader = new BufferedReader(isr);
				users = new ArrayList<User>();
				String record = null;
				while ((record = reader.readLine()) != null) {
					String[] parts = record.split("!");
					User p = new User();
					p.setUsername(parts[0]);
					p.setPassword(parts[1]);
					String progByArray = parts[2];
					if (!progByArray.equals("/")) {
						List<Integer> progByUser = new ArrayList<Integer>();
						String[] progs = progByArray.split(",");
						for (int i = 0; i < progs.length; i++) {
							progByUser.add(Integer.valueOf(progs[i]));
						}
						p.setSearches(progByUser);
					}
					users.add(p);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean writePrognoziToDB(List<Prognoza> prog) {
		if (getServletContext() == null)
			return false;
		String progRecord = "";

		try {
			File f = new File(getServletContext().getRealPath("/WEB-INF/data/prognoza.db"));
			FileWriter fout = new FileWriter(f);
			int size = prog.size();
			int i = 0;

			while (size > 0) {
				progRecord = prog.get(i).getId() + "!" + prog.get(i).getgrad() + "!" + prog.get(i).getmomentalna() + "!"
						+ prog.get(i).getmomentalnastatus() + "!" + prog.get(i).getin3hours() + "!" + prog.get(i).getstatus3h() + "!"+ prog.get(i).getin1day()+ "!" + prog.get(i).getstatus1d()+ "!" + prog.get(i).getin2days()+ "!"+ prog.get(i).getstatus2d()+ "!"+ prog.get(i).getin3days()+ "!"+ prog.get(i).getstatus3d();
				if (prog.get(i).getUsers() == null) {
					progRecord += "/" + "\n";
				} else {
					for (int j = 0; j < prog.get(i).getUsers().size(); j++) {
						if (j == (prog.get(i).getUsers().size() - 1)) {
							progRecord += prog.get(i).getUsers().get(j);
						} else {
							progRecord += prog.get(i).getUsers().get(j) + ",";
						}
					}
					progRecord += "\n";
				}
				fout.append(progRecord);
				i++;
				size--;
			}
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean writeUsersToDB(List<User> users) {
		if (getServletContext() == null)
			return false;
		String userRecord = "";

		try {
			File f = new File(getServletContext().getRealPath("/WEB-INF/data/users.db"));
			FileWriter fout = new FileWriter(f);
			int size = users.size();
			int i = 0;

			while (size > 0) {
				userRecord = users.get(i).getUsername() + "!" + users.get(i).getPassword() + "!";
				if (users.get(i).getSearches() == null) {
					userRecord += "/" + "\n";
				} else {
					for (int j = 0; j < users.get(i).getSearches().size(); j++) {
						if (j == (users.get(i).getSearches().size() - 1)) {
							userRecord += users.get(i).getSearches().get(j);
						} else {
							userRecord += users.get(i).getSearches().get(j) + ",";
						}
					}
					userRecord += "\n";
				}
				fout.append(userRecord);
				i++;
				size--;
			}
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean login(String username, String password) {
		populateUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				if (users.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean register(String username, String p1, String p2) {
		populateUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				return false;
			}
		}
		if (!(p1.equals(p2))) {
			return false;
		}
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(p1);
		users.add(newUser);
		writeUsersToDB(users);
		return true;
	}

	public boolean unregister(String username, String password) {
		populateUsers();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				if (users.get(i).getPassword().equals(password)) {
					users.remove(i);
					writeUsersToDB(users);
					return true;
				}
			}
		}
		return false;
	}

	public String getAllUsers() {
		populateUsers();
		return usersToJSON(users);
	}

	public boolean addPrognoza(String grad, String momentalna, String momentalnastatus, String in3hours, String status3h, String in1day, String status1d, String in2days, String status2d, String in3days, String status3d) {
		populatePrognozi();
		for (int i = 0; i < prognozi.size(); i++) {
			if (prognozi.get(i).getgrad().equals(grad)) {
				return false;
			}
		}
		Integer newId = prognozi.get(prognozi.size() - 1).getId() + 1;
		Prognoza newp = new Prognoza();
		newp.setgrad(grad);
		newp.setmomentalna(momentalna);
		newp.setmomentalnastatus(momentalnastatus);
		newp.setin3hours(in3hours);
        newp.setstatus3h(status3h);
        newp.setin1day(in1day);
        newp.setstatus1d(status1d);
        newp.setin2days(in2days);
        newp.setstatus2d(status2d);
        newp.setin3days(in3days);
        newp.setstatus3d(status3d);
		newp.setId(newId);
		prognozi.add(newp);
		writePrognoziToDB(prognozi);
		return true;
	}

	public boolean deleteP(String Id) {
		populatePrognozi();
		Integer id = Integer.valueOf(Id);
		for (int i = 0; i < prognozi.size(); i++) {
			if (prognozi.get(i).getId().equals(id)) {
				prognozi.remove(i);
				writePrognoziToDB(prognozi);
				return true;
			}
		}
		return false;
	}

	public boolean updatePrognoza(String Id, String grad, String momentalna, String momentalnastatus, String in3hours, String status3h, String in1day, String status1d, String in2days, String status2d, String in3days, String status3d) {
		populatePrognozi();
		Integer id = Integer.valueOf(Id);
		for (int i = 0; i < prognozi.size(); i++) {
			if (prognozi.get(i).getId().equals(id)) {
				if (!grad.equals("none")) {
					prognozi.get(i).setgrad(grad);
				}
				if (!momentalna.equals("none")) {
					prognozi.get(i).setmomentalna(momentalna);
				}
				if (!momentalnastatus.equals("none")) {
					prognozi.get(i).setmomentalnastatus(momentalnastatus);
				}
				if (!in3hours.equals("none")) {
					prognozi.get(i).setin3hours(in3hours);
				}
                if (!status3h.equals("none")) {
					prognozi.get(i).setstatus3h(status3h);
				}
                if (!in1day.equals("none")) {
					prognozi.get(i).setin1day(in1day);
				}
                if (!status1d.equals("none")) {
					prognozi.get(i).setstatus1d(status1d);
				}
                if (!in2days.equals("none")) {
					prognozi.get(i).setin2days(in2days);
				}
                if (!status2d.equals("none")) {
					prognozi.get(i).setstatus2d(status2d);
				}
                if (!in3days.equals("none")) {
					prognozi.get(i).setin3days(in3days);
				}
                if (!status3d.equals("none")) {
					prognozi.get(i).setstatus3d(status3d);
				}
				writePrognoziToDB(prognozi);
				return true;
			}
		}
		return false;
	}


	public String getAllPrognozi() {
		populatePrognozi();
		return prognoziToJSON(prognozi);
	}

	public String getSearchesByUser(String username) {
		populateUsers();
		populatePrognozi();
		List<Integer> searchesByUser = new ArrayList<Integer>();
		Integer x = 0;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				x = 1;
				if (users.get(i).getSearches() != null) {
					searchesByUser = users.get(i).getSearches();
				}
			}
		}
		if (x == 0) {
			return "The user does not exist.";
		}
		List<Prognoza> progg = new ArrayList<Prognoza>();
		if (searchesByUser.size() > 0) {
			for (int j = 0; j < searchesByUser.size(); j++) {
				for (int z = 0; z < prognozi.size(); z++) {
					if (searchesByUser.get(j) == prognozi.get(z).getId()) {
						progg.add(prognozi.get(z));
						break;
					}
				}
			}
			return prognoziToJSON(progg);
		} else {
			return "The user has no searches.";
		}
	}

	public String getUsersByProg(String id) {
		populatePrognozi();
		populateUsers();
		Integer Id = Integer.valueOf(id);
		List<String> usersByProg = new ArrayList<String>();
		Integer x = 0;
		for (int i = 0; i < prognozi.size(); i++) {
			if (prognozi.get(i).getId() == Id) {
				x = 1;
				if (prognozi.get(i).getUsers() != null) {
					usersByProg = prognozi.get(i).getUsers();
				}
			}
		}
		if (x == 0) {
			return "The search does not exist.";
		}
		List<User> usrs = new ArrayList<User>();
		if (usersByProg.size() > 0) {
			for (int j = 0; j < usersByProg.size(); j++) {
				for (int z = 0; z < users.size(); z++) {
					if (usersByProg.get(j).equals(users.get(z).getUsername())) {
						usrs.add(users.get(z));
						break;
					}
				}
			}
			return usersToJSON(usrs);
		} else {
			return "This weather forecast hasn't been made yet.";
		}
	}
    public String getPrognozaByCity(String city) {
        populatePrognozi();
        List<Prognoza> prog = new ArrayList<Prognoza>();
        for(int i = 0; i < prognozi.size(); i++){
            if(prognozi.get(i).getgrad().equals(city)){
                prog.add(prognozi.get(i));
            }
        }	
        if (prog.size() == 0) {
			return "We have no forecasts for this city.";
		} else {
			return prognoziToJSON(prog);
		}	
}

	private String prognoziToJSON(List<Prognoza> prognozi) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		for (int i=0; i<prognozi.size(); i++) {
			try {
				String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(prognozi.get(i));
				json = json + jsonString + "\n";
			}catch (Exception e) {
				return e.getMessage();
			}
		}
		return json;
	}

	private String usersToJSON(List<User> users) {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		for (int i=0; i<users.size(); i++) {
			try {
				String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users.get(i));
				json = json + jsonString + "\n";
			}catch (Exception e) {
				return e.getMessage();
			}
		}
		return json;
	}
}
