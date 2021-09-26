<jsp:useBean id    = "prog" 
	     type  = "vreme.Vreme" 
	     class = "vreme.Vreme"> 
  <% 

    String verb = request.getMethod();
    java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()));
    String str = "";
    String line = br.readLine();

    while (line != null) {
      str += line;
      line = br.readLine();
    }
    str = str.replace("'", "");
    prog.setServletContext(application);
    String [] elements = str.split(",",13);

    if (elements[0].equals("users")) {
      if (elements[1].equals("login")) {
        out.println(prog.login(elements[2], elements[3]));
      } else if (elements[1].equals("register")) {
        out.println(prog.register(elements[2], elements[3], elements[4]));
      } else if (elements[1].equals("unregister")) {
        out.println(prog.unregister(elements[2], elements[3]));
      } else if (elements[1].equals("getAllUsers")) {
        out.println(prog.getAllUsers());
      } else if (elements[1].equals("usersByPrognozi")) {
        out.println(prog.getUsersByBook(elements[2]));
      } else {
        out.println("Invalid.");
      }
    } else if (elements[0].equals("prognoza")){
      if (elements[1].equals("add")) {
        elements[2] = elements[2].replace("_", " ");
        elements[3] = elements[3].replace("_", " ");
        out.println(prog.addPrognoza(elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12]));
      } else if (elements[1].equals("delete")) {
        out.println(prog.deleteP(elements[2]));
      } else if (elements[1].equals("update")) {
        if(elements[3] != "none"){
          elements[3] = elements[3].replace("_", " ");
        }
        if (elements[4] != "none"){
          elements[4] = elements[4].replace("_", " ");
        }
        if (elements[5] != "none"){
            elements[5] = elements[5].replace("_", " ");
          }
          if (elements[6] != "none"){
            elements[6] = elements[6].replace("_", " ");
          }
          if (elements[7] != "none"){
            elements[7] = elements[7].replace("_", " ");
          }
          if (elements[8] != "none"){
            elements[8] = elements[8].replace("_", " ");
          }
          if (elements[9] != "none"){
            elements[9] = elements[9].replace("_", " ");
          }
          if (elements[10] != "none"){
            elements[10] = elements[10].replace("_", " ");
          }
          if (elements[11] != "none"){
            elements[11] = elements[11].replace("_", " ");
          }
          if (elements[12] != "none"){
            elements[12] = elements[12].replace("_", " ");
          }
        out.println(prog.update(elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12]));
      } else if (elements[1].equals("searchesByUser")) {
        out.println(prog.getSearchesByUser(elements[2]));
      } else if (elements[1].equals("getAllPrognozi")) {
        out.println(prog.getAllPrognozi());
      } else if (elements[1].equals("byCity")) {
        out.println(prog.getPrognozaByCity(elements[2], elements[3], elements[4], elements[5], elements[6], elements[7], elements[8], elements[9], elements[10], elements[11], elements[12]));
      }
      else {
        out.println("Invalid.");
      }
    }
  %>
</jsp:useBean> 
