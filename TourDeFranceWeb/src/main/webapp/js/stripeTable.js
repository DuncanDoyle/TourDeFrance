//add event function from http://jszen.blogspot.com/
function addEvent(obj, evType, fn){ 
 if (obj.addEventListener){ 
   obj.addEventListener(evType, fn, true); 
   return true; 
 } else if (obj.attachEvent){ 
   var r = obj.attachEvent("on"+evType, fn); 
   return r; 
 } else { 
   return false; 
 } 
}

//find html element by class function. From http://www.snook.ca/archives/000370.php
function getElementsByClassName(classname){
        var rl = new Array();
        var re = new RegExp('(^| )'+classname+'( |$)');
        var ael = document.getElementsByTagName('*');
        var op = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;
        if (document.all && !op) ael = document.all;
        for(i=0, j=0 ; i<ael.length ; i++) {
                if(re.test(ael[i].className)) {
                        rl[j]=ael[i];
                        j++;
                }
        }
        return rl;
}

//stripe a table with default css and add mouseover effect. Based on: http://validweb.nl/artikelen/javascript/better-zebra-tables/
//Edited by cody lindley
var stripetable = function() {
		var tables = getElementsByClassName("stripetables");	

		for(var x=0;x!=tables.length;x++){
			var table = tables[x];
			if (! table) { return; }
			
			var tbodies = table.getElementsByTagName("tbody");
			
			for (var h = 0; h < tbodies.length; h++) {
				var even = true;
				var trs = tbodies[h].getElementsByTagName("tr");
				
				for (var i = 0; i < trs.length; i++) {
					trs[i].onmouseover=function(){
						this.className += " tm_over"; return false
					}
					trs[i].onmouseout=function(){
						this.className = this.className.replace("tm_over", ""); return false
					}
					
					if(even)
						trs[i].className += " even";
					
					even = !even;
				}
			}
		}
	}


//event added using the addEvent() function above
addEvent(window, 'load', stripetable);

//stripe a list. Based on: http://www.renegadezen.com/blog/article/53/javascript-zebra-lists
//Edited by cody lindley
var stripedefinition = function() {

    var even1 = false;
    var even2 = false;
  
    var evenColor = arguments[1] ? arguments[1] : "#F3F3F3";
    var oddColor = arguments[2] ? arguments[2] : "#fff";

    var dls = getElementsByClassName("stripedefinitions");
    if (! dls) { return; }
	
	for(var x=0;x!=dls.length;x++){
			var dl = dls[x];
			
    var dtelements = dl.getElementsByTagName("dt");

    for (var h = 0; h < dtelements.length; h++) {
      var dtlist = dtelements[h];
    dtlist.style.backgroundColor = even1 ? evenColor : oddColor;
	
        even1 =  ! even1; 
    }

	var ddelements = dl.getElementsByTagName("dd");
	  // and iterate through them...
    for (var k = 0; k < ddelements.length; k++) {
      var ddlist = ddelements[k];  
    ddlist.style.backgroundColor = even2 ? evenColor : oddColor;

        even2 =  ! even2; 
    }
	}
	
  }

//event added using the addEvent() function above
addEvent(window, 'load', stripedefinition);

//stripe a list. Based on: http://www.renegadezen.com/blog/article/53/javascript-zebra-lists
//Edited by cody lindley
var stripelist = function() {

    var even = false;
  
    var evenColor = arguments[1] ? arguments[1] : "#F3F3F3";
    var oddColor = arguments[2] ? arguments[2] : "#fff";

    var uls = getElementsByClassName("stripelists");
    if (! uls) { return; }

	
	for(var x=0;x!=uls.length;x++){
			var ul = uls[x];
	
    var lis = ul.getElementsByTagName("li");

    for (var h = 0; h < lis.length; h++) {
	 var list = lis[h];  
	list.onmouseover=function(){this.className = "lm_over"; return false}
	list.onmouseout=function(){this.className = this.className.replace("lm_over", ""); return false}
    list.style.backgroundColor = even ? evenColor : oddColor;
    even =  ! even;

					
    }
	
	}
	
  }

//event added using the addEvent() function above
addEvent(window, 'load', stripelist);
