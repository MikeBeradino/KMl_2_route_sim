import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 
import java.io.File; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class KMl_2_route_sim extends PApplet {


ControlP5 cp5;


int myColorBackground = color(0,0,0);
RadioButton r1;
String inputpath;
String outputpath;
String inputpathtemp;
String outputpathtemp;
PImage img;

XML xml; 
PrintWriter output;
PrintWriter outputtemp;
int b = 0;
int c = 0;
int d = 0;
int mode=0;
float slidervalue;

//println(cp5.getController("Speed_in_KM_per_h").getValue());

//(cp5.getToggle("Save").getValue());

public void setup() {
  
   colorMode(RGB);
  img = loadImage("data/background1.jpg");
  cp5 = new ControlP5(this);
  r1 = cp5.addRadioButton("radioButton")
         .setPosition(20,50)
         .setSize(40,20)
         .setColorForeground(color(120))
         .setColorActive(color(255))
         .setColorLabel(color(255))
         .setItemsPerRow(1)
         .setSpacingColumn(50)
         .addItem("loop",1)
         .addItem("foward",2)
         .addItem("backward",3);
        
    for(Toggle t:r1.getItems()) {
       t.getCaptionLabel().setColorBackground(color(255,80));
       t.getCaptionLabel().getStyle().moveMargin(-7,0,0,-3);
       t.getCaptionLabel().getStyle().movePadding(7,0,0,3);
       t.getCaptionLabel().getStyle().backgroundWidth = 45;
       t.getCaptionLabel().getStyle().backgroundHeight = 13;
     }
   
      cp5 = new ControlP5(this);
      
      cp5.addSlider("Speed_in_KM_per_h")
     .setRange(0, 30)
     .setPosition(20, 170)
     .setSize(150, 40)
     .setFont(createFont("arial",14))
     .setValue(5)
     //.setNumberOfTickMarks(5)
     //.moveTo(controlWindow)
     ;
     
       // reposition the Label for controller 'slider'
  //cp5.getController("Speed_in_KM/h").getValueLabel().align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE).setPaddingX(0);
  cp5.getController("Speed_in_KM_per_h").getCaptionLabel().align(ControlP5.LEFT, ControlP5.BOTTOM_OUTSIDE).setPaddingX(0);
     
       
      cp5.addBang("Save")
    // .setValue(0)
     .setPosition(200,250)
     .setSize(80,40)
     // .setFont(createFont("arial",20))
     .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)
     ;   
     
     
     cp5.addBang("INPUT_file_kml")
     .setPosition(200,50)
     .setSize(80,40)
      //.setFont(createFont("arial",20))
     .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)
     ;  
     
     
      cp5.addBang("OUT_PUT_Folder")
     .setPosition(200,100)
     .setSize(80,40)
      //.setFont(createFont("arial",20))
     .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)
     ;
 }
public void draw() {
image(img,0,0);
 
}

public void keyPressed() {
  switch(key) {
    case('0'): r1.deactivateAll(); break;
    case('1'): r1.activate(0); break;
    case('2'): r1.activate(1); break;
    case('3'): r1.activate(2); break;
   }
}
public void controlEvent(ControlEvent theEvent) {
  if(theEvent.isAssignableFrom(Textfield.class)) {
    println("controlEvent: accessing a string from controller '"
            +theEvent.getName()+"': "
            +theEvent.getStringValue()
            );
  }

if(theEvent.isFrom(r1)) {
   // print("got an event from "+theEvent.getName()+"\t");
    for(int i=0;i<theEvent.getGroup().getArrayValue().length;i++) {
      print(PApplet.parseInt(theEvent.getGroup().getArrayValue()[i]));
      }
    println("\t "+theEvent.getValue());
    myColorBackground = color(PApplet.parseInt(theEvent.getGroup().getValue()*50),0,0);
  }
}






public void Save(float slidervalue) {
 
  
 
   
    if  ( c==1 ) {
      println("b=1");
      println("Save");
    
    
    outputtemp = createWriter(outputpathtemp); /// out to tempfile
    XML xml = loadXML (inputpath);
    XML[] coordinates2 = xml.getChildren("Document/Folder/Placemark/LineString/coordinates");
    outputtemp.println("<gpx>");
  for (int i = 0; i < coordinates2.length; i++) {
    String s = coordinates2[i].getContent(); 
    String[] nums = (splitTokens(s, ", ")); // splits and cleans data


if (mode==2 ) {
    
   //foward
    for (int count = 0; count < nums.length; count += 3){
    outputtemp.println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    }
println("foward");
d=1;
b=0;
break;

}

if (mode==1) {
   
   //foward
     for (int count = 0; count < nums.length; count += 3){
    outputtemp.println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
 
 }
    //backward
    for (int count = (nums.length-6); count > 0; count -= 3){
    outputtemp.println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
   } 
  println("loop");
  d=1;
  b=0;
  break;
 }
  
if (mode==3) {
   //backward
    for (int count = (nums.length-6); count > 0; count -= 3){
    outputtemp.println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    println("<wpt  " + "lat=" + "'" + nums[1+count] + "'" +  " lon=" + "'" + nums [count] + "'" + "></wpt>"  );
    
   } 
   println("backward");
   d=1;
   b=0;
   break;
 } 
 
 
   }       
     outputtemp.flush(); // Writes the remaining data to the file
     outputtemp.println("</gpx>");
     outputtemp.close(); // Finishes the file
    
 
   
 }  










  println(cp5.getController("Speed_in_KM_per_h").getValue());
  slidervalue = cp5.getController("Speed_in_KM_per_h").getValue();

if  (d == 1 ){
  
  xml = loadXML(inputpathtemp); /// tempread
  XML[] children = xml.getChildren("wpt");
  //// neeed to input path----> tempfile write---> tempfile read------> outputpath
  
  output = createWriter(outputpath);
  output.println("<gpx>");
for (int i = 0; i < children.length-1; i++) {
  float lat1 = children[i].getFloat("lat");
  float long1 = children[i].getFloat("lon");
  float lat2 = children[i+1].getFloat("lat");
  float long2 = children[i+1].getFloat("lon");
  float radius = 6371; // the approximate radius of the Earth in kilometers
  float dlat = radians(lat2 - lat1);
  float dlong = radians(long2 - long1);
  float a = sin(dlat/2)*sin(dlat/2) + cos(radians(lat1))*cos(radians(lat2))*sin(dlong/2)*sin(dlong/2);
  float c = 2 * atan2(sqrt(a), sqrt(1-a));
  float d = radius * c;
   //println(lat1 + ", " +long1 );
  //println(d);
////////////////////////////////////
//float delta_per_s;
//delta_per_s= (d*2); // takes d and makes  M_per_s
// d = chainge in distance in KM
 

 
float speedrequest_m_per_s;
float speedlimit;
speedrequest_m_per_s = (slidervalue *.277777777777778f); // makes km/h to m/s
speedlimit = (speedrequest_m_per_s);

float d_in_meters;
float points; //the number of points to move to delta
float seconds; // goves the points form xcode .5s the value of 1s 


d_in_meters = d*1000;
points = d_in_meters/speedlimit;
seconds = points *2;

println(d);
println(d_in_meters);
println(speedlimit);

/// need a caculatoin of for speed contorl by points

for (float lim = 1; lim <= seconds; lim++) {
  float x = lerp(lat1, lat2, lim/seconds);
  float y = lerp(long1, long2, lim/seconds);

  String sx = str(x);
  String sy = str(y);
 
   sx = ("<wpt  "+ "lat='" + sx + "' " + "lon='" + sy + "'></wpt>");
  if ((lat1 != lat2) && (long1 != long2 )){
    
     println(x,y);
     output.println(sx);
       
       }
     output.flush(); // Writes the remaining data to the file
     } 
   }
   
   
   output.println("</gpx>");
   println("got this far");
 
  output.close(); // Finishes the file

File file = new File(outputpathtemp);

file.delete();

//delete();
// outputpathtemp.delete(); 

exit();  
 } 
}
  







public void OUT_PUT_Folder (){
   selectOutput("Select a file to write to:", "folderSelected" );
 }




public void folderSelected(File selection) {
  if (selection == null ) {
    println("Window was closed or the user hit cancel.");
  } else  {
    println("User selected " + selection.getAbsolutePath());
    outputpath = (selection.getAbsolutePath()+".gpx");
    outputpathtemp = (selection.getAbsolutePath());
    inputpathtemp = selection.getAbsolutePath();
    output = createWriter(outputpath);
    outputtemp = createWriter(outputpathtemp);
    c=1;
    println ("c=1");
    }
} 

public void INPUT_file_kml (){
 selectInput("Select a file to process:", "fileSelected");
 }
 
 public void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    println("User selected " + selection.getAbsolutePath());
    inputpath = selection.getAbsolutePath(); // path for input file sellection
    
   }
}

public void radioButton(int a) {
  if (a==1)
  {mode=1;
   println("1");
    }else if (a==2)
    {mode=2;
    println("2");
       }else if (a==3)
       {mode=3;
       println("3");
       
     }  
}
  public void settings() {  size(300,300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "KMl_2_route_sim" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
