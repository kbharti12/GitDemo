import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(Payload.coursePrice());
		int count =js.getInt("courses.size()");
		System.out.println(count);
		
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		String titleofcourse=js.getString("courses.title[0]");
		System.out.println(titleofcourse);
		
				
		for(int i=0;i<count;i++) {
			
			String courseTitle=js.getString("courses.title["+i+"]");
			System.out.println(js.get("courses["+i+"].price").toString());
			
			System.out.println(courseTitle);
					}
		
		System.out.println("Print no. of copies for RPA course: ");
		
	for(int i=0;i<count;i++)
		 {
			  String courseTitles=js.get("courses["+i+"].title");
			  if(courseTitles.equalsIgnoreCase("RPA"))
			  {
				  int copies=js.get("courses["+i+"].copies");
				  System.out.println(copies);
				  System.out.println(copies);
				  System.out.println(copies);
				  System.out.println(copies);
				  break;
			  }
			
			  
		 }
	}
	

}
