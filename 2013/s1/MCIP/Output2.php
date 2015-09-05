<?php
$name=$_GET["name"];
echo $name;
$path = "database/" . $name . ".wav";
?>
<br>
<?php
 if (file_exists($path))
      {
      echo "Please listen to the pronunciation.";
      }
    else
      {
      echo "This name has not recorded yet.";
      }
?>
<!DOCTYPE html>
<html>
 <head>
  <title>Test</title>

  <script src="jquery.min.js"> </script>
  <script src="jRecorder.js"> </script>

  <style>
  li {display:inline; margin-right:10px;}
  </style>

 </head>
 <body>
 <br>
 <br>
 <br>
 <audio controls>
  <source src= '<?php echo $path; ?>' type="audio/wav">
 </audio>
 <hr>
  <div>
  <script>
  $.jRecorder(
  { 
   host : 'http://localhost/~Yifei/MCID/acceptfile.php?filename=temp',
   callback_started_recording: function(){callback_started(); },
   callback_stopped_recording: function(){callback_stopped(); },
   callback_activityLevel: function(level){callback_activityLevel(level); },
   callback_activityTime: function(time){callback_activityTime(time); },
   callback_finished_sending: function(time){ callback_finished_sending() },
   swf_path : 'jRecorder.swf',
  }
  );
  </script>
  </div>

  <div style="background-color: #eeeeee;border:1px solid #cccccc">
  Time: <span id="time">00:00</span>
  </div>

  <div>
  Level: <span id="level"></span>
  </div>  

  <div id="levelbase" style="width:200px;height:20px;background-color:#ffff00">
   <div id="levelbar" style="height:19px; width:2px;background-color:red"></div>
  </div>

  <div>
  Status: <span id="status"></status>
  </div> 

  <div>
  <input type="button" id="record" value="Imitate" style="color:red">  
  <input type="button" id="stop" value="Stop">
  </div>

<hr>

<div>
<script>
  (function() {
    var cx = '010904382031041111388:u2odagidga4';
    var gcse = document.createElement('script');
    gcse.type = 'text/javascript';
    gcse.async = true;
    gcse.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') +
        '//www.google.com/cse/cse.js?cx=' + cx;
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(gcse, s);
  })();
</script>
<gcse:search></gcse:search>
</div>

<p>
<a href="http://www.behindthename.com/" target="_blank"> Go to search the meaning of names </a> <br>
</p>

 </body>
</html>

<script type="text/javascript">
 $('#record').click(function(){
                               $.jRecorder.record(30);
                              })
 $('#stop').click(function(){
                             $.jRecorder.stop();
                            })
 $('#send').click(function(){
                             $.jRecorder.sendData();
                            })
                  function callback_finished(){
                                    $('#status').html('Recording is finished');
                                              }
                  function callback_started(){
                                     $('#status').html('Recording is started');
                                             }
                  function callback_error(code){
                                      $('#status').html('Error, code:' + code);
                                               }
                  function callback_stopped(){
                                 $('#status').html('Stop request is accepted');
                                             }
                  function callback_finished_recording(){
                              $('#status').html('Recording event is finished');
                                                        }
                  function callback_finished_sending(){
 $('#status').html('File has been sent to server mentioned as host parameter');
                                                      }
                  function callback_activityLevel(level){
                                                       $('#level').html(level);
                               if(level == -1){
                               $('#levelbar').css("width",  "2px");
                               }else{
                               $('#levelbar').css("width", (level * 2)+ "px");}
                                                        }
                  function callback_activityTime(time){
                   //$('.flrecorder').css("width", "1px"); 
                   //$('.flrecorder').css("height", "1px"); 
                    $('#time').html(time);
                                                      }
</script>
