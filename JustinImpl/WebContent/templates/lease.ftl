<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lease Your Apartment</title>
    <link href="form.css" type="text/css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#submitLease").click(function(){
                if(checkValid()) {
                    callServlet();
                }
            });
            function callServlet(){
                var dataInfo = $("#leaseForm").serializeArray();
                dataInfo.push({name: "type", value: "lease"});
                //We can get the image source to push to the database because we know that the form is valid.
                var image = $("#myImg").attr('src');
                dataInfo.push({name: "imgSrc" , value: image});
                $.ajax({
                    url : 'SearchServlet',
                    data : dataInfo,
                    success : function(responseText) {
                    	if(responseText == "valid"){
                    		alert("You have succesfully added a Lease");
                        	window.location.href = "searchPage.html";
                    	}
                    	else{
                    		alert("There was an error. Please check that your fields are correct and try again.");
                    	}
                    }
                });
            }
            function checkValid(){
                var form = $('#leaseForm');
                form.validate();
                var checkValid = form.valid();
                return checkValid;
            }
        });
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#myImg')
                        .attr('src', e.target.result)
                        .width(150)
                        .height(200);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</head>
<body>
<h1>Lease your apartment</h1>

<div id = "form">
    <p></p>
    <form id="leaseForm" action="Servlet" method="post">
        Name of apartment: <input type="text" name="name" required/> <br/>
        Email: <input type="text" name="email" required/> <br/>
        Address: <input type="text" name="address" required/> <br/>
        Location: <select name="location">
        <option value="north">North</option>
        <option value="south">South</option>
        <option value="east">East</option>
        <option value="west">West</option>
    </select><br/>
        Price: <input type="text" name="price" required/> <br/>
        # of beds: <select name="beds">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
    </select><br/>
        Semester:  <select name="semester">
        <option value="fall">fall</option>
        <option value="spring">spring</option>
        <option value="summer">summer</option>
    </select><br/>
        <img id="myImg" src="http://shashgrewal.com/wp-content/uploads/2015/05/default-placeholder.png" alt="your image" width="150" height="150"/><br/>
        Image: <input type='file' name="pic" accept="image/*" onchange="readURL(this)" required/><br/>

        <p></p>
        <button id="submitLease" type="button">Submit</button>
        <p></p>
    </form>

</div>
</body>
</html>