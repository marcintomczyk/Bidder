<#import "layout/defaultLayout.ftl" as layout>
<@layout.bidderLayout>

<head>
  <@sj.head />	
  <script language="JavaScript" src="/Bidder/js/jquery.validate.js" type="text/javascript"></script>

  <script type="text/javascript">

	var validator;

	$(document).ready(function() {
	
		$.validator.addMethod('double', function(value, element) {
		return /-?[0-9]+\.[0-9]{2}$/.test(value);	
		}, "Please enter a correct number, format xxxx.xx");
	
	
			validator = $("#createBidForm").validate({
				
				rules: {
			        bid_id: {
			            required: true,
			            maxlength: 5,
			            number: true
			        },
			        cost: {
			            required: true,
			            maxlength: 7,
			            double:true
			        },
			        sites: {
			            required: true
			        }
			    },
			    
			    messages: {
			        bid_id: "Please enter a valid id (integer, max 5 letters).",
			        cost: "Please enter a valid cost (format x.xx - xxxx.xx).",
			        sites: "Please select a site/sites."
			    },
			    
			    submitHandler: $.submitBid,
    			
    			invalidHandler: function(){
        			//$.ajax(...);
        			//alert("INVALID FORM");
    			}
    			
			}); //end of validate
	
		}); // end of ready function
	
	$.submitBid = function() {
   		
   			var bid_id = parseInt($('input:text[name=bid_id]').val());
			var cost = parseFloat($('input:text[name=cost]').val()); 
			var errorMessage = $('#bidErrorMessage');
			
			var selectedSites = "";

            $checkedSitesCollection =  $("input:checkbox[name=sites]:checked");
        
                $checkedSitesCollection.each( function(){
                
                selectedSites += $(this).val() + ",";
        
                });
             
			var data = {
				data : {
					"bid_id" : bid_id,
					"urls" : selectedSites,
					"cost" : cost 
				}
			};
	
			$.ajax({
				type : "POST",
				url : "http://localhost:8080/Bidder/bid/submitBid.action",
				data : JSON.stringify(data),
				async : false,
				contentType : "application/json; charset=utf-8",
				success : function(response) {

					// I just want to invoke an action, I do not pass an object as a parameter
					// because in this case it is not needed.
					//The goal of the action "viewBids" is to display all bids
					//which are in db and not the bid which was just created 
					
					var resp = JSON.stringify(response);
					//alert("response in success function: " + resp);
					
					//can have errors others than standard validation's errors
					//these are errors from database like duplicated id etc.
					//these kind of errors are available only after submitting form
					if(response.actionErrors){
        				errorMessage.text('Error: ' + response.actionErrors);
					}else
					{
						window.location.replace("viewBids.action");
					}
					
					return false;
					
				}, //end of success
    				error: function(responseError){
	    				var errors = JSON.stringify(responseError);
        				errorMessage.text('Unexpected Error: ' + responseError);
	        			return false;
    				} //end of error
			}); //end of ajax's call
			
			return false;
		} // end of submitBid function
	
	
</script>
</head>
 
<div id="content">
   <fieldset>
      <legend>Add New Bid</legend>

    <@s.form id="createBidForm">
    
    	<div id="bidInternalErrors">
    		<label id="bidErrorMessage" class="bidInternalEror"/>
    	</div>
    
        <div id="bid_id">
            <label for="bid_id" class="error"/>
            <@s.textfield id="bid_id" label="Id" name="bid_id"/>
        </div>
		
		<div id="cost">
            <label for="cost" class="error"/>
            <@s.textfield id="cost" label="Cost" name="cost"/>
        </div>
    	
    	<div id="sites">
            <label for="sites" class="error"/>
            <@s.checkboxlist id="sites" label="Select a site"
            				 list="{'www.onet.pl', 'www.wp.pl', 'www.yahoo.com'}"
            				  name="sites"/>
        </div>
        
       <@s.submit id="submitBidButton" value="Create a Bid"/>
    </@s.form>
 
    </fieldset> 
  </div>
</@layout.bidderLayout>