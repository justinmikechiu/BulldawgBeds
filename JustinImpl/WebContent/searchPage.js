function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 17,
          center: {lat: 33.950747, lng: -83.375816}
        });
        var geocoder = new google.maps.Geocoder();
        document.getElementById('submit').addEventListener('click', function() {
          geocodeAddress(geocoder, map);
        });
      }
      function geocodeAddress(geocoder, resultsMap) {
        var address = document.getElementById('address').value;
        geocoder.geocode({'address': address}, function(results, status) {
          if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            resultsMap.setZoom(17);
            var marker = new google.maps.Marker({
              map: resultsMap,
              position: results[0].geometry.location
            });
          } else {
            alert('Geocode was not successful for the following reason: ' + status);
          }
        });
      }

$(document).ready(function(){
	$("#submitSearch").click(function(){
		callServlet();
	});
	
	function callServlet(){
		
		var dataInfo = $("#searchFilter").serializeArray();
		dataInfo.push({name: "type", value: "search"});
		
		$.ajax({
			url : 'SearchServlet',
			data : dataInfo,
			success : function(responseText) {
				$('#tableview').html(responseText);
			}
		});
	}
	
	$("#applicationSubmit").click(function(){
		callServlet2();
	});

	function callServlet2(){

		var dataInfo = $("#applyPopUp").serializeArray();
		dataInfo.push({name: "type", value: "apply"});

		$.ajax({
			url : 'SearchServlet',
			data : dataInfo,
			success : function(responseText) {
				if(responseText == "invalid"){
					alert("This email does not exist. Please try again");
				}
				else{
					alert("Your message was successfully sent!")
					$('#my_popup').popup('hide');
				}
			}
		});
	}

	$.fn.popup.defaults.transition = 'all 0.3s';
	$.fn.popup.defaults.pagecontainer = '.container';
	$('#my_popup').popup();
});
