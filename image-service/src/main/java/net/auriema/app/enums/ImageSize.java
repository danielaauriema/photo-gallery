package net.auriema.app.enums;

public enum ImageSize {
	RAW ("raw"),
	THUMB ("thumb");
	
	private final String size;   

	ImageSize(String size) {
		this.size = size;		
	}
	
	public String getSize() {
		return this.size;
	}
	
    public static ImageSize fromString(String text) {
        for (ImageSize b : ImageSize.values()) {
            if (b.size.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }	

}
