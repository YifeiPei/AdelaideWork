
<!DOCTYPE html>
<html>
 <head>
  <title>Output interface</title>
  <script src="jquery-2.0.2.min.js"></script>
  <style type="text/css">
  input.styledbtn { 
                   font-size:16px;
                   font-family:Tahoma,sans-serif;
                   color:#FFFFFF;
                   width:180px;
                   background-color:#01DF01;
                   border-top-style:solid;
                   border-top-color:#99AA99;
                   border-top-width:2px;
                   border-bottom-style:solid;
                   border-bottom-color:#99AA99;
                   border-bottom-width:2px;
                   border-left-style:solid;
                   border-left-color:#BBCCBB;
                   border-left-width:5px;
                   border-right-style:solid;
                   border-right-color:#BBCCBB;
                   border-right-width:5px;
                  }
  table.altrowstable {
	font-family: verdana,arial,sans-serif;
	font-size:16px;
	color:#333333;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
  }
  table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
        background-color:#336699;
  }
  table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
  }
  .oddrowcolor{
	background-color:#ffffff;
  }
  .evenrowcolor{
	background-color:#f0f8ff;
  }

  </style>

  <script type="text/javascript">
  function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
  }
  window.onload=function(){
	altRows('alternatecolor');
  }
  </script>

  <script language="javascript">

  function goLite(FRM,BTN)
  {
   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#CCE8CC";
  }

  function goDim(FRM,BTN)
  {
   window.document.forms[FRM].elements[BTN].style.backgroundColor = "#01DF01";
  }

  </script>

<script>
 function popup(x,y) 
 {
  var n = "_";
  var str = x.concat(n,y);
  var win = window.open("../MCID/Output2.php?name="+str,"Audio Output","height=500,width=800,left=400,top=100,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes");
 }
</script>

 </head>
 <body>


 <table 
 border="1"
 cellpadding="15"
 cellspacing="2"
 class="altrowstable" 
 id="alternatecolor">
 <tr>
 <th> Course </th>
 <th colspan="3"> MCI Project </th>
 </tr>
 <tr>
 <th> Student ID </th>
 <th> Last Name </th>
 <th> First Name </th>
 <th> Academic Program </th>
 </tr>
 <tr>
 <td> 1602101 </td>
 <td>

 <form name="btnform1"
       action="">
 <input type="button"
        name="btn1"
        id="1602101"
        class="styledbtn"
        value="Alhajaj"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform2"
       action="">
 <input type="button"
        name="btn2"
        id="1602101"
        class="styledbtn"
        value="Mohammed Hussain"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1218941 </td>
 <td>  

 <form name="btnform3"
       action="">
 <input type="button"
        name="btn3"
        id="1218941"
        class="styledbtn"
        value="Guo"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform4"
       action="">
 <input type="button"
        name="btn4"
        id="1218941"
        class="styledbtn"
        value="Zilong"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1602409 </td>
 <td>  

 <form name="btnform5"
       action="">
 <input type="button"
        name="btn5"
        id="1602409"
        class="styledbtn"
        value="Hernandez Jimenez"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform6"
       action="">
 <input type="button"
        name="btn6"
        id="1602409"
        class="styledbtn"
        value="Juan Manuel"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1616861 </td>
 <td> 

 <form name="btnform7"
       action="">
 <input type="button"
        name="btn7"
        id="1616861"
        class="styledbtn"
        value="Hong"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform8"
       action="">
 <input type="button"
        name="btn8"
        id="1616861"
        class="styledbtn"
        value="Yu"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1184863 </td>
 <td>  

 <form name="btnform9"
       action="">
 <input type="button"
        name="btn9"
        id="1184863"
        class="styledbtn"
        value="Huang"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform10"
       action="">
 <input type="button"
        name="btn10"
        id="1184863"
        class="styledbtn"
        value="Kun"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1161334 </td>
 <td>  

 <form name="btnform11"
       action="">
 <input type="button"
        name="btn11"
        id="1161334"
        class="styledbtn"
        value="Merchant"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform12"
       action="">
 <input type="button"
        name="btn12"
        id="1161334"
        class="styledbtn"
        value="Scott Gallum"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
 <tr>
 <td> 1611648 </td>
 <td>  

 <form name="btnform13"
       action="">
 <input type="button"
        name="btn13"
        id="1611648"
        class="styledbtn"
        value="Pei"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform14"
       action="">
 <input type="button"
        name="btn14"
        id="1611648"
        class="styledbtn"
        value="Yifei"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>
</td>
 
 <tr>
 <td> 1602101 </td>
 <td>

 <form name="btnform15"
       action="">
 <input type="button"
        name="btn15"
        id="1611333"
        class="styledbtn"
        value="Zhang"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td>  

 <form name="btnform16"
       action="">
 <input type="button"
        name="btn16"
        id="1602101"
        class="styledbtn"
        value="Xin"
        onMouseOver="goLite(this.form.name,this.name)"
        onMouseOut="goDim(this.form.name,this.name)"
        onClick="popup(this.id,this.value)"
        >
 </form>

 </td>
 <td> M. Computing & Innovation </td>
 </tr>

 </table>
 </body>
</html>
