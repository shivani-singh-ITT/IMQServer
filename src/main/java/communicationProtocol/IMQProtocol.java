package communicationProtocol;

public class IMQProtocol{ 
	private String dataFormat;
	private String version;
	private String sourceUrl;
	private String destinationUrl; 

	 public String getDataFormat(){
		return dataFormat;
	}

	public void setDataFormat(String dataFormat){
		this.dataFormat = dataFormat;
	}
	
	public String getVersion(){
		return version;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getSourceUrl(){
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl){
		this.sourceUrl = sourceUrl;
	}

	public String getDestinationUrl(){
		return destinationUrl;
	}

	public void setDestinationUrl(String destinationUrl){
		this.destinationUrl = destinationUrl;
	}
}
