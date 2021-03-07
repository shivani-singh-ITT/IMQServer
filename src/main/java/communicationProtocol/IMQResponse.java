package communicationProtocol;

public class IMQResponse extends IMQProtocol{ 
  private String response;
  
  public void setResponse(String response) {
	  this.response=response;
  }
  
  public String getResponse() {
	  return response;
  }
}
