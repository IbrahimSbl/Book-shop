package Product;

public abstract class Product {
	 String preview;
	 
	 public Product(String previeww){
	 preview=previeww;
	 }
	 public void setpreview(String pre){
	 this.preview=pre;
	 }
	 public String getpreview(){
	 return this.preview;
	 }
	}
