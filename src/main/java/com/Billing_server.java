package com; 


	import model.Billing; 
	//For REST Service
	import javax.ws.rs.*; 
	import javax.ws.rs.core.MediaType; 
	//For JSON
	import com.google.gson.*; 
	//For XML
	import org.jsoup.*; 
	import org.jsoup.parser.*; 
	import org.jsoup.nodes.Document; 
	@Path("/Items") 
	public class  Billing_server
	{ 
	 Billing billObj = new Billing(); 
	 
	 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	
	 { 
		return billObj.readItems(); 
	 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("userCode") String Code, 
	 
	 @FormParam("userName") String Name, 
	 @FormParam("userRate") String rate,
	 @FormParam("userConsumed") String consumed,
	 @FormParam("userCharge") String charge) 
	{ 
	 String output = billObj.insertItem(Code, Name, rate, consumed, charge); 
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <userID>
	 String userID = doc.select("userID").text(); 
	 String output = billObj.deleteItem(userID); 
	return output; 
	}

	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String userID = itemObject.get("userID").getAsString(); 
	 String userCode = itemObject.get("userCode").getAsString(); 
	 String userName = itemObject.get("userName").getAsString(); 
	 String userRate = itemObject.get("useRate").getAsString(); 
	 String userConsumed = itemObject.get("userConsumed").getAsString();
	 String userCharge = itemObject.get("userCharge").getAsString();
	 String output = billObj.updateItem(userID, userCode, userName, userRate, userConsumed, userCharge); 
	return output; 
	}
	}
