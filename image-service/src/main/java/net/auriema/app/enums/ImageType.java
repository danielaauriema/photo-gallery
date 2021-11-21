package net.auriema.app.enums;

public enum ImageType {
	JPG ("jpg"),
	PNG ("png");
	
	private final String type;   

	ImageType(String type) {
		this.type = type;		
	}
	
	public String getType() {
		return this.type;
	}
	
    public static ImageType fromString(String text) {
        for (ImageType b : ImageType.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }	
	

}
