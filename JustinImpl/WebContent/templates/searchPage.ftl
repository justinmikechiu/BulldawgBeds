<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BullDawg Search</title>
    <link href="searchPage.css" type="text/css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
    <!-- Include jQuery Popup Overlay -->
    <script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>
    <script src="searchPage.js"></script>
    <!--This is the link so that we can use Open Sans on our html page-->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
</head>
<body>
<h3>Google Maps addition</h3>
<div id="searchStuff">
    <div id ="menubar">
        <p>Hello</p>
        <form action="Servlet" method="post">
            <ul class="menu">
                <li class="menuItem">Location
                    <select name="location">
                        <option value="north">North</option>
                        <option value="south">South</option>
                        <option value="east">East</option>
                        <option value="west">West</option>
                    </select>
                </li>
                <li class="menuItem">Beds
                    <select name="bedNumber">
                        <option value="one">1</option>
                        <option value="two">2</option>
                        <option value="three">3</option>
                        <option value="four">4</option>
                    </select>
                </li>
                <li class="menuItem">Date
                    <select name="date">
                        <option value="fall">Fall</option>
                        <option value="spring">Spring</option>
                        <option value="summer">Summer</option>
                    </select>
                </li>
                <li class="menuItem">Price Range
                    <select name="price">
                        <option value="range1">$200-$399</option>
                        <option value="range2">$400-$599</option>
                        <option value="range3">$600-$799</option>
                        <option value="range4">$800+</option>
                    </select>
                </li>
                <li class="menuItem">
                    <input type="submit" name ="searchFilter" value="Search"/>
                </li>
            </ul>
        </form>
    </div>
    <div id="tableview">
        <table class="infoTable">
            <tr>
                <td class="imageTD"><img class="images" src="http://image1.apartmentguide.com/imgr/03a6173620512c30fee0547c3b074cba/724-" alt="Apartment Image"/></td>
                <td class="infoTD">
                    <ul class="searchInfo">
                        <li class="infoItem">Name: Reserve at Athens</li>
                        <li class="infoItem">Address: Wonderland</li>
                        <li class="infoItem">Price: $425</li>
                        <li class="infoItem">Beds: 4</li>
                    </ul>
                </td>
                <td id="apply">
                    <!--Important to not change the class of this button or else it will not work.-->
                    <button class="my_popup_open">Apply</button>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="map"></div>
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyClxRJ-Xmbc4CiuztFlLQfXwYjcrz8LfsA&callback=initMap"></script>
<!--This is the pop up div that will show up in the middle of the screen.
The name of the id is what makes it open so do not change its id.-->
<div id ="my_popup">
    <p>Please enter your information below so that the owner of this property may contact you later.</p>
    <form id="applyPopUp" action="Servlet" method="post">
        <input type="text" name="name" placeholder="Name"><br/>
        <input type="text" name="phone" placeholder="Phone"><br/>
        <input type="text" name="email" placeholder="Email"><br/>
        <input type="text" name="message" placeholder="Message"><br/>
        <div class="center">
            <input id="applicationSubmit" type="submit" name="sendApplication" value="Apply"/>
        </div>
    </form>
</div>
</body>
</html>