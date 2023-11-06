package model.dto;
// Call소켓부분에서 dto 구분 짓기 위해 만들었음.
public class RequestDto {
	
	 private String type;
	 private ServiceDto serviceDto;
	 private RiderDto riderDto;
	 
	 public RequestDto() {
		// TODO Auto-generated constructor stub
	}
	 
	
	public RequestDto(String type, ServiceDto serviceDto, RiderDto riderDto) {
		super();
		this.type = type;
		this.serviceDto = serviceDto;
		this.riderDto = riderDto;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ServiceDto getServiceDto() {
		return serviceDto;
	}
	public void setServiceDto(ServiceDto serviceDto) {
		this.serviceDto = serviceDto;
	}
	public RiderDto getRiderDto() {
		return riderDto;
	}
	public void setRiderDto(RiderDto riderDto) {
		this.riderDto = riderDto;
	}
	@Override
	public String toString() {
		return "RequestDto [type=" + type + ", serviceDto=" + serviceDto + ", riderDto=" + riderDto + "]";
	}
	 
	 
	
	
}
