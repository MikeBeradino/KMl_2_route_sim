


Java / Processing utility to convert Google .KML maps to .GPX routes with speed values. I use it with Xcode to simulate gps movements while debugging. Lately I have been using it to spoof GPS coordinates in PokeMon Go.

There is a packaged .app in the application.macosx. To run you need some Java (8): http://java.com/en/download/

The .pde can be compiled by loading it and running in processing. https://processing.org/download/


Usage is simple.

Load file -- pick a .Kml (Creat and download from google MY MAPS not maps!)

Select   -- output location.

Select   -- route direction froward / reverse / loop.

Select   -- emulated speed 5km/h is default, ~12km/h is usually pretty smooth. 

Things get buggy on the high and low end. 

Save    

Load in Xcode under GPS sim and enjoy. :)





